package samsung;

import java.util.*;
import java.io.*;

public class Boj3190_Snake {
	static int n;	//맵 크기
	static int[][] map; //맵 입력
	
	static int k; 	//사과 개수
	static int[] appleX; //사과 x 좌표
	static int[] appleY; //사과 y 좌표
	
	static int l;	//뱀의 방향 변환 횟수
	static int[] turnTime; //뱀의 회전 시각
	static char [] turnDriection; //회전 방향(L:왼쪽, D: 오른쪽)
	
	static Queue<int []> snake=new LinkedList<>(); //뱀이 지나간 자리를 넣을 큐
	
	static int x=0;//뱀의 초기 위치
	static int y=0;//뱀의 초기 위치
	//				  북, 동, 남, 서
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= { 0, 1, 0, -1};
	static int d=1; //뱀의 초기 방향은 오른쪽(동쪽)
	
	//좌회전 함수
	static void turnLeft()
	{
		d-=1;
		if(d==-1)
		{
			d=3;
		}
	}
	//우회전 함수
	static void turnRight()
	{
		d+=1;
		if(d==4)
		{
			d=0;
		}
	}

	public static void main(String args[]) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//맵 크기 입력
		n=Integer.parseInt(br.readLine());
		//사과 개수 입력
		k=Integer.parseInt(br.readLine());
		//사과 위치 입력
		appleX=new int[k];
		appleY=new int[k];
		for(int i=0;i<k;i++)
		{
			st=new StringTokenizer(br.readLine());
			appleX[i]=Integer.parseInt(st.nextToken())-1;
			appleY[i]=Integer.parseInt(st.nextToken())-1;
		}
		
		//뱀의 회전 횟수 입력
		l=Integer.parseInt(br.readLine());
		turnTime=new int[l];
		turnDriection=new char[l];
		//뱀의 방향 정보 입력
		for(int i=0;i<l;i++)
		{
			st=new StringTokenizer(br.readLine());
			turnTime[i]=Integer.parseInt(st.nextToken());
			turnDriection[i]=st.nextToken().charAt(0);
		}
	
		//비즈니스 로직 구현
		int time=0;// 게임 경과 시간
		int turnCnt=0;//회전 횟수
		
		//사과 위치를 map에 표시(1)
		map=new int[n][n];
		for(int i=0;i<k;i++)
		{
			map[appleX[i]][appleY[i]]=1;
		}
		//뱀의 위치를 map과 큐에 표시(2)
		snake.add(new int [] {0,0}); //x,y배열을 가진 큐로 넣기, 초기 위치:0,0
		map[x][y]=2; //뱀의 위치를 2로 표시
		
		while(true)
		{	
			//1. 회전 시각에 따라 회전
			//아직 회전을 못 했거나 시간이 회전한 시각이면 회전
			if(turnCnt<l && time==turnTime[turnCnt])
			{
				//좌회전이면 좌회전
				if(turnDriection[turnCnt]=='L'){
					turnLeft();
				}
				//우회전이면 우회전
				else{
					turnRight();
				}
				turnCnt+=1;
			}
			//1.뱀의 머리 이동 (매초마다 이동)
			time+=1; //게임 시간 흐름
			
			int nextX=x+dx[d];
			int nextY=y+dy[d];
			x=nextX;
			y=nextY;
			snake.add(new int[] {x,y}); //큐에도 넣기
			
			//2.벽이나 자기자신(map[x][y]==2)을 만나면 GameOver
			if( x<0 || y<0 || x>=n || y>=n || map[x][y]==2)
			{
				break;
			}
			//3. 사과가 없으면(1이 아니면) 꼬리 한개제거
			if(map[x][y]==0)
			{
				int[] tail=snake.poll();
				map[tail[0]][tail[1]]=0; //*******큐의 값을
			}
			//4. 사과가 있으면(1이면) 사과가 없어지고, 뱀의 꼬리는 그대로
			map[x][y]=2;
		}
		
//		//체크 로직
//		for(int i=0;i<n;i++)
//		{
//			for(int j=0;j<n;j++)
//			{
//				System.out.print(map[i][j]+" ");
//			}
//			System.out.println();
//		}

		System.out.println(time);
	}
}
