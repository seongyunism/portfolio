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

function listPost() {
	$.ajax({
		type : "POST",
		url : "board?action=listPost",
		dataType : "text",
			success: function(response) {
				if(response == "OK") {

				}
			}, error: function(xhr,status,error) {
				alert(error);
			}			
	});
}

function writePost() {
	
	var mode = $("#writePlate form.writeForm input[name='mode']").val();
	var action = "";
	
	if(writeClick) {
		if(mode == "new") { // 새글 작성 시
			action = "board?action=writePost";
		} else if(mode == "update") {
			action = "board?action=updatePost";
		} else {
			alert("잘못된 접근입니다.");
			return;
		}
		
		var postNo = $("#writePlate form.writeForm input[name='postNo']").val();			
		var postThumbnailAddr = $("#writePlate form.writeForm input[name='inputPostThumbnailAddr']").val();
		var postViewMode = $("#writePlate form.writeForm input[name='inputPostViewMode']:checked").val();
		var projectCategory = $("#writePlate form.writeForm input[name='inputProjectCategory']:checked").val();
		var projectTitle = $("#writePlate form.writeForm input[name='inputProjectTitle']").val();
		var projectSubTitle = $("#writePlate form.writeForm input[name='inputProjectSubTitle']").val();
		var projectPeriod = $("#writePlate form.writeForm input[name='inputProjectPeriod']").val();
		var projectPurpose = $("#writePlate form.writeForm input[name='inputProjectPurpose']").val();
		var projectCollabo = $("#writePlate form.writeForm input[name='inputProjectCollabo']").val();
		var projectLanguage = $("#writePlate form.writeForm input[name='inputProjectLanguage']").val();
		var projectDate = $("#writePlate form.writeForm input[name='inputProjectDate']").val();
		var projectLink = $("#writePlate form.writeForm input[name='inputProjectLink']").val();
		var projectMovAddr = $("#writePlate form.writeForm input[name='inputProjectMovAddr']").val();
		var projectMovPreview = $("#writePlate form.writeForm input[name='inputProjectMovPreview']").val();
		var projectImgAddr01 = $("#writePlate form.writeForm input[name='inputProjectImgAddr01']").val();
		var projectImgAddr02 = $("#writePlate form.writeForm input[name='inputProjectImgAddr02']").val();
		var projectImgAddr03 = $("#writePlate form.writeForm input[name='inputProjectImgAddr03']").val();
		var projectImgAddr04 = $("#writePlate form.writeForm input[name='inputProjectImgAddr04']").val();
		var projectMemo = $("#writePlate form.writeForm textarea[name='inputProjectMemo']").val();
			
//		var postThumbnailAddrFile = $("#writePlate form.writeForm textarea[name='inputPostThumbnailAddrFile']").files[0];
//		var projectMovPreviewFile = $("#writePlate form.writeForm textarea[name='inputProjectMovPreviewFile']").files[0];
//		var projectImgAddr01File = $("#writePlate form.writeForm textarea[name='inputProjectImgAddr01File']").files[0];
//		var projectImgAddr02File = $("#writePlate form.writeForm textarea[name='inputProjectImgAddr02File']").files[0];
//		var projectImgAddr03File = $("#writePlate form.writeForm textarea[name='inputProjectImgAddr03File']").files[0];
//		var projectImgAddr04File = $("#writePlate form.writeForm textarea[name='inputProjectImgAddr04File']").files[0];
			
		var form_data = {
			inputMode : mode,
					
			inputPostNo : postNo,
			inputPostThumbnailAddr : postThumbnailAddr,
//			inputPostThumbnailAddrFile : postThumbnailAddrFile,
			inputPostViewMode : postViewMode,
			inputProjectCategory : projectCategory,
			inputProjectTitle : projectTitle,
			inputProjectSubTitle : projectSubTitle,
			inputProjectPeriod : projectPeriod,
			inputProjectPurpose : projectPurpose,
			inputProjectCollabo : projectCollabo,
			inputProjectLanguage : projectLanguage,
			inputProjectDate : projectDate,
			inputProjectLink : projectLink,
			inputProjectMovAddr : projectMovAddr,
			inputProjectMovPreview : projectMovPreview,
//			inputProjectMovPreview : projectMovPreviewFile,
			inputProjectImgAddr01 : projectImgAddr01,
//			inputProjectImgAddr01File : projectImgAddr01File,
			inputProjectImgAddr02 : projectImgAddr02,
//			inputProjectImgAddr02File : projectImgAddr02File,
			inputProjectImgAddr03 : projectImgAddr03,
//			inputProjectImgAddr03File : projectImgAddr03File,
			inputProjectImgAddr04 : projectImgAddr04,
//			inputProjectImgAddr04File : projectImgAddr04File,
			inputProjectMemo : projectMemo
		};		
			
		$.ajax({
			type : "POST",
			url : action,
			data : form_data,
			contentType: "multipart/form-data",
			dataType : "text",
			success: function(response) {

			}, error: function(xhr,status,error) {
				alert(error);
			}
		});
				
		return false;

	} else {
		alert("잘못된 접근입니다.");
		return;
	}
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
	$("#writePlate div.innerContent div.left div.data input." + className).val(file);
	
	
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