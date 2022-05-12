CREATE TABLE IF NOT EXISTS `Entreprise`.`Departement_has_Projet` (
  `Departement_id` INT NOT NULL,
  `Projet_id` INT NOT NULL,
  PRIMARY KEY (`Departement_id`, `Projet_id`),
  INDEX `fk_Departement_has_Projet_Projet1_idx` (`Projet_id` ASC) VISIBLE,
  INDEX `fk_Departement_has_Projet_Departement1_idx` (`Departement_id` ASC) VISIBLE,
  CONSTRAINT `fk_Departement_has_Projet_Departement1`
    FOREIGN KEY (`Departement_id`)
    REFERENCES `Entreprise`.`Departement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Departement_has_Projet_Projet1`
    FOREIGN KEY (`Projet_id`)
    REFERENCES `Entreprise`.`Projet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB