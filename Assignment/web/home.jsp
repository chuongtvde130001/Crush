<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Crush</title>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/style.css">
    </head>
    <body id="home">
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
            <div class="container">
                <a href="home.html" class="navbar-brand">Crush</a>
                <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a href="#home" class="nav-link">Home</a>
                        </li>
                        <li class="nav-item">
                            <a href="login.jsp" class="nav-link">Login</a>
                        </li>
                        <li class="nav-item">
                            <a href="#explore-head-section" class="nav-link">About Us</a>
                        </li>
                        <li class="nav-item">
                            <a href="#share-head-section" class="nav-link">How We Work?</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- HOME SECTION -->
        <header id="home-section">
            <div class="dark-overlay">
                <div class="home-inner">
                    <div class="container">
                        <div class="row">
                            <div class="col-lg-8 d-none d-lg-block">
                                <h1 class="display-4">Find <strong>your true love</strong></h1>
                                <div class="d-flex flex-row">
                                    <div class="p-4 align-self-start">
                                        <i class="fa fa-check"></i>
                                    </div>
                                    <div class="p-4 align-self-end">
                                        <p class="display-4">Easy To Use</p>
                                    </div>
                                </div>

                                <div class="d-flex flex-row">
                                    <div class="p-4 align-self-start">
                                        <i class="fa fa-check"></i>
                                    </div>
                                    <div class="p-4 align-self-end">
                                        <p class="display-4">Safe</p>
                                    </div>
                                </div>

                                <div class="d-flex flex-row">
                                    <div class="p-4 align-self-start">
                                        <i class="fa fa-check"></i>
                                    </div>
                                    <div class="p-4 align-self-end">
                                        <p class="display-4">Private</p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <div class="card bg-danger text-center card-form">
                                    <div class="card-body">
                                        <h3>Sign Up Today</h3>
                                        <p>Please fill out this form to register</p>
                                        <form action="ProcessRegister" method="post">
                                            <div class="form-group">
                                                <input type="text" class="form-control form-control-lg" placeholder="Username" name="username">
                                            </div>
                                            <div class="form-group">
                                                <input type="email" class="form-control form-control-lg" placeholder="Email" name="email">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-lg" placeholder="Password" name="password">
                                            </div>
                                            <div class="form-group">
                                                <input type="password" class="form-control form-control-lg" placeholder="Confirm Password" name="confirmPassword">
                                            </div>
                                            <input type="submit" class="btn btn-outline-light btn-block" value="Register">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </header>

        <!-- ABOUT HEAD -->
        <section id="explore-head-section">
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <div class="py-5">
                            <h1 class="display-4">About Us</h1>
                            <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Incidunt sed, magni, veritatis aliquam sequi eveniet? Rerum quia accusantium alias provident perferendis</p>
                            <a href="#" class="btn btn-outline-secondary">More information</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- HOW.. HEAD -->
        <section id="share-head-section" class="bg-danger">
            <div class="container">
                <div class="row">
                    <div class="col text-center">
                        <div class="py-5">
                            <h1 class="display-4">How We Work?</h1>
                            <p class="lead">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Incidunt sed, magni, veritatis aliquam sequi eveniet? Rerum quia accusantium alias provident perferendis</p>
                            <a href="#" class="btn btn-outline-light">Find Out More</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- MAIN FOOTER -->
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

        <!-- CONTACT MODAL -->
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
