//
// Created by stuart on 18/10/2019.
//
#include <stdio.h>
#include <stdlib.h>

int *allocVector(int n) {
    return (int *)malloc(n * sizeof(int));
}

int **allocMatrix(int m, int n) {
    int **mat = (int **)malloc(m * sizeof(int *));
    if (mat == NULL) {
        return NULL;
    }

    for (int i = 0; i < m; ++i) {
        mat[i] = allocVector(n);

        if (mat[i] == NULL) {
            free(mat);
            return NULL;
        }
    }

    return mat;
}

void writeMatrix(int **mat, int m, int n) {
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            printf("%d ", mat[i][j]);
        }
        printf("\n");
    }
}

void matrixProd(int **left, int **right, int **result, int m, int n) {
    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < m; ++j) {
            int val = 0;

            for (int k = 0; k < n; ++k) {
                val += left[i][k] * right[k][j];
            }

            result[i][j] = val;
        }
    }
}

int main() {
    const int m = 10;
    const int n = 8;

    int **left = allocMatrix(m, n);
    int **right = allocMatrix(n, m);
    int **result = allocMatrix(m, m);

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            left[i][j] = i * 10 + j;
        }
    }

    writeMatrix(left, m, n);
    printf("\n");

    for (int i = 0; i < n; ++i) {
        for (int j = 0; j < m; ++j) {
            right[i][j] = i * 20 + j;
        }
    }

    writeMatrix(right, n, m);
    printf("\n");

    matrixProd(left, right, result, m, n);

    writeMatrix(result, m, m);

    return 0;
}