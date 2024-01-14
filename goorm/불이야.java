import java.io.*;
import java.util.*;

class Point{
    int y;
    int x;
    int cnt;
    Point(int y, int x, int cnt){
        this.y = y;
        this.x = x;
        this.cnt = cnt;
    }
}
class Main {
    static int N;
    static int M;
    static String[][] arr;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int ans = -1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new String[N][M];
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = input.charAt(j)+"";
                if(arr[i][j].equals("@")){
                    queue.add(new Point(i, j, -1));
                }
            }
        }


        while (!queue.isEmpty()){
            Point point = queue.poll();

            //4방탐색
            for (int d = 0; d < 4; d++) {
                int my = point.y+dy[d];
                int mx = point.x+dx[d];
                if(my<0||my>=N||mx<0||mx>=M) continue;
                if(arr[my][mx].equals("&")) { //탈출조건
                    ans = point.cnt+1;
                    break;
                }
                //불이 번질 수 있다면
                if(arr[my][mx].equals(".")){
                    arr[my][mx] = (point.cnt+1)+"";
                    queue.add(new Point(my, mx, Integer.parseInt(arr[my][mx])));
                }
            }

            if(ans!=-1) break;
        }

        System.out.println(ans);
        

    }
}