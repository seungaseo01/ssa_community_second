    $(function(){
        var test = $("#testCon").val();
        $("#innerCon").html(test);

    })

//저장버튼 클릭
$(document).on('click', '#likeBtn', function () {

    pushLike();
});


function pushLike(){
	alert("aaa");
}

