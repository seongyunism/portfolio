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

//[상단메뉴 영역] 로그인 버튼 클릭 시 이벤트 처리
function loginTextClick() {
	if(!topMenuClick) {
		topMenuSlider(true);
	} else {
		topMenuSlider(false);
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
			} else if(response == "NotEmail") {
				alert("잘못된 이메일 입니다.");
			} else {
				alert("회원가입에 실패하였습니다.");
			}
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});
		
	return false;
}

// [로그인 영역] 새글작성 버튼 클릭 시 이벤트 처리
function writeTextClick() {
	$("body").css("overflow-y", "hidden");
	$("#writePlate").slideDown(500);
	$("#topMenu").fadeOut(500);
	writeClick = true;
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

// [쓰기판 영역] 닫기 버튼 클릭 시 이벤트 처리
function writePlateClose() {
	$("#topMenu").fadeIn(500);
	$("body").css("overflow-y", "auto");
	
	$("#writePlate").slideUp(500, function() {
		$("#writePlate div.innerContent div.top input").val("");
		$("#writePlate div.innerContent div.left input").val("");
		$("#writePlate div.innerContent div.right input").val("");
		$("#writePlate div.innerContent div.right textarea").val("");
	});
	
	writeClick = false;
}

// [쓰기판 영역] 업로드 버튼 클릭 시 이벤트 처리
function uploadBtnClick(className) {
	var file = $("#writePlate div.innerContent div.left div.button input." + className + "File").val();
	$("#writePlate div.innerContent div.left div.data input." + className).val(file.replace("C:\\fakepath\\", ""));
}

// [보기판 영역] 수정 버튼 클릭 시 이벤트 처리
function viewPlateUpdate() {
	
	viewPlateClose();
	
	var action = "board?action=viewPost";
	var postNo = "postNo=" + $("#viewPlate div.innerContent").attr("name");
		
	$.ajax({
		type : "POST",
		url : action,
		data : postNo,
		dataType : "json",
		success: function(response) {
			$("#writePlate input[name='inputMode']").val("update");
			$("#writePlate input[name='inputPostNo']").val(response.pfNo);
			$("#writePlate div.innerContent input[name='inputProjectTitle']").val(response.pfProjectTitle.replace("\n",""));
			$("#writePlate div.innerContent input[name='inputProjectSubTitle']").val(response.pfProjectSubTitle.replace("\n",""));
			$("#writePlate div.innerContent input[name='inputProjectLink']").val(response.pfProjectLink.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectDate']").val(response.pfProjectDate);			
			$("#writePlate div.innerContent input[name='inputProjectPeriod']").val(response.pfProjectPeriod.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectPurpose']").val(response.pfProjectPurpose.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectCollabo']").val(response.pfProjectCollabo.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectLanguage']").val(response.pfProjectLanguage.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputPostThumbnailAddr']").val(response.pfPostThumbnailAddr.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectMovAddr']").val(response.pfProjectMovAddr.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectMovPreview']").val(response.pfProjectMovPreview.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectImgAddr01']").val(response.pfProjectImgAddr01.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectImgAddr02']").val(response.pfProjectImgAddr02.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectImgAddr03']").val(response.pfProjectImgAddr03.replace("\n",""));			
			$("#writePlate div.innerContent input[name='inputProjectImgAddr04']").val(response.pfProjectImgAddr04.replace("\n",""));
			$("#writePlate div.innerContent input[name='inputPostViewMode']:input[value='" + response.pfPostViewMode + "']").attr("checked", true);
			$("#writePlate div.innerContent input[name='inputProjectCategory']:input[value='" + response.pfProjectCategory + "']").attr("checked", true);
			$("#writePlate div.innerContent textarea[name='inputProjectMemo']").val(response.pfProjectMemo.replace("\n",""));
		}, error: function(xhr,status,error) {
			alert(error);
		}
	});

	writeTextClick();

	return false;	
}

// [보기판 영역] 삭제 버튼 클릭 시 이벤트 처리
function viewPlateDelete() {
	
	check = confirm("정말로 삭제하시겠습니까?");

	if(!check) {
		return false;
	} else {

		var action = "board?action=deletePost";
		var postNo = "postNo=" + $("#viewPlate div.innerContent").attr("name");
	
		$.ajax({
			type : "POST",
			url : action,
			data : postNo,
			dataType : "text",
			success: function(response) {
				if(response == "DeleteOK") {
					alert("성공적으로 삭제되었습니다.");
					location.reload(true);
				} else {
					alert("잘못된 접근입니다.");
				}
			}, error: function(xhr,status,error) {
				alert(error);
			}
		});
	
		return false;	
	}
}

// [보기판 영역] 닫기 버튼 클릭 시 이벤트 처리
function viewPlateClose() {
	$("#topMenu").fadeIn(500);
	$("body").css("overflow-y", "auto");
	
	$("#viewPlate").slideUp(500, function() {
		$("#viewPlate div.innerContent div.top div.title").html("");
		$("#viewPlate div.innerContent div.top div.subTitle").html("");
		$("#viewPlate div.innerContent div.right div.subject div.link").html("");
		$("#viewPlate div.innerContent div.right div.subject div.data").html("");
		$("#viewPlate div.innerContent div.right div.period div.data").html("");
		$("#viewPlate div.innerContent div.right div.purpose div.data").html("");
		$("#viewPlate div.innerContent div.right div.collabo div.data").html("");
		$("#viewPlate div.innerContent div.right div.language div.data").html("");
		$("#viewPlate div.innerContent div.left").html("<div class=\"fotorama\" data-auto=\"false\"></div>");
	});
	
	postClick = false;
}