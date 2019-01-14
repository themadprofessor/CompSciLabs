//
// Created by stuart on 14/01/19.
//

#ifndef HELLO_ERRCONV_H
#define HELLO_ERRCONV_H

char* errconv_socket(int err);
char* errconv_bind(int err);
char* errconv_listen(int err);
char* errconv_accept(int err);

#endif //HELLO_ERRCONV_H
