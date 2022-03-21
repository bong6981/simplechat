const chatBox = document.querySelector(".chat-box");
const chatForm = document.querySelector("#chat-form");
const chatInput = chatForm.querySelector("input");
const roomId = room.id;
let stompClient = null;

chatForm.addEventListener("submit", onChatSubmit);

function connect() {
    const socket = new SockJS("/ws-stomp-chat");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        stompClient.subscribe("/topic/chat/room/" + roomId, function (chat) {
            const content = JSON.parse(chat.body);
            const message = content.message;
            if(content.writer === member) {
                paintSentChat(message);
            } else {
                paintReceivedChat(message);
            }
        });
        stompClient.send("/app/chat/notice/join", {}, JSON.stringify({chatRoomId: roomId, writer: member}));
    });
}

function paintSentChat(chat) {
    const outerDivClassNameForSentChat = "d-flex justify-content-end mb-4";
    const innerDivClassNameForSentChat = "message-sent";
    paintChat(chat, outerDivClassNameForSentChat, innerDivClassNameForSentChat);
}

function paintReceivedChat(chat) {
    const outerDivClassNameForReceivedChat = "d-flex justify-content-start mb-4";
    const innerDivClassNameForReceivedChat = "message-received";
    paintChat(chat, outerDivClassNameForReceivedChat, innerDivClassNameForReceivedChat)
}

function paintChat(chat, outerDivClassName, innerDivClassName) {
    const outerDiv = document.createElement("div");
    outerDiv.className = outerDivClassName;

    const innerDiv = document.createElement("div");
    innerDiv.className = innerDivClassName;
    innerDiv.innerText = member + " : " + chat;

    outerDiv.appendChild(innerDiv);
    chatBox.appendChild(outerDiv);
}

function onChatSubmit(event) {
    event.preventDefault();
    stompClient.send("/app/chat/message", {}, JSON.stringify({chatRoomId: roomId, message: chatInput.value, writer:member}));
    chatInput.value = "";
}

document.addEventListener("DOMContentLoaded", function() {
    connect();
});
