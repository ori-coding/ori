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
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        int[][] fire = new int[N][M];
        int[][] fireVis = new int[N][M];
        Queue<location> fireLoc = new LinkedList<>();
        int[][] jh = new int[N][M];
        int[][] jhVis = new int[N][M];
        Queue<location> jhLoc = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            char[] chArr = br.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                char target = chArr[j];
                if(target == '#') {
                    fire[i][j] = 1000001;
                    jh[i][j] = 1000001;
                    fireVis[i][j] = 1;
                    jhVis[i][j] = 1;
                }
                else if(target == '.') {
                    fire[i][j] = 1000001;
                    jh[i][j] = 1000001;
                    fireVis[i][j] = 0;
                    jhVis[i][j] = 0;
                }
                else if(target == 'J') {
                    fire[i][j] = 1000001;
                    jh[i][j] = 0;
                    fireVis[i][j] = 0;
                    jh[i][j] = 1;
                    jhLoc.add(new location(i, j, 0));
                }
                else if(target == 'F') {
                    fire[i][j] = 0;
                    jh[i][j] = 1000001;
                    fireVis[i][j] = 1;
                    jh[i][j] = 0;
                    fireLoc.add(new location(i, j, 0));
                }
            }
        }
        // fire
        while(!fireLoc.isEmpty()) {
            location current = fireLoc.poll();
            for(int i = 0; i < dx.length; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1) continue;
                else if(fireVis[nextX][nextY] == 0) {
                    fireVis[nextX][nextY] = 1;
                    fire[nextX][nextY] = current.day + 1;
                    fireLoc.add(new location(nextX, nextY, current.day + 1));
                }
            }
        }
        while(!jhLoc.isEmpty()) {
            location current = jhLoc.poll();
            for(int i = 0; i < dx.length; i++) {
                int nextX = current.x + dx[i];
                int nextY = current.y + dy[i];
                if(nextX < 0 || nextX > N-1 || nextY < 0 || nextY > M-1) {
                    bw.write(current.day + 1 + "");
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                else if(jhVis[nextX][nextY] == 0) {
                    jhVis[nextX][nextY] = 1;
                    if(fire[nextX][nextY] > current.day + 1) {
                        jh[nextX][nextY] = current.day + 1;
                        jhLoc.add(new location(nextX, nextY, current.day + 1));
                    }
                }
            }
        }
        bw.write("IMPOSSIBLE");
        bw.flush();
        bw.close();
        br.close();
    }
}
class location {
    int x;
    int y;
    int day;

    public location(int x, int y, int day) {
        this.x = x;
        this.y = y;
        this.day = day;
    }
}