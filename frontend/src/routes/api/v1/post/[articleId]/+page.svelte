<script>
	// import { page } from '$app/stores';
	// let articleId = $page.params['articleId'];
	export let data;
</script>

<article>
	<h1>{data.post.title}</h1>
	<p>{data.post.body}</p>
</article>
<!-- {#await item}
	<p>...Loading</p>
{:then item}
	<p>{item.title}</p>
	<p>{item.writer}</p>
	<p>{item.hit}</p>
	<p>{item.dateTime}</p>
{/await} -->
