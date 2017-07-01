package br.com.intelipost.second.services;

import br.com.intelipost.second.domain.Link;
import br.com.intelipost.second.repositories.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
@Service
@Configuration
public class LinkService {

    @Autowired
    private LinkRepository linkRepository;

    @Cacheable("links")
    public List<Link> getLinksForToken(String token) {
        return linkRepository.findLinksByToken(token);
    }
}
