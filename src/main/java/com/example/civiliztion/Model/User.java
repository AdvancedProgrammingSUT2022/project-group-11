package com.example.civiliztion.Model;

public class User {
    private String username;
    private String password;
    private String nickname;
    private Civilization civilization;
    private int score;

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
}
