import java.util.*; //자바 유틸리티 패키지 임포트
import java.io.*; //자바 입출력 패키지 임포트

public class DFS2 {
    //최소 면적을 저장할 정적 변수, 초기값은 가능한 가장 큰 값으로 설정
    static int minArea = Integer.MAX_VALUE;
    //각 색깔별 좌표를 지정하는 ArrayList 배열
    static ArrayList<int[]>[] colorDots;
    
    public static void main(String args[]) throws IOException {
        //입력을 받기 위한 BufferReader 생성
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //첫 줄 입력을 받아 공백으로 구분한 후, n과 k를 추출
        String[] nk = br.readLine().split(" ");
        int n = Integer.parseInt(nk[0]); //점의 개수
        int k = Integer.parseInt(nk[1]); //색깔의 개수
        // 각 색깔에 해당하는 좌표를 저장할 ArrayList 배열 생성
        colorDots = new ArrayList[k + 1]; //색깔이 1부터 k까지 사용되므로 크기를 k+1로 생성
        for (int i = 1; i <= k; i++) {
            colorDots[i] = new ArrayList<>(); //각 색깔별로 ArrayList를 초기화 
        }
        //각 점의 좌표와 색깔 정보를 입력받아 colorDots에 저장
        for (int i = 0; i < n; i++) {
            String[] dot = br.readLine().split(" ");
            int x = Integer.parseInt(dot[0]); //X좌표
            int y = Integer.parseInt(dot[1]); //Y좌표
            int color = Integer.parseInt(dot[2]); //점의 색깔
            colorDots[color].add(new int[]{x, y}); 
            //해당 색깔의 ArrayList에 (x, y) 좌표를 추가
        }
        // 백트래킹 시작, 초기 색깔은 1, x와 y의 경로를 저장할 리스트는 비어 있음
        backtracking(1, k, new ArrayList<>(), new ArrayList<>());
        // 최소 면적을 출력
        System.out.println(minArea);
    }
    // 백트래킹 메서드 : 모든 색깔을 포함하는 직사각형의 최소 면적을 구함
    static void backtracking(int color, int endColor, List<Integer> pathX, List<Integer> pathY) {
        // 종료 조건 : 모든 색깔을 탐색했을 경우
        if (color > endColor) {
            // 현재 저장된 좌표들로 직사각형의 면적을 계산
            int xMin = Collections.min(pathX); // x좌표 중 최소값
            int xMax = Collections.max(pathX); // x좌표 중 최대값
            int yMin = Collections.min(pathY); // y좌표 중 최소값
            int yMax = Collections.max(pathY); // y좌표 중 최대값 
            int area = (xMax - xMin) * (yMax - yMin); //직사각형의 면적 계산
            minArea = Math.min(minArea, area); 
            // 현재 계산한 면적이 최소 면적보다 작으면 업데이트
            return;
        }
        // 현재 색깔의 모든 좌표에 대해 백트래킹 수행
        for (int[] dot : colorDots[color]) {
            pathX.add(dot[0]); //현재 좌표의 x값을 경로에 추가
            pathY.add(dot[1]); //현재 좌표의 y값을 경로에 추가
            // 다음 색깔을 탐색하도록 재귀 호출
            backtracking(color + 1, endColor, pathX, pathY);
            // 백트래킹 : 경로에서 마지막으로 추가한 좌표를 제거하여 이전 상태로 복원
            pathX.remove(pathX.size() - 1);
            pathY.remove(pathY.size() - 1);
        }
    }
}

