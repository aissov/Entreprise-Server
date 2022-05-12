package com.entreprise.spring.datajpa.repository;

import com.entreprise.spring.datajpa.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Long> {

   List<Projet> findByIntitule(String name);


}