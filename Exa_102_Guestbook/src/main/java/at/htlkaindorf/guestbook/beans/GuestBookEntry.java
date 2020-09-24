package at.htlkaindorf.guestbook.beans;

import java.io.Serializable;

public class GuestBookEntry implements Serializable {

    private String nickname, email, comment;

    public GuestBookEntry(String nickname, String email, String comment) {
        this.nickname = nickname;
        this.email = email;
        this.comment = comment;
    }

    public GuestBookEntry() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "GuestBookEntry{" + "nickname=" + nickname + ", email=" + email + ", comment=" + comment + '}';
    }
    
    
    
}
