package seongyunism.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import seongyunism.model.BoardDAO;
import seongyunism.model.domain.Board;
import seongyunism.util.DBUtil;

public class BoardController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		process(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		process(req, res);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		if (action.equals("postView")) {
			postView(req, res);
		} else if(action.equals("postList")) {
			postListCount(req, res);
		}
	}
	
	// 카테고리가 일치하는 포스트 개수 구하기
	public void postListCount(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		try {
			int inputProjectCategory = (req.getParameter("projectCategory") != null) ? Integer.parseInt(req.getParameter("projectCategory")) : 0;
			int postCount = BoardDAO.getListCount(inputProjectCategory);
			
			res.getWriter().write(postCount);
					
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// 포스트 리스트에서 포스트 클릭 시 해당 포스트에 해당하는 프로젝트 내용 가져오기
	public void postView(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		req.setCharacterEncoding("utf8");

		try {
			int inputBoardPostNo = (req.getParameter("postNo") != null) ? Integer.parseInt(req.getParameter("postNo")) : 0;
			Board thisPost = BoardDAO.getPost(inputBoardPostNo);
			
			// 하나의 정보를 저장할 JSONObject를 설정
			JSONObject jObject = new JSONObject();
			
			// 데이터를 삽입
			jObject.put("pfNo", inputBoardPostNo);
			jObject.put("pfProjectTitle", thisPost.getPfProjectTitle());
			jObject.put("pfProjectSubTitle", thisPost.getPfProjectSubTitle());
			jObject.put("pfProjectPeriod", thisPost.getPfProjectPeriod());
			jObject.put("pfProjectPurpose", thisPost.getPfProjectPurpose());
			jObject.put("pfProjectCollabo", thisPost.getPfProjectCollabo());
			jObject.put("pfProjectLanguage", thisPost.getPfProjectLanguage());
			jObject.put("pfProjectMovAddr", thisPost.getPfProjectMovAddr());
			jObject.put("pfProjectMovPreview", thisPost.getPfProjectMovPreview());	
			jObject.put("pfProjectImgAddr01", thisPost.getPfProjectImgAddr01());
			jObject.put("pfProjectImgAddr02", thisPost.getPfProjectImgAddr02());
			jObject.put("pfProjectImgAddr03", thisPost.getPfProjectImgAddr03());
			jObject.put("pfProjectImgAddr04", thisPost.getPfProjectImgAddr04());
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");

			System.out.println(jObject.toString());
			res.getWriter().write(jObject.toString());

		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}
		
	}
	
}
