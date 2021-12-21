$(function() {

	comment_insert();
	//comment_list();
	//comment_delete();
	//comment_update();
	
});



//데이터 저장
function comment_insert() {


	    $(document).on('click', '#comment_save_btn', function () {

		alert("aaaa");
		
	    	var comment = new Object();
	    	var board = new Object();

			// 댓글내용 가져오기
			comment['cmContent'] =  $("#cmContent").val();
	
			// 게시글 번호
			board['bNo'] =  parseInt($(".bNo").val());
			comment['board']= board;
			
			// 댓글순서
			comment['cmSeq'] = 1;
			 
			// 댓글계층
			comment['cmLvl'] = 0;
			
			alert(comment)
			console.log(comment)
			
			
			$.ajax({
		         url : '/comment/insert',
		         method : 'post',
			     contentType: 'application/json',
		         dataType:'json',
	 		     data: JSON.stringify(comment),
		         success:function(data){
				 	alert("댓글등록 완료!");
				 },
		         error:function(request,status,error){
		            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		         }
		      });	
    	});
}
