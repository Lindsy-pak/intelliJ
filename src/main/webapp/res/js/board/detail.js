var cmtFrmElem = document.querySelector('#cmtFrm');
var cmtListElem = document.querySelector('#cmtList');
var cmtModModalElem = document.querySelector('#modal');

function regCmt() {
	var cmtVal = cmtFrmElem.cmt.value;
	var param = {
		iboard: cmtListElem.dataset.iboard,
		cmt: cmtVal
	};
	regAjax(param);
}

//서버에게 등록해줘~~~
function regAjax(param) {
	const init = {
		method: 'POST',
		body: JSON.stringify(param),
		heaers:{
			'accept' : 'application/json',
			'content-type' : 'application/json;charset=UTF-8'
		}
	};

	fetch('cmt', init)
		.then(function(res) { /*promise객체를 리턴해 준다. */
			return res.json();
		})
		.then(function(myJson) {
			console.log(myJson);

			switch(myJson.result) {
				case 0: //등록 실패
					alert('등록 실패!');
					break;
				case 1: //등록 성공
					cmtFrmElem.cmt.value = ''; /*댓글창을 작성한 뒤에 기존에 적혀져있는 내용으 지우고 refresh하는 기능*/
					getListAjax();
					break;
			}
		});
}

//서버에게 댓글 리스트 자료 달라고 요청하는 함수
function getListAjax() {
	var iboard = cmtListElem.dataset.iboard;

	fetch('cmt/ ' + iboard)
		.then(function(res) {
			return res.json();
		})
		.then(function(myJson) {
			console.log(myJson);

			makeCmtElemList(myJson);
		});
}

// ajax 로 화면 구현하기 위해서 보여주는 거 (서버로 부터 json형태로 객체로 만들어서 보여줄려고)
// 페이스북처럼 리프레쉬 없이 화면 만드는법
function makeCmtElemList(data) { //list table을 html이 아닌 script로 만드는 방법

	cmtListElem.innerHTML = '';

	var tableElem = document.createElement('table');
	var trElemTitle = document.createElement('tr');
	var thElemCtnt = document.createElement('th');
	var thElemWriter = document.createElement('th');
	var thElemRegdate = document.createElement('th');
	var thElemBigo = document.createElement('th');

	thElemCtnt.innerText = '내용';
	thElemWriter.innerText = '작성자';
	thElemRegdate.innerText = '작성일';
	thElemBigo.innerText = '비고';

	trElemTitle.append(thElemCtnt);
	trElemTitle.append(thElemWriter);
	trElemTitle.append(thElemRegdate);
	trElemTitle.append(thElemBigo);

	tableElem.append(trElemTitle);
	cmtListElem.append(tableElem);

	var loginUserPk = cmtListElem.dataset.loginUserPk;

	//table에서 tr반복하는 부분
	//callback 함수로 부름 (내가 보낸 함수를 부르는 함수)
	data.forEach(function(item) {
		var trElemCtnt = document.createElement('tr');
		var tdElem1 = document.createElement('td');
		var tdElem2 = document.createElement('td');
		var tdElem3 = document.createElement('td');
		var tdElem4 = document.createElement('td');

		tdElem1.innerText = item.cmt;
		tdElem2.append(item.writerNm);
		tdElem3.append(item.regdate);

		if(parseInt(loginUserPk) === item.iuser) { //내가 쓴 댓글만 수정 삭제 버튼이 뜬다
			var delBtn = document.createElement('button');
			var modBtn = document.createElement('button');

			//첫번째 인자는 무슨 동작 했을 때 두번째 인자는 무슨 함수가 실행이 되는지
			//삭제버튼 클릭시
			delBtn.addEventListener('click', function() {
				if(confirm('삭제하시겠습니까?')) {
					delAjax(item.icmt);
				}
			});

			//수정버튼 클릭시
			modBtn.addEventListener('click', function() {
				//댓글 수정 모달창 띄우기
				openModModal(item);
			});

			delBtn.innerText = '삭제';
			modBtn.innerText = '수정';

			tdElem4.append(delBtn);
			tdElem4.append(modBtn);
		}

		trElemCtnt.append(tdElem1);
		trElemCtnt.append(tdElem2);
		trElemCtnt.append(tdElem3);
		trElemCtnt.append(tdElem4);

		tableElem.append(trElemCtnt);
	});
}

function delAjax(icmt) {
	fetch('cmt/' + icmt, { method: 'DELETE'})//fetch: 메소드 호출(promise객체)
		.then(function(res) { //then이라는 키워드를 쓰는 이유 : 호출한거에서 (결과값).then 그리고 나서
			return res.json();
		})
		.then(function(data) {
			console.log(data);

			switch(data.result) {
				case 0:
					alert('댓글 삭제를 실패하였습니다.');
					break;
				case 1:
					getListAjax();
					break;
			}
		});
}

function modAjax() {
	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	var param = {
		icmt: cmtModFrmElem.icmt.value,
		cmt: cmtModFrmElem.modCmt.value
	}

	const init = {
		method: 'POST',
		body: new URLSearchParams(param)
	};

	fetch('cmtDelUpd', init)
		.then(function(res) {
			return res.json();
		})
		.then(function(myJson) {
			closeModModal();
			switch(myJson.result) {
				case 0:
					alert('댓글 수정을 실패하였습니다.');
					break;
				case 1:
					getListAjax();
					break;
			}
		});
}

function openModModal({icmt, cmt}) {
	cmtModModalElem.className = '';

	var cmtModFrmElem = document.querySelector('#cmtModFrm');
	cmtModFrmElem.icmt.value = icmt;
	cmtModFrmElem.cmt.value = cmt;
}

function closeModModal() {
	cmtModModalElem.className = 'displayNone';
}

getListAjax(); //이 파일이 임포트되면 함수 1회 호출!




















