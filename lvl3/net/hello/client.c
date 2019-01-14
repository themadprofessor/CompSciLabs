#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdbool.h>
#include <errno.h>
#include <string.h>

int main() {
    int socket = socket(AF_INET, SOCK_STREAM, 0);
    if (socket == -1) {
        perror("Failed to initialise socket! [%s]");
        return -1;
    }

    return 0;
}