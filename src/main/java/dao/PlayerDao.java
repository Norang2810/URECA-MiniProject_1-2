package dao;

import dto.PlayerDto;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {

    // 전체 플레이어 조회
    public List<PlayerDto> selectAll() {
        List<PlayerDto> list = new ArrayList<>();
        String sql = "SELECT player_id, player_name, email, created_at FROM players ORDER BY player_id DESC";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                PlayerDto dto = new PlayerDto();
                dto.setPlayerId(rs.getInt("player_id"));
                dto.setPlayerName(rs.getString("player_name"));
                dto.setEmail(rs.getString("email"));
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

    // 플레이어 등록
    public void insert(PlayerDto dto) {
        String sql = "INSERT INTO players (player_name, email) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dto.getPlayerName());
            pstmt.setString(2, dto.getEmail());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
    }

    // 플레이어 삭제
    public void delete(int id) {
        String sql = "DELETE FROM players WHERE player_id = ?";
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
