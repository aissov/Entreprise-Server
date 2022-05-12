CREATE TABLE IF NOT EXISTS `Entreprise`.`Projet` (
  `id` INT NOT NULL,
  `Code` VARCHAR(45) NOT NULL,
  `Intitulé` VARCHAR(45) NOT NULL,
  `Description` VARCHAR(45) NULL,
  `Budget` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `Code_UNIQUE` (`Code` ASC) VISIBLE)
ENGINE = InnoDB