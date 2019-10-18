//
// Created by stuart on 18/10/2019.
//
#include <stdio.h>
#include <stdlib.h>

int *allocVector(int n) {
    return (int *) malloc(n * sizeof(int));
}

int dotProduct(const int *left, const int *right, int n) {
    int result = 0;

    for (int i = 0; i < n; ++i) {
        result += left[i] * right[i];
    }

    return result;
}

int main() {
    const int n = 10;

    int *vec1 = allocVector(n);
    int *vec2 = allocVector(n);

    printf("First vec [");
    for (int i = 0; i < n; ++i) {
        vec1[i] = i;
        printf("%d ", vec1[i]);
    }
    printf("]\n");

    printf("Second vec [");
    for (int i = 0; i < n; ++i) {
        vec2[i] = 2*i;
        printf("%d ", vec2[i]);
    }
    printf("]\n");

    printf("Dot product: %d\n", dotProduct(vec1, vec2, n));

    return 0;
}