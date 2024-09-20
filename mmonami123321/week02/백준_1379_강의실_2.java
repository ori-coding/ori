package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_1379_강의실_2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        int[][] arr = new int[n][4];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr, (a, b) -> {
            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            return a[2] - b[2];
        });
        int room = 1;
        queue.add(arr[0]);
        arr[0][3] = room++;
        for (int i = 1; i < n; i++) {
            if (arr[i][1] >= queue.peek()[2]) {
                int r = queue.poll()[3];
                arr[i][3] = r;
            } else {
                arr[i][3] = room++;
            }
            queue.add(arr[i]);
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        StringBuilder sb = new StringBuilder();
        sb.append(room - 1).append("\n");
        for (int i = 0; i < n; i++) {
            sb.append(arr[i][3]).append("\n");
        }
        System.out.print(sb);
        br.close();
    }
}
