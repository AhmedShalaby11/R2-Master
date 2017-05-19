<%-- 
    Document   : Home
    Created on : May 11, 2017, 10:20:29 AM
    Author     : AShalaby11
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS | Home</title>
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <script src="js/bootstrap.js" type="text/javascript"></script>
        <link href="css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <script src="js/mainJS.js" type="text/javascript"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1">
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    </head>
    <body>
        <div class="vodafoneBrand "></div>
        <div id="mainDiv" class="w3-animate-right  welcomeDiv"><h1>Welcome to IMS,</h1>
            <button class="w3-btn w3-border w3-red" onclick="getStartPage()" >Get Started</button>

        </div>
 <script>
                                  $(document).ready(function(){
                                      $('#updatebutton').click(function(){
                                       $.get("NewServlet1",function(response){
                                          window.location.replace("loading.jsp");
                                          $('body').append(response);
                                       });
                                      });
                                  });
                                  </script>
    </body>
</html>
