#include <stdio.h>
#include <ctype.h>

int main() {
	char buff[1] = {0};

	while (scanf("%c", buff) != EOF) {
		printf("%c", buff[0]);
		if (isspace(buff[0])) {
			while (scanf("%c", buff) != EOF && isspace(buff[0])) {
			}
			printf("%c", buff[0]);
		}
	}

	printf("\n");
}
