//package logvinov.springcourse.dao;
//
//import logvinov.springcourse.models.Person;
//import org.springframework.jdbc.core.RowMapper;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class PersonMapper implements RowMapper<Person> {
//    @Override
//    public Person mapRow(ResultSet resultSet, int i) throws SQLException {
//
//        Person person = new Person();
//
//        person.setId(resultSet.getInt("idperson"));
//        person.setName(resultSet.getString("name"));
//        person.setAge(resultSet.getInt("age"));
//        person.setEmail(resultSet.getString("email"));
//
//        return person;
//    }
//}
