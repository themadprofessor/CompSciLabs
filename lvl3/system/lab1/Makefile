CC= clang
CFLAGS= -Wall -Werror --std=c99

%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

repeat: repeat.o

lfcopy: lfcopy.o

lines: lines.o lfcopy.o

words: words.o lfcopy.o

collapse: collapse.o

clean:
	rm -f *.o repeat lfcopy lines words
