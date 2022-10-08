<template>
    <div class="container mx-auto flex-1 flex flex-col items-center px-2 mt-5">
        <div class="container w-full  float:left mt-10">
            <button @click="toggleModalCreate('modal-id')" class="bg-teal-500 hover:bg-blue-700 float-left text-white py-2 px-4 mb-5 focus:outline-none focus:shadow-outline" type="button">
                + Add Bar
            </button>
            <div class="hidden overflow-x-hidden overflow-y-auto fixed inset-0 z-50 outline-none focus:outline-none justify-center items-center" id="modal-id">
                <div class="relative w-auto my-6 mx-auto max-w-3xl">
                    <!--content-->
                    <div class="border-0 shadow-lg relative flex flex-col w-full bg-white outline-none focus:outline-none">
                        <!--header-->
                        <div class="flex items-start justify-between p-5 border-b border-solid border-gray-300 rounded-t">
                            <h3 class="text-3xl font-semibold">
                                Create Bar
                            </h3>
                            <button class="p-1 ml-auto bg-transparent border-0 text-black opacity-5 float-right text-3xl leading-none font-semibold outline-none focus:outline-none" @click="toggleModalCreate('modal-id')">
          <span class="bg-transparent text-black opacity-5 h-6 w-6 text-2xl block outline-none focus:outline-none">
            ×
          </span>
                            </button>
                        </div>
                        <!--body-->
                        <form v-on:submit.prevent="submit1">
                        <div class="modal-content py-4 text-left px-6">
                                <div class="mb-4 ml-10">
                                    <label class="block text-gray-700 text-sm font-bold mb-2" for="newBarName">
                                        Bar name
                                    </label>
                                    <input class="shadow appearance-none border w-3/4 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                           id="newBarName" type="text" name="newBarName" v-model="newBarName">
                                </div>
                                <div class="mb-4 ml-10">
                                    <label class="block text-gray-700 text-sm font-bold mb-2" for="newSelectBartender">
                                        Assign Bartender
                                    </label>
                                    <select class="block border border-grey-light w-3/4 p-3 mt-4" id="newSelectBartender"
                                            name="newSelectBartender" v-model.trim="newSelectBartender">
                                        <option disabled value="">Please select one</option>
                                        <option v-for="bartender in bartenders" :value="bartender.employeeId" v-bind:key="bartender">{{bartender.username}}</option>
                                    </select>
                                </div>
                        </div>
                        <!--footer-->
                        <div class="flex items-center justify-end p-6 border-t border-solid border-gray-300 rounded-b">
                            <button class="bg-teal-500 text-white active:bg-green-600 font-bold uppercase text-sm px-6 py-3 shadow hover:shadow-lg outline-none focus:outline-none mr-1 mb-1" type="submit" style="transition: all .15s ease" @click="toggleModalCreate('modal-id')">
                                Create Bar
                            </button>
                            <button class="text-red-500 background-transparent font-bold uppercase px-6 py-2 text-sm outline-none focus:outline-none mr-1 mb-1" type="button" style="transition: all .15s ease" @click="toggleModalCreate('modal-id')">
                                Close
                            </button>
                        </div>
                        </form>
                    </div>
                </div>
            </div>
            <div class="hidden opacity-25 fixed inset-0 z-40 bg-black" id="modal-id-backdrop"></div>
        </div>
        <div class="pt-2 relative w-full mx-auto text-gray-600 mb-5">
            <input class="float-left border-2 border-gray-300 bg-white h-10 px-5 pr-16 text-sm focus:outline-none"
                   type="search" name="search" placeholder="Search" v-model="search">
        </div>
    <table class="table-fixed w-full">
        <thead>
        <tr class="bg-gray-100">
            <th class="px-4 py-2 w-20">No.</th>
            <th class="px-4 py-2">Bar name</th>
            <th class="px-4 py-2">Assigned Bartender</th>
            <th class="px-4 py-2">Action</th>
        </tr>
        </thead>
        <tbody>
        <tr class="border px-4 py-2" v-for="bar in searchBars" v-bind:key="bar.barId">
            <td class="border px-4 py-2">{{bar.barId}}</td>
            <td class="border px-4 py-2">{{bar.barName}}</td>
            <td class="border px-4 py-2">{{getChosenEmployee(bar.assignedBartender)}}</td>
            <td class="border px-4 py-2">
                <button @click="toggleModal();getBarById(bar.barId);getEmployeeById(bar.assignedBartender)"
                        class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                        id="editBtn">Edit
                </button>
                <button @click="deleteBar(bar.barId)"
                        class="bg-teal-500 hover:bg-red-700 text-white font-bold py-2 px-4">Delete
                </button>
            </td>
        </tr>
        </tbody>
    </table>
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
                        <p class="text-2xl font-bold">Edit Bar</p>
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
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="barName">
                                Bar name
                            </label>
                            <input class="shadow appearance-none border w-3/4 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                   id="barName" type="text" name="barName" v-model="barName">
                        </div>
                        <div class="mb-4 ml-20">
                            <label class="block text-gray-700 text-sm font-bold mb-2" for="selectBartender">
                                Assign Bartender
                            </label>
                            <select class="shadow block border border-grey-light w-3/4 p-3 mt-4 leading-tight focus:outline-none focus:shadow-outline" id="selectBartender"
                                    name="selectBartender" v-model.trim="selectBartender">
                                <option disabled value="">Please select one</option>
                                <option v-for="bartender in bartenders" :value="bartender.employeeId" v-bind:key="bartender">{{bartender.username}}</option>
                            </select>
                        </div>

                        <!--Footer-->
                        <div class="absolute flex justify-end">
                            <button type="submit"
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
</template>

<script>
    import axios from 'axios'

    export default {
        data() {
            return {
                bars: [],
                bartenders:[],
                selectBartenderId: null,
                selectBartender:null,
                selectedBartender: null,
                selected: "Choose bartender",
                selectedOption:null,

                newBarName: null,
                newSelectBartender: null,

                barId: null,
                barName: null,

                myModal: false,
                dynamicButton: 'Insert',
                dynamicTitle: 'Add Data',
                i: null,

                selectedBarId: null,
                name: null,
                selectAssignedEmployee: null,

                newName: null,
                newAssignedEmployee: null,

                search:null,
            }
        },

        mounted() {
            this.gerBars();
            this.getAllBartenders();

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
        computed: {
            searchBars (){
                if(this.search){
                    return this.bars.filter((bar)=>{
                        return this.search.toLowerCase().split(' ').every(v => bar.barName.toLowerCase().includes(v))
                    })
                }else{
                    return this.bars;
                }
            },
        },

        methods: {
            getCookie(name) {
                const value = document.cookie;
                console.log(document.cookie);
                const parts = value.split(name);
                if (parts.length === 2) return parts.pop().split(';').shift();
            },
            gerBars: function () {
                var token = this.getCookie("Token");
                axios.get('http://localhost:9090/system/bars', {headers: {'Authorization': 'Bearer' + token}}
                ).then(response => {
                    this.bars = response.data;
                    console.log(response);
                })
                    .catch(function (response) {
                        console.log(response);
                        alert("Could not load bars");
                    });
            },

            getBarById: function (id) {
                var token = this.getCookie("Token");
                axios.get('http://localhost:9090/system/bars/' + id, {headers: {'Authorization': 'Bearer' + token}}
                ).then(response => {
                    this.barId = response.data.barId;
                    this.barName = response.data.barName;
                })
                    .catch(function (response) {
                        console.log(response);
                        alert("Could not load bar");
                    });

            },

            getEmployeeById: function(id){
                var token = this.getCookie("Token");
                if(id !== 0){
                    axios.get('http://localhost:9090/system/employees/' + id, {headers: {'Authorization': 'Bearer' + token}}
                    ).then(response => {
                        this.selectBartender = response.data;
                        console.log(response);
                    })
                        .catch(function (response) {
                            console.log(response);
                            // alert("Could not load employees");
                        });
                }
            },

            getChosenEmployee(bartenderId){
                for(var i = 0; i< this.bartenders.length;i++)
                {
                    if(bartenderId == this.bartenders[i].employeeId){
                        return this.bartenders[i].username;
                    }
                }
                return "no assigned bartender";
            },

            // showSelectedBartender(bartenderId){
            //     // var bartender = this.getChosenEmployee(bartenderId);
            //     // console.log(bartender);
            //     // this.selected = bartender;
            // },

            getAllBartenders(){
                var token = this.getCookie("Token");
                    axios.get(' http://localhost:9090/system/employees/account/HEAD_BARTENDER', {headers: {'Authorization': 'Bearer' + token}}
                    ).then(response => {
                        this.bartenders = response.data;
                        console.log(response);
                    })
                        .catch(function (response) {
                            console.log(response);
                            alert("Could not load bartenders");
                        });
            },

            toggleModal() {
                const body = document.querySelector('body')
                const modal = document.querySelector('.modal')
                modal.classList.toggle('opacity-0')
                modal.classList.toggle('pointer-events-none')
                body.classList.toggle('modal-active')
            },

            toggleModalCreate: function(modalID){
                document.getElementById(modalID).classList.toggle("hidden");
                document.getElementById(modalID + "-backdrop").classList.toggle("hidden");
                document.getElementById(modalID).classList.toggle("flex");
                document.getElementById(modalID + "-backdrop").classList.toggle("flex");
            },

            deleteBar(barId) {
                var token = this.getCookie("Token");
                console.log("delete customer with id " + barId);
                if (confirm('Are you sure you want to delete this?')) {
                    axios.delete('http://localhost:9090/system/bars/' + barId, {headers: {'Authorization': 'Bearer' + token}}
                    ).then(() => {
                        this.gerBars();
                    })
                }
            },

            submit1() {
                var token = this.getCookie("Token");
                if(this.newSelectBartender == null){
                    axios.post('http://localhost:9090/system/bars/', {
                        barName: this.newBarName,
                    }, {headers: {'Authorization': 'Bearer' + token}})
                        .then(response => {
                            console.log(response);
                            this.gerBars();
                            this.newBarName = null,
                                this.newSelectBartender = null,
                                alert('The bar has been created.');
                        })
                        .catch(error => {
                            if (error.response.status == 422) {
                                this.errors = error.response.data.errors;
                            }
                            console.log('Error');
                        });
                }
                else {
                    axios.post('http://localhost:9090/system/bars/', {
                        barName: this.newBarName,
                        assignedBartender: this.newSelectBartender,
                    }, {headers: {'Authorization': 'Bearer' + token}})
                        .then(response => {
                            console.log(response);
                            this.gerBars();
                            this.newBarName = null,
                                this.newSelectBartender = null,
                                alert('The bar has been created.');
                        })
                        .catch(error => {
                            if (error.response.status == 422) {
                                this.errors = error.response.data.errors;
                            }
                            console.log('Error');
                        });
                }
            },

            submit() {
                var token = this.getCookie("Token");
                console.log(this.barId);
                console.log(this.barName);
                console.log(this.selectBartender);
                axios.put('http://localhost:9090/system/bars/' + this.barId, {
                    barId: this.barId,
                    barName: this.barName,
                    assignedBartender: this.selectBartender,
                }, {headers: {'Authorization': 'Bearer' + token}})
                    .then(response => {
                        console.log(response);
                        this.gerBars();
                        this.selectBartender = null,
                        this.assignedBartender = null,
                        this.barName = null,
                        alert('The bar has been updated.');
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
</script>

<style scoped>

</style>