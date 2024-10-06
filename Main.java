// 성적 평균 구하기
// 문제) N명의 학생들의 성적이 학번 순서대로 주어졌다.
// 학번 구간 [A,B] 가 주어졌을 대 이 학생들의 성적의 평균을 구하는 프로그램을 작성
// 입력 
// 첫 번째 줄에는 학생 수 N과 구간 K가 주어진다. 
// 두 번째 줄에는 학생의 성적 S(1 <= i <= N)이 주어진다. 
// i + 2(1<= i <= K) 번째 줄에는 i번째 구간이 주어진다. 
// 출력
// i번째 줄에 i번째 구간의 성적평균 (소수셋째자리에서 반올림)을 출력
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 첫 번째 줄: 학생 수 N과 구간 수 K 입력
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        // 두 번째 줄 : 학생 성적 입력
        int[] scores = new int[N];
        for (int i = 0; i < N; i++) {
            scores[i] = scanner.nextInt();
        }

        // 구간 처리
        for (int i = 0; i < K; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();

            //구간의 성적 평균 계산
            int sum = 0;
            for (int j = A - 1; j <= B - 1; j++) {
                sum += scores[j];
            }
            double average = (double) sum / (B - A + 1);
            //소수점 셋째 자리에서 반올림하여 출력
            System.out.printf("%.3f\n", average);
        }

        scanner.close();

    }
}