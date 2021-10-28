package com.libraryegg.service;

import com.libraryegg.entity.Author;
import com.libraryegg.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private Author author= new Author();
    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void createAuthor(String name){
        author.setName(name);
        author.setAvailable(true);
        authorRepository.save(author);
    }

  @Transactional(readOnly = true)
    public Author findAuthor(Integer id){
        Optional<Author>authorOptional=authorRepository.findById(id);
        //return authorOptional.orElse(createAuthor("Caterina Cunsolo"));
        return authorOptional.orElse(null);
    }

/*    @Transactional
    public Author editName(Author author, String name){
        author.setName(name);
        authorRepository.save(author);
        return author;
    }Ctrl+Mayus+/*/

    @Transactional(readOnly = true)
    public List<Author> findAll(){
        return authorRepository.findAll();
    }

    @Transactional
    public void findAuthors() {
        List<Author> authors = authorRepository.findAll();
        System.out.printf("%-10s%-25s%-30s%n", "ID", "AVAILABILITY",
                "NAME");
        authors.forEach((author) -> {
            System.out.printf("%-10s%-25s%-30s%n", author.getId(), author.getAvailable(), author.getName());
        });
    }

    @Transactional
public void edit(Integer id, String name){
        authorRepository.edit(id,name);
    }

    @Transactional
    public void delete(Integer id){
        authorRepository.deleteById(id);
    }

    @Transactional
    public void deActivate(Integer id) {
        if(authorRepository.findById(id).get().getAvailable()==true){
            authorRepository.deActivate(id,false);
        } else{
            authorRepository.deActivate(id,true);
        }
    }

    @Transactional
    public List<Author> findAvailable(){
      /*  List<Author> availables = new ArrayList<>();
        for(Author author : authorRepository.findAll()){
            if(author.getAvailable()==true){
                availables.add(author);
            }
        } return availables;*/
 return authorRepository.findByAvailableTrue(); /*check*/
    }

}
