package com.libraryegg.service;

import com.libraryegg.entity.Book;
import com.libraryegg.repository.AuthorRepository;
import com.libraryegg.repository.BookRepository;
import com.libraryegg.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private Book book = new Book();
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public Book createBook(Long isbn, String title, Integer year, Integer copies, Integer copiesLeft, Integer authorID, Integer editorialID) {
        book.setIsbn(isbn);
        book.setTitle(title);
        book.setYear(year);
        book.setCopies(copies);
        book.setCopiesLoaned(0);
        book.setCopiesLeft(copiesLeft);
        book.setAvailable(true);
        book.setAuthor(authorRepository.findById(authorID).orElse(null));
        //book.setEditorial(editorialService.findEditorial(editorialID));
        book.setEditorial(editorialRepository.findById(editorialID).orElse(null));
        bookRepository.save(book);
        return book;
    }

    @Transactional(readOnly = true)
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findBook(Integer id){
        Optional<Book>bookOptional=bookRepository.findById(id); //idk por que aca no me toma el integer y en otros Service, si
        return bookOptional.orElse(null);
    }

@Transactional
    public void delete(Integer id){bookRepository.deleteById(id);}

    @Transactional
    public void edit(Integer id, String title, Integer year, Integer copiesLeft, Integer authorID, Integer editorialID){
        Book book = findBook(id);
        book.setTitle(title);
        book.setYear(year);
        book.setCopiesLeft(copiesLeft);
        book.setAuthor(authorRepository.findById(authorID).orElse(null));
        book.setEditorial(editorialRepository.findById(editorialID).orElse(null));
        bookRepository.save(book);
    }

}
