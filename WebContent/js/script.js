// 브라우저 가로 사이즈에 따른 타이틀 이미지 지정
function titleImageInit() {
	if($("html").width() > 1440) {
		$("#content div.title img.title").attr("src", "img/title_1920.png");
	} else {
		$("#content div.title img.title").attr("src", "img/title_1440.png");			
	}
}	

// [상단메뉴 영역] 상단 메뉴 나타내기
function topMenuSlider(status) {
	if(status) { // true
		$("#topMenu").animate({"top":"+=58px"}, 500);
		$("#topMenu div.topButton span.loginText").text("닫기");
		$("#topMenu div.topButton span.joinText").fadeIn(500);
		topMenuClick = true;
	} else { // false
		$("#topMenu").animate({"top":"-=58px"}, 500);
		$("#topMenu div.topButton span.loginText").text("로그인");
		$("#topMenu div.topButton span.joinText").hide();
		topMenuClick = false;
	}
}

// [상단메뉴 영역] 회원가입 버튼 클릭 시 이벤트 처리
function joinTextClick() {
	if(!topMenuJoinTextClick) {
		$("#topMenu div.topBar div.joinForm").slideDown(500);
		$("#topMenu div.topBar div.loginForm").hide();
		$("#topMenu div.topButton span.joinText").text("로그인");
		topMenuJoinTextClick = true;
	} else {
		$("#topMenu div.topBar div.loginForm").slideDown(500);
		$("#topMenu div.topBar div.joinForm").hide();
		$("#topMenu div.topButton span.joinText").text("회원가입");
		topMenuJoinTextClick = false;
	}
}

// [상단메뉴 영역] 로그인 모드 중 서버로 전송 버튼 클릭 시 이벤트 처리
function loginMember() {

	var action = "member?action=loginMember";
	var MemberEmail = $("#topMenu div.topBar div.loginForm div.loginEmail input.inputMemberEmail").val();
	var MemberPassword = $("#topMenu div.topBar div.loginForm div.loginPassword input.inputMemberPassword").val();
	
	var form_data = {
		inputMemberEmail : MemberEmail,
		inputMemberPassword : MemberPassword
	};
	
	$.ajax({
		type : "POST",
		url : action,
		data : form_data,
		dataType : "text",
		success: function(response) {
			if(response == "LoginOK") {
				location.reload(true);
			} else if(response == "Fail") {
				alert("로그인에 실패하였습니다.");
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});
		
	return false;
}

//[상단메뉴 영역] 회원가입 모드 중 서버로 전송 버튼 클릭 시 이벤트 처리
function joinMember() {

	var action = "member?action=joinMember";
	var MemberEmail = $("#topMenu div.topBar div.joinForm div.joinEmail input.inputMemberEmail").val();
	var MemberPassword = $("#topMenu div.topBar div.joinForm div.joinPassword input.inputMemberPassword").val();
	var MemberName = $("#topMenu div.topBar div.joinForm div.joinName input.inputMemberName").val();	
	
	var form_data = {
		inputMemberEmail : MemberEmail,
		inputMemberPassword : MemberPassword,
		inputMemberName : MemberName
	};
	
	$.ajax({
		type : "POST",
		url : action,
		data : form_data,
		dataType : "text",
		success: function(response) {
			if(response == "JoinOK") {
				location.reload(true);
			} else if(response == "Duplicate") {
				alert("중복된 이메일 입니다.");
			} else {
				alert("회원가입에 실패하였습니다.");
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});
		
	return false;
}



// [로그인 영역] 로그아웃 버튼 클릭 시 이벤트 처리
function logoutMember() {
	$.ajax({
		type : "POST",
		url : "member?action=logoutMember",
		dataType : "text",
		success: function(response) {
			if(response == "LogoutOK") {
				location.reload(true);
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});
}

// [블랙판 영역] 닫기 버튼 클릭 시 이벤트 처리
function blackplateClose() {
	$("#topMenu").fadeIn(500);
	$("body").css("overflow-y", "auto");
	
	$("#blackPlate").slideUp(500).queue(function() {
		$("#blackPlate div.innerContent div.top div.title").html("");
		$("#blackPlate div.innerContent div.top div.subTitle").html("");
		$("#blackPlate div.innerContent div.right div.subject div.link").html("");
		$("#blackPlate div.innerContent div.right div.subject div.data").html("");
		$("#blackPlate div.innerContent div.right div.period div.data").html("");
		$("#blackPlate div.innerContent div.right div.purpose div.data").html("");
		$("#blackPlate div.innerContent div.right div.collabo div.data").html("");
		$("#blackPlate div.innerContent div.right div.language div.data").html("");
		$("#blackPlate div.innerContent div.left").html("<div class=\"fotorama\" data-auto=\"false\"></div>");
	});
	
	postClick = false;
}