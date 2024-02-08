package com.dlatfullin.project.dao;

import com.dlatfullin.project.models.Book;
import com.dlatfullin.project.models.Person;
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
        return jdbcTemplate.query("SELECT * FROM Book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public Optional<Book> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE book_id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
    }

    public void create(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES (?, ?, ?)",
                book.getName(), book.getAuthor(), book.getYear());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE book_id = ?",
                id);
    }

    public void update(int id, Book book) {
        jdbcTemplate.update("UPDATE Book SET name = ?, author = ?, year = ? WHERE book_id = ?",
                book.getName(), book.getAuthor(), book.getYear(), id);
    }

    public Optional<Person> getBookOwner(int id) {
       return jdbcTemplate.query("SELECT * FROM Person p1 JOIN Book b1 on p1.person_id = b1.person_id WHERE book_id = ?",
               new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public void updateOwner(int id, Person person) {
        jdbcTemplate.update("UPDATE Book SET person_id = ? WHERE book_id = ?",
                person.getPerson_id(), id);
    }

    public void release(int id) {
        jdbcTemplate.update("UPDATE Book SET person_id = NULL WHERE book_id = ?", id);
    }



}
