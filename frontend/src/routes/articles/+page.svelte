<script>
    import {onMount} from "svelte";
    import {goto} from "$app/navigation";
    import Time, {dayjs} from "svelte-time";

    let articles = [];
    onMount(async ()=>{
        await fetch(`http://localhost:8080/api/v1/post/list`)
            .then((res)=>res.json())
            .then((res)=>{
                articles = res;
            })
    })


</script>

<div class="overflow-x-auto">
    <table class="table">
        <!-- head -->
        <thead>
        <tr class="text-center">
            <th>author</th>
            <th>title</th>
            <th>like</th>
            <th>view</th>
            <th>date</th>
        </tr>
        </thead>
        <tbody>
        <!-- row 1 -->
        {#each articles as article}
        <tr class="text-center">
            <td>
                <div>{article.writer}</div>
            </td>
            <td>
                <a class="font-bold" on:click={(event)=> goto('/articles/' + article.id)}>{article.title}</a>
            </td>
            <td>Purple</td>
            <th>
                {article.hit}
            </th>
            <th>
                <Time timestamp="{article.dateTime}" format="MMM DD YYYY, hh:mm"></Time>
            </th>
        </tr>
            {/each}
        <!-- foot -->
        <tfoot>
        <tr>
            <th></th>
            <th>Name</th>
            <th>Job</th>
            <th>Favorite Color</th>
            <th></th>
        </tr>
        </tfoot>
    </table>
</div>