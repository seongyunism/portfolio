package seongyunism.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import seongyunism.model.domain.Board;
import seongyunism.model.domain.BoardCategory;
import seongyunism.model.domain.Guestbook;
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
					rset.getInt(19),	// String pfPostViewMode
					rset.getInt(20),		// int pfPostViews
					rset.getInt(21),		// int pfPostWrittenDate
					rset.getInt(22),		// int pfPostTotalComments
					rset.getInt(23)		// int pfPostTotalLike
				);

			}

			return thisPost;
			
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
			
			return postCount;

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

			}

			return thisCategory;

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
						rset.getInt(19),	// String pfPostViewMode
						rset.getInt(20),		// int pfPostViews
						rset.getInt(21),		// int pfPostWrittenDate
						rset.getInt(22),		// int pfPostTotalComments
						rset.getInt(23)		// int pfPostTotalLike
				);
				
				thisList.add(thisPost);
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

	public static int getNextPostNo() throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int nextPostNo = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT max(pfNo) FROM pf_post");
			rset = pstmt.executeQuery();
			
			rset.next();
			nextPostNo = rset.getInt(1) + 1;
			
			return nextPostNo;
			
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

	public static boolean writePost(int inputPostNo, int inputProjectCategory, String inputProjectTitle, String inputProjectSubTitle,
		String inputProjectPeriod, String inputProjectPurpose, String inputProjectCollabo, String inputProjectLanguage,
		int inputProjectDate, String inputProjectLink, String inputProjectMovAddr, String inputProjectMovPreview,
		String inputProjectImgAddr01, String inputProjectImgAddr02, String inputProjectImgAddr03, String inputProjectImgAddr04,
		String inputPostThumbnailAddr, String inputProjectMemo, int inputPostViewMode, int inputPostViews, int inputPostWrittenDate,
		int inputPostTotalComments, int inputPostTotalLike) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO pf_post(pfNo, pfProjectCategory, pfProjectTitle, pfProjectSubTitle, "
				+ "pfProjectPeriod, pfProjectPurpose, pfProjectCollabo, pfProjectLanguage, pfProjectDate, pfProjectLink, "
				+ "pfProjectMovAddr, pfProjectMovPreview, pfProjectImgAddr01, pfProjectImgAddr02, pfProjectImgAddr03, "
				+ "pfProjectImgAddr04, pfPostThumbnailAddr, pfProjectMemo, pfPostViewMode, pfPostViews, pfPostWrittenDate, "
				+ "pfPostTotalComments, pfPostTotalLike) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
			pstmt.setInt(1, inputPostNo);
			pstmt.setInt(2, inputProjectCategory);
			pstmt.setString(3, inputProjectTitle); 
			pstmt.setString(4, inputProjectSubTitle); 
			pstmt.setString(5, inputProjectPeriod); 
			pstmt.setString(6, inputProjectPurpose); 
			pstmt.setString(7, inputProjectCollabo); 
			pstmt.setString(8, inputProjectLanguage); 
			pstmt.setInt(9, inputProjectDate); 
			pstmt.setString(10, inputProjectLink); 
			pstmt.setString(11, inputProjectMovAddr); 
			pstmt.setString(12, inputProjectMovPreview); 
			pstmt.setString(13, inputProjectImgAddr01); 
			pstmt.setString(14, inputProjectImgAddr02); 
			pstmt.setString(15, inputProjectImgAddr03); 
			pstmt.setString(16, inputProjectImgAddr04); 
			pstmt.setString(17, inputPostThumbnailAddr); 
			pstmt.setString(18, inputProjectMemo); 
			pstmt.setInt(19, inputPostViewMode); 
			pstmt.setInt(20, inputPostViews);
			pstmt.setInt(21, inputPostWrittenDate); 
			pstmt.setInt(22, inputPostTotalComments);
			pstmt.setInt(23, inputPostTotalLike);
			pstmt.executeUpdate();
			
			System.out.println("BoardDAO - New Post : " + inputPostNo + " : " + inputProjectTitle + "(" + inputProjectSubTitle + ")");

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

	public static boolean updatePost(int inputPostNo, int inputProjectCategory, String inputProjectTitle, String inputProjectSubTitle,
		String inputProjectPeriod, String inputProjectPurpose, String inputProjectCollabo, String inputProjectLanguage, int inputProjectDate,
		String inputProjectLink, String inputProjectMovAddr, String inputProjectMovPreview, String inputProjectImgAddr01, String inputProjectImgAddr02,
		String inputProjectImgAddr03, String inputProjectImgAddr04, String inputPostThumbnailAddr, String inputProjectMemo, int inputPostViewMode) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("UPDATE pf_post SET pfProjectCategory=?, pfProjectTitle=?, pfProjectSubTitle=?, "
					+ "pfProjectPeriod=?, pfProjectPurpose=?, pfProjectCollabo=?, pfProjectLanguage=?, pfProjectDate=?, pfProjectLink=?, "
					+ "pfProjectMovAddr=?, pfProjectMovPreview=?, pfProjectImgAddr01=?, pfProjectImgAddr02=?, pfProjectImgAddr03=?, "
					+ "pfProjectImgAddr04=?, pfPostThumbnailAddr=?, pfProjectMemo=?, pfPostViewMode=? WHERE pfNo=?");
			
				pstmt.setInt(1, inputProjectCategory);
				pstmt.setString(2, inputProjectTitle); 
				pstmt.setString(3, inputProjectSubTitle); 
				pstmt.setString(4, inputProjectPeriod); 
				pstmt.setString(5, inputProjectPurpose); 
				pstmt.setString(6, inputProjectCollabo); 
				pstmt.setString(7, inputProjectLanguage); 
				pstmt.setInt(8, inputProjectDate); 
				pstmt.setString(9, inputProjectLink); 
				pstmt.setString(10, inputProjectMovAddr); 
				pstmt.setString(11, inputProjectMovPreview); 
				pstmt.setString(12, inputProjectImgAddr01); 
				pstmt.setString(13, inputProjectImgAddr02); 
				pstmt.setString(14, inputProjectImgAddr03); 
				pstmt.setString(15, inputProjectImgAddr04); 
				pstmt.setString(16, inputPostThumbnailAddr); 
				pstmt.setString(17, inputProjectMemo); 
				pstmt.setInt(18, inputPostViewMode);
				pstmt.setInt(19, inputPostNo);
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

	public static boolean deletePost(int inputPostNo) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		int deleteQueryCount = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("DELETE FROM pf_post WHERE pfNo=?");
			pstmt.setInt(1, inputPostNo);
			deleteQueryCount = pstmt.executeUpdate();

			if(deleteQueryCount == 1) {
				return true;
			} else {
				return false;
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

	public static ArrayList<Guestbook> getListGuestPost() throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Guestbook thisPost = null;
		ArrayList<Guestbook> thisList = new ArrayList<Guestbook>();

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_guestbook ORDER BY pfGuestPostDate DESC");
			rset = pstmt.executeQuery();
				
			while (rset.next()) {
				
				thisPost = new Guestbook(
						rset.getInt(1),		// int pfNo
						rset.getInt(2),		// int pfMemberNo
						rset.getString(3),	// String pfGuestMemberName
						rset.getString(4),	// String pfGuestMemberPassword
						rset.getLong(5),	// long pfGuestPostDate
						rset.getString(6)	// String pfGuestPoseMemo
				);
				
				thisList.add(thisPost);
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
	
	public static boolean writeGuestPost(int inputMemberNo, String inputGuestMemberName, String inputGuestMemberPassword,
		long inputGuestPostDate, String inputGuestPostMemo) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("INSERT INTO pf_guestbook(pfMemberNo, pfGuestMemberName, pfGuestMemberPassword, pfGuestPostDate, pfGuestPostMemo) "
				+ "VALUES (?, ?, password(?), ?, ?)");
		
			pstmt.setInt(1, inputMemberNo);
			pstmt.setString(2, inputGuestMemberName);
			pstmt.setString(3, inputGuestMemberPassword); 
			pstmt.setLong(4, inputGuestPostDate); 
			pstmt.setString(5, inputGuestPostMemo); 
			pstmt.executeUpdate();
			
			System.out.println("BoardDAO - New Guestbook Post : " + inputGuestMemberName);

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

	
}
