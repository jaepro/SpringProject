$(document).ready(function() {
    // 전체 선택
    $('#checkAll').click(function() {
        $('.seqCheckbox').prop('checked', this.checked);
    });
    
    //전체 선택 후 하나라도 해제한 경우, 전체 선택 체크 해제시키기
    $('input[name="seqCheckbox"]').click(function(){
    	$('#checkAll').prop('checked', $('input[name="seqCheckbox"]').length == $('input[name="seqCheckbox"]:checked').length)
    });
    
    $('#deleteBtn').click(function() {
        const seqs = $('.seqCheckbox:checked').map(function() { 
            return $(this).val();
        }).get();

        if (seqs.length === 0) {
            alert('삭제할 항목을 선택해주세요.');
            return;
        }
        console.log(seqs);
        
        $.ajax({
            url: '/spring/user/uploadDelete',
            type: 'POST',
            
            //contentType : 전송 데이터 형식 / dataType : 응답 데이터 형식
            contentType: 'application/json',  
            data: JSON.stringify(seqs), 
            //json형식으로 변환 -> 이렇게 json으로 보내면 받을 때, @RequestParam이 아닌 @RequestBody로 받아야 함!! 
            success: function(data) {
                if (data) {
                    alert('삭제가 완료되었습니다.');
                    location.reload();
                }
            },
            error: function() {
                alert('삭제에 실패했습니다. 다시 시도해주세요.');
            }
        });
    });
});


/*
.map(function() { ... }):

jQuery의 map() 메서드
정의: jQuery에서 map() 메서드는 선택한 요소 집합(예: DOM 요소)에 대해 함수를 실행하고, 그 결과를 새로운 jQuery 객체로 반환합니다.
용도: 주로 선택한 요소의 속성 값이나 텍스트를 추출할 때 사용됩니다. 예를 들어, 여러 체크박스의 값을 모아서 배열로 만들 때 유용합니다.

작동 방식:
map() 메서드는 배열의 각 요소에 대해 함수를 호출하고, 이 함수에서 반환된 값을 수집하여 새로운 배열을 만듭니다.
이 과정에서 원래 배열의 크기는 변하지 않지만, 각 요소에 대해 수행한 연산의 결과를 기반으로 새로운 배열이 생성됩니다.
*/