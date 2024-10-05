package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader = bufio.NewReader(os.Stdin)
var writer *bufio.Writer = bufio.NewWriter(os.Stdout)

type answer struct {
	one int
	two int
}

func main() {
	defer writer.Flush()

	var count int
	fmt.Scan(&count)

	dp := make([]answer, 41)
	dp[0] = answer{1, 0}
	dp[1] = answer{0, 1}

	for j := 2; j < 41; j++ {
		dp[j] = answer{dp[j-2].one + dp[j-1].one, dp[j-2].two + dp[j-1].two}
	}

	for i := 0; i < count; i++ {
		var num int
		fmt.Scan(&num)

		// 어차피 공백 자동으로 해줌
		fmt.Println(dp[num].one, dp[num].two)

	}

}
