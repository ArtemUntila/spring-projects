package me.artyom.library.spring.boot.repository;

import me.artyom.library.spring.boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {

    List<Person> findByFullName(String fullName);
}
