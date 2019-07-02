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
        <link rel="stylesheet" href="css/home-style.css">
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
                </div><br>
                <div class="container text-center">
                    <div class="button-group">
                        <a href="ProcessLogout" class="profile-card__buttonLeft button--orange">Logout</a>
                        <a class="profile-card__buttonLeft button--purple">Profile</a><br>
                        <a href="find_crush.jsp" class="profile-card__buttonLeft button--blueGreen">Find my Crush</a>
                        <a href="" class="profile-card__buttonLeft button--green">Who Crush Me?</a>      
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
            <div class="content">
                <diprofile-card-formv class="wrapper">
                    <div class="profile-card js-profile-card">
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
                                <button class="profile-card__button button--orange js-message-btn">Crush</button>
                                <button class="profile-card__button button--blue ">Pass</button>

                            </div>
                        </div>

                        <div class="profile-card-message js-message">
                            <form class="profile-card-form">
                                <div class="profile-card-form__container">
                                    <textarea placeholder="Say something..."></textarea>
                                </div>

                                <div class="profile-card-form__bottom">
                                    <button class="profile-card__button button--orange js-message-close">
                                        Crush!
                                    </button>
                                    <button class="profile-card__button button--gray js-message-close">
                                        Cancel
                                    </button>
                                </div>
                            </form>

                            <div class="profile-card__overlay js-message-close"></div>
                        </div>
                    </div>
            </div>
            <script src="js/home-js.js"></script>               
</html>