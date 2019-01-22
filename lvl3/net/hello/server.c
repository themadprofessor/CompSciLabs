#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdbool.h>
#include <errno.h>
#include <string.h>

#define BUFSIZE 1500

int main() {
    int fd = socket(AF_INET, SOCK_STREAM, 0);
    if (fd == -1) {
        perror("Failed to initialise socket");
        return -1;
    }

    struct sockaddr_in addr;
    addr.sin_addr.s_addr = INADDR_ANY;
    addr.sin_family = AF_INET;
    addr.sin_port = htons(25565);

    if (bind(fd, (struct sockaddr*) &addr, sizeof(addr)) == -1) {
        perror("Failed to bind socket");
        return -2;
    }

    int backlog = 10;
    if (listen(fd, backlog) == -1) {
        perror("Failed to listen to socket");
        return -3;
    }

    while (true) {
        struct sockaddr_in client_addr;
        socklen_t client_addr_len = sizeof(client_addr);

        int connection = accept(fd, (struct sockaddr*) &client_addr, &client_addr_len);
        if (connection == -1) {
            perror("Failed to accept connect");
            continue;
        }

        ssize_t count;
        char buff[BUFSIZE] = {0};
        int flags = 0;
        count = recv(connection, buff, BUFSIZE, flags);
        if (count == -1) {
            perror("Failed to receive data");
        }
        printf("Received %ldB\n", count);

        if (send(connection, buff, (size_t) count, MSG_EOR) == -1) {
            perror("Failed to send data");
        }
    }
}