package br.com.intelipost.second.repositories;

import br.com.intelipost.second.domain.Link;
import br.com.intelipost.second.domain.Profile;
import br.com.intelipost.second.util.mappers.LinkRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
@Repository
public class LinkRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private LinkRowMapper linkRowMapper;

    @Transactional(readOnly = true)
    public List<Link> findLinksByToken(String token) {
        return jdbcTemplate.query("select l.* from link l, link_profile lp, user_profile up, user_token ut where l.id = lp.link_id and lp.profile_id = up.profile_id and up.user_id = ut.user_id and ut.hash = ?;", new Object[]{ token }, linkRowMapper);
    }
}
