package br.com.intelipost.second.daos;

import br.com.intelipost.second.LoginNotFoundException;
import br.com.intelipost.second.domain.User;
import br.com.intelipost.second.domain.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lucastex on 28/06/17.
 */
@Repository
public class UserRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Transactional(readOnly=true)
    public User findUserByCredentials(String username, String hashedPassword) throws LoginNotFoundException {

        User user = jdbcTemplate.query(
        "select id, username from user where username = ? and password = ?",
        new ResultSetExtractor<User>() {
            public User extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setUsername(rs.getString("username"));
                    return user;
                }
                return null;
            }
        }, new Object[]{ username, hashedPassword });

        return user;
    }

    public UserToken createNewToken(User user) {

        String token = UUID.randomUUID().toString();
        jdbcTemplate.update("insert into user_token (user_id, hash, login_date) values (?, ?, ?)", user.getId(), token, new Date());

        UserToken userToken = new UserToken();
        userToken.setUser(user);
        userToken.setHash(token);

        return userToken;
    }

    public void invalidateToken(String token) {
        jdbcTemplate.update("update user_token set logout_date = ? where hash = ?", new Date(), token);
    }
}
