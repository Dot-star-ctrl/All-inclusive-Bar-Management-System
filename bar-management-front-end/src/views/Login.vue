<template>
    <div class="container mx-auto flex-1 flex flex-col items-center px-2 mt-10">
        <img height="80"  width="80" src='../../public/images/ABM-5.png'/>
        <h2 class="text-2xl text-gray-400 mt-5">All-Inclusive Bar Management</h2>
        <div class="w-full max-w-xs">
            <form class="bg-teal shadow-md rounded px-8 pt-6 pb-8 mt-20 border-t-4 border-teal-500 rounded-md shadow-lg" v-on:submit.prevent="submit">
                <h2 class="text-3xl text-gray-400 mb-3">Login</h2>
                <div class="mb-10">
                    <input class="shadow appearance-none border w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="username" type="text"
                           name="username" v-model="username" placeholder="Username" :class="{ 'is-invalid': submitted && !username }">
                    <div v-show="submitted && !username" class="invalid-feedback text-red-500 text-xs italic">Username is required</div>
                </div>
                <div class="mb-6">
                    <input class="shadow appearance-none border w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline" id="password" type="password"
                           name="password" v-model="password" placeholder="Password" :class="{ 'is-invalid': submitted && !password }">
                    <div v-show="submitted && !password" class="invalid-feedback text-red-500 text-xs italic">Password is required</div>
                    <ul class="list-group alert alert-danger text-red-500" v-if="errors.length > 0">
                        <li class="list-group-item" v-for="error in errors" :key="errors.indexOf(error)">
                            {{ error }}
                        </li>
                    </ul>
                </div>
                <div class="items-center">
                    <button class="bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 focus:outline-none focus:shadow-outline" type="submit" name="signIn">
                        Sign In
                    </button>
                </div>
            </form>
            <p class="text-center text-gray-500 text-xs">
                &copy;2020 ABM Corp. All rights reserved.
            </p>
        </div>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        name: 'TheLogin',
        data(){
            return {
                username: null,
                password: null,

                submitted: false,
                employees: [],
                errors: [],
                employeeRole: null,
                verifyProblem:false,
                wentWrong:false
            }
        },
        mounted (){
            this.errors = [];
        },
        methods: {
            submit() {
                this.submitted = true;
                if (this.username && this.password) {
                var params = new URLSearchParams();
                params.append('username', this.username);
                params.append('password', this.password);

                axios.post('http://localhost:9090/system/authentication',params)
                    .then(function(response) {
                        let d = new Date();
                        d.setTime(d.getTime() + 1 * 24 * 60 * 60 * 1000);
                        let expires = "expires=" + d.toUTCString();
                        document.cookie = "Token=" + response.data + ";" + expires + ";path=/";
                        console.log(document.cookie);
                        window.location.href = 'http://127.0.0.1:8080';
                    }).catch(error => {
                        console.log(error);
                    if (error.response.status == 403) {
                        if(!this.verifyProblem) {
                            this.errors.push("We couldn't verify your account details.");
                            this.verifyProblem = true;
                        }
                    } else {
                        if(!this.wentWrong) {
                            this.errors.push("Something went wrong, please refresh and try again.");
                            this.wentWrong = true;
                        }
                    }
                });
                }
            },
        },
    }
</script>
