#include <stdio.h>

int main() {
	char buff[1] = {0};

	while (scanf("%c", buff) != EOF) {
		printf("%c", buff[0]);
	}	
}
