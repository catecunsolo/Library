package com.libraryegg.service;

import com.libraryegg.entity.Author;
import com.libraryegg.entity.Editorial;
import com.libraryegg.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {
    private Editorial editorial = new Editorial();
    @Autowired
    private EditorialRepository editorialRepository;

    @Transactional
    public Editorial createEditorial(String name){
        editorial.setName(name);
        editorial.setAvailable(true);
        editorialRepository.save(editorial);
        return editorial;
    }

    @Transactional
    public Editorial findEditorial(Integer id){
        Optional<Editorial> editorialOptional=editorialRepository.findById(id);
        // return editorialOptional.orElse(createEditorial("Caterina Cunsolo"));
        return editorialOptional.orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Editorial> findAll(){return editorialRepository.findAll();}

    @Transactional
public void edit(Integer id,String name){editorialRepository.edit(id,name);}

    @Transactional
    public void delete(Integer id){editorialRepository.deleteById(id);}

}


