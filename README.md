# MiniProject_1_servlet_jsp_ajax

> **LG U+ URECA 3기 | Java Servlet + JSP + AJAX 실습 프로젝트**  
> 플레이어 CRUD(등록/조회/삭제) 기능을 중심으로, Servlet과 JSP, 그리고 AJAX 비동기 통신의 흐름을 완전히 이해하기 위한 미니 프로젝트입니다.

---

## 프로젝트 개요

이 프로젝트는 **서블릿(Servlet)**, **JSP**, **AJAX**, **JDBC**를 함께 활용하여  
프론트엔드와 백엔드 간의 통신 과정을 학습하고, 실제 데이터베이스(MySQL)와 연결하여  
**플레이어(Player) 관리 시스템**을 구현한 예제입니다.

---

## 📁 프로젝트 구조

MiniProject_1_servlet_jsp_ajax/  

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
   
User->>Browser: 플레이어 등록 클릭  
Browser->>Servlet: POST /player (playerName, email)  
Servlet->>DB: INSERT INTO players ...  
DB-->>Servlet: 성공 (201)  
Servlet-->>Browser: JSON 응답  
Browser->>Browser: listPlayer() 재호출 → 테이블 갱신  


## 배운 점
Servlet + JSP + AJAX의 전체 통신 흐름을 직접 구현하면서  
클라이언트-서버 간 요청/응답 구조의 핵심 로직을 명확히 이해함.  
BufferedReader → Gson 방식과 URLSearchParams 기반 form-urlencoded 방식의 차이를 실습함.  
fetch()의 비동기 동작(async/await)과 DOM 업데이트 타이밍을 익힘.  
서버 응답 후 즉시 listPlayer() 호출로 SPA-like 갱신 구조를 구현.  


