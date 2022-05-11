package com.entreprise.spring.datajpa.api;


import java.util.List;

import com.entreprise.spring.datajpa.model.Departement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entreprise.spring.datajpa.exception.RessourceNotFoundException;
import com.entreprise.spring.datajpa.model.Employee;
import com.entreprise.spring.datajpa.repository.EmployeeRepository;
import com.entreprise.spring.datajpa.repository.DepartementRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class EmployeeController {
    @Autowired
    private DepartementRepository departementRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/departement/{departementId}/employee")
    public ResponseEntity<List<Employee>> getAllEmployeesByDepartementId(@PathVariable(value = "departementId") Long departementId) {
        if (!departementRepository.existsById(departementId)) {
            throw new RessourceNotFoundException("Not found Departement with id = " + departementId);
        }
        List<Employee> employees = employeeRepository.findByDepId(departementId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/employee/{id}")
    public ResponseEntity<Employee> getEmployeesByDepartementId(@PathVariable(value = "id") Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Not found Employee with id = " + id));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/departement/{departementId}/employee")
    public ResponseEntity<Employee> createEmployee(@PathVariable(value = "departementId") Long departementId,
                                                 @RequestBody Employee employeeRequest)
    {
        Employee employee = departementRepository.findById(departementId).map(departement -> {
            employeeRequest.setDepartement(departement);
            return employeeRepository.save(employeeRequest);
        }).orElseThrow(() -> new RessourceNotFoundException("Not found Departement with id = " + departementId));
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    @PutMapping("/employee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long id, @RequestBody Employee employeeRequest) {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new RessourceNotFoundException("EmployeeId " + id + "not found"));
        employee.setNom(employeeRequest.getNom());
        employee.setPrenom(employeeRequest.getPrenom());
        employee.setMatricule(employeeRequest.getMatricule());
        employee.setMatricule(employeeRequest.getMatricule());
        employee.setDateNaissance(employeeRequest.getDateNaissance());
        employee.setDateEmbauche(employeeRequest.getDateEmbauche());
        employee.setFonction(employeeRequest.getFonction());
        employee.setSalaire(employeeRequest.getSalaire());
        employee.setEmail(employeeRequest.getEmail());
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.OK);
    }
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/departement/{departementId}/employee")
    public ResponseEntity<List<Employee>> deleteAllEmployeesOfDepartement(@PathVariable(value = "departementId") Long departementId) {
        if (!employeeRepository.existsById(departementId)) {
            throw new RessourceNotFoundException("Not found Tutorial with id = " + departementId);
        }
        employeeRepository.deleteByDepartementId(departementId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
