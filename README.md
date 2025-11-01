# MiniProject_1_servlet_jsp_ajax

> **LG U+ URECA 3기 | Java Servlet + JSP + AJAX 실습 프로젝트**  
> 게임 랭킹 관리시스템 CRUD 기능을 중심으로, Servlet과 JSP, 그리고 AJAX 비동기 통신의 흐름을 완전히 이해하기 위한 미니 프로젝트1을 리팩토리하며 공부한 내용입니다.

---

## 프로젝트 개요

이 프로젝트는 **서블릿(Servlet)**, **JSP**, **AJAX**, **JDBC**를 함께 활용하여  
미니프로젝트 1을 리팩토링하며 프론트엔드와 백엔드 간의 통신 과정을 학습하고, 실제 데이터베이스와 연결하여  
**랭킹 관리 시스템**을 구현한 예제입니다.  

MiniProject_1_servlet_jsp_ajax/라고   
Dynamic Web Project로 생성하였지만 실습을 쉽게 진행하기 위해 Context-root를 mini_ajax 로  
수정하여 브라우저창에서의 URL 요청인 GET 방식으로 http://localhost:8080/mini_ajax/ 이렇게 접근할수있게하고,  
디렉터리 구조는 최대한 MVC패턴을 활용하여 구현하였다.  
이전 미니프로젝트1에서는 UI를 SWING으로 구현하였지만 이번 프로젝트는 JSP를 활용해 웹에서 진행되는 웹프로젝트이다.  

---

## 📁 프로젝트 구조

<img width="491" height="1431" alt="image" src="https://github.com/user-attachments/assets/785dfdf3-4339-493a-bc12-b4ad6542c48d" />



## 주요 기능

| 기능 | 설명 |
|------|------|
| **플레이어 조회** | DB에 등록된 모든 플레이어 목록을 비동기로 가져와 테이블에 렌더링 |
| **플레이어 등록** | `fetch(POST)`를 통해 입력된 이름, 이메일을 서버로 전송 후 DB에 추가 |
| **플레이어 삭제** | 특정 ID를 전달하여 DB에서 해당 레코드 삭제 |
| **AJAX 비동기 갱신** | 등록 및 삭제 후 `listPlayer()`가 자동 호출되어 즉시 반영 |

---

## 기술 스택

| 구분 | 사용 기술 |
|------|------------|
| **Backend** | Java 17, Servlet, JSP, JDBC |
| **Frontend** | HTML5, JavaScript (Fetch API, AJAX) |
| **DB** | MySQL 8.0 |
| **Build/Deploy** | Apache Tomcat 10.1, Eclipse IDE |
| **Library** | Gson 2.10.1 (JSON 변환) |

---

## ⚙️ 데이터베이스 구성

```sql
USE mini_gamedb;

-- -----------------------------------------------------
-- 1. genres (장르 테이블)
-- -----------------------------------------------------
CREATE TABLE genres (
    genre_id INT AUTO_INCREMENT PRIMARY KEY,
    genre_name VARCHAR(50) UNIQUE NOT NULL
);

-- -----------------------------------------------------
-- 2. games (게임 테이블)
-- -----------------------------------------------------
CREATE TABLE games (
    game_id INT AUTO_INCREMENT PRIMARY KEY,
    game_title VARCHAR(100) NOT NULL,
    genre_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_game_genre FOREIGN KEY (genre_id)
        REFERENCES genres(genre_id)
        ON DELETE SET NULL
);

-- -----------------------------------------------------
-- 3. players (플레이어 테이블)
-- -----------------------------------------------------
CREATE TABLE players (
    player_id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- -----------------------------------------------------
-- 4. game_rankings (랭킹 테이블)
-- -----------------------------------------------------
CREATE TABLE game_rankings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_id INT NOT NULL,
    game_id INT NOT NULL,
    score INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_ranking_player FOREIGN KEY (player_id)
        REFERENCES players(player_id)
        ON DELETE CASCADE,
    CONSTRAINT fk_ranking_game FOREIGN KEY (game_id)
        REFERENCES games(game_id)
        ON DELETE CASCADE
);

```

## 실습 사진  

<img width="2363" height="925" alt="image" src="https://github.com/user-attachments/assets/d9159cbb-ea69-45e1-a07b-e1e892df6f83" />
<img width="1260" height="636" alt="image" src="https://github.com/user-attachments/assets/13a94ab0-ffde-4156-9ba3-0efd78bb0807" />
<img width="1586" height="928" alt="image" src="https://github.com/user-attachments/assets/40739d2d-5d07-4f9b-8f17-4651e3b199d4" />
<img width="1973" height="790" alt="image" src="https://github.com/user-attachments/assets/ca4b4213-6d82-485e-9969-2e3fdfde13b3" />





## 배운 점
Servlet + JSP + AJAX의 전체 통신 흐름을 직접 구현하면서  
클라이언트-서버 간 요청/응답 구조의 핵심 로직을 명확히 이해함.  
BufferedReader → Gson 방식과 URLSearchParams 기반 form-urlencoded 방식의 차이를 실습함.  
fetch()의 비동기 동작(async/await)과 DOM 업데이트 타이밍을 익힘.  
서버 응답 후 즉시 listPlayer() 호출로 SPA-like 갱신 구조를 구현.  


