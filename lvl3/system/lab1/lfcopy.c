#include <stdio.h>
#include <string.h>
#include "lfcopy.h"
#define MAX_LINE 100

/*int main() {
	char line[MAX_LINE] = {0};
	while (readline(line, MAX_LINE) != 0) {
		writeline(line);
	}
}*/

int readline(char line[], int max) {
	if (fgets(line, max, stdin) == NULL) {
		return 0;
	} else {
		return strlen(line);
	}
}

int writeline(const char line[]) {
	fputs(line, stdout);
	return strlen(line);
}
