package logvinov.springcourse.models;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;


public class Person {
    private int id;

    @NotEmpty(message = "This field cannot be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50  characters")
    private String name;

    @Min(value = 0, message = "Age must be greater than zeroy")
    private int age;

    @NotEmpty(message = "This field cannot be empty")
    @Email(message = "You entered an invalid email")
    private String email;


    public Person() {
    }
//    public Person(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }


    public Person(int id, String name, int age, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
