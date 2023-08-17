package by.Yandr2022.springlearn.dao;

import by.Yandr2022.springlearn.models.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;
    List<Person> people;

    {
        people = new ArrayList<>() {{
            add(new Person(++PEOPLE_COUNT, "Yuli"));
            add(new Person(++PEOPLE_COUNT, "Ivan"));
            add(new Person(++PEOPLE_COUNT, "Natali"));
            add(new Person(++PEOPLE_COUNT, "Alex"));
        }};
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }
}
