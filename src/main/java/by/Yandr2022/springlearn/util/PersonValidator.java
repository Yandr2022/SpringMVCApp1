package by.Yandr2022.springlearn.util;

import by.Yandr2022.springlearn.dao.PersonDAO;
import by.Yandr2022.springlearn.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (personDAO.show(((Person) target).getEmail()).isPresent()) {
            errors.rejectValue("email", "", "This email is already taken");
        }

    }
}
