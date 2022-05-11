package com.entreprise.spring.datajpa.repository;

import java.util.List;


import com.entreprise.spring.datajpa.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

    List<Projet> findByProjetId(Long postId);
   List<Projet> findByProjetName(String name);


}