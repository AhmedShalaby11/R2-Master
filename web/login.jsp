<!doctype html>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
	

<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
        <link rel="shortcut icon" type="image/png" href="favicon.png" />

        <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel='stylesheet' href='./css/font-awesome.min.css'/>
        <link href='http://fonts.googleapis.com/css?family=helvetica&subset=latin,latin-ext' rel='stylesheet' type='text/css'>

        <script src="./js/jquery-2.1.0.js"></script>
        <script src="./js/bootstrap.js"></script>
        <script src="./js/blocs.js"></script>
        <script src="./js/jqBootstrapValidation.js"></script>
        <script src="./js/formHandler.js"></script>
        <title>Home</title>
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body style="background-image: url(img/Vm0wNWEx.jpg);background-repeat: no-repeat;background-position: top;background-color: rgba(0, 0, 0, .15)">
        <!-- Main container -->
        <div class="page-container">

            <!-- bloc-1 -->
            <div class="bloc bloc-fill-screen bg-Vm0wNWEx bg-r-edge bgc-gainsboro l-bloc" id="bloc-1">
                <div class="container fill-bloc-top-edge">
                    <nav class="navbar row">
                        <div class="navbar-header">
                            <a class="navbar-brand" href="index.jsp"><img src="img/SF28686LOGO-b.jpeg" width="120" height="41" /></a>
                            <button id="nav-toggle" type="button" class="ui-navbar-toggle navbar-toggle" data-toggle="collapse" data-target=".navbar-1">
                                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                            </button>
                        </div>
                        <div class="collapse navbar-collapse navbar-1">
                        </div>
                    </nav>
                </div>
                <div class="container">
                    <div class="row">
                        <div class="col-sm-5">
                            <h1>Beta version</h1>

                            <div class="w3-container w3-darkgrey">
                                <h2>Login</h2>
                            </div>
                            <%--<jsp:useBean id="obj" class="integratorPackage.checkUserDetails" scope="session"/>--%>

             
                            <form class="w3-container w3-animate-left" action="login">
                                <label class="w3-text-black"><b>Username</b></label>
                                <input class="w3-input w3-border w3-light-grey w3-round-xxlarge" name="input_username" type="text">

                                <label class="w3-text-black"><b>Password</b></label>
                                <input class="w3-input w3-border w3-light-grey w3-round-xxlarge" name="input_password" type="password">

                                <button type="submit"  class="w3-btn w3-block w3-gray w3-round-xxlarge">Login</button>
                            </form>
                            <!--check sql 	-->


                           




                        </div>
                    </div>
                </div>
                <div class="container fill-bloc-bottom-edge">
                    <div class="row">
                        <div class="col-sm-12">
                            <!--<a id="scroll-hero" class="blocs-hero-btn-dwn" href="#"><span class="fa fa-chevron-down"></span></a>-->
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <!-- bloc-1 END -->

        <!-- bloc-2 -->
        <div  class="bloc build-with-blocs-bloc bgc-4698" style="margin-top: -200px" id="bloc-2">
            <div class="container bloc-md">
                <div class="row">
                    <div class="col-sm-12 text-center">
                        <a href="#" data-lightbox="img/favicon.png" target="_blank"><img src="img/favicon.png" class="center-block mg-sm" height="103" width="138" /></a>
                        <p class="text-center">
                            Powered by Vodafone Egypt Integration Management
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- bloc-2 END -->

        <!-- ScrollToTop Button -->
        <a class="bloc-button btn btn-d scrollToTop" onclick="scrollToTarget('1')"><span class="fa fa-chevron-up"></span></a>
        <!-- ScrollToTop Button END-->

    </div>
    <!-- Main container END -->


</body>

<!-- Google Analytics -->

<!-- Google Analytics END -->

</html>
