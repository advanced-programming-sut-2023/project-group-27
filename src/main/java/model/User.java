package model;

import java.io.PrintStream;

public class User implements Comparable<User>{
    private String username;
    private String nickname;
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

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getSecurityQ() {
        return securityQ;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public String getSecurityA() {
        return securityA;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSecurityQ(String securityQ) {
        this.securityQ = securityQ;
    }

    public void setSecurityA(String securityA) {
        this.securityA = securityA;
    }

    public boolean isPasswordCorrect(String password) {
        return this.password.equals(password);
    }

    @Override
    public int compareTo(User o) {
        return o.highScore - this.highScore;
    }
}
