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

        int[] arr = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        int[] count = new int[9];
        int max = 0;
        for(int num : arr) {
            if(num == 9) num = 6;
            count[num]++;
            if(num == 6) {
                if(max < Math.ceil((double) count[num] / 2)) max = (int) Math.ceil((double) count[num] / 2);
            }
            else if(max < count[num]) max = count[num];
        }
        bw.write(max + "");
        bw.flush();
        bw.close();
        br.close();
    }
}