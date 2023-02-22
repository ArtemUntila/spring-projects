package me.artyom.library.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {

    private int id;

    @NotEmpty(message = "Название книги не может быть пустым")
    private String title;

    @NotEmpty(message = "Укажите автора книги")
    private String author;

    @Min(value = 1800, message = "Библиотека на хранит книги, опубликованные раньше 1800 года")
    @Max(value = 2023, message = "Книга из будущего?")
    private int yearOfPublication;

    public Book() {
    }

    public Book(int id, String title, String author, int yearOfPublication) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d", title, author, yearOfPublication);
    }
}
