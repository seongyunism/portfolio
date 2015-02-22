package seongyunism.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import seongyunism.model.domain.Board;
import seongyunism.util.DBUtil;

public class BoardDAO {

	// [index.jsp][콘텐츠 영역] 
	public static Board getPost(int postNo) throws SQLException {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		Board thisPost = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement("SELECT * FROM pf_post WHERE pfNo=?");
			pstmt.setInt(1, postNo);
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
					rset.getInt(8),		// int pfProjectDate
					rset.getString(9),	// String pfProjectLink
					rset.getString(10),	// String pfProjectMovAddr
					rset.getString(11),	// String pfProjectImgAddr01
					rset.getString(12),	// String pfProjectImgAddr02
					rset.getString(13),	// String pfProjectImgAddr03
					rset.getString(14),	// String pfProjectImgAddr04
					rset.getString(15),	// String pfPostThumbnailAddr
					rset.getString(16),	// String pfProjectMemo
					rset.getString(17),	// String pfPostViewMode
					rset.getInt(18),	// int pfPostViews
					rset.getInt(19),	// int pfPostWrittenDate
					rset.getInt(20),	// int pfPostTotalComments
					rset.getInt(21)		// int pfPostTotalLike
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

}
