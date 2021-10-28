package com.libraryegg.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "author")
public class Author {
   @Id
   @GeneratedValue
   private Integer id;
   private String name;
   @Column(columnDefinition = "TINYINT(1)")
   private Boolean available;
   @OneToMany(mappedBy = "author")
   private List<Book> books;

    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Author(List<Book> books){
        books = new ArrayList<>();
    }

    public Author() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    } //se omite

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
