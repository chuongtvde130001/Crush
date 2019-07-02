<%@ page import="model.User" %>
<%@ page import="dao.FriendDAO" %>
<%@ page import="servlet.ServletListener" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User usr = (User) session.getAttribute("user");
    if (usr == null) {
        response.sendRedirect("register.jsp");
    }
    //Clear MessageStorage
    ServletListener.getMesStorage().clearMessage(usr.getUid());
    //Get All Friends
    request.setAttribute("friends", FriendDAO.getFriends(((User) session.getAttribute("user")).getUid()));
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Find my Crush</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/tinder.png" />
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/styles-chat.css">
        <!--        <link rel="stylesheet" href="css/chat-box-style.css">-->
        <link rel="stylesheet" href="css/home-style.css">
        <link rel="stylesheet" href="css/emojionearea.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.all.min.js"></script>
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
                </div><br>
                <div class="container">
                    <div class="btn-group-vertical btn-block" style="width: 100%;">
                        <a href="ProcessLogout" class="btn btn-danger">Logout</a>
                        <a class="btn btn-secondary">Profile</a>
                        <a href="" onclick="findCrush()" class="btn btn-success">Find my Crush</a>
                        <a href="" class="btn btn-primary">Who Crush Me?</a>
                    </div>
                </div> <br>           
                <script>
                    $("#btn-chat").click(function () {
                        $(".find-crush").hide();
                        $(".messages").show();
                        $(".message-input").show();
                        $(".contact-profile").show();
                    });
                    $("#btn-logout").click(function () {
                        window.location.href = 'ProcessLogout';
                    })
                    $("#btn-profile").click(function () {
                        window.location.href = 'update_info.jsp';
                    })
                    $("#btn-find-crush").click(function () {
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
                            <li class="contact" onclick="showCrush()">
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
                            if (curChatActive == fid)
                                return;

                            let name = $(this).find(".name")[0].innerText;
                            let avatar = $(this).find(".avatar")[0].src;

                            $(".find-crush").hide();
                            $(".messages").show();
                            $(".message-input").show();
                            $(".contact-profile").show();

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
                            //Update chat engine
                            reconfigChat(fid);
                            $("#ct_last_" + fid).addClass('read').removeClass('unread')
                        });
                    }
                </script>
            </div>
            <div class="content" style="background-image: linear-gradient(to right top, #051937, #004d7a, #008793, #00bf72, #a8eb12);">
                <div id="findCrush" class="profile-card">
                    <!-------- Crush ------>
                    <div class="profile-card__img">
                        <img src="<%=usr.getAvatar()%>" alt="profile card">
                    </div>
                    <div class="profile-card__cnt js-profile-cnt">
                        <div class="profile-card__name display-2"><%=usr.getFullName()%></div>
                        <div class="profile-card__txt">Looking for <strong>Girlfriends</strong></div>
                        <div class="profile-card-inf">
                            <div class="profile-card-inf__item">
                                <div class="profile-card-inf__title">Age</div>
                                <div class="profile-card-inf__txt"><%=usr.getAge()%></div>
                            </div>

                            <div class="profile-card-inf__item">
                                <div class="profile-card-inf__title">Gender</div>
                                <div class="profile-card-inf__txt"><%=usr.getGender()%></div>
                            </div>

                            <div class="profile-card-inf__item">
                                <div class="profile-card-inf__title">Email</div>
                                <div class="profile-card-inf__txt"><%=usr.getEmail()%></div>
                            </div>
                        </div>
                        <div class="profile-card-ctr">
                            <input onclick="change()" type="button" value="Crush" id="crush" class="profile-card__button button--orange"></input>
                            <input onclick="" type="button" value="Pass" id="crush" class="profile-card__button button--blue"></input>
                        </div>
                        <script>
                            function change() {
                                var x = document.getElementById('crush');
                                if (x.value === 'Crush') {
                                    Swal.fire({
                                        title: 'You are crushing <%=usr.getFullName()%>',
                                        width: 600,
                                        padding: '3em',
                                        backdrop: `
                                            rgba(0,0,123,0.4)
                                            url("img/heart.gif")
                                            center top
                                            no-repeat
`
                                    })
                                    x.value = "Uncrush";
                                } else if (x.value === 'Uncrush') {
                                    Swal.fire({
                                        title: 'Are you sure to uncrush <%=usr.getFullName()%>?',
                                        type: 'warning',
                                        showCancelButton: true,
                                        confirmButtonColor: '#3085d6',
                                        cancelButtonColor: '#d33',
                                        confirmButtonText: 'Yes, uncrush!'
                                    }).then((result) => {
                                        if (result.value) {
                                            Swal.fire(
                                                    'You and <%=usr.getFullName()%> are no longer friends!',
                                                    'success'
                                                    )
                                            x.value = "Crush";
                                        }
                                    })
                                }
                            }
                        </script>

                    </div>
                </div>
                <!-------- Chat ------>
                <div id="chat" class="profile-card_chat" style="display: none;">
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
        <script>
            function showCrush() {
                var x = document.getElementById("chat");
                var y = document.getElementById("findCrush");
                if (x.style.display === "none") {
                    x.style.display = "block";
                    y.style.display = "none";
                }
            }
            function findCrush() {
                var x = document.getElementById("chat");
                var y = document.getElementById("findCrush");
                y.style.display = "block";
                x.style.display = "none";
            }
        </script>
    </body>
    <script type="text/javascript" src="js/chat.js"></script>
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