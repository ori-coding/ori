package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_1074_Z {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        System.out.println(cal(1 << n, r, c));


        br.close();
    }

    static int cal(int n, int r, int c) {
        if (n == 2) {
            return (r << 1) + c;
        }

        int limit = n >> 1;
        int m;
        if (r < limit && c < limit) {
            m = 0;
        } else if (r < limit) {
            m = 1;
            c -= limit;
        } else if (c < limit) {
            m = 2;
            r -= limit;
        } else {
            m = 3;
            r -= limit;
            c -= limit;
        }
        return m * limit * limit + cal(n >> 1, r, c);
    }
}
