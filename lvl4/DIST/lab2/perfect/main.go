package main

import (
  "fmt"
  "os"
  "strconv"
  "time"
)

// Determine whether n is a perfect number

func perfect(n int64) bool {
  if n < 6 {
    return false
  }

  var sum int64 = 1

  for i := n / 2; i > 1; i-- {
    if n % i == 0 {
      sum += i
    }
  }

  return sum == n
}

func main() {
  var n int64
  var err error
  // Read and argument
  if len(os.Args) < 2 {
    panic(fmt.Sprintf("Usage: must provide number as an argument"))
  }

  if n, err = strconv.ParseInt(os.Args[1], 10, 64); err != nil {
    panic(fmt.Sprintf("Can't parse first argument"))
  }

  var start = time.Now()
  var i int64 = 0
  for ; i < n; i++ {
    if perfect(i) {
      fmt.Println(i, "is perfect")
    }
  }
  fmt.Println("Took", time.Now().Sub(start))
}