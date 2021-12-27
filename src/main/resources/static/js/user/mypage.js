alert('ggg');

//수정버튼
$(".mod_user_btn").on('click', function (){

	var id = new Object();
	
	var mid = $(this).attr("data-id");
	var mpw = $(this).attr("data-pw");
	var u_pw = prompt("비밀번호를 입력하세요.");
	
	id['id'] =  mid;
	alert(mpw);
	alert(u_pw);
	if(u_pw == mpw){
	alert('비밀번호 확인완료');
		$.ajax({
			url : "/user/modForm",
	        method : 'GET',
			success : function(data) {
				if(data == 1){
				   alert("회원탈퇴가 완료되었습니다.");
	               location.href="/";				
				}else if(data == -1){
					alert("회원탈퇴를 실패했습니다.");	
				}
			},
			error : function(request, status, error) {
				alert("code:"+request.status);
				alert("에러");	
			}
    				   				
		}) 	   	
	}else{
	alert('xmffla');
	}

})


//탈퇴버튼
$(".del_user_btn").on('click', function (){
	var mid = $(this).attr("id");
	var mpw = $(this).attr("pw");
	var u_pw = prompt("비밀번호를 입력하세요.");
	
	if(u_pw == mpw){
		$.ajax({
			url : "/user/delete",
			type : "DELETE",
			data : {id:mid}, //{파라미터 명:파라미터 값}
			success : function(data) {
				if(data == 1){
				   alert("회원탈퇴가 완료되었습니다.");
	               location.href="/";				
				}else if(data == -1){
					alert("회원탈퇴를 실패했습니다.");	
				}
			},
			error : function(request, status, error) {
				alert("code:"+request.status);
				alert("에러");
				
			}
    				
    				
		}) 	            

	}

})