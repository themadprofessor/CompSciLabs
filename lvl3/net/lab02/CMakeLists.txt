cmake_minimum_required(VERSION 3.13)
project(wserver C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_COMPILER clang)
set(CMAKE_C_FLAGS  "-W -Wall -Werror -pthread")

add_executable(wserver wserver.c)