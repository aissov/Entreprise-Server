package com.entreprise.spring.datajpa.repository;


import com.entreprise.spring.datajpa.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

   //List<Departement> findByDepartementId(Long postId);


    List<Departement> findByNom(String name);

}
