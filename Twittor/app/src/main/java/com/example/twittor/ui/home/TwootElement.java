package com.example.twittor.ui.home;

public class TwootElement {
    public int twootId;
    public String twootUserImg;
    public String twootUserName;
    public String twootUserTwoot;
    public int twootRetwoot;
    public int twootLike;

    public TwootElement(int twootId, String twootUserImg, String twootUserName, String twootUserTwoot, int twootRetwoot, int twootLike) {
        this.twootId = twootId;
        this.twootUserImg = twootUserImg;
        this.twootUserName = twootUserName;
        this.twootUserTwoot = twootUserTwoot;
        this.twootRetwoot = twootRetwoot;
        this.twootLike = twootLike;
    }

    public int getTwootId() {
        return twootId;
    }

    public void setTwootId(int twootId) {
        this.twootId = twootId;
    }

    public String getTwootUserImg() {
        return twootUserImg;
    }

    public void setTwootUserImg(String twootUserImg) {
        this.twootUserImg = twootUserImg;
    }

    public String getTwootUserName() {
        return twootUserName;
    }

    public void setTwootUserName(String twootUserName) {
        this.twootUserName = twootUserName;
    }

    public String getTwootUserTwoot() {
        return twootUserTwoot;
    }

    public void setTwootUserTwoot(String twootUserTwoot) {
        this.twootUserTwoot = twootUserTwoot;
    }

    public int getTwootRetwoot() {
        return twootRetwoot;
    }

    public void setTwootRetwoot(int twootRetwoot) {
        this.twootRetwoot = twootRetwoot;
    }

    public int getTwootLike() {
        return twootLike;
    }

    public void setTwootLike(int twootLikes) {
        this.twootLike = twootLikes;
    }
}
