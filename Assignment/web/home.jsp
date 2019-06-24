<%@ page import="model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    User usr = (User) session.getAttribute("user");
//    if(usr == null) response.sendRedirect("register.jsp");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login Account</title>
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/styles-chat.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/popper.min.js"></script>
</head>
<body>
<div id="frame">
    <div id="sidepanel">
        <!----- Pofile của mình hiện ở đây ----->
        <div id="profile">
            <div class="wrap">
                <img id="profile-img" src="<%=usr.getAvatar()%>" class="online" alt="" />
                <p><%=usr.getFullName()%></p>
                <button class="btn btn-primary btn-sm ml-5">Logout</button>
            </div>
        </div>
        <!--- Khung tìm Kiếm ---->
        <div id="search">
            <label for=""><i class="fa fa-search" aria-hidden="true"></i></label>
            <input type="text" placeholder="Search..." />
        </div>
        <div class="container m-3">
            <div class="row">
                <button id="btn-logout" class="btn btn-sm btn-primary m-1">Logout</button>
                <button id="btn-profile" class="btn btn-sm btn-secondary m-1">Profile</button>
                <button id="btn-chat" class="btn btn-sm btn-danger m-1">Chat</button>
                <button id="btn-find-crush" class="btn btn-sm btn-success m-1">Find my Crush!</button>
            </div>
        </div>
        <script>
            $("#btn-chat").click(function() {
                $(".find-crush").hide();
                // $("#contacts").empty();
                $(".contact-profile").show();
                $(".messages").show();
                $(".message-input").show();
                $(".contact-profile").empty();
                // $(".messages").empty();
            });
            $("#btn-logout").click(function() {
                window.location.href='login.html';
            })
            $("#btn-profile").click(function() {
                window.location.href='updateinfo.html';
            })
            $("#btn-find-crush").click(function() {
                $("#contacts").empty();
                $(".find-crush").show();
                $(".contact-profile").hide();
                $(".messages").hide();
                $(".message-input").hide();
            });
        </script>
        <!--- Danh sách bạn bè ---->
        <div id="contacts">
            <ul>
                <!--- E viết code để đưa list friend lên theo mẫu div này nhé ---->
                <li class="contact">
                    <div class="wrap">
                        <span class="contact-status online"></span>  <!--- Trạng thái đang hoạt động ---->
                        <img src="http://emilcarlsson.se/assets/louislitt.png" alt="" />
                        <div class="meta">
                            <p class="name">Dương Đẹp Trai</p>
                            <p class="preview">Đẹp trai vl</p>
                        </div>
                    </div>
                </li>
                <li class="contact active">
                    <div class="wrap">
                        <span class="contact-status busy"></span><!--- Trạng thái đang bận ---->
                        <img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
                        <div class="meta">
                            <p class="name">Thúy Hằng</p>
                            <p class="preview">Thầy Lành Khó Tính VL</p>
                        </div>
                    </div>
                </li>
                <li class="contact">
                    <div class="wrap">
                        <span class="contact-status away"></span>
                        <img src="http://emilcarlsson.se/assets/rachelzane.png" alt="" />
                        <div class="meta">
                            <p class="name">Trần Văn Chương</p>
                            <p class="preview">Ố ồ, Bú cu cho 5 ngàn</p>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <script>
            var header = document.getElementById("contacts");
            var btns = header.getElementsByClassName("contact");
            for (var i = 0; i < btns.length; i++) {
                btns[i].addEventListener("click", function() {
                    var current = document.getElementsByClassName("active");
                    current[0].className = current[0].className.replace(" active", "");
                    this.className += " active";
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
        <div class="contact-profile"><!--- Thôn tin cá nhân của người chat--->
            <img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
            <p>Trần Văn Chương</p>
        </div>
        <div class="messages" id = "messages"><!--- Tin nhắn hội thoại ở đây--->
            <ul>
                <li class="sent">
                    <img src="http://emilcarlsson.se/assets/mikeross.png" alt="" />
                    <p>Bú cu không chương?</p>
                </li>
                <li class="replies">
                    <img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
                    <p>Oke 5k</p>
                </li>
                <li class="replies">
                    <img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
                    <p>oke..</p>
                </li>
                <li class="sent">
                    <img src="http://emilcarlsson.se/assets/harveyspecter.png" alt="" />
                    <p>Oke chốt 4.5k không bớt</p>
                </li>
            </ul>
        </div>
        <script>
            $(".contact").click(function() {
                $(".contact-profile").empty();
                $(".messages").empty();
            });
        </script>
        <!-------- Ta không biết mi có dùng form hay ko nếu dùng thêm vô đây ---->
        <div class="message-input">
            <div class="wrap">
                <form id="messageForm" onsubmit="function x(evt) {
                  evt.preventDefault();
                  alert('XX');
                }" name="messageForm" nameForm="messageForm">
                        <input type="text" id="message" placeholder="Write your message..." />
                    <button class="submit"><i class="fa fa-paper-plane" aria-hidden="true"></i></button>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
<script type="text/javascript" src="js/chat.js"></script>
</html>
