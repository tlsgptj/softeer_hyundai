/* 문제) 알파벳 대문자로 이루어진 길이 N의 문자열 S = s1s2...sN이 주어진다. 이때, 다음과 같은 쿼리를 Q번 처리해야한다. 
- 1 idx c : sidx를 c로 변경한다. 
- 2 | r : sisi+1...sr의 부분열 중 ROCK으로 끝나는 문자열의 개수를 출력한다. 단, 수가 매우 클 수 가 있으니 10^9 + 7로 나눈 나머지를 출력한다. 
제약조건
4<=N<=250,000
문자열 S는 알파벳 대문자로만 이루너져 있다. 
1 <= Q <= 250,000
가장 마지막으로 주어지는 쿼리는 2번 쿼리이다. 
입력형식 
첫 번째 줄에 문자열 S의 길이 N이 주어진다. 
두 번째 줄에 문자열 S가 주어진다. 
세 번째 줄에 쿼리의 개수 Q가 주어진다. 
네 번째 줄부터 Q개의 줄에 걸쳐 쿼리가 주어진다. 
출력형식 
2번 쿼리에 대해 정답을 한 줄에 하나씩 출력한다.*/
import java.util.Scanner;

public class segment {
    static final int MOD = 1000000007;
    static char[] s;
    static int[] segmentTree;
    static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //첫 번째 줄 : 문자열의 길이 N
        n = scanner.nextInt();
        s = new char[n];
        segmentTree = new int[4 * n]; //세그먼트 트리 배열

        // 두 번째 줄 : 문자열 s 
        String input = scanner.next();
        s = input.toCharArray();

        //세그먼트 트리 초기화
        build(0, 0, n-1);

        //세 번째 줄 : 쿼리의 개수 Q
        int q = scanner.nextInt();

        //쿼리 처리
        while (q --> 0) {
            int queryType = scanner.nextInt();
            if (queryType == 1) {
                // 1 idx c : s[idx]를 c로 변경한다.
                int idx = scanner.nextInt() - 1;
                char c = scanner.next().charAt(0);
                update(0, 0, n-1, idx, c);
            } else if (queryType == 2) {
                // 2 r : s[0..r] 부분 문자열에서 "ROCK"로 끝나는 개수
                int r = scanner.nextInt() - 1;
                int result = query(0, 0, n-1, 0, r);
                System.out.println(result);
            }
            
        }

        scanner.close();

    }

    //세그먼트 함수를 써줘야한다.
    static void build(int node, int start, int end) {
        if (start == end) {
            // 리프 노드: "ROCK"의 끝일 수 있는 자리인지 확인
            segmentTree[node] = isRockEnd(start) ? 1 : 0;

        } else {
            int mid = (start + end) / 2;
            build(2 * node + 1, start, mid);
            build(2 * node + 2, mid + 1, end);
            segmentTree[node] = (segmentTree[2 * node + 1] + segmentTree[2 * node + 2]) % MOD;
        }
    }

    //세그먼트 트리 업데이트
    static void update(int node, int start, int end, int idx, char newChar) {
        if (start == end) {
            // 리프 노드 업데이트
            s[idx] = newChar;
            segmentTree[node] = isRockEnd(start) ? 1 : 0;
        } else {
            int mid = (start + end) / 2;
            if (start <= idx && idx <= mid) {
                update(2 * node + 1, start, mid, idx, newChar);
            } else {
                update(2 * node + 2, mid + 1, end, idx, newChar);
            }
            segmentTree[node] = (segmentTree[2 * node + 1] + segmentTree[2 * node + 2]) % MOD;
        }
    }

    // 세그먼트 트리 쿼리
    static int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            // 범위 밖
            return 0;
        }
        if (l <= start && end <= r) {
            // 범위 내에 완전히 포함
            return segmentTree[node];
        }
        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);
        return (leftSum + rightSum) % MOD;
    }

    // "ROCK"의 끝인지 확인하는 함수
    static boolean isRockEnd(int idx) {
        if (idx < 3) return false;
        return s[idx - 3] == 'R' && s[idx - 2] == 'O' && s[idx - 1] == 'C' && s[idx] == 'K';
    }

}

/*
 * 코드 설명 
 * 1. 변수 및 상수
 * - MOD: 10^9 + 7로, 쿼리 결과가 매우 클 수 있으므로 결과를 이 값으로 나눈 나머지 반환 
 * s[] 주어진 문자열을 문자 배열로 저장 
 * segmentTree[] : 세그먼트 트리 배열 
 * n : 문자열의 길이 
 * 세그먼트 트리란?
 * 세그먼트 트리는 배열이나 리스트 같은 연속적인 데이터를 다룰 때, 특정 구간에 대한 "쿼리"와 업데이트를 효율적으로 처리하기 위해 사용
 * build() -> 세그먼트 트리 생성
 * update() -> 세그먼트 트리 업데이트
 * query() -> 세그먼트 트리 쿼리 
 * isRockEnd() -> "ROCK"로 끝나는지 확인
 */