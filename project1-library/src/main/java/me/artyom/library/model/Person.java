package me.artyom.library.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {

    private int id;

    @NotEmpty(message = "Пустое имя")
    @Pattern(regexp = "([А-Я][а-я]+) ([А-Я][а-я]+) ([А-Я][а-я]+)?", message = "Формат ФИО: Фамилия Имя [Отчество]")
    private String fullName;

    @Min(value = 1900, message = "Год рождения не может быть меньше 1900")
    @Max(value = 2011, message = "Год рождения не должен превышать 2011")
    private int yearOfBirth;

    public Person() {
    }

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    @Override
    public String toString() {
        return fullName + ", " + yearOfBirth;
    }
}
