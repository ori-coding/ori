package main

import (
	"container/heap"
	"fmt"
	"sort"
)

type MinHeap []int

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	// slice[a:b] : a부터 b-1까지
	*h = old[0 : n-1]
	return x
}

func main() {
	// 1. 첫번째 줄 입력 받기 (강의 갯수)
	// fmt.Scan : 표준 입력에서 공백, 탭, 줄 바꿈 등의 구분자로 값을 읽어옴
	// 변수 하나라는 건 하나만 받겠다는 뜻
	var n int
	fmt.Scan(&n)

	// 2. 배열 만들기
	type Lecture struct {
		start, end int
	}
	lectures := make([]Lecture, n)

	// 3. 배열에 강의 넣기
	for i := 0; i < n; i++ {
		var id, start, end int
		fmt.Scan(&id, &start, &end)
		lectures[i] = Lecture{start: start, end: end}
	}

	// 4. 강의 시작 시간으로 정렬
	sort.Slice(lectures, func(i, j int) bool {
		return lectures[i].start < lectures[j].start
	})

	// 5. 최소힙(우선순위 큐) 초기화
	classrooms := &MinHeap{} // 포인터를 줘야 원본 수정을 하던지 말던지 하지
	heap.Init(classrooms)

	// 6. 첫번째 강의 끝나는 시간을 힙에 추가
	heap.Push(classrooms, lectures[0].end)

	// 7. 나머지 강의 처리
	for i := 1; i < n; i++ {
		// 힙의 가장 작은값이 현재 강의 시작보다 작거나 같으면 지우기
		if lectures[i].start >= (*classrooms)[0] {
			heap.Pop(classrooms)
		}
		// 현재 강의 끝나는 시간 추가
		heap.Push(classrooms, lectures[i].end)
	}

	// 힙의 길이가 필요한 최소 강의실 수
	fmt.Println(len(*classrooms))

}
