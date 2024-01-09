<script>
    import {onMount} from "svelte";
    import {page} from "$app/stores";

    let article = [];
    onMount(async ()=>{
        await fetch(`http://localhost:8080/api/v1/post/`+$page.params.id)
            .then((res)=> res.json())
            .then((res)=>{
                article = res;
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

<div class="card w-auto bg-base-100 shadow-xl m-1">
    <div class="card-body">
        <h2 class="card-title">{article.commentList}</h2>
        <p>{article.body}</p>
    </div>
</div>
