package main

import (
	"container/heap"
	"fmt"
	"sort"
)

// Minheap strcut 및 heap.Interface 구현
type Minheap []int

func (h Minheap) Len() int           { return len(h) }
func (h Minheap) Less(i, j int) bool { return h[i] < h[j] }
func (h Minheap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }

// interface{} : 모든 타입을 담을 수 있는 타입 (다양한 타입 데이터 받을 수 있도록 사용)
// x.(int) : int로 타입 변환
// (h *Minheap) : (리시버 : 리시버 타입)
// 값 리시버 : 객체의 복사본 사용 (원본 데이터 변경 안됨)
// 포인터 리시버 : 객체의 값 직접 수정
func (h *Minheap) Push(x interface{}) {
	// h : MinHeap 주소를 가리키는 포인터
	// *h : 실제 MinHeap 슬라이스
	// aapend() : 슬라이스에 값을 추가한 후 새로운 슬라이스를 반환
	*h = append(*h, x.(int))
}

func (h *Minheap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	// slice[a:b] : 슬라이스의 a부터 b-1까지 반환
	*h = old[0 : n-1]
	return x
}

func NewMinHeap() *Minheap {

	// h : Minheap을 새로 만든 주소
	// & : 변수의 주소 가져오는 연산자
	// * : 포인터가 가리키는 값을 가져오는 연산자 (타입)
	// heap.Init : 포인터를 인자로 받기 때문에

	h := &Minheap{} // Minheap{}의 주소를 h에 저장
	heap.Init(h)    // h는 MinHeap의 주소, 이 주소를 포인터로 전달
	return h        // 주소 반환
}

type Lecture struct {
	start, end int
}

func main() {

	var n int
	// fmt.Scan : 표준 입력에서 공백, 탭, 줄 바꿈 등의 구분자로 값을 읽어옴
	// 입력값이 n 변수에 저장
	fmt.Scan(&n)

	lectures := make([]Lecture, n)
	for i := 0; i < n; i++ {
		// fmt.Scan : 스페이스로 구분해 3개의 변수로 저장
		var id, start, end int
		fmt.Scan(&id, &start, &end)
		lectures[i] = Lecture{start: start, end: end}
	}

	// 1. 강의 시작 시간 기준으로 정렬
	sort.Slice(lectures, func(i, j int) bool {
		return lectures[i].start < lectures[j].start
	})

	// 2. 최소 힙 (우선순위 큐) 초기화
	classrooms := &Minheap{}
	heap.Init(classrooms)

	// 3. 첫번째 강의 끝나는 시간 힙에 추가
	heap.Push(classrooms, lectures[0].end)

	// 4. 나머지 강의 처리
	for i := 1; i < n; i++ {
		// 힙의 가장 작은 값(가장 빨리 끝나는 강의 시간)이 현재 강의 시작보다 작거나 같으면 재사용 가능
		if lectures[i].start >= (*classrooms)[0] {
			heap.Pop(classrooms)
		}
		heap.Push(classrooms, lectures[i].end)
	}

	// 	힙의 크기가 필요한 최소 강의실 수

	// fmt.Println("정답 :::")
	fmt.Println(len(*classrooms))

}
