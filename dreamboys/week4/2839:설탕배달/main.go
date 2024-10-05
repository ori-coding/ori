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

	var n int
	fmt.Fscan(reader, &n)

	maxFive := n / 5
	maxThree := n / 3

	//answer := new(big.Int).SetInt64(int64(n + 1))
	var changed bool

	answer := n
	for i := maxFive; i >= 0; i-- {

		for j := maxThree; j >= 0; j-- {
			if (i*5)+(j*3) == n {
				//sum := new(big.Int).SetInt64(int64(i + j))

				// if sum.Cmp(answer) < 0 {
				// 	answer.Set(sum)
				// 	changed = true
				// }
				if i+j < answer {
					answer = i + j
					changed = true
				}
			}
		}
	}

	if changed == true {
		fmt.Println(answer)
	} else {
		fmt.Println(-1)
	}

}
