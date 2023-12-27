<script>
	import { page } from '$app/stores';
	let articleId = $page.params['articleId'];

	$: item = fetch(`http://localhost:8080/api/v1/post/` + articleId).then((response) =>
		response.json()
	);
</script>

<h1>api/v1/post/{articleId}</h1>
<table>
	<thead>
		<tr>
			<th>제목</th>
			<th>작성자</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			{#await item}
				<td>...Loading</td>
			{:then item}
				<td>{item.title}</td>
				<td>{item.writer}</td>
				<td>{item.hit}</td>
				<td>{item.dateTime}</td>
			{/await}
		</tr>
	</tbody>
</table>
