<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="seongyunism.model.BoardDAO, org.json.simple.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	int postNo = (request.getParameter("postNo") != null) ? Integer.parseInt(request.getParameter("postNo").trim()) : 0;
	
//	int postNo =1;

	//하나의 정보를 저장할 JSONObject를 설정
	JSONObject jObject = new JSONObject();
	
	//데이터를 삽입
	jObject.put("pfNo", postNo);
	jObject.put("pfProjectTitle", BoardDAO.getPost(postNo).getPfProjectTitle());
	jObject.put("pfProjectSubTitle", BoardDAO.getPost(postNo).getPfProjectSubTitle());
	  
	out.println(jObject.toJSONString());
%>