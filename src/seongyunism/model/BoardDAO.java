package seongyunism.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seongyunism.model.domain.Board;
import seongyunism.model.domain.BoardCategory;
import seongyunism.util.DBUtil;

public class BoardDAO {

	// [index.jsp][콘텐츠 영역] 포스트 가져오기
	public static Board getPost(int inputPostNo) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Board thisPost = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_post WHERE pfNo=?");
			pstmt.setInt(1, inputPostNo);
			rset = pstmt.executeQuery();	
			
			if (rset.next()) {
				
				thisPost = new Board(
					rset.getInt(1),		// int pfNo
					rset.getInt(2),		// int pfProjectCategory 
					rset.getString(3),	// String pfProjectTitle
					rset.getString(4),	// String pfProjectSubTitle
					rset.getString(5),	// String pfProjectPeriod
					rset.getString(6),	// String pfProjectPurpose
					rset.getString(7),	// String pfProjectCollabo
					rset.getString(8),	// String pfProjectLanguage
					rset.getInt(9),		// int pfProjectDate
					rset.getString(10),	// String pfProjectLink
					rset.getString(11),	// String pfProjectMovAddr
					rset.getString(12),	// String pfProjectMovPreview
					rset.getString(13),	// String pfProjectImgAddr01
					rset.getString(14),	// String pfProjectImgAddr02
					rset.getString(15),	// String pfProjectImgAddr03
					rset.getString(16),	// String pfProjectImgAddr04
					rset.getString(17),	// String pfPostThumbnailAddr
					rset.getString(18),	// String pfProjectMemo
					rset.getString(19),	// String pfPostViewMode
					rset.getInt(20),		// int pfPostViews
					rset.getInt(21),		// int pfPostWrittenDate
					rset.getInt(22),		// int pfPostTotalComments
					rset.getInt(23)		// int pfPostTotalLike
				);

			} else {
//				throw new RecordNotFoundException();
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
		
		return thisPost;
	}

	// [index.jsp][콘텐츠 영역] 해당 카테고리 포스트 개수 가져오기
	public static int getListCount(int inputProjectCategory) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int postCount = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT count(*) FROM pf_post WHERE pfProjectCategory=? AND pfPostViewMode<2");
			pstmt.setInt(1, inputProjectCategory);
			rset = pstmt.executeQuery();
			
			rset.next();
			postCount = rset.getInt(1);
			
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
		
		return postCount;
	}
	
	public static BoardCategory getCategoryName(int inputProjectCategory) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		BoardCategory thisCategory = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_post_category WHERE pfNo=?");
			pstmt.setInt(1, inputProjectCategory);
			rset = pstmt.executeQuery();
				
			if (rset.next()) {
				
				thisCategory = new BoardCategory(
					rset.getInt(1),		 // int pfNo
					rset.getString(2),	// String pfCategoryName;
					rset.getString(3)  // String pfCategoryNameKor
				);

			} else {
//				throw new RecordNotFoundException();
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

		return thisCategory;
	}

	public static ArrayList<Board> getList(int inputProjectCategory) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Board thisPost = null;
		ArrayList<Board> thisList = new ArrayList<Board>();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_post WHERE pfProjectCategory=? AND pfPostViewMode<2 ORDER BY pfProjectDate DESC");
			pstmt.setInt(1, inputProjectCategory);
			rset = pstmt.executeQuery();
				
			while (rset.next()) {
				
				thisPost = new Board(
						rset.getInt(1),		// int pfNo
						rset.getInt(2),		// int pfProjectCategory 
						rset.getString(3),	// String pfProjectTitle
						rset.getString(4),	// String pfProjectSubTitle
						rset.getString(5),	// String pfProjectPeriod
						rset.getString(6),	// String pfProjectPurpose
						rset.getString(7),	// String pfProjectCollabo
						rset.getString(8),	// String pfProjectLanguage
						rset.getInt(9),		// int pfProjectDate
						rset.getString(10),	// String pfProjectLink
						rset.getString(11),	// String pfProjectMovAddr
						rset.getString(12),	// String pfProjectMovPreview
						rset.getString(13),	// String pfProjectImgAddr01
						rset.getString(14),	// String pfProjectImgAddr02
						rset.getString(15),	// String pfProjectImgAddr03
						rset.getString(16),	// String pfProjectImgAddr04
						rset.getString(17),	// String pfPostThumbnailAddr
						rset.getString(18),	// String pfProjectMemo
						rset.getString(19),	// String pfPostViewMode
						rset.getInt(20),		// int pfPostViews
						rset.getInt(21),		// int pfPostWrittenDate
						rset.getInt(22),		// int pfPostTotalComments
						rset.getInt(23)		// int pfPostTotalLike
				);
				
				thisList.add(thisPost);
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

		return thisList;
	}
	
}
