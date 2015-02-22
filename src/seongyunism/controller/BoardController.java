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
	
	public void postListCount(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		try {
			int inputProjectCategory = (req.getParameter("projectCategory") != null) ? Integer.parseInt(req.getParameter("projectCategory")) : 0;
			int postCount = BoardDAO.getListCount(inputProjectCategory);
			
			res.getWriter().write(postCount);
					
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<Board> postList(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		ArrayList<Board> postList = new ArrayList<Board>();

		try {
			int inputProjectCategory = (req.getParameter("projectCategory") != null) ? Integer.parseInt(req.getParameter("projectCategory")) : 0;
			
			postList = BoardDAO.getList(inputProjectCategory);
			
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}
		
		return postList;
		
	}
	
	
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
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");

			System.out.println(jObject.toString());
			res.getWriter().write(jObject.toString());

		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}
		
	}
	
	
	
	
}
