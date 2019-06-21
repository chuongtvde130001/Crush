'use strict';
var usernamePage = document.querySelector('#userA-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var socket = null;
var uid = getCookie("uid");
var fid = 1 //TEMPORARY!!!!
var userA = getCookie("username");
var userB = "GUEST";

var colors = [
    '#2196F3', '#32c787', '#00BCD4', '#ff5652',
    '#ffc107', '#ff85af', '#FF9800', '#39bbb0'
];

function getCookie(name) {
    var re = new RegExp(name + "=([^;]+)");
    var value = re.exec(document.cookie);
    return (value != null) ? unescape(value[1]) : null;
}

window.onload = config();

function config() {
    setTimeout(getMessage,3000);
}

function getMessage(){
    console.log("Getting:::");
    socket = new WebSocket("ws://localhost:8080/Assignment/getMessage");
    var getRequest = {
        fid: fid,
        uid: uid,
    };
    socket.onopen = function () {
        socket.send(JSON.stringify(getRequest));
    }
    socket.onmessage = function (message) {
        console.log("RESULT: "+message.data);
        socket.close()
    }
    setTimeout(getMessage, 10000);
}


function sendMessage(evt) {
    evt.preventDefault();
    var messageContent = messageInput.value.trim();

    if(messageContent) {
        var message = {
            fid: fid,
            from: uid,
            content: messageInput.value,
        };
        socket = new WebSocket("ws://localhost:8080/Assignment/sendMessage");
        socket.onopen = function () {
            socket.send(JSON.stringify(message));
            socket.close()
        }
        message['sender']=userA;
        addMessage(message);
        messageInput.value = '';
    }
}

function addMessage(message){
    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');

    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(message.sender.charAt(0));
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.sender);

    messageElement.appendChild(avatarElement);

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(message.sender);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);

    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);

    messageElement.appendChild(textElement);

    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
    var hash = 0;
    for (var i = 0; i < messageSender.length; i++) {
        hash = 31 * hash;
    }

    var index = Math.abs(hash % colors.length);
    return colors[index];
}

// usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', sendMessage, true)