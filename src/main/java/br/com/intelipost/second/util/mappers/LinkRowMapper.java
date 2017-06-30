package br.com.intelipost.second.util.mappers;

import br.com.intelipost.second.domain.Link;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lucastex on 30/06/17.
 */
@Component
public class LinkRowMapper implements RowMapper<Link> {

    @Override
    public Link mapRow(ResultSet rs, int rowNum) throws SQLException {

        Link link = new Link();
        link.setId(rs.getLong("id"));
        link.setName(rs.getString("name"));
        link.setPath(rs.getString("path"));

        return link;
    }
}
