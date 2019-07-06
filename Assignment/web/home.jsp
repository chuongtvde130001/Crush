<%@ page import="model.User" %>
<%@ page import="dao.FriendDAO" %>
<%@ page import="servlet.ServletListener" %>
<%@ page import="dao.WantDAO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User usr = (User) session.getAttribute("user");
    if (usr == null) {
        response.sendRedirect("register.jsp");
        return;
    }
    //Clear MessageStorage
    ServletListener.getMesStorage().clearMessage(usr.getUid());
    //Get All Friends
    request.setAttribute("friends", FriendDAO.getFriends(usr.getUid()));
    //Get All User meet want
    request.setAttribute("wants", WantDAO.getUsrsMatchWant(usr.getUid()));
    //Get All Target crush User
    request.setAttribute("targets", FriendDAO.getTarsCrushUser(usr.getUid()));
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Find my Crush</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png"/>

        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/styles-chat.css">
        <link href="https://fonts.googleapis.com/css?family=Courgette|Lobster|Pacifico&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/home-style.css">
        <link rel="stylesheet" href="css/emojionearea.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.min.css">

        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.all.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/emojionearea.min.js"></script>
        <script src="js/want.js"></script>
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
                <div class="container">
                    <div class="btn-group-vertical btn-block" style="width: 100%;">
                        <a id="btn-find-crush" class="btn btn-success">Find my Crush</a>
                        <a id="btn-who-crush" class="btn btn-primary">Who Crush Me?</a>
                        <a href="update_info.jsp" class="btn btn-secondary">Profile</a>
                        <a href="ProcessLogout" class="btn btn-danger">Logout</a>
                    </div>
                </div><br>
                <div id="search">
                    <label><i class="fa fa-search" aria-hidden="true"></i></label>
                    <input id="search_input" type="text" placeholder="Search..." />
                </div><br>
                <script>
                    $("#btn-find-crush").click(function () {
                        if (!isWListEmpty())
                            $("#findCrush").show(200);
                        $("#chat").hide(400);
                        $("#crushOnMe").hide(400);
                        disableChat();
                    })
                    $("#btn-who-crush").click(function () {
                        $("#crushOnMe").show(200);
                        $("#findCrush").hide(400);
                        $("#chat").hide(400);
                        disableChat();
                    })
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
                    let curChatActive = -1;
                    let header = $("#contacts");
                    let btns = $(header).find(".contact");
                    for (let i = 0; i < btns.length; i++) {
                        btns[i].addEventListener("click", function () {
                            let fid = $(this).find(".fid")[0].innerText;
                            if (curChatActive == fid) {
                                return;
                            }
                            let name = $(this).find(".name")[0].innerText;
                            let avatar = $(this).find(".avatar")[0].src;

                            $("#findCrush").hide(400);
                            $("#crushOnMe").hide(400);
                            $("#chat").show(200);
                            $(".messages").show(200);
                            $(".message-input").show(200);

                            //Click Contact Process
                            let current = $(header).find(".active");
                            if (current[0] != null)
                                current[0].className = current[0].className.replace(" active", "");
                            this.className += " active";

                            //Update Chat Info Header
                            $("#chat-info-img").attr("src", avatar);
                            $("#chat-info-usr-name").text(name);
                            //Show Corresponding Chat Content
                            $("#chat-box-" + fid).show();
                            $("#chat-box-" + curChatActive).hide();
                            curChatActive = fid;

                            messageArea.scrollTop = messageArea.scrollHeight;

                            //Update chat engine
                            reconfigChat(fid);
                            $("#ct_last_" + fid).addClass('read').removeClass('unread')
                        });
                    }
                    function disableChat() {
                        $(header).find(".active")[0].className = "contact";
                        curChatActive = -1
                    }
                </script>
            </div>
            <div class="content" style="background-image: linear-gradient(to right top, #051937, #004d7a, #008793, #00bf72, #a8eb12);">
                <div id="findCrush" class="profile-card_chat">
                    <div class="card" id="coverPhoto" style="background-image: url('img/share-section1.jpg')">
                        <div class="profile-card__img">
                            <img src="" alt="profile card">
                        </div>
                    </div>
                    <div class="profile-card__cnt js-profile-cnt">
                        <div class="profile-card__name display-2"></div>
                        <div class="profile-card__txt"></div>
                        <div class="profile-card-inf">
                            <div class="profile-card-inf__item">
                                <div class="profile-card-inf__title">Age</div>
                                <div class="profile-card-inf__txt"></div>
                            </div>
                            <div class="profile-card-inf__item">
                                <div class="profile-card-inf__title">Gender</div>
                                <div class="profile-card-inf__txt"></div>
                            </div>

                            <div class="profile-card-inf__item">
                                <div class="profile-card-inf__title">Email</div>
                                <div class="profile-card-inf__txt"></div>
                            </div>
                        </div>
                        <div class="profile-card-ctr">
                            <form action="ProcessCrush" method="post">

                                <input id="crush_button" type="submit" value="Crush" class="profile-card__button button--orange"/>

                                <input id="pass_button" type="submit" value="Pass" class="profile-card__button button--blue"/>
                        </div>
                        <script>
                            <c:forEach var="usr" items="${wants}">
                            want_list.push([${usr.uid}, '${usr.fullName}',${usr.age}, '${usr.gender}', '${usr.email}', '${usr.avatar}', '${usr.description}']);
                            </c:forEach>
                        </script>
                        <input type="text" value="${usr.uid}" name ="value" hidden>
                        </form>
                    </div>
                </div>
                <div id="crushOnMe" class="profile-card_chat" style="overflow: scroll; display: none;">
                    <p class="display-1 text-center" style="font-family: 'Lobster', cursive;">People crushing you</p>
                    <div class="py-5">
                        <div class="container">
                            <ul class="list-group font-weight-bold">
                                <li class="list-group-item">
                                    <div>
                                        <div class="row">
                                            <div class="col-2">
                                                Avatar
                                            </div>
                                            <div class="col-3">
                                                Name
                                            </div>
                                            <div class="col-2">
                                                Gender
                                            </div>
                                            <div class="col-1">
                                                Age
                                            </div>
                                            <div class="col-2">
                                                Action
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <c:forEach var="t" items="${targets}">
                                    <li id="list-group-item-${t.uid}" class="list-group-item">
                                        <div class="row">
                                            <div class="col-2">
                                                <img src="<%=usr.getAvatar()%>" style="width: 50px; height: 50px;">
                                            </div>
                                            <div class="col-3">
                                                ${t.fullName}
                                            </div>
                                            <div class="col-2">
                                                ${t.gender}
                                            </div>
                                            <div class="col-1">
                                                ${t.age}
                                            </div>
                                            <div class="col-4 text-right text-light">
                                                <a onclick="crush(${cookie.uid.value},${t.uid});
                                                        remove(${t.uid})" class="btn profile-card__buttonMenu button--orange">Crush</a>
                                                <a onclick="uncrush(${t.uid},${cookie.uid.value});
                                                        remove(${t.uid})" class="btn profile-card__buttonMenu button--gray text-dark">Remove</a>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-------- Chat ------>
                <div id="chat" class="profile-card_chat" style="display: none;">
                    <div class="messages">
                        <c:forEach var="fri" items="${friends}">
                            <ul id="chat-box-${fri.key}" style="display: none"></ul>
                        </c:forEach>
                    </div>
                    <div class="message-input">
                        <div class="wrap">
                            <form id="messageForm" name="messageForm" nameForm="messageForm">
                                <div class="input-group">
                                    <input type="text" id="message" placeholder="Write your message..." class="form-control width100"  style="font-size: 25px;"/>
                                    <span class="">
                                        <button type="submit" class="m-0"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="js/chat.js"></script>
    <script>
                                                    //ADD FID & UInfo TO LIST
                                                    let list = {};
        <c:forEach var="fri" items="${friends}">
                                                    list[${fri.key}] = [${fri.value.uid}, "${fri.value.avatar}"];
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
</div>
<%--Chat Area--%>
</html>