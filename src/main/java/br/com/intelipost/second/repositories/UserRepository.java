package br.com.intelipost.second.repositories;

import br.com.intelipost.second.LoginNotFoundException;
import br.com.intelipost.second.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by lucastex on 28/06/17.
 */
@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(readOnly = true)
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
                }, new Object[]{username, hashedPassword});

        return user;
    }

    @Transactional(readOnly = true)
    public List<User> listUsers() {
        List<User> users = jdbcTemplate.query("select * from USER",
                new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                        User user = new User();
                        user.setId(rs.getLong("id"));
                        user.setUsername(rs.getString("username"));
                        return user;
                    }
                }
        );

        return users;
    }

    @Transactional(readOnly = true)
    public User findById(Long id) {

        User user = jdbcTemplate.query(
                "select * from user where id = ?",
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
                }, new Object[]{id});

        return user;

    }
}