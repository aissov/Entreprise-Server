CREATE TABLE IF NOT EXISTS `Entreprise`.`Projet_has_Employee` (
  `Projet_id` INT NOT NULL,
  `Employee_id` INT NOT NULL,
  PRIMARY KEY (`Projet_id`, `Employee_id`),
  INDEX `fk_Projet_has_Employee_Employee1_idx` (`Employee_id` ASC) VISIBLE,
  INDEX `fk_Projet_has_Employee_Projet1_idx` (`Projet_id` ASC) VISIBLE,
  CONSTRAINT `fk_Projet_has_Employee_Projet1`
    FOREIGN KEY (`Projet_id`)
    REFERENCES `Entreprise`.`Projet` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Projet_has_Employee_Employee1`
    FOREIGN KEY (`Employee_id`)
    REFERENCES `Entreprise`.`Employee` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB