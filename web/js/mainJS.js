/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function clearBody()
{
  
    var body  = document.getElementById("mainDiv");
    body.innerHTML ='';


}
function getStartPage()
{
   clearBody();
    _appendOptions();
}
//main function for the home screen's button
function _appendOptions()
{
    //2.insert a side panel 
    
    //3.insert a list of options ( update msan,analysis,bras activity... ) 
    var _listOfOptions = '<table><tr><td><button>test</button></td><td><button></button></td><td><button></button></td></tr></table>';
  var body = document.getElementById("mainDiv");
   body.innerHTML =   _listOfOptions  ;
   _appendSidePanel("Pick a selection","Select one of the available options ","mainDiv",30,30);
   _functionUploadCabinets();
}

// append right side panel for notifications.
function _appendSidePanel(panelHeader,panelBody,elementID,width,height)
{
         var _appendPanelHTML = '<div class="w3-card-4 w3-animate-left  w3-card-4-right" style="width:'+width+'%;height:'+height+'%;"><header class="w3-container  w3-red"><h4>'+panelHeader+'</h4></header><div class="w3-container  w3-white-bg"><p>'+panelBody+'</p></div>';
 document.getElementById(elementID).innerHTML = _appendPanelHTML;
 
 
}
function _functionUploadCabinets ()
{

    var body = document.getElementById("mainDiv");
    
    body.innerHTML = '<div id="notification"></div><form action="uploading.jsp" method="post" enctype="multipart/form-data" class=""><input type="file" name="file"  size="50"><br><input type="submit"  class="w3-btn w3-border w3-red" value="Upload File"></form>';
    _appendSidePanel("Cabinet Names","1.Upload CSV File. <br> 2.Cabinet names ex('1-1-1-1')<br> ","notification",30,30);
     
}

function _functionUploadCabinetsServices()
{
    
    var body = document.getElementById("mainDiv");
    body.innerHTML = '<div id="notification"></div><form action="uploadingServices.jsp" method="post" enctype="multipart/form-data" class=""><input type="file" name="file" size="50"><br><input type="submit" onClick="_appendDatabaseUpdate()" class="w3-btn w3-border w3-red" value="Upload File"> </form>';
    _appendSidePanel("Cabinet Services","1.Upload CSV File. <br> 2.Services ex('03-1-05-64','2826','Lag-9','Ramsis BNG Bras';)<br> ","notification",30,30);
     
}
function _appendDatabaseUpdate()
{
   
    _addButton("red","","Update Database","mainDiv","updatebutton");
    
}


function _addButton (btnColor,onClick,btnTitle,elementID,btnID)
{
    var button = ' <button id="'+btnID+'" class="w3-btn w3-border w3-'+btnColor+'" '+onClick+' >'+btnTitle+'</button>';
    var el = document.getElementById(elementID);
    el.innerHTML = button;
}

