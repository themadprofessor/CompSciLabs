#include "lfcopy.h"
#include <stdbool.h>
#include <stdio.h>
#include <ctype.h>
#define MAX_LINE 100

int main() {
	char buff[MAX_LINE] = {0};
	int count = 0;
	bool in_word = false;
	int line_len;

	while ((line_len = readline(buff, MAX_LINE)) > 0) {
		for (int i = 0; i < line_len; i++) {
			if (in_word && !isalpha(buff[i])) {
				in_word = false;
			} else if (!in_word && isalpha(buff[i])) {
				count++;
				in_word = true;
			}
		}
	}

	printf("%d words\n", count);
}
