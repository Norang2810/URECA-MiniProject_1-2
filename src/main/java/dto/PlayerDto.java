package dto;

import java.sql.Timestamp;

public class PlayerDto {
    private int playerId;
    private String playerName;
    private String email;
    private Timestamp createdAt;

    public PlayerDto() {}

    public PlayerDto(int playerId, String playerName, String email, Timestamp createdAt) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.email = email;
        this.createdAt = createdAt;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "PlayerDto [playerId=" + playerId + ", playerName=" + playerName +
               ", email=" + email + ", createdAt=" + createdAt + "]";
    }
}
