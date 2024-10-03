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

	var a, b int
	// fmt.Fscan(r, a...interface{}) : 입력을 읽어 변수를 저장하는데 사용
	// r : io.Reader 인터페이스를 구현한 입력 소스
	// a...interface{} : 읽어들인 데이터를 저장할 변수들 (가변)
	// 반환값 n, err : n이 스캔 항목 갯수
	fmt.Fscan(reader, &a, &b)

	fmt.Fprintln(writer, a+b)

}
