package com.entreprise.spring.datajpa.api;


import com.entreprise.spring.datajpa.exception.RessourceNotFoundException;
import com.entreprise.spring.datajpa.model.Employee;
import com.entreprise.spring.datajpa.repository.DepartementRepository;
import com.entreprise.spring.datajpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/employee")

public class EmployeeController {

    @Autowired
    private DepartementRepository departementRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/departement/{departementId}/getAll")
    public ResponseEntity<List<Employee>> getAllEmployeesByDepartementId(@PathVariable(value = "departementId") Long departementId) {
        if (!departementRepository.existsById(departementId)) {
            throw new RessourceNotFoundException("Not found Departement with id = " + departementId);
        }
        List<Employee> employees = employeeRepository.findByDepartementId(departementId);
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Employee> getEmployeesByDepartementId(@PathVariable(value = "id") Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RessourceNotFoundException("Not found Employee with id = " + id));
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
    @PostMapping("/departement/{departementId}/create")
    public ResponseEntity<Employee> createEmployee(@PathVariable(value = "departementId") Long departementId,
                                                 @RequestBody Employee employeeRequest)
    {
        Employee employee = departementRepository.findById(departementId).map(departement -> {
            employeeRequest.setDepartement(departement);
            return employeeRepository.save(employeeRequest);
        }).orElseThrow(() -> new RessourceNotFoundException("Not found Departement with id = " + departementId));
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
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
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") long id) {
        employeeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteDepartement/{departementId}/deleteAll")
    public ResponseEntity<List<Employee>> deleteAllEmployeesOfDepartement(@PathVariable(value = "departementId") Long departementId) {
        if (!employeeRepository.existsById(departementId)) {
            throw new RessourceNotFoundException("Not found Tutorial with id = " + departementId);
        }
        employeeRepository.deleteByDepartementId(departementId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
