package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_13459_구슬_탈출 {
    static int flag = 0;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static char[][] map;
    static int N, M, goalR, goalC, redR, redC, blueR, blueC;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'O') {
                    goalR = i;
                    goalC = j;
                } else if (map[i][j] == 'R') {
                    redR = i;
                    redC = j;
                } else if (map[i][j] == 'B') {
                    blueR = i;
                    blueC = j;
                }
            }
        }
        perm(0, new boolean[N][M][N][M], redR, redC, blueR, blueC);
        System.out.println(flag);
        br.close();
    }

    static void perm(int depth, boolean[][][][] visited, int rr, int rc, int br, int bc) {
        if (depth == 11 || flag != 0) {
            return;
        }

        if (rr == goalR && rc == goalC) {
            flag = 1;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int[] nextBlue = getNextCoord(br, bc, i);
            int nbr = nextBlue[0];
            int nbc = nextBlue[1];

            if (nbr == goalR && nbc == goalC) {
                continue;
            }

            int[] nextRed = getNextCoord(rr, rc, i);
            int nrr = nextRed[0];
            int nrc = nextRed[1];


            if (nrr == nbr && nrc == nbc) {
                if (nextRed[2] > nextBlue[2]) {
                    nrr -= dr[i];
                    nrc -= dc[i];
                } else {
                    nbr -= dr[i];
                    nbc -= dc[i];
                }
            }
            if (visited[nrr][nrc][nbr][nbc]) {
                continue;
            }
            visited[nrr][nrc][nbr][nbc] = true;
            perm(depth + 1, visited, nrr, nrc, nbr, nbc);
            visited[nrr][nrc][nbr][nbc] = false;
        }

    }

    static int[] getNextCoord(int r, int c, int dir) {
        int[] res = new int[3];
        int cnt = 0;
        while (true) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];
            cnt++;
            if (map[nr][nc] != '#' && map[nr][nc] != 'O') {
                r = nr;
                c = nc;
            } else if (map[nr][nc] == 'O') {
                res[0] = nr;
                res[1] = nc;
                res[2] = cnt;
                break;
            } else {
                res[0] = r;
                res[1] = c;
                res[2] = cnt - 1;
                break;
            }
        }
        return res;
    }
}
