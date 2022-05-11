package com.entreprise.spring.datajpa.api;

import com.entreprise.spring.datajpa.exception.RessourceNotFoundException;
import com.entreprise.spring.datajpa.model.Projet;
import com.entreprise.spring.datajpa.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

public class ProjetController {

@Autowired
ProjetRepository projetRepository;
@GetMapping("/projet")
public ResponseEntity<List<Projet>> getAllProjets(@RequestParam(required = false) String nom) {
        List<Projet> projet = new ArrayList<Projet>();
        if (nom == null)
            projet.addAll(projetRepository.findAll());
        else
            projet.addAll(projetRepository.findByProjetName(nom));
        if (projet.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(projet, HttpStatus.OK);
        }
@GetMapping("/departement/{id}")
public ResponseEntity<Projet> getProjetyId(@PathVariable("id") long id) {
    Projet projet = projetRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Not found Tutorial with id = " + id));
        return new ResponseEntity<>(projet, HttpStatus.OK);
        }
@PostMapping("/departement")
public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
    Projet Dep= new Projet(projet.getId(),projet.getIntitule(),projet.getCode(),projet.getSujet(),projet.getBudget());
    Projet _projet = projetRepository.save(Dep);
        return new ResponseEntity<>(_projet, HttpStatus.CREATED);
        }
@PutMapping("/departement/{id}")
public ResponseEntity<Projet> updateProjet(@PathVariable("id") long id, @RequestBody Projet projet) {
    Projet _projet = projetRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Not found Tutorial with id = " + id));
    _projet.setIntitule(projet.getIntitule());
    _projet.setCode(projet.getCode());
    _projet.setSujet(projet.getSujet());
    _projet.setBudget(projet.getBudget());
        ResponseEntity<Projet> projetResponseEntity;
    projetResponseEntity = new ResponseEntity<>(projetRepository.save(_projet), HttpStatus.OK);

        return projetResponseEntity;
        }
@DeleteMapping("/departement/{id}")
public ResponseEntity<HttpStatus> deleteProjett(@PathVariable("id") long id) {
    projetRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
@DeleteMapping("/departement")
public ResponseEntity<HttpStatus> deleteAllProjets() {
    projetRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        }