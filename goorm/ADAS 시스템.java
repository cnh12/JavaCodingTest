import java.io.*;
import java.util.*;

/**
 * 1. 정렬기준 수립 시 오름차순/내림차순 실수
 * - 오름차순 : this가 더 크다면 1 리턴;
 * 2. 정렬을 해야 한다는 생각에 Queue를 못쓰고 ArrayList 사용 -> PriorityQueue로 대체
 * 3. 음수처리조건 (전체 점수가 음수이면 0으로 처리)
 */
class Point implements Comparable<Point> {

    public Point(int y, int x, int p) {
        this.y = y;
        this.x = x;
        this.p = p;

    }

    int y;
    int x;
    int p;  //'P'면 1, '0'이면 0

    //우선순위 맞게 정렬
    @Override
    public int compareTo(Point o) {
        if(this.p<o.p) return 1;
        else if(this.p>o.p) return -1;
        else{
            if(this.y>o.y) return 1;
            else if(this.y<o.y) return -1;
            else {
                if(this.x>o.x) return 1;
                else if(this.x<o.x) return -1;
                else return 0;
            }
        }
    }
}

class Main {
    static int N;
    static int M;
    static char[][] arr;
    static int[][] map;  
    static boolean[][] visit;

    static int score;

    static int[] dy = {-1,-1,-1,0,0,1,1,1};
    static int[] dx = {-1,0,1,-1,1,-1,0,1};

    //각 칸 점수 미리 계산
    static int calculateScore(int y, int x){
        int pnum = 0;
        for (int d = 0; d < 8; d++) {
            int my = y+dy[d];
            int mx = x+dx[d];
            if(my<0 || my>=N || mx<0 || mx>=M) continue;
            if(arr[my][mx]=='P') pnum++;
        }

        if(arr[y][x]=='P') return pnum-3;
        else return pnum;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new char[N][M];
        map = new int[N][M];
        visit = new boolean[N][M];
        score = 0;
        PriorityQueue<Point> queue = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j);
                if(arr[i][j] == 'S') queue.add(new Point(i, j, 0));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = calculateScore(i, j);
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }
        //초기 점수 세팅
        score -= map[queue.peek().y][queue.peek().x];


        while(!queue.isEmpty()){
            Point point = queue.poll();

            //큐에서 꺼낸 점이 이미 방문한 점이라면 PASS
            if(visit[point.y][point.x]) continue;
            visit[point.y][point.x] = true;

            score += map[point.y][point.x];

            //4방향 탐색
            int i = point.y;
            int j = point.x;
            if(i-1>=0 && !visit[i-1][j]){
                if(arr[i-1][j]=='0') queue.add(new Point(i-1, j, 0));
                else if(arr[i-1][j]=='P') queue.add(new Point(i-1, j, 1));
                else if(arr[i-1][j]=='E') break;
            }
            if(i+1<N && !visit[i+1][j]){
                if(arr[i+1][j]=='0') queue.add(new Point(i+1, j, 0));
                else if(arr[i+1][j]=='P') queue.add(new Point(i+1, j, 1));
                else if(arr[i+1][j]=='E') break;
            }
            if(j-1>=0 && !visit[i][j-1]){
                if(arr[i][j-1]=='0') queue.add(new Point(i, j-1, 0));
                else if(arr[i][j-1]=='P') queue.add(new Point(i, j-1, 1));
                else if(arr[i][j-1]=='E') break;
            }
            if(j+1<M && !visit[i][j+1]){
                if(arr[i][j+1]=='0') queue.add(new Point(i, j+1, 0));
                else if(arr[i][j+1]=='P') queue.add(new Point(i, j+1, 1));
                else if(arr[i][j+1]=='E') break;
            }

        }

        if(score<0) score = 0;
        System.out.println(score);


    }
}