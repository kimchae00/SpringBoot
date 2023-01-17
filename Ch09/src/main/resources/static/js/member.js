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
	$('.member_register').click(function(){
		
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
			url: '/Ch09/member',
			method: 'POST',
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
			"uid":"a101",
			"pass":"1234",
			"name":"박혁거세",
			"hp":"010-8888-8888",
			"pos":"사장",
			"dep":"101"
			//"rdate":"now",
			
		};
		
		$.ajax({
			url: '/Ch09/member',
			method: 'PUT',
			data: jsonData,
			dataType: 'json',
			success: function(data){
				console.log(data)
			}
		});
	});
	
	// member 삭제
	
	$('.member_delete').click(function(){
		
		$.ajax({
			url: '/Ch09/member/a111',
			method: 'delete',
			dataType: 'json',
			success: function(data){
				console.log(data)
			}
		});
		
		
	});
	
	
	
	
	
	
	
	
	
	
	
	
	
});