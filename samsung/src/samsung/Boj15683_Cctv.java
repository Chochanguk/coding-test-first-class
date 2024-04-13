package samsung;

import java.util.*;
import java.io.*;

public class Boj15683_Cctv {
		
	static int n,m; //맵 크기
	static int [][]map;//맵 정보 ,-1: 마킹, 0: 빈칸, 1~5: cctv번호, 6:벽
	
	//cctv 정보 클래스 (cttv는 여러개 가능) :x,y,type
	static class Cctv{
		int x;
		int y;
		int type;
		Cctv(int x,int y,int type)
		{
			this.x=x;
			this.y=y;
			this.type=type;
		}
	}
	//cctv들을 관리하는 리스트
	static ArrayList<Cctv> cctvs=new ArrayList<>();
	
	//감시 방향 이동(추후 dfs에서 사용)
	//		   북, 동, 남, 서
	static int[] dx= {-1, 0, 1, 0};
	static int[] dy= { 0, 1, 0,-1};

	
	//확인 가능한 
	static int minBlindSpot=Integer.MAX_VALUE;
	
	static void dfs(int index,int[][] map) {
	
		//종료 조건
		if(index==cctvs.size())
		{
			minBlindSpot=Math.min(minBlindSpot,caculateBlindSpot(map));
			return;
		}
		
		//cctv에서 꺼낸 후 해당 cctv가 어떤 타입인지
		Cctv cctv=cctvs.get(index);
		int types[][]=getType(cctv);
		
		//백트래킹 기법 사용
		for(int i=0;i<types.length;i++)
		{
			int[][]tempMap=copyMap(map);
			
			//방향 별로 감시하기
			for(int j=0;j<types[i].length;j++)
			{
				int dir=types[i][j];
				//cctv별로 마킹하기
				marking(tempMap,cctv.x,cctv.y,dir);
			}
			
			dfs(index+1,tempMap);
			
		}
	}
	//맵을 복사해주는 메서드
	static int[][] copyMap(int[][] original)
	{
		int[][] copy=new int[n][m];
		for(int i=0;i<n;i++)
		{	
			for(int j=0;j<m;j++)
			{
				copy[i][j]=original[i][j];
			}
		}
		return copy;
	}
	
	//감시 영역은 -1로 마킹하는 함수
	static void marking(int[][]tempMap,int x,int y,int dir) {
		//해당 좌표에서 해당 쭉 방향으로 마킹하기
		int nx=x;
		int ny=y;
		while(true) {
			nx+=dx[dir];
			ny+=dy[dir];
			
			//만약 범위를 벗어났거나 벽이면 종료
			if(nx<0||nx>=n || ny<0||ny>=m || map[nx][ny]==6)
			{
				break;
			}
			//cctv이면 마킹x
			if(map[nx][ny]>=1&&map[nx][ny]<=5)
			{
				continue;
			}
			tempMap[nx][ny]=-1;
		}
		map=copyMap(tempMap);
	}
	//감시 구역 확인
	static int caculateBlindSpot(int[][]tempMap) {
		
		int blindSpot=0;
		for(int i=0;i<n;i++)
		{	
			for(int j=0;j<m;j++)
			{
				//빈칸이 아니면 continue
				if(tempMap[i][j]!=0)
				{
					continue;
				}
				blindSpot+=1;
			}
		}
		
		return blindSpot;
	}
	//cctv가 보는 케이스별 방향 반환 메서드
	static int[][] getType(Cctv cctv)
	{
		int type=cctv.type;
		
		int[][]cctv1= {{0},{1},{2},{3}};		//북,동,남,서
		int[][]cctv2= {{0,2},{1,3}};			//북남, 동서
		int[][]cctv3= {{0,1},{1,2},{2,3},{3,0}};//
		int[][]cctv4= {{0,1,2},{1,2,3},{2,3,0},{3,0,1}};
		int[][]cctv5= {{0,1,2,3}};
		
		//cctv에서 감시하는 방향 유형
		if(type==1)
		{
			return cctv1;
		}
		else if(type==2)
		{
			return cctv2;
		}
		else if(type==3)
		{
			return cctv3;
		}
		else if(type==4)
		{
			return cctv4;
		}
		else if(type==5)
		{
			return cctv5;
		}
		//빈 배열 리턴
		return new int[][] {};
	}
	public static void main(String args[]) throws IOException{
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//입력
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		m=Integer.parseInt(st.nextToken());
		map=new int[n][m];
		
		for(int i=0;i<n;i++)
		{
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<m;j++)
			{
				map[i][j]=Integer.parseInt(st.nextToken());
				//cctvs 인스턴스에 좌표, 타입 값을 넣기
				if(map[i][j]>=1 && map[i][j]<=5)
				{
					cctvs.add(new Cctv(i,j,map[i][j]));
				}
			}
		}
		
		//비즈니스 로직 구현
		dfs(0,map);
		
		System.out.print(minBlindSpot);
	}
	
}
