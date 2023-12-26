<script>
	$: items = fetch(`http://localhost:8080/api/v1/post/list`).then((response) => response.json());
</script>

<h1 class="text-3xl font-bold underline">api/v1/post/list</h1>

<table class="table-auto border-separate border-spacing-2 border border-slate-400">
	<thead>
		<tr>
			<th class="text-center">글번호</th>
			<th>작성자</th>
			<th>제목</th>
			<th>조회수</th>
			<th>작성일</th>
		</tr>
	</thead>
	{#await items then items}
		{#each items as item}
			<tbody>
				<tr>
					<td>{item.id}</td>
					<td>{item.writer.username}</td>
					<td>{item.title}</td>
					<td>{item.hit}</td>
					<td>{item.dateTime}</td>
				</tr>
			</tbody>
		{/each}
	{/await}
</table>

<style lang="postcss">
	:global(html) {
		background-color: theme(colors.gray.100);
	}
</style>
