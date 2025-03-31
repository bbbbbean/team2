package Domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    // DB 연결 정보
    private static final String ID = "system";
    private static final String PW = "1234";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";

    // 싱글톤 패턴 적용
    private static UserDao instance;

    private UserDao() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
    }

    public static UserDao getInstance() throws Exception {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, ID, PW);
        if (conn == null) {
            throw new SQLException("DB 연결 실패!");
        }
        System.out.println("DB 연결 성공");
        return conn;
    }

    // 회원 단건 조회
    public UserDto select(int memberId) throws Exception {
        UserDto user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            if (conn == null) {
                throw new SQLException("DB 연결이 설정되지 않았습니다.");
            }

            // ✅ 테이블명이 정확한지 확인하세요. (대소문자 주의)
            String sql = "SELECT * FROM Member_Tbl WHERE MEMBER_ID = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, memberId);
            rs = pstmt.executeQuery();

            if (rs.next()) {  
                user = new UserDto(
                    rs.getInt("Member_id"),
                    rs.getString("Member_name"),
                    rs.getString("Member_identity"),
                    rs.getString("Member_grade"),
                    rs.getString("Member_addr"),
                    rs.getString("Member_phone")
                );
            } else {
                System.out.println("회원 정보가 존재하지 않습니다. ID: " + memberId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("회원 조회 중 오류 발생: " + e.getMessage());
        } finally {
            close(rs, pstmt, conn);
        }

        return user;
    }

    // 회원 전체 조회
    public List<UserDto> selectAll() throws Exception {
        List<UserDto> userList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            if (conn == null) {
                throw new SQLException("DB 연결이 설정되지 않았습니다.");
            }

            String sql = "SELECT * FROM  Member_Tbl";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) { 
                UserDto user = new UserDto(
                    rs.getInt("Member_id"),
                    rs.getString("Member_name"),
                    rs.getString("Member_identity"),
                    rs.getString("Member_grade"),
                    rs.getString("Member_addr"),
                    rs.getString("Member_phone")
                );
                userList.add(user);
            }

            if (userList.isEmpty()) {
                System.out.println("등록된 회원이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("전체 회원 조회 중 오류 발생: " + e.getMessage());
        } finally {
            close(rs, pstmt, conn);
        }

        return userList;
    }

    private void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
        try { if (pstmt != null) pstmt.close(); } catch (Exception e) { e.printStackTrace(); }
        try { if (conn != null) conn.close(); } catch (Exception e) { e.printStackTrace(); }
    }
}
