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
    struct sockaddr_in bind_addr; // Address the socket will be bound to
    struct sockaddr recv_addr; // The address of the sender of the data
    int fd; //The socker
    ssize_t received; // Amount of data received
    char buffer[BUFFER_LEN] = {0}; // Buffer to hold the data sent, {0} zeros the buffer
    socklen_t recv_addr_len = sizeof(recv_addr);

    fd = socket(AF_INET, SOCK_DGRAM, 0); // Attempt to open the socket

    // Handle failure
    if (fd == -1) {
        perror("failed to open socket");
        return EXIT_FAILURE;
    }
    eprintf("opened socket\n");

    // Initialise binding address to be IPv4, at port number PORT and accept data from any sender
    memset(&bind_addr, 0, sizeof(bind_addr));
    bind_addr.sin_family = AF_INET;
    bind_addr.sin_port = htons(PORT);
    bind_addr.sin_addr.s_addr = htonl(INADDR_ANY);

    // Bind socket and handle failure
    if (bind(fd, (const struct sockaddr *) &bind_addr, sizeof(bind_addr)) != 0) {
        perror("failed to bind socket");
        return EXIT_FAILURE;
    }
    eprintf("bound socket\n");

    // Block until data is sent, then store it in the buffer and handle failure
    // The size of the buffer is reduced by 1 to make sure the buffer is always zero-terminated
    received = recvfrom(fd, buffer, sizeof(buffer)-1, 0, &recv_addr, &recv_addr_len);
    if (received < 0) {
        perror("failed to receive data");
        return EXIT_FAILURE;
    }
    eprintf("received %ld bytes\n", received);

    // Print the data
    printf("%s\n", buffer);

    // Cleanup
    if (close(fd) == -1) {
        perror("failed to close socket");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}