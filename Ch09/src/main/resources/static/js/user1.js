/**
 * 
 */
 $(document).ready(function(){
				
	// user1 목록요청
	$('.user1_list1').click(function(){
		
		$.ajax({
			'url': '/Ch09/user1',
			'method': 'GET',
			'dataType': 'json',
			'success': function(data){
				console.log(data);
			}
		});
	});
	
	// user1 목록요청
	$('.user1_list2').click(function(){
		
		// input 값 넣어서 해당 값 select
		let uid = $('input[name=uid]').val();
		console.log(uid);
		
		$.ajax({
			'url': '/Ch09/user1/'+uid,
			'method': 'GET',
			'dataType': 'json',
			'success': function(data){
				console.log(data);
			}
		});
	});
	
	// user1 등록요청
	$('.user1_register').click(function(){
		
		let jsonData = {
				"uid": "r101",
				"pass": "1234",
				"name": "홍길동",
				"hp": "010-1234-1111",
				"age": 21
			};
		
		$.ajax({
			url: '/Ch09/user1',
			method: 'POST',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data);
			}
		});
	});
	
	// user1 수정요청
	$('.user1_modify').click(function(){
		
		let jsonData = {
				"uid": "r101",
				"pass": "1234",
				"name": "홍길동",
				"hp": "010-1234-2222",
				"age": 27
			};
		
		$.ajax({
			url: '/Ch09/user1',
			method: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data);
			}
		});
	});
	
	// user1 삭제요청
	$('.user1_delete').click(function(){
		
		// 주소에 값 고정
		$.ajax({
			url: '/Ch09/user1/r101',
			method: 'DELETE',
			dataType: 'json',
			success: function(data){
				console.log(data);
			}
		});
	});
});