package samsung;

import java.util.*;
import java.io.*;

public class Boj14889_StrartAndLink {
	
	   	static int n; //맵의 크기
	    static int[][] s; //능력치
	    static boolean[] visited; //dfs
	    static int minDiff = Integer.MAX_VALUE; //최소 차이

	    public static void main(String[] args) throws IOException{
	    	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	    	StringTokenizer st;
	    	
	        n = Integer.parseInt(br.readLine());
	        s = new int[n][n];
	        visited = new boolean[n];

	        for (int i = 0; i < n; i++) {
	        	st=new StringTokenizer(br.readLine());
	        	
	            for (int j = 0; j < n; j++) {
	                s[i][j] = Integer.parseInt(st.nextToken());
	            }
	        }

	        combination(0, 0);
	        System.out.println(minDiff);
	    }

	    // 조합을 통해 팀 나누기
	    public static void combination(int index, int depth) {
	    	//1. 팀을 나눴으면
	        if (depth == n / 2) {
	        	//2. 차이 구하기
	            calculateDifference();
	            return;
	        }

	        for (int i = index; i < n; i++) {
	        	//방문x-> 방문 처리: 추후 startTeam에서 활용
	            if (!visited[i]) {
	                visited[i] = true; //방문 처리
	                combination(i + 1, depth + 1);
	                visited[i] = false; //재귀가 끝나면 비방문 처리
	            }
	        }
	    }

	    // 두 팀의 능력치 차이 계산
	    
	    /*선수 0, 1, 2가 스타트 팀인 경우:
	     * 스타트 팀 능력치: (s[0][1] + s[1][0]) + (s[0][2] + s[2][0]) + s[1][2] + s[2][1] = 1 + 1 + 2 + 2 + 2 + 2 = 10
	     * 링크 팀 능력치: s[3][4] + s[4][3] + s[3][5] + s[5][3] + s[4][5] + s[5][4] = 4 + 4 + 5 + 5 + 5 + 5 = 28
	     * 차이: |10 - 28| = 18
	     */
	    public static void calculateDifference() {
	        int startTeam = 0;
	        int linkTeam = 0;

	        for (int i = 0; i < n - 1; i++) {
	            for (int j = i + 1; j < n; j++) {
	            	//i번째 사람과 j번째 사람이 true면 startTra
	                if (visited[i] && visited[j]) {
	                    startTeam += s[i][j] + s[j][i];
	                } else if (!visited[i] && !visited[j]) {
	                    linkTeam += s[i][j] + s[j][i];
	                }
	            }
	        }

	        int diff = Math.abs(startTeam - linkTeam);
	        minDiff = Math.min(minDiff, diff);
	    }
	}