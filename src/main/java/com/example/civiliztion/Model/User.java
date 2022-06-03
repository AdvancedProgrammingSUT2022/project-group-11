package com.example.civiliztion.Model;


public class User {
    private String username;
    private String password;
    private String nickname;
    private Civilization civilization;
    private int score = 0;
    private String lastWin = "00:00";
    private String lastLogin = "00:00";

    private String profilePicture = "/src/main/resources/com/example/civiliztion/PNG/Avatars/users.png";

    public User(String username, String password, String nickname, Civilization civil) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.civilization = civil;
    }

    public Civilization getCivilization() {
        return this.civilization;
    }

    public void setCivilization(Civilization civil) {
        this.civilization = civil;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastWin() {
        return lastWin;
    }

    public void setLastWin(String lastWin) {
        this.lastWin = lastWin;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
    public String getProfilePicture() {
        return profilePicture;
    }


}
