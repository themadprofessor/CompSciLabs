package ads2.cw1;

/**
 * Created by wim on 28/11/2017.
 * The public interface of this class is provided by Cache
 * All other methods are private. 
 * You must implement/complete all these methods
 * You are allow to create helper methods to do this, put them at the end of the class 
 */
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

class FullyAssocLiFoCache implements Cache {

    final private static boolean VERBOSE = true;

    final private int CACHE_SZ;
    final private int CACHELINE_SZ;
    final private int CL_MASK;
    final private int CL_SHIFT;

    // WV: because the cache replacement policy is "Last In First Out" you only need to know the "Last Used" location
    // "Last Used" means accessed for either read or write
    // The helper functions below contain all needed assignments to last_used_loc so I recommend you use these.

    private int last_used_loc;
    // WV: Your other data structures here
    // Hint: You need 4 data structures
    // - One for the cache storage
    private int[][] cache_storage;
    // - One to manage locations in the cache
    private Deque<Integer> location_stack;
    // And because the cache is Fully Associative:
    // - One to translate between memory addresses and cache locations
    private Map<Integer, Integer> address_to_cache_loc;
    // - One to translate between cache locations and memory addresses  
    private Map<Integer, Integer> cache_loc_to_address;
    


    FullyAssocLiFoCache(int cacheSize, int cacheLineSize) {

        CACHE_SZ =  cacheSize;
        CACHELINE_SZ = cacheLineSize;
        CL_MASK = CACHELINE_SZ - 1;
        Double cls = Math.log(CACHELINE_SZ)/Math.log(2);
        CL_SHIFT = cls.intValue();

        last_used_loc = CACHE_SZ/CACHELINE_SZ - 1;
        // WV: Your initialisations here
        cache_storage = new int[cacheSize/cacheLineSize][cacheLineSize];
        location_stack = new ArrayDeque<>(cache_storage.length);
        address_to_cache_loc = new HashMap<>();
        cache_loc_to_address = new HashMap<>();

        //Populate location stack with all locations
        for (int i = 0; i < cache_storage.length; i++) {
            location_stack.push(i);
        }

        if (VERBOSE) {
            System.out.println("Initialised Cache");
            System.out.println("Cache Storage :" + cache_storage.length + "x" + cacheLineSize);
            System.out.println("Location Stack :" + location_stack);
        }
    }

    public void flush(int[] ram, Status status) {
        if (VERBOSE) System.out.println("Flushing cache");
        // WV: Your other data structures here
        address_to_cache_loc.forEach((addr, loc) ->
                System.arraycopy(cache_storage[loc], 0, ram, cache_line_start_mem_address(addr), CACHELINE_SZ));
        cache_storage = new int[cache_storage.length][CACHELINE_SZ];
        location_stack.clear();
        address_to_cache_loc.clear();
        cache_loc_to_address.clear();

        //Populate location stack with all locations
        for (int i = 0; i < cache_storage.length; i++) {
            location_stack.push(i);
        }
        status.setFlushed(true);
    }

    public int read(int address,int[] ram,Status status) {
        return read_data_from_cache( ram, address, status);
    }

    public void write(int address,int data, int[] ram,Status status) {
        write_data_to_cache(ram, address, data, status);
    }

    // The next two methods are the most important ones as they implement read() and write()
    // Both methods modify the status object that is provided as argument

    private void write_data_to_cache(int[] ram, int address, int data, Status status){
        status.setReadWrite(false); // i.e. a write
        status.setAddress(address);
        status.setData(data);
        status.setEvicted(false);
        // Your code here
        // The cache policy is write-back, so the writes are always to the cache. 
        // The update policy is write allocate: on a write miss, a cache line is loaded to cache, followed by a write operation. 
         // ...
        status.setHitOrMiss(true);
        if (!address_in_cache_line(address)) {
            status.setHitOrMiss(false);
            read_from_mem_on_miss_wrapper(ram, address, status);
        }
        update_cache_entry(address, data);
        status.setFreeLocations(location_stack.size());
    }
        
    private int read_data_from_cache(int[] ram,int address, Status status){
        status.setReadWrite(true); // i.e. a read
        status.setAddress(address);
        status.setEvicted(false);
        status.setHitOrMiss(true); // i.e. a hit
        // Your code here
        // Reads are always to the cache. On a read miss you need to fetch a cache line from the DRAM
        // If the data is not yet in the cache (read miss),fetch it from the DRAM
        // Get the data from the cache
         // ...
        int data = 0;
        if (!address_in_cache_line(address)) {
            status.setHitOrMiss(false);
            read_from_mem_on_miss_wrapper(ram, address, status);
        }

        data = fetch_cache_entry(address);

        status.setFreeLocations(location_stack.size());
        status.setData(data);
        return data;
    }

    //Ensure the location stack has a value and update status
    private void read_from_mem_on_miss_wrapper(int[] ram, int address, Status status) {
        if (cache_is_full()) {
            status.setEvicted(true);
            status.setEvictedCacheLoc(last_used_loc);
            status.setEvictedCacheLineAddr(cache_loc_to_address.get(last_used_loc));
            write_to_mem_on_evict(ram, last_used_loc);
        }
        read_from_mem_on_miss(ram, address);
    }

    // You might want to use the following methods as helpers
    // but it is not mandatory, you may write your own as well
    
    // On read miss, fetch a cache line    
    private void read_from_mem_on_miss(int[] ram,int address){
        int[] cache_line = new int[CACHELINE_SZ];
        int loc;
        // Your code here
         // ...
        int start_addr = cache_line_start_mem_address(cache_line_address(address));
        loc = get_next_free_location();

        System.arraycopy(ram, start_addr, cache_line, 0, CACHELINE_SZ);
        cache_storage[loc] = cache_line;
        address_to_cache_loc.put(cache_line_address(address), loc);
        cache_loc_to_address.put(loc, cache_line_address(address));

        last_used_loc=loc;
   }

    // On write, modify a cache line
    private void update_cache_entry(int address, int data){
        int loc;
         // Your code here
         // ...
        loc = address_to_cache_loc.get(cache_line_address(address));
        cache_storage[loc][cache_entry_position(address)] = data;
        last_used_loc=loc;
    }

    // When we fetch a cache entry, we also update the last used location
    private int fetch_cache_entry(int address){
        int[] cache_line;
        int loc;
         // Your code here
         // ...
        loc = cache_line_address(address);
        cache_line = new int[CACHELINE_SZ];
        System.arraycopy(cache_storage[loc], 0, cache_line, 0, cache_line.length);

        last_used_loc=loc;

        return cache_line[cache_entry_position(address)];
    }

    // Should return the next free location in the cache
    private int get_next_free_location(){
         // Your code here
         // ...
        if (cache_is_full()) {
            return -1;
        } else {
            return location_stack.pop();
        }
    }

    // Given a cache location, evict the cache line stored there
    private void evict_location(int loc){
         // Your code here
         // ...
        location_stack.push(loc);
        address_to_cache_loc.remove(cache_loc_to_address.get(loc));
        cache_loc_to_address.remove(loc);
        if (VERBOSE) {
            System.out.println("Evicted: " + loc);
        }
    }

    private boolean cache_is_full(){
         // Your code here
         // ...
        return location_stack.size() == 0;
    }

    // When evicting a cache line, write its contents back to main memory
    private void write_to_mem_on_evict(int[] ram, int loc){

        int evicted_cl_address;
        int[] cache_line;
        if (VERBOSE) System.out.println("Cache line to RAM: ");
        // Your code here
         // ...
        evicted_cl_address = cache_line_start_mem_address(cache_loc_to_address.get(loc));
        cache_line = cache_storage[loc];
        System.arraycopy(cache_line, 0, ram, evicted_cl_address, CACHELINE_SZ);

        evict_location(loc);
    }

    // Test if a main memory address is in a cache line stored in the cache
    // In other words, is the value for this memory address stored in the cache?
    private boolean address_in_cache_line(int address) {
        // Your code here
         // ...
       return address_to_cache_loc.containsKey(cache_line_address(address));
    }

    // Given a main memory address, return the corresponding cache line address
    private int cache_line_address(int address) {
        return address>>CL_SHIFT;
    }

    // Given a main memory address, return the corresponding index into the cache line
    private int cache_entry_position(int address) {
        return address & CL_MASK;
    }
    // Given a cache line address, return the corresponding main memory address
    // This is the starting address of the cache line in main memory
    private int cache_line_start_mem_address(int cl_address) {
        return cl_address<<CL_SHIFT;
    }

}
