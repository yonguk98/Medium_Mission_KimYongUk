<script>
    import {onMount} from "svelte";
    import {page} from "$app/stores";
    import Time from "svelte-time";

    let article = [];
    let comments = [];
    onMount(async ()=>{
        await fetch(`http://localhost:8080/api/v1/post/`+$page.params.id)
            .then((res)=> res.json())
            .then((res)=>{
                article = res;
                comments = article.commentList;
                console.log(article);
            })
    })
</script>

<div class="card w-auto bg-base-100 shadow-xl m-1">
    <div class="card-body">
        <h2 class="card-title">{article.title}</h2>
        <p>{article.body}</p>
    </div>
</div>

{#each comments as comment}
<div class="card w-auto bg-base-100 shadow-xl m-1">
    <div class="card-body">
        <div class="columns-2">
            <p>{comment.body}</p>
            <div class="text-end">
                <span>
                    <button class="btn btn-square btn-info">수정</button>
                    <button class="btn btn-square btn-error">삭제</button>
                </span>
            </div>
        </div>
        <div class="text-end">
            <Time timestamp="{comment.dateTime}" format="MMM DD YYYY, hh:mm"></Time>
        </div>

    </div>
</div>
{/each}