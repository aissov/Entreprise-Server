package com.entreprise.spring.datajpa.api;

import com.entreprise.spring.datajpa.exception.RessourceNotFoundException;
import com.entreprise.spring.datajpa.model.Departement;
import com.entreprise.spring.datajpa.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;




@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/departement")
public class DepartementController {
    @Autowired
    DepartementRepository departementRepository;

    /*
    @Autowired
    public void setRepository(DepartementRepository departementRepository) {
        this.departementRepository = departementRepository;
    }


    @Override
    public void setIdentifier(Long id, Departement departement) {
        departement.setId(id);
    }*/



    @GetMapping("/departement")
    public ResponseEntity<List<Departement>> getAllDepartements(@RequestParam(required = false) String nom) {
        List<Departement> departement = new ArrayList<Departement>();
        if (nom == null)
            departement.addAll(departementRepository.findAll());
        else
            departement.addAll(departementRepository.findByNom(nom));
        if (departement.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(departement, HttpStatus.OK);
    }
    @GetMapping("/departement/{id}")
    public ResponseEntity<Departement> getDepartementById(@PathVariable("id") long id) {
        Departement departement = departementRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Not found Tutorial with id = " + id));
             return new ResponseEntity<>(departement, HttpStatus.OK);
    }
    @PostMapping("/departement")
    public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement) {
        Departement Dep= new Departement(departement.getNom(),departement.getDescription(),departement.getChefDepartement(),departement.getLocalisation(),departement.getBudget());
        Departement _departement = departementRepository.save(Dep);
        return new ResponseEntity<>(_departement, HttpStatus.CREATED);
    }
    @PutMapping("/departement/{id}")
    public ResponseEntity<Departement> updateDepartement(@PathVariable("id") long id, @RequestBody Departement departement) {
        Departement _departement = departementRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("Not found Tutorial with id = " + id));
        _departement.setNom(departement.getNom());
        _departement.setDescription(departement.getDescription());
        _departement.setChefDepartement(departement.getChefDepartement());
        _departement.setLocalisation(departement.getLocalisation());
        _departement.setBudget(departement.getBudget());
        return new ResponseEntity<>(departementRepository.save(_departement), HttpStatus.OK);

    }
    @DeleteMapping("/departement/{id}")
    public ResponseEntity<HttpStatus> deleteDepartement(@PathVariable("id") long id) {
        departementRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping("/departement")
    public ResponseEntity<HttpStatus> deleteAllDepartements() {
        departementRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
