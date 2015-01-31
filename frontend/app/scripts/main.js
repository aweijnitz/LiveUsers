$( "#userlistContainer" ).hide();


var loadData = function(el) {

};

$( "#welcome" ).click(function() {
  $( "#welcome" ).fadeOut(500, function() {
    $( "#userlistContainer" ).fadeIn(500);
  });

});
