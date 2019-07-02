<%@ page import="model.User" %>
<%@ page import="dao.FriendDAO" %>
<%@ page import="servlet.ServletListener" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User usr = (User) session.getAttribute("user");
    if(usr == null) {
        response.sendRedirect("register.jsp");
    }
    //Clear MessageStorage
    ServletListener.getMesStorage().clearMessage(usr.getUid());
    //Get All Friends
    request.setAttribute("friends",FriendDAO.getFriends(((User) session.getAttribute("user")).getUid()));
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Crush!</title>
    <link rel="shortcut icon" type="image/x-icon" href="img/tinder.png" />
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles-chat.css">
    <link rel="stylesheet" href="css/emojionearea.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/popper.min.js"></script>
    <script src="js/emojionearea.min.js"></script>
</head>
<body>
<div id="frame">
    <div id="sidepanel">
        <!----- Pofile của mình hiện ở đây ----->
        <div id="profile">
            <div class="wrap">
                <img id="profile-img" src="<%=usr.getAvatar()%>" class="online"/>
                <p><%=usr.getFullName()%></p>
            </div>
        </div>
        <!--- Khung tìm Kiếm ---->
        <div id="search">
            <label><i class="fa fa-search" aria-hidden="true"></i></label>
            <input id="search_input" type="text" placeholder="Search..." />
        </div>
        <div class="container m-3">
            <div class="row">
                <button id="btn-logout" class="btn btn-sm btn-primary m-1">Logout</button>
                <button id="btn-profile" class="btn btn-sm btn-secondary m-1">Profile</button>
                <button id="btn-chat" class="btn btn-sm btn-success m-1">Chat</button>
                <button id="btn-find-crush" class="btn btn-sm btn-danger m-1">Find my Crush!</button>
            </div>
        </div>
        <script>
            $("#btn-chat").click(function() {
                $(".find-crush").hide();
                $(".messages").show();
                $(".message-input").show();
                $(".contact-profile").show();
            });
            $("#btn-logout").click(function() {
                window.location.href='ProcessLogout';
            })
            $("#btn-profile").click(function() {
                window.location.href='update_info.jsp';
            })
            $("#btn-find-crush").click(function() {
                $(".find-crush").show();
                $(".contact-profile").hide();
                $(".messages").hide();
                $(".message-input").hide();
            });
        </script>
        <!--- Danh sách bạn bè ---->
        <div id="contacts">
            <ul>
                <c:forEach var="fri" items="${friends}">
                    <li class="contact">
                        <div class="wrap">
                            <span class="contact-status online"></span>
                            <img class="avatar" src="${fri.value.avatar}"/>
                            <div class="meta">
                                <p class="name">${fri.value.fullName}</p>
                                <p class="read" id="ct_last_${fri.key}">⠀</p>
                            </div>
                            <p class="fid" hidden>${fri.key}</p>
                        </div>
                    </li>
                </c:forEach>
<%--                <span class="contact-status away"></span>--%>
<%--                <span class="contact-status busy"></span>--%>
            </ul>
        </div>
        <script>
            let curChatActive= -1;
            let header = $("#contacts");
            let btns = $(header).find(".contact");
            for (let i = 0; i < btns.length; i++) {
                btns[i].addEventListener("click", function() {

                    let fid = $(this).find(".fid")[0].innerText;
                    if(curChatActive==fid) return;

                    let name = $(this).find(".name")[0].innerText;
                    let avatar = $(this).find(".avatar")[0].src;

                    $(".find-crush").hide();
                    $(".messages").show();
                    $(".message-input").show();
                    $(".contact-profile").show();
                    //Click Contact Process
                    let current = $(header).find(".active");
                    if(current[0]!=null)
                        current[0].className = current[0].className.replace(" active", "");
                    this.className += " active";

                    //Update Chat Info Header
                    $("#chat-info-img").attr("src",avatar);
                    $("#chat-info-usr-name").text(name);
                    //Show Corresponding Chat Content
                    $("#chat-box-"+fid).show();
                    $("#chat-box-"+curChatActive).hide();
                    curChatActive=fid;
                    //Update chat engine
                    reconfigChat(fid);
                    $("#ct_last_"+fid).addClass('read').removeClass('unread')
                });
            }
        </script>
    </div>
    <div class="content">
        <div class="find-crush h-75 w-25">
            <img id="crush-ava" src="img/login.jpg">
            <div class="cruch-btn-group">
                <button class="btn-hover color-4">CRUSH</button>
                <button class="btn-hover color-8">PASS</button>
            </div>
        </div>

        <%--Chat Area--%>
        <div class="contact-profile">
            <img id="chat-info-img" src=""/>
            <p id="chat-info-usr-name"></p>
        </div>
        <div class="messages">
            <c:forEach var="fri" items="${friends}">
                <ul id="chat-box-${fri.key}" style="display: none">
                    <li class="replies">
                        <img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
                        <p>${fri.value.uid}</p>
                    </li>
                </ul>
            </c:forEach>
        </div>
        <div class="message-input">
            <div class="wrap">
                <form id="messageForm" name="messageForm" nameForm="messageForm">
                    <input type="text" id="message" placeholder="Write your message..." />
<%--                    <textarea id="message" placeholder="Write your message..."></textarea>--%>
                    <button type="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                </form>
            </div>
        </div>
        <%--Chat Area--%>
    </div>
</div>
</body>
<script type="text/javascript" src="js/chat.js"></script>
<script>
    //ADD FID & UInfo TO LIST
    let list = {};
    <c:forEach var="fri" items="${friends}">
        list[${fri.key}]=[${fri.value.uid},"${fri.value.avatar}"];
    </c:forEach>
    config(list)
    //Submit On Enter
    // $('#message').keypress(function(e){
    //     if(e.which == 13){
    //         e.preventDefault();
    //         $(this).closest('form').find('button').click();
    //     }
    // });
    //Ini Emoji
    // $('#message').emojioneArea({
    //     pickerPosition:"top"
    // })
    // $(document).ready(function(){
    // $('#editor_catch').on('keydown', function(event) {
    //     console.log(event.keyCode);
    // })});
</script>
</html>