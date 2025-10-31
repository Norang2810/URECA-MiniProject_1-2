<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>장르 관리</title>
</head>
<body>
	<h2>장르 관리</h2>

	<form id="form">
		장르 이름: <input type="text" id="genreName">
		<button type="button" onclick="addGenre()">등록</button>
	</form>

	<hr>

	<table border="1">
		<thead>
			<tr>
				<th>ID</th>
				<th>이름</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody id="tbody"></tbody>
	</table>

	<script>
async function loadGenres() {
  const res = await fetch('/mini_ajax/genre');
  const data = await res.json();
  const tbody = document.getElementById('tbody');
  tbody.innerHTML = '';
  data.forEach(g => {
    tbody.innerHTML += `<tr>
      <td>${g.genreId}</td>
      <td>${g.genreName}</td>
      <td><button onclick="del(${g.genreId})">삭제</button></td>
    </tr>`;
  });
}

async function addGenre() {
  const payload = { genreName: document.getElementById('genreName').value };
  await fetch('/mini_ajax/genre', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(payload)
  });
  loadGenres();
}

async function del(id) {
  await fetch(`/mini_ajax/genre?id=${id}`, { method: 'DELETE' });
  loadGenres();
}

loadGenres();
</script>
</body>
</html>
