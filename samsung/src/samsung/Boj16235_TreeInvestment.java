package samsung;

import java.util.*;
import java.io.*;

public class Boj16235_TreeInvestment {

	static int n; //map 크기
	static int[][]map;//맵 입력
	
	
	static int initialTreeCnt; //처음 심은 나무 개수 (M)
	static int k; //반복 시킬 횟수(단위: 1년)
	
	//나무의 특성: 위치,나이
	static class Tree{
		int x;
		int y;
		int age;
		Tree(int x, int y, int age){
			this.x=x;
			this.y=y;
			this.age=age;
		}
	}
	static int[] x;
	static int[] y;
	static int[] age;
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//n입력
		st=new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		//m입력
		initialTreeCnt=Integer.parseInt(st.nextToken());
		//k입력
		k=Integer.parseInt(st.nextToken());
		
		
		//map 입력
		map=new int[n][n];
		for(int i=0;i<n;i++)
		{		
			st=new StringTokenizer(br.readLine());
			for(int j=0;j<n;j++)
			{
				map[i][j]=Integer.parseInt(st.nextToken());
			}	
		}
		
		//처음 심은 각 나무의 특성(x,y,age) 입력
		x=new int[initialTreeCnt];
		y=new int[initialTreeCnt];
		age=new int[initialTreeCnt];
		for(int i=0;i<initialTreeCnt;i++)
		{	
			st=new StringTokenizer(br.readLine());
			x[i]=Integer.parseInt(st.nextToken());
			y[i]=Integer.parseInt(st.nextToken());
			age[i]=Integer.parseInt(st.nextToken());
		}
		
		//비즈니스 로직 구현
		
		
		
		
		
		
		
		
	}
	
}
