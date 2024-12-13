package com.example.eval.controller;

import com.example.eval.dao.ConventionDao;
import com.example.eval.dao.SalarieDao;
import com.example.eval.dao.SalarieDao;
import com.example.eval.model.Convention;
import com.example.eval.model.Salarie;
import com.example.eval.model.Salarie;
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
public class SalarieController {

    @Autowired
    SalarieDao salarieDao;
    @Autowired
    private ConventionDao conventionDao;


    @IsEntreprise
    @GetMapping("/salaries")
    public List<Salarie> getAll() {

        return salarieDao.findAll();

    }
    

    @IsEntreprise
    @GetMapping("/salarie/{id}")
    public ResponseEntity<Salarie> get(@PathVariable Integer id) {

        //On vérifie que l'salarie existe bien dans la base de donnée
        Optional<Salarie> optionalSalarie = salarieDao.findById(id);

        //si l'salarie n'existe pas
        if(optionalSalarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalSalarie.get(),HttpStatus.OK);
    }

    @IsEntreprise
    @PostMapping("/salarie")
    public ResponseEntity<?> add(@RequestBody Salarie salarie) {
        salarieDao.save(salarie);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @IsEntreprise
    @DeleteMapping("/salarie/{id}")
    public ResponseEntity<Salarie> delete(@PathVariable Integer id) {

        salarieDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @IsEntreprise
    @PutMapping("/salarie/{id}")
    public ResponseEntity<Salarie> update(
            @RequestBody @Valid Salarie salarieEnvoye, @PathVariable Integer id) {

        salarieEnvoye.setId(id);
        Optional<Salarie> optionalSalarie =salarieDao.findById(id);

        if(optionalSalarie.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        salarieDao.save(salarieEnvoye);

        return new ResponseEntity<>(optionalSalarie.get(), HttpStatus.OK);
    }

}
