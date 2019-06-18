<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Login Account</title>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body id="home">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
            <div class="container">
                <form action="ProcessLogout" method="get">
                    <button class="btn btn-danger" type="submit">Logout</button>
                    </button>
                </form>
            </div>
        </nav>
        <!-------- Login page ------------->
        <header id="home-section-update">
            <div class="home-inner">
                <div class="container">
                    <div class="row-1">
                        <p class="display-1 text-center">Hello ${sessionScope.user.userName}!</p>
                        <p class="display-4 text-center">Complete Your Profile</p>
                    </div>
                    <div class="row-5">
                        <div class="card bg-dark text-center card-form">
                            <div class="card-body text-left">
                                <h3>Your Info</h3>
                                <p>Please fill out this form to update your information</p>
                                <form>
                                    <div class="form-row">
                                        <div class="col">
                                            <h4>My Full Name :</h4>
                                            <input type="text" class="form-control" placeholder="Your Full Name" name="fullName">
                                        </div>
                                        <div class="col">
                                            <h4>Age:</h4>
                                            <input type="text" class="form-control" placeholder="How old are you?" name="age">
                                        </div>
                                    </div><br>
                                    <div class="form-row">
                                        <div class="col">
                                            <h4>I want to meet :</h4>
                                            <select id="gender" class="form-control">
                                                <option>Male</option>
                                                <option>Female</option>
                                            </select>
                                        </div>
                                        <div class="col">
                                            <h4>Gender :</h4>
                                            <select id="gender" class="form-control">
                                                <option>Male</option>
                                                <option>Female</option>
                                            </select>
                                        </div>
                                    </div><br>
                                    <div class="form-row">
                                        <div class="col">
                                            <h4>Age Range :</h4>
                                            <div class="form-row text-center">
                                                <div class="col">
                                                    <h3>From</h3>
                                                </div>
                                                <div class="col">
                                                    <input type="text" class="form-control" placeholder="Age">
                                                </div>
                                                <div class="col">
                                                    <h3>To</h3>
                                                </div>
                                                <div class="col">
                                                    <input type="text" class="form-control" placeholder="Age">
                                                </div>
                                            </div>
                                        </div> 
                                        <div class="col">
                                            <h3>Select Your Avatar</h3>
                                            <div class="col"><label for="file">File input</label>
                                                <form method="post" action="UploadFileServlet" enctype="multipart/form-data">
                                                    <input type="file" id="file" class="form-control-file">
                                                    <small id="fileHelp" class="form-text text-muted">Max 3mb size</small></div>
                                            </form>
                                        </div>
                                    </div><br>
                                    <br>
                                    <button type="submit" class="btn btn-primary btn-block">Take me to the home page !</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
