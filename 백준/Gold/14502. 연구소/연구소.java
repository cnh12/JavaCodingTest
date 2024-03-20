import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


class Point{
    int y;
    int x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }

    @Override
    public String toString() {
        return "Point{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
public class Main {

    static int N;
    static int M;
    static int[][] arr;
    static ArrayList<Point> zeroArr;
    static ArrayList<Point> virusArr;
    static ArrayList<Point> curArr;
    static int max;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    //빈 곳 중에 3곳 뽑아서 조합 계산
    static void combination(int cur, int cnt){
        if(cnt == 3){
            wallCalculate(curArr.get(0), curArr.get(1), curArr.get(2));
            return;
        }

        for (int i = cur+1; i < zeroArr.size(); i++) {
            curArr.add(zeroArr.get(i));
            combination(i, cnt+1);
            curArr.remove(curArr.size()-1);
        }
    }

    //copyarr 하나 만들어서 벽 세우기
    static void wallCalculate(Point p1, Point p2, Point p3){

        int[][] copyarr = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyarr[i][j] = arr[i][j];
            }
        }

        copyarr[p1.y][p1.x] = 1;
        copyarr[p2.y][p2.x] = 1;
        copyarr[p3.y][p3.x] = 1;

        virusSpread(copyarr);

    }

    //bfs로 바이러스 확산 계산
    static void virusSpread(int[][] copyarr){
        for (int i = 0; i < virusArr.size(); i++) {
            Point p = virusArr.get(i);

            Queue<Point> queue = new LinkedList<>();
            queue.add(p);
            while (!queue.isEmpty()){
                Point point = queue.poll();
                for (int d = 0; d < dy.length; d++) {
                    int my = point.y+dy[d];
                    int mx = point.x+dx[d];

                    if(my<0 || my>=N || mx<0 || mx>=M) continue;
                    if(copyarr[my][mx] == 0) {
                        copyarr[my][mx] = 2;
                        queue.add(new Point(my, mx));
                    }
                }
            }

        }

        //청정구역 계산
        int curcnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(copyarr[i][j] == 0){
                    curcnt++;
                }
            }
        }
        if(max<curcnt) max=curcnt;
    }

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stf = new StringTokenizer(br.readLine());
        N = Integer.parseInt(stf.nextToken());
        M = Integer.parseInt(stf.nextToken());

        arr = new int[N][M];
        curArr = new ArrayList<>();
        zeroArr = new ArrayList<>();
        virusArr = new ArrayList<>();
        max = 0;


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 0) {
                    zeroArr.add(new Point(i, j));
                }
                else if(arr[i][j]==2){
                    virusArr.add(new Point(i, j));
                }
            }
        }
        combination(-1, 0);

        int ans = max;
        System.out.println(ans);

    }

}
