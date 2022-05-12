package com.entreprise.spring.datajpa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "employee_generator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name="Nom")
    private String nom;
    @Column(name="Prenom")
    private String prenom;
    @Column(name="Matricule")
    private Integer matricule;
    @Column(name="DateNaissance")
    private Date dateNaissance;
    @Column(name="DateEmbauche")
    private Date dateEmbauche;
    @Column(name="Fonction")
    private String fonction;
    @Column(name="Salaire")
    private Integer salaire;
    @Column(name="Email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departement_id", nullable = false)
   // @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Departement departement;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "employee_projet",
            joinColumns = { @JoinColumn(name = "employee_id") },
            inverseJoinColumns = { @JoinColumn(name = "projet_id") })
    private Set<Projet> projets = new HashSet<>();



    public Employee(String nom, String prenom, Integer matricule, Date dateNaissance, Date dateEmbauche, String fonction, Integer salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.fonction = fonction;
        this.salaire = salaire;
    }

    public Employee() {

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Integer getMatricule() {
        return matricule;
    }

    public void setMatricule(Integer matricule) {
        this.matricule = matricule;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public Integer getSalaire() {
        return salaire;
    }

    public void setSalaire(Integer salaire) {
        this.salaire = salaire;
    }


    public Set<Projet> getProjet() {
        return projets;
    }

    public void setProjet(Set<Projet> projet) {
        this.projets = projet;
    }

    public void addProjet(Projet projet) {
        this.projets.add(projet);
        projet.getEmployees().add(this);
    }

    public void removeProjet(long projetId) {
        Projet projet = this.projets.stream().filter(t -> t.getId() == projetId).findFirst().orElse(null);
        if (projet != null) {
            this.projets.remove(projet);
            projet.getEmployees().remove(this);
        }
    }

}

