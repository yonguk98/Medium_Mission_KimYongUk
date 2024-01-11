<script>
    import {goto} from "$app/navigation";
    import toast, {Toaster} from 'svelte-french-toast';

    $: loginData = {
        username: '',
        password: ''
    };

    async function postLogin() {
        if (checkBlank()) {
            await fetch(`http://localhost:8080/api/v1/member/login`, {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                credentials: 'include',
                body: JSON.stringify(loginData),
            })
                .then((res) => {
                    if(res.status === 200){
                        console.log(res);
                        toast.success("login success!");
                        goto('/');
                    }
                })
        } else {
            toast.error(errorMsg);
        }
    }

    let errorMsg = '';

    function checkBlank() {
        if (loginData.username == '') {
            errorMsg = '아이디를 입력해주세요.';
            return false;
        }
        if (loginData.password == '') {
            errorMsg = '비밀번호를 입력해주세요';
            return false;
        }
        return true
    }
</script>
<div class="flex justify-center">
    <div class="card w-96 bg-base-100 shadow-xl">
        <form>
            <div class="card-body">
                <label class="form-control w-full max-w-xs self-center">
                    <div class="label">
                        <span class="label-text">ID</span>
                    </div>
                    <input type="text" placeholder="ID"
                           class="input input-bordered input-primary w-full max-w-xs" bind:value={loginData.username}/>
                </label>
            </div>
            <div class="card-body">
                <label class="form-control w-full max-w-xs self-center">
                    <div class="label">
                        <span class="label-text">Password</span>
                    </div>
                    <input type="password" placeholder="password"
                           class="input input-bordered input-primary w-full max-w-xs" bind:value={loginData.password}/>
                </label>
            </div>
            <div class="card-body">
                <div class="card-actions justify-end">
                    <button class="btn btn-primary" on:click={(event)=>postLogin()}>Login</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="flex justify-center">
    <div>
        <div>Don't have an account? &nbsp<a class="link link-accent" on:click={e=>goto("/members/signup")}>sign up
            here</a></div>
    </div>
</div>

<style>
    .card-body {
        padding: 5%;
    }

    .link {
        text-decoration: underline;
    }

</style>