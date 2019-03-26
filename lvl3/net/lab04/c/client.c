#include <stdio.h>
#include <string.h>
#include <arpa/inet.h>
#include <sys/socket.h>
#include <netdb.h>
#include <stdlib.h>

// Some macro magic to convert a macro's value into a string
#define STR_ARG(s) __STR_ARG(s)
#define __STR_ARG(s) #s

#define PORT 5006
#define PORT_STR STR_ARG(PORT)
#define eprintf(...) fprintf(stderr, __VA_ARGS__)

int address_to_socket(char* address, void* dest) {
    int result;
    int fd = -1;
    struct sockaddr_in* in_addr = dest;

    // Attempt to convert the given address into an IPv4 address
    result = inet_pton(AF_INET, address, &(in_addr->sin_addr));

    // If it failures, run a DNS lookup on the address, otherwise use the address
    if (result != 1) {
        struct addrinfo hints;
        struct addrinfo* addr_list;
        struct addrinfo* curr_addr;

        eprintf("not given address, resolving address\n");

        // Initialise DNS hints to only allow IPv4 datagram addresses
        memset(&hints, 0, sizeof(hints));
        hints.ai_family = AF_INET;
        hints.ai_socktype = SOCK_DGRAM;

        // Run DNS lookup and handle failure
        result = getaddrinfo(address, PORT_STR, &hints, &addr_list);
        if (result != 0) {
            eprintf("failed to resolve address: %s", gai_strerror(result));
            return -1;
        }

        // Iterate over the list of addresses and attempt to open a socket to each address, breaking upon first success
        for (curr_addr = addr_list; curr_addr != NULL; curr_addr = curr_addr->ai_next) {
            // Attempt to open socket, run next iteration if it fails
            fd = socket(curr_addr->ai_family, curr_addr->ai_socktype, curr_addr->ai_protocol);
            if (fd == -1) {
                continue;
            }

            // Break from the loop as the socket successfully opened
            eprintf("found address\n");
            break;
        }

        // Free the address list
        freeaddrinfo(addr_list);

        // If no addresses successfully opened a socket (fd == -1), return -1, otherwise return the socket
        if (fd == -1) {
            return -1;
        }

        // Update dest point with new address and return the socket
        memcpy(dest, curr_addr->ai_addr, curr_addr->ai_addrlen);
        return fd;
    } else {
        // Since the address is a valid address, open a socket
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
    struct sockaddr_in address; // Address to send data to
    char msg[] = "Hello World"; // Data to be sent

    // Ensure the address is given as an argument
    if (argc < 2) {
        eprintf("invalid arg count\n");
        return EXIT_FAILURE;
    }

    // Initialise address to be IPv4 and send to port number PORT
    memset(&address, 0, sizeof(address));
    address.sin_family = AF_INET;
    address.sin_port = htons(PORT);

    // Open a socket
    fd = address_to_socket(argv[1], &address);

    // Handle failure
    if (fd == -1) {
        return EXIT_FAILURE;
    }

    // Send data and handle failure
    if (sendto(fd, msg, sizeof(msg), 0, (struct sockaddr*) &address, sizeof(address)) < 0) {
        perror("failed to send data");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}