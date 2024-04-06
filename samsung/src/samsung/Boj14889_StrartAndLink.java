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
	    public static void combination(int start, int depth) {
	        if (depth == n / 2) {
	            calculateDifference();
	            return;
	        }

	        for (int i = start; i < n; i++) {
	            if (!visited[i]) {
	                visited[i] = true;
	                combination(i + 1, depth + 1);
	                visited[i] = false;
	            }
	        }
	    }

	    // 두 팀의 능력치 차이 계산
	    public static void calculateDifference() {
	        int startTeam = 0;
	        int linkTeam = 0;

	        for (int i = 0; i < n - 1; i++) {
	            for (int j = i + 1; j < n; j++) {
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