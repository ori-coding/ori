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

        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int sum = Integer.parseInt(br.readLine());
        int count = 0;
        int[] countArr = new int[sum];

        for(int target : arr) {
          if(target >= sum) continue;
          int counter = sum - target;
          if(countArr[counter] != 0) {
              countArr[counter]--;
              count++;
          }
          else {
              countArr[target]++;
          }
        }
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }
}