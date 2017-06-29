package br.com.intelipost.second.domain;

/**
 * Created by lucastex on 28/06/17.
 */
public class User {

    private Long id;
    private String username;

    public String getUsername() {
        return this.username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

}
