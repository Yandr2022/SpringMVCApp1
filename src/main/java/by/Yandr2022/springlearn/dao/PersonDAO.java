package by.Yandr2022.springlearn.dao;

import by.Yandr2022.springlearn.models.Person;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PEOPLE_COUNT;

    private static final String URL = "jdbc:mysql://localhost:3306/first_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Zasada301187";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> index() {
        List<Person> people = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM person");

            while (resultSet.next()) {
                Person person = new Person(resultSet.getInt("id"), resultSet.getString("name")
                        , resultSet.getInt("age"), resultSet.getString("email"));
                people.add(person);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return people;
    }

    public Person show(int id) {
        Person person = null;
        try {
            PreparedStatement statement
                    = connection.prepareStatement("SELECT * from first_db.person where person.id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            set.next();
            person = new Person(set.getInt("id"), set.getString("name")
                    , set.getInt("age"), set.getString("email"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public void save(Person person) {
//        person.setId(++PEOPLE_COUNT);
//        people.add(person);
        try {
            PreparedStatement statement
                    = connection.prepareStatement("INSERT INTO first_db.person values(1, ?, ?, ?)");
            statement.setString(1, person.getName());
            statement.setInt(2, person.getAge());
            statement.setString(3, person.getEmail());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int id, Person updatedPerson) {
        try {
            PreparedStatement statement
                    = connection.prepareStatement("UPDATE first_db.person SET name=?, age=?, email=? WHERE id=?");
            statement.setString(1, updatedPerson.getName());
            statement.setInt(2, updatedPerson.getAge());
            statement.setString(3, updatedPerson.getEmail());
            statement.setInt(4, updatedPerson.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement statement
                    = connection.prepareStatement("DELETE FROM first_db.person WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
