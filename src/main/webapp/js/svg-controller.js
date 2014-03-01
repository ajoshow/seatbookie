var selectedColor = {
	buyed : '#ef5b66',
	selectBySelf : '#22c0d5',
	selectByOther : '#786aef',
	original : '#FED949'
};

//Login part
var userId;
var chatRef = new Firebase('https://seatbookie.firebaseio.com/');
var auth = new FirebaseSimpleLogin(chatRef, function(error, user) {
	userId = user.id;
	SeatBookie.setUserId('userId');
	// 設定user id
	var url = window.location.toString().split('=')[1];
	 
	console.log(url);
	SeatBookie.setGraphId(url);
	// 設定graph id

});

auth.login('anonymous');

var graphSvgFile = 'xml/seat_map_02.xml';
if(graphId != null){
	graphSvgFile = "./" + graphId +".svg";
}

$.ajax({
	type : "GET",
	url : graphSvgFile,
	dataType : "xml",
	async : false,
	success : function(xml) {
		// Load SVG. use pure javascript to prevent Safari couldn't load svg issue.
		$svg = $(xml).find('svg');
		var svg = $(xml).find('svg')[0];
		var svgClone = $(xml).find('svg').clone();
		// we clone it, so one will not effect to another.
		$('#minimap-svg').html(svg);
		$('#viewport-svg').html(svgClone[0]);

		// Get actual svg size
		var svgWidth = $svg.attr("width");
		var svgHeight = $svg.attr("height");

		// actual svg size in Integer format
		var svgWidthInt = svgWidth.replace("px", "");
		var svgHeightInt = svgHeight.replace("px", "");

		// change viewport-scroller manifier to actual svg size
		$("#viewport-scroller").css("width", svgWidth);
		$("#viewport-scroller").css("height", svgHeight);

		// change minimap wrap size to the ratio of actual svg size
		var ratio = svgHeightInt / $("#minimap").height();
		var minimapWidthInt = svgWidthInt / ratio;
		$("#minimap").css("width", minimapWidthInt + "px");

		// change both svg viewBox
		var svgViewbox = "0 0 " + svgWidthInt + " " + svgHeightInt;
		document.getElementById("minimap-svg").setAttribute("viewBox", svgViewbox);
		document.getElementById("viewport-svg").setAttribute("viewBox", svgViewbox);

		/*
		*
		*/
		//var messagesRef = new Firebase('https://xw8zu54swv8.firebaseio-demo.com/');
		//var messagesRef = new Firebase('https://vivid-fire-1865.firebaseio.com/');
		//messagesRef.remove();
		EventHandler.setup();
		EventHandler.socket();
		EventHandler.submit();

	}
});
