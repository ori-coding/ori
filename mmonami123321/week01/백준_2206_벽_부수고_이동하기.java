package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_2206_벽_부수고_이동하기 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] map = new char[n][m];
        for (int i = 0; i < n; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int[][][] visited = new int[2][n][m];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(visited[i][j], Integer.MAX_VALUE);
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1, 0});
        visited[0][0][0] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int r = now[0];
            int c = now[1];
            int t = now[2];
            int b = now[3];

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1) {
                    continue;
                }

                if (map[nr][nc] == 49 && b == 1) {
                    continue;
                }

                if (map[nr][nc] == 48) {
                    if (visited[b][nr][nc] > t + 1) {
                        queue.add(new int[]{nr, nc, t + 1, b});
                        visited[b][nr][nc] = t + 1;
                    }
                } else {
                    if (visited[b + 1][nr][nc] > t + 1) {
                        queue.add(new int[]{nr, nc, t + 1, b + 1});
                        visited[b + 1][nr][nc] = t + 1;
                    }
                }
            }
        }
        int ans = Math.min(visited[0][n - 1][m - 1], visited[1][n - 1][m - 1]);
        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        System.out.print(ans);
        br.close();
    }
}
