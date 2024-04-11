package com.wasd.usermicroservice.persistence.user;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserJdbcRepository implements UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select id, email, registered_at, username, password " +
                "from users", this::mapRowToUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select id, email, registered_at, username, password from users where id = ?",
                    this::mapRowToUser,
                    id));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(
                    "select * from users where username = ?",
                    this::mapRowToUser,
                    username));
        } catch (EmptyResultDataAccessException exception) {
            return Optional.empty();
        }
    }

    @Override
    public void save(User user) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("""
                insert into users (username, email, registered_at, password)
                values (?, ?, ?, ?)
                """);

        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(List.of(
                user.getUsername(), user.getEmail(), user.getRegisteredAt(), user.getPassword()
        ));

        GeneratedKeyHolder generatedKeyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(psc, generatedKeyHolder);
        Map<String, Object> keys = generatedKeyHolder.getKeys();

        if (keys != null) {
            user.setId((Long) keys.get("id"));
        }
    }

    @Override
    public void update(User user) {
        jdbcTemplate.update("""
                update users set
                username = ?,
                email = ?,
                password = ?
                where id = ?
                """, user.getUsername(), user.getEmail(), user.getPassword(), user.getId());
    }

    @Override
    public void delete(User user) {
        jdbcTemplate.update("delete from users where id = ?", user.getId());
    }

    private User mapRowToUser(ResultSet resultSet, int rowNum) throws SQLException {
        return User.builder()
                .id(resultSet.getLong("id"))
                .email(resultSet.getString("email"))
                .registeredAt(resultSet.getTimestamp("registered_at").toLocalDateTime())
                .username(resultSet.getString("username"))
                .password(resultSet.getString("password"))
                .build();
    }
}