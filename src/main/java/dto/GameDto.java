package dto;

import java.sql.Timestamp;

public class GameDto {
    private int gameId;
    private String gameTitle;
    private int genreId;
    private Timestamp createdAt;

    public GameDto() {}

    public GameDto(int gameId, String gameTitle, int genreId, Timestamp createdAt) {
        this.gameId = gameId;
        this.gameTitle = gameTitle;
        this.genreId = genreId;
        this.createdAt = createdAt;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "GameDto [gameId=" + gameId + ", gameTitle=" + gameTitle +
               ", genreId=" + genreId + ", createdAt=" + createdAt + "]";
    }
}
