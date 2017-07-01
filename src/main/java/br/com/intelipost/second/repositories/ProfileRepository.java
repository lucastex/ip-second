package br.com.intelipost.second.repositories;

import br.com.intelipost.second.domain.Profile;
import br.com.intelipost.second.util.mappers.ProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lucastex on 30/06/17.
 */
@Repository
public class ProfileRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProfileRowMapper profileRowMapper;

    @Transactional(readOnly = true)
    public List<Profile> getProfilesByUser(Long userId) {
        return jdbcTemplate.query("select p.* from profile p, user_profile up where up.profile_id = p.id and up.user_id = ?", new Object[]{userId}, profileRowMapper);
    }

}
