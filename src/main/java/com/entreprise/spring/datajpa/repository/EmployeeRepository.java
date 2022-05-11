package com.entreprise.spring.datajpa.repository;

import java.util.List;

import com.entreprise.spring.datajpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{
    List<Employee>  findByDepId(Long postId);
  //  List<Employee> findByMatricule(Integer matricule);
 //   List<Employee> findByNomPrenom(String nom,String prenom,Integer DateNaissance);

    @Transactional
    void deleteByDepartementId(long DepartementId);
}

