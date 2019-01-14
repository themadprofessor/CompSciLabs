#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <stdbool.h>
#include <errno.h>
#include <string.h>
#include <netdb.h>
#include <unistd.h>

int main(int argc, char* argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Invalid number of arguments\n");
        return -1;
    }

    struct addrinfo hints;
    struct addrinfo* addrinfo_list;
    int i;

    memset(&hints, 0, sizeof(hints));
    hints.ai_family = PF_UNSPEC;
    hints.ai_socktype = SOCK_STREAM;
    if ((i = getaddrinfo(argv[1], "25565", &hints, &addrinfo_list)) != 0) {
        fprintf(stderr, "Failed to lookup IP address: [%s]\n", gai_strerror(i));
        return -2;
    }

    struct addrinfo* current_info;
    int socket;

    for (current_info = addrinfo_list; current_info != NULL; current_info = current_info->ai_next) {
        socket = socket(current_info->ai_family, current_info->ai_socktype, current_info->ai_protocol);
        if (socket == -1) {
            continue;
        }
        if (connect(socket, current_info->ai_addr, current_info->ai_addrlen) == -1) {
            close(socket);
            continue;
        }
        break;
    }

    if (current_info == NULL) {
        fprintf(stderr, "Failed to find IP address!\n");
        return -2;
    }

    return 0;
}