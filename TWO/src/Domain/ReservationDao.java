package Domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReservationDao {
	//DB Attr
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String id="system";
	private String pw = "1234";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	// 싱글톤 패턴처리
	private static ReservationDao reser;
	private ReservationDao() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,id,pw);
	}
	
	public static ReservationDao getInstance() throws ClassNotFoundException, SQLException {
		if (reser == null) {
			reser = new ReservationDao();
		}
		return reser;
	}
	
	public int delete(ReservationDto dto) throws Exception {
		try {
			System.out.println("삭제 시도 Rental_id = " + dto.getRental_id()); // 확인용
			pstmt = conn.prepareStatement("delete from Reserve_Tbl where RENTAL_ID = ?");
			pstmt.setInt(1, dto.getRental_id());
			int result =  pstmt.executeUpdate();
			System.out.println("삭제 결과 row count = " + result); // 성공 여부 확인
			return result;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new SQLException("RESERDAO DELETE SQL EXCEPTION");
		}finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
				
	}
}
