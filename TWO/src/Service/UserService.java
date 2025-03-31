package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Domain.UserDao;
import Domain.UserDto;

public class UserService {
	private UserDao userDao ;
	
	private static UserService instance;
	private UserService () throws Exception {
		userDao=UserDao.getInstance();
		System.out.println("[Sevice] UserService init...");
	}
	public static UserService getInstance() throws Exception {
		if(instance==null)
			instance = new UserService();
		return instance;
	}
	
	// 단건 조회 (회원 ID로 조회)
	public UserDto getUserById(int memberId) throws Exception {
	    UserDto user = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	conn = UserDao.getConnection(); 
	    	
	        if (conn == null) {
	            throw new Exception("데이터베이스 연결을 가져올 수 없습니다.");
	        }

	        String sql = "SELECT * FROM Member_Tbl WHERE MEMBER_ID = ?";
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, memberId);
	        rs = pstmt.executeQuery();
	        
	        if (rs.next()) {
	            user = new UserDto(
	                rs.getInt("member_id"),
	                rs.getString("member_name"),
	                rs.getString("member_identity"),
	                rs.getString("member_grade"),
	                rs.getString("member_addr"),
	                rs.getString("member_phone")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new SQLException("회원 조회 중 오류 발생!");
	    } finally {
	        if (rs != null) try { rs.close(); } catch (Exception e) {}
	        if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
	        if (conn != null) try { conn.close(); } catch (Exception e) {} 
	    }
	    
	    return user;
	}

	// 다건 조회 (전체 회원 리스트 조회)
	public List<UserDto> getAllUsers() throws Exception {
	    List<UserDto> userList = new ArrayList<>();
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	 conn = UserDao.getConnection();  // DatabaseUtil 대신 UserDao에서 직접 관리
	         
	         if (conn == null) {
	             throw new SQLException("데이터베이스 연결을 가져올 수 없습니다.");
	         }
	         
	        String sql = "SELECT * FROM Member_Tbl";
	        pstmt = conn.prepareStatement(sql);
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            UserDto user = new UserDto(
	                rs.getInt("member_id"),
	                rs.getString("member_name"),
	                rs.getString("member_identity"),
	                rs.getString("member_grade"),
	                rs.getString("member_addr"),
	                rs.getString("member_phone")
	            );
	            userList.add(user);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        throw new SQLException("전체 회원 조회 중 오류 발생!");
	    } finally {
	        if (rs != null) try { rs.close(); } catch (Exception e) {}
	        if (pstmt != null) try { pstmt.close(); } catch (Exception e) {}
	        if (conn != null) try { conn.close(); } catch (Exception e) {} 
	    }
	    
	    return userList;
	}


	
}


