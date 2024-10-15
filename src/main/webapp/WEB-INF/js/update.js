$(function() {
	$('#updateBtn').click(function(){
		$('#nameDiv').empty();
    	$('#pwdDiv').empty();
		
		if($('#name').val() == '')
    		$('#nameDiv').html('이름을 입력하세요.');
    	else if($('#pwd').val() == '')
    		$('#pwdDiv').html('비밀번호를 입력하세요.');
    	else 
		$.ajax({
			type: 'post',
			url: '/spring/user/update',
			data: $('#userUpdateForm').serialize() ,
			success: function(data) {
				alert('정상적으로 수정되었습니다.');
				location.href = '/spring/user/list?pg=' + $('#pg').val();
			},
			error: function(e){
				console.log(e);
			}
		});
	});
});