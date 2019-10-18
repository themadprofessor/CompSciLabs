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

int **transpose(int **mat, int m, int n) {
    int **result = allocMatrix(n, m);

    if (result == NULL) {
        return NULL;
    }

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            result[j][i] = mat[i][j];
        }
    }

    return result;
}

int main() {
    const int m = 10;
    const int n = 8;

    int **mat = allocMatrix(m, n);
    if (mat == NULL) {
        return 1;
    }

    for (int i = 0; i < m; ++i) {
        for (int j = 0; j < n; ++j) {
            mat[i][j] = i * 10 + j;
        }
    }

    writeMatrix(mat, m, n);

    int **trans = transpose(mat, m, n);
    if (trans == NULL) {
        return 2;
    }

    writeMatrix(trans, n, m);
}
