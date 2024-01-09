<script>
    import '../app.css';
    import {goto} from "$app/navigation";
</script>

<div class="navbar bg-base-100" >
    <div class="navbar-start w-min-1">
        <a class="btn btn-ghost text-xl" href="/">Medium Blog.</a>
    </div>
    <div class="navbar-center lg:flex">
        <ul class="menu menu-horizontal px-1">
            <li><a on:click={(event)=> {goto('/articles')}}>Article</a></li>
            <li>
                <details>
                    <summary>Mypage</summary>
                    <ul class="p-2">
                        <li><a>Submenu 1</a></li>
                        <li><a>Submenu 2</a></li>
                    </ul>
                </details>
            </li>
            <li><a>Item 3</a></li>
        </ul>
    </div>
    <div class="navbar-end">
        <a class="btn m-1 min-w-20">Login</a>
        <a class="btn m-1 min-w-20">Sign in</a>
    </div>
</div>