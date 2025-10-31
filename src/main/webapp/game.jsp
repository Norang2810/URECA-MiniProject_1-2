<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 관리</title>

</head>

<body>
    <h1>게임 관리</h1>

    <table border="1" width="600">
        <thead>
            <tr>
                <th>ID</th>
                <th>게임명</th>
                <th>장르ID</th>
                <th>등록일</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody id="gameTbody"></tbody>
    </table>

    <hr>
    <h3>새 게임 등록</h3>
    <input type="text" id="gameTitle" placeholder="게임명 입력"> 
    <input type="number" id="genreId" placeholder="장르 ID (선택)">
    <button onclick="addGame()">등록</button>
</body>

<script>
window.onload = function() {
    loadGames();
};

async function loadGames() {
    const response = await fetch("/mini_ajax/game?job=list");
    const data = await response.json();

    const tbody = document.getElementById("gameTbody");
    tbody.innerHTML = "";

    data.forEach(g => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>\${g.gameId}</td>
            <td>\${g.gameTitle}</td>
            <td>\${g.genreId ?? '-'}</td>
            <td>\${g.createdAt}</td>
            <td><button onclick="deleteGame(${g.gameId})">삭제</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function addGame() {
    const gameTitle = document.getElementById("gameTitle").value;
    const genreId = document.getElementById("genreId").value || null;

    const response = await fetch("/mini_ajax/game?job=insert", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ gameTitle, genreId })
    });

    if (response.ok) {
        alert("등록 성공!");
        document.getElementById("gameTitle").value = "";
        document.getElementById("genreId").value = "";
        loadGames();
    } else {
        alert("등록 실패!");
    }
}

async function deleteGame(id) {
    if (!confirm("정말 삭제하시겠습니까?")) return;
    const response = await fetch(`/mini_ajax/game?job=delete&id=${id}`, { method: "DELETE" });
    if (response.ok) {
        alert("삭제 완료!");
        loadGames();
    }
}
</script>
</html>
