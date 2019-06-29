<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Welcome ${sessionScope.user.userName}</title>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/style.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.min.css">
    </head>
    <body id="home">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@8.13.0/dist/sweetalert2.all.min.js"></script> 
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
                                <form method="post" action="ProcessUpdate" enctype="multipart/form-data" onsubmit="return checkForm(this);">
                                    <div class="form-row">
                                        <div class="col">
                                            <h4>Full Name :</h4>
                                            <input id="fullName" type="text" class="form-control" placeholder="Your Full Name" name="fullName">
                                        </div>
                                        <div class="col">
                                            <h4>Age:</h4>
                                            <input id="age" type="number" class="form-control" placeholder="How old are you?" name="age">
                                        </div>
                                    </div><br>
                                    <div class="form-row">
                                        <div class="col">
                                            <h4>I want to meet :</h4>
                                            <select id="genderMeet" class="form-control">
                                                <option>Male</option>
                                                <option>Female</option>
                                                <option>Other</option>
                                            </select>
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
                                            <h3>Select Your Avatar</h3>
                                            <div class="col"><label for="file">File input</label>
                                                <input type="file" name="myImage" accept="image/x-png,image/gif,image/jpeg" />
                                            </div>
                                        </div><br>
                                        <br>
                                    </div><br>
                                    <button type="submit" class="btn btn-primary btn-block">Take me to the home page !</button>
                                    <script>
                                        var personNameRegex = /^([a-zA-Z]+[\'\,\.\-]?[a-zA-Z ]*)+[ ]([a-zA-Z]+[\'\,\.\-]?[a-zA-Z ]+)+$/;
                                        var fullName = document.getElementById('fullName');
                                        var age = document.getElementById('age');
                                        function checkForm(form) {
                                            if ((fullName.value == "") && (age.value == "")) {
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
