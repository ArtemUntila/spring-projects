package me.artyom.library.spring.boot.util;

import me.artyom.library.spring.boot.model.Person;
import me.artyom.library.spring.boot.service.PeopleService;
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
