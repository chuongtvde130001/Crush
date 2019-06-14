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
                <a href="home.html" class="navbar-brand">
                    <button class="btn btn-danger">Back To Home Page</button>
                </a>
                </button>
            </div>
        </nav>
        <!-------- Login page ------------->
        <header id="home-section-login">
            <div class="dark-overlay">
                <div class="home-inner">
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-">
                                <div class="card bg-danger text-center card-form">
                                    <div class="card-body">
                                        <h3>Login Your Account</h3>
                                        <form action="ProcessLogin" method="post">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-lg" placeholder="Username" name="username">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-lg" placeholder="Password" name="password">
                                            </div>

                                            <button type="submit" class="btn btn-primary btn-block">Login</button>
                                            <p class="mt-2">Dont have account?</p>
                                            <button class="btn btn-dark btn-block"><a href="home.html" class="text-white">Register Now</a></button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
        </header>
        <!---------Footer ----------------->
        <footer id="main-footer" class="bg-dark">
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <div class="py-4">
                            <h1 class="h3">Crush Team</h1>
                            <p>Copyright &copy; 2019</p>
                            <button class="btn btn-danger" data-toggle="modal" data-target="#contactModal">Contact Us</button>
                        </div>
                    </div>
                </div>
            </div>
        </footer>
        <div class="modal fade text-dark" id="contactModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="contactModalTitle">Contact Us</h5>
                        <button class="close" data-dismiss="modal"><span>&times;</span></button>
                    </div>
                    <div class="modal-body">
                        <form>
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="text" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="name">Message</label>
                                <textarea class="form-control"></textarea>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-danger btn-block">Submit</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>
