package logvinov.springcourse.dao;

import logvinov.springcourse.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAOcop {
    private static int PEOPLE_COUNT;
    private List<Person> people;

    //Блок инициализации поля. (Можно было бы селать через конструктор)
    {
        people = new ArrayList<>();
        people.add(new Person(++PEOPLE_COUNT, "Dimon", 19, "ggg@mail.ry"));
        people.add(new Person(++PEOPLE_COUNT, "Vasj", 74, "dsdsxsx@mail.ry"));
        people.add(new Person(++PEOPLE_COUNT, "Jenek", 54, "dlllllllllllsxsx@mail.ry"));
        people.add(new Person(++PEOPLE_COUNT, "Vanek", 89, "yyyyyyx@mail.ry"));
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        for (Person person : people) {
            if (person.getId() == id) {
                return person;
            }
        }
        return null;
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updatePerson){

        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updatePerson.getName());
        personToBeUpdated.setAge(updatePerson.getAge());
        personToBeUpdated.setEmail(updatePerson.getEmail());
    }
    public void delete(int id){

        people.removeIf(p -> p.getId() == id);
    }



}
