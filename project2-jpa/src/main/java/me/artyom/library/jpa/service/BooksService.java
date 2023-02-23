package me.artyom.library.jpa.service;

import me.artyom.library.jpa.model.Book;
import me.artyom.library.jpa.model.Person;
import me.artyom.library.jpa.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAllSortBy(String propertyName) {
        return booksRepository.findAll(Sort.by(propertyName));
    }

    public List<Book> findAllPage(int page, int size) {
        return booksRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<Book> findAllPageSortBy(int page, int size, String propertyName) {
        return booksRepository.findAll(PageRequest.of(page, size, Sort.by(propertyName))).getContent();
    }

    public Book findById(int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);

        return optionalBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    public Person getOwnerById(int id) {
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void releaseById(int id) {
        findById(id).setOwner(null);
    }

    @Transactional
    public void assignById(int id, Person person) {
        findById(id).setOwner(person);
    }
}
