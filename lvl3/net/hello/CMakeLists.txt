cmake_minimum_required(VERSION 3.13)
project(hello C)

set(CMAKE_C_STANDARD 11)

set(CMAKE_C_FLAGS  "-W -Wall -Werror")

add_executable(hello_client client.c errconv.c errconv.h)
add_executable(hello_server server.c errconv.c errconv.h)