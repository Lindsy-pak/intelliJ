var frmElem = document.querySelector('#frm'); /*태그 선택자 */
//document는 전수 조사를 한다 (처음부터 내용을 끝까지 검사)
var uidElem = frmElem.uid; /*userJoin에서 name="uid"로 접근*/
var upwElem = frmElem.upw; // frm으로 접근해도 가능(하지만 안하는것이 좋다)
var chkUpwElem = frmElem.chkUpw; /*아이디든 네임으로든 접근 가능*/
var unmElem = frmElem.unm;
var chkUidResultElem = frmElem.querySelector('#chkUidResult');

var btnChkIdElem = frmElem.btnChkId; //중복ID체크 버튼
/*var btnChkIdElem = frmElem.querySelector('#btnChkId');*/
/*btnChkIdElem.onclick = function() { //이벤트를 걸어주는 기능, html에서 onclick 적는거랑 똑같음 }*/
btnChkIdElem.addEventListener('click', function() { //이벤트 바인딩
	// 눌러졌을 때 일어났으면 하는 것들을 function 안에 넣는다.
	idChkAjax(uidElem.value);
});

function idChkAjax(uid) {
	console.log(uid);
	/*ajax로 json파일을 통해 연결하는 방법 */
	fetch('/user/idChk?uid=' + uid)
		.then(function(res) {
			return res.json();
		})
		.then(function(myJson) {
			console.log(myJson);
			switch(myJson.result) {
				case 0:
					chkUidResultElem.innerText = '이 아이디는 사용할 수 있습니다.'; // innerText -> 안에 내용을 넣는 기능
					break;
				case 1:
					chkUidResultElem.innerText = '이 아이디는 사용할 수 없습니다.';
					break;
			}
		});
		/*따로 설정을 안하면 get방식으로 날아간다. 첫번째 인자값은 주소*/
}

function frmChk() {
	//이상이 생기면 return false;

	//var uidVal = document.getElementByName('uid'); 옛날 버전 브라우저에 사용
	var uidVal = uidElem.value; /* 속성의 값을 다 가져오는 기능 value */
	/* 전역 변수로 선언을 했기 때문에 value로 접근 가능함 아니면 위에 방식으로 접근해야 함 */

	//아이디 하나도 안 적으면 alert "아이디를 작성해 주세요" false 리턴
	//2자 이하면 alert "아이디는 3자 이상 작성해 주세요." false 리턴
	if(uidVal.length < 3) {
		if(uidVal.length == 0) {
			alert('아이디를 작성해 주세요');
		} else {
			alert('아이디는 3자 이상 작성해 주세요.');
		}
		return false;
	}

	var upwVal = upwElem.value;
	var chkUpwVal = chkUpwElem.value;

	//비밀번호 하나도 안 적으면 alert "비밀번호를 작성해 주세요" false 리턴
	//3자 이하면 alert "비밀번호는 4자 이상 작성해 주세요." false 리턴
	//비밀번호와 확인비밀번호가 다르면 "비밀번호를 확인해 주세요" false 리턴
	if(upwVal.length < 3) {
		if(upwVal.length == 0) {
			alert('비밀번호를 작성해 주세요');
		} else {
			alert('비밀번호는 4자 이상 작성해 주세요.');
		}
		return false;
	} else if(upwVal !== chkUpwVal) { /*equals는 자바만 가능 : 문자열 비교*/
		alert('비밀번호를 확인해 주세요');
		return false;
	}

	if(unmElem.value.length < 2) {
		alert('이름은 2자 이상 작성해 주세요.');
		return false;
	}
}







