package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dto.GenreDto;
import util.DBUtil;

public class GenreDao {

    // 전체 장르 목록 조회
    public List<GenreDto> selectAll() {
        List<GenreDto> list = new ArrayList<>();
        String sql = "SELECT genre_id, genre_name FROM genres ORDER BY genre_id DESC";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                GenreDto dto = new GenreDto();
                dto.setGenreId(rs.getInt("genre_id"));
                dto.setGenreName(rs.getString("genre_name"));
                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 장르 등록
    public void insert(GenreDto dto) {
        String sql = "INSERT INTO genres (genre_name) VALUES (?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dto.getGenreName());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 장르 삭제
    public void delete(int id) {
        String sql = "DELETE FROM genres WHERE genre_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
