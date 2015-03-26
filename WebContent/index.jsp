<%@ page import="java.util.ArrayList"%>
<%@ page import="seongyunism.model.MemberDAO" %>
<%@ page import="seongyunism.model.BoardDAO" %>
<%@ page import="seongyunism.model.domain.Board"%>
<%@ page import="seongyunism.model.domain.BoardCategory"%>
<%@ page import="seongyunism.model.domain.Guestbook"%>
<%@ page import="seongyunism.util.Convertor"%>
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
	var writeClick = false;
	var topMenuClick = false;
	var topMenuJoinTextClick = false;
	var viewPlateCommentFormOpenBtnClick = false;
	var leftMenuClick = false;
	var memberLoginCheck = false;
	var postTotalCommentsCount = 0;
	
	$(document).ready(function() {
		initialize();
	});

	$(window).load(function() { });
	
	$(function() {
		// 키보드 ESC 누를 시 이벤트 처리
		$(document).keyup(function (e) {
			if(e.keyCode == 27 && postClick == true) {
				viewPlateClose();
			}
		});
		
		// [컨텐츠 영역] 포스트박스 클릭 시 이벤츠 처리
		$("#content div.innerContent div.grid figure").click(function() {
			if(!postClick) {				

				var action = "board?action=viewPost";
				var postNo = "postNo=" + $(this).attr("name");
				
				$.ajax({
					type : "POST",
					url : action,
					data : postNo,
					dataType : "json",
					success: function(response) {
						$("#viewPlate div.innerContent").attr("name", response.pfNo);
						$("#viewPlate div.innerContent div.top div.title").html(response.pfProjectTitle.replace("\n",""));
						$("#viewPlate div.innerContent div.top div.subTitle").html(response.pfProjectSubTitle.replace("\n",""));
						$("#viewPlate div.innerContent div.right div.subject div.data").html(response.pfProjectTitle.replace("\n",""));
						$("#viewPlate div.innerContent div.right div.period div.data").html(response.pfProjectPeriod.replace("\n",""));	
						$("#viewPlate div.innerContent div.right div.purpose div.data").html(response.pfProjectPurpose.replace("\n",""));	
						$("#viewPlate div.innerContent div.right div.collabo div.data").html(response.pfProjectCollabo.replace("\n",""));	
						$("#viewPlate div.innerContent div.right div.language div.data").html(response.pfProjectLanguage.replace("\n",""));
						$("#viewPlate div.innerContent div.right div.memo div.data").html(response.pfProjectMemo.replace(/\n/g, "<br />"));
						
						if(response.pfProjectLink != "") {
							$("#viewPlate div.innerContent div.right div.subject div.link").html("<a href='" + response.pfProjectLink.replace("\n","") + "' target='_blank'>프로젝트 들어가기</a>");						
						}
						
						if(response.pfProjectMovAddr != "") {
							$("#viewPlate div.innerContent div.left div.fotorama").append("<a class='mov' href='" + response.pfProjectMovAddr.replace("\n","") + "'><img class='movPreview' src='" + response.pfProjectMovPreview.replace("\n","") + "' /></a>")
						}

						if(response.pfProjectImgAddr01 != "") {
							$("#viewPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr01 + "' />");		
						}
						
						if(response.pfProjectImgAddr02 != "") {
							$("#viewPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr02 + "' />");		
						}				

						if(response.pfProjectImgAddr03 != "") {
							$("#viewPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr03 + "' />");		
						}
						
						if(response.pfProjectImgAddr04 != "") {
							$("#viewPlate div.innerContent div.left div.fotorama").append("<img src='" + response.pfProjectImgAddr04 + "' />");		
						}					
						
						if(response.pfComments.length > 0) {
							postTotalCommentsCount = response.pfComments.length;
							var comments = "";
							
							for(i=0; i<response.pfComments.length; i++) {
								var comment = "<div class='comment'><div class='left'><div class='commentName'>" + response.pfComments[i].pfMemberName
									+ "</div><div class='commentDate'>(" + response.pfComments[i].pfCommentDate + ")</div></div><div class='right'><div class='commentMemo'>"
									+ response.pfComments[i].pfCommentMemo.replace(/\n/g, "<br />") + "</div></div></div>";
								comments += comment;
							}
							$("#viewPlate div.innerContent div.right div.comments div.list").show();
							$("#viewPlate div.innerContent div.right div.comments div.list").html(comments);
						} else {
							postTotalCommentsCount = 0;
							$("#viewPlate div.innerContent div.right div.comments div.list").hide();
							$("#viewPlate div.innerContent div.right div.comments div.button").css("margin-top", "20px");
						}
												
						$("#viewPlate div.innerContent div.left div.fotorama").fotorama();
						
						
					}, error: function(xhr,status,error) {
						alert(error);
					}
				});
				
				$("body").css("overflow-y", "hidden");
				$("#viewPlate").slideDown(500);
				$("#topMenu").fadeOut(500);
				postClick = true;

				return false;
			} else {
				viewPlateClose();
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

	<div id="writePlate">
		<form class="writeForm" action="board?action=writePost" method="post" enctype="multipart/form-data">
			<input type="hidden" name="inputMode" value="new" />
			<input type="hidden" name="inputPostNo" value="0" />
			
			<div class="innerContent">
				<div class="top">
					<div class="button">
						<div class="close" onclick="writePlateClose()"><img src="img/btnClose.png" /></div>
					</div>
					<div class="title"><input type="text" name="inputProjectTitle" class="inputProjectTitle" placeholder="프로젝트 타이틀"/></div>
					<div class="subTitle"><input type="text" name="inputProjectSubTitle" class="inputProjectSubTitle" placeholder="프로젝트 서브타이틀"/></div>
					<div class="margin"></div>
				</div>
				<div class="left">
					<div class="projectLink">
						<div class="column">관련 페이지</div>
						<div class="data"><input type="text" name="inputProjectLink" class="inputProjectLink" /></div>
					</div>
					<div class="projectDate">
						<div class="column">프로젝트 완료일</div>
						<div class="data"><input type="text" name="inputProjectDate" class="inputProjectDate" placeholder="필수 입력 사항 (YYYYMMDD)" onkeyPress="if((event.keyCode<48) || (event.keyCode>57)) event.returnValue=false;" maxlength=8/></div>
					</div>				
					<div class="projectPeriod">
						<div class="column">프로젝트 기간</div>
						<div class="data"><input type="text" name="inputProjectPeriod" class="inputProjectPeriod" maxlength="17" /></div>
					</div>
					<div class="projectPurpose">
						<div class="column">프로젝트 목적</div>
						<div class="data"><input type="text" name="inputProjectPurpose" class="inputProjectPurpose" /></div>
					</div>
					<div class="projectCollabo">
						<div class="column">협업사항</div>
						<div class="data"><input type="text" name="inputProjectCollabo" class="inputProjectCollabo" /></div>
					</div>
					<div class="projectLanguage">
						<div class="column">개발 언어</div>
						<div class="data"><input type="text" name="inputProjectLanguage" class="inputProjectLanguage" /></div>
					</div>
					<div class="postThumbnail">
						<div class="column">커버 이미지</div>
						<div class="data"><input type="text" name="inputPostThumbnailAddr" class="inputPostThumbnailAddr" readonly="readonly" /></div>
						<div class="button">찾아보기<input type="file" name="inputPostThumbnailAddrFile" class="inputPostThumbnailAddrFile" onchange="uploadBtnClick('inputPostThumbnailAddr')" /></div>
					</div>
					<div class="projectMov">
						<div class="column">영상 주소</div>
						<div class="data"><input type="text" name="inputProjectMovAddr" class="inputProjectMovAddr" /></div>
					</div>
					<div class="projectMovPreview">
						<div class="column">영상 프리뷰</div>
						<div class="data"><input type="text" name="inputProjectMovPreview" class="inputProjectMovPreview" readonly="readonly" /></div>
						<div class="button">찾아보기<input type="file" name="inputProjectMovPreviewFile" class="inputProjectMovPreviewFile" onchange="uploadBtnClick('inputProjectMovPreview')" /></div>
					</div>
					<div class="projectImgAddr01">
						<div class="column">이미지 01</div>
						<div class="data"><input type="text" name="inputProjectImgAddr01" class="inputProjectImgAddr01" readonly="readonly" /></div>
						<div class="button">찾아보기<input type="file" name="inputProjectImgAddr01File" class="inputProjectImgAddr01File" onchange="uploadBtnClick('inputProjectImgAddr01')" /></div>
					</div>	
					<div class="projectImgAddr02">
						<div class="column">이미지 02</div>
						<div class="data"><input type="text" name="inputProjectImgAddr02" class="inputProjectImgAddr02" readonly="readonly" /></div>
						<div class="button">찾아보기<input type="file" name="inputProjectImgAddr02File" class="inputProjectImgAddr02File" onchange="uploadBtnClick('inputProjectImgAddr02')" /></div>
					</div>			
					<div class="projectImgAddr03">
						<div class="column">이미지 03</div>
						<div class="data"><input type="text" name="inputProjectImgAddr03" class="inputProjectImgAddr03" readonly="readonly" /></div>
						<div class="button">찾아보기<input type="file" name="inputProjectImgAddr03File" class="inputProjectImgAddr03File" onchange="uploadBtnClick('inputProjectImgAddr03')" /></div>
					</div>					
					<div class="projectImgAddr04">
						<div class="column">이미지 04</div>
						<div class="data"><input type="text" name="inputProjectImgAddr04" class="inputProjectImgAddr04" readonly="readonly" /></div>
						<div class="button">찾아보기<input type="file" name="inputProjectImgAddr04File" class="inputProjectImgAddr04File" onchange="uploadBtnClick('inputProjectImgAddr04')" /></div>
					</div>
				</div>
				<div class="right">
					<div class="postViewMode">
						<div class="column">화면 출력 여부</div>
						<div class="data"><input type="radio" name="inputPostViewMode" value="0" checked />&nbsp;공개 모드&nbsp;&nbsp;&nbsp;<input type="radio" name="inputPostViewMode" value="1" />&nbsp;관리자 모드&nbsp;&nbsp;&nbsp;<input type="radio" name="inputPostViewMode" value="2" />&nbsp;비출력</div>
					</div>
					<div class="projectCategory">
						<div class="column">카테고리</div>
						<div class="data"><input type="radio" name="inputProjectCategory" value="1" checked />&nbsp;웹 퍼블리싱&nbsp;&nbsp;&nbsp;<input type="radio" name="inputProjectCategory" value="2" />&nbsp;영상 제작&nbsp;&nbsp;&nbsp;<input type="radio" name="inputProjectCategory" value="3" />&nbsp;북 퍼블리싱</div>
					</div>
					<div class="projectMemo">
						<div class="column">메모</div>
						<div class="data"><textarea name="inputProjectMemo" class="inputProjectMemo"></textarea></div>
					</div>
				</div>
				<div class="bottom">
					<input type="submit" value="서버로 전송" />
				</div>
			</div>
		</form>
	</div>

	<div id="viewPlate">
		<div class="innerContent" name="">
			<div class="top">
				<div class="button">
					<div class="close" onclick="viewPlateClose()"><img src="img/btnClose.png" /></div>
					<c:if test="${sessionScope.pfMemberAdmin == true}"><div class="delete" onclick="viewPlateDelete()">삭제</div></c:if>
					<c:if test="${sessionScope.pfMemberAdmin == true}"><div class="modify" onclick="viewPlateUpdate()">수정</div></c:if>
				</div>
				<div class="title"></div>
				<div class="subTitle"></div>
				<div class="margin"></div>
			</div>
			<div class="left" name="slide">
				<div class="fotorama" data-auto="false"></div>
			</div>
			<div class="right">
				<div class="subject">
					<div class="column">프로젝트 타이틀</div>
					<div class="data"></div>
					<div class="link"></div>
				</div>
				<div class="period">
					<div class="column">프로젝트 기간</div>
					<div class="data"></div>
				</div>
				<div class="purpose">
					<div class="column">프로젝트 목적</div>
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
				<div class="comments">
					<div class="list"></div>
					<div class="form"><textarea class="memo"></textarea></div>
					<div class="button">
						<input type="button" class="commentFormCloseBtn" value="취소" onclick="commentFormOpenBtnClick(false)" />
						<input type="button" class="commentFormOpenBtn" value="덧글달기" onclick="commentFormOpenBtnClick(true)" />
					</div>
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
			<c:if test="${empty sessionScope.pfMemberNo}"><span class="joinText" onclick="joinTextClick()">회원가입</span><span class="loginText" onclick="loginTextClick()">로그인</span></c:if>
			<c:if test="${not empty sessionScope.pfMemberNo}"><c:if test="${sessionScope.pfMemberAdmin == true}"><span class="writeText" onclick="writeTextClick()">새글작성</span></c:if><span class="logoutText" onclick="logoutMember()">로그아웃</span></c:if>
			<span class="guestbookText" onclick="guestbookTextClick()">방명록</span>
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

	<div id="rightGuestbook">
		<div class="title">
			<div class="margin"></div>
			<div class="text">당신은 나에게, 나는 당신에게</div>
			<div class="close"><span class="closeText" onclick="guestbookClose()">닫기</span></div>
		</div>
		<form>
			<div class="form">
				<div class="name"><input type="text" name="inputGuestMemberName" class="inputGuestMemberName" placeholder="이름 혹은 별명"/></div>
				<div class="password"><input type="password" name="inputGuestMemberPassword" class="inputGuestMemberPassword" placeholder="비밀번호" 	/></div>
				<div class="memo"><textarea name="inputGuestPostMemo" class="inputGuestPostMemo"></textarea></div>
			</div>
			<div class="button"><input type="button" class="inputGuestSubmitBtn" value="서버로 전송" onclick="guestbookWrite()" /></div>
		</form>	
		<div class="list">
			<% ArrayList<Guestbook> guestbookList = BoardDAO.getListGuestPost(); for(int i=0; i<guestbookList.size(); i++) { %>
			<div class="guestPost" name="<%=guestbookList.get(i).getPfNo() %>">
				<div class="memo"><%=guestbookList.get(i).getPfGuestPostMemo() %></div>
				<div class="bottom"><span class="name"><%=guestbookList.get(i).getPfGuestMemberName() %></span>&nbsp;<span class="at">@</span>&nbsp;<span class="date"><%=Convertor.toConvertTimeFromUnixTime_Guest(guestbookList.get(i).getPfGuestPostDate()) %></span></div>
			</div>	
			<% } %>
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
			
   			<!--
   			<div class="subContent">
	    		<div class="subTitle"><%=BoardDAO.getCategoryName(2).getPfCategoryNameKor()%> | <%=BoardDAO.getCategoryName(2).getPfCategoryName()%></div>
				<div class="grid">
					<c:forEach items="${requestScope.list}" var="list2">
					<c:out value="${list2.pfNo}" />
						<figure class="effect-bubba" name="<c:out value='${list.pfNo}' />">
							<img src="<c:out value='${list2.pfPostThumbnailAddr}' />" />
							<figcaption>
								<h2><c:out value="${list2.pfProjectTitle}" /></h2>
								<p><c:out value="${list2.pfProjectSubTitle}" /></p>
								<a href="">View more</a>
							</figcaption>			
						</figure>
					</c:forEach>
				</div>	
   			</div>
   			-->
   			
    	</div>
    </div>

    <script src="js/jquery.pageslide.min.js"></script>
    <script>$("#content div.title a.menu").pageslide({ direction: "right", modal: true });</script>
 
</body>
</html>