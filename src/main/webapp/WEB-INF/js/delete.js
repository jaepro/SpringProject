$(function() {
    $('#deleteBtn').click(function() {
        var confirmDelete = confirm('정말 탈퇴를 하시겠습니까?');

        if (confirmDelete) {
            var pwd = prompt('비밀번호를 입력하세요');
            
            if (!pwd) {
                alert('비밀번호가 일치하지 않습니다.');
                return;
            }

            $.ajax({
                type: 'post',
                url: '/spring/user/delete',
                data: {   			//서버로 보내는 데이터
                    id: $('#id').val(),
                    pwd: pwd
                },
                dataType: 'text',  //서버로부터 받는 데이터  문자열 - text / xml형식 / json형식
                success: function(data) {
                    alert('정상적으로 탈퇴되었습니다.');
                    location.href = '/spring/user/list';
                },
                error: function(e) {
                    console.log(e);
                }
            });
        }
    });
});