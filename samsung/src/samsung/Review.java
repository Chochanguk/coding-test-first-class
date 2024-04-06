package samsung;

import java.util.*;
import java.io.*;

//복습용 클래스
//1번 시험감독


public class Review {
	//1번 시험감독
//	static int n; //시험장 수
//	static int[] a;
//	static int b,c; //학생 수(a),각 총감독 할당 수(b),각 보조감독 할당수(c)
	
	//2번 퇴사
	static int n;   //n일
	static int[] t; //기간
	static int[] p; //금액
	static int[] dp; //동적 프로그래밍
	static int maxResult=Integer.MIN_VALUE; //최대 금액
	public static void main(String args[]) throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		//1번 시험감독
//		
//		//학생수 입력
//		n=Integer.parseInt(br.readLine());
//		
//		//시험장수 입력
//		a=new int[n];
//		st=new StringTokenizer(br.readLine());
//		for(int i=0;i<n;i++)
//		{
//			a[i]=Integer.parseInt(st.nextToken());
//		}
//		//b,c 입력
//		st=new StringTokenizer(br.readLine());
//		b=Integer.parseInt(st.nextToken());
//		c=Integer.parseInt(st.nextToken());
//		
//		//비즈니스 로직 구현
//		long cnt=n;//보조 감독 수 
//		
//		for(int i=0;i<n;i++)
//		{
//			//총 감독관 보다 작거나 같으면 보조감독은 필요 없다
//            if(a[i]<b)
//            {
//                continue;
//            }
//			//나머지가 있는 경우
//			if(((a[i]-b)%c)>0)
//			{
//				cnt+=(a[i]-b)/c+1;
//			}
//			//나머지가 없는 경우
//			else
//			{
//				cnt+=(a[i]-b)/c;
//			}
//		}
//		
//		System.out.println(cnt); //결과
		
//		//2번 퇴사
//		
//		//n일 입력
//		n=Integer.parseInt(br.readLine());
//		
//		//기간,금액 입력
//		t=new int[n];
//		p=new int[n];
//		for(int i=0;i<n;i++)
//		{
//			st=new StringTokenizer(br.readLine());
//			t[i]=Integer.parseInt(st.nextToken());
//			p[i]=Integer.parseInt(st.nextToken());
//		}
//		
//		//비즈니스 로직 구현
//		dp=new int[n+1];
//		for(int i=0;i<n;i++)
//		{
//			//상담을 선택한 경우
//			//i일에 상담을 선택했을때 최대로 얻을 수 있는 수익
//			if(i+t[i]<n+1)
//			{
//				dp[i+t[i]]=Math.max(dp[i+t[i]],dp[i]+p[i]);
//			}
//			//상담을 선택하지 않은 경우
//			//다음날은 그 전날과 비교 했을시 더큰지 확인
//			dp[i+1]=Math.max(dp[i+1], dp[i]);
//			
//		}
//	
//		System.out.println(dp[n]); //n일차
		
		//3번 1이 될때까지
		
		
		
	}
}
