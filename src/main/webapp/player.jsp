
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>플레이어 관리</title>
</head>
<body>
	<h2>플레이어 관리</h2>

	<form id="form">
		이름: <input type="text" id="playerName"> 이메일: <input
			type="text" id="email">
		<button type="button" onclick="addPlayer()">등록</button>
	</form>

	<hr>

	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>이메일</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody id="tbody"></tbody>
	</table>

	<script>
window.onload = listPlayer;

async function listPlayer() {
    const response = await fetch('/mini_ajax/player');
    const data = await response.json();
    console.log("받아온 데이터:", data);

    let html = "";
    data.forEach(p => {
        html += `
            <tr>
                <td>\${p.playerId}</td>
                <td>\${p.playerName}</td>
                <td>\${p.email}</td>
                <td><button onclick="deletePlayer(${p.playerId})">삭제</button></td>
            </tr>
        `;
    });
    document.getElementById("tbody").innerHTML = html;
}

// ✅ URLSearchParams로 폼데이터 전송
async function addPlayer() {
    const urlParams = new URLSearchParams({
        playerName: document.getElementById("playerName").value,
        email: document.getElementById("email").value
    });

    await fetch('/mini_ajax/player', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: urlParams
    });

    listPlayer();
}

async function deletePlayer(id) {
    await fetch(`/mini_ajax/player?id=${id}`, { method: 'DELETE' });
    listPlayer();
}
</script>
</body>
</html>

