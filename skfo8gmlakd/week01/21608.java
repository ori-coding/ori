package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.Map.Entry;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] location = new int[N][N];

        // 2 => {1,3,4,5} 만족도 계산을 위한 저장용
        Map<Integer, List<Integer>> iLikeWho = new HashMap<>();
        // 2 => 2_2 : 2번 학생이 2행 2열에 앉아있다. location 배열 내 특정 친구의 위치를 빠르게 찾기 위한 용도
        Map<Integer, String> whereIsMyFriend = new HashMap<>();

        for(int i = 0; i < N * N; i++) {
            int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            // 앉을 학생 번호
            int target = input[0];
            iLikeWho.put(target, new ArrayList<>());
            // [ 1번 조건 ] 계산
            Map<String, Integer> whereIsMyFavorite = new HashMap<>();
            for(int j = 1; j < 5; j++) {
                iLikeWho.get(target).add(input[j]);
                if(!whereIsMyFriend.containsKey(input[j])) continue;
                int[] splitLocation = Arrays.stream(whereIsMyFriend.get(input[j]).split("_")).mapToInt(Integer::parseInt).toArray();
                int left = splitLocation[0];
                int right = splitLocation[1];
                // 위
                if(left > 0 && location[left-1][right] == 0) {
                    String locationStr = (left-1) + "_" + right;
                    if(whereIsMyFavorite.containsKey(locationStr)) whereIsMyFavorite.put(locationStr, whereIsMyFavorite.get(locationStr) + 1);
                    else whereIsMyFavorite.put(locationStr, 1);
                }
                // 왼쪽
                if(right > 0 && location[left][right-1] == 0) {
                    String locationStr = left + "_" + (right-1);
                    if(whereIsMyFavorite.containsKey(locationStr)) whereIsMyFavorite.put(locationStr, whereIsMyFavorite.get(locationStr) + 1);
                    else whereIsMyFavorite.put(locationStr, 1);
                }
                // 오른쪽
                if(right < N-1 && location[left][right+1] == 0) {
                    String locationStr = left + "_" + (right+1);
                    if(whereIsMyFavorite.containsKey(locationStr)) whereIsMyFavorite.put(locationStr, whereIsMyFavorite.get(locationStr) + 1);
                    else whereIsMyFavorite.put(locationStr, 1);
                }
                // 아래
                if(left < N-1 && location[left+1][right] == 0) {
                    String locationStr = (left+1) + "_" + right;
                    if(whereIsMyFavorite.containsKey(locationStr)) whereIsMyFavorite.put(locationStr, whereIsMyFavorite.get(locationStr) + 1);
                    else whereIsMyFavorite.put(locationStr, 1);
                }
            }
            List<Entry<String, Integer>> maxFavorite = new ArrayList<>();
            for(Entry<String, Integer> entry : whereIsMyFavorite.entrySet()) {
                if(maxFavorite.isEmpty() || maxFavorite.get(0).getValue() < entry.getValue()) {
                    maxFavorite = new ArrayList<>();
                    maxFavorite.add(entry);
                }
                else if(Objects.equals(maxFavorite.get(0).getValue(), entry.getValue())) maxFavorite.add(entry);
            }
            // [ 1번 조건 ] 충족 자리 1개
            if(maxFavorite.size() == 1) {
                int[] splitLocation = Arrays.stream(maxFavorite.get(0).getKey().split("_")).mapToInt(Integer::parseInt).toArray();
                int left = splitLocation[0];
                int right = splitLocation[1];
                whereIsMyFriend.put(target, maxFavorite.get(0).getKey());
                location[left][right] = target;
            }
            // [ 1번 조건 ] 충족 자리 여러개 or 0개
            else {
                // [ 2번 조건 ] 계산
                Map<Integer, List<String>> blankNumber = new HashMap<>();
                // 빈칸을 계산할 자리
                List<String> targetStr = new ArrayList<>();
                // 1번 조건 충족 자리 여러개
                if(maxFavorite.size() > 1) {
                    for(Entry<String, Integer> current : maxFavorite) {
                        targetStr.add(current.getKey());
                    }
                }
                // 1번 조건 충족 자리 0개 ( 비어있는 모든 자리가 빈칸 계산 대상 )
                else {
                    for(int left = 0; left < N; left++) {
                        for(int right = 0; right < N; right++) {
                            if(location[left][right] == 0) targetStr.add(left + "_" + right);
                        }
                    }
                }
                for(String targetStrElem : targetStr) {
                    int[] splitLocation = Arrays.stream(targetStrElem.split("_")).mapToInt(Integer::parseInt).toArray();
                    int left = splitLocation[0];
                    int right = splitLocation[1];
                    int blankCount = 0;

                    // 위
                    if(left > 0 && location[left-1][right] == 0) blankCount++;
                    // 왼쪽
                    if(right > 0 && location[left][right-1] == 0) blankCount++;
                    // 오른쪽
                    if(right < N-1 && location[left][right+1] == 0) blankCount++;
                    // 아래
                    if(left < N-1 && location[left+1][right] == 0) blankCount++;

                    if(blankNumber.containsKey(blankCount)) blankNumber.get(blankCount).add(targetStrElem);
                    else {
                        List<String> strList = new ArrayList<>();
                        strList.add(targetStrElem);
                        blankNumber.put(blankCount, strList);
                    }
                }
                List<String> resultSet = new ArrayList<>();
                // [ 2번 조건 ] 만족하는 그룹 찾기
                for(int b = 4; b >= 0; b--) {
                    if(blankNumber.containsKey(b)) {
                        resultSet = blankNumber.get(b);
                        break;
                    }
                }
                // [ 2번 조건 ] 충족 자리 1개
                if(resultSet.size() == 1) {
                    int[] splitLocation = Arrays.stream(resultSet.get(0).split("_")).mapToInt(Integer::parseInt).toArray();
                    int left = splitLocation[0];
                    int right = splitLocation[1];
                    whereIsMyFriend.put(target, resultSet.get(0));
                    location[left][right] = target;
                }
                // [ 2번 조건 ] 충족 자리 여러개
                else {
                    // [ 3번 조건 ] 계산
                    int currentLeft = 20;
                    int currentRight = 20;
                    String currentStr = null;
                    for(String str : resultSet) {
                        int[] splitLocation = Arrays.stream(str.split("_")).mapToInt(Integer::parseInt).toArray();
                        int left = splitLocation[0];
                        int right = splitLocation[1];
                        // [ 3번 조건 ] 중 행의 번호가 가장 작은 칸
                        if(left < currentLeft) {
                            currentLeft = left;
                            currentRight = right;
                            currentStr = str;
                        }
                        // [ 3번 조건 ] 중 행의 번호가 같으면 열의 번호가 가장 작은 칸
                        else if(left == currentLeft && right < currentRight) {
                            currentRight = right;
                            currentStr = str;
                        }
                    }
                    // [ 3번 조건 ] 충족 자리
                    whereIsMyFriend.put(target, currentStr);
                    location[currentLeft][currentRight] = target;
                }
            }
        }
        // 모두 채워진 자리를 가지고 [ 만족도 ] 계산
        int totalCount = 0;
        for(int k = 0; k < N; k++) {
            for(int t = 0; t < N; t++) {
                // 좋아하는 사람이 몇 명이나 주변에 있는지 계산
                int count = 0;
                List<Integer> iLikeFriend = iLikeWho.get(location[k][t]);
                for(int friend : iLikeFriend) {
                    // 위
                    if(k > 0 && location[k-1][t] == friend) count++;
                    // 왼쪽
                    else if(t > 0 && location[k][t-1] == friend) count++;
                    // 오른쪽
                    else if(t < N-1 && location[k][t+1] == friend) count++;
                    // 아래
                    else if(k < N-1 && location[k+1][t] == friend) count++;
                }
                // 좋아하는 사람의 인접수에 따라 만족도 가산
                if(count == 1) totalCount += 1;
                else if(count == 2) totalCount += 10;
                else if(count == 3) totalCount += 100;
                else if(count == 4) totalCount += 1000;
            }
        }
        // [ 만족도 ] 출력
        bw.write(totalCount + "");
        bw.flush();
        bw.close();
        br.close();
    }
}