package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int[][] vis = new int[N][M];
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        for(int i = 0; i < N; i++) {
            int j = 0;
            for(char c : br.readLine().toCharArray()) {
                arr[i][j] = Character.getNumericValue(c);
                j++;
            }
        }
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, 0));
        vis[0][0] = 1;
        while(!queue.isEmpty()) {
            Pair target = queue.poll();
            for(int i = 0; i < dx.length; i++) {
                int nextX = target.x + dx[i];
                int nextY = target.y + dy[i];
                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1) continue;
                else if(arr[nextX][nextY] > 0 && vis[nextX][nextY] == 0) {
                    queue.add(new Pair(nextX, nextY));
                    vis[nextX][nextY] = 1;
                    arr[nextX][nextY] = arr[target.x][target.y] + 1;
                }
            }
        }
        bw.write( arr[N-1][M-1] + "");
        bw.flush();
        bw.close();
        br.close();
    }
}

class Pair {
    int x;
    int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}