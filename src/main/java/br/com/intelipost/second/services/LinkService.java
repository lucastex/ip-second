package br.com.intelipost.second.services;

import br.com.intelipost.second.domain.Link;
import br.com.intelipost.second.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
@Service
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    public List<Link> getLinksForUser(String token) {
        return linkRepository.findLinksByToken(token);
    }
}
