import java.util.*;
import java.io.*;
public class DFS {
    public static void main(String[] args) throws IOException {
        //데이터를 버퍼에 정하면서 읽기 때문에, 많은 양의 데이터를 다룰 때 속도가 빠르다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //자바에서 문자열은 불변하기 때문에, 문자열을 변경할 때마다 새로운 객체 생성
        StringBuilder sb = new StringBuilder();
        //연비 입력
        String[] NQ = br.readLine().split(" ");
        int N = Integer.parseInt(NQ[0]);
        int Q = Integer.parseInt(NQ[1]);
        int[] arr = new int[N];

        //배열 정렬 
        String[] line = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(line[i]);
        }
        Arrays.sort(arr);

        //이분 탐색 
        for (int i = 0; i < Q; i++) {
            int m = Integer.parseInt(br.readLine());
            int idx = Arrays.binarySearch(arr, m);

            //값이 존재할 경우 조합 계산
            if (idx >= 0) {
                sb.append(idx * ((N - 1) - idx)).append("\n");
            } else {
                sb.append(0).append("\n");
            }
        }
        System.out.println(sb);

    }
    
}
