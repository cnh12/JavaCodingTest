import java.io.*;
import java.util.*;


class Main {
    static int N;
    static int inventory;
    static int[] arr;
    static int min;
    static int max;
    static int minTime;
    static int minTimeHeight;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        N = row*col;
        inventory = Integer.parseInt(st.nextToken());
        arr = new int[N];
        minTime = Integer.MAX_VALUE;
        minTimeHeight = 0;

        int index = 0;
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                arr[index++] = Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(arr);
        min = arr[0];
        max = arr[N-1];

        calculate();

        System.out.println(minTime + " " + minTimeHeight);
    }

    static void calculate(){
        for (int cur = min; cur <= max; cur++) {
            int curTime = 0;
            int curInventory = inventory;
            boolean available = true;
            //뒤에서부터 평탄화 작업 시행
            for (int i = N-1; i >= 0; i--) {
                int diff = (arr[i]-cur);
                if(diff>0){
                    curTime += diff*2;
                    curInventory += diff;
                }
                else if(diff<0){
                    curTime += diff*-1;
                    curInventory -= diff*-1;
                }

                //cur 높이로는 평탄화 불가
                if(curInventory<0) {
                    available = false;
                    break;
                }

                //이미 최소시간보다 큰 시간인 경우 탐색 중지
                if(curTime > minTime){
                    available = false;
                    break;
                }

            }

            //cur 높이로 평탄화 가능
            if(minTime >= curTime && available) {
                minTime = curTime;
                if(minTimeHeight < cur) minTimeHeight = cur;
            }

        }
    }
}