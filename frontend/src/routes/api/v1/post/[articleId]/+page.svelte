<script>
    import {page} from '$app/stores';
    // import {load} from "./+page.js";
    let id = $page.params['articleId'];

    async function item() {
        return await fetch(`http://localhost:8080/api/v1/post/${id}`).then((res) => res.json());
    }
</script>

<article>
<!--	<h1>{data.post.title}</h1>-->
<!--	<p>{data.post.body}</p>-->

{#await item()}
	<p>...Loading</p>
{:then item}
	<p>{item.title}</p>
	<p>{item.writer}</p>
	<p>{item.hit}</p>
	<p>{item.dateTime}</p>
    <p>{item.body}</p>
{/await}
</article>
