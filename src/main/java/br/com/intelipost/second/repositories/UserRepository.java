package br.com.intelipost.second.repositories;

import br.com.intelipost.second.exception.LoginNotFoundException;
import br.com.intelipost.second.domain.User;
import br.com.intelipost.second.util.mappers.UserResultSetExtractor;
import br.com.intelipost.second.util.mappers.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by lucastex on 28/06/17.
 */
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRowMapper userRowMapper;

    @Autowired
    private UserResultSetExtractor userResultSetExtractor;

    @Transactional(readOnly = true)
    public User findUserByCredentials(String username, String hashedPassword) throws LoginNotFoundException {
        return jdbcTemplate.query("select id, username from user where username = ? and password = ?", userResultSetExtractor, new Object[]{username, hashedPassword});
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        return jdbcTemplate.query("select * from user", userRowMapper);
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {
        return jdbcTemplate.query("select * from user where id = ?", userResultSetExtractor, new Object[]{id});
    }
}