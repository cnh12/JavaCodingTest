import java.io.*;
import java.util.*;


class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 0; test_case < T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            String input = st.nextToken();

            StringBuilder str = new StringBuilder();
            for (int i = 0; i < input.length(); i++) {
                for (int j = 0; j < N; j++) {
                    str.append(input.charAt(i));
                }
            }

            System.out.println(str);
        }

    }
}