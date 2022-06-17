#  트리플 클럽 마일리지 적립 

## 구현 기술 스택
- Java11
- Spring Boot (v2.7.0)
- MySQL (8.0.27)
- JPA (Hibernate)

## 테스트 케이스
![image](https://cdn.discordapp.com/attachments/485361381575622676/987367661937758208/unknown.png)

## 실행 방법
 ### SQL 파일 경로 
   1. triplebackendhomework\src\main\resources\data.sql
   2.  triplebackendhomework\src\main\resources\testTemp.sql
3. 위의 순서대로 sql파일 실행


### 서버 실행 
```
cd target
java -jar triplebackendhomework-0.0.1-SNAPSHOT.jar
```

## ERD 관계도
![image](https://cdn.discordapp.com/attachments/485361381575622676/987368882748661800/unknown.png)
- 포인트 점수에 관련한 기록은 리뷰가 삭제 되거나 장소가 삭제 되어도 남아 있어야 한다고 판단 하여 관계를 맺지 않음 





