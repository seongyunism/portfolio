package seongyunism.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import seongyunism.model.domain.Member;
import seongyunism.util.DBUtil;

public class MemberDAO {

	
	public static int checkJoinMember(String inputMemberEmail, String inputMemberPassword,
			String inputMemberName, int inputMemberConnectedSNS, String inputMemberFacebook) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int duplicatedCheck = 0;
			
		try {
			con = DBUtil.getConnection();
			
			// 1. 이메일 중복 체크
			pstmt = con.prepareStatement("SELECT * FROM pf_member WHERE pfMemberEmail=?");
			pstmt.setString(1,  inputMemberEmail);
			rset = pstmt.executeQuery();	
			
			while(rset.next()) {
				duplicatedCheck++;
			}
			
			// 2. 
			if(duplicatedCheck == 0) {
				pstmt = con.prepareStatement("INSERT INTO pf_member(pfMemberEmail, pfMemberPassword,"
						+ "pfMemberName, pfMemberConnectedSNS, pfMemberFacebook) values(?, password(?), ?, ?, ?)");
				
				pstmt.setString(1, inputMemberEmail);
				pstmt.setString(2, inputMemberPassword);
				pstmt.setString(3, inputMemberName);
				pstmt.setInt(4,  inputMemberConnectedSNS);
				pstmt.setString(5,  inputMemberFacebook);
				pstmt.executeUpdate();
				
				System.out.println("MemberDAO - Member Join : " + inputMemberName + "(" + inputMemberEmail + ")");
				return 1; // JoinOK
			} else {
				return 2; // Fail (Duplicated Email)
			}
			
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
	
		} finally {
			try {
				DBUtil.close(con, pstmt, rset);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	
	public static int checkLoginMember(String inputMemberEmail, String inputMemberPassword) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int check = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT pfNo FROM pf_member WHERE pfMemberEmail=? AND pfMemberPassword=password(?)");
			pstmt.setString(1, inputMemberEmail);
			pstmt.setString(2, inputMemberPassword);
			rset = pstmt.executeQuery();	
		
			while(rset.next()) {
				check = rset.getInt("pfNo");
			}
			
			return check;

		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
	
		} finally {
			try {
				DBUtil.close(con, pstmt, rset);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}

	public static Member getMember(int checkedMemberNo) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member thisMember = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_member WHERE pfNo=?");
			pstmt.setInt(1, checkedMemberNo);
			rset = pstmt.executeQuery();	
					
			while(rset.next()) {
				thisMember = new Member(						
						rset.getInt(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getInt(5),
						rset.getString(6)
				);
			}
			
			return thisMember;
			
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
	
		} finally {
			try {
				DBUtil.close(con, pstmt, rset);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	
	public static Member getMember(String inputMemberEmail) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Member thisMember = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_member WHERE pfMemberEmail=?");
			pstmt.setString(1, inputMemberEmail);
			rset = pstmt.executeQuery();	
					
			while(rset.next()) {
				thisMember = new Member(						
						rset.getInt(1),
						rset.getString(2),
						rset.getString(3),
						rset.getString(4),
						rset.getInt(5),
						rset.getString(6)
				);
			}
			
			return thisMember;
			
		} catch (SQLException se) {
			se.printStackTrace();
			throw se;
	
		} finally {
			try {
				DBUtil.close(con, pstmt, rset);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}	
}
