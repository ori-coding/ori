package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 백준_18405_경쟁적_전염 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[3] != b[3]) {
                return a[3] - b[3];
            }
            return a[2] - b[2];
        });
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    pq.add(new int[]{i, j, map[i][j], 0});
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int goalT = Integer.parseInt(st.nextToken());
        int goalR = Integer.parseInt(st.nextToken()) - 1;
        int goalC = Integer.parseInt(st.nextToken()) - 1;
        int time = 0;
        while (!pq.isEmpty() && time != goalT) {
            while (!pq.isEmpty() && pq.peek()[3] == time) {
                int[] now = pq.poll();
                int r = now[0];
                int c = now[1];
                int k = now[2];
                int t = now[3];

                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];

                    if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1 || map[nr][nc] != 0) {
                        continue;
                    }
                    pq.add(new int[]{nr, nc, k, t + 1});
                    map[nr][nc] = k;
                }
            }
            time++;
        }
        System.out.println(map[goalR][goalC]);
        br.close();
    }
}
