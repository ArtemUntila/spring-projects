package me.artyom.library.jpa.service;

import me.artyom.library.jpa.model.Book;
import me.artyom.library.jpa.model.Person;
import me.artyom.library.jpa.repository.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void deleteById(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksById(int id) {
        Optional<Person> optionalPerson = peopleRepository.findById(id);

        if (optionalPerson.isPresent()) {
            List<Book> books = optionalPerson.get().getBooks();
            // Adding books to persistence context manually because of @OneToMany Lazy Initialization
            Hibernate.initialize(books);

            return books;
        } else {
            return null;
        }
    }

    // Validation

    public Optional<Person> findByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName).stream().findAny();
    }
}
