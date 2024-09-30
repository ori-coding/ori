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
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        int[][] vis = new int[N][M];
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int isFake = 1;
        Queue<tomato> queue = new LinkedList<>();

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                isFake *= arr[i][j];
                if(arr[i][j] == 1) {
                    queue.add(new tomato(i, j, 0));
                    vis[i][j] = 1;
                }
            }
        }
        if(isFake != 0) {
            bw.write("0");
        }
        else {
            while(!queue.isEmpty()) {
                tomato current = queue.poll();
                for(int i = 0; i < dx.length; i++) {
                    int nextX = current.x + dx[i];
                    int nextY = current.y + dy[i];
                    if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1) continue;
                    else if(vis[nextX][nextY] == 0 && arr[nextX][nextY] == 0) {
                        vis[nextX][nextY] = 1;
                        arr[nextX][nextY] = current.day + 1;
                        queue.add(new tomato(nextX, nextY, current.day + 1));
                    }
                }
            }
            boolean clear = true;
            int maxDay = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(arr[i][j] == 0) {
                        clear = false;
                        break;
                    }
                    else if(arr[i][j] > maxDay) maxDay = arr[i][j];
                }
                if(!clear) break;
            }
            if(!clear) bw.write(-1 + "");
            else bw.write( maxDay + "");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
class tomato {
    int x;
    int y;
    int day;

    public tomato(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }
}