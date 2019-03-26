#include <stdio.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>

#define STR_ARG(s) __STR_ARG(s)
#define __STR_ARG(s) #s
#define PORT 5006
#define PORT_STR STR_ARG(PORT)
#define eprintf(...) fprintf(stderr, __VA_ARGS__)

int address_to_socket(char* address, void* dest) {
    int result;
    int fd = -1;
    struct sockaddr_in* in_addr = dest;

    result = inet_pton(AF_INET, address, &(in_addr->sin_addr));
    if (result != 1) {
        struct addrinfo hints;
        struct addrinfo* addr_list;
        struct addrinfo* curr_addr;

        eprintf("not given address, resolving address\n");

        memset(&hints, 0, sizeof(hints));
        hints.ai_family = AF_INET;
        hints.ai_socktype = SOCK_DGRAM;

        result = getaddrinfo(address, PORT_STR, &hints, &addr_list);
        if (result != 0) {
            eprintf("failed to resolve address: %s", gai_strerror(result));
            return -1;
        }

        for (curr_addr = addr_list; curr_addr != NULL; curr_addr = curr_addr->ai_next) {
            fd = socket(curr_addr->ai_family, curr_addr->ai_socktype, curr_addr->ai_protocol);
            if (fd == -1) {
                continue;
            }
            eprintf("found address\n");
            break;
        }

        freeaddrinfo(addr_list);

        return fd == -1 ? -1 : fd;

    } else {
        eprintf("given address, opening socket\n");
        fd = socket(AF_INET, SOCK_DGRAM, 0);
        if (fd == -1) {
            perror("failed to open socket with valid ip");
            return -1;
        }
        return fd;
    }
}

int main(int argc, char** argv) {
    int fd;
    struct sockaddr_in address;
    char msg[] = "Hello World";

    if (argc < 2) {
        eprintf("invalid arg count\n");
        return EXIT_FAILURE;
    }

    address.sin_family = AF_INET;
    address.sin_port = htons(PORT);

    fd = address_to_socket(argv[1], &address);

    if (fd == -1) {
        return EXIT_FAILURE;
    }

    if (sendto(fd, msg, sizeof(msg), 0, (struct sockaddr*) &address, sizeof(address)) < 0) {
        perror("failed to send data");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}