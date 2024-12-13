package com.example.eval.controller;

import com.example.eval.dao.EntrepriseDao;
import com.example.eval.dao.EntrepriseDao;
import com.example.eval.model.Entreprise;
import com.example.eval.model.Entreprise;
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
public class EntrepriseController {

    @Autowired
    private EntrepriseDao entrepriseDao;



    @IsAdministrateur
    @GetMapping("/entreprises")
    public List<Entreprise> getAll() {

        return entrepriseDao.findAll();

    }

    @IsAdministrateur
    @GetMapping("/entreprise/{id}")
    public ResponseEntity<Entreprise> get(@PathVariable Integer id) {

        Optional<Entreprise> optionalEntreprise = entrepriseDao.findById(id);

        if(optionalEntreprise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(optionalEntreprise.get(),HttpStatus.OK);
    }
    @IsAdministrateur
    @PostMapping("/entreprise")
    public ResponseEntity<?> add(@RequestBody Entreprise entreprise) {
        entrepriseDao.save(entreprise);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @IsAdministrateur
    @DeleteMapping("/entreprise/{id}")
    public ResponseEntity<Entreprise> delete(@PathVariable Integer id) {

        entrepriseDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @IsEntreprise
    @PutMapping("/entreprise/{id}")
    public ResponseEntity<Entreprise> update(
            @RequestBody @Valid Entreprise entrepriseEnvoye, @PathVariable Integer id) {

        entrepriseEnvoye.setId(id);
        Optional<Entreprise> optionalEntreprise =entrepriseDao.findById(id);

        if(optionalEntreprise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        entrepriseDao.save(entrepriseEnvoye);

        return new ResponseEntity<>(optionalEntreprise.get(), HttpStatus.OK);
    }

}
