package service;

import dao.PlayerDao;
import dto.PlayerDto;

import java.util.List;

public class PlayerService {
    private final PlayerDao dao = new PlayerDao();

    // 전체 플레이어 조회
    public List<PlayerDto> getAllPlayers() {
        return dao.selectAll();
    }

    // 플레이어 등록
    public void insertPlayer(PlayerDto dto) {
        if (dto.getPlayerName() == null || dto.getPlayerName().isBlank()) {
            throw new IllegalArgumentException("플레이어 이름은 필수입니다.");
        }
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("이메일은 필수입니다.");
        }
        dao.insert(dto);
    }

    // 플레이어 삭제
    public void deletePlayer(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("잘못된 ID 값입니다.");
        }
        dao.delete(id);
    }
}
