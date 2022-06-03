package com.entreprise.spring.datajpa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;



@Entity
@Table(name = "projet")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Projet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="Intitule")
    private String intitule;
    @Column(name="Code" , unique=true)
    private String code;
    @Column(name="DateCreation")
    private LocalDate dateCreaction;
    @Column(name="TimeLimit")
    private LocalDate timeLimit;
    @Column(name="Sujet")
    private String sujet;
    @Column(name="Budget")
    private Integer budget;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departement_id", nullable = false)
    private Departement departement;


    @ManyToMany(
            mappedBy = "projets",
            fetch = FetchType.LAZY,
            cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE
                      }
                )
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonBackReference
    private Set<Employee> employee=new HashSet<>();


    public LocalDate getDateCreaction() {
        return dateCreaction;
    }

    public void setDateCreaction(LocalDate dateCreaction) {
        this.dateCreaction = dateCreaction;
    }

    public LocalDate getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(LocalDate timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public Set<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(Set<Employee> employee) {
        this.employee = employee;
    }

    public Projet() {
    }

    public Projet(String intitule, String code, LocalDate dateCreaction, LocalDate timeLimit, String sujet, Integer budget) {
        this.id = id;
        this.intitule = intitule;
        this.code = code;
        this.dateCreaction = dateCreaction;
        this.timeLimit = timeLimit;
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



    public void setEmployees(Set<Employee> employee) {
        this.employee = employee;
    }

    public Set<Employee> getEmployees() {
        return employee;
    }







}
