package com.libraryegg.repository;

import com.libraryegg.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    //en vez de hacer UPDATE hacerlo desde el service con getters y setters

/*
    @Modifying
    @Query("UPDATE Book b SET b.title = :title WHERE b.isbn = :isbn")
    void edit(@Param("isbn") Long isbn, @Param("title") String title, @Param("author"));*/

}
