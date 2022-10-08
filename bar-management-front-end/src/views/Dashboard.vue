<template>
    <div class="container mx-auto flex-1 flex flex-col items-center px-2 mt-5">
        <div class="container w-full  float:left mt-10">
        </div>
    </div>
    <div class="flex flex-wrap">
            <div class="md:relative mx-auto lg:float-right lg:px-6">
                <ul class="list-reset flex flex-row md:flex-col text-center md:text-left">
                    <li class="mr-3 flex-1">
                        <a href="#" class="block py-1 md:py-3 pl-1 align-middle text-teal-500 no-underline hover:text-teal-800 border-b-2 border-gray-800 md:border-teal-500 hover:border-teal-800">
                            <i class="fas fa-glass-martini-alt pr-0 md:pr-3"></i><span class="pb-1 md:pb-0 text-xs md:text-base text-gray-600 md:text-gray-600 block md:inline-block">Pool Bar</span>
                        </a>
                    </li>
                    <li class="mr-3 flex-1">
                        <a href="#" class="block py-1 md:py-3 pl-1 align-middle text-teal-500 no-underline hover:text-teal-800 border-b-2 border-gray-800 md:border-teal-500 hover:border-teal-800">
                            <i class="fas fa-glass-martini-alt pr-0 md:pr-3"></i><span class="pb-1 md:pb-0 text-xs md:text-base text-gray-600 md:text-gray-600 block md:inline-block">Snack Bar</span>
                        </a>
                    </li>
                    <li class="mr-16 flex-1">
                        <a href="#" class="block py-1 md:py-3 pl-1 align-middle text-teal-500 no-underline hover:text-teal-800 border-b-2 border-teal-800">
                            <i class="fas fa-glass-martini-alt pr-0 md:pr-3"></i><span class="pb-1 md:pb-0 text-xs md:text-base text-gray-600 md:font-bold block md:inline-block">Lobby Bar</span>
                        </a>
                    </li>
                    <li class="mr-3 flex-1">
                        <a href="#" class="block py-1 md:py-3 pl-1 align-middle text-teal-500 no-underline hover:text-teal-800 border-b-2 border-gray-800 md:border-teal-500 hover:border-teal-800">
                            <i class="fas fa-glass-martini-alt pr-0 md:pr-3"></i><span class="pb-1 md:pb-0 text-xs md:text-base text-gray-600 md:text-gray-600 block md:inline-block">Beach Bar</span>
                        </a>
                    </li>
                </ul>
            </div>
<!--            </div>-->

    <div class="container w-full md:w-4/5 mx-auto bg-white border rounded shadow border-teal-500">
        <div class="border-b p-3">
            <h5 class="uppercase text-gray-600">Lobby Bar</h5>
        </div>
        <div class="w-full px-4 md:px-0 md:mt-8 mb-16 text-grey-darkest leading-normal">

            <!--Console Content-->

            <div class="flex flex-wrap">
                <div class="w-full md:w-1/2 xl:w-1/3 p-3">
                    <!--Metric Card-->
                    <div class="bg-white border rounded shadow p-2 border-indigo-500 text-gray-600">
                        <div class="flex flex-row items-center">
                            <div class="flex-shrink pr-4">
                                <div class="rounded p-3 bg-green-dark"><i class="fas fa-calendar-day fa-2x fa-fw"></i></div>
                            </div>
                            <div class="flex-1 text-right md:text-center">
                                <h5 class="uppercase text-grey">Today</h5>
                                <h3 class="text-3xl">$32,46 <span class="text-green"><i class="fas fa-caret-up"></i></span></h3>
                            </div>
                        </div>
                    </div>
                    <!--/Metric Card-->
                </div>
                <div class="w-full md:w-1/2 xl:w-1/3 p-3">
                    <!--Metric Card-->
                    <div class="bg-white border rounded shadow p-2 border-green-500 text-gray-600">
                        <div class="flex flex-row items-center">
                            <div class="flex-shrink pr-4">
                                <div class="rounded p-3 bg-orange-dark"><i class="fas fa-calendar-week fa-2x fa-fw"></i></div>
                            </div>
                            <div class="flex-1 text-right md:text-center">
                                <h5 class="uppercase text-grey">Weekly</h5>
                                <h3 class="text-3xl">$500,49<span class="text-orange"><i class="fas fa-exchange-alt"></i></span></h3>
                            </div>
                        </div>
                    </div>
                    <!--/Metric Card-->
                </div>
                <div class="w-full md:w-1/2 xl:w-1/3 p-3">
                    <!--Metric Card-->
                    <div class="bg-white border rounded shadow p-2 border-yellow-500 text-gray-600">
                        <div class="flex flex-row items-center">
                            <div class="flex-shrink pr-4">
                                <div class="rounded p-3 bg-yellow-dark"><i class="fas fa-dollar-sign fa-2x fa-fw"></i></div>
                            </div>
                            <div class="flex-1 text-right md:text-center">
                                <h5 class="uppercase text-grey">Total Cost</h5>
                                <h3 class="text-3xl">$3249 <span class="text-yellow-dark"><i class="fas fa-caret-up"></i></span></h3>
                            </div>
                        </div>
                    </div>
                    <!--/Metric Card-->
                </div>
            </div>

            <!--Divider-->
            <hr class="border-b-2 border-teal-500 my-8 mx-4">

            <div class="flex flex-row flex-wrap flex-grow mt-2">

                <div class="w-full md:w-1/2 p-3">
                    <!--Graph Card-->
                    <div class="bg-white border rounded shadow border-teal-500 text-gray-600">
                        <button
                                class="bg-teal-500 hover:bg-red-700 text-white font-bold px-4 float-right absolute mt-4 ml-48">Filter
                        </button>
                        <div class="border-b p-3">
                                <h5 class="uppercase">Items flow</h5>
                        </div>
                        <div class="flex flex-col">
                            <div class="flex-grow overflow-auto h-64">
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
                                        <td class="px-6 py-4 text-center">Over - Gin</td>
                                        <td class="px-6 py-4 text-center">3</td>
                                    </tr>
                                    <tr>
                                        <td class="px-6 py-4 text-center">17/01/2021</td>
                                        <td class="px-6 py-4 text-center">Received - Orange Juice</td>
                                        <td class="px-6 py-4 text-center">3</td>
                                    </tr>
                                    <tr>
                                        <td class="px-6 py-4 text-center">17/01/2021</td>
                                        <td class="px-6 py-4 text-center">Over - Orange Juice</td>
                                        <td class="px-6 py-4 text-center">2</td>
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

<!--                        </div>-->
                    </div>
                    <!--/Graph Card-->
                </div>

                <div class="w-full md:w-1/2 p-3">
                    <!--Graph Card-->
                    <div class="bg-white border rounded shadow border-teal-500">
                        <div class="border-b p-3">
                            <h5 class="uppercase text-gray-600">Graph</h5>
                        </div>
                        <div class="h-64 ml-10">
                            <canvas id="chartjs-0" class="chartjs" width="undefined" height="undefined"></canvas>
                            <img height="350" width="500" src='../../public/images/chart2.png'/>
                        </div>
                    </div>
                    <!--/Graph Card-->
                </div>
            </div>
        </div>

    </div>
    </div>
</template>

<script>
    export default {
        name: "Dashboard"
    }
</script>

<style scoped>

</style>