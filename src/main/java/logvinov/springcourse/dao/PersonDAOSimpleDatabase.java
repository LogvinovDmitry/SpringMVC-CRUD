package logvinov.springcourse.dao;

import logvinov.springcourse.models.Person;
import logvinov.springcourse.util.ConnectionSpringMVSQRUDSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
public class PersonDAOSimpleDatabase {


    private static final String GET_PERSON_BY_ID = "SELECT idperson, name, age, email FROM `springmvs-qrud`.person WHERE idperson = ?";
    private static final String INSERT_NEW_PERSON = "INSERT INTO `springmvs-qrud`.`person` VALUES (NULL, ?, ?, ?)";
    private static final String UPDATE_PERSON = "UPDATE `springmvs-qrud`.`person` SET `name` = ?, `age` = ?, `email` = ? WHERE (`idperson` = ?)";
    private static final String DELETE_PERSON = "DELETE FROM `springmvs-qrud`.`person` WHERE (`idperson` = ?)";


    ConnectionSpringMVSQRUDSchema conLeather = new ConnectionSpringMVSQRUDSchema();
    //Connection con = conLeather.getConnection();

    public List<Person> index() {
        final String GET_ALL_PEOPLE = "SELECT idperson, name, age, email FROM `springmvs-qrud`.person";

        Connection con = conLeather.getConnection();
        List<Person> people = new ArrayList<>();

        try (Statement statement = con.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_PEOPLE)) {

            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getInt("idperson"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));

                people.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return people;
    }

    public Person show(int id) {
        Person person = new Person();
        Connection con = conLeather.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_PERSON_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                person.setId(resultSet.getInt("idperson"));
                person.setName(resultSet.getString("name"));
                person.setAge(resultSet.getInt("age"));
                person.setEmail(resultSet.getString("email"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return person;
    }

    public void save(Person person) {
        Connection con = conLeather.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_NEW_PERSON)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());
            preparedStatement.setString(3, person.getEmail());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void update(int id, Person updatePerson) {

        Connection con = conLeather.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(UPDATE_PERSON)) {
            preparedStatement.setString(1, updatePerson.getName());
            preparedStatement.setInt(2, updatePerson.getAge());
            preparedStatement.setString(3, updatePerson.getEmail());
            preparedStatement.setInt(4, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

        Connection con = conLeather.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_PERSON)) {
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
