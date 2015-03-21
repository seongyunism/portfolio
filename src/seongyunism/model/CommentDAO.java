package seongyunism.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seongyunism.model.domain.Comment;
import seongyunism.util.DBUtil;

public class CommentDAO {

	public static boolean writeComment(int inputPostNo, int inputMemberNo, int inputCommentNo, int inputCommentDepth, int inputCommentMode,
		long inputCommentDate, int inputCommentTotalLike, String inputCommentMemo) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO pf_comment(pfPostNo, pfMemberNo, pfCommentNo, pfCommentDepth, pfCommentMode, "
				+ "pfCommentDate, pfCommentTotalLike, pfCommentMemo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		
			pstmt.setInt(1, inputPostNo);
			pstmt.setInt(2, inputMemberNo);
			pstmt.setInt(3, inputCommentNo);
			pstmt.setInt(4, inputCommentDepth); 
			pstmt.setInt(5, inputCommentMode); 
			pstmt.setLong(6, inputCommentDate); 
			pstmt.setInt(7, inputCommentTotalLike); 
			pstmt.setString(8, inputCommentMemo);
			pstmt.executeUpdate();
			
			return true;
			
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

	public static ArrayList<Comment> getComments(int inputPostNo) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Comment thisComment = null;
		ArrayList<Comment> thisList = new ArrayList<Comment>();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_comment WHERE pfPostNo=?");
			pstmt.setInt(1, inputPostNo);
			rset = pstmt.executeQuery();
				
			while (rset.next()) {
				
				thisComment = new Comment(
					rset.getInt(1),		// int pfNo
					rset.getInt(2),		// int pfPostNo
					rset.getInt(3),		// int pfMemberNo
					rset.getInt(4),		// int pfCommentNo;
					rset.getInt(5),		// int pfCommentDepth;
					rset.getInt(6),		// int pfCommentMode;
					rset.getLong(7),	// long pfCommentDate;
					rset.getInt(8), 	// int pfCommentTotalLike;
					rset.getString(9) 	// String pfCommentMemo;
				);
				
				thisList.add(thisComment);
			}
			
			return thisList;

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
