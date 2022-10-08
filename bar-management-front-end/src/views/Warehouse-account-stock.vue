<template>
    <main class="flex h-screen">
        <aside class="flex flex-col justify-between bg-white w-1/5 min-h-full shadow">
            <div class="flex flex-col w-2/3 mx-auto">
                    <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mt-10" for="selectItem">
                        Add item
                    </label>

                <select class="border-2 border-gray-300 my-3 py- bg-white h-10 text-center focus:outline-none" id="selectItem"
                        name="selectBartender" v-model.trim="selectItem">
                    <option disabled value="">Please select one</option>
                    <option v-for="item in items" :value="item" v-bind:key="item">{{item.itemName}}</option>
                </select>
                <span v-show="select && !selectItem" class="invalid-feedback text-red-500 text-xs italic mb-5">Select an item</span>
                <button class="bg-teal-500 hover:bg-blue-700 w-full text-white py-2 px-4 mb-5 focus:outline-none focus:shadow-outline" @click="addItem()">
                    Add
                </button>

                <button class="bg-teal-500 hover:bg-blue-700 w-full text-white py-2 px-4 mb-5 focus:outline-none focus:shadow-outline" @click="editItem()">
                    Edit
                </button>

                <button class="bg-teal-500 hover:bg-blue-700 w-full text-white py-2 px-4 mb-5 focus:outline-none focus:shadow-outline" @click="deleteItem()">
                    Delete
                </button>

                <button class="bg-teal-500 hover:bg-blue-700 w-full text-white py-2 px-4 mb-5 focus:outline-none focus:shadow-outline" @click="isOpen = !isOpen">
                    Create Item <i class="fa fas fa-sort-down"></i>
                </button>
                <button v-if="isOpen" @click="isOpen = false" tabindex="-1" class="fixed top-0 inset-0 h-full w-full bg-black opacity-0 cursor-default"></button>
                <div v-if="isOpen" class="relative top-auto w-full py-2 mt-2 bg-white">
                    <form v-on:submit.prevent="submit1">
                    <div class="w-full mt-5">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="newItem">
                            Item name
                        </label>
                        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 py-2 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                               id="newItem" type="text" name="newItem" v-model="newItemName">
                        <span v-show="submittedCreate && !newItemName" class="invalid-feedback text-red-500 text-xs italic mb-5">Item name is required</span>
                    </div>

                    <div class="w-full mt-5">
                        <label class="block uppercase tracking-wide text-gray-700 text-xs font-bold mb-2" for="newItemPrice">
                            Price
                        </label>
                        <input class="appearance-none block w-full bg-gray-200 text-gray-700 border border-gray-200 py-2 px-4 leading-tight focus:outline-none focus:bg-white focus:border-gray-500"
                               id="newItemPrice" type="text" name="newItemPrice" v-model="newItemPrice">
                        <span v-show="submittedCreate && !newItemPrice" class="invalid-feedback text-red-500 text-xs italic mb-5">Item price is required</span>
                    </div>
                    <div class="w-full mt-5">
                        <button class="bg-teal-500 hover:bg-blue-700 w-full text-white py-2 px-4 mb-5 focus:outline-none focus:shadow-outline" type="submit">
                            Create
                        </button>
                    </div>
                    </form>
                </div>
                <div class="text-grey-darkest my-3 py-2 inline-flex items-start justify-start font-bold mt-5">
                    <h3>Total Cost:</h3>
                </div>
                <button class="relative z-10 block m-2 overflow-hidden focus:outline-none text-sm leading-loose align-middle px-4 py-1 cursor-pointer text-sm bg-teal-500 text-white" @click="submitReceiving()">
                    Submit Receiving
                </button>
                <div v-show="submittedReceiving && !itemsToAccount.length > 0" class="invalid-feedback text-red-500 text-xs italic mb-5">There are no items to be received</div>
            </div>
        </aside>
        <div class="flex flex-col justify-between bg-white w-3/4 shadow min-h-full">
        <div class="container mx-auto px-4 sm:px-8">
            <h1 class="text-lg mt-8">Receive Items</h1>
            <div class="-mx-4 sm:-mx-8 px-4 sm:px-8 py-4 overflow-x-auto mt-10">
                <div
                        class="inline-block w-full shadow-md overflow-hidden">
                    <table class="min-w-full leading-normal shadow">
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
                                Product name
                            </th>
                            <th
                                    class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-700 uppercase tracking-wider"
                            >
                                price
                            </th>
                            <th
                                    class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-700 uppercase tracking-wider"
                            >
                                quantity
                            </th>
                            <th
                                    class="px-5 py-3 border-b-2 border-gray-200 bg-gray-100 text-xs font-semibold text-gray-700 uppercase tracking-wider"
                            >
                                Actions
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr class="border px-4 py-2" v-for="item in itemsToAccount" v-bind:key="item.itemId">
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <div class="flex">
                                    <div class="ml-3">
                                        <p class="text-gray-900 whitespace-no-wrap">
                                            {{item.itemId}}
                                        </p>
                                    </div>
                                </div>
                            </td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <p class="text-gray-900 whitespace-no-wrap">{{item.itemName}}</p>
                            </td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <p class="text-gray-900 whitespace-no-wrap">{{item.price}}$</p>
                            </td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <input type="number" min="1" class="w-10 bg-gray-200 text-gray-700 border border-gray-200"
                                       id="quantity" name="quantity" v-model.trim="item.quantity">
                            </td>
                            <td class="px-5 py-5 border-b border-gray-200 bg-white text-sm">
                                <button
                                        class="bg-teal-500 hover:bg-red-700 text-white font-bold py-2 px-4" @click="removeItem(item.itemId)">Delete
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

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
                            <p class="text-2xl font-bold text-gray-500">Edit Item</p>
                            <div class="modal-close cursor-pointer z-50">
                                <svg class="fill-current text-black" xmlns="http://www.w3.org/2000/svg" width="18"
                                     height="18" viewBox="0 0 18 18">
                                    <path d="M14.53 4.53l-1.06-1.06L9 7.94 4.53 3.47 3.47 4.53 7.94 9l-4.47 4.47 1.06 1.06L9 10.06l4.47 4.47 1.06-1.06L10.06 9z"></path>
                                </svg>
                            </div>
                        </div>

                        <!--Body-->
                        <form v-on:submit.prevent="submitEdit">
                            <div class="mb-4 ml-20">
                                <label class="block text-gray-500 text-sm font-bold mb-2" for="itemName">
                                    Name
                                </label>
                                <input class="shadow appearance-none border rounded w-3/4 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                       id="itemName" type="text" name="itemName" v-model="itemName">
                                <div v-show="submittedEdit && !itemName" class="invalid-feedback text-red-500 text-xs italic">Item name is required</div>
                            </div>
                            <div class="mb-4 ml-20">
                                <label class="block text-gray-500 text-sm font-bold mb-2" for="itemPrice">
                                    Price
                                </label>
                                <input class="shadow appearance-none border rounded w-3/4 py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
                                       id="itemPrice" type="text" name="itemPrice" v-model.trim="itemPrice">
                                <div v-show="submittedEdit && !itemPrice" class="invalid-feedback text-red-500 text-xs italic mb-5">Item price is required</div>
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
        </div>
    </main>
</template>

<script>
    import axios from "axios";

    export default {
        components: {
        },
        data() {
            return {
                myModal: false,

                items: [],
                itemsToAccount: [],

                selectItem: null,
                selectItemId: null,
                search: null,
                isOpen: false,
                isSearchOpen: false,

                itemName: null,
                itemPrice: null,

                newItemName: null,
                newItemPrice: null,

                select: false,
                submittedCreate: false,
                submittedEdit: false,
                submittedReceiving: false,
            }
        },
        mounted() {
            this.getItems();

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
            searchItems (){
                if(this.search){
                    return this.items.filter((item)=>{
                        return this.search.toLowerCase().split(' ').every(v => item.itemName.toLowerCase().includes(v))
                    })
                }else{
                    return this.items;
                }
            },
        },
        methods: {
            submitReceiving() {
                this.submittedReceiving = true;
                if (this.itemsToAccount.length > 0) {
                    var token = this.getCookie("Token");
                    for (let i = 0; i < this.itemsToAccount.length; i++) {
                        axios.post('http://localhost:9090/system/warehouse/', {
                            itemId: this.itemsToAccount[i].itemId,
                            quantity: this.itemsToAccount[i].quantity,
                        }, {headers: {'Authorization': 'Bearer' + token}})
                            .then(response => {
                                console.log(response);
                                this.getItems();
                                this.submittedReceiving = false;
                            })
                            .catch(error => {
                                if (error.response.status == 422) {
                                    this.errors = error.response.data.errors;
                                }
                                console.log('Error');
                            });
                    }
                    this.itemsToAccount = [];
                    // alert("Receiving submitted");
                }
            },

            removeItem(itemId) {
                for (let i = 0; i < this.itemsToAccount.length; i++) {
                    if(this.itemsToAccount[i].itemId === itemId){
                        const index = this.itemsToAccount.indexOf(this.itemsToAccount[i]);
                        if (index > -1) {
                            this.itemsToAccount.splice(index, 1);
                        }
                    }
                }
            },

            toggleModal() {
                const body = document.querySelector('body')
                const modal = document.querySelector('.modal')
                modal.classList.toggle('opacity-0')
                modal.classList.toggle('pointer-events-none')
                body.classList.toggle('modal-active')
            },

            getCookie(name) {
                const value = document.cookie;
                console.log(document.cookie);
                const parts = value.split(name);
                if (parts.length === 2) return parts.pop().split(';').shift();
            },

            addItem() {
                this.select = true;
                if (this.selectItem) {
                    this.itemsToAccount.push(this.selectItem);
                    console.log(this.itemsToAccount);
                    this.select = false;
                }
            },

            getItems: function () {
                var token = this.getCookie("Token");

                axios.get('http://localhost:9090/system/items', {headers: {'Authorization': 'Bearer' + token}}
                ).then(response => {
                    this.items = response.data;
                    console.log(response);
                })
                    .catch(function (response) {
                        console.log(response);
                        alert("Could not load items");
                    });
            },

            editItem() {
                this.select = true;
                if (this.selectItem) {
                    this.toggleModal();
                    this.itemName = this.selectItem.itemName;
                    this.itemPrice = this.selectItem.price;
                    this.select = false;
                }
            },

            deleteItem() {
                this.select = true;
                if (this.selectItem) {
                    var token = this.getCookie("Token");
                    console.log("delete item with id " + this.selectItem.itemId);
                    axios.delete('http://localhost:9090/system/items/' + this.selectItem.itemId, {headers: {'Authorization': 'Bearer' + token}}).then(() => {
                        this.getItems();
                        this.select = false;
                    })
                }
            },

            submit1() {
                this.submittedCreate = true;
                if (this.newItemName && this.newItemPrice) {
                    var token = this.getCookie("Token");
                    axios.post('http://localhost:9090/system/items/', {
                        itemName: this.newItemName,
                        price: this.newItemPrice,
                    }, {headers: {'Authorization': 'Bearer' + token}})
                        .then(response => {
                            console.log(response);
                            this.getItems();
                            this.newItemName = null;
                            this.newItemPrice = null;
                            this.submittedCreate = false;
                        })
                        .catch(error => {
                            if (error.response.status == 422) {
                                this.errors = error.response.data.errors;
                            }
                            console.log('Error');
                        });
                }
            },

            submitEdit() {
                this.submittedEdit = true;
                if (this.ItemName && this.ItemPrice) {
                    console.log("hello");
                    var token = this.getCookie("Token");
                    console.log(this.selectItem.itemId);
                    axios.put('http://localhost:9090/system/items/' + this.selectItem.itemId, {
                        itemId: this.selectItem.itemId,
                        itemName: this.itemName,
                        price: this.itemPrice,
                    }, {headers: {'Authorization': 'Bearer' + token}})
                        .then(response => {
                            console.log(response);
                            this.getItems();
                            this.submittedEdit = false;
                            alert('The item account has been updated.');
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