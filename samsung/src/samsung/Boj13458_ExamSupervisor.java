package samsung;

import java.io.*;
import java.util.*;

public class Boj13458_ExamSupervisor  {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; //공백으로 값 구분
		
		int n;//시험장 수
	    n= Integer.parseInt(br.readLine()); //시험장 수 입력
        int[] a=new int[n]; //응시자
        int b; //총감독관이 확인 가능한 수
        int c; //보조 감독관이 확인 가능한 수
        
       //입력 시작
       
       //각 시험장 별 응시자 수 입력 
       st = new StringTokenizer(br.readLine());
       for(int i=0;i<n;i++)
       {
    	   a[i]=Integer.parseInt(st.nextToken());
       }
       //감독관 확인 가능 수	
       st = new StringTokenizer(br.readLine());
       b = Integer.parseInt(st.nextToken());
       c = Integer.parseInt(st.nextToken());
       
       long cnt=n;//최악의 경우의 수: 1백만 시험장*1백만 응시자 => 최악의 경우 발생
       for(int i=0;i<n;i++)
       {
    	  //총 감독이 보믄 사람 빼기
    	  a[i]-=b;
    	  //0보다 작으면
    	  if(a[i]<= 0)
    	  {
    		  continue; //다음 시험장으로
    	  }
    	  //우선 k명의 감독관의 수를 더함
    	  cnt+=a[i]/c;
    	  //나머지가 있으면 증가
    	  if((a[i]%c) !=0)
    	  {
    		  cnt++;
    	  }
    	  
       }
       System.out.print(cnt);
	} 
}