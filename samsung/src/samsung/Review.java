package samsung;

import java.util.*;
import java.io.*;


public class Review {
	
	
	static int[][] gear=new int [4][8];
	
	static String[] stringGear=new String[4];
	
	static int k;// 회전 횟수
	static int[] selectedGear; //회전 시킬 톱니바퀴
	static int[] way; //시계, 반시계 방법
	
	//시계 회전
	static void turnClock(int[] gear)
	{
		int temp=gear[7];
		for(int i=7;i>0;i--)
		{
			gear[i]=gear[i-1];
		}
		gear[0]=temp;
	}
	
	//반 시계 회전
	static void turnAntiClock(int[] gear)
	{
		int temp=gear[0];
		for(int i=0;i<7;i++)
		{
			gear[i]=gear[i+1];
		}
		gear[7]=temp;
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//톱니 바퀴 상태 입력
		for(int i=0;i<4;i++)
		{
			stringGear[i]=br.readLine(); //스트링 형태로 받은 후	
		}
		//톱니 바퀴 
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<8;j++)
			{
				gear[i][j]=stringGear[i].charAt(j)-'0';
			}
		}
		
		
		//k 입력
		k=Integer.parseInt(br.readLine());
		//회전시킬 톱니바퀴, 회전 방법 입력
		selectedGear=new int[k];
		way=new int[k];
		for(int i=0;i<k;i++)
		{
			st=new StringTokenizer(br.readLine());
			selectedGear[i]=Integer.parseInt(st.nextToken())-1;
			way[i]=Integer.parseInt(st.nextToken());
		}
	
		//비즈니스 로직 구현
		
		//톱니 바퀴 회전
		for(int i=0;i<k;i++)
		{
			boolean[] rotated=new boolean[4]; //각 톱니바퀴 회전여부
			int[] dirs=new int[4];//각 톱니바퀴의 회전 방향 저장
			
			dirs[selectedGear[i]]=way[i];  //선택된 톱니바퀴의 초기 회전 방향 저장
			
			
			//선택된 톱니바퀴의 오른쪽 톱니바퀴 쭉 상태 확인
			for(int j=selectedGear[i]+1;j<4;j++)
			{
				//만약 선택된 톱니바퀴의 오른쪽이 다르면
				if(gear[j-1][2]!=gear[j][6]) //나,오른쪽 비교
				{
					dirs[j]=-dirs[j-1];//오른쪽 톱니 바퀴 방향 변경
				}
				//같으면 회전을 안하므로 상태 확인종료
				else {
					break;
				}
			}
			
			
			//선택된 톱니바퀴의 왼쪽 톱니바퀴들 상태 확인
			for(int j=selectedGear[i]-1;j>=0;j--)
			{
				//만약 선택된 톱니바퀴의 왼쪽이 다르면
				if(gear[j+1][6]!=gear[j][2]) //나,왼쪽 비교
				{
					dirs[j]=-dirs[j+1];//왼쪽 톱니 바퀴 방향 변경
				}
				//같으면 회전을 안하므로 상태 확인종료
				else {
					break;
				}
			}

			
			//실제 회전
			for(int j=0;j<4;j++)
			{
				//시계 방향 회전
				if(dirs[j]==1)
				{
					turnClock(gear[j]);
				}
				else if(dirs[j]==-1)
				{
					turnAntiClock(gear[j]);
				}				
			}
		
		}
	
		//결과
		int result=0;
		if(gear[0][0]==1)
		{
			result+=1;
		}
		if(gear[1][0]==1)
		{
			result+=2;
		}
		if(gear[2][0]==1)
		{
			result+=4;
		}
		if(gear[3][0]==1)
		{
			result+=8;
		}
	
		System.out.println(result);
		
	}
}
