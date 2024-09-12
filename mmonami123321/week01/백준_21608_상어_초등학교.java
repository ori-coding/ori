package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_21608_상어_초등학교 {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Desk {
        int r, c, f, e;

        public Desk(int r, int c, int f, int e) {
            this.r = r;
            this.c = c;
            this.f = f;
            this.e = e;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        HashSet<Integer>[] sharks = new HashSet[n * n + 1];


        StringTokenizer st;
        for (int i = 0; i < n * n; i++) {
            st = new StringTokenizer(br.readLine());
            int id = Integer.parseInt(st.nextToken());
            HashSet<Integer> set = new HashSet<>();
            for (int j = 0; j < 4; j++) {
                set.add(Integer.parseInt(st.nextToken()));
            }
            sharks[id] = set;
            List<Desk> list = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if (map[j][k] != 0) {
                        continue;
                    }
                    int cnt = 0;
                    int emptyCnt = 0;

                    for (int l = 0; l < 4; l++) {
                        int nr = j + dr[l];
                        int nc = k + dc[l];
                        if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1) {
                            continue;
                        }
                        if (set.contains(map[nr][nc])) {
                            cnt++;
                        } else if (map[nr][nc] == 0) {
                            emptyCnt++;
                        }
                    }
                    list.add(new Desk(j, k, cnt, emptyCnt));
                }
            }
            list.sort((a, b) -> {
                if (a.f != b.f) {
                    return b.f - a.f;
                }
                if (a.e != b.e) {
                    return b.e - a.e;
                }
                if (a.r != b.r) {
                    return a.r - b.r;
                }
                return a.c - b.c;
            });
            Desk desk = list.get(0);
            map[desk.r][desk.c] = id;
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int shark = map[i][j];
                HashSet<Integer> set = sharks[shark];
                int cnt = 0;

                for (int k = 0; k < 4; k++) {
                    int nr = i + dr[k];
                    int nc = j + dc[k];
                    if (nr < 0 || nr > n - 1 || nc < 0 || nc > n - 1) {
                        continue;
                    }
                    if (set.contains(map[nr][nc])) {
                        cnt++;
                    }
                }

                if (cnt == 1) {
                    ans += 1;
                } else if (cnt == 2) {
                    ans += 10;
                } else if (cnt == 3) {
                    ans += 100;
                } else if (cnt == 4) {
                    ans += 1000;
                }
            }
        }
        System.out.println(ans);


        br.close();
    }


}
