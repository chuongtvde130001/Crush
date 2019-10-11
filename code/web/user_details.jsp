<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <header>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>User Details</title>
        <link rel="shortcut icon" type="image/x-icon" href="img/favicon.png"/>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link href="https://fonts.googleapis.com/css?family=Courgette|Lobster|Pacifico&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/emojionearea.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.min.css">
        <link rel="stylesheet" href="css/noti.css">
        <link rel="stylesheet" href="css/admin.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.all.min.js"></script>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/emojionearea.min.js"></script>
        <script src="js/contact.js"></script>
        <script src="js/want.js"></script>
        <script src="js/noti.js"></script>
    </header>
    <body>
        <%
            User u = (User) session.getAttribute("user");
            if (u == null) {
                response.sendRedirect("login.jsp");
            } else if (u.getUserRight() != 1) {
                response.sendRedirect("home.jsp");
            }
        %> 
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-3 fixed-top">
            <div class="container">
                <a class="navbar-brand" href="#">Crush</a>
                <ul class="navbar-nav">
                    <p class="nav-item text-light m-auto">You are now loggin as Administrator</p>
                </ul>
            </div>
        </nav><br>
        <div style="height: 5%;"></div>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-md-4 col-lg-3 col-xl-3">
                    <ul class="list-group mb-5">
                        <div class="list-group-item">Control Panel</div>
                        <a class="list-group-item list-group-item-action" href="update_info.jsp"><i class="fa fa-user" aria-hidden="true"> Profile</i></a>
                        <a class="list-group-item list-group-item-action" href="#"><i class="fa fa-comments" aria-hidden="true"> Noftication</i></a>
                        <a class="list-group-item list-group-item-action" href="#"><i class="fa fa-flag" aria-hidden="true"> Report Queqe</i></a>
                        <a class="list-group-item list-group-item-action" href="ProcessLogout"><i class="fa fa-sign-out" aria-hidden="true"> Logout</i></a>
                    </ul>
                </div>
                <div class="col-sm-6 col-md-8 col-lg-9 col-xl-9">                                        
                    <div class="card text-dark" id="details">
                        <div class="card-header text-light bg-info">${requestScope.userDetails.fullName}</div>
                        <div class="card-body">              
                            <div class="card w-50 m-auto">
                                <img class="card-img-top" src="${requestScope.userDetails.avatar}" alt="Card image cap">
                                <div class="card-body text-center">
                                    <div>
                                        <label>Full Name : ${requestScope.userDetails.fullName}</label><br>
                                        <label>Age : ${requestScope.userDetails.age}</label><br>
                                        <label>Gender : ${requestScope.userDetails.gender}</label><br>
                                    </div>
                                    <form action="ProcessBanUser" method="get">
                                        <label>Enter Duration Ban:</label>
                                        <div class="input-group mb-3">
                                            <input type="number" class="form-control" placeholder="Enter days" name="duration" min="1">
                                            <span class="input-group-addon">days</span>
                                        </div>
                                        <input hidden type="text" name="id" value="<%= request.getParameter("id")%>">
                                        <input class="btn btn-danger btn-block" type="submit" value="BAN">
                                    </form>
                                    <a class="btn btn-primary btn-block text-white" href="ProcessUnbanUser?id=<%= request.getParameter("id")%>">UNBAN</a><br>
                                    ${requestScope.MSG}
                                </div>
                            </div><br>         
                        </div>         
                    </div> 
                </div>
            </div>   
        </div>
    </body>
</html>