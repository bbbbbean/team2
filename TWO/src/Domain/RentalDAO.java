package Domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalDAO {
	
	// DB 연결용
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String id = "system";
	private String pw = "1234";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// 싱글톤
	private static RentalDAO instance;
	private RentalDAO() throws Exception {
		// DB연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url, id, pw);
	}
	public static RentalDAO getInstance() throws Exception {
		if(instance==null)
			instance = new RentalDAO();
		return instance;
	}
	
	public int insert(RentalDTO rentalDto) {
		return 0;
	}
	public int select(RentalDTO rentalDto) {
		return 0;
	}
	public int update(RentalDTO rentalDto) throws Exception {
		try {
			pstmt = conn.prepareStatement("update Rental_tbl set rental_id=?,Book_code=?,Member_id=? where rental_id=?");
			pstmt.setInt(1, rentalDto.getRentalId());
			pstmt.setInt(2, rentalDto.getBookCode());
			pstmt.setInt(3, rentalDto.getMemberId());
			pstmt.setInt(4, rentalDto.getRentalId());
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BookDao's Insert SQL Exception");
		}finally {
			try {pstmt.close();}catch(Exception e2) {}
		}
	}
	public int delete(RentalDTO rentalDto) {
		return 0;
	}
	
	
	

}
