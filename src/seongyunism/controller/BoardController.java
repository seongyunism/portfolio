package seongyunism.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import seongyunism.model.BoardDAO;
import seongyunism.model.domain.Board;


public class BoardController extends HttpServlet {

	private static final int MEMORY_THRESHOLD = 1024*1024*3; //3MB
	private static final int MAX_FILE_SIZE    = 1024*1024*40; //40MB
	private static final int MAX_REQUEST_SIZE = 1024*1024*50; //50MB

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		process(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		process(req, res);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		if (action.equals("viewPost")) {
			viewPost(req, res);
		} else if(action.equals("listPostCount")) {
			listPostCount(req, res);
		} else if(action.equals("listPost")) {
			listPost(req, res);
		} else if(action.equals("writePost")) {
			writePost(req, res);
		}
	}
	
	// 포스트 리스트에서 포스트 클릭 시 해당 포스트에 해당하는 프로젝트 내용 가져오기
	public void viewPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		req.setCharacterEncoding("utf8");
		
		try {
			int inputPostNo = (req.getParameter("postNo") != null) ? Integer.parseInt(req.getParameter("postNo")) : 0;
			Board thisPost = BoardDAO.getPost(inputPostNo);
			
			// 하나의 정보를 저장할 JSONObject를 설정
			JSONObject jObject = new JSONObject();
			
			// 데이터를 삽입
			jObject.put("pfNo", inputPostNo);
			jObject.put("pfProjectTitle", thisPost.getPfProjectTitle());
			jObject.put("pfProjectSubTitle", thisPost.getPfProjectSubTitle());
			jObject.put("pfProjectPeriod", thisPost.getPfProjectPeriod());
			jObject.put("pfProjectPurpose", thisPost.getPfProjectPurpose());
			jObject.put("pfProjectCollabo", thisPost.getPfProjectCollabo());
			jObject.put("pfProjectLanguage", thisPost.getPfProjectLanguage());
			jObject.put("pfProjectLink", thisPost.getPfProjectLink());
			jObject.put("pfProjectMovAddr", thisPost.getPfProjectMovAddr());
			jObject.put("pfProjectMovPreview", thisPost.getPfProjectMovPreview());	
			jObject.put("pfProjectImgAddr01", thisPost.getPfProjectImgAddr01());
			jObject.put("pfProjectImgAddr02", thisPost.getPfProjectImgAddr02());
			jObject.put("pfProjectImgAddr03", thisPost.getPfProjectImgAddr03());
			jObject.put("pfProjectImgAddr04", thisPost.getPfProjectImgAddr04());
			
			res.setContentType("application/json");
			res.setCharacterEncoding("UTF-8");
			
//			System.out.println(jObject.toString());
			res.getWriter().write(jObject.toString());
			
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}
		
	}
	
	// 카테고리가 일치하는 포스트 개수 구하기
	public void listPostCount(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		try {
			int inputProjectCategory = (req.getParameter("projectCategory") != null) ? Integer.parseInt(req.getParameter("projectCategory")) : 0;
			int postCount = BoardDAO.getListCount(inputProjectCategory);
			
			res.getWriter().write(postCount);
					
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	// 카테고리가 일치하는 포스트 리스트 가져오기
	public void listPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		try {
			ArrayList<Board> list = new ArrayList<Board>();
			list = BoardDAO.getList(2);
			
			req.setAttribute("list", list);
			res.getWriter().write("OK");
			
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}
	}
	
	// 글쓰기
	public void writePost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {		
		req.setCharacterEncoding("utf8");
			
		String result = "";
		String realFolder = ""; // 웹 애플리케이션 상의 절대 경로
		String saveFolder = "/temp"; // 파일 업로드 경로 지정
		String encType = "UTF-8"; // 인코딩 지정
		int maxSize = 1024 * 1024 * 5; // 업로드될 파일 크기 최대 5MB
		
		// 현재 JSP 페이지의 웹 애플리케이션의 절대 경로 구함
		ServletContext context = getServletContext();
		realFolder = context.getRealPath(saveFolder);
		
		try {
			MultipartRequest upload = null;
			
			// 파일 업로드를 수행하는 MultipartRequest 인스턴스 생성
			upload = new MultipartRequest(req, realFolder, maxSize, encType, new DefaultFileRenamePolicy());
			
			// type이 file이 아닌 모든 파라미터를 얻어냄
			Enumeration<?> params = upload.getParameterNames();
			System.out.println(params.nextElement());
			System.out.println(params.nextElement());
			System.out.println(params.nextElement());
			
		
			/*
			String inputMode = (req.getParameter("inputMode") != null) ? req.getParameter("inputMode") : null;
			
			int inputPostNo = (req.getParameter("inputPostNo") != null) ? Integer.parseInt(req.getParameter("inputPostNo")) : 0;
			int inputProjectCategory = (req.getParameter("inputProjectCategory") != null) ? Integer.parseInt(req.getParameter("inputProjectCategory")) : 0;
			String inputProjectTitle = (req.getParameter("inputProjectTitle") != null) ? req.getParameter("inputProjectTitle") : null;
			String inputProjectSubTitle = (req.getParameter("inputProjectSubTitle") != null) ? req.getParameter("inputProjectSubTitle") : null;
			String inputProjectPeriod = (req.getParameter("inputProjectPeriod") != null) ? req.getParameter("inputProjectPeriod") : null;
			String inputProjectPurpose = (req.getParameter("inputProjectPurpose") != null) ? req.getParameter("inputProjectPurpose") : null;
			String inputProjectCollabo = (req.getParameter("inputProjectCollabo") != null) ? req.getParameter("inputProjectCollabo") : null;
			String inputProjectLanguage = (req.getParameter("inputProjectLanguage") != null) ? req.getParameter("inputProjectLanguage") : null;
			int inputProjectDate = (req.getParameter("inputProjectDate") != null) ? Integer.parseInt(req.getParameter("inputProjectDate")) : 0;
			String inputProjectLink = (req.getParameter("inputProjectLink") != null) ? req.getParameter("inputProjectLink") : null;
			String inputProjectMovAddr = (req.getParameter("inputProjectMovAddr") != null) ? req.getParameter("inputProjectMovAddr") : null;
			String inputProjectMovPreview = (req.getParameter("inputProjectMovPreview") != null) ? req.getParameter("inputProjectMovPreview") : null;
			String inputProjectImgAddr01 = (req.getParameter("inputProjectImgAddr01") != null) ? req.getParameter("inputProjectImgAddr01") : null;
			String inputProjectImgAddr02 = (req.getParameter("inputProjectImgAddr02") != null) ? req.getParameter("inputProjectImgAddr02") : null;
			String inputProjectImgAddr03 = (req.getParameter("inputProjectImgAddr03") != null) ? req.getParameter("inputProjectImgAddr03") : null;
			String inputProjectImgAddr04 = (req.getParameter("inputProjectImgAddr04") != null) ? req.getParameter("inputProjectImgAddr04") : null;
			String inputPostThumbnailAddr = (req.getParameter("inputPostThumbnailAddr") != null) ? req.getParameter("inputPostThumbnailAddr") : null;
			String inputProjectMemo = (req.getParameter("inputProjectMemo") != null) ? req.getParameter("inputProjectMemo") : null;
			int inputPostViewMode = (req.getParameter("inputPostViewMode") != null) ? Integer.parseInt(req.getParameter("inputPostViewMode")) : 0;
			
			System.out.println(inputMode);
			
			if(inputMode.equals("new")) {
				inputPostNo = BoardDAO.getNextPostNo();
			}
			*/
			
			
			
			res.getWriter().write("WriteOK");
			
//		} catch (SQLException e) {
//			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
