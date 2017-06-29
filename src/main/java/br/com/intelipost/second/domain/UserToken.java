package br.com.intelipost.second.domain;

/**
 * Created by lucastex on 28/06/17.
 */
public class UserToken {

    private User user;
    private String hash;

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getHash() {
        return this.hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

}
