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
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png" />
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/styles-chat.css">
        <link rel="stylesheet" href="css/home-style.css">
        <link rel="stylesheet" href="css/profile-style.css">
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
                        $(".find-crushBut").hide();
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
                    $("#btn-find-crushBut").click(function () {
                        $(".find-crushBut").show();
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

                            $(".find-crushBut").hide();
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
                <div class="container emp-profile">
                    <div id="showInfo">
                        <form method="post">
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="profile-img">
                                        <img src="<%=usr.getAvatar()%>" alt=""/>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="profile-head">
                                        <h5>
                                            <%=usr.getFullName()%>
                                        </h5>
                                        <h6>
                                            Gender: Male
                                        </h6>
                                        <p class="proile-rating">Date join : <span>8/10/2019</span></p>
                                        <ul class="nav nav-tabs" id="myTab" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">About</a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-md-2">
                                    <a class="btn btn-secondary text-white" onclick="switchView()">Edit Profile</a>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-4">
                                    <div class="profile-work">
                                        <p>Details</p>
                                        <a href="">Friends</a><br/>
                                        <p>About</p>
                                        <a href="">Web Designer</a><br/>
                                        <a href="">Web Developer</a><br/>
                                        <a href="">WordPress</a><br/>
                                        <a href="">WooCommerce</a><br/>
                                        <a href="">PHP, .Net</a><br/>
                                    </div>
                                </div>
                                <div class="col-md-8">
                                    <div class="tab-content profile-tab" id="myTabContent">
                                        <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>User Id</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><%=usr.getUid()%></p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Name</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><%=usr.getFullName()%></p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Email</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><%=usr.getEmail()%></p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Age</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><%=usr.getAge()%></p>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Status</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <p><%=usr.getStatus()%></p>
                                                </div>
                                            </div>
                                        </div>                                
                                    </div>
                                </div>
                        </form>
                    </div>
                    <div id="updateInfo" style="display: none">
                        <form method="post" action="ProcessUpdate" enctype="multipart/form-data" onsubmit="return checkForm(this);">
                            <div class="form-row">
                                <div class="col">
                                    <h4>Full Name :</h4>
                                    <input value="<%=usr.getFullName()%>" id="fullName" type="text" class="form-control" placeholder="Your Full Name" name="fullName">
                                </div>
                                <div class="col">
                                    <h4>Age:</h4>
                                    <input value="<%=usr.getAge()%>" id="age" type="number" class="form-control" placeholder="How old are you?" name="age">
                                </div>
                            </div><br>
                            <div class="form-row">
                                <div class="col">
                                    <h4>Age range I want to meet :</h4>
                                    <div class="input-group">                                           
                                        <div class="input-group-addon">From</div>
                                        <input class="form-control col-3" type="number" id="ageFrom">
                                        <div class="input-group-addon">To</div>
                                        <input class="form-control col-3" type="number" id="ageTo">
                                        <div class="input-group-addon">Gender</div>
                                        <select id="genderMeet" class="form-control" name="gender">
                                            <option>Male</option>
                                            <option>Female</option>
                                            <option>Other</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col">
                                    <h4>Gender :</h4>
                                    <select id="gender" class="form-control" name="gender">
                                        <option>Male</option>
                                        <option>Female</option>
                                        <option>Other</option>
                                    </select>
                                </div>
                            </div><br>
                            <div class="form-row"> 
                                <div class="col">
                                    <h3>Avatar</h3>
                                    <div class="col"><label for="file">File input</label>
                                        <input id="imageName" type="file" name="myImage" accept="image/x-png,image/gif,image/jpeg" onchange="readURL(this);"/>
                                        <div class="col ml-3">
                                            <script>
                                                function readURL(input) {
                                                    if (input.files && input.files[0]) {
                                                        var reader = new FileReader();

                                                        reader.onload = function (e) {
                                                            $('#blah')
                                                                    .attr('src', e.target.result)
                                                                    .width(150)
                                                                    .height(200);
                                                        };

                                                        reader.readAsDataURL(input.files[0]);
                                                    }

                                                }
                                            </script>
                                            <img id="blah" src="<%=usr.getAvatar()%>" onerror="this.src='img/avatar.png'" style=" width: 200px; height: 200px;object-fit: cover;object-position: center;" class="mt-2 rounded"/>
                                        </div>
                                    </div>
                                </div><br>
                                <br>
                                <div class="col">
                                    <h3>About me</h3>
                                    <textarea class="form-control" rows="5" id="comment"></textarea>
                                </div><br>
                            </div><br>
                            <div class="text-center"> 
                                <button type="submit" class="btn btn-success">Save</button>
                                <button type="submit" class="btn btn-secondary">Cancel</button>
                            </div>
                            <script>
                                var personNameRegex = /^([a-zA-Z]+[\'\,\.\-]?[a-zA-Z ]*)+[ ]([a-zA-Z]+[\'\,\.\-]?[a-zA-Z ]+)+$/;
                                var fullName = document.getElementById('fullName');
                                var age = document.getElementById('age');
                                var ageFrom = document.getElementById('ageFrom');
                                var ageTo = document.getElementById('ageTo');
                                function checkForm(form) {
                                    if ((fullName.value == "") && (age.value == "") && (ageFrom.value == "")
                                            && (ageTo.value == "")) {
                                        Swal.fire({
                                            type: 'warning',
                                            title: 'Please fill the box...',
                                        });
                                        return false;
                                    } else if (fullName.value == "") {
                                        Swal.fire({
                                            type: 'warning',
                                            title: 'Full Name can not be empty!',
                                        });
                                        return false;
                                    } else if (age.value == "") {
                                        Swal.fire({
                                            type: 'warning',
                                            title: 'Age can not be empty!',
                                        });
                                        return false;
                                    } else if (ageFrom.value == "") {
                                        Swal.fire({
                                            type: 'warning',
                                            title: 'Age Range can not be empty!',
                                        });
                                        return false;
                                    } else if (ageTo.value == "") {
                                        Swal.fire({
                                            type: 'warning',
                                            title: 'Age Range can not be empty!',
                                        });
                                        return false;
                                    } else {
                                        if (!personNameRegex.test(fullName.value)) {
                                            Swal.fire({
                                                type: 'warning',
                                                title: 'Please type a valid Name...',
                                                text: 'Name can not includes numbers or too short!',
                                            });
                                            return false;
                                        }
                                        if (age.value < 18) {
                                            Swal.fire({
                                                type: 'warning',
                                                title: 'You are too young!',
                                            });
                                            return false;
                                        }
                                    }
                                    return true;
                                }
                            </script>
                        </form>
                        <script>
                            function switchView() {
                                var x = document.getElementById('showInfo');
                                var y = document.getElementById('updateInfo');
                                if (y.style.display === "none") {
                                    x.style.display = "none";
                                    y.style.display = "block";
                                }
                            }
                        </script>
                    </div>
                </div>
<<<<<<< HEAD
                <script src="js/want.js"></script>
                </html>
=======
            </div>
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
>>>>>>> a4470b324ac458b58d76861b2b49b4dced90c03c
