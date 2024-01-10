
<script>
    import '../app.css';
    import Navbar from './navbar.svelte';
    import toast, { Toaster } from 'svelte-french-toast';

</script>
<Navbar/>
<Toaster/>


<slot></slot>
