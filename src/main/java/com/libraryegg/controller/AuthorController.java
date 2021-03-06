package com.libraryegg.controller;

import com.libraryegg.entity.Author;
import com.libraryegg.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    AuthorService authorService;

    @GetMapping("/trial")
    public String trialGet(){
        return "Por aquí andamos probando 1 2 3";
    }

    @GetMapping("/get-all-void")
    public void getAuthorsVoid(){
        authorService.findAuthors();
    }

    @GetMapping("/add")
    public ModelAndView addAuthor(){
        ModelAndView modelAndView = new ModelAndView("author-form");
        modelAndView.addObject("author",new Author());
        modelAndView.addObject("title","Add Author");
        modelAndView.addObject("action","save");
        return modelAndView;
    }

    @PostMapping("/save")
    public RedirectView save(@RequestParam String name){
        authorService.createAuthor(name); //falta el id
        return new RedirectView("/author/get-all");
    }

    @GetMapping("/get-all")
    public ModelAndView getAuthors() {
        ModelAndView modelAndView = new ModelAndView("author");
        modelAndView.addObject("authors", authorService.findAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public RedirectView edit(@RequestParam Integer id, @RequestParam String name){
        //authorService.editName(authorService.findAuthor(id), name);
        authorService.edit(id,name);
        return new RedirectView("/author/get-all");
    }

    @GetMapping("/edit/{id}")
public ModelAndView editAuthor(@PathVariable Integer id){
        ModelAndView modelAndView = new ModelAndView("author-form");
        modelAndView.addObject("author",authorService.findAuthor(id));
        modelAndView.addObject("title","Edit Author");
        modelAndView.addObject("action","edit");
        return modelAndView;
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id){
authorService.delete(id);
        return new RedirectView("/author/get-all");
    }

    @PostMapping("/deActivate/{id}")
    public RedirectView deActivate(@PathVariable Integer id){
        authorService.deActivate(id);
        return new RedirectView("/author/get-all");
    }

}
