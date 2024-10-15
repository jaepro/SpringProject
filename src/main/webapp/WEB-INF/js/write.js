$(function() {
    // 아이디 중복 체크
    $('#id').focusout(function() {
        $('#idDiv').empty();
        
        if ($('#id').val() == '') {
            $('#idDiv').html('먼저 아이디 입력');
        } else {
            $.ajax({
                type: 'post',
                url: '/spring/user/getExistId',  
                data: 'id=' + $('#id').val() ,
                dataType: 'text',
                success: function(data) {
                	//alert(data)
                    if (data == 'exist') {
                        $('#idDiv').html('이미 사용 중인 아이디입니다.');
                        $('#idDiv').css('color', 'red');
                    } else {
                        $('#idDiv').html('사용 가능한 아이디입니다.');
                        $('#idDiv').css('color', 'blue');
                    }
                },
                error: function() {
                    $('#idDiv').html('아이디 체크 중 오류가 발생했습니다.');
                }
            });
        }
    });
    
    
    //회원가입
    $('#writeBtn').click(function(){
    	$('#nameDiv').empty();
    	$('#idDiv').empty();
    	$('#pwdDiv').empty();
    	
    	if($('#name').val() == '')
    		$('#nameDiv').html('이름을 입력하세요.');
    	else if($('#id').val() == '')
    		$('#idDiv').html('아이디를 입력하세요.');
    	else if($('#pwd').val() == '')
    		$('#pwdDiv').html('비밀번호를 입력하세요.');
    	else 
    		$.ajax({
    			type: 'post',
                url: '/spring/user/write',  
                data: $('#userWriteForm').serialize() ,  //폼의 
                success: function(data) {
                    alert('회원가입 완료');
                    location.href = '/spring/user/list';
                }
    		});
    });
});
