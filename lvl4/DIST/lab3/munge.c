#include <stdio.h>
#include <stdlib.h>

int main( int argc, char *argv[] )
{

    double *x, *y;     /* the arrays                 */
    int    arraySize = 100000000;
    int    i;

    /* Allocate memory for the arrays. */
    x = (double *) malloc( (size_t) (  arraySize * sizeof(double) ) );
    y = (double *) malloc( (size_t) (  arraySize * sizeof(double) ) );

    /* Initialize x with some junk. */
#pragma omp for
    for ( i = 0; i < arraySize; i++ )
    {
        x[i] = ( (double) i ) / ( i + 1000 );
    }

#pragma omp for simd
    for ( i = 0; i < (arraySize-1); i++ )
    {
        y[i] = (x[i]/x[i+1]) + (x[i+1]*2.14) - (x[i]/5.84);
    }

    printf("\nProgram successfully terminated.\n\n");

    return(0);
}
