package br.com.intelipost.second.util.mappers;

import br.com.intelipost.second.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lucastex on 30/06/17.
 */
@Component
public class UserResultSetExtractor implements ResultSetExtractor<User> {

    @Autowired
    private UserRowMapper userRowMapper;

    @Override
    public User extractData(ResultSet rs) throws SQLException, DataAccessException {

        if (rs.next()) {
            return userRowMapper.mapRow(rs, 0);
        }

        return null;

    }
}
