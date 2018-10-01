#include <stdio.h>
#include "lfcopy.h"
#define MAX_LINE 100

int main() {
	char buff[MAX_LINE] = {0};
	int count = 0;

	while (readline(buff, MAX_LINE) > 0) {
		count++;
	}

	printf("%d lines\n", count);
}
