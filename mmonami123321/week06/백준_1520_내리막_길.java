package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1520_내리막_길 {
    static int n, m;
    static int[][] map, dp;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(dfs(0, 0));
        br.close();
    }

    static int dfs(int r, int c) {
        if (r == n - 1 && c == m - 1) {
            return 1;
        }
        int cnt = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr < 0 || nr > n - 1 || nc < 0 || nc > m - 1 || map[nr][nc] >= map[r][c] || dp[nr][nc] == -1) {
                continue;
            }
            if (dp[nr][nc] != 0) {
                cnt += dp[nr][nc];
            } else {
                cnt += dfs(nr, nc);
            }
        }
        if (cnt == 0) {
            dp[r][c] = -1;
            return 0;
        }
        return dp[r][c] = cnt;
    }

}
