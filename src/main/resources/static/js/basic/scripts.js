document.addEventListener("DOMContentLoaded", function() {
    const chatBox = document.getElementById("chat-box");
    const chatForm = document.getElementById("chat-form");
    const chatInput = chatForm.querySelector("input");

    // const webSocket = new WebSocket("ws://localhost:8080/ws/simplechatHandler");
    const webSocket = new SockJS("/ws/simplechatHandler");

    chatForm.addEventListener("submit", onChatSubmit);

    function onChatSubmit(event) {
        event.preventDefault();
        webSocket.send(chatInput.value);
        chatInput.value = "";
    }

    webSocket.onmessage = function (messageEvent) {
        const chatMessage = messageEvent.data;
        paintChat(chatMessage);
    }

    function paintChat(chatMessage) {
        const li = document.createElement("li");
        li.innerText = chatMessage;
        chatBox.appendChild(li);
    }
});

