package samsung;

import java.io.*;
import java.util.*;

public class Boj14888_Insert_Operator {

    static int n;    // 수열 개수
    static int[] a;  // 수열 입력
    static int[] opNumber = new int[4]; // 연산자 수 입력(+,-,*,/ 순)
    static long maxResult = Long.MIN_VALUE; // 최대 결과값 초기화
    static long minResult = Long.MAX_VALUE; // 최소 결과값 초기화

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine()); // 수열 개수 입력
        a = new int[n]; // 수열의 크기에 맞게 배열 초기화

        // 수열 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        // 연산자 수 입력
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            opNumber[i] = Integer.parseInt(st.nextToken());
        }

        // 연산 시작
        dfs(a[0], 1);

        // 최대값과 최소값 출력
        System.out.println(maxResult);
        System.out.println(minResult);
    }

    // 깊이 우선 탐색을 이용한 모든 경우의 수 탐색
    public static void dfs(long result, int index) {
        if(index == n) {
            maxResult = Math.max(result, maxResult);
            minResult = Math.min(result, minResult);
            return;
        }

        for(int i = 0; i < 4; i++) {
            if(opNumber[i] > 0) {
                opNumber[i]--;
                switch(i) {
                    case 0: dfs(result + a[index], index + 1); break; // +
                    case 1: dfs(result - a[index], index + 1); break; // -
                    case 2: dfs(result * a[index], index + 1); break; // *
                    case 3: dfs(result / a[index], index + 1); break; // /
                }
                opNumber[i]++;
            }
        }
    }
}