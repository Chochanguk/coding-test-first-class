package samsung;

import java.util.*;
import java.io.*;

public class Boj23291_FishTank {

	static int n,k;
	static int[][] fishTank; //1차원
	//				  북, 동, 남, 서
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= { 0, 1, 0,-1}; 
	
	//1. 정리 후 가장 큰 값과 가장 작은 값의 차이가 k초과이면 false
	 static boolean maxDiffLargerThan(int k) {
	        int minFish = Integer.MAX_VALUE;
	        int maxFish = Integer.MIN_VALUE;

	        for (int i = 0; i < n; i++) {
	            minFish = Math.min(minFish, fishTank[i][0]);
	            maxFish = Math.max(maxFish, fishTank[i][0]);
	        }

	        if(maxFish - minFish > k) return true;
	        else return false;
	    }
	
	public static void main(String[] args) throws IOException{
		
		//입력
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());
		
		fishTank=new int[n][25];
		
		st=new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++)
		{
			fishTank[i][0]=Integer.parseInt(st.nextToken());
		}
		
		//구현
		int count=0;
		while( maxDiffLargerThan(k))
		{
			count+=1;
			
			//1.제일 작은 배열에다 +1하기
			addFish();
			//2.1개씩 어항쌓기
			firstFold();
			//3.물고기 수 조절하기
		    spread();
			//4.어항 일렬로 놓기
		    unFold();
			//5.2개씩 어항쌓기
		    secondFold();
			//6.물고기 수 조절하기
		    spread();
			//7.어항 일렬로 놓기
		    unFold();
			
		}
		
		
		//검증로직
		System.out.println("=====테스트======");
		for(int i=0;i<n;i++)
		{
			for(int j=0;j<n;j++)
			{
				System.out.print(fishTank[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("==============");
	
		System.out.print(count);
	}


	//1. 제일 작은 어항에 물고기 넣기
	static void addFish() {
		int minFish=Integer.MAX_VALUE;
		
		for(int i=0;i<n;i++)
		{
			//최솟값 찾기
			if(fishTank[i][0]<=minFish)
			{
				minFish=fishTank[i][0];
			}
		}
		
		
		for(int i=0;i<n;i++)
		{
			//최솟값이랑 같으면 추가하기
			if(fishTank[i][0]==minFish)
			{
				fishTank[i][0]+=1;
			}
		}
	}
	//2. 1개씩 쌓기
	static void firstFold() {
		
		  int startX = 0; //맨앞
		  int vert = 1; //i축 길이
	      int hori = 1; //j축 길이
	      //값 변동 전 체크하기
	      while (startX + vert + hori <= n) {
	    	  
	    	 //i축 기준, startX로부터 제일 멀리 떨어진 좌표에서부터 옮기기 
	         for (int i = vert-1; i >= 0; i--) {
	        	 //가로 길이 까지(j축기준)
	             for (int j = 0; j < hori; j++) {
	            	 //핵심(그림 그리면 이해가 됨)
	                 int nx = startX + vert + j;
	                 int ny = vert - i;
	                 //전의 좌표는 0으로 초기화
	                 fishTank[nx][ny] = fishTank[startX + i][j];
	                 fishTank[startX + i][j] = 0;
	             }
	         }
	         //다음 접기 수행을 위해서
	         startX += vert;
	         if (vert == hori) hori++;
	         else vert++;
	     }
	}
	//3. 어항 정리하기
	static void spread() {
		
		int[][] save = new int[n][25]; // 변화량을 임시 저장할 배열
        boolean[][] visited = new boolean[n][25]; // 방문 체크 배열
        //어항 전체 조사
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < 25; y++) {
            	
                visited[x][y] = true;
                //값이 있는 경우에만 시행 (시간 다운)
                if(fishTank[x][y] == 0) continue;

                //4방향 검사
                for (int dir = 0; dir < 4; dir++) {
                    int nx = x + dx[dir];
                    int ny = y + dy[dir];
                    //nx,ny가 범위에 벗어나 있지 않고, 다음좌표가 탐색되지 않았으며, 다음값이 0이 아니면
                    if(valid(nx, ny) && !visited[nx][ny] && fishTank[nx][ny] != 0) {
                    	//차이 값
                        int diff = (fishTank[x][y] - fishTank[nx][ny])/5;
                        //차이가 1이상 차이나면
                        if(Math.abs(diff) >= 1) {
                        	//차이만큼 빼고 (-이면 +가 된다.)
                            save[x][y] -= diff;
                            //다음거에는 더한다.
                            save[nx][ny] += diff;
                        }
                    }
                }
            }
        }
        //변화량에 대해서 
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < 25; y++) {
            	fishTank[x][y] += save[x][y];
            }
        }
	}
	static boolean valid(int x, int y) {
        if(x < 0 || x >= n || y < 0 || y >= 25) return false;
        else return true;
    }
	
	//4.평탄화 하기
	static void unFold() {
		//0으로 초기화 되지않은 첫번째 x좌표 찾기        
		int x = 0;
        while( fishTank[x][0] == 0) x++;

        //
        int index = 0;
        int[] save = new int[n];
        for (int i = x; i < n; i++) {
            for (int j = 0; j < 25; j++) {
            	//0이되면, 즉, hori길이가 1인 곳부턴 탐색안해도 됨.
                if( fishTank[i][j] == 0) break;
                //평탄화해서 임시 배열에 저장하기
                save[index++] =  fishTank[i][j];
                //옮긴것은 다시 0으로 초기화
                fishTank[i][j] = 0;
            }
        }
        
        for (int i = 0; i < n; i++) {
        	//첫번째 열에 다 집어 넣기
        	fishTank[i][0] = save[i];
        }
		
	}
	//5. 두번째 접기
	static void secondFold() {
		//90도 회전하
	    for(int i = 0; i < n/2; i++) {
	    	 fishTank[n-1-i][1] =  fishTank[i][0];
	    	 fishTank[i][0] = 0;
        }
	    //180도 회전하기
        for (int i = 0; i < n/4; i++) {
            for (int j = 0; j < 2; j++) {
            	fishTank[n-1-i][3-j] = fishTank[n/2+i][j];
            	fishTank[n/2+i][j] = 0;
            }
        }
		
	}

}
