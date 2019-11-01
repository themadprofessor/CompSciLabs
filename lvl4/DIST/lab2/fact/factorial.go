package fact;

import (
	"fmt"
)

func factorial(n int64) int64 {
	if n == 0 {
		return 0
	} else if n == 1 {
		return 1
	} else {
		return n * factorial(n-1)
	}
}

func main() {
	fmt.Println("Factorial 16", factorial(16))
}