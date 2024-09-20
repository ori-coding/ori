package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 백준_31423_신촌_통폐합_계획 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] arr = new String[n + 1];
        int[] next = new int[n + 1];
        int[] last = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            arr[i] = br.readLine();
            next[i] = i;
            last[i] = i;
        }

        StringTokenizer st;
        int from = 0;
        int to = 0;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            next[last[from]] = to;
            last[from] = last[to];
        }


        StringBuilder sb = new StringBuilder();
        while (n-- > 0) {
            sb.append(arr[from]);
            from = next[from];
        }
        System.out.print(sb);
        br.close();
    }
}
