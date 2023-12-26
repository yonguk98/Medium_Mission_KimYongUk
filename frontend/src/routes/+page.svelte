<script>
	import { json } from '@sveltejs/kit';
	import axios from 'axios';
	import { onMount } from 'svelte';

	$: items = fetch(`http://localhost:8080/api/v1/post/list`).then((response) => response.json());
</script>

<h1>Welcome to SvelteKit</h1>
<p>Visit <a href="https://kit.svelte.dev">kit.svelte.dev</a> to read the documentation</p>
{#await items}
	<p>...Loading</p>
{:then items}
	{#each items as item}
		<p>{item.id} {item.writer.username} {item.title} {item.body}</p>
	{/each}
{:catch error}
	<p>오류가 발생했습니다.</p>
{/await}
