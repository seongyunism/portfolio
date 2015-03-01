<%@page import="java.util.ArrayList"%>
<%@ page import="seongyunism.model.MemberDAO" %>
<%@ page import="seongyunism.model.BoardDAO" %>
<%@ page import="seongyunism.model.domain.Board"%>
<%@ page import="seongyunism.model.domain.BoardCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.sql.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<title>PERSONAL PORTFOLIO # SEONGYUNISM # 890313.com</title>
	<meta charset="UTF-8" />
	<meta name="author" content="seongyunism" />
	<meta name="description" content="This page is a my personal portfolio" />
	<meta name="keywords" content="portfolio, web design" />
	<script src="js/script.js"></script>
    <script src="js/jquery-1.11.0.min.js"></script>
    <script src="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.5.2/fotorama.js"></script>
	<script>
	var postClick = false;
	var topMenuClick = false;
	var topMenuJoinTextClick = false;
	var leftMenuClick = false;
	var memberLoginCheck = false;
	
	$(document).ready(function() {
		titleImageInit();
		$(window).resize(titleImageInit); // 리사이즈될때마다 타이틀 사이즈 재조정
	});

	$(window).load(function() { });
	
	$(function() {
		// [컨텐츠 영역] 포스트박스 클릭 시 이벤츠 처리
		$("#content div.innerContent div.grid figure").click(function() {
			if(!postClick) {				

				var action = "board?action=postView";
				var postNo = "postNo=" + $(this).attr("name");
				
				$.ajax({
					type : "POST",
					url : action,
					data : postNo,
					dataType : "json",
					success: function(response) {
						$("#blackPlate div.innerContent div.top div.title").html(response.pfProjectTitle.replace("\n",""));
						$("#blackPlate div.innerContent div.top div.subTitle").html(response.pfProjectSubTitle.replace("\n",""));
						$("#blackPlate div.innerContent div.right div.subject div.data").html(response.pfProjectTitle.replace("\n",""));
						$("#blackPlate div.innerContent div.right div.period div.data").html(response.pfProjectPeriod.replace("\n",""));	
						$("#blackPlate div.innerContent div.right div.purpose div.data").html(response.pfProjectPurpose.replace("\n",""));	
						$("#blackPlate div.innerContent div.right div.collabo div.data").html(response.pfProjectCollabo.replace("\n",""));	
						$("#blackPlate div.innerContent div.right div.language div.data").html(response.pfProjectLanguage.replace("\n",""));
						
						if(response.pfProjectLink != "") {
							$("#blackPlate div.innerContent div.right div.subject div.link").html("<a href='" + response.pfProjectLink.replace("\n","") + "' target='_blank'>프로젝트 들어가기</a>");						
						}
						
						if(response.pfProjectMovAddr != "") {
							$("#blackPlate div.innerContent div.left div.fotorama").append("<a class='mov' href='" + response.pfProjectMovAddr.replace("\n","") + "'><img class='movPreview' src='" + response.pfProjectMovPreview.replace("\n","") + "' /></a>")
						}

						if(response.pfProjectImgAddr01 != "") {
							$("#blackPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr01 + "' />");		
						}
						
						if(response.pfProjectImgAddr02 != "") {
							$("#blackPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr02 + "' />");		
						}				

						if(response.pfProjectImgAddr03 != "") {
							$("#blackPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr03 + "' />");		
						}
						
						if(response.pfProjectImgAddr04 != "") {
							$("#blackPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr04 + "' />");		
						}					
						
						$("#blackPlate div.innerContent div.left div.fotorama").fotorama();
						
						
					}, error: function(xhr,status,error) {
						alert(error);
					}
				});
				
				$("body").css("overflow-y", "hidden");
				$("#blackPlate").slideDown(500);
				$("#topMenu").fadeOut(500);
				postClick = true;

				return false;
			} else {
				blackplateClose();
			}
		});
			
		// [상단 메뉴 영역] 로그인 버튼 클릭 시 이벤트 처리
		$("#topMenu div.topButton span.loginText").click(function() {
			if(!topMenuClick) {
				topMenuSlider(true);
			} else {
				topMenuSlider(false);
			}
		});
	});

	</script>
	<link rel="stylesheet" type="text/css" href="css/style.css" />
	<link rel="stylesheet" type="text/css" href="css/boxComponent.css" />
	<link rel="stylesheet" type="text/css" href="http://portfolio.890313.com/include/css/applyCharacter_8080.css" />
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Raleway:400,800,300" />
	<link rel="stylesheet" type="text/css" href="http://cdnjs.cloudflare.com/ajax/libs/fotorama/4.5.2/fotorama.css" />
</head>

<body>

	<div id="blackPlate">
		<div class="innerContent">
			<div class="top">
				<div class="button">
					<div class="close" onclick="blackplateClose()"><img src="img/btnClose.png" /></div>
					<div class="delete">삭제</div>
					<div class="modify">수정</div>
				</div>
				<div class="title"></div>
				<div class="subTitle"></div>
				<div class="margin"></div>
			</div>
			<div class="left">
				<div class="fotorama" data-auto="false"></div>
			</div>
			<div class="right">
				<div class="subject">
					<div class="column">프로젝트명</div>
					<div class="data"></div>
					<div class="link"></div>
				</div>
				<div class="period">
					<div class="column">프로젝트기간</div>
					<div class="data"></div>
				</div>
				<div class="purpose">
					<div class="column">프로젝트목적</div>
					<div class="data"></div>
				</div>
				<div class="collabo">
					<div class="column">협업사항</div>
					<div class="data"></div>
				</div>
				<div class="language">
					<div class="column">개발 언어</div>
					<div class="data"></div>
				</div>
				<div class="memo">
					<div class="column">메모</div>
					<div class="data"></div>
				</div>
			</div>	
		</div>
	</div>

	<div id="topMenu">
		<div class="topBar">
			<form method="post">
				<div class="loginForm">
					<div class="loginEmail"><input type="text" name="inputMemberEmail" class="inputMemberEmail" placeholder="이메일 주소" /></div>
					<div class="loginPassword"><input type="password" name="inputMemberPassword" class="inputMemberPassword" placeholder="비밀번호" /></div>
					<div class="loginBtn"><input type="button" class="inputLoginBtn" value="로그인하기" onclick="loginMember()" /></div>
				</div>
				<div class="joinForm">
					<div class="joinEmail"><input type="text" name="inputMemberEmail" class="inputMemberEmail" placeholder="이메일 주소" /></div>
					<div class="joinPassword"><input type="password" name="inputMemberPassword" class="inputMemberPassword" placeholder="비밀번호" /></div>
					<div class="joinName"><input type="text" name="inputMemberName" class="inputMemberName" placeholder="이름 혹은 별명" /></div>
					<div class="joinBtn"><input type="button" class="inputJoinBtn" value="회원가입하기" onclick="joinMember()" /></div>
				</div>
			</form>
		</div>
		<div class="topButton">
			<c:if test="${empty sessionScope.pfMemberNo}"><span class="joinText" onclick="joinTextClick()">회원가입</span><span class="loginText">로그인</span></c:if>
			<c:if test="${not empty sessionScope.pfMemberNo}"><span class="logoutText" onclick="logoutMember()">로그아웃</span></c:if>
		</div>
	</div>

    <div id="leftMenu">
        <div class="title">
        	<div class="text">PELSONAL PORTFOLIO</div>
        	<div class="button"><a href="javascript:$.pageslide.close()">CLOSE</a></div>
        </div>
        <div class="profileImage">
        	<img src="img/menu_profileImage.jpg" />
        </div>
  
  		<div class="name">Seong Gyun, <span style="font-weight:bold;">Jeon</span> <span style="font-size:0.7em; color:#666666;">(1989.03.13)</span></div>
  
  		<div class="summary">&nbsp;&nbsp;&nbsp;I have been currently working as an <span style="font-weight:Bold;">System Engineer</span> of Daou Technology since May in 2014. At the same time, I have been studying as an <span style="font-weight:Bold;">Senior Student</span> in Software Engineering at Dankook University.</div>
  
  		<div class="spec">
  			<h3>Main Career</h3>
  			<h4>+ 2010.06 - 2010.12 | Computer Security Team, Bae, Kim & Lee</h4>
			<h4>+ 2011.01 - 2012.10 | Criminal Investigaion Group, R.O.K Army</h4>
			<h4>+ 2012.12 - 2013.10 | System Operating Team, XLGames</h4>
			<h4>+ 2014.05 - 2015.02 | Technology Support Team, Daou Tech <span class="present">(present)</span></h4>

			<h3 style="margin-top:10px;">Technical Capability</h3>
			<h4>+ System : C, C++, JAVA</h4>
			<h4>+ Backend : Redhat Linux, Windows Server<!-- , OpenStack, Docker, Hadoop--></h4>
			<h4>+ Frontend : HTML5, CSS3, PHP5, JSP, Javascript, jQuery, AJAX, JSON</h4>
			<h4>+ Graphic : After Effect CS6, Photoshop CS6, 3ds Max 2009</h4>
			<h4>+ Office: Access, Excel, PowerPoint, Word</h4>
			
			<h3 style="margin-top:10px;">Contact</h3>
			<h4>+ Official E-mail :&nbsp;&nbsp;<a href="mailto:goodjsg@naver.com">goodjsg [at] naver.com</a></h4>
			<h4>+ Homepage :&nbsp;&nbsp;<a href="http://890313.com" target="_blank">http://890313.com</a></h4>

  		</div>

    </div>

    <div id="content">
    	<div class="title"><a href="#leftMenu" class="menu"><img class="title" /></a></div>
    	<div class="innerContent">
   			<div class="subContent">
	    		<div class="subTitle"><%=BoardDAO.getCategoryName(1).getPfCategoryNameKor()%> | <%=BoardDAO.getCategoryName(1).getPfCategoryName()%></div>
				<div class="grid">

					<% ArrayList<Board> postList = BoardDAO.getList(1); for(int i=0; i<postList.size(); i++) { %>
						<figure class="effect-sarah" name="<%=postList.get(i).getPfNo()%>">
							<img src="<%=postList.get(i).getPfPostThumbnailAddr() %>" />
							<figcaption>
								<h2><%=postList.get(i).getPfProjectTitle() %></h2>
								<p><%=postList.get(i).getPfProjectSubTitle() %></p>
								<a href="">View more</a>
							</figcaption>			
						</figure>
					<% } %>
				</div>
			</div>

   			<div class="subContent">
	    		<div class="subTitle"><%=BoardDAO.getCategoryName(2).getPfCategoryNameKor()%> | <%=BoardDAO.getCategoryName(2).getPfCategoryName()%></div>
				<div class="grid">
					<% ArrayList<Board> postList2 = BoardDAO.getList(2); for(int i=0; i<postList2.size(); i++) { %>
						<figure class="effect-bubba" name="<%=postList2.get(i).getPfNo()%>">
							<img src="<%=postList2.get(i).getPfPostThumbnailAddr() %>" />
							<figcaption>
								<h2><%=postList2.get(i).getPfProjectTitle() %></h2>
								<p><%=postList2.get(i).getPfProjectSubTitle() %></p>
								<a href="">View more</a>
							</figcaption>			
						</figure>
					<% } %>
				</div>		
			</div>
			
   			<div class="subContent">
   			</div>
    	</div>
    </div>

    <script src="js/jquery.pageslide.min.js"></script>
    <script>$(".menu").pageslide({ direction: "right", modal: true });</script>
 
</body>
</html>