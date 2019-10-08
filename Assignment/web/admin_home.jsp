<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <header>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Admistrator</title>
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
    <%
        User u = (User) session.getAttribute("user");
        if (u == null) {
            response.sendRedirect("login.jsp");
        } else if (u.getUserRight() != 1) {
            response.sendRedirect("home.jsp");
        }
    %>  
    <body>
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
                        <a class="list-group-item list-group-item-action" href="home.jsp"><i class="fa fa-home" aria-hidden="true"> Home</i></a>
                        <a class="list-group-item list-group-item-action" href="update_info.jsp"><i class="fa fa-user" aria-hidden="true"> Profile</i></a>
                        <a class="list-group-item list-group-item-action" href="#"><i class="fa fa-flag" aria-hidden="true"> Report Queqe</i></a>
                        <a class="list-group-item list-group-item-action" href="ProcessLogout"><i class="fa fa-sign-out" aria-hidden="true"> Logout</i></a>
                    </ul>
                </div>
                <div class="col-sm-6 col-md-8 col-lg-9 col-xl-9">                                        
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Search Users</h4>
                            <form action="ProcessSearchUsers" method="POST">
                                <div class="form-group row"> 
                                    <div class="col-md-5" >
                                        <div class="input-group">
                                            <span class="input-group-addon">User </span>
                                            <input required type="text" class="form-control" name="type" value="" placeholder="Type something here...">
                                        </div>
                                    </div>
                                    <div class="col-md-4" > 
                                        <div class="input-group">
                                            <span class="input-group-addon">Search By </span>
                                            <select class="form-control" id="selector" name="selector">
                                                <option>Username</option>
                                                <option>Full Name</option>
                                                <option>Email</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-3">
                                        <input type="submit" value="Search" class="btn btn-success">
                                    </div>
                                </div>
                            </form>

                            <table class="table table-hover">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Avatar</th>
                                        <th scope="col">Username</th>
                                        <th scope="col">FullName</th>
                                        <th scope="col">Gender</th>
                                        <th scope="col">Email</th>
                                        <th scope="col">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="uList" items="${requestScope.uList}">
                                        <tr>
                                            <th scope="row">${uList.key}</th>
                                            <td><img src="${uList.value.avatar}" style="width:  30px; height: 30px;"></td>
                                            <td>${uList.value.userName}</td>
                                            <td>${uList.value.fullName}</td>
                                            <td>${uList.value.gender}</td>
                                            <td>${uList.value.email}</td>
                                            <td>
                                                <a class="view-details btn btn-success btn-block" href="ProcessViewUser?id=${uList.key}">Details</a>
                                            </td>
                                        </tr>                                      
                                    </c:forEach>                                  
                                </tbody>
                            </table>
                            <div class="d-flex justify-content-center">
                                <nav aria-label="Page navigation example">
                                    <ul class="pagination">
                                        <c:forEach begin="1" end="${applicationScope.totalUsers/2}" varStatus="loop">
                                            <li class="page-item"><a class="page-link" href="ProcessView?pageid=${loop.index}">${loop.index}</a></li>
                                        </c:forEach>                                                                        
                                    </ul>
                                </nav>
                            </div>
                            ${requestScope.MSG}
                        </div>
                    </div>
                </div>
            </div>   
        </div>
    </body>
</html>