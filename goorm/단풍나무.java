import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    static int N;
    static int[][] arr;
    static int[][] copyarr;
    static int count;
    static int day;

    static void makeCopyArr(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                copyarr[i][j] = arr[i][j];
            }
        }
    }

    static void makeArr(){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                arr[i][j] = copyarr[i][j];
            }
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        copyarr = new int[N][N];
        count = 0;
        day = 0;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 0) count++;
            }
        }

//        for (int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(arr[i]));
//        }

        while(true){

            if(count==N*N){
                break;
            }

            //아침
            makeCopyArr();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(arr[i][j]!=0){
                        int tmpcnt = 0;
                        //상하좌우 검사
                        if(i-1>=0 && arr[i-1][j]==0){
                            tmpcnt++;
                        }
                        if(i+1<N && arr[i+1][j]==0){
                            tmpcnt++;
                        }
                        if(j-1>=0 && arr[i][j-1]==0){
                            tmpcnt++;
                        }
                        if(j+1<N && arr[i][j+1]==0){
                            tmpcnt++;
                        }

                        //copyarr에 업데이트
                        if(arr[i][j]<=tmpcnt){
                            copyarr[i][j] = 0;
                            count++;
                        }
                        else {
                            copyarr[i][j] = copyarr[i][j]-tmpcnt;
                        }
                    }
                }
            }

            makeArr();
            day++;


        }

        System.out.println(day);


    }
}