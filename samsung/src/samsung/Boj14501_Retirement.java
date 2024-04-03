package samsung;

import java.io.*;
import java.util.*;

public class Boj14501_Retirement {

	static int[] t, p,dp;
    static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		//n일 정하기
		n= Integer.parseInt(br.readLine());
		t=new int[n];//걸리는 일수
		p=new int[n];//금액
	    dp = new int[n + 1]; //동적 프로그래밍을 위한 배열
	    
	    //동적 프로그래밍은 전에거 답을 사용하는 프로그래밍
		
		//일자 별 입력 상담이 걸리는 일수, 금액 입력
		for(int i=0;i<n;i++)
		{
			st=new StringTokenizer(br.readLine());//입력 초기화
			t[i]=Integer.parseInt(st.nextToken());
			p[i]=Integer.parseInt(st.nextToken());
		}
		
		for(int i=0;i<n;i++)
		{	
			//p[i]+p[i+t[i]]+p[i+t[i]+t[i+t[i]]]....
			
			if(i+t[i]<=n)
			{
				//dp=10,0,0,30,0,45 
				dp[i+t[i]]=Math.max(dp[i+t[i]], dp[i]+p[i]);
			}
			//누적이 되게
			//dp=10,10,10,30,45,45 
			dp[i+1]=Math.max(dp[i+1], dp[i]);
		}
		System.out.println(dp[n]);
	}
}