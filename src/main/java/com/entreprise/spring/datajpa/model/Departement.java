package com.entreprise.spring.datajpa.model;


import javax.persistence.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "departement")

public class Departement {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departement_generator")
    private Long id;
    @Column(name="Nom")
    private String nom;
    @Column(name="Description")
    private String description;
    @Column(name="Chef du Departement")
    private String chefDepartement;
    @Column(name="Localisation")
    private String localisation;
    @Column(name="Budget")
    private Integer budget;

    public Departement() {
    }

    public Departement(Long id, String nom, String description, String chefDepartement, String localisation, Integer budget) {
        this.id = id;
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
}
