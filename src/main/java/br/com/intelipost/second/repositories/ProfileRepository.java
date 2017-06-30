package br.com.intelipost.second.repositories;

import br.com.intelipost.second.domain.Profile;
import br.com.intelipost.second.util.mappers.ProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public List<Profile> findAllProfiles() {
        return jdbcTemplate.query("select * from profile;", profileRowMapper);
    }
}
