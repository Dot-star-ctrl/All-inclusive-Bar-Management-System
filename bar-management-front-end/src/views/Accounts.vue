<template>
    <div class="container mx-auto flex-1 flex flex-col items-center px-2 mt-10">
        <form v-on:submit.prevent="submit1">
            <div class="flex flex-wrap -mx-3 mb-6">
                <div class="w-full md:w-1/4 px-3 mb-6 md:mb-0">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="newUsername">
                        Username
                    </label>
                    <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                           id="newUsername" type="text" name="newUsername" v-model="newUsername" :invalid="$v.newUsername.$invalid">
                    <span v-if="$v.newUsername.$invalid" class="invalid-feedback text-red-500 text-xs italic">Username is required</span>
                </div>
                <div class="w-full md:w-1/4 px-3">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2"
                           for="newSelectAccountType">
                        Account Type
                    </label>
                    <select class="block w-full bg-gray-200 text-gray-700 border border-gray-200 py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                            id="newSelectAccountType"
                            name="newSelectAccountType" v-model="newSelectAccountType">
                        <option v-for="type in accountTypes" v-bind:key="type">{{type}}</option>
                    </select>
                    <span v-if="$v.newSelectAccountType.$invalid" class="invalid-feedback text-red-500 text-xs italic">Account Type is required</span>
                </div>
                <div class="w-full md:w-1/4 px-3">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="newPassword">
                        Password
                    </label>
                    <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 py-3 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                           id="newPassword" type="text" name="newPassword" v-model="newPassword">
                    <span v-if="$v.newPassword.$invalid" class="invalid-feedback text-red-500 text-xs italic">Password is required</span>
                </div>
                <div class="w-full md:w-1/4 px-3 mt-6">
                    <button
                            class="bg-teal-500 hover:bg-blue-700 text-white font-bold py-3 px-4 mb-3" :disabled="$v.$invalid" @click="submitValidate">Create New
                        Employee
                    </button>
                </div>
            </div>
        </form>
        <div class="modal opacity-0 pointer-events-none fixed w-full h-full top-0 left-0 flex items-center justify-center"
             id="myModal">
            <div class="modal-overlay absolute w-full h-full bg-gray-900 opacity-50"></div>

            <div class="modal-container bg-white w-11/12 md:max-w-md mx-auto shadow-lg z-50 overflow-y-auto">

                <div class="modal-close absolute top-0 right-0 cursor-pointer flex flex-col items-center mt-4 text-white text-sm z-50">
                    <svg class="fill-current text-white" xmlns="http://www.w3.org/2000/svg" width="18" height="18"
                         viewBox="0 0 18 18">
                        <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"></path>
                    </svg>
                    <span class="text-sm">(Esc)</span>
                </div>

                <div class="modal-content py-4 text-left px-6">
                    <!--Title-->
                    <div class="flex justify-between items-center pb-3">
                        <p class="text-2xl font-bold text-gray-500">Edit Account</p>
                        <div class="modal-close cursor-pointer z-50">
                            <svg class="fill-current text-black" xmlns="http://www.w3.org/2000/svg" width="18"
                                 height="18" viewBox="0 0 18 18">
                                <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"></path>
                            </svg>
                        </div>
                    </div>

                    <!--Body-->
                    <form v-on:submit.prevent="submit">
                        <div class="mb-4 ml-20">
                            <label class="block text-gray-500 text-sm font-bold mb-2" for="username">
                                Username
                            </label>
                            <input class="shadow appearance-none border rounded w-3/4 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                   id="username" type="text" name="username" v-model="username" :class="{ 'is-invalid': submitted && !username }">
                            <div v-show="submitted && !username" class="invalid-feedback text-red-500 text-xs italic">Username is required</div>
                        </div>
                        <div class="mb-4 ml-20">
                            <label class="block text-gray-500 text-sm font-bold mb-2" for="selectAccountType">
                                Account Type
                            </label>
                            <select class="block border border-grey-light w-3/4 p-3 rounded mt-4" id="selectAccountType"
                                    name="selectAccountType" v-model.trim="selectAccountType" :class="{ 'is-invalid': submitted && !selectAccountType }">
                                <option v-for="type in accountTypes" v-bind:key="type">{{type}}</option>
                            </select>
                            <div v-show="submitted && !selectAccountType" class="invalid-feedback text-red-500 text-xs italic">Account Type is required</div>
                        </div>

                        <div class="mb-4 ml-20">
                            <label class="block text-gray-500 text-sm font-bold mb-2" for="password">
                                Password
                            </label>
                            <input class="shadow appearance-none border rounded w-3/4 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                   id="password" type="text" name="password" v-model.trim="password" :class="{ 'is-invalid': submitted && !password }">
                            <div v-show="submitted && !password" class="invalid-feedback text-red-500 text-xs italic">Password is required</div>
                        </div>

                        <!--Footer-->
                        <div class="absolute flex justify-end">
                            <button
                                    class="bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2 p-3 mt-2">
                                Edit
                            </button>
                        </div>
                    </form>
                    <div class="flex justify-end pt-2">
                        <button class="modal-close px-4 bg-teal-500 py-2 p-3 text-white hover:bg-blue-700">
                            Close
                        </button>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="container mx-auto px-4 sm:px-8">
            <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto">
                <div
                        class="inline-block min-w-full shadow-md overflow-hidden">
                    <table class="min-w-full leading-normal">
                        <thead>
                        <tr>
                            <th
                                    class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-700 uppercase tracking-wider"
                            >
                                No.
                            </th>
                            <th
                                    class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-700 uppercase tracking-wider"
                            >
                                Username
                            </th>
                            <th
                                    class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-700 uppercase tracking-wider"
                            >
                                Account Type
                            </th>
                            <th
                                    class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-700 uppercase tracking-wider"
                            >
                                Actions
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="border px-4 py-2" v-for="employee in employees" v-bind:key="employee.employeeId">
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <div class="flex">
                                    <div class="ml-3">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            {{employee.employeeId}}
                                        </p>
                                    </div>
                                </div>
                            </td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <p class="text-gray-900 whitespace-no-wrap">{{employee.username}}</p>
                            </td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <p class="text-gray-900 whitespace-no-wrap">{{employee.accountType}}</p>
                            </td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <button @click="toggleModal();getEmployeeId(employee.employeeId);getEmployeeById(employee.employeeId)"
                                        class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                        id="editBtn">Edit
                                </button>
                                <button @click="deleteCustomer(employee.employeeId)"
                                        class="bg-teal-500 hover:bg-red-700 text-white font-bold py-2 px-4">Delete
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
    </div>
</template>

<script>
    import { ref } from 'vue'
    import useVuelidate from "@vuelidate/core"
    import { required } from "@vuelidate/validators"
    import axios from 'axios'

    export default {
        data() {
            return {
                employees: [],
                accountTypes: [
                    'BAR_MANAGER',
                    'HEAD_BARTENDER',
                    'WAREHOUSE_KEEPER',
                    'BAR_SUPPLIER'
                ],
                myModal: false,
                dynamicButton: 'Insert',
                dynamicTitle: 'Add Data',
                i: null,

                selectedEmployeeId: null,
                username: null,
                selectAccountType: null,
                password: null,

                submitted: false,
            }
        },
        setup(){

            const newUsername = ref('');
            const newPassword = ref('');
            const newSelectAccountType = ref('');

            // Validation Logics
            const rules = {
                newUsername: {required},
                newPassword: {required},
                newSelectAccountType: {required},
            };

            const $v = useVuelidate(rules, {newUsername, newPassword, newSelectAccountType});

                return {
                    newUsername,
                    newPassword,
                    newSelectAccountType,
                    $v,
                    submitValidate,
                };

            function submitValidate() {
                this.$v.$reset();
                $v.value.$touch()
                if(!$v.value.$invalid){
                    this.$v.$reset();
                }
            }
        },
        mounted() {
            this.gerCustomers();


            //modal
            var openmodal = document.querySelectorAll('.modal-open')
            for (this.i = 0; this.i < openmodal.length; this.i++) {
                openmodal[this.i].addEventListener('click', function (event) {
                    event.preventDefault()
                    this.toggleModal()
                })
            }

            const overlay = document.querySelector('.modal-overlay')
            overlay.addEventListener('click', this.toggleModal)

            var closemodal = document.querySelectorAll('.modal-close')
            for (this.i = 0; this.i < closemodal.length; this.i++) {
                closemodal[this.i].addEventListener('click', this.toggleModal)
            }

            document.onkeydown = function (evt) {
                evt = evt || window.event
                var isEscape = false
                if ("key" in evt) {
                    isEscape = (evt.key === "Escape" || evt.key === "Esc")
                } else {
                    isEscape = (evt.keyCode === 27)
                }
                if (isEscape && document.body.classList.contains('modal-active')) {
                    this.toggleModal()
                }
            };
        },

        methods: {
            Clicked() {
              this.isClicked = true;
            },
            getCookie(name) {
                const value = document.cookie;
                console.log(document.cookie);
                const parts = value.split(name);
                if (parts.length === 2) return parts.pop().split(';').shift();
            },

            gerCustomers: function () {
                 var token = this.getCookie("Token");
                axios.get('http://localhost:9090/system/employees',{headers: {'Authorization': 'Bearer' + token}
                }).then(response => {
                    this.employees = response.data;
                    console.log(response);
                })
                    .catch(function (response) {
                        console.log(response);
                        alert("Could not load employees");
                    });
            },

            toggleModal() {
                const body = document.querySelector('body')
                const modal = document.querySelector('.modal')
                modal.classList.toggle('opacity-0')
                modal.classList.toggle('pointer-events-none')
                body.classList.toggle('modal-active')
            },

            deleteCustomer(employee) {
                var token = this.getCookie("Token");
                console.log("delete customer with id " + employee);
                // if (confirm('Are you sure you want to delete this?')) {
                    axios.delete('http://localhost:9090/system/employees/' + employee, {headers: {'Authorization': 'Bearer' + token}}).then(() => {
                        this.gerCustomers();
                    })
                // }
            },

            getEmployeeId(id) {
                this.selectedEmployeeId = id;
            },

            getEmployeeById(id){
                var token = this.getCookie("Token");
                axios.get('http://localhost:9090/system/employees/' + id, {headers: {'Authorization': 'Bearer' + token}}).then(response => {
                    this.username = response.data.username;
                    this.selectAccountType = response.data.accountType;
                    this.password = response.data.password;
                    console.log(this.username);

                })
                    .catch(function (response) {
                        console.log(response);
                        alert("Could not load employees");
                    });
            },

            submit1() {
                this.gerCustomers();
                var token = this.getCookie("Token");
                axios.post('http://localhost:9090/system/employees/', {
                    username: this.newUsername,
                    accountType: this.newSelectAccountType,
                    password: this.newPassword,
                }, {headers: {'Authorization': 'Bearer' + token}})
                    .then(response => {
                        console.log(response);
                        this.gerCustomers();
                        this.newUsername = null;
                        this.newSelectAccountType = null;
                        this.newPassword = null;
                    })
                    .catch(error => {
                        if (error.response.status == 422) {
                            this.errors = error.response.data.errors;
                        }
                        console.log('Error');
                    });
            },

            submit() {
                this.submitted = true;
                if (this.username && this.password && this.selectAccountType) {
                    var token = this.getCookie("Token");
                    console.log(this.selectAccountType);
                    console.log(this.selectedEmployeeId);
                    axios.put('http://localhost:9090/system/employees/' + this.selectedEmployeeId, {
                        employeeId: this.selectedEmployeeId,
                        username: this.username,
                        accountType: this.selectAccountType,
                        password: this.password,
                    }, {headers: {'Authorization': 'Bearer' + token}})
                        .then(response => {
                            console.log(response);
                            this.gerCustomers();
                            this.selectedEmployeeId = null;
                            this.username = null;
                            this.selectAccountType = null;
                            this.password = null;
                            // alert('The employee account has been updated.');
                        })
                        .catch(error => {
                            if (error.response.status == 422) {
                                this.errors = error.response.data.errors;
                            }
                            console.log('Error');
                        });
                }
            }
        }
    }
</script>