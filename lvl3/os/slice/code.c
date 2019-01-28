//
// Created by stuart on 28/01/19.
//

#include <stdio.h>

int task1_iter = 10;
int task2_iter = 10;
int task3_iter = 10;

void task1(void) {
    int i;
TASK1_LOOP:
        printf("1: %d\n", i);
        i++;
    if (i < task1_iter) goto TASK1_LOOP ;
}

void task2(void) {
    int j;
TASK2_LOOP:
        printf("2: %d\n", j);
        j++;
    if (j < task2_iter) goto TASK2_LOOP ;
}

void task3(void) {
    int k;
TASK3_LOOP:
        printf("3: %d\n", k);
        k++;
    if (k < task3_iter) goto TASK3_LOOP ;
}
