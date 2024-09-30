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

        int T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; i++) {
            int l = Integer.parseInt(br.readLine());
            int[][] loc = new int[l][l];
            int[][] vis = new int[l][l];
            int[] dx = {-2, -1, 1, 2, -2, -1, 1, 2};
            int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};
            Queue<location> queue = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int currentX = Integer.parseInt(st.nextToken());
            int currentY = Integer.parseInt(st.nextToken());
            vis[currentX][currentY] = 1;
            queue.add(new location(currentX, currentY, 0));
            st = new StringTokenizer(br.readLine());
            int targetX = Integer.parseInt(st.nextToken());
            int targetY = Integer.parseInt(st.nextToken());
            if(currentX == targetX && currentY == targetY) {
                bw.write("0\n");
            }
            else {
                loc[targetX][targetY] = -1;
                boolean finish = false;
                while(!queue.isEmpty() && !finish) {
                    location current = queue.poll();
                    for(int j = 0; j < dx.length; j++) {
                        int nextX = current.x + dx[j];
                        int nextY = current.y + dy[j];
                        if(nextX < 0 || nextX > l-1 || nextY < 0 || nextY > l-1) continue;
                        else if(vis[nextX][nextY] == 0) {
                            vis[nextX][nextY] = 1;
                            if(loc[nextX][nextY] == -1) {
                                bw.write(current.count + 1 + "\n");
                                finish = true;
                                break;
                            }
                            loc[nextX][nextY] = current.count + 1;
                            queue.add(new location(nextX, nextY, current.count + 1));
                        }
                    }
                }
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
class location {
    int x;
    int y;
    int count;

    public location(int x, int y, int count) {
        this.x = x;
        this.y = y;
        this.count = count;
    }
}