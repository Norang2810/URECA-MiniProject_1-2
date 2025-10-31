package service;

import dao.GameDao;
import dto.GameDto;

import java.util.List;

public class GameService {
    private final GameDao dao = new GameDao();

    // 전체 게임 목록 조회
    public List<GameDto> getAllGames() {
        return dao.selectAll();
    }

    // 게임 등록
    public void insertGame(GameDto dto) {
        if (dto.getGameTitle() == null || dto.getGameTitle().isBlank()) {
            throw new IllegalArgumentException("게임 제목은 필수입니다.");
        }
        dao.insert(dto);
    }

    // 게임 삭제
    public void deleteGame(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("잘못된 게임 ID입니다.");
        }
        dao.delete(id);
    }
}
