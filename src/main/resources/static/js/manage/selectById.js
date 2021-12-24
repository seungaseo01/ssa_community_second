alert("ggg");

$(function(){
    $("#getAuth").hide();
	var role = $("#user_role").text();
	
	if(role=="ROLE_USER"){
		$("#getAuth").show();
	}

})