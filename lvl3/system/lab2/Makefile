CC= clang
CFLAGS= -Wall -Werror --std=c99

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

queueFIFO: qtest.o queueFIFO.o

queueLIFO: qtest.o queueLIFO.o

queuePrioLL: qtest.o queuePrioLL.o

queuePrioMultiLL: qtest.o queuePrioMultiLL.o

clean:
	rm -f *.o queueFIFO queueLIFO queuePrioLL
