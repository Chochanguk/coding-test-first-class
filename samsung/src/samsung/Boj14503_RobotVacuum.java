package samsung;

import java.io.*;
import java.util.*;

public class Boj14503_RobotVacuum {
	
	static int n,m; //방 크기
	static int x,y; //청소기 초기 위치
	static int d; 	//청소기 초기 방향
	static int[][] stateRoom; // 방 상태
	static int[][] location; // 방문한 위치 정보
	
	// 북,동,남,서 이동 좌표
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    
    //반시계 90도 회전(초기 방향에서 -1)
    static void turnLeft()
    {
    	//왼쪽이동
    	d-=1;
    	//-1이면 한바퀴 돌기(초기 d=0(북)이라고 문제에서 제시했기에)
    	if(d==-1)
    	{
    		d=3;
    	}
    }
    
	public static void main(String args[]) throws IOException
	{
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//방 크기 입력
		st=new StringTokenizer(br.readLine());//입력 초기화
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		
		//로봇 청소기 위치, 방향 입력
		st=new StringTokenizer(br.readLine());
		x=Integer.parseInt(st.nextToken());
		y=Integer.parseInt(st.nextToken());
		d=Integer.parseInt(st.nextToken());
		
		//방 상태 입력
		stateRoom=new int[n][m];
		for(int i=0;i<n;i++)
		{
			st= new StringTokenizer(br.readLine());
			
			for(int j=0;j<m;j++)
			{
				stateRoom[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		//청소 했던 구간 정의
		location=new int[n][m];
		//청소기 위치 방문 처리
		location[x][y]=1;
		//청소한 구간
		int cnt=1;
		int turn_cnt=0;
		
		while(true)
		{
			//회전시행
			turnLeft(); turn_cnt++;
			//회전한 곳의 위치
			int nextX= x+dx[d];
			int nextY= y+dy[d];
			
			//1. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
			if(stateRoom[nextX][nextY]==0 && location[nextX][nextY]==0)
			{
				x=nextX; //로봇청소기 위치 이동
				y=nextY; //로봇청소기 위치 이동
				turn_cnt =0; //이동이 끝났으니 회전 횟수 초기화
				location[x][y]=1; //방문 처리
				cnt++; //청소 횟수 증가
		
			     // 그 다음 왼쪽이 갈수 있는지 체크를 위해 다시 루프를 돔.
                continue;
			}
			
			//2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우. 즉,다 1인경우  
			if(turn_cnt==4) {
			    nextX=x-dx[d]; //바라보는 방향으로 후진
			    nextY=y-dy[d]; //바라보는 방향으로 후진
			    
			    // 후진할 수 있는 경우(벽(1)이 아닌 경우)
			    if(nextX >= 0 && nextX < n && nextY >= 0 && nextY < m && stateRoom[nextX][nextY] == 0) {
			        x=nextX;
			        y=nextY;
			        turn_cnt=0; // 뒤로 이동했으니 탐색해야 할 방향 횟수 초기화
			    }
			    else {
			        break; // 후진할 수 없으므로 작동을 멈춤
			    }
			}
		}
		
		System.out.println(cnt);
	}
}