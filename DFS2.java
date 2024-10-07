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
        
        backtracking(1, k, new ArrayList<>(), new ArrayList<>());
        System.out.println(minArea);
    }

    static void backtracking(int color, int endColor, List<Integer> pathX, List<Integer> pathY) {
        if (color > endColor) {
            int xMin = Collections.min(pathX);
            int xMax = Collections.max(pathX);
            int yMin = Collections.min(pathY);
            int yMax = Collections.max(pathY);
            int area = (xMax - xMin) * (yMax - yMin);
            minArea = Math.min(minArea, area);
            return;
        }

        for (int[] dot : colorDots[color]) {
            pathX.add(dot[0]);
            pathY.add(dot[1]);
            backtracking(color + 1, endColor, pathX, pathY);
            pathX.remove(pathX.size() - 1);
            pathY.remove(pathY.size() - 1);
        }
    }
}

