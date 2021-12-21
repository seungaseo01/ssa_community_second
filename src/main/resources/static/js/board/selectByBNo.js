$(function(){
    var test = $("#testCon").val();
    $("#innerCon").html(test);

})

//좋아요버튼 클릭
$(document).on('click', '#likeBtn', function () {

    pushLike();
});


function pushLike(){

var bNo = $(".bNo").val();

alert(bNo);

         $.ajax({
            url:'/board/likes',
            type : 'POST',
            data : {bNo : bNo},
            success : function(data) {
					alert(data);
                    alert("ajax 성공");
                    console.log(data);
                    location.href = "/board/select?bNo="+bNo;

            },
            error : function(request, status, error){
            alert("ajax 실패");
                 console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }

         });
}



