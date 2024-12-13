package com.example.eval.controller;

import com.example.eval.dao.ConventionDao;
import com.example.eval.dao.UtilisateurDao;
import com.example.eval.model.Convention;
import com.example.eval.model.Utilisateur;
import com.example.eval.security.IsAdministrateur;
import com.example.eval.security.IsEntreprise;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@CrossOrigin
@RestController
public class ConventionController {

    @Autowired
    UtilisateurDao utilisateurDao;
    @Autowired
    private ConventionDao conventionDao;

    @IsAdministrateur
    @PostMapping("/convention")
    public ResponseEntity<Utilisateur> add(@RequestBody Convention convention) {

       conventionDao.save(convention);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @IsAdministrateur
    @DeleteMapping("/convention/{id}")
    public ResponseEntity<Utilisateur> delete(@PathVariable Integer id) {

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
