package com.entreprise.spring.datajpa.repository;

import com.entreprise.spring.datajpa.model.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjetRepository extends JpaRepository<Projet, Long> {


   @Query("select p from Projet p where timeLimit<current_date()")
   List<Projet> timeOUT();

/*
   @Query("Select * from Projet where curdate()>timeOut ")
   List<Projet> projetTimeOut(LocalDate timeOut);*/

   List<Projet> findByIntitule(String name);


}