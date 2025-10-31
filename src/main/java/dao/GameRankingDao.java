package dao;

import dto.RankingDto;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameRankingDao {

    // ✅ 전체 랭킹 목록 조회 (JOIN으로 플레이어 이름, 게임명 포함)
    public List<RankingDto> selectAll() {
        List<RankingDto> list = new ArrayList<>();
        String sql = """
            SELECT r.id, r.player_id, r.game_id, r.score, r.created_at,
                   p.player_name, g.game_title
            FROM game_rankings r
            JOIN players p ON r.player_id = p.player_id
            JOIN games g ON r.game_id = g.game_id
            ORDER BY r.score DESC
        """;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                RankingDto dto = new RankingDto();
                dto.setId(rs.getInt("id"));
                dto.setPlayerId(rs.getInt("player_id"));
                dto.setGameId(rs.getInt("game_id"));
                dto.setScore(rs.getInt("score"));
                dto.setCreatedAt(rs.getTimestamp("created_at"));
                dto.setPlayerName(rs.getString("player_name"));
                dto.setGameTitle(rs.getString("game_title"));
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return list;
    }

    // ✅ 랭킹 등록
    public void insert(RankingDto dto) {
        String sql = "INSERT INTO game_rankings (player_id, game_id, score) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dto.getPlayerId());
            pstmt.setInt(2, dto.getGameId());
            pstmt.setInt(3, dto.getScore());
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(pstmt, conn);
        }
    }

    // ✅ 랭킹 삭제
    public void delete(int id) {
        String sql = "DELETE FROM game_rankings WHERE id = ?";
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

    // ✅ 특정 플레이어의 최고 점수 조회 (선택 기능)
    public int getBestScoreByPlayer(int playerId) {
        String sql = "SELECT MAX(score) FROM game_rankings WHERE player_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int bestScore = 0;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, playerId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                bestScore = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(rs, pstmt, conn);
        }

        return bestScore;
    }
}
