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

        Stack<Character> st = new Stack<>();
        int result = 0;
        int buffResult = 1;
        boolean canBeAdded = false;
        for(char c : br.readLine().toCharArray()) {
            if(c == '(') {
                buffResult *= 2;
                canBeAdded = true;
                st.push(c);
            }
            else if(c == '[') {
                buffResult *= 3;
                canBeAdded = true;
                st.push(c);
            }
            else if(!st.empty()) {
                char counter = st.peek();
                if(counter == '(' && c == ')') {
                    st.pop();
                    if(canBeAdded) result += buffResult;
                    buffResult /= 2;
                    canBeAdded = false;
                }
                else if(counter == '[' && c == ']') {
                    st.pop();
                    if(canBeAdded) result += buffResult;
                    buffResult /= 3;
                    canBeAdded = false;
                }
                else st.push(c);
            }
            else st.push(c);
        }
        if(!st.empty()) result = 0;
        bw.write( result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}