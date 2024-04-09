package samsung;

import java.io.*;
import java.util.*;

public class Boj16235_TreeInvestment {

    static int n; // 맵 크기
    static int[][] map; // 초기 양분 정보 맵: 전부 5로 초기화
    static int[][] addGood; // 맵 입력

    static int initialTreeCnt; // 처음 심은 나무 개수 (M)
    static int k; // 반복 시킬 횟수(단위: 1년)

    static ArrayList<Tree> trees = new ArrayList<>(); //나무들
    static ArrayList<Tree> deadTrees = new ArrayList<>(); //죽은 나무들

    // 나무의 특성: 위치, 나이
    static class Tree {
        int x;
        int y;
        int age;
        boolean dead; //나무 생존 여부
        Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }
    }

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // n, m, k 입력
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        initialTreeCnt = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // addGood 입력
        addGood = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                addGood[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 나무 정보 입력
        for (int i = 0; i < initialTreeCnt; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            trees.add(new Tree(x, y, age));
        }

        // 비즈니스 로직 구현
        
        //Collections 클래스를 통해 ArrayList를 age기준으로 정렬 해준다.
        
        //**암기 ((t1-t2) > 0 )=true 이면 t2가 더 작으므로 t2가 앞으로 간다.
        // t1-t2<=0 이면 그냥 유지한다.
//        Collections.sort(trees, (t1, t2) -> t1.age - t2.age);
        //Collections.sort(trees, (t1, t2) -> t2.age - t1.age);

        // 초기 맵
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
        	
        	for (int j = 0; j < n; j++) {
        		map[i][j]=5;// 전부 5로 초기화
        	}	
        }

        //k년 동안 반복
        for (int year = 0; year < k; year++) {
        	//매해 마다 나무들 나이 순대로 초기화 해놓기
        	Collections.sort(trees, (t1, t2) -> t1.age - t2.age);
            // 봄
            spring();

            // 여름
            summer();

            // 가을
            fall();

            // 겨울
            winter();
        }
        //ArrayList에 있는 나무 개수(size)를 출력
        System.out.print(trees.size());
    }

    static void spring() {
    	//모든 트리에서 하나씩 적용(이미 Collections를 통해 나이 순으로 정렬 되어 있다)
    	ArrayList<Tree> survivedTrees=new ArrayList<>();
    	for(int i=0;i<trees.size();i++)
    	{
    		Tree tree=trees.get(i);
    		//땅의 양분이 나무의 나이보다 적으면
    		if(map[tree.x][tree.y]>=tree.age)
    		{
    			//아니면 땅의 양분 업데이트, 나무 나이 증가
        		map[tree.x][tree.y]-=tree.age;
        		tree.age+=1;
        		survivedTrees.add(tree); // 살아남은 나무 리스트에 추가
    		}
    		else {
    			deadTrees.add(tree); //해당 나무를 죽은 나무로 취급
    		}
    	}
    	trees=survivedTrees; //// 살아남은 나무들로 기존 나무 리스트를 업데이트
    }

    static void summer() {
    	
    	for(int i=0;i<deadTrees.size();i++)
    	{
        	Tree deadedTree=deadTrees.get(i);
        	map[deadedTree.x][deadedTree.y]+=deadedTree.age/2;
    	}
        deadTrees.clear(); // 죽은 나무들 클리어
    }

    static void fall() {
        ArrayList<Tree> newTrees = new ArrayList<>(); // 새로 생겨난 나무들
        
        for (Tree tree : trees) {
        	//만약 tree의 나이가 5의 배수이면
            if (tree.age % 5 == 0) {
            	//dx,dy: 움직일 거리
            	for (int dx = -1; dx <= 1; dx++) {
            	    for (int dy = -1; dy <= 1; dy++) {
            	        if (dx == 0 && dy == 0) continue; // 본인 위치 제외
            	        int nx = tree.x + dx;
            	        int ny = tree.y + dy;
            	        if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
            	            newTrees.add(new Tree(nx, ny, 1));
            	        }
            	    }
            	}
            }
        }
        trees.addAll(newTrees);
    }
    //양분 추가하기
    static void winter() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] += addGood[i][j];
            }
        }
    }
}
