package samsung;
import java.io.*;
import java.util.*;

/*
시간 복잡도: O(n^4) - 매번 BFS를 수행할 때 최악의 경우 모든 칸을 검사할 수 있고, 이를 물고기를 먹을 때마다 반복하기 때문
공간 복잡도: O(n^2) - 맵과 체크 배열을 저장하기 위한 공간이 필요
사용한 알고리즘: BFS(너비 우선 탐색)를 이용한 완전 탐색
사용한 자료구조: 2차원 배열(map, check), 큐(Queue)
 */

public class Boj16236_BabyShark {
    
    static int n; // 맵크기
    static int[][] map;
    static int dx[] = {-1, 0, 1, 0}; //위 왼 아래 오
    static int dy[] = {0, 1, 0, -1};
    static ArrayList<Node> fishes;
    
    static Queue<Node> shark = new LinkedList<>();
    
    static int sharkSize = 2;
    
    public static void main(String[] args) throws IOException{
    	
    	BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st;
        
        n = Integer.parseInt(br.readLine());
        //맵 정보 입력
        map = new int[n][n];
      
        for(int i = 0; i < n; i++){
        	st=new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
            	
                map[i][j] =Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                	//상어 정보 넣기
                    shark.add(new Node(i, j, 0));
                    map[i][j] = 0;
                }
            }
        }
        
        //비즈니스 로직 구현
        int eat = 0;  //먹은 물고기 개수
        int time = 0; //총 이동 횟수
        while(true){
        	//물고기 정보(리스트)
            ArrayList<Node> fish = new  ArrayList<>();
            int[][] dist = new int[n][n]; //거리 초기화
            
            //현재 상어크기로 먹을 수 있는 물고기 후보들을 찾은 후 리스트에 넣는다.
            while (!shark.isEmpty()) {
            	//상어 정보(큐)
                Node current = shark.poll();
                
                //상,하,좌,우 탐색
                for(int i=0; i<4; i++){
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];
                    //탐색한 방향이 이동 가능한 곳이면(벽이 아니거나, 탐색해본 적 없고, 물고기의 크기가 상어보다 작거나 같으면)
                    if(nx >= 0 && ny >= 0 && nx < n && ny < n && dist[nx][ny]==0 && map[nx][ny] <= sharkSize){
                        //****탐색한 곳에 거리를 넣고 거리 정보 증가(+1)*****
                    	dist[nx][ny] = dist[current.x][current.y] + 1;
                    	//상어가 이동 가능한 곳이라 큐에 추가 한다.(상어를 이동시킨다)
                        shark.add(new Node(nx, ny, dist[nx][ny]));
                        //잡아 먹을 수 있는 물고기가 있다면(물고기 크기:1~6인데 이보다 상어 크기가 더 크면)
                        if(1 <= map[nx][ny] && map[nx][ny] <= 6 && map[nx][ny] < sharkSize)
                       	{
                        	//물고기 리스트에 잡아 먹을 후보로 넣는다.(물고기 위치,거리)
                       		fish.add(new Node(nx, ny, dist[nx][ny]));
                       	}
                    }   
                }

            }
            //만약 잡아 먹을 수 있는 fish가 없으면 시뮬레이션 종료
            if(fish.size() == 0){
                System.out.println(time);
                return;
            }
 
            //위를 거쳐 물고기를 잡아 먹을 수 있다면
            //***잡아 먹을 물고기 선정***
            
            //우선 리스트에 맨 앞을 꺼낸 후
            Node currentFish = fish.get(0);
            //맨앞과 다음거 비교(조건에 만족하면 업데이트)
            for(int i = 1; i < fish.size(); i++){
            	//거리가 더 작은 쪽을 현재 잡아 먹을 물고기로 선정
                if(currentFish.dist > fish.get(i).dist) {
                    currentFish = fish.get(i);
                }
                //거리가 같으면
                else if(currentFish.dist == fish.get(i).dist) {
                	//위쪽인 즉, x값이 더 작은 물고기를 잡아 먹을 물고기로 
                    if(currentFish.x > fish.get(i).x) currentFish = fish.get(i);
                    //만약 위쪽 방향이 같다면
                    else if(currentFish.x == fish.get(i).x){
                    	//더 왼쪽 인것이 우선 순위로 잡아 먹을 물고기로 선정한다.
                        if(currentFish.y > fish.get(i).y) 
                       	{
                        	currentFish = fish.get(i);
                       	}
                    }
                }   
            }
            //시뮬레이션 시간은 
            time += currentFish.dist;
            eat+=1; //잡아 먹음
            map[currentFish.x][currentFish.y] = 0; //먹은 곳은 체크
            //만약 먹은 수가 상어 크기와 같다면 상어 크기 증가 및 먹은 횟수 초기화
            if(eat == sharkSize) {
                sharkSize+=1;
                eat = 0;
            }
            //상어를 먹은 곳으로 초기화
            shark.add(new Node(currentFish.x, currentFish.y, 0));
        }
    }
    
    //물고기의 x,y,거리 정보
    static class Node {
        int x;
        int y;
        int dist;
        
        public Node(int x, int y, int dist) {
            super();
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
}    