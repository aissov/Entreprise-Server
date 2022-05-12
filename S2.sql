CREATE TABLE IF NOT EXISTS `Entreprise`.`Departement` (
  `id` INT NOT NULL,
  `Nom` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NULL,
  `Localisation` VARCHAR(45) NULL,
  `Budget` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB