$( "#userlistContainer" ).hide();

// TODO: Move to own module
var ws = new WebSocket(config.serverUrl);
ws.onopen = function()
{
  alert("Web Socket is connected!!");
};
ws.onessage = function (evt)
{
  var msg = evt.data;
  alert("Message received:" +  msg);
};
ws.onclose = function()
{
  alert("Connection is closed...");
};
// ---------


var connect = function(el) {

};

$( "#welcome" ).click(function() {
  $( "#welcome" ).fadeOut(500, function() {
    $( "#userlistContainer" ).fadeIn(500);
  });

});
