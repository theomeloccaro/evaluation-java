package com.example.eval.controller;

import com.example.eval.dao.ConventionDao;
import com.example.eval.dao.ConventionDao;
import com.example.eval.model.Convention;
import com.example.eval.model.Convention;
import com.example.eval.security.IsAdministrateur;
import com.example.eval.security.IsEntreprise;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class ConventionController {

    @Autowired
    ConventionDao entrepriseDao;
    @Autowired
    private ConventionDao conventionDao;
    
    
    
    @IsEntreprise
    @GetMapping("conventions")
    public List<Convention> getAll() {

        return conventionDao.findAll();

    }


    @IsEntreprise
    @GetMapping("/convention/{id}")
    public ResponseEntity<Convention> get(@PathVariable Integer id) {

        //On vérifie que l'entreprise existe bien dans la base de donnée
        Optional<Convention> optionalConvention = conventionDao.findById(id);

        //si l'entreprise n'existe pas
        if(optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalConvention.get(),HttpStatus.OK);
    }

    @IsAdministrateur
    @PostMapping("/convention")
    public ResponseEntity<Convention> add(@RequestBody Convention convention) {

       conventionDao.save(convention);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @IsAdministrateur
    @DeleteMapping("/convention/{id}")
    public ResponseEntity<Convention> delete(@PathVariable Integer id) {

        conventionDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @IsEntreprise
    @PutMapping("/convention/{id}")
    public ResponseEntity<Convention> update(
            @RequestBody @Valid Convention conventionEnvoye, @PathVariable Integer id) {

        conventionEnvoye.setId(id);

        Optional<Convention> optionalConvention = conventionDao.findById(id);

        if(optionalConvention.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        conventionDao.save(conventionEnvoye);

        return new ResponseEntity<>(optionalConvention.get(), HttpStatus.OK);
    }

}
