package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 백준_17834_사자와_토끼 {
    static List<Integer>[] adjList;
    static int[] visited;
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[n + 1];
        visited = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            adjList[i] = new ArrayList<>();
            visited[i] = -1;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adjList[a].add(b);
            adjList[b].add(a);
        }
        dfs(1, 0);
        if (flag) {
            System.out.println(0);
            return;
        }
        int cnt = 0;
        for (int i = 1; i < n + 1; i++) {
            if (visited[i] == 0) {
                cnt++;
            }
        }
        System.out.println(cnt * (n - cnt) << 1);
        br.close();
    }

    static void dfs(int node, int mark) {
        if (flag) {
            return;
        }

        visited[node] = mark;
        for (int c : adjList[node]) {
            if (visited[c] == -1) {
                dfs(c, mark ^ 1);
            } else {
                if (visited[c] == mark) {
                    flag = true;
                    return;
                }
            }
        }
    }
}
