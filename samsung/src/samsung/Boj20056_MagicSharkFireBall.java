package samsung;

import java.util.*;
import java.io.*;

public class Boj20056_MagicSharkFireBall {
    
    static int N, M, k;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
    
    static class FireBall {
        int x, y, m, s, d;
        FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    
    //ArayList를 2차원 배열로 선언
    static ArrayList<FireBall>[][] map;

    //map에 있는 모든 fireball의 총합 구하는 함수
    static int sum() {
        int totalMass = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FireBall ball : map[i][j]) {
                    totalMass += ball.m;
                }
            }
        }
        return totalMass;
    }
    // 1. 명령에 따라 이동한다.
    // 2. 합친다.
    // 3. 분열한다.
    static void order() {
    	// newMap에 명령 수행하기
    	
    	//ArrayList안에 2차원 배열로 초기화
        ArrayList<FireBall>[][] newMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }
        
        //1.이동하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FireBall ball : map[i][j]) {
                    int nextX = (ball.x + dx[ball.d] * ball.s) % N;
                    int nextY = (ball.y + dy[ball.d] * ball.s) % N;
                    if (nextX < 0) nextX += N;
                    if (nextY < 0) nextY += N;
                    newMap[nextX][nextY].add(new FireBall(nextX, nextY, ball.m, ball.s, ball.d));
                }
            }
        }
        
        //2.병합하고, 분열하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
            	//2개이상이면
                if (newMap[i][j].size() >= 2) {
                	//1.병합하기
                    int mSum = 0, sSum = 0, count = newMap[i][j].size();
                    boolean allEven = true, allOdd = true;
                    for (FireBall ball : newMap[i][j]) {
                        mSum += ball.m;
                        sSum += ball.s;
                        if (ball.d % 2 == 0) allOdd = false;
                        else allEven = false;
                    }
                    newMap[i][j].clear();
                    //2.분열하기
                    if (mSum / 5 > 0) {
                        for (int d = 0; d < 8; d += 2) {
                            if (allOdd || allEven)
                            {
                            	newMap[i][j].add(new FireBall(i, j, mSum / 5, sSum / count, d));
                           	}
                            else {
                            	newMap[i][j].add(new FireBall(i, j, mSum / 5, sSum / count, d + 1));
                            }
                        }
                    }
                }
            }
        }
        
        map = newMap;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //입력
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        //2차원 배열안에 ArrayList로 초기화하기
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
        //값 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            map[x][y].add(new FireBall(x, y, m, s, d));
        }
        
        //실제 구현
        
        //K번 수행
        for (int i = 0; i < k; i++) {
        	order();
        }
        
        //k번 수행 후 map에 있는 질량 합 구하기
        int finalM=sum();
        System.out.println(finalM);
    }
}
