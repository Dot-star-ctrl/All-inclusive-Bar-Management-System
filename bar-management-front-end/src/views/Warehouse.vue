<template>
    <div class="container mx-auto flex-1 flex flex-col items-center px-2 mt-5">
        <div class="container w-full  float:left mt-10">
        </div>
    </div>
    <div class="flex flex-wrap">
        <div class="container w-full md:w-2/6 mx-auto bg-white border shadow border-teal-500">
            <div class="border-b p-3">
                <h5 class="uppercase text-grey-dark">Items log</h5>
                <div class="block border border-grey-light w-full p-3 rounded mt-4 border-teal-500">
                    <label class="pr-20" for="dateTime">Select Date:</label>
                    <input type="datetime-local" id="dateTime" name="dateTime"/>
                </div>
            </div>
            <div class="flex flex-col">
                <div class="flex-grow overflow-auto">
                    <table class="relative w-full border">
                        <thead>
                        <tr>
                            <th class="sticky top-0 px-6 py-3 text-white bg-teal-500">Date</th>
                            <th class="sticky top-0 px-6 py-3 text-white bg-teal-500">Product</th>
                            <th class="sticky top-0 px-6 py-3 text-white bg-teal-500">Quantity</th>
                        </tr>
                        </thead>
                        <tbody class="divide-y bg-red-100">
                        <tr>
                            <td class="px-6 py-4 text-center">17/01/2021</td>
                            <td class="px-6 py-4 text-center">Received - Whiskey</td>
                            <td class="px-6 py-4 text-center">3</td>
                        </tr>
                        <tr>
                            <td class="px-6 py-4 text-center">17/01/2021</td>
                            <td class="px-6 py-4 text-center">Received - Gin</td>
                            <td class="px-6 py-4 text-center">3</td>
                        </tr>
                        <tr>
                            <td class="px-6 py-4 text-center">17/01/2021</td>
                            <td class="px-6 py-4 text-center">Received - Orange Juice</td>
                            <td class="px-6 py-4 text-center">3</td>
                        </tr>
                        <tr>
                            <td class="px-6 py-4 text-center">17/01/2021</td>
                            <td class="px-6 py-4 text-center">Received - Orange Juice</td>
                            <td class="px-6 py-4 text-center">5</td>
                        </tr>
                        <tr>
                            <td class="px-6 py-4 text-center">17/01/2021</td>
                            <td class="px-6 py-4 text-center">Received - Apple Juice</td>
                            <td class="px-6 py-4 text-center">6</td>
                        </tr>
                        <tr>
                            <td class="px-6 py-4 text-center">17/01/2021</td>
                            <td class="px-6 py-4 text-center">Received - Grenadine</td>
                            <td class="px-6 py-4 text-center">3</td>
                        </tr>
                        </tbody>

                    </table>
                </div>
            </div>
        </div>

        <div class="container w-full md:w-3/6 mx-auto bg-white border shadow border-teal-500">
            <div class="border-b p-3 border-teal-500">
                <h5 class="uppercase text-grey-dark">Items availability</h5>
            </div>
            <div class="w-full px-4 md:px-0 mb-16 text-grey-darkest leading-normal">

                <!--Console Content-->

                <table class="table-fixed w-full">
                    <thead>
                    <tr>
                        <th class="px-4 py-2 w-20">No.</th>
                        <th class="px-4 py-2">Item</th>
                        <th class="px-4 py-2">Quantity</th>
                        <th class="px-4 py-2">Price per unit</th>
                        <th class="px-4 py-2">Total price</th>
                        <th class="px-4 py-2">
                            <button class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                    id="editBtn">Filter
                            </button>
                        </th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Wiskey</td>
                        <td class="border px-4 py-2">32</td>
                        <td class="border px-4 py-2">$19</td>
                        <td class="border px-4 py-2">$608</td>
                        <td class="border px-4 py-2">
                            <button class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                    id="editBtn">Edit
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Wiskey</td>
                        <td class="border px-4 py-2">32</td>
                        <td class="border px-4 py-2">$19</td>
                        <td class="border px-4 py-2">$608</td>
                        <td class="border px-4 py-2">
                            <button class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                    id="editBtn">Edit
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Wiskey</td>
                        <td class="border px-4 py-2">32</td>
                        <td class="border px-4 py-2">$19</td>
                        <td class="border px-4 py-2">$608</td>
                        <td class="border px-4 py-2">
                            <button class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                    id="editBtn">Edit
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Wiskey</td>
                        <td class="border px-4 py-2">32</td>
                        <td class="border px-4 py-2">$19</td>
                        <td class="border px-4 py-2">$608</td>
                        <td class="border px-4 py-2">
                            <button class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                    id="editBtn">Edit
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Wiskey</td>
                        <td class="border px-4 py-2">32</td>
                        <td class="border px-4 py-2">$19</td>
                        <td class="border px-4 py-2">$608</td>
                        <td class="border px-4 py-2">
                            <button class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                    id="editBtn">Edit
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <td class="border px-4 py-2">1</td>
                        <td class="border px-4 py-2">Wiskey</td>
                        <td class="border px-4 py-2">32</td>
                        <td class="border px-4 py-2">$19</td>
                        <td class="border px-4 py-2">$608</td>
                        <td class="border px-4 py-2">
                            <button class="modal-open bg-teal-500 hover:bg-blue-700 text-white font-bold py-2 px-4 mr-2"
                                    id="editBtn">Edit
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
    export default {
        name: "Warehouse"
    }
</script>

<style scoped>

</style>