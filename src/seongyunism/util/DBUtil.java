package seongyunism.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DBUtil {
	// CP 기능이 탑재된 매우 유용한 자바 객체 : 서버의 내부 메모리에서 Connection 객체 반환
	private static DataSource ds;
	
	static{
		// Server 즉 Container의 설정 정보등을 제공해주는 환경 객체
		InitialContext ctx = null;		
		try {
			ctx = new InitialContext();
			
			// lookup() : 자원에 부여한 별칭으로 해당 자원의 관리하는 객체 요청하는 메서드 
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			System.out.println("ERROR : DataSource를 찾을 수 없습니다!");
		}
	}
		public static Connection getConnection() throws SQLException{
		return ds.getConnection();
	}
	
	public static void close(Connection conn, Statement stmt) throws SQLException{		
		if(stmt!= null){
			stmt.close();
		}
		if(conn != null){
			conn.close();
		}
	}
	
	public static void close(Connection conn, Statement stmt, ResultSet rset) throws SQLException{
		if(rset != null){
			rset.close();
			rset = null;
		}
		if(stmt != null){
			stmt.close();
		}
		if(conn!=null){
			conn.close();
		}
	}	
}
