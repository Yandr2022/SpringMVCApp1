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
            add(new Person(++PEOPLE_COUNT, "Yuli", "Andr", 35, "YA@gmail.com"));
            add(new Person(++PEOPLE_COUNT, "Ivan", "Andr", 35, "IA@gmail.com"));
            add(new Person(++PEOPLE_COUNT, "Natali", "Kovshik", 50, "NK@mail.ru"));
            add(new Person(++PEOPLE_COUNT, "Alex", "Smirniy", 45, "AS@yandex.ru"));
        }};
    }

    public List<Person> index() {
        return people;
    }

    public Person show(int id) {
        return people.stream().filter(person -> person.getId() == id)
                .findAny().orElse(null);
    }

    public void save(Person person) {
        person.setId(++PEOPLE_COUNT);
        people.add(person);
    }

    public void update(int id, Person updPerson) {
        Person personToBeUpdated = show(id);
        personToBeUpdated.setName(updPerson.getName());
        personToBeUpdated.setSurname(updPerson.getSurname());
        personToBeUpdated.setAge(updPerson.getAge());
        personToBeUpdated.setEmail(updPerson.getEmail());
    }

    public void delete(int id) {
        people.removeIf(p -> p.getId() == id);
    }
}
