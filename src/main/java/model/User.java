package model;

public class User implements Comparable<User>{
    private String username;
    private String password;
    private int highScore;
    private String slogan;
    private String email;
    private String securityQ;
    private String securityA;

    public User(String username, String password, int highscore, String slogan, String email, String securityQ, String securityA) {
        this.username = username;
        this.password = password;
        this.highScore = highscore;
        this.slogan = slogan;
        this.email = email;
        this.securityQ = securityQ;
        this.securityA = securityA;
    }

    public int getHighScore() {
        return highScore;
    }

    public String getSlogan() {
        return slogan;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    @Override
    public int compareTo(User o) {
        return o.highScore - this.highScore;
    }
}
