package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_17245_서버실 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        long sum = 0;
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }
        int start = 0;
        int end = 10000000;
        if ((sum & 1) != 0) {
            sum++;
        }
        sum >>= 1;

        while (start <= end) {
            int mid = start + end >> 1;
            long cnt = getCnt(map, mid);
            if (cnt >= sum) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start);
        br.close();
    }

    static long getCnt(int[][] map, int ref) {
        long res = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                res += Math.min(ref, map[i][j]);
            }
        }
        return res;
    }
}
