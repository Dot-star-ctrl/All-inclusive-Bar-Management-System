<template>
<div>
        <div class="flex mx-auto items-center justify-center shadow-lg mt-24 mx-8 mb-4 max-w-lg border border-teal-500">
            <div class="w-full max-w-xl bg-white rounded-lg px-4 pt-2">
                <div class="flex flex-wrap -mx-3 mb-6">
                    <h2 class="px-4 pt-3 pb-2 text-gray-500 text-lg">Your message will be sent to all employees</h2>
                    <div class="w-full md:w-full px-3 mb-2 mt-2">
                        <div class="-mr-1">
                            <button type='submit' id="connect-button" class="bg-white text-gray-500 font-medium py-1 px-4 border float-left border-gray-400 tracking-wide mr-1 hover:bg-gray-100" @click="onConnectClick()">Connect</button>
                        </div>
                        <br><br>
                        <div id="console" class="h-32"></div>
                        <br>
                        <input id="message-input" type="text" class="bg-gray-100 border border-gray-400 leading-normal resize-none w-3/5 float-left py-1 px-3 font-medium placeholder-gray-700 focus:outline-none focus:bg-white">
                        <button id="send-button" class="bg-white text-white font-medium py-1 px-4 border bg-teal-500 border-gray-400 float-right tracking-wide mr-1 hover:bg-gray-100 hover:text-gray-500" @click="onSendClick()">Send message</button>
                    </div>
                    <div class="w-full md:w-full flex items-start md:w-full px-3">
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: 'WebSocket',
        data: function() {
            return {
                connection: null,
                webSocketUri: "wss://echo.websocket.org",
                connected:false,
                webSocket:null
            }
        },
        methods: {
            appendToConsole(message, className) {
                var consoleElement = document.getElementById("console");
                var lineToAppend = document.createElement("div");

                lineToAppend.className = className;
                lineToAppend.textContent = message;

                consoleElement.appendChild(lineToAppend);
            },
            onSendClick() {
                var messageInput = document.getElementById("message-input");
                console.log("onSendClick invoked...");
                if (this.webSocket && this.webSocket.readyState === 1) {
                    console.log("onSendClick(): sending message ", messageInput.value);

                    this.webSocket.send(messageInput.value);
                    this.appendToConsole(messageInput.value, "sent");
                }
            },
            onConnectClick() {
                var connectButton = document.getElementById("connect-button");
                console.log("onConnectClick() called...");

                if (!this.webSocket || this.webSocket.readyState === 3) {
                    console.log("onConnectClick(): connecting web socket...");
                    this.webSocket = new WebSocket(this.webSocketUri);
                    this.webSocket.onopen = this.ws_onOpen;
                    this.webSocket.onclose = this.ws_onClose;
                    this.webSocket.onmessage = this.ws_onMessage;
                    this.webSocket.onerror = this.ws_onError;

                    connectButton.innerHTML = "Connecting...";
                    connectButton.disabled = true;
                    this.appendToConsole("Connecting...", "system");
                } else {
                    console.log("onConnectClick(): closing web socket...");
                    this.webSocket.close();
                }
            },
            ws_onOpen() {
                var connectButton = document.getElementById("connect-button");
                console.log("WebSocket is open.");
                this.connected = true;
                connectButton.innerHTML = "Disconnect";
                connectButton.disabled = false;
                this.appendToConsole("Connected", "system");
            },
            ws_onClose() {
                var connectButton = document.getElementById("connect-button");
                console.log("WebSocket is closing.");
                this.connected = false;
                connectButton.innerHTML = "Connect";
                this.webSocket = null;
                this.appendToConsole("Closed", "system");
            },
            ws_onMessage(e) {
                this.appendToConsole(e.data, "received");
            },
            ws_onError() {
                var connectButton = document.getElementById("connect-button");
                alert("An error occurred.");
                this.connected = false;
                this.webSocket = null;
                connectButton.value = "Connect";
            }
        },
    }
</script>

<style>
    #console {
        border: 1px solid #cccccc;
        overflow-y: scroll;
    }
    .system {
        color: green;
    }

    .sent {
        color: blue;
    }

    .received {
        color: red;
    }
</style>