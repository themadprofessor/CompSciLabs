cmake_minimum_required(VERSION 3.14)
project(c C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O3 -Wall -Werror -Wextra")

add_executable(server server.c)
add_executable(client client.c)