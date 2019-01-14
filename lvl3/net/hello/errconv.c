//
// Created by stuart on 14/01/19.
//

#include <errno.h>
#include "errconv.h"

char* errconv_socket(int err) {
    switch (err) {
        case EAFNOSUPPORT:
            return "address family not supported";
        case EMFILE:
            return "all file descriptors open";
        case ENFILE:
            return "no file descriptors left";
        case EPROTONOSUPPORT:
            return "protocol not supported by address family";
        case EPROTOTYPE:
            return "socket type doesn't support protocol";
        case EACCES:
            return "insufficient privileges";
        case ENOBUFS:
            return "insufficient resources";
        case ENOMEM:
            return "insufficient memory";
        default:
            return "unknown error";
    }
}

char* errconv_bind(int err) {
    switch (err) {
        case EADDRINUSE:
            return "address already in use";
        case EADDRNOTAVAIL:
            return "address is not available";
        case EAFNOSUPPORT:
            return "address is not supported by address family of given socket";
        case EALREADY:
            return "assignment request already in progress";
        case EBADF:
            return "socket is not a valid file descriptor";
        case EINPROGRESS:
            return "assignment cannot be performed immediately, being performed asynchronously";
        case EINVAL:
            return "socket is already bound, has an invalid length for the address family, or has been shutdown";
        case ENOBUFS:
            return "insufficient resources";
        case ENOTSOCK:
            return "given socket is not a socket";
        case EOPNOTSUPP:
            return "socket does not support binding";
        case EACCES:
            return "insufficient privileges";
        case EDESTADDRREQ:
        case EISDIR:
            return "address is NULL";
        case EIO:
            return "IO error occurred";
        case ELOOP:
            return "symbolic loop link found";
        case ENAMETOOLONG:
            return "component of path name is too long";
        case ENOENT:
            return "component of path does not exist or path is empty";
        case ENOTDIR:
            return "path ends with a '/'";
        case EROFS:
            return "path is on a read-only filesystem";
        case EISCONN:
            return "socket is already connected";
        default:
            return "unknown error";
    }
}

char* errconv_listen(int err) {
    switch (err) {
        case EBADF:
            return "socket is not a valid file descriptor";
        case EDESTADDRREQ:
            return "socket is bound to local address and protocol doesn't support listening unbound sockets";
        case EINVAL:
            return "socket is already connected or is shutdown";
        case ENOTSOCK:
            return "given socket is not a socket";
        case EOPNOTSUPP:
            return "protocol doesn't support listening";
        case ENOBUFS:
            return "insufficient resources";
        case EACCES:
            return "insufficient privileges";
        default:
            return "unknown error";
    }
}

char* errconv_accept(int err) {
    switch (err) {
        case EAGAIN:
        case EWOULDBLOCK:
            return "no connections present";
        case EBADF:
            return "socket is not a valid file descriptor";
        case ECONNABORTED:
            return "connection was aborted";
        case EINTR:
            return "interrupted by signal";
        case :
    }
}