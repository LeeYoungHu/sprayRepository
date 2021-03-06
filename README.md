# 백앤드 사전 개발과제

## 프로젝트 정보

### 1. 프로젝트 구성 정보
+ JDK 1.8(Java)
+ Spring boot 2.2.8
+ Maven
+ My sql 8.0(Database)
+ Test : Junit5
+ 사용 tool : sts4, MySQL Workbench
+ 프로젝트 구동 방법 
```
 프로젝트 받은 후 Mysql 로컬에 schema kakaotask, user kakao, pw kakao01!@ 로 생성
 table.sql에 있는 테이블 생성(JDK1.8 및 My sql 8.0은 이미 있다고 가정)
 $ java -jar "kakaopay-task\target\kakaopay-task-0.0.1-SNAPSHOT.war" 로 java option 사용하여 실행
```

------------

### 2. 핵심 문제 해결 전략
1. token 발급
```
 token은 token 생성 시 사용할 문자와 길이를 yaml에 등록 후 String을 char[]로 변환하여 Random 함수 통해 난수 생성
 token이 겹칠 확률이 있으며 받기, 조회 API시 token값, 채팅방 Header만 넘어오기에 10분과 7일이라는 제약 조건에 따라
 이전 달과 현재 달 내에는 같은 token으로 발급 되지 않도록 sql로 조회하여 조건에 맞는 토큰 발급
```

2. 분배 로직
```
 실제로 카카오에서 뿌리기 기능 확인한 결과 각 사용자들에게 0원이 돌아가도 상관없는 구조임을 확인함.
 따라서 총 금액에서 Random 함수 통해 값 생성 후 0원이 들어가도 상관없도록 로직 구성
 마지막 인원의 받기 금액 배정시에는 남은 모든 금액 입력하여 남는 돈이 없도록 
 추후에 받기 API가 들어오면 아직 받지 않은 SEQ들의 값을 조회한 후 순번대로 발급
 금액이 이미 Random이므로 들어오는 순서대로 발급하여도 무방하다고 판단
```

3. 댜량의 트래픽 문제
```
 다량의 트래픽 문제를 해결하기 위해 KAKAO_SPRAY, KAKAO_DETAIL, KAKAO_REC_DETAIL 월 파티션 테이블로 생성
 7일 이후 조회가 불가능한 구조이므로 일 파티션 테이블로로 생성하여도 무방하다 생각 됨
 신한에서 카드 데이터를 월로 하여 파티션 구성을 했으므로 성능에는 문제가 없을 것으로 판단
 대용량 트래픽에서는 Update를 지향해야 하므로 모든 로직은 Insert로만 구성
 조회 쿼리에는 무조건 파티션 키 값 및 PK 컬럼이 들어가게 구성
```
