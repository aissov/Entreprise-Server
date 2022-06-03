package com.entreprise.spring.datajpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "departement")

public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="Nom")
    private String nom;
    @Column(name="Description")
    private String description;
    @Column(name="Chef_du_Departement")
    private String chefDepartement;
    @Column(name="Localisation")
    private String localisation;
    @Column(name="Budget")
    private Integer budget;





    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "departement_projet",
            joinColumns = { @JoinColumn(name = "departement_id") },
            inverseJoinColumns = { @JoinColumn(name = "projet_id") })
    @JsonBackReference
    private Set<Projet> projets = new HashSet<>();


    @Column(name="SumProjetsBudget")
    @Formula("Select sum(budget) from projet inner join departement_projet dp where dp.projet_id=p.id and dp.departement_id=departement.departement_id)")
    private Integer sumProjetsBudget;


    public Departement() {
    }

    public Departement(String nom, String description, String chefDepartement, String localisation, Integer budget) {
        this.nom = nom;
        this.description = description;
        this.chefDepartement = chefDepartement;
        this.localisation = localisation;
        this.budget = budget;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChefDepartement() {
        return chefDepartement;
    }

    public void setChefDepartement(String chefDepartement) {
        this.chefDepartement = chefDepartement;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }



    public Set<Projet> getProjets() {
        return projets;
    }

    public void setProjets(Set<Projet> projets) {
        this.projets = projets;
    }

    public Integer getSumProjetsBudget() {
        return sumProjetsBudget;
    }

    public void setSumProjetsBudget(Integer sumProjetsBudget) {
        sumProjetsBudget = sumProjetsBudget;
    }
/*
    public void addProjet(Projet projet) {
        this.projets.add(projet);
        projet.getDepartement().add(this);

    public void removeProjet(long projetId) {
        Projet projet = this.projets.stream().filter(t -> t.getId() == projetId).findFirst().orElse(null);
        if (projet != null) {
            this.projets.remove(projet);
            projet.getDepartement().remove(this);
        }
    }*/
}
