package  com.chessmaster.chessmaster.Model;

public class User{
    String username;
    String color;
    Boolean isTurn;

    public User() {}

    public User(String username, String color, Boolean isTurn) {
        this.username = username;
        this.color = color;
        this.isTurn = isTurn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getIsTurn() {
        return isTurn;
    }

    public void setIsTurn(Boolean isTurn) {
        this.isTurn = isTurn;
    }
 
}