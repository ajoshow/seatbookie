var SeatBookie = SeatBookie || function() {

	var SELECTED = "SELECTED";
	var BOOKED = "BOOKED";
	var EMPTY = "EMPTY";
	var FAILED = "FAILED";
	var HOST = "https://crackling-fire-7265.firebaseio.com/"; //https://seatbookie.firebaseio.com

	var graphId = null;
	var userId = null;
	var seatData = {};

	function getGraphSeatQueryPath() {
		return HOST+"/"+graphId+"/seat";
	}
	
	function getSeatQueryPath(seatName) {
		return HOST+"/"+graphId+"/seat/"+seatName;
	}
	
	function getSeatUserQueryPath(seatName) {
		return HOST+"/"+graphId+"/seat/"+seatName+"/user";
	}
	
	function getSeatUserIdQueryPath(seatName, userId) {
		return HOST+"/"+graphId+"/seat/"+seatName+"/user/"+userId;
	}
	
	var onFirebaseComplete = function(obj, onComplete) {
		onComplete(obj);
	}

	return {

		// �]�wgraph id
		setGraphId: function(id) {
			graphId = id;
			var graphData = new Firebase(getGraphSeatQueryPath());
			graphData.on("value", function(snapshot) {
				var seats = snapshot.val();
				for(var seatName in seats) {
					if(seatData[seatName] != seats[seatName].state) {
						seatData[seatName] = seats[seatName].state;
						var obj = {
							"userId": userId,
							"className": seatName,
							"state": seats[seatName].state
						};
						SeatBookie.onChange(obj);
					}
				}
			});
		},
		
		// �]�wuser id
		setUserId: function(id) {
			userId = id;
		},

		// �����y��
		selectSeat: function(obj, onComplete) {
			var url = getSeatQueryPath(obj.className);
			var dataSource = new Firebase(url);
			dataSource.update({state: SELECTED});
			
			url = getSeatUserIdQueryPath(obj.className, obj.userId);
			dataSource = new Firebase(url);
			dataSource.set(1);
		},

		// ���������y��
		unSelectSeat: function(obj, onComplete) {
			var url = getSeatUserIdQueryPath(obj.className, obj.userId);
			var dataSource = new Firebase(url);
			dataSource.remove();
			
			dataSource.parent().once("value", function(dataSnapshot) {
				if(!dataSnapshot.hasChildren()) {
					var url = getSeatQueryPath(obj.className);
					var dataSource = new Firebase(url);
					dataSource.update({state: EMPTY});
				}
			});
		},
		
		// �q�y��
		bookSeat: function(obj, onComplete) {
			/*
			var url = getSeatQueryPath(obj.className);
			var dataSource = new Firebase(url);
			dataSource.update({state: BOOKED});
			
			url = getSeatUserQueryPath(obj.className);
			dataSource = new Firebase(url);
			var json = {};
			json[obj.userId] = 1;
			dataSource.set(json);
			*/
			if(window.XMLHttpRequest) // code for IE7+, Firefox, Chrome, Opera, Safari
				var xmlhttp=new XMLHttpRequest();
			else // code for IE6, IE5
				var xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

			xmlhttp.onreadystatechange=function() {
				if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					// do nothing
				}
			}
			xmlhttp.open("GET","/seatbookie/bookseat?graphId="+graphId+"&seatName="+obj.className, true);
			xmlhttp.send();
		},
		
		onChange: function(obj) {
		}
	}
}();