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


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/projet")



public class ProjetController {

        @Autowired
        ProjetRepository projetRepository;

        @GetMapping("/test")
        public String test(){
            return "API OK";
        }

        @GetMapping("/getAll")
        public ResponseEntity<List<Projet>> getAllProjets(@RequestParam(required = false) String nom) {
                List<Projet> projet = new ArrayList<Projet>();
                if (nom == null)
                    projet.addAll(projetRepository.findAll());
                else
                    projet.addAll(projetRepository.findByIntitule(nom));
                if (projet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
                return new ResponseEntity<>(projet, HttpStatus.OK);
                }

        @GetMapping("/get/{id}")
        public ResponseEntity<Projet> getProjetyId(@PathVariable("id") long id) {
            Projet projet = projetRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Not found Tutorial with id = " + id));
                return new ResponseEntity<>(projet, HttpStatus.OK);
                }


        @GetMapping("/timeOUT")
        public ResponseEntity<List<Projet>> getAllProjets() {

            List<Projet> projet = new ArrayList<Projet>(projetRepository.timeOUT());


            return new ResponseEntity<>(projet, HttpStatus.OK);
        }

        @PostMapping("/create")
        public ResponseEntity<Projet> createProjet(@RequestBody Projet projet) {
            Projet pro= new Projet(
                    projet.getIntitule(),
                    projet.getCode(),
                    projet.getDateCreaction(),
                    projet.getTimeLimit(),
                    projet.getSujet(),
                    projet.getBudget()
                                    );
            Projet _projet = projetRepository.save(pro);
                return new ResponseEntity<>(_projet, HttpStatus.CREATED);
                }

        @PutMapping("/update/{id}")
        public ResponseEntity<Projet> updateProjet(@PathVariable("id") long id, @RequestBody Projet projet) {
            Projet _projet = projetRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Not found Tutorial with id = " + id));
            _projet.setIntitule(projet.getIntitule());
            _projet.setCode(projet.getCode());
            _projet.setSujet(projet.getSujet());
            _projet.setBudget(projet.getBudget());
            return new ResponseEntity<>(projetRepository.save(_projet), HttpStatus.OK);

        }

        @DeleteMapping("/delete/{id}")
        public ResponseEntity<HttpStatus> deleteProjett(@PathVariable("id") long id) {
            projetRepository.deleteById(id);

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }
        @DeleteMapping("/deleteAll")
        public ResponseEntity<HttpStatus> deleteAllProjets() {
            projetRepository.deleteAll();

                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                }

 }