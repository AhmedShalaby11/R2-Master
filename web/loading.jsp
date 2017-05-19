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
	<link rel="stylesheet" href="./css/animate.css" /><link rel='stylesheet' href='./css/font-awesome.min.css'/><link href='http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=helvetica&subset=latin,latin-ext' rel='stylesheet' type='text/css'>
    
	<script src="./js/jquery-2.1.0.js"></script>
	<script src="./js/bootstrap.js"></script>
	<script src="./js/blocs.js"></script>
    <title>loading</title>
<script>
$(document).ready(function(){
	setTimeout( function(){ 
window.location.replace("viewdata.jsp");

  }  , 8000 );
});
</script>
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
						<a href="viewdata.jsp">viewData</a>
					</li>
				</ul>
			</div>
		</nav>
	</div>
</div>
<!-- bloc-0 END -->

<!-- bloc-2 -->
<div class="bloc bgc-white l-bloc" id="bloc-2">
	<div class="container bloc-md">
		<div class="row animated bounceInLeft">
			<div class="col-sm-5 col-sm-6">
				<h2 class="mg-lg device-bloc-text-vc">
					Did you know?
				</h2>
				<p class="mg-lg ">
					Robust is 20x time saver than the manual work! adding ,it keeps data safe!
				</p>
			</div>
			<div class="col-sm-6">
				
					<h3>Processing your request!</h3>
<div class="progress">

  <div class="progress-bar  progress-bar-danger progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width: 100%">

  </div>
</div>
					
				</div>
			</div>
		</div>
	</div>
	
</div>
<!-- bloc-2 END -->

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
