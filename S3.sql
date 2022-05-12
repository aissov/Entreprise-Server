CREATE TABLE IF NOT EXISTS `Entreprise`.`Employee` (
  `id` INT NOT NULL,
  `Nom` VARCHAR(45) NOT NULL,
  `Prenom` VARCHAR(45) NOT NULL,
  `DateNaissance` DATE NOT NULL,
  `DateEmbauche` DATE NULL,
  `Fonction` VARCHAR(45) NULL,
  `Salaire` INT NULL,
  `Matricule` INT NOT NULL,
  `Departement_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `Matricule_UNIQUE` (`Matricule` ASC) VISIBLE,
  INDEX `fk_Employee_Departement_idx` (`Departement_id` ASC) VISIBLE,
  CONSTRAINT `fk_Employee_Departement`
    FOREIGN KEY (`Departement_id`)
    REFERENCES `Entreprise`.`Departement` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB