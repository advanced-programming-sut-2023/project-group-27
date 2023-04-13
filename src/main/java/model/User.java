package model;

public class User {
    String username;
    String password;
    int highscore;
    String slogan;
    String email;
    String securityQ;
    String securityA;

    public User(String username, String password, int highscore, String slogan, String email, String securityQ, String securityA) {
        this.username = username;
        this.password = password;
        this.highscore = highscore;
        this.slogan = slogan;
        this.email = email;
        this.securityQ = securityQ;
        this.securityA = securityA;
    }
}
