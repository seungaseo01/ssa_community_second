bNo =  parseInt($(".bNo").val());
alert(bNo);


//댓글 추가

$(document).on('click', '#comment_save_btn', function () {

	alert("aaaa");

	var comment = new Object();

	// 댓글내용 가져오기
	comment['cmContent'] =  $("#cmContent").val();

	// 게시글 번호
	comment['bNo'] =  bNo;
	
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
			location.href = "/board/select?bNo="+data;
		 },
         error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });	
});




// 대댓글 입력창 생성
$(".add_btn").on('click', function () {
	alert("AAA");
	
	$("#cmContent_tr").hide();
	
	var cmGrp = $(this).attr("id");
	alert(cmGrp);

    var replyContent = `
			<tr id="cmContent_tr">
			<td colspan=5>
            <input type="text" name="cmContent" placeholder="댓글을 입력해주세요" id="cmContent_input">
            <button onclick="addReply(${cmGrp})">댓글 추가</button>
			</td>
			</tr>`;
			
	$(this).closest('tr').after(replyContent);

});

// 대댓글 입력
function addReply(cmGrp){

	alert("nn");
	alert(cmGrp);

	var comment = new Object();
	
	// 댓글내용 가져오기
	comment['cmContent']=  $("#cmContent_input").val();

	// 게시글 번호
	comment['bNo'] = bNo;

	// 댓글grp
	comment['cmGrp'] = cmGrp;
	
	$.ajax({
		         url : '/comment/reInsert',
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
    
}




// 댓글확인 
function comment_select(bNo){
	
		alert("comment_select");
		alert("넘어온값 : "+bNo);
		
		// bNo = parseInt(bNo);
		
		$.ajax({
         url : '/comment/selectComment/'+bNo,
         method : 'Get',
	     contentType:'application/json;charset=utf-8',
	     dataType : 'json',         
		 success:function(data){
		 	alert("댓글조회 성공");
			alert(data);
			console.log(JSON.stringify(data))	
		 },
         error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
         }
      });	
}

// 댓글 삭제
$(".remove_btn").on('click', function () {
	alert("AAA");
	
	$("#cmContent_tr").hide();
	
	var cmNo = $(this).attr("id");
	alert(cmNo);

         var result = confirm('댓글을 정말 삭제하시겠습니까?');
         alert('result : ' + result);
         if(result){
            $.ajax({
               url : '/comment/deleteComment/' + cmNo,
               method : 'DELETE',
               success:function(){
                  user_list();
               },
               error:function(request,status,error){
                  alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
               }
            });
         }
});

  

