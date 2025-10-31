<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게임 랭킹</title>

</head>

<body>
    <h1>랭킹 관리</h1>

    <table border="1" width="800">
        <thead>
            <tr>
                <th>ID</th>
                <th>플레이어명</th>
                <th>게임명</th>
                <th>점수</th>
                <th>등록일</th>
                <th>관리</th>
            </tr>
        </thead>
        <tbody id="rankingTbody"></tbody>
    </table>

    <hr>
    <h3>새 랭킹 등록</h3>
    <input type="number" id="playerId" placeholder="플레이어 ID">
    <input type="number" id="gameId" placeholder="게임 ID">
    <input type="number" id="score" placeholder="점수">
    <button onclick="addRanking()">등록</button>
</body>

<script>
window.onload = function() {
    loadRankings();
};

async function loadRankings() {
    const response = await fetch("/mini_ajax/ranking?job=list");
    const data = await response.json();

    const tbody = document.getElementById("rankingTbody");
    tbody.innerHTML = "";

    data.forEach(r => {
        const tr = document.createElement("tr");
        tr.innerHTML = `
            <td>\${r.id}</td>
            <td>\${r.playerName}</td>
            <td>\${r.gameTitle}</td>
            <td>\${r.score}</td>
            <td>\${r.createdAt}</td>
            <td><button onclick="deleteRanking(${r.id})">삭제</button></td>
        `;
        tbody.appendChild(tr);
    });
}

async function addRanking() {
    const playerId = document.getElementById("playerId").value;
    const gameId = document.getElementById("gameId").value;
    const score = document.getElementById("score").value;

    const response = await fetch("/mini_ajax/ranking?job=insert", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({ playerId, gameId, score })
    });

    if (response.ok) {
        alert("등록 완료!");
        loadRankings();
    } else {
        alert("등록 실패!");
    }
}

async function deleteRanking(id) {
    if (!confirm("삭제하시겠습니까?")) return;
    const response = await fetch(`/mini_ajax/ranking?job=delete&id=${id}`, { method: "DELETE" });
    if (response.ok) {
        alert("삭제 완료!");
        loadRankings();
    }
}
</script>
</html>
