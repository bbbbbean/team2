package Domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class BookDAO {		
	
	
	
	//DB Attr
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private String id = "system";
	private String pw = "1234";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	
	
	//싱글톤 패턴처리
	private static BookDAO instance;
	private BookDAO() throws ClassNotFoundException, SQLException {
		System.out.println("[SERVICE] BookServiceImpl init...");
//		Class.forName("com.mysql.cj.jdbc.Driver");
		Class.forName("oracle.jdbc.driver.OracleDriver");
		conn = DriverManager.getConnection(url,id,pw);
		System.out.println("BookDao DB Connection Success");
		
	};
	
	public static BookDAO getInstance() throws ClassNotFoundException, SQLException {
		if(instance==null)
			instance = new BookDAO();
		return instance;
	}
	
	//CRUD
	//추가
	public int insert(BookDTO bookDto) throws Exception {
		
		try {
			
			pstmt = conn.prepareStatement("insert into Book_Tbl values(?,?,?,?,?,?)");
			pstmt.setInt(1, bookDto.getBookCode());
			pstmt.setInt(2, bookDto.getClassificationId());
			pstmt.setString(3, bookDto.getBookAuthor());
			pstmt.setString(4, bookDto.getBookName());
			pstmt.setString(5, bookDto.getPublisher());
			pstmt.setInt(6, bookDto.getIsreserve());
			
			
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's INSERT SQL EXCEPTION!!");
		}finally {
			try {pstmt.close();} catch (SQLException e) {}
		}
	}
	// 수정
	public int update(BookDTO bookDto) throws SQLException {
	    pstmt = conn.prepareStatement(
	            "UPDATE Book_Tbl SET CLASSIFICATION_ID=?, BOOK_AUTHOR=?, BOOK_NAME=?, PUBLISHER=?, ISRESERVE=? WHERE BOOK_CODE=?");

	        pstmt.setInt(1, bookDto.getClassificationId());
	        pstmt.setString(2, bookDto.getBookAuthor());
	        pstmt.setString(3, bookDto.getBookName());
	        pstmt.setString(4, bookDto.getPublisher());
	        pstmt.setInt(5, bookDto.getIsreserve());
	        pstmt.setInt(6, bookDto.getBookCode());

	        return pstmt.executeUpdate();
	}
	//삭제
	public int delete(BookDTO bookDto) throws SQLException {
	    pstmt = conn.prepareStatement("DELETE FROM Book_Tbl WHERE BOOK_CODE = ?");
	    pstmt.setInt(1, bookDto.getBookCode());
	    return pstmt.executeUpdate();
	}
	//단건조회
	public BookDTO select(BookDTO bookDto) throws Exception{
		
		try {
		pstmt = conn.prepareStatement("select * from Book_Tbl where BOOK_CODE=?");
		pstmt.setInt(1, bookDto.getBookCode());
		rs = pstmt.executeQuery();
		BookDTO resultDTO = null ;

			if(rs.next()) {
				resultDTO = new BookDTO();
				resultDTO.setBookCode(rs.getInt("BOOK_CODE"));
				resultDTO.setClassificationId(rs.getInt("CLASSIFICATION_ID"));
				resultDTO.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				resultDTO.setBookName(rs.getString("BOOK_NAME"));
				resultDTO.setPublisher(rs.getString("PUBLISHER"));
				resultDTO.setIsreserve(rs.getInt("ISRESERVE"));

			}
		
		return resultDTO;
	} catch (SQLException e) {
		e.printStackTrace();
		throw new SQLException("BOOKDAO's select SQL EXCEPTION!!");
	}finally {
		try {pstmt.close();} catch (SQLException e) {}
	}
	}
	
	//다건조회
	public List<BookDTO> selectAll() throws Exception{	
		
		try {
		List<BookDTO> resultList = new ArrayList<>();
		pstmt = conn.prepareStatement("select * from Book_Tbl");
		
		rs = pstmt.executeQuery();
		

		if(rs!=null) {			
			while(rs.next()) {
				BookDTO dto = new BookDTO();
				dto.setBookCode(rs.getInt("BOOK_CODE"));
				dto.setClassificationId(rs.getInt("CLASSIFICATION_ID"));
				dto.setBookAuthor(rs.getString("BOOK_AUTHOR"));
				dto.setBookName(rs.getString("BOOK_NAME"));
				dto.setPublisher(rs.getString("PUBLISHER"));
				dto.setIsreserve(rs.getInt("ISRESERVE"));
				resultList.add(dto);
			}
		}
		return resultList;
	} catch (SQLException e) {
		e.printStackTrace();
		throw new SQLException("BOOKDAO's select SQL EXCEPTION!!");
	}finally {
		try {pstmt.close();} catch (SQLException e) {}
	}
	}
		
	
}
