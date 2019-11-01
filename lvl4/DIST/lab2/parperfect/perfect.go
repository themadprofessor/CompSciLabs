package main

import (
	"fmt"
	"sync"
)

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

func perfectInterval(lower, upper int64, channel chan int64) {
	var i = lower

	for ; i < upper; i++ {
		if perfect(i) {
			channel <- i
		}
	}

	close(channel)
}

func merge(channels []chan int64) chan int64 {
	var waitGroup sync.WaitGroup

	merged := make(chan int64)
	waitGroup.Add(len(channels))

	merger := func(channel <-chan int64) {
		for val := range channel {
			merged <- val
		}
		waitGroup.Done()
	}

	for _, channel := range channels {
		go merger(channel)
	}

	go func() {
		waitGroup.Wait()
		close(merged)
	}()

	return merged
}

func main() {
	var channels = make([]chan int64, 10)
	for i := range channels {
		lower := int64(i * 1000)
		channel := make(chan int64)
		go perfectInterval(lower, lower + 1000, channel)
		channels[i] = channel
	}

	results := merge(channels)

	for val := range results {
		fmt.Println(val, "is perfect")
	}
}