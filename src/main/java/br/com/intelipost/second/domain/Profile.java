package br.com.intelipost.second.domain;

/**
 * Created by lucastex on 30/06/17.
 */
public class Profile {

    private Long id;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean equals(Profile profile) {
        return profile.getId().equals(id);
    }
}
