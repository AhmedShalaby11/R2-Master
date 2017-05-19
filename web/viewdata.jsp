<!doctype html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="keywords" content="">
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=0">
        <link rel="shortcut icon" type="image/png" href="favicon.png" />
        <script src="./js/jquery-2.1.0.js"></script>
        <script type='text/javascript' src='https://cdn.datatables.net/v/dt/dt-1.10.13/af-2.1.3/b-1.2.4/b-colvis-1.2.4/b-html5-1.2.4/cr-1.3.2/r-2.1.0/sc-1.4.2/se-1.2.0/datatables.min.js'></script>
        <link rel='stylesheet' type='text/css' href='https://cdn.datatables.net/v/dt/dt-1.10.13/af-2.1.3/b-1.2.4/b-colvis-1.2.4/b-html5-1.2.4/cr-1.3.2/r-2.1.0/sc-1.4.2/se-1.2.0/datatables.min.css'/>





        <link rel="stylesheet" type="text/css" href="./css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="style.css"><link rel='stylesheet' href='./css/font-awesome.min.css'/><link href='http://fonts.googleapis.com/css?family=helvetica&subset=latin,latin-ext' rel='stylesheet' type='text/css'>


        <script src="./js/bootstrap.js"></script>
        <script src="./js/blocs.js"></script>
        <title>Robust</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


        <title>View Data</title>

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
                        <div class="collapse navbar-collapse navbar-1">
                            <ul class="site-navigation nav navbar-nav">
                                <li>
                                    <a href="index.jsp">MSAN OPS</a>
                                </li>
                                <li>
                                    <a href="trends.jsp">TRENDS</a>
                                </li>
                                <li>
                                    <a href="viewdata.jsp">View Data</a>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
            <!-- bloc-0 END -->

            <!-- bloc-4 -->
            <div class="bloc bgc-white l-bloc" id="bloc-4">
                <div class="container bloc-lg">
                    <div class="row">
                        <div class="col-sm-12">

                            <div id="DSLAM">
                            </div>
                            <hr>
                            <script>
                                $(document).ready(function () {


                                    $.get("readCabinetsCSV", function (response) {
                                        $("#DSLAM").append(response);
                                        //this is so important for runtime.
                                        $("#_msanTables").dataTable();
                                    });
                                });

                            </script>



                        </div>   
                    </div>

                </div>
            </div>
        </div>
        <!-- bloc-4 END -->

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
