package com.example.batchprocessing;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonDaoImpl implements PersonDao {

    private final JdbcTemplate jdbcTemplate;

    public PersonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @SuppressWarnings("deprecation")
    @Override
    public Person findByLastName(String lastname) {
        String sql = "SELECT * FROM people WHERE last_name = ?";
        return jdbcTemplate.queryForObject(sql, 
                new Object[]{lastname}, 
                (rs, rowNum) -> new Person( rs.getString("last_name"), rs.getString("first_name")) 
        );
    }

    @SuppressWarnings("deprecation")
    @Override
    public Person getPersonById(Long id) {
        String sql = "SELECT * FROM people WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, 
                new Object[]{id}, 
                (rs, rowNum) -> new Person( rs.getString("last_name"), rs.getString("first_name"))
        );
    }
//
//    @Override
//    public List<Person> getAllPersons() {
//        String sql = "SELECT * FROM people";
//         return jdbcTemplate.query(sql, (rs, rowNum) ->
//                new Person(rs.getLong("id"), rs.getString("name"), rs.getInt("age"))
//        );
//    }
//
//    @Override
//    public boolean createPerson(Person person) {
//         String sql = "INSERT INTO people (name, age) VALUES (?, ?)";
//         int rowsAffected = jdbcTemplate.update(sql, person.getName(), person.getAge());
//         return rowsAffected > 0;
//    }
//
//    @Override
//    public boolean updatePerson(Person person) {
//        String sql = "UPDATE people SET name = ?, age = ? WHERE id = ?";
//        int rowsAffected = jdbcTemplate.update(sql, person.getName(), person.getAge(), person.getId());
//        return rowsAffected > 0;
//    }
//
//    @Override
//    public boolean deletePerson(Person person) {
//        String sql = "DELETE FROM people WHERE id = ?";
//        int rowsAffected = jdbcTemplate.update(sql, person.getId());
//        return rowsAffected > 0;
//    }


}