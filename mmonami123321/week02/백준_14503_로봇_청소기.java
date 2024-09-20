package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_14503_로봇_청소기 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int dir = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;

        while (true) {
            if (map[r][c] == 0) {
                map[r][c] = 2;
                cnt++;
            }

            if (hasSpot(r, c, map)) {
                for (; ; ) {
                    dir = (dir + 3) % 4;
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];
                    if (map[nr][nc] == 0) {
                        r = nr;
                        c = nc;
                    }
                    break;
                }
            } else {
                int nr = r + dr[(dir + 2) % 4];
                int nc = c + dc[(dir + 2) % 4];
                if (map[nr][nc] == 1) {
                    break;
                } else {
                    r = nr;
                    c = nc;
                }
            }
        }
        System.out.println(cnt);


        br.close();
    }

    static boolean hasSpot(int r, int c, int[][] map) {
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (map[nr][nc] == 0) {
                return true;
            }
        }
        return false;
    }
}
