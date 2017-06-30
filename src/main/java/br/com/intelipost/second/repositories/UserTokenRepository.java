package br.com.intelipost.second.repositories;

import br.com.intelipost.second.domain.User;
import br.com.intelipost.second.domain.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

/**
 * Created by lucastex on 30/06/17.
 */
@Repository
public class UserTokenRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
