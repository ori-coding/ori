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

        int[] rangeX = {-1, 0, 1, 0};
        int[] rangeY = {0, 1, 0, -1};
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] location = new int[N][M];
        st = new StringTokenizer(br.readLine());
        int currentX = Integer.parseInt(st.nextToken());
        int currentY = Integer.parseInt(st.nextToken());
        int currentF = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                location[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cleanCount = 0;
        boolean endFlag = false;
        while(true) {
            if(endFlag) break;
            if(location[currentX][currentY] == 0) {
                location[currentX][currentY] = 2; // 청소됨
                cleanCount++;
            }
            for(int i = 0; i < 4; i++) {
                // 왼쪽으로 회전
                currentF--;
                if(currentF < 0) currentF = 3;
                int nextX = currentX + rangeX[currentF];
                int nextY = currentY + rangeY[currentF];

                // 해당 칸이 청소되지 않은 경우
                if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && location[nextX][nextY] == 0) {
                    currentX = nextX;
                    currentY = nextY;
                    break;
                }

                // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
                if(i == 3) {
                    // 후진
                    int backF = currentF - 2;
                    if(backF < 0) backF = 4 + backF;
                    int backX = currentX + rangeX[backF];
                    int backY = currentY + rangeY[backF];

                    if (backX >= 0 && backY >= 0 && backX < N && backY < M && location[backX][backY] != 1) {
                        currentX = backX;
                        currentY = backY;
                    } else {
                        endFlag = true;
                    }
                }
            }
        }
        bw.write(cleanCount + "");
        bw.flush();
        bw.close();
        br.close();
    }
}