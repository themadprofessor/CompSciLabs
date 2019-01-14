#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdbool.h>
#include <errno.h>
#include <string.h>
#include "errconv.h"

int main() {
    int socket = socket(AF_INET, SOCK_STREAM, 0);
    if (socket == -1) {
        fprintf(stderr, "Failed to initialise socket! [%s]\n", strerror(errno));
        return -1;
    }

    struct sockaddr_in addr;
    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(80);

    if (bind(socket, (struct sockaddr*) &addr, sizeof(addr)) == -1) {
        fprintf(stderr, "Failed to bind socket! [%s]\n", strerror(errno));
        return -2;
    }

    int backlog = 10;
    if (listen(socket, backlog) == -1) {
        fprintf(stderr, "Failed to listen to socket! [%s]\n", strerror(errno));
        return -3;
    }

    while (true) {
        struct sockaddr_in client_addr;
        socklen_t client_addr_len = sizeof(client_addr);

        int connection = accept(socket, (struct sockaddr*) &client_addr, &client_addr_len);
        if (connection == -1) {

        }
    }

    return 0;
}