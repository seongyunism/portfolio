package seongyunism.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import seongyunism.model.MemberDAO;
import seongyunism.model.domain.Member;

public class MemberController extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		process(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		process(req, res);
	}
	
	public void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		if(action.equals("joinMember")) {
			joinMember(req, res);
		} else if(action.equals("loginMember")) {
			loginMember(req, res);
		} else if(action.equals("logoutMember")) {
			logoutMember(req, res);
		} else if(action.equals("loginCheck")) {
			loginCheck(req, res);
		}
	}

	public void joinMember(HttpServletRequest req, HttpServletResponse res) throws ServletException {
		
		int checkedMember = 0;
		String returnJoinCheck = null;
		
		try {
			String inputMemberEmail = (req.getParameter("inputMemberEmail") != null) ? req.getParameter("inputMemberEmail") : null;
			String inputMemberPassword = (req.getParameter("inputMemberPassword") != null) ? req.getParameter("inputMemberPassword") : null;
			String inputMemberName = (req.getParameter("inputMemberName") != null) ? req.getParameter("inputMemberName") : null;
			int inputMemberConnectedSNS = (req.getParameter("inputMemberConnectedSNS") != null) ? Integer.parseInt(req.getParameter("inputMemberConnectedSNS")) : 0;
			String inputMemberFacebook = (req.getParameter("inputMemberFacebook") != null) ? req.getParameter("inputMemberFacebook") : "";
			
			checkedMember = MemberDAO.checkJoinMember(inputMemberEmail, inputMemberPassword, inputMemberName, inputMemberConnectedSNS, inputMemberFacebook);
		
			if(checkedMember == 1) {
				Member thisMember = MemberDAO.getMember(inputMemberEmail);
				
				HttpSession sessionMember = req.getSession();

				sessionMember.setAttribute("pfMemberNo", thisMember.getPfNo());
				sessionMember.setAttribute("pfMemberEmail", inputMemberEmail);
				sessionMember.setAttribute("pfMemberName", inputMemberName);
				sessionMember.setAttribute("pfMemberConnectedSNS", inputMemberConnectedSNS);
				sessionMember.setAttribute("pfMemberFacebook", inputMemberFacebook);
				
				returnJoinCheck = "JoinOK";
			} else if(checkedMember == 2) {
				returnJoinCheck = "Duplicate";
			} else if(checkedMember == 3) {
				returnJoinCheck = "NotEmail";
			} else {
				returnJoinCheck = "Fail";
			}
			
			res.getWriter().write(returnJoinCheck);
			
		} catch (SQLException se) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		} catch (IOException ie) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (IO에러)");
		}
	}

	public void loginMember(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		int checkedMemberNo = 0;
		String returnLoginCheck = null;

		try {
			String inputMemberEmail = (req.getParameter("inputMemberEmail") != null) ? req.getParameter("inputMemberEmail") : null;
			String inputMemberPassword = (req.getParameter("inputMemberPassword") != null) ? req.getParameter("inputMemberPassword") : null;

			checkedMemberNo = MemberDAO.checkLoginMember(inputMemberEmail, inputMemberPassword);
			
			if(checkedMemberNo > 0) {
				Member thisMember = MemberDAO.getMember(checkedMemberNo);
				
				HttpSession sessionMember = req.getSession();
				
				if(checkedMemberNo == 1) {
					sessionMember.setAttribute("pfMemberAdmin", "true");
				}
				
				sessionMember.setAttribute("pfMemberNo", checkedMemberNo);
				sessionMember.setAttribute("pfMemberEmail", inputMemberEmail);
				sessionMember.setAttribute("pfMemberName", thisMember.getPfMemberName());
				sessionMember.setAttribute("pfMemberConnectedSNS", thisMember.getPfMemberConnectedSNS());
				sessionMember.setAttribute("pfMemberFacebook", thisMember.getPfMemberFacebook());

				System.out.println("MemberController - User Login # MemberNo : " + checkedMemberNo + ", "
						+ "MemberName : " + thisMember.getPfMemberName() + ", MemberEmail : " + thisMember.getPfMemberEmail());

				returnLoginCheck = "LoginOK";
				
			} else {
				returnLoginCheck = "Fail";
			}
			
			res.getWriter().write(returnLoginCheck);

		} catch (SQLException e) {
			req.setAttribute("errorMsg", "ERROR : 포스트 가져오기 실패! (SQL에러)");
		}

	}
	
	public void logoutMember(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		HttpSession sessionMember = req.getSession(false);
		
		try {
			if (sessionMember != null) {				
				System.out.println("MemberController - User Logout # MemberNo : " + sessionMember.getAttribute("pfMemberNo") + ", "
						+ "MemberName : " + sessionMember.getAttribute("pfMemberName") + ", MemberEmail : " + sessionMember.getAttribute("pfMemberEmail"));

				sessionMember.invalidate();
				res.getWriter().write("LogoutOK");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void loginCheck(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {

		int memberNo = 0;
		
		try {
			
			HttpSession sessionMember = req.getSession();
			memberNo = (sessionMember.getAttribute("pfMemberNo") != null) ? Integer.parseInt(sessionMember.getAttribute("pfMemberNo").toString()) : 0;
			
			if(memberNo > 0) {
				res.getWriter().write("Checked");
			} else {
				res.getWriter().write("Fail");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
}
