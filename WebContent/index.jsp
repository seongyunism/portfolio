<%@ page import="seongyunism.model.domain.Board"%>
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
    <script src="js/jquery-1.7.2.min.js"></script>
	<script>
	var postClick = false;
	var leftMenuClick = false;
	var profileImageClick = false;
	
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

				var action = "post?action=postView";
//				var action = "page/ajaxGetPost.jsp";
				var postNo = "postNo=1";
				
				$.ajax({
					type : "POST",
					url : action,
					data : postNo,
					dataType : "json",
					success: function(response) {
						$("#blackPlate div.innerContent div.top div.title").html("<br />" + response.pfProjectTitle.replace("\n",""));
						$("#blackPlate div.innerContent div.top div.subTitle").text(response.pfProjectSubTitle.replace("\n",""));
						$("#blackPlate div.innerContent div.right div.subject div.data").html(response.pfProjectTitle.replace("\n",""));	
						$("#blackPlate div.innerContent div.right div.period div.data").html(response.pfProjectPeriod.replace("\n",""));	
						$("#blackPlate div.innerContent div.right div.purpose div.data").html(response.pfProjectPurpose.replace("\n",""));	
						$("#blackPlate div.innerContent div.right div.collabo div.data").html(response.pfProjectCollabo.replace("\n",""));	
						
					}, error: function(xhr,status,error) {
						alert(error);
					}
				});
				
				$("#blackPlate").slideDown(500);
				postClick = true;

				return false;
			} else {
				$("#blackPlate").slideUp(500);
				postClick = false;
			}
		});
		
		// [블랙판 영역] 닫기 버튼 클릭 시 이벤트 처리
		$("#blackPlate div.innerContent div.top div.button div.close").click(function() {
			$("#blackPlate").slideUp(500);
			postClick = false;
		});
		
		// [사이드바 영역] 프로필 사진 클릭 시 이벤트 처리
		$("#leftMenu div.profileImage img").click(function() {
			if(!profileImageClick) {
				if(!memberLoginCheck) {
					$("#leftMenu div.profileImage div.button div.login").show();
				} else {
					$("#leftMenu div.profileImage div.button div.logged").show();					
				}
				profileImageClick = true;
			} else {
				if(!memberLoginCheck) {
					$("#leftMenu div.profileImage div.button div.login").hide();
				} else {
					$("#leftMenu div.profileImage div.button div.logged").hide();					
				}
				profileImageClick = false;			
			}
		});
		
	});
	
	// 브라우저 가로 사이즈에 따른 타이틀 이미지 지정
	function titleImageInit() {
		if($("html").width() > 1440) {
			$("#content div.title img.title").attr("src", "img/title_1920.png");
		} else {
			$("#content div.title img.title").attr("src", "img/title_1440.png");			
		}
	}	
	</script>
	<link rel="stylesheet" type="text/css" href="css/boxComponent.css" />
	<link rel="stylesheet" type="text/css" href="http://portfolio.890313.com/include/css/applyCharacter.css" />
	<link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Raleway:400,800,300" />
	<style>
	html, body {width:100%; height:100%; margin:0px; padding:0px;}

    body {background:url('img/background.jpg'); background-attachment:fixed; background-repeat:no-repeat; background-position:center; background-size:cover; -webkit-font-smoothing:antialiased; -webkit-text-size-adjust:none; overflow-x:hidden;}

	a, h1, h2, h3, h4 {margin:0px; padding:0px;}

	/* 브라우저 크기가 1440px 이하일 경우 */
	@media screen and (max-width:1440px) {
		#blackPlate div.innerContent {width:1310px; height:737px; margin:-368.5px 0px 0px -655px;}
		#blackPlate div.innerContent div.top {height:237px;}
		#blackPlate div.innerContent div.top div.button {height:48px;}
		#blackPlate div.innerContent div.top div.title {height:91px; line-height:45px;}
		#blackPlate div.innerContent div.top div.subTitle {height:50px; line-height:50px;}
		#blackPlate div.innerContent div.top div.margin {height:48px;}
		#blackPlate div.innerContent div.left {width:500px; height:500px;}
		#blackPlate div.innerContent div.left div.slideImage img {width:452px;}
		#blackPlate div.innerContent div.right {width:810px;}
		
		#pageslide {width:340px;}

		#content {width:1180px;}
		#content div.title {height:210px;}
		#content div.innerContent div.grid {padding:16px 0px 0px 16px;}
		#content div.innerContent div.grid figure {width:372px; height:400px; margin:0px 11px 11px 0px; }
		
		#leftMenu div.profileImage img {width:300px;}
		#leftMenu div.profileImage div.button div.login div.loginBtn {width:300px;}
	}

	/* 브라우저 크기가 1440px 초과할 경우 */
	@media screen and (min-width:1441px) { 
		#blackPlate div.innerContent {width:1748px; height:983px; margin:-491.5px 0px 0px -874px;}
		#blackPlate div.innerContent div.top {height:283px;}
		#blackPlate div.innerContent div.top div.button {height:48px;}
		#blackPlate div.innerContent div.top div.title {height:121px}
		#blackPlate div.innerContent div.top div.subTitle {height:66px; line-height:66px;}
		#blackPlate div.innerContent div.top div.margin {height:48px;}
		
		#pageslide {width:453px;}

		#content {width:1574px;}
		#content div.title {height:280px;}
		#content div.innerContent div.grid {padding:20px 0px 0px 20px;}
		#content div.innerContent div.grid figure {width:498px; height:400px; margin:0px 15px 15px 0px; }
		
		#leftMenu div.profileImage img {width:413px;}
		#leftMenu div.profileImage div.button {width:413px;}		
	}

	#blackPlate {position:fixed; width:100%; height:100%; background:rgba(0, 0, 0, 0.3); z-index:10; display:none; cursor:pointer;}
	#blackPlate div.innerContent {position:absolute; top:50%; left:50%; background:rgba(0, 0, 0, 0.85); box-shadow:0px 0px 3px 1px rgba(0, 0, 0, 0.3);}
	#blackPlate div.innerContent div.top {width:100%;}
	#blackPlate div.innerContent div.top div.button {height:48px;}
	#blackPlate div.innerContent div.top div.button div.modify {width:72px; height:48px; float:right; font-size:1.1em; text-align:center; line-height:48px; color:#888888;}
	#blackPlate div.innerContent div.top div.button div.modify:hover {color:#eeeeee; background:rgba(255, 255, 255, 0.05);}
	#blackPlate div.innerContent div.top div.button div.delete {width:72px; height:48px; float:right; font-size:1.1em; text-align:center; line-height:48px; color:#888888;}		
	#blackPlate div.innerContent div.top div.button div.delete:hover {color:#eeeeee; background:rgba(255, 255, 255, 0.05);}
	#blackPlate div.innerContent div.top div.button div.close {width:48px; height:48px; float:right; text-align:center; line-height:48px;}
	#blackPlate div.innerContent div.top div.button div.close:hover {background:rgba(255, 255, 255, 0.05);}
	#blackPlate div.innerContent div.top div.button div.close img {width:28px; height:28px; margin:10px;}
	#blackPlate div.innerContent div.top div.title {clear:both; font-family:MyriadPro; font-size:2em; font-weight:bold; color:#eeeeee; padding-left:48px;}
	#blackPlate div.innerContent div.top div.subTitle {clear:both; font-family:MyriadPro; font-size:1.3em; color:#eeeeee; padding-left:48px; background:rgba(255, 255, 255, 0.02);}
	#blackPlate div.innerContent div.top div.margin {clear:both;}
	#blackPlate div.innerContent div.left {clear:both; float:left;}
	#blackPlate div.innerContent div.left div.slideImage {padding-left:48px;}
	#blackPlate div.innerContent div.left div.slideImage img {}
	#blackPlate div.innerContent div.right {float:right; padding:0px 10px 0px 10px;}	
	#blackPlate div.innerContent div.right div.subject {height:34px; font-family:NanumGothic; font-size:1.2em; line-height:34px; color:#ffffff; border-bottom:1px solid #1a1a1a;}
	#blackPlate div.innerContent div.right div.period {height:34px; font-family:NanumGothic; font-size:1.2em; line-height:34px; color:#ffffff; border-bottom:1px solid #1a1a1a;}
	#blackPlate div.innerContent div.right div.purpose {height:34px; font-family:NanumGothic; font-size:1.2em; line-height:34px; color:#ffffff; border-bottom:1px solid #1a1a1a;}
	#blackPlate div.innerContent div.right div.collabo {height:34px; font-family:NanumGothic; font-size:1.2em; line-height:34px; color:#ffffff; border-bottom:1px solid #1a1a1a;}
	#blackPlate div.innerContent div.right div.development {height:34px; font-family:NanumGothic; font-size:1.2em; line-height:34px; color:#ffffff; border-bottom:1px solid #1a1a1a;}
	#blackPlate div.innerContent div.right div.memo {}
	
	#blackPlate div.innerContent div.right div.column {width:120px; height:34px; float:left; font-family:NanumGothic; font-size:0.8em; font-weight:bold; text-align:center; line-height:34px;}	
	#blackPlate div.innerContent div.right div.data {float:left;}
	
    #pageslide{position:fixed; top:0px; height:100%; color:#ffffff; background:rgba(255, 255, 255, 0.3); padding:20px; display:none; z-index:999999; -webkit-box-shadow:0px 0px 6px 3px rgba(0, 0, 0, 0.1); -moz-box-shadow:0px 0px 6px 3px rgba(0, 0, 0, 0.1); box-shadow:0px 0px 6px 3px rgba(0, 0, 0, 0.1);}

    #content {padding:0px; margin:auto; margin-bottom:50px;}
    #content div.title {text-align:center; }
    #content div.title a {}
    #content div.title img.title {border:0px; outline:0px;}
    #content div.innerContent {background:rgba(0, 0, 0, 0.1); box-shadow:0px 0px 5px 1px rgba(0, 0, 0, 0.1);}
    #content div.innerContent div.subTitle {font-family:Helvetica; font-size:1.5em; font-weight:100; text-shadow:1px 1px 0px rgba(255, 255, 255, 0.5); height:40px; line-height:40px; background:rgba(0, 0, 0, 0.05); padding-left:10px;}
	#content div.innerContent div.grid {width:100%; list-style:none; margin:0px; overflow:hidden;}
	#content div.innerContent div.grid figure {position:relative; font-family: 'Raleway', Arial, sans-serif; text-align:center; box-shadow:0px 0px 3px 1px rgba(0, 0, 0, 0.15); padding:0px; display:inline-block; overflow:hidden; z-index:1; cursor:pointer; overflow: hidden;}
	#content div.innerContent div.grid figure img {position:absolute; left:50%; top:50%; width:auto; height:100%; -webkit-transform:translate(-50%,-50%); -ms-transform:translate(-50%,-50%); transform:translate(-50%,-50%);}

    #leftMenu {display:none;}
    #leftMenu a{font-weight:bold; color:#333; background:rgba(0, 0, 0, 0.1); border:none; padding:5px 10px;}
    #leftMenu a:hover{background:#aaa;}
    #leftMenu div.title {font-family:Helvetica; font-size:1.2em; font-weight:600; text-shadow:1px 1px 0px rgba(255, 255, 255, 0.5); color:#000000; margin:0px 0px 20px 0px; padding:0px;}
	#leftMenu div.profileImage {line-height:0px;}
	#leftMenu div.profileImage img {box-shadow:0px 0px 3px 1px rgba(0, 0, 0, 0.3); cursor:pointer;}
	#leftMenu div.profileImage div.button {clear:both;}
	#leftMenu div.profileImage div.button div.login {display:none;}
	#leftMenu div.profileImage div.button div.login div.loginBtn {height:40px; font-family:NanumGothic; font-size:0.9em; text-align:center; line-height:40px; color:#333333; background:rgba(255, 255, 255, 0.4); box-shadow:0px 0px 3px 1px rgba(0, 0, 0, 0.05); cursor:pointer;}	
	#leftMenu div.profileImage div.button div.logged {display:none;}	
	#leftMenu div.profileImage div.button div.logged div.logoutBtn {height:40px; float:left; line-height:40px;}
	#leftMenu div.profileImage div.button div.logged div.newPostBtn {height:40px; float:left; line-height:40px;}	
	#leftMenu div.name {height:30px; clear:both; font-family:Helvetica; font-size:1.4em; font-weight:100; letter-spacing:-1px; text-align:right; color:#000000; text-shadow:1px 1px 0px rgba(255, 255, 255, 0.5); padding-top:20px;}
				
    </style>
</head>

<body>

	<div id="blackPlate">
		<div class="innerContent">
			<div class="top">
				<div class="button">
					<div class="close"><img src="img/btnClose.png" /></div>
					<div class="delete">삭제</div>
					<div class="modify">수정</div>
				</div>
				<div class="title"><br /></div>
				<div class="subTitle"></div>
				<div class="margin"></div>
			</div>
			<div class="left">
				<div class="slideImage"><img src="img/slide/photo/001/001.jpg" /></div>
			</div>
			<div class="right">
				<div class="subject">
					<div class="column">프로젝트명</div>
					<div class="data"></div>
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
				<div class="development">
					<div class="column">개발 언어</div>
					<div class="data"></div>
				</div>
				<div class="memo"></div>
			</div>	
		</div>
	</div>

    <div id="leftMenu">
        <div class="title">PELSONAL PORTFOLIO</div>
        <div class="profileImage">
        	<img src="img/menu_profileImage.jpg" />
        	<div class="button">
        		<div class="login">
	        		<div class="loginBtn">로그인</div>
	        	</div>
	        	<div class="logged">
	        		<div class="logoutBtn">로그아웃</div>
	        		<div class="newPostBtn">새글작성</div>
	        	</div>
        	</div>
        </div>
  
  		<div class="name">Seong Gyun, Jeon (1989)</div>
  
        <!--<a href="javascript:$.pageslide.close()">Close</a>-->
    </div>

    <div id="content">
    	<div class="title"><a href="#leftMenu" class="menu"><img class="title" /></a></div>
    	<div class="innerContent">
    		<div class="subTitle">PUBLICATION</div>
			<div class="grid">
			
				<figure class="effect-sarah">
					<img src="img/cover/001.jpg" alt="img01"/>
					<figcaption>
						<h2>Grand Mint Festival 2013</h2>
						<p>Olympic Park, SEOUL (2013.10.20)</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<!--
				<figure class="effect-sadie">
					<img src="img/test/2.jpg" alt="img02"/>
					<figcaption>
						<h2>Holy <span>Sadie</span></h2>
						<p>Sadie never took her eyes off me. <br>She had a dark soul.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-honey">
					<img src="img/test/7.jpg" alt="img07"/>
					<figcaption>
						<h2>Dreamy <span>Honey</span> <i>Now</i></h2>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
					
				<figure class="effect-layla">
					<img src="img/test/4.jpg" alt="img04"/>
					<figcaption>
						<h2>Crazy <span>Layla</span></h2>
						<p>When Layla appears, she brings an eternal summer along.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-zoe">
					<img src="img/test/14.jpg" alt="img14"/>
					<figcaption>
						<h2>Creative <span>Zoe</span></h2>
						<span class="icon-heart"></span>
						<span class="icon-eye"></span>
						<span class="icon-paper-clip"></span>
						<p>Zoe never had the patience of her sisters. She deliberately punched the bear in his face.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-oscar">
					<img src="img/test/8.jpg" alt="img08"/>
					<figcaption>
						<h2>Warm <span>Oscar</span></h2>
						<p>Oscar is a decent man. He used to clean porches with pleasure.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-marley">
					<img src="img/test/9.jpg" alt="img09"/>
					<figcaption>
						<h2>Sweet <span>Marley</span></h2>
						<p>Marley tried to convince her but she was not interested.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-ruby">
					<img src="img/test/10.jpg" alt="img10"/>
					<figcaption>
						<h2>Glowing <span>Ruby</span></h2>
						<p>Ruby did not need any help. Everybody knew that.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-roxy">
					<img src="img/test/3.jpg" alt="img03"/>
					<figcaption>
						<h2>Charming <span>Roxy</span></h2>
						<p>Roxy was my best friend..</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-bubba">
					<img src="img/test/6.jpg" alt="img06"/>
					<figcaption>
						<h2>Fresh <span>Bubba</span></h2>
						<p>Bubba likes to appear out of thin air.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-romeo">
					<img src="img/test/5.jpg" alt="img05"/>
					<figcaption>
						<h2>Wild <span>Romeo</span></h2>
						<p>Romeo never knows what he wants. He seemed to be very cross about something.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-dexter">
					<img src="img/test/12.jpg" alt="img12"/>
					<figcaption>
						<h2>Strange <span>Dexter</span></h2>
						<p>Dexter had his own strange way. You could watch him training ants.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-sarah">
					<img src="img/test/13.jpg" alt="img13"/>
					<figcaption>
						<h2>Free <span>Sarah</span></h2>
						<p>Sarah likes to watch clouds. She's quite depressed.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-chico">
					<img src="img/test/15.jpg" alt="img15"/>
					<figcaption>
						<h2>Silly <span>Chico</span></h2>
						<p>Chico's main fear was missing the morning bus.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				
				<figure class="effect-milo">
					<img src="img/test/11.jpg" alt="img11"/>
					<figcaption>
						<h2>Faithful <span>Milo</span></h2>
						<p>Milo went to the woods. He took a fun ride and never came back.</p>
						<a href="#">View more</a>
					</figcaption>			
				</figure>
				-->
				
				
			</div>
    	</div>
    </div>

    <script src="js/jquery.pageslide.min.js"></script>
    <script>$(".menu").pageslide({ direction: "right", modal: true });</script>
 
</body>
</html>