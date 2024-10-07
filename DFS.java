/*
 * 1. 연비 문제
 * 이 문제는 이분 탐색을 사용해 연비 배열에서 특정 값을 찾아 조합을 계산하는 문제입니다. 자바의 Arrays.binarySearch()를 활용하면 쉽게 이분 탐색을 구현할 수 있습니다. 다음은 자바를 활용한 이 문제의 풀이 코드와 함께 해설입니다.
 * 코드 설명:
 * 배열을 정렬한 후, 중앙값이 배열에 존재하는지 binarySearch 메서드를 사용해 확인합니다.
 * 존재한다면 해당 인덱스의 값을 사용해 조합의 수를 계산합니다.
 * 이분 탐색의 시간복잡도는 O(logN)으로, 입력 크기가 크더라도 효율적으로 문제를 해결할 수 있습니다.
 */
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
