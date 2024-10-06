# 성적 평균 구하기
# 문제) N명의 학생들의 성적이 학번 순서대로 주어졌다.
# 학번 구간 [A,B] 가 주어졌을 대 이 학생들의 성적의 평균을 구하는 프로그램을 작성
# 입력 
# 첫 번째 줄에는 학생 수 N과 구간 K가 주어진다. 
# 두 번째 줄에는 학생의 성적 S(1 <= i <= N)이 주어진다. 
# i + 2(1<= i <= K) 번째 줄에는 i번째 구간이 주어진다. 
# 출력
# i번째 줄에 i번째 구간의 성적평균 (소수셋째자리에서 반올림)을 출력
# 입력 받기
N, K = map(int, input().split()) #학생 수 N과 구간 수 K
scores = list(map(int, input().split())) #N명의 학생 성적 리스트 

#구간별 입력을 저장할 리스트 
ranges = []
for _ in range(K):
    A, B = map(int, input().split())
    ranges.append((A, B))

# 구간별 평균 성적 계산 및 출력 
for A, B in ranges:
    # 주어진 구간은 [A, B]이며, 인덱스는 0-based로 변환하여 사용
    avg_score = sum(scores[A-1:B]) / (B - A + 1)
    print(f"{avg_score:.3f}")