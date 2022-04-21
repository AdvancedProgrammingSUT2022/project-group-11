package Model;

public class User
{
    private String username;
    private String password;
    private String nickname;
    private int score;

    public User (String username, String password, String nickname)
    {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
