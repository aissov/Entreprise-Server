package com.entreprise.spring.datajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "projet")

public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="Intitule")
    private String intitule;
    @Column(name="Code" , unique=true)
    private String code;
    @Column(name="Sujet")
    private String sujet;
    @Column(name="Budget")
    private Integer budget;


    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade =   {
                            CascadeType.PERSIST,
                            CascadeType.MERGE
                        },
            mappedBy = "projets"
    )
    //@JoinColumn(name = "departement_id", nullable = false)

    @JsonIgnore
    private Set<Departement> departement=new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            mappedBy = "projets")

    //@JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore
    private Set<Employee> employee=new HashSet<>();

    public Projet() {
    }

    public Projet(Long id, String intitule, String code, String sujet, Integer budget) {
        this.id = id;
        this.intitule = intitule;
        this.code = code;
        this.sujet = sujet;
        this.budget = budget;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public void setDepartements(Set<Departement> departement) {
        this.departement = departement;
    }

    public void setEmployees(Set<Employee> employee) {
        this.employee = employee;
    }

    public Set<Departement> getDepartements() {
        return departement;
    }

    public Set<Employee> getEmployees() {
        return employee;
    }
}
