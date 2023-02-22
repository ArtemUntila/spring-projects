package me.artyom.library.util;

import me.artyom.library.dao.PersonDAO;
import me.artyom.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> optionalPerson = personDAO.getPersonByFullName(person.getFullName());

        if (optionalPerson.isPresent()) {
            if (person.getId() != optionalPerson.get().getId()) {
                errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
            }
        }
    }
}
