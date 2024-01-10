<script>
    import {goto} from "$app/navigation";
    import toast, { Toaster } from 'svelte-french-toast';

    $: signupData = {
        username: '',
        password: '',
        passwordConfirm: ''
    };

    async function postMember() {
        if(checkSignupData()){
            await fetch(`http://localhost:8080/api/v1/member/signup`, {
                headers: {
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                credentials: 'include',
                body: JSON.stringify(signupData)
            })
                .then((res)=>res.json())
                .then((res)=>toast.success(res.msg));
        } else{
            toast.error("signup fail");
        }
    }
    let errorMsg = '';
    function passwordConfirm() {
        if (signupData.password == '' && signupData.passwordConfirm == '') {
            errorMsg = '비밀번호를 입력해주세요.'
            return false;
        }
        if (signupData.password != signupData.passwordConfirm) {
            errorMsg = '비밀번호가 일치하지 않습니다.'
            return false;
        }
        return true;
    }

    function checkSignupData(){
        if(signupData.username == ''){
            errorMsg = '아이디를 입력해주세요'
            return false;
        }
        if(!passwordConfirm()){
            return false;
        }
        return true;
    }

</script>
<div class="flex justify-center ">
    <div class="card w-96 bg-base-100 shadow-xl">
        <form>
            <div class="card-body">
                <label class="form-control w-full max-w-xs self-center">
                    <div class="label">
                        <span class="label-text">What is your ID?</span>
                    </div>
                    <input type="text" placeholder="ID" class="input input-bordered input-secondary w-full max-w-xs"
                           bind:value={signupData.username}/>
                </label>
            </div>
            <div class="card-body">
                <label class="form-control w-full max-w-xs self-center">
                    <div class="label">
                        <span class="label-text">What is your password?</span>
                    </div>
                    <input type="password" placeholder="password" class="input input-bordered input-secondary w-full max-w-xs"
                           bind:value={signupData.password}/>
                </label>
            </div>
            <div class="card-body">
                <label class="form-control w-full max-w-xs self-center">
                    <div class="label">
                        <span class="label-text">Check the password</span>
                        {#if passwordConfirm()}
                            <span class="label-text-alt">✅</span>
                        {/if}
                    </div>
                    <input type="password" placeholder="password check" class="input input-bordered input-secondary w-full max-w-xs "
                           bind:value={signupData.passwordConfirm}/>
                </label>
            </div>
            <div class="card-body">
                <div class="card-actions justify-end">
                    <button class="btn btn-secondary" on:click={(event)=> postMember()}>Sign up</button>
                </div>
            </div>
        </form>
    </div>
</div>
<div class="flex justify-center">
    <div>
        <div>Already have an account? &nbsp<a class="link link-accent" on:click={e=>goto("/members/signup")}>Login
            here</a></div>
    </div>
</div>

<style>
    .card-body {
        padding: 5%;
    }
</style>