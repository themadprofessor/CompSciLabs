#include <stdio.h>

int main() {
	char buff[2] = {0};

	while (scanf("%c", buff) != EOF) {
		printf("%c", buff[0]);
	}	
}
