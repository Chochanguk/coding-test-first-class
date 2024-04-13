package samsung;

import java.io.*;
import java.util.*;

public class Boj20057_MagicSharkTornado {
    static int N;
    static int[][] map;
    //				   좌,하,우,상
    static int[] dx = {0,1,0,-1};   //토네이토의 x 이동 방향
    static int[] dy = {-1,0,1,0};   //토네이토의 y 이동 방향
    
    //				   좌,하,우,상
    static int[] dc = {1,1,2,2};   // 토네이도의 각 방향으로 이동하는 칸 초기 횟수(ex: 좌:1,3,5,.../ 우: 2,4,6,8...)
    
	//모래가 퍼지는 x방향
    static int[][] dsx = {
    		{-1,1,-2,-1,1,2,-1,1,0}, //좌
    		{-1,-1,0,0,0,0,1,1,2},   //하
            {1,-1,2,1,-1,-2,1,-1,0}, //우
            {1,1,0,0,0,0,-1,-1,-2}}; //상
    //모래가 퍼지는 y방향
    static int[][] dsy = {
    		{1,1,0,0,0,0,-1,-1,-2},
    		{-1,1,-2,-1,1,2,-1,1,0}, 
    		{-1,-1,0,0,0,0,1,1,2},
            {1,-1,2,1,-1,-2,1,-1,0}
    		};
    //퍼질 모래의 비율			
    static int[] sandRatio ={1,1,2,7,7,2,10,10,5};
    
    //격자 밖으로 나간 총 모래의 양
    static int answer=0;
    
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      
        StringTokenizer st;

        N = Integer.parseInt(br.readLine().trim());
        map = new int[N][N];

        for(int r=0; r<N; r++){
            st = new StringTokenizer(br.readLine()," ");
            for(int c=0; c<N; c++){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        //구현 메서드
        moveTornado();
        
        System.out.print(answer);
    }
    //현재위치에서 이동 -> 이동한 위치의 모래 뿌리기 -> 이동한위치를 현재위치로 업데이트
    static void moveTornado() {
        int x = (N-1)/2;
        int y = (N-1)/2;
        int nx=x;
        int ny=y;
        while (true) {
        	// 방향: 좌, 하, 우, 상
            for (int d = 0; d < 4; d++) { 
            	//각 방향별 이동해야하는 칸만큼 이동! 이게 토네이도의 핵심
                for (int move = 0; move < dc[d]; move++) {
                	//이동
                	nx += dx[d];
                    ny += dy[d];
                    
                    // 종료 조건
                    if (nx == 0 && ny == 0) {
                    	//0,0에서도 모래는 뿌림
                        spreadSand(x, y, d);
                        return;
                    }
                    //토네이도 이동했으니 모래 뿌림
                    spreadSand(nx, ny, d);
                }
                //해당 방향에서 다음 본인 차례에 이동할 칸 업데이트 해주기
                dc[d] += 2; // 이동 횟수 업데이트
            }
        }
    }

    static void spreadSand(int x, int y, int d) {
        int sand = map[x][y];
        map[x][y] = 0; // 모래가 이동할 위치 비우기
        int spreadTotal = 0;
        
        for (int i = 0; i < 9; i++) {
        	//다음칸에서 이동할 칸 nx,ny
            int nx = x + dsx[d][i];
            int ny = y + dsy[d][i];
            int amount = sand * sandRatio[i] / 100;
            //뿌릴 총 모래의 양 추후 a계산
            spreadTotal += amount;

            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                answer += amount; // 격자 밖으로 나간 모래
            } else {
                map[nx][ny] += amount; // 격자 내에 모래 분산
            }
        }

        int alphaX = x + dx[d]; // alpha 위치: 토네이도의 현재 방향으로 한 칸 더 이동
        int alphaY = y + dy[d];
        
        int alphaAmount = sand - spreadTotal;

        if (alphaX < 0 || alphaX >= N || alphaY < 0 || alphaY >= N) {
            answer += alphaAmount; // 격자 밖으로 나간 모래
        } else {
            map[alphaX][alphaY] += alphaAmount; // 격자 내에 남은 모래 배치
        }
    }
}
