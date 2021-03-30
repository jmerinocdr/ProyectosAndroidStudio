package com.example.twittor;

public class Twoots {
    private int id;
    private String username;
    private String userimage;
    private String usermail;
    private String twoottext;
    private int retwoots;
    private int likes;

    public Twoots(int id, String username, String userimage, String usermail, String twoottext, int retwoots, int likes) {
        this.id = id;
        this.username = username;
        this.userimage = userimage;
        this.usermail = usermail;
        this.twoottext = twoottext;
        this.retwoots = retwoots;
        this.likes = likes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public String getUsermail() {
        return usermail;
    }

    public void setUsermail(String usermail) {
        this.usermail = usermail;
    }

    public String getTwoottext() {
        return twoottext;
    }

    public void setTwoottext(String twoottext) {
        this.twoottext = twoottext;
    }

    public int getRetwoots() {
        return retwoots;
    }

    public void setRetwoots(int retwoots) {
        this.retwoots = retwoots;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
