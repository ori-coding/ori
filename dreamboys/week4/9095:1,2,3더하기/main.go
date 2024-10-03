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

	//
	var amount int
	fmt.Scan(&amount)

	// 뭔 숫자든 마지막에 +1, +2,+3이 될텐데, 결국 그 말은
	// dp[n] = dp[n-1] + dp[n-2] + dp[n-3]

	// 테스트 케이스 하나씩 받아서 최대 숫자 찾기
	// n이 최대 11이므로
	dp := make([]int, 11)
	dp[0] = 1
	dp[1] = 2
	dp[2] = 4
	for i := 3; i <= 10; i++ {
		dp[i] = dp[i-1] + dp[i-2] + dp[i-3]
	}

	for j := 0; j < amount; j++ {
		var n int
		fmt.Scan(&n)

		fmt.Println(dp[n-1])
	}

}
