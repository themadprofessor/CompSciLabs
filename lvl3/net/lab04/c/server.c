#include <stdio.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define eprintf(...) fprintf(stderr, __VA_ARGS__)
#define PORT 5006
#define BUFFER_LEN 1024

int main() {
    struct sockaddr_in bind_addr;
    struct sockaddr recv_addr;
    int fd;
    ssize_t received;
    char buffer[BUFFER_LEN] = {0};
    socklen_t recv_addr_len = sizeof(recv_addr);

    fd = socket(AF_INET, SOCK_DGRAM, 0);

    if (fd == -1) {
        perror("failed to open socket");
        return EXIT_FAILURE;
    }
    eprintf("opened socket\n");

    memset(&bind_addr, 0, sizeof(bind_addr));
    bind_addr.sin_family = AF_INET;
    bind_addr.sin_port = htons(PORT);
    bind_addr.sin_addr.s_addr = htonl(INADDR_ANY);

    if (bind(fd, (const struct sockaddr *) &bind_addr, sizeof(bind_addr)) != 0) {
        perror("failed to bind socket");
        return EXIT_FAILURE;
    }
    eprintf("bound socket\n");

    received = recvfrom(fd, buffer, sizeof(buffer)-1, 0, &recv_addr, &recv_addr_len);
    if (received < 0) {
        perror("failed to receive data");
        return EXIT_FAILURE;
    }
    eprintf("received %ld bytes\n", received);

    printf("%s\n", buffer);

    if (close(fd) == -1) {
        perror("failed to close socket");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}