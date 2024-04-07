package samsung;

import java.util.*;
import java.io.*;

public class Boj14891_Gear  {
	static 	String[] strNum=new String[4];
	//각 기아별 상태
    static int[][] gears = new int[4][8];
    
	static int n; //회전 시킬 횟수
	static int[] selectedGear; //회전시킬 톱니바퀴
	static int[] direction; //방향 
	
	//시계 방향으로 돌리는 매서드
	static void clock(int[] gear)
	{
		int g0=gear[7];
		
		for(int i=7;i>0;i--)
		{ 
			gear[i] = gear[i - 1];
		}
		gear[0]=g0;
		
	}
	
	//시계 반대 방향으로 돌리는 매서드
	static void anti_clock(int[] gear)
	{
		int g7=gear[0];
		
		for(int i=0;i<7;i++)
		{
			gear[i]=gear[i+1];
		}
		gear[7]=g7;
	}
	
	public static void main(String args[]) throws IOException{
		
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		//기아 상태를 문자열로 입력 받기
		for(int i=0;i<4;i++)
		{
			strNum[i]=br.readLine();
		}
			
		//톱니 바퀴 극 상태 입력
		for(int i=0;i<4;i++)
		{
			for(int j=0;j<8;j++)
			{
				//문자열에서 각 원소별로 뽑아서 쓰기
				gears[i][j]=strNum[i].charAt(j)-'0';	
			}
		}
		
		//회전 시킬 횟수 입력
		n=Integer.parseInt(br.readLine());
		
		//톱니바퀴와 방향 입력
		selectedGear=new int[n];
		direction=new int[n];
		
		for (int i = 0; i < n; i++) {
		    st = new StringTokenizer(br.readLine());
		    selectedGear[i] = Integer.parseInt(st.nextToken()) - 1; // 입력값을 0~3으로(1~4 톱니바퀴 입력)
		    direction[i] = Integer.parseInt(st.nextToken());
		}
		
		
		//비즈니스 로직 시작
        for (int i = 0; i < n; i++) {
            int[] dirs = new int[4]; // 각 톱니바퀴의 회전 방향 저장
            dirs[selectedGear[i]] = direction[i]; //선택된 톱니바퀴의 초기 회전 방향 저장

            // 선택된 톱니바퀴로부터 왼쪽 톱니바퀴 영향 검사
            for (int j = selectedGear[i] - 1; j >= 0; j--) {
                if (gears[j][2] != gears[j + 1][6]) { //왼쪽, 나
                    dirs[j] = -dirs[j + 1];//왼쪽은 나와 반대로 돌아서
                } else {
                    break;
                }
            }

            // 선택된 톱니바퀴로부터 오른쪽 톱니바퀴 영향 검사
            for (int j = selectedGear[i] + 1; j < 4; j++) {
                if (gears[j][6] != gears[j - 1][2]) {//오른쪽, 나 비교
                    dirs[j] = -dirs[j - 1]; //오른쪽은 나와 반대로 돌아서
                } else {
                    break;
                }
            }

            // 실제 회전
            for (int j = 0; j < 4; j++) {
                if (dirs[j] == 1) {
                    clock(gears[j]);
                } else if (dirs[j] == -1) {
                    anti_clock(gears[j]);
                }
            }
        }

		
		
		//각 톱니 바퀴의 12시 방향이 S극(1)이면 더하기
		int result=0;
		
		if(gears[0][0]==1)
		{
			result+=1;	
		}
		if(gears[1][0]==1)
		{
			result+=2;	
		}			
		if(gears[2][0]==1)
		{
			result+=4;			
		}
		if(gears[3][0]==1)
		{				
			result+=8;	
		}
		System.out.println(result);
		
		br.close();
		
	}
}