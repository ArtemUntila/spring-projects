package me.artyom.library.jpa.util;

import me.artyom.library.jpa.service.PeopleService;
import me.artyom.library.jpa.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Person.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        Optional<Person> optionalPerson = peopleService.findByFullName(person.getFullName());

        if (optionalPerson.isPresent()) {
            if (person.getId() != optionalPerson.get().getId()) {
                errors.rejectValue("fullName", "", "Человек с таким ФИО уже существует");
            }
        }
    }
}
