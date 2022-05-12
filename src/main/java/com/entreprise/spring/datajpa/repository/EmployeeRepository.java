package com.entreprise.spring.datajpa.repository;

import com.entreprise.spring.datajpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.List;


public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    List<Employee>  findByDepartementId(Long postId);
   //  List<Employee> findByMatricule(Integer matricule);
  //   List<Employee> findByNomPrenom(String nom,String prenom,Integer DateNaissance);

    @Transactional
    void deleteByDepartementId(long DepartementId);
}

