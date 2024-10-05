package main

import (
	"bufio"
	"fmt"
	"os"
)

var reader *bufio.Reader = bufio.NewReader(os.Stdin)
var writer *bufio.Writer = bufio.NewWriter(os.Stdout)

// 다이나믹 프로그래밍의 핵심:
// 작은 문제로 쪼개기: 큰 문제를 작은 문제로 나눕니다. 예를 들어, N을 1로 만들기 위해 N-1, N/2, N/3에서 1로 만드는 문제로 나눌 수 있습니다.
// 결과 저장: 작은 문제의 답을 미리 저장해 둡니다. 그래서 같은 문제를 다시 풀 필요 없이, 저장된 답을 재활용합니다.
// 최적의 해 구하기: 여러 방법으로 문제를 풀 수 있지만, 가장 효율적인 방법을 선택합니다. 예를 들어, N을 1로 만드는 방법 중 가장 적은 연산을 사용하는 방법을 찾습니다.

func main() {

	defer writer.Flush()

	var n int
	fmt.Fscan(reader, &n)

	fmt.Println(makeOne(n))

}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func makeOne(n int) int {
	dp := make([]int, n+1) // dp 배열을 만들어서 0 ~ n까지 값 저장
	dp[1] = 0              // 1은 이미 1

	for i := 2; i <= n; i++ { // 2부터 n까지 최소 값 저장해놓겠다.
		dp[i] = dp[i-1] + 1 // 일단 이전 수의 정답보다 한번 더 할 수 있잖아 (-1) 하면 똑같으니까
		if i%2 == 0 {
			dp[i] = min(dp[i], dp[i/2]+1) // 1/2 한 결과값보다 하나 크니까, 그거랑 -1 한거랑 비교
		}
		if i%3 == 0 {
			dp[i] = min(dp[i], dp[i/3]+1) // 1/3 한 결과보다 한번 더 한거니까, 그거랑 -1 한거랑 비교
		}
	}
	return dp[n]

}
