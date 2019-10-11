'use strict';
const RELOAD_TIME = 650;
const NUM_OLD_MES_ET = 10;
const API_URL = "http://www.crushme.tk/api"; //www.crushme.tk/api";

let messageForm = document.querySelector('#messageForm');
let messageInput = document.querySelector('#message');
let searchInput = document.querySelector('#search_input');
let messageArea = $(".messages")[0];

let uid = getCookie("uid");
let userB = -1;
let fid = -1
let avatar = $("#profile-img").attr("src");

let fid_usr_list = null;
let initialized_chat = {};

function getCookie(name) {
    let re = new RegExp(name + "=([^;]+)");
    let value = re.exec(document.cookie);
    return (value != null) ? unescape(value[1]) : null;
}

function config(list) {
    fid_usr_list= list;
    getMessage();
    messageArea.addEventListener("scroll",scrollCheck);
}
//RE_CONFIG CHAT BOX
function reconfigChat(f) {
    if(initialized_chat[f]==null) {
        let ulLen = $("#chat-box-"+f+" li").length;
        //Get old except new messages
        initialized_chat[f] = Math.floor(ulLen/NUM_OLD_MES_ET);
        getOldMessage(f,ulLen%NUM_OLD_MES_ET);
        messageArea.scrollTop = messageArea.scrollHeight;
    }
    fid = f;
    userB = fid_usr_list[f][0];
}

//GET OLD MESSAGE (except for new message if dont have new -> except=-1)
function getOldMessage(fid,except) {
    var gOldApi = new XMLHttpRequest();
    gOldApi.open("GET", API_URL+"/getOldMessage?fid="+fid+"&i="+initialized_chat[fid]+"&n="+NUM_OLD_MES_ET,true);
    gOldApi.onreadystatechange = function() {
        if (gOldApi.readyState == XMLHttpRequest.DONE) {
            let list = JSON.parse(gOldApi.responseText);
            let gap2end = messageArea.scrollHeight - messageArea.scrollTop;
            list = (except!=-1) ? list.splice(except) : list;
            list.forEach(function (i) {
                i['isOld'] = true;
                i['avatar'] = (i.from == uid) ? avatar : fid_usr_list[i.fid][1];
                i['type'] = (i.from == uid) ? 'sent' : 'replies';
                updateMessage(i);
            });
            messageArea.scrollTop = messageArea.scrollHeight - gap2end;
            initialized_chat[fid]++;
        }
    }
    gOldApi.send();
}

//GET NEW MESSAGE FOR ALL
function getMessage(){
    var gNewApi = new XMLHttpRequest();
    gNewApi.open("GET", API_URL+"/getNewMessage?uid="+uid, true);
    gNewApi.onreadystatechange = function() {
        if (gNewApi.readyState == XMLHttpRequest.DONE) {
            let hash = JSON.parse(gNewApi.responseText)
            if(hash!=null) {
                Object.keys(hash).forEach(function (key) {
                    hash[key].forEach(function(data) {
                        let message = {
                            fid: key,
                            type: 'replies',
                            avatar: fid_usr_list[key][1],
                            content: data,
                        };
                        updateMessage(message);
                        updateContactMessage(message);
                    })
                });
            }
        }
    }
    gNewApi.send();
    setTimeout(getMessage, RELOAD_TIME);
}

function sendMessage(evt) {
    evt.preventDefault();
    let messageContent = messageInput.value.trim();

    if(messageContent) {
        let message = {
            fid: fid,
            from: uid,
            content: messageInput.value,
        };
        var sendApi = new XMLHttpRequest();
        sendApi.open("POST", API_URL+"/sendMessage", true);
        addMessageFromSend(message);
        sendApi.send(JSON.stringify(message));
    }
}

function addMessageFromSend(message) {
    message['on']=userB;
    message['type']='sent';
    message['avatar']=avatar;
    updateMessage(message);
    updateContactMessage(message);
    messageInput.value = '';
}

function updateMessage(message){
    let messageElement = document.createElement('li');
    messageElement.classList.add(message.type);

    let avatarElement = document.createElement('img');
    avatarElement.setAttribute("src",message.avatar);

    messageElement.appendChild(avatarElement);

    let textElement = document.createElement('p');

    let messageText = document.createTextNode(message.content);

    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);

    if(message.isOld==true) {
        $(messageArea).find("#chat-box-" + message.fid).prepend(messageElement);
    }else {
        $(messageArea).find("#chat-box-" + message.fid).append(messageElement);
        messageArea.scrollTop = messageArea.scrollHeight;
    }
}

function updateContactMessage(message) {
    if(message.type=="replies")
        $("#ct_last_"+message.fid).addClass('unread').removeClass('read');
    $("#ct_last_"+message.fid).text(((message.type=="sent") ? 'You: ' : '')+message.content);
}
function scrollCheck() {
    if(messageArea.scrollTop==0){
        getOldMessage(curChatActive);
    }
}

//Contact Filter On Search
function filterContact(){
    let s = searchInput.value.toLowerCase();
    $('.contact').each(function() {
        ($(this).find(".name").text().toLowerCase().includes(s)) ? $(this).show():$(this).hide();
    });
}

messageForm.addEventListener('submit', sendMessage, true);
searchInput.addEventListener('input', filterContact, true);