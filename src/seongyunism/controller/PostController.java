package seongyunism.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import seongyunism.model.BoardDAO;
import seongyunism.model.domain.Board;

public class PostController extends HttpServlet {

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
		} 
	}
	
	public void postView(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		String url = "index.jsp?content=error.jsp";
		
		try {
			req.setCharacterEncoding("utf8");
//			int postNo = Integer.parseInt(req.getParameter("postNo"));
			int postNo = 1;
			Board thisPost = BoardDAO.getPost(postNo);
			
			//하나의 정보를 저장할 JSONObject를 설정
			JSONObject jObject = new JSONObject();
			
			//데이터를 삽입
			jObject.put("pfNo", postNo);
			jObject.put("pfProjectTitle", thisPost.getPfProjectTitle());
			jObject.put("pfProjectSubTitle", thisPost.getPfProjectSubTitle());
			jObject.put("pfProjectPeriod", thisPost.getPfProjectPeriod());
			jObject.put("pfProjectPurpose", thisPost.getPfProjectPurpose());
			jObject.put("pfProjectCollabo", thisPost.getPfProjectCollabo());
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");

			System.out.println(jObject.toString());
			res.getWriter().write(jObject.toString());

			url = "index.jsp?mode=postView";

		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}
		
//		req.getRequestDispatcher(url).forward(req, res);
	}
}
