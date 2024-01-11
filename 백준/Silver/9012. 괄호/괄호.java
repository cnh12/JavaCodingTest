import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 0; test_case < T; test_case++) {
            String input = br.readLine();

            int cnt = 0;
            boolean result = true;
            for (int i = 0; i < input.length(); i++) {
                char cur = input.charAt(i);
                if(cur=='(') cnt++;
                else if (cur==')') cnt--;

                if(cnt<0) {
                    result = false;
                    break;
                }
            }
            if(result)  {
                if(cnt == 0) System.out.println("YES");
                else System.out.println("NO");
            }
            else System.out.println("NO");
        }


    }

}