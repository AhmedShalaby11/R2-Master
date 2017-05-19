<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
        <link rel="shortcut icon" type="image/png" href="favicon.png" />

        <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="style.css">
        <link rel="stylesheet" href="./css/animate.css" /> <link rel="stylesheet" type="text/css" href="./css/styleSheet.css"></script><link rel='stylesheet' href='./css/font-awesome.min.css'/><link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=helvetica&subset=latin,latin-ext' rel='stylesheet' type='text/css'>

    <script src="./js/jquery-2.1.0.js"></script>
    <script src="./js/bootstrap.js"></script>
    <script src="./js/blocs.js"></script><script src="./js/javaScript.js"></script>
    <script src='http://canvasjs.com/assets/script/canvasjs.min.js'></script>
    <title>trends</title>
    <style>

        </style>
</head>
<body>
    <!-- Main container -->
    <div class="page-container">

        <!-- bloc-0 -->
        <div class="bloc bgc-white l-bloc" id="bloc-0">
            <div class="container bloc-md">
                <nav class="navbar row">
                    <div class="navbar-header">
                        <a class="navbar-brand" href="index.jsp"><img src="img/SF28686LOGO-b.jpeg" width="120" height="41" /></a>
                        <button id="nav-toggle" type="button" class="ui-navbar-toggle navbar-toggle" data-toggle="collapse" data-target=".navbar-1">
                            <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span>
                        </button>
                    </div>
                    
                    <div class="collapse navbar-collapse navbar">
                        <ul class="site-navigation nav navbar-nav" >
                            <li>
                                <a style="color:black" href="index.jsp">MSAN OPS</a>
                            </li>
                            <li>
                                <a style="color:black" href="trends.jsp">TRENDS</a>
                            </li>
                            <li>
                                <a style="color:black" href="viewdata.jsp">View Data</a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>
        </div>
        <!-- bloc-0 END -->

        <!-- bloc-3 -->
        <div class="bloc bgc-white l-bloc" id="bloc-3">
            <div class="container bloc-lg">
                  <div id ="charts" class="col-sm-8" style="margin-right:100%;">
                            <script>
                                $(document).ready(function () {
                                    $("#chart1").click(function () {
                                        $(".canvasjs-chart-canvas").remove();
                                       
                                        $.get("chartTransactions", function (response) {


                                            $("#charts").append(response);
                                            $("#closePanel").click();
                                            getChart();
                                            $('.canvasjs-chart-credit').text('Integration Management Reporting');
                                            $('.canvasjs-chart-credit').css({'color': 'red', 'font-size': 'large'});


                                        });
                                    });
                                    $("#chart2").click(function () {
                                        var query = "select log_category,log_message,count(log_message) from integration.i_log where log_message in ('connection closed','connected to Oracle database :ADSL PRO PRD') group by log_category,log_message";
                                        var chartType = "column";
                                        var chartTitle = "This is Ajax Test";
                                        $.post("chartOpenedClosed", {query: query, chart: chartType, title: chartTitle}, function (response) {

                                            $("#charts").append(response);
                                            $("#closePanel").click();

                                            getChart();
                                            $('.canvasjs-chart-credit').text('Integration Management Reporting');
                                            $('.canvasjs-chart-credit').css({'color': 'red', 'font-size': 'large'});

                                        });
                                    });
                                });




                            </script>
                        </div>
                <div class="row">
                    
                    <div class="col-sm-3">
                        
                       <!--Charts-->
                      
                      
                        
                        
                       <div id="wrapper">

                            <div class="overlay"></div>
                            <!-- Sidebar -->
                            <nav class="navbar navbar-inverse navbar-fixed-top" id="sidebar-wrapper" role="navigation">
                                <ul class="nav sidebar-nav">
                                    <li class="sidebar-brand">
                                        <a href="#">
                                            What Else?
                                        </a>
                                    </li>
                                    <li>
                                        <a id="chart1" class="w3-bar-item w3-button">Application Transactions <i class="fa fa-pie-chart" aria-hidden="true"></i></a>
                                    </li>
                                    <li>
                                        <a id="chart2">Connections <i class="fa fa-area-chart" aria-hidden="true"></i></a>
                                    </li>
                                  
<!--                                    <li class="dropdown">
                                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">drop <span class="caret"></span></a>
                                        <ul class="dropdown-menu" role="menu">
                                            <li class="dropdown-header">Dropdown heading</li>
                                            <li><a href="#">Action</a></li>
                                            <li><a href="#">Another action</a></li>
                                            <li><a href="#">Something else here</a></li>
                                            <li><a href="#">Separated link</a></li>
                                            <li><a href="#">One more separated link</a></li>
                                        </ul>
                                    </li>-->
<!--                                    <li>
                                        <a href="#">Services</a>
                                    </li>
                                    <li>
                                        <a href="#">Contact</a>
                                    </li>-->

                                </ul>
                            </nav>
                            <!-- /#sidebar-wrapper -->

                            <!-- Page Content -->
                        </div>

                    </div>
                    <div class="col-sm-6">
<div id="page-content-wrapper" >
                            <button id="closePanel"  style="margin-top:200px;" type="button" class="hamburger is-closed" data-toggle="offcanvas">
                                <span class="hamb-top"></span>
                                <span class="hamb-middle"></span>
                                <span class="hamb-bottom"></span>
                                
                            </button>
                        </div>
                        

                    </div>
                    
                </div>
            
            </div>
           
        </div>

    </div>
    
    

<!-- bloc-3 END -->

<!-- ScrollToTop Button -->
<a class="bloc-button btn btn-d scrollToTop" onclick="scrollToTarget('1')"><span class="fa fa-chevron-up"></span></a>
<!-- ScrollToTop Button END-->


<!-- Footer - bloc-5 -->
<div class="bloc build-with-blocs-bloc bgc-1 " id="bloc-5">
    <div class="container bloc-md">
        <div class="row">
            <div class="col-sm-12 text-center">
                <a href="http://blocsapp.com/" target="_blank"><img src="img/favicon.png" class="img-responsive center-block mg-sm" height="38" width="38" /></a>
                <p class="text-center">
                    Powered by Integration Management Team
                </p>
            </div>
        </div>
    </div>
</div>
<!-- Footer - bloc-5 END -->
</div>
<!-- Main container END -->


</body>

<!-- Google Analytics -->

<!-- Google Analytics END -->

</html>
