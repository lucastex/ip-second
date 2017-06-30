package br.com.intelipost.second.util.mappers;

import br.com.intelipost.second.domain.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lucastex on 30/06/17.
 */
@Component
public class ProfileRowMapper implements RowMapper<Profile> {

    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {

        Profile profile = new Profile();
        profile.setId(rs.getLong("id"));
        profile.setName(rs.getString("name"));

        return profile;
    }
}
