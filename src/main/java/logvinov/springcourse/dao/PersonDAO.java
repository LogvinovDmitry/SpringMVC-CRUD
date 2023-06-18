package logvinov.springcourse.dao;

import logvinov.springcourse.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAO {

    private static final String GET_ALL_PEOPLE = "SELECT id, name, age, email FROM `springmvc_crud`.person";


    private static final String GET_PERSON_BY_ID = "SELECT id, name, age, email FROM `springmvc_crud`.person WHERE id = ?";
    private static final String INSERT_NEW_PERSON = "INSERT INTO `springmvc_crud`.`person` VALUES (NULL, ?, ?, ?)";
    private static final String UPDATE_PERSON = "UPDATE `springmvc_crud`.`person` SET `name` = ?, `age` = ?, `email` = ? WHERE (`id` = ?)";
    private static final String DELETE_PERSON = "DELETE FROM `springmvc_crud`.`person` WHERE (`id` = ?)";


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Person> index() {
        // List<Person> query = jdbcTemplate.query(GET_ALL_PEOPLE, new PersonMapper());

        //return jdbcTemplate.query(GET_ALL_PEOPLE, new PersonMapper()); //с использованием своего напечатанного Роумапера
        return jdbcTemplate.query(GET_ALL_PEOPLE, new BeanPropertyRowMapper<>(Person.class)); //с использованием встроенного Роумапера в спринг,
        //который переводит значения из БД в поля нужного объекта.
    }


    //new Object[]{id} - вторй арргумент ожидает массив, значения которго будут подставлены заместо
    // знаков вопросов в запросе к БД.

    // .stream().findAny().orElse(null) - если в списке есть элемент с нужным айди, он будет возвращен;
    // если его нет то будет возврещен null.
    public Person show(int id) {
        return jdbcTemplate.query(GET_PERSON_BY_ID, new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);

    }

    public void save(Person person) {
        jdbcTemplate.update(INSERT_NEW_PERSON, person.getName(), person.getAge(), person.getEmail());

    }

    public void update(int id, Person updatePerson) {
        jdbcTemplate.update(UPDATE_PERSON, updatePerson.getName(), updatePerson.getAge(), updatePerson.getEmail(), id);

    }

    public void delete(int id) {
        jdbcTemplate.update(DELETE_PERSON, id);

    }


}
