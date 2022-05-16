package com.entreprise.spring.datajpa.repository;


import com.entreprise.spring.datajpa.model.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartementRepository extends JpaRepository<Departement, Long> {

   //List<Departement> findByDepartementId(Long postId);

//and d.budget<(Select sum(p.budget) from Projet p where p.departement_id=o.id/ where d.id=p.departement_id )
    //where budget< (Select SUM(budget) from Projet departement.departement_id=d.id)
    @Query("select d from Departement d where budget< (select SUM(budget) from Projet where departement_id=d.id) ")
    List<Departement> BudgetDefficit();

    List<Departement> findByNom(String name);


}
