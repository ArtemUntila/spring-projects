package me.artyom.library.dao;

import me.artyom.library.model.Book;
import me.artyom.library.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query(
                "SELECT id, title, author, year_of_publication FROM book",
                new BeanPropertyRowMapper<>(Book.class)
        );
    }

    public Book show(int id) {
        return jdbcTemplate.query(
                "SELECT id, title, author, year_of_publication FROM book WHERE id=?",
                new BeanPropertyRowMapper<>(Book.class),
                id
        ).stream().findAny().orElse(null);
    }

    public void save(Book book) {
        jdbcTemplate.update(
                "INSERT INTO book (title, author, year_of_publication) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication()
        );
    }

    public void update(int id, Book book) {
        jdbcTemplate.update(
                "UPDATE book SET title=?, author=?, year_of_publication=? WHERE id=?",
                book.getTitle(), book.getAuthor(), book.getYearOfPublication(), id
        );
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }

    public Optional<Person> getPerson(int id) {
        return jdbcTemplate.query(
                "SELECT person.* FROM person JOIN book ON (person.id = book.person_id) WHERE book.id=?",
                new BeanPropertyRowMapper<>(Person.class),
                id
        ).stream().findAny();
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=null WHERE id=?", id);
    }

    public void assign(int id, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", person.getId(), id);
    }
}
