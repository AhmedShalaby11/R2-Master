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
	<script src="./js/jqBootstrapValidation.js"></script>
	<script src="./js/formHandler.js"></script>
    <title>Home</title>
<style>
.btn-danger
{
	margin-top:5px;
}
body {
    margin-top:40px;
}
.stepwizard-step p {
    margin-top: 10px;
}
.stepwizard-row {
    display: table-row;
}
.stepwizard {
    display: table;
    width: 50%;
    position: relative;
}
.stepwizard-step button[disabled] {
    opacity: 1 !important;
    filter: alpha(opacity=100) !important;
}
.stepwizard-row:before {
    top: 14px;
    bottom: 0;
    position: absolute;
    content: " ";
    width: 100%;
    height: 1px;
    background-color: #ccc;
    z-order: 0;
}
.stepwizard-step {
    display: table-cell;
    text-align: center;
    position: relative;
}
.btn-circle {
    width: 30px;
    height: 30px;
    text-align: center;
    padding: 6px 0;
    font-size: 12px;
    line-height: 1.428571429;
    border-radius: 15px;
}
</style>

<script>
$(document).ready(function () {
  var navListItems = $('div.setup-panel div a'),
          allWells = $('.setup-content'),
          allNextBtn = $('.nextBtn');

  allWells.hide();

  navListItems.click(function (e) {
      e.preventDefault();
      var $target = $($(this).attr('href')),
              $item = $(this);

      if (!$item.hasClass('disabled')) {
          navListItems.removeClass('btn-danger').addClass('btn-default');
          $item.addClass('btn-danger');
          allWells.hide();
          $target.show();
          $target.find('input:eq(0)').focus();
      }
  });

  allNextBtn.click(function(){
      var curStep = $(this).closest(".setup-content"),
          curStepBtn = curStep.attr("id"),
          nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
          curInputs = curStep.find("input[type='text'],input[type='url']"),
          isValid = true;

      $(".form-group").removeClass("has-error");
      for(var i=0; i<curInputs.length; i++){
          if (!curInputs[i].validity.valid){
              isValid = false;
              $(curInputs[i]).closest(".form-group").addClass("has-error");
          }
      }

      if (isValid)
          nextStepWizard.removeAttr('disabled').trigger('click');
  });

  $('div.setup-panel div a.btn-danger').trigger('click');
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


<!-- bloc-1 -->
<div class="bloc bgc-white l-bloc" id="bloc-1">
	<div class="container bloc-lg">
		<div class="row">
			<div class="col-sm-12">
				
					<div class="container">
  
<div class="stepwizard col-md-offset-3">
    <div class="stepwizard-row setup-panel">
      <div class="stepwizard-step">
        <a href="#step-1" type="button" class="btn btn-danger btn-circle">1</a>
        <p>Upload MSANs</p>
      </div>
      <div class="stepwizard-step">
        <a href="#step-2" type="button" class="btn btn-default btn-circle" disabled="disabled">2</a>
        <p>Upload Configuration</p>
      </div>
      <div class="stepwizard-step">
        <a href="#step-3" type="button" class="btn btn-default btn-circle" disabled="disabled">3</a>
        <p>Update Database</p>
      </div>
    </div>
  </div>
  
  
    <div class="row setup-content" id="step-1">
      <div class="col-xs-6 col-md-offset-3">
          <div class="col-md-12"><br>
          

                                 <div>

                                            
                                            <form action="uploading.jsp" method="post" enctype="multipart/form-data" class="">
                                                <input type="file" name="file" size="50">
                                                

                                                <input type="submit" class="btn btn-danger" value="Upload File">
                                            </form>
                                        </div>
          <button class="btn btn-primary nextBtn btn-lg pull-right" type="button">Next</button>
        </div>
      </div>
    </div>
    <div class="row setup-content" id="step-2">
      <div class="col-xs-6 col-md-offset-3">
          <div class="col-md-12"><br>
          

          <div class="form-group">
           
                                        <form action="uploadingServices.jsp" method="post" enctype="multipart/form-data" class="">
                                            <input type="file" name="file" size="50">
                                            


                                            <input type="submit" class="btn btn-danger" value="Upload File">
                                        </form>
          </div>
          <button class="btn btn-primary nextBtn btn-lg pull-right" type="button">Next</button>
        </div>
      </div>
    </div>
    
  
  <div class="row setup-content" id="step-3">
      <div class="col-xs-6 col-md-offset-3">
        <div class="col-md-12">
            <h3>We are done!</h3><br>
           <input id="btn1" class="btn btn-danger" value="Update Database"/>
                              <script>
                                  $(document).ready(function(){
                                      $('#btn1').click(function(){
                                       $.get("syncDatabase",function(response){
                                          window.location.replace("loading.jsp");
                                       });
                                      });
                                  });
                                  </script>
          
        </div>
      </div>
    </div>
  
</div>
					
				</div>
			</div>
		</div>
	</div>
	
</div>
<!-- bloc-1 END -->

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
