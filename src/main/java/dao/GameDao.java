package dao;

import dto.GameDto;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {

    // 전체 게임 목록 조회 (장르 JOIN)
    public List<GameDto> selectAll() {
        List<GameDto> list = new ArrayList<>();
        String sql = """
            SELECT g.game_id, g.game_title, g.genre_id, g.created_at
            FROM games g
            ORDER BY g.game_id DESC
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                GameDto dto = new GameDto();
                dto.setGameId(rs.getInt("game_id"));
                dto.setGameTitle(rs.getString("game_title"));
                dto.setGenreId(rs.getInt("genre_id"));
                dto.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return list;
    }

    // 게임 등록
    public void insert(GameDto dto) {
        String sql = "INSERT INTO games (game_title, genre_id) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getGameTitle());
            if (dto.getGenreId() > 0) pstmt.setInt(2, dto.getGenreId());
            else pstmt.setNull(2, Types.INTEGER);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
    }

    // 게임 삭제
    public void delete(int id) {
        String sql = "DELETE FROM games WHERE game_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
    }
}
