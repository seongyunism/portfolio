package seongyunism.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.FilePart;
import com.oreilly.servlet.multipart.MultipartParser;
import com.oreilly.servlet.multipart.ParamPart;
import com.oreilly.servlet.multipart.Part;

import seongyunism.model.BoardDAO;
import seongyunism.model.domain.Board;


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
		
		if (action.equals("viewPost")) {
			viewPost(req, res);
		} else if(action.equals("listPostCount")) {
			listPostCount(req, res);
		} else if(action.equals("listPost")) {
			listPost(req, res);
		} else if(action.equals("writePost")) {
			writePost(req, res);
		} else if(action.equals("deletePost")) {
			deletePost(req, res);
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
			jObject.put("pfProjectCategory", thisPost.getPfProjectCategory());
			jObject.put("pfProjectTitle", thisPost.getPfProjectTitle());
			jObject.put("pfProjectSubTitle", thisPost.getPfProjectSubTitle());
			jObject.put("pfProjectPeriod", thisPost.getPfProjectPeriod());
			jObject.put("pfProjectPurpose", thisPost.getPfProjectPurpose());
			jObject.put("pfProjectCollabo", thisPost.getPfProjectCollabo());
			jObject.put("pfProjectLanguage", thisPost.getPfProjectLanguage());
			jObject.put("pfProjectDate", thisPost.getPfProjectDate());
			jObject.put("pfProjectLink", thisPost.getPfProjectLink());
			jObject.put("pfProjectMovAddr", thisPost.getPfProjectMovAddr());
			jObject.put("pfProjectMovPreview", thisPost.getPfProjectMovPreview());	
			jObject.put("pfProjectImgAddr01", thisPost.getPfProjectImgAddr01());
			jObject.put("pfProjectImgAddr02", thisPost.getPfProjectImgAddr02());
			jObject.put("pfProjectImgAddr03", thisPost.getPfProjectImgAddr03());
			jObject.put("pfProjectImgAddr04", thisPost.getPfProjectImgAddr04());
			jObject.put("pfPostThumbnailAddr", thisPost.getPfPostThumbnailAddr());
			jObject.put("pfProjectMemo", thisPost.getPfProjectMemo());
			jObject.put("pfPostViewMode", thisPost.getPfPostViewMode());
			
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

		Map<String, String> map = new HashMap<String, String>();

		String filePostNo = "";
		String reName = "";
		
		Part part = null;
		int sizeLimit = 20 * 1024 * 1024 ; // 2메가까지 제한 넘어서면 예외발생
		
		boolean queryCheck = false;
		
		try {
			MultipartParser mRequest = new MultipartParser(req, sizeLimit);
			mRequest.setEncoding("UTF-8");
			
			while ((part = mRequest.readNextPart()) != null) {
				String paramName = part.getName();

				// 파일이 아닐때
				if (part.isParam()) {
					String paramValue = "";
					ParamPart paramPart = (ParamPart) part;
					paramValue = paramPart.getStringValue();
					
					if(paramName.equals("inputPostNo")) {
						if(paramValue.equals("0")) {
							paramValue = String.valueOf(BoardDAO.getNextPostNo());
						}
						
						filePostNo = String.format("%03d", Integer.parseInt(paramValue));
						
						File movfilePath = new File(getServletContext().getRealPath("") + File.separator + "img/slide/movie/" + filePostNo);
						File imgfilePath = new File(getServletContext().getRealPath("") + File.separator + "img/slide/photo/" + filePostNo);
						movfilePath.mkdir();
						imgfilePath.mkdir();
					} 
					
					map.put(paramName, paramValue);
					
				// 파일일 때
				} else if (part.isFile()) {

					FilePart filePart = (FilePart) part;
					String oriFileName = filePart.getFileName();
						
					filePart.setRenamePolicy(new DefaultFileRenamePolicy()); //중복 파일 이름 정의    

					if (oriFileName != null) {
						String savePath = "";
						
						if(paramName.equals("inputPostThumbnailAddrFile")) {
							savePath = getServletContext().getRealPath("") + File.separator + "img/cover";
							reName = filePostNo + ".jpg";
							map.put("inputPostThumbnailAddr", "img/cover/" + filePostNo + ".jpg");
						} else if(paramName.equals("inputProjectMovPreviewFile")) {
							savePath = getServletContext().getRealPath("") + File.separator + "img/slide/movie/" + filePostNo;
							reName = "preview.jpg";
							map.put("inputProjectMovPreview", "img/slide/movie/" + filePostNo + "/preview.jpg");
						} else if(paramName.equals("inputProjectImgAddr01File")) {
							savePath = getServletContext().getRealPath("") + File.separator + "img/slide/photo/" + filePostNo;
							reName = "001.jpg";
							map.put("inputProjectImgAddr01", "img/slide/photo/" + filePostNo + "/001.jpg");
						} else if(paramName.equals("inputProjectImgAddr02File")) {
							savePath = getServletContext().getRealPath("") + File.separator + "img/slide/photo/" + filePostNo;
							reName = "002.jpg";
							map.put("inputProjectImgAddr02", "img/slide/photo/" + filePostNo + "/002.jpg");
						} else if(paramName.equals("inputProjectImgAddr03File")) {
							savePath = getServletContext().getRealPath("") + File.separator + "img/slide/photo/" + filePostNo;
							reName = "003.jpg";
							map.put("inputProjectImgAddr03", "img/slide/photo/" + filePostNo + "/003.jpg");
						} else if(paramName.equals("inputProjectImgAddr04File")) {
							savePath = getServletContext().getRealPath("") + File.separator + "img/slide/photo/" + filePostNo;
							reName = "004.jpg";
							map.put("inputProjectImgAddr04", "img/slide/photo/" + filePostNo + "/004.jpg");
						}
						
						File filePath = new File( savePath );
						long size = filePart.writeTo(filePath);

						// 첨부파일 이름 변경
						File up1 = new File(savePath + "/" + oriFileName);
						File up2 = new File(savePath+"/"+ reName);

						if(up1.exists()) {
							boolean rslt = up1.renameTo(up2);
						}
					}
				}
			}
			
			// 모드에 따른 DB 삽입
			if(map.get("inputMode").equals("new")) {
				queryCheck = BoardDAO.writePost(Integer.parseInt(map.get("inputPostNo")), Integer.parseInt(map.get("inputProjectCategory")), 
					map.get("inputProjectTitle"), map.get("inputProjectSubTitle"), map.get("inputProjectPeriod"), map.get("inputProjectPurpose"),
					map.get("inputProjectCollabo"), map.get("inputProjectLanguage"), Integer.parseInt(map.get("inputProjectDate")),
					map.get("inputProjectLink"), map.get("inputProjectMovAddr"), map.get("inputProjectMovPreview"), map.get("inputProjectImgAddr01"),
					map.get("inputProjectImgAddr02"), map.get("inputProjectImgAddr03"), map.get("inputProjectImgAddr04"), map.get("inputPostThumbnailAddr"),
					map.get("inputProjectMemo"), 0, 0, 0, 0, 0);
			} else if(map.get("inputMode").equals("update")) {
				queryCheck = BoardDAO.updatePost(Integer.parseInt(map.get("inputPostNo")), Integer.parseInt(map.get("inputProjectCategory")), 
					map.get("inputProjectTitle"), map.get("inputProjectSubTitle"), map.get("inputProjectPeriod"), map.get("inputProjectPurpose"),
					map.get("inputProjectCollabo"), map.get("inputProjectLanguage"), Integer.parseInt(map.get("inputProjectDate")),
					map.get("inputProjectLink"), map.get("inputProjectMovAddr"), map.get("inputProjectMovPreview"), map.get("inputProjectImgAddr01"),
					map.get("inputProjectImgAddr02"), map.get("inputProjectImgAddr03"), map.get("inputProjectImgAddr04"), map.get("inputPostThumbnailAddr"),
					map.get("inputProjectMemo"), Integer.parseInt(map.get("inputPostViewMode")));
			}

			if(queryCheck) {
				res.sendRedirect("index.jsp");
//				res.getWriter().write("OK");				
			} else {
//				res.getWriter().write("Fail");
			}
						
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}
		
	}
	
	public void deletePost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		req.setCharacterEncoding("utf8");
		
		boolean queryCheck = false;
		
		try {
			int inputPostNo = (req.getParameter("postNo") != null) ? Integer.parseInt(req.getParameter("postNo")) : 0;
			
			queryCheck = BoardDAO.deletePost(inputPostNo);
			
			if(queryCheck) {
				res.getWriter().write("DeleteOK");
			} else {
				res.getWriter().write("Fail");
			}
			
		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}	
		
	}
	
	
}
