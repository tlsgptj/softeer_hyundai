import java.util.*;
import java.io.*;
public class DFS {
    //IOException : 자바에서 입출력 작업 중 발생하는 예외를 처리하는 것
    public static void main(String[] args) throws IOException {
        //데이터를 버퍼에 정하면서 읽기 때문에, 많은 양의 데이터를 다룰 때 속도가 빠르다.
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //자바에서 문자열은 불변하기 때문에, 문자열을 변경할 때마다 새로운 객체 생성
        StringBuilder sb = new StringBuilder();
        //연비 입력
        String[] NQ = br.readLine().split(" ");
        //NQ에다가 저장 N이 연비 데이터의 개수
        int N = Integer.parseInt(NQ[0]);
        //정수로 변환하여 변수 N에 저장
        int Q = Integer.parseInt(NQ[1]);
        //Q는 연비 쿼리의 개수를 의미
        int[] arr = new int[N];
        //N은 연비 데이터 개수, Q는 연비 쿼리의 수

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
