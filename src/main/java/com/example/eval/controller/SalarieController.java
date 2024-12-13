package com.example.eval.controller;

import com.example.eval.dao.ConventionDao;
import com.example.eval.dao.SalarieDao;
import com.example.eval.dao.UtilisateurDao;
import com.example.eval.model.Convention;
import com.example.eval.model.Salarie;
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
public class SalarieController {

    @Autowired
    UtilisateurDao utilisateurDao;
    @Autowired
    private SalarieDao salarieDao;

    @IsEntreprise
    @PostMapping("/salarie")
    public ResponseEntity<Utilisateur> add(@RequestBody Salarie salarie) {

       salarieDao.save(salarie);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @IsEntreprise
    @DeleteMapping("/salarie/{id}")
    public ResponseEntity<Utilisateur> delete(@PathVariable Integer id) {

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
