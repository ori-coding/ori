package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[][] arr = new int[R][C];
        int[][] vis = new int[R][C];
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int count = 0;
        int maxWidth = 0;
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(vis[i][j] == 1) continue;
                else if(arr[i][j] == 0) continue;
                Queue<Pair> queue = new LinkedList<>();
                queue.add(new Pair(i, j));
                vis[i][j] = 1;
                count++;
                int width = 1;
                while(!queue.isEmpty()) {
                    Pair target = queue.poll();
                    for(int k = 0; k < dx.length; k++) {
                        int nextX = target.x + dx[k];
                        int nextY = target.y + dy[k];
                        if(nextX < 0 || nextX > R-1 || nextY < 0 || nextY > C-1) continue;
                        else if(vis[nextX][nextY] == 0 && arr[nextX][nextY] == 1) {
                            vis[nextX][nextY] = 1;
                            queue.add(new Pair(nextX, nextY));
                            width++;
                        }
                    }
                }
                if(width > maxWidth) maxWidth = width;
            }
        }
        bw.write( count + "\n");
        bw.write( maxWidth + "");
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