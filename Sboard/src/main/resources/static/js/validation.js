/**
 * 
 */
// 데이터 검증에 사용할 정규표현식
let regUid   = /^[a-z]+[a-z0-9]{4,19}$/g;
let regName  = /^[가-힣]{2,4}$/;
let regNick  = /^[가-힣a-zA-Z0-9]+$/;
let regEmail = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
let regHp 	 = /^\d{3}-\d{3,4}-\d{4}$/;
let regPass  = /^.*(?=^.{5,15}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/;

// 폼 데이터 검증 결과 상태변수
let isUidOk   = false;
let isPassOk  = false;
let isNameOk  = false;
let isNickOk  = false;
let isEmailOk = false;
let isHpOk 	  = false;

/*
// 순수 자바스크립트(바닐라 스크립트) AJAX 처리 로직(아이디)
window.onload = () => {
	
	const btnCheckUid = document.getElementById('btnUidCheck');
	
	btnCheckUid.addEventListener('click', ()=>{
		
		let uid = document.querySelector('input[name=uid]').value;
		const resultUid = document.querySelector(".resultUid");
		
		if(!uid.match(regUid)){
			isUidOk = false;
			resultUid.innerText = "아이디가 유효하지 않습니다.";
		    resultUid.style.color = "red";
			return;
		}
		
		// ajax 부분
		const xhr = new XMLHttpRequest();
		xhr.open("GET", "/Sboard/user/checkUid?uid="+uid);
		xhr.responseType = "json";
		xhr.send();
		
		xhr.onreadystatechange = function(){
			
			if (xhr.readyState === XMLHttpRequest.DONE) {
				
				if (xhr.status === 200) {
		    		const data = xhr.response;  				    		
		    		
		    		if(data.result == 1){
						isUidOk = true;  				    			
		    			resultUid.innerText = "이미 사용중인 아이디 입니다.";
		    			resultUid.style.color = "red";
		    		}else{
						isUidOk = false;
		    			resultUid.innerText = "사용 가능한 아이디 입니다.";
		    			resultUid.style.color = "green";
		    		}
		      	} else {
		        	alert('Request Error!');
		      	}
		    }
		};
	});
};
*/
// 제이쿼리 방법
$(function(){
	
	// 아이디 검증
	$('#btnUidCheck').click(function(){
		let uid = $('input[name=uid]').val();
		
		if(isUidOk){
			return;
		}
		if(!uid.match(regUid)){
			isUidOk = false;
			$('.resultUid').css('color', 'red').text('아이디가 유효하지 않습니다.');
			return;
		}
		
		$.ajax({
			url : '/Sboard/user/checkUid',
			method : 'get',
			data : {"uid":uid},
			dataType : 'json',
			success : function(data){
				if(data.result == 0){
					isUidOk = true;
					$('.resultUid').css('color', 'green').text('사용 가능한 아이디입니다.')
				}else{
					isUidOk = false;
					$('.resultUid').css('color', 'red').text('이미 사용중인 아이디입니다.')
				}
			}
		});
	});
	
	// 비밀번호 일치여부 확인
	$('input[name=pass2]').focusout(function(){
		let pass1 = $('input[name=pass1]').val();
		let pass2 = $(this).val();
		
		if(pass1 == pass2){
		
			if(pass2.match(regPass)){
				isPassOk = true;
				$('.resultPass').css('color', 'green').text('비밀번호가 일치합니다.');
			}else{
				isPassOk = false;
				$('.resultPass').css('color', 'red').text('영문, 숫자, 특수문자 조합 최소 5자 이상이어야 합니다.');
			}
		}else{
			isPassOk = false;
			$('.resultPass').css('color', 'red').text('비밀번호가 일치하지 않습니다.');
		}
	});
	
	// 이름 유효성 검증
	$('input[name=name]').focusout(function(){
		let name = $(this).val();
		
		if(!name.match(regName)){
			isNameOk  = false;
			$('.resultName').css('color', 'red').text('이름은 한글 2자 이상이어야 합니다.');
		}else{
			isNameOk  = true;
			$('.resultName').text('');
		}
	});
	
	// 별명 유효성 검사 & 중복체크
	
	$('#btnNickCheck').click(function(){
		let nick = $('input[name=nick]').val();
		
		if(isNickOk){
			return;
		}
		if(!nick.match(regNick)){
			isNickOk = false;
			$('.resultNick').css('color', 'red').text('별명이 유효하지 않습니다.');
			return;
		}
		
		$.ajax({
			url : '/Sboard/user/checkNick',
			method : 'get',
			data : {"nick":nick},
			dataType : 'json',
			success : function(data){
				if(data.result == 0){
					isNickOk = true;
					$('.resultNick').css('color', 'green').text('사용 가능한 별명입니다.')
				}else{
					isNickOk = false;
					$('.resultNick').css('color', 'red').text('이미 사용중인 별명입니다.')
				}
			}
		});
	});
	
	// 이메일 유효성 검사
	$('input[name=email]').focusout(function(){
		let email = $(this).val();
		
		if(!email.match(regEmail)){
			isEmailOk = false;
			$('.resultEmail').css('color', 'red').text('이메일이 유효하지 않습니다.');
		}else{
			isEmailOk = true;
			$('.resultEmail').text('');
		}
		
	});
	
	// 휴대폰 유효성 검사
	$('input[name=hp]').focusout(function(){
		let hp = $(this).val();
		
		if(!hp.match(regHp)){
			isHpOk = false;
			$('.resultHp').css('color', 'red').text('휴대폰이 유효하지 않습니다.');
		}else{
			isHpOk = true;
			$('.resultHp').text('');
		}
	});
	
	// 폼 전송이 시작될 때 실행되는 폼 이벤트(폼 전송 버튼을 클릭했을 때)
	$('.register > form').submit(function(){
		
		////////////////////////////////////
		// 폼 데이터 유효성 검증(Vaildation)
		////////////////////////////////////
		// 아이디 검증
		if(!isUidOk){
			alert('아이디를 확인하십시오.');
			return false;
		}
		// 비밀번호 검증
		if(!isPassOk){
			alert('비밀번호를 확인하십시오.');
			return false;
		}
		// 이름 검증
		if(!isNameOk){
			alert('이름을 확인하십시오.');
			return false;
		}
		// 별명 검증
		if(!isNickOk){
			alert('별명을 확인하십시오.');
			return false;
		}
		// 이메일 검증
		if(!isEmailOk){
			alert('이메일을 확인하십시오.');
			return false;
		}
		// 휴대폰 검증
		if(!isHpOk){
			alert('휴대폰을 확인하십시오.');
			return false;
		}
		
		// 최종 전송
		return true;
	});
});