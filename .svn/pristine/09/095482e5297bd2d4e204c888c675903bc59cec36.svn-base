var EventHandler = function() {
	//socket part

	function setup() {

		var selectedClassName;
		$('.seats').on("vclick", function() {

			//var messagesRef = new Firebase('https://xw8zu54swv8.firebaseio-demo.com/');
			// And then we write data to his first and last name locations:
			selectedClassName = $(this).attr('class').split(' ')[1];
			// messagesRef.push({
			// className : selectedClassName,
			// userId : userId
			// });

			

			var selectedClassName = $(this).attr('class').split(' ')[1];
			//can't buy checker
			if($(this).attr('booked') == 'true'){
				alert('此位置已售出');
				return 0;
			}
			SeatBookie.selectSeat({
				className : selectedClassName,
				userId : userId
			});
			
			

			if ($(this).attr('bSelected') === undefined) {
				$(this).attr('bSelected', false);
			}

			var checker = $(this).attr('bSelected');
			if (checker == 'false') {

				$(this).attr('bSelected', true);
				$('.' + selectedClassName).css('fill', selectedColor.selectBySelf);
			} else {

				$(this).attr('bSelected', false);
				$('.' + selectedClassName).css('fill', selectedColor.original);
				SeatBookie.unSelectSeat({
				className : selectedClassName,
				userId : userId
			});

			}

		});
	}

	function socket() {
		//其他人選位子  被呼叫
		//messagesRef.limit(10).on('child_added', function(snapshot) {
		SeatBookie.onChange = function(snapshot) {
			  console.log(snapshot);
			 
			if (snapshot.state === 'BOOKED') {
			
				$('.' + snapshot.className).attr('fill', selectedColor.buyed);
				$('.' + snapshot.className).attr('booked', true);
				return 0;
			}
		
			 
			// obj.state
			// obj.userId
			// obj.className

			var message = snapshot;
			var selectedClassNameOther = message.className;
			 
			if(snapshot.state === "SELECTED"){
				 console.log(userId + ','+ message.userId);
			if (userId === message.userId) {
				console.log('userId != message.userId');
				if ($('.' + selectedClassNameOther).attr('otherSelected') === undefined) {
					$('.' + selectedClassNameOther).attr('otherSelected', false);
				}

				var checker2 = $('.' + selectedClassNameOther).attr('otherSelected');

				if (checker2 === "false") {
					console.log('snapshot.checker2' + checker2);
					$('.' + selectedClassNameOther).attr('otherSelected', true);

					if ($('.' + selectedClassNameOther).attr('bSelected') === "true") {

					} else {
						//$('.' + selectedClassNameOther).css('fill', selectedColor.selectByOther);
						$('.' + selectedClassNameOther).attr('fill', selectedColor.selectByOther);
					}

				} else {
					console.log('false.false' + checker2);
					if ($('.' + selectedClassNameOther).attr('bSelected') == 'true') {

					} else {
						$('.' + selectedClassNameOther).attr('otherSelected', false);
						//$('.' + selectedClassNameOther).css('fill', selectedColor.original);
						$('.' + selectedClassNameOther).attr('fill', selectedColor.original);
					}

				}
				//console.log('checker2after' + checker2);

			} else {
			console.log('else != else.else');
				$('.' + message.className).removeAttr('otherSelected');
				$('.' + message.className).css('fill', selectedColor.selectByOther);
			}
			
			}

		};
	}

	function submit() {
		$('img').click(function() {
			$('.seats').each(function() {

				if ($(this).attr('bSelected') == 'true') {

					var selectedClassName = $(this).attr('class').split(' ')[1];
					SeatBookie.bookSeat({
						className : selectedClassName,
						userId : userId // 訂座位
					});
					
					$('.' + selectedClassName).attr('fill', selectedColor.buyed);
					$('.' + selectedClassName).css('fill', selectedColor.buyed);
					console.log(selectedColor.buyed);
					$('.' + selectedClassName).attr('booked', true);
					$('.' + selectedClassName).removeAttr('bSelected');
				
					//console.log(selectedClassName);
				}

			});
			alert('訂票成功');
		});
	}

	return {
		setup : setup,
		socket : socket,
		submit : submit
	};
}();
