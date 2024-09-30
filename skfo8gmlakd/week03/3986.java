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

        int n = Integer.parseInt(br.readLine());
        int count = 0;
        for(int i = 0; i < n; i++) {
            String targetStr = br.readLine();
            Stack<Character> st = new Stack<>();
            for(char c : targetStr.toCharArray()) {
                if(!st.empty() && st.peek() == c) st.pop();
                else st.push(c);
            }
            if(st.empty()) count++;
        }
        bw.write( count + "");
        bw.flush();
        bw.close();
        br.close();
    }
}