<template>
    <div class="bg-teal-500" id="navbar">
        <div class="container mx-auto px-4">
            <div class="flex items-center md:justify-between py-4">
                <div class="w-1/4 md:hidden">
                    <svg class="fill-current text-white h-8 w-8" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20"><path d="M16.4 9H3.6c-.552 0-.6.447-.6 1 0 .553.048 1 .6 1h12.8c.552 0 .6-.447.6-1 0-.553-.048-1-.6-1zm0 4H3.6c-.552 0-.6.447-.6 1 0 .553.048 1 .6 1h12.8c.552 0 .6-.447.6-1 0-.553-.048-1-.6-1zM3.6 7h12.8c.552 0 .6-.447.6-1 0-.553-.048-1-.6-1H3.6c-.552 0-.6.447-.6 1 0 .553.048 1 .6 1z"/></svg>
                </div>
                <div class="w-1/2 md:w-auto text-center text-white text-2xl font-medium">
                    <img height="50"  width="50" src='../../../public/images/ABM-4.png'/>
                </div>
                <div class="w-1/4 md:w-auto md:flex text-right">
                    <div>
                        <img class="inline-block h-8 w-8 rounded-full mt-1" src="../../../public/images/default-user-image.png" alt="user icon" @click="$router.push('Personal-info')">
                    </div>
                    <div class="hidden md:block md:flex md:items-center ml-2">
                        <div class="text-white text-sm mr-1" :value="this.username">{{this.username}}</div>
                    </div>
                    <button class="relative p-2 ml-10 text-gray-400 hover:bg-gray-100 hover:text-gray-600 focus:bg-gray-100 focus:text-gray-600 rounded-full" @click="logout();$router.push('login')">
                        <span class="sr-only">Log out</span>
                        <svg aria-hidden="true" fill="none" viewBox="0 0 24 24" stroke="currentColor" class="h-6 w-6">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 16l4-4m0 0l-4-4m4 4H7m6 4v1a3 3 0 01-3 3H6a3 3 0 01-3-3V7a3 3 0 013-3h4a3 3 0 013 3v1" />
                        </svg>
                    </button>
                </div>
            </div>
        </div>
    </div>
    <nav class="bg-white hidden bg-blue-dark md:block md:bg-white md:border-b">
        <div class="max-w-7xl mx-auto px-2 sm:px-6 lg:px-8">
            <div class="relative flex items-center justify-between h-16">
                <div class="absolute inset-y-0 left-0 flex items-center sm:hidden">
                    <!-- Mobile menu button-->
                    <button class="inline-flex items-center justify-center p-2 rounded-md text-gray-400 hover:text-white hover:bg-gray-700 focus:outline-none focus:bg-gray-700 focus:text-white transition duration-150 ease-in-out" aria-label="Main menu" aria-expanded="false">
                        <!-- Icon when menu is closed. -->
                        <!--
                          Heroicon name: menu

                          Menu open: "hidden", Menu closed: "block"
                        -->
                        <svg class="block h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
                        </svg>
                        <!-- Icon when menu is open. -->
                        <!--
                          Heroicon name: x

                          Menu open: "block", Menu closed: "hidden"
                        -->
                        <svg class="hidden h-6 w-6" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                        </svg>
                    </button>
                </div>
                <div class="flex-1 flex items-center justify-center sm:items-stretch sm:justify-start">
                    <div class="flex-shrink-0">
                    </div>
                    <div class="hidden sm:block sm:ml-6">
                        <div class="flex">
                            <div v-if="this.employeeRole === 'BAR_MANAGER'">
                            <router-link to="/"  class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"> <i class="fas fa-chart-pie mr-1"></i><span class="pb-1 md:pb-0 text-sm">Dashboard</span></router-link>
                            <router-link to="/accounts" class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"><i class="fas fa-user mr-1"></i><span class="pb-1 md:pb-0 text-sm">Accounts</span></router-link>
                            <router-link to="/bars" class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"><i class="fas fa-glass-martini mr-1"></i><span class="pb-1 md:pb-0 text-sm">Bars</span></router-link>
                            <router-link to="/stockrequests" class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"><i class="fas fa-sticky-note mr-1"></i><span class="pb-1 md:pb-0 text-sm">Stock Requests</span></router-link>
                            <router-link to="/warehouse" class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"><i class="fas fa-cubes mr-1"></i><span class="pb-1 md:pb-0 text-sm">Warehouse</span></router-link>
                                <router-link to="/websocket" class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"><i class="far fa-comment mr-1"></i><span class="pb-1 md:pb-0 text-sm">Chat</span></router-link>
                            </div>
                            <div v-else-if="this.employeeRole === 'WAREHOUSE_KEEPER'">
                            <router-link to="/warehouse-account-stock" class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"><i class="fas fa-file-invoice mr-1"></i><span class="pb-1 md:pb-0 text-sm">Account Stock</span></router-link>
                                <router-link to="/websocket" class="ml-4 px-3 py-2 rounded-md text-m font-medium leading-5 text-gray-500 hover:text-white hover:bg-teal-700 focus:outline-none focus:text-white focus:bg-teal-700 transition duration-150 ease-in-out"><i class="far fa-comment mr-1"></i><span class="pb-1 md:pb-0 text-sm">Chat</span></router-link>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="absolute inset-y-0 right-0 flex items-center pr-2 sm:static sm:inset-auto sm:ml-6 sm:pr-0">
                    <div class="ml-3 relative">
                        <div>
                            <button class="flex text-sm border-2 border-transparent rounded-full focus:outline-none focus:border-white transition duration-150 ease-in-out" id="user-menu" aria-label="User menu" aria-haspopup="true">
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="hidden sm:hidden">
            <div class="px-2 pt-2 pb-3">
            </div>
        </div>
    </nav>
</template>

<script>
    import axios from 'axios'

    export default {
        name: 'Navbar',
        props: {
            msg: String
        },
        data(){
          return {
              username: null,
              employees: [],
              employeeRole: null,
            }
        },
        mounted() {
            this.gerCustomers();
        },
        methods:{
            logout() {
                // remove cookie
                location.reload();
                document.cookie = "Token= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
            },
            getCookie(name) {
                const value = document.cookie;
                const parts = value.split(name);
                if (parts.length === 2) return parts.pop().split(';').shift();
            },
            gerCustomers: function () {
                var token = this.getCookie("Token");
                axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
                }).then(response => {
                    this.employees = response.data;
                    // console.log(this.employees[1].username);
                    this.getEmployeeRole();
                })
                    .catch(function (response) {
                        console.log(response);
                        alert("Could not load employees");
                    });
            },

            getEmployeeRole(){
                var base64Url = this.getCookie('Token').split('.')[1];
                var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
                var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
                    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
                }).join(''));
                var user = JSON.parse(jsonPayload);
                this.username = user.sub;
                console.log(this.username);

                if(this.employees) {
                    for (var i = 0; i < this.employees.length; i++) {
                        if (this.employees[i].username === user.sub) {
                            this.employeeRole = this.employees[i].accountType;
                            console.log(this.employeeRole);
                        }
                    }
                }
            }
        },
        created(){
            var base64Url = this.getCookie('Token').split('.')[1];
            var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
            var jsonPayload = decodeURIComponent(atob(base64).split('').map(function (c) {
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            }).join(''));
            var user = JSON.parse(jsonPayload);
            this.username = user.sub;
            console.log(this.username);
        }
    }
</script>