package service;

import dao.GameRankingDao;
import dto.RankingDto;

import java.util.List;

public class GameRankingService {
    private final GameRankingDao dao = new GameRankingDao();

    // 전체 랭킹 조회
    public List<RankingDto> getAllRankings() {
        return dao.selectAll();
    }

    // 랭킹 등록
    public void insertRanking(RankingDto dto) {
        if (dto.getPlayerId() <= 0 || dto.getGameId() <= 0) {
            throw new IllegalArgumentException("플레이어 ID와 게임 ID는 필수입니다.");
        }
        if (dto.getScore() < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
        dao.insert(dto);
    }

    // 랭킹 삭제
    public void deleteRanking(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("잘못된 랭킹 ID입니다.");
        }
        dao.delete(id);
    }

    // 특정 플레이어 최고 점수 조회
    public int getBestScoreByPlayer(int playerId) {
        if (playerId <= 0) {
            throw new IllegalArgumentException("플레이어 ID가 유효하지 않습니다.");
        }
        return dao.getBestScoreByPlayer(playerId);
    }
}
