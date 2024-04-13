package samsung;

import java.io.*;
import java.util.*;

public class Boj12100_2048Easy {
    
    static int n;  // 보드의 크기
    static int[][] map;  // 초기 맵
    static int maxInt = Integer.MIN_VALUE;  // 발견된 최대 타일 값
    
    // DFS 함수: 최대 5번 이동하여 가능한 최대 값 찾기
    static void dfs(int index, int[][] currentMap) {
        if (index == 5) {  // 종료 조건: 5번 이동했다면 최대값 계산 후 반환
            maxInt = Math.max(maxInt, calculateMax(currentMap));
            return;
        }
        
        // 네 방향으로 이동: 북쪽 방향(0)을 시작으로, 
        //"백트래킹 기법을 활용하여 가능 한 모든 방향 조사"
        for (int dir = 0; dir < 4; dir++) { 
        	// 현재 맵을 복사=> 백트래킹을 위해서
            int[][] newMap = game(dir, copyMap(currentMap)); 
            dfs(index + 1, newMap);  // 재귀적으로 다음 이동 실행
        }
    }
    
    // 맵 복사하는 함수
    static int[][] copyMap(int[][] original) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
        	for (int j = 0; j < n; j++) {
        		copy[i][j]=original[i][j];
        	}
        }
        return copy;
    }
    
    // 맵에서 타일을 지정된 방향으로 이동시키는 함수
    static int[][] game(int dir, int[][] tempMap) {
    	 // 새로운 맵 생성(각 방향 별1.이동 2.합치기 두 과정을 시행한 맵 )
        int[][] newMap = new int[n][n];
        
        // 북쪽으로 모든 칸 이동 및 합치기
        if (dir == 0) {
            for (int j = 0; j < n; j++) { //j(열) 고정후 i(행) 위로 이동
                int position = 0; // 새로운 타일이 들어갈 위치를 관리 (위에서부터 시작)
                int lastValue = 0; // 마지막으로 처리된 타일의 값
                for (int i = 0; i < n; i++) { // 현재 열의 각 행을 위에서 아래로 확인
                	 // 현재 타일이 0이 아니라면 (즉, 타일이 있다면)
                	if (tempMap[i][j] != 0) {
                    	// 현재 타일과 이전의 타일이 같다면(여기서 맨 처음 lastValue는 0, tempMap[i][j]!=0이므로 newMap[-1][0]이 절대 안됨)
                        if (tempMap[i][j] == lastValue) {
                            newMap[position-1][j] += lastValue; // 이전 위치의 타일 값과 합치기(merge)
                            lastValue = 0; // 합쳐진 후에는 다음 타일 합침을 위해 0으로 리셋
                        } else {
                            lastValue = tempMap[i][j]; // lastValue을 현재 타일 값으로 업데이트 하여 다음 순회때 사용
                            newMap[position][j] = lastValue; // 새 맵의 현재 position에 lastValue를 설정
                            position++; // 다음 타일이 들어갈 위치를 업데이트
                        }
                    }
                }
            }
        } 

        // 서쪽으로 모든 칸 이동 및 합치기
        else if (dir == 1) { 
            for (int i = 0; i < n; i++) {//i(행) 고정후 j(열) 좌측이동
                int position = 0;
                int lastValue = 0;
                for (int j = 0; j < n; j++) {
                    if (tempMap[i][j] != 0) { //현재 타일에 값이 있다면
                    	//현재 타일 값과 이전 타일 값이 같다면
                        if (tempMap[i][j] == lastValue) {
                            newMap[i][position-1] += lastValue; //현재 값과 이전 타일 merge
                            lastValue = 0; //merge했으니 0으로 초기화
                        } else {
                            lastValue = tempMap[i][j];
                            newMap[i][position] = lastValue;
                            position++;
                        }
                    }
                }
            }
        } 
        // 남쪽으로 이동
        else if (dir == 2) { 
            for (int j = 0; j < n; j++) { //j(열) 고정후 i(행) 아래로 이동
                int position = n - 1; //n-1값을 n번째 값으로 옮겨야 됨(맨 아래부터 남쪽으로 이동) 
                int lastValue = 0;
                for (int i = n - 1; i >= 0; i--) {
                    if (tempMap[i][j] != 0) {
                        if (tempMap[i][j] == lastValue) {
                            newMap[position+1][j] += lastValue;
                            lastValue = 0;
                        } else {
                            lastValue = tempMap[i][j];
                            newMap[position][j] = lastValue;
                            position--;
                        }
                    }
                }
            }
        } 
        // 동쪽으로 이동
        else if (dir == 3){
            for (int i = 0; i < n; i++) { //i열 고정후 j열 우측이동
                int position = n - 1;
                int lastValue = 0;
                for (int j = n - 1; j >= 0; j--) {
                    if (tempMap[i][j] != 0) {
                        if (tempMap[i][j] == lastValue) {
                            newMap[i][position+1] += lastValue;
                            lastValue = 0;
                        } else {
                            lastValue = tempMap[i][j];
                            newMap[i][position] = lastValue;
                            position--;
                        }
                    }
                }
            }
        }
        return newMap;
    }
    
    // 최대 타일 값을 계산하는 함수
    static int calculateMax(int[][] map) {
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
        return max;
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        dfs(0, map);
        System.out.println(maxInt);
    }
}
