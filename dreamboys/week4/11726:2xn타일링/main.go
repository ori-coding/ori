package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader = bufio.NewReader(os.Stdin)
var writer *bufio.Writer = bufio.NewWriter(os.Stdout)

func main() {

	defer writer.Flush()

	var width int
	fmt.Scan(&width)

	if width == 1 {
		fmt.Println(1)
		return
	} else if width == 2 {
		fmt.Println(2)
		return
	}

	dp := make([]int, width+1)
	dp[1] = 1
	dp[2] = 2

	for i := 3; i <= width; i++ {
		dp[i] = (dp[i-2] + dp[i-1]) % 10007

	}
	fmt.Println(dp[width])
}
