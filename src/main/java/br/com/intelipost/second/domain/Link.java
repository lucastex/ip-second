package br.com.intelipost.second.domain;

/**
 * Created by lucastex on 30/06/17.
 */
public class Link {

    private Long id;
    private String name;
    private String path;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
