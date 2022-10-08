<template>
<div v-if="isLogged() === true">
<Navbar></Navbar>
<router-view/>
</div>
<div v-else>
<Login></Login>
</div>
</template>

<script>
  import Navbar from './components/partials/Navbar.vue'
  import Login from './views/Login.vue'
  export default {
    components:{
      'Navbar': Navbar,
        'Login': Login,
    },
      methods:{
        isLogged(){
            var user;
            let cookieToken = null;
            const value = document.cookie;
            console.log(document.cookie);
            const parts = value.split("Token");
            if (parts.length === 2){
                cookieToken = parts.pop().split(';').shift();
            }
            if(cookieToken) {
                var base64Url = cookieToken.split('.')[1];
                var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');

                var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
                    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                }).join(''));
                user = JSON.parse(jsonPayload);
                console.log(user);
            }
            if(user){
                return true;
            }
            else{
                return false;
            }
        }
      }
  }
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
