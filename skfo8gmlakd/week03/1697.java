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
        int K = Integer.parseInt(st.nextToken());
        if(N == K) {
            bw.write("0");
            bw.flush();
            bw.close();
            br.close();
            return;
        }
        int[] loc = new int[200001];
        int[] vis = new int[200001];
        Queue<Integer> queue = new LinkedList<>();
        loc[K] = -1;
        queue.add(N);
        vis[N] = 1;

        while(!queue.isEmpty()) {
            int target = queue.poll();
            // + 1
            int nextLoc = target + 1;
            if(nextLoc < 200000 && vis[nextLoc] == 0) {
                vis[nextLoc] = 1;
                if(loc[nextLoc] == -1) {
                    bw.write(loc[target] + 1 + "");
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                loc[nextLoc] = loc[target] + 1;
                queue.add(nextLoc);
            }
            // - 1
            nextLoc = target - 1;
            if(nextLoc > -1 && vis[nextLoc] == 0) {
                vis[nextLoc] = 1;
                if(loc[nextLoc] == -1) {
                    bw.write(loc[target] + 1 + "");
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                loc[nextLoc] = loc[target] + 1;
                queue.add(nextLoc);
            }
            // * 2
            nextLoc = target * 2;
            if(nextLoc < 200000 && vis[nextLoc] == 0) {
                vis[nextLoc] = 1;
                if(loc[nextLoc] == -1) {
                    bw.write(loc[target] + 1 + "");
                    bw.flush();
                    bw.close();
                    br.close();
                    return;
                }
                loc[nextLoc] = loc[target] + 1;
                queue.add(nextLoc);
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}