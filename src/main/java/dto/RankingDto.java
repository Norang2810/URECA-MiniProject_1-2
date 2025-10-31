package dto;

import java.sql.Timestamp;

public class RankingDto {
    private int id;
    private int playerId;
    private int gameId;
    private int score;
    private Timestamp createdAt;

    // DTO 확장용 필드 (JOIN 시 사용)
    private String playerName;
    private String gameTitle;

    public RankingDto() {}

    public RankingDto(int id, int playerId, int gameId, int score, Timestamp createdAt) {
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        this.score = score;
        this.createdAt = createdAt;
    }

    // getter/setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    @Override
    public String toString() {
        return "RankingDto [id=" + id + ", playerId=" + playerId + ", gameId=" + gameId +
               ", score=" + score + ", createdAt=" + createdAt +
               ", playerName=" + playerName + ", gameTitle=" + gameTitle + "]";
    }
}
