import java.io.*;
import java.util.*;

class Point{
    int y;
    int x;
    Point(int y, int x){
        this.y=y; this.x=x;
    }
}

class Main {
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visit;
    static int[] dy = {-1,1,0,0};
    static int[] dx = {0,0,-1,1};
    static List<Integer> scoreList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N][M];
        visit = new boolean[N][M];
        scoreList = new ArrayList();

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // for(int i = 0; i < N; i++){
        // 	System.out.println(Arrays.toString(arr[i]));
        // }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                //탐색 시작
                if(!visit[i][j] && arr[i][j]!=1){
                    Queue<Point> queue = new LinkedList<>();
                    queue.add(new Point(i,j));
                    int score = 0;

                    //같은 주차구역 탐색, bfs
                    while(!queue.isEmpty()){
                        Point point = queue.poll();
                        if(visit[point.y][point.x]) continue;
                        if(arr[point.y][point.x] == 2) score-=2;
                        else if(arr[point.y][point.x] == 0) score+=1;

                        visit[point.y][point.x] = true;

                        for(int d=0; d<4; d++){
                            int my = point.y+dy[d];
                            int mx = point.x+dx[d];
                            if(my<0||my>=N || mx<0||mx>=M) continue;
                            if(!visit[my][mx] && arr[my][mx]!=1) queue.add(new Point(my, mx));

                        }
                    }

                    scoreList.add(score);
                }
            }
        }

        Collections.sort(scoreList);
        if(scoreList.size()>0 && scoreList.get(scoreList.size()-1)<0) System.out.println(0);
        else if(scoreList.size()>0) System.out.println(scoreList.get(scoreList.size()-1));
        else System.out.println(0);
    }
}