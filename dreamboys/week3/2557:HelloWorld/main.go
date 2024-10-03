package main

import (
	"bufio"
	"fmt"
	"os"
)

var writer *bufio.Writer = bufio.NewWriter(os.Stdout)

func main() {
	defer writer.Flush()
	fmt.Fprintln(writer, "Hello World!")

}
