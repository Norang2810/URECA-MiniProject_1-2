package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Tomcat JNDI 기반 Connection Pool 관리 클래스
 * - context.xml에 정의된 Resource (jdbc/madang) 사용
 * - Connection close() 시 실제 종료가 아닌 "풀 반납" 처리됨
 */
public class DBUtil {

    // Connection 객체 빌려오기
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // JNDI Context 초기화
            Context initCtx = new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/madang");
            conn = ds.getConnection();
        } catch (Exception e) {
            System.err.println("❌ DB 커넥션 획득 실패: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

    //  PreparedStatement, Connection 반납
    public static void close(PreparedStatement pstmt, Connection conn) {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close(); // 실제 종료 아님, 풀에 반납됨
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //ResultSet, PreparedStatement, Connection 반납
    public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //가변인자 버전 (유연하게 자원 해제 가능)
    public static void close(AutoCloseable... resources) {
        for (AutoCloseable r : resources) {
            if (r != null) {
                try {
                    r.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
