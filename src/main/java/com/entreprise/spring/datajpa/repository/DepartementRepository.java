package com.entreprise.spring.datajpa.repository;


import java.util.List;

import com.entreprise.spring.datajpa.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

    List<Departement> findByDepartementId(Long postId);
    List<Departement> findByDepartementName(String name);

}
