/**
 * 
 */
$(document).ready(function(){
	
	// member 목록 1 출력
	$('.member_list1').click(function(){
		
		$.ajax({
			url: '/Ch09/member',
			method: 'GET',
			dataType: 'json',
			success: function(data){
				console.log(data)
			}
		});
	});
	
	// member 목록 2 출력
	$('.member_list2').click(function(){
		
		$.ajax({
			url: '/Ch09/member/a101',
			method: 'GET',
			dataType: 'json',
			success: function(data){
				console.log(data)
			}
		});
	});
	
	// member 등록
	$('.member_list2').click(function(){
		
		let jsonData = {
			"uid":"r101",
			"pass":"1234",
			"name":"홍길동",
			"hp":"010-8888-8888",
			"pos":"사장",
			"dep":"101",
			"rdate":"",
			
		};
		
		$.ajax({
			url: '/Ch09/member/a101',
			method: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data)
			}
		});
	});
	// member 수정
	$('.member_modify').click(function(){
		
		let jsonData = {
			"uid":"r101",
			"pass":"1234",
			"name":"홍길동",
			"hp":"010-8888-8888",
			"pos":"사장",
			"dep":"101",
			"rdate":"",
			
		};
		
		$.ajax({
			url: '/Ch09/member/a101',
			method: 'GET',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data)
			}
		});
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
});