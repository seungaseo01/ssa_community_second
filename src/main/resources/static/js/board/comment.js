$(function() {

	comment_insert();
	//comment_list();
	//comment_delete();
	//comment_update();
	
});



//댓글 추가
function comment_insert() {


	    $(document).on('click', '#comment_save_btn', function () {

		alert("aaaa");
		
	    	var comment = new Object();

			// 댓글내용 가져오기
			comment['cmContent'] =  $("#cmContent").val();
	
			// 게시글 번호
			comment['bNo'] =  parseInt($(".bNo").val());
			
			alert(comment)
			console.log(comment)
			
			
			$.ajax({
		         url : '/comment/insert',
		         method : 'post',
			     contentType:'application/json;charset=utf-8',
	 		     data: JSON.stringify(comment),
		         success:function(data){
				 	alert("댓글등록 완료!");
					alert(data);
					comment_select(data);
					location.href = "/board/select?bNo="+data;
					
				 },
		         error:function(request,status,error){
		            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		         }
		      });	
    	});
}



// 댓글확인 
function comment_select(bNo){
	
		alert("comment_select");
		alert("넘어온값 : "+bNo);
		
		bNo = parseInt(bNo);
		
		$.ajax({
         url : '/comment/selectComment/'+bNo,
         method : 'Get',
	     contentType:'application/json;charset=utf-8',
         success:function(data){
		 	alert("댓글조회 성공");
			alert(data);
			
		 },
         error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });	


}

// 대댓글 추가  그룹 번호, bNo comtent
$(document).on('click', '#add_btn', function () {
	var res = $("#add_btn").value;
	alert("yyyy");
	alert(res);
	
	 $.ajax({
     url : '/comment/selectComment/'+bNo,
     method : 'Get',
     contentType:'application/json;charset=utf-8',
     success:function(data){
	 	alert("댓글조회 성공");
		alert(data);
		
	 },
     error:function(request,status,error){
        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
        console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
     }
  });	

});

// 대댓글 input창 만들기

$(".add_btn").on('click', function () {
	alert("AAA");
	
	var cmGrp = $(this).attr("id");
	alert(cmGrp);

		
});



// 대댓글 추가  그룹 번호, bNo, comtent

		$(".add_btn").on('click', function () {
			alert("AAA");
			
			var cmGrp = $(this).attr("id");
			alert(cmGrp);
		
	    		
    	});
