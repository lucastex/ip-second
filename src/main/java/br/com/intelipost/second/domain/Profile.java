package br.com.intelipost.second.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
public class Profile {

    private Long id;
    private String name;
    private List<Link> links;

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

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public List<Link> getLinks() {
        return links;
    }

    public Profile addLink(Link link) {

        if (links == null)
            links = new LinkedList<>();

        links.add(link);
        return this;
    }

    public boolean equals(Profile profile) {
        return profile.getId().equals(id);
    }
}
