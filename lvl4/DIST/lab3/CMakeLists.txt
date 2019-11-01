cmake_minimum_required(VERSION 3.15)
project(lab3 C)

set(CMAKE_C_STANDARD 11)

find_package(OpenMP)

add_executable(munge munge.c)

if (OpenMP_C_FOUND)
    target_link_libraries(munge PRIVATE OpenMP::OpenMP_C)
endif ()