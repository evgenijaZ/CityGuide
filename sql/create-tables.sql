SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema attractions
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema attractions
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `attractions` DEFAULT CHARACTER SET utf8 ;
USE `attractions` ;

-- -----------------------------------------------------
-- Table `attractions`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `attractions`.`category` (
  `c_id` INT(10) NOT NULL,
  `c_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`c_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `attractions`.`place`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `attractions`.`place` (
  `p_id` INT(10) NOT NULL AUTO_INCREMENT,
  `p_name` VARCHAR(60) NOT NULL,
  `p_address` VARCHAR(120) NOT NULL,
  `p_lat` FLOAT(10,6) NOT NULL,
  `p_lng` FLOAT(10,6) NOT NULL,
  `p_rating` FLOAT(3,1) NULL DEFAULT NULL,
  PRIMARY KEY (`p_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `attractions`.`m2m_place_category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `attractions`.`m2m_place_category` (
  `place_id` INT(10) NULL DEFAULT NULL,
  `category_id` INT(10) NULL DEFAULT NULL,
  INDEX `m2m_p_c_category` (`category_id` ASC),
  INDEX `m2m_p_c_place` (`place_id` ASC),
  CONSTRAINT `m2m_p_c_category`
  FOREIGN KEY (`category_id`)
  REFERENCES `attractions`.`category` (`c_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `m2m_p_c_place`
  FOREIGN KEY (`place_id`)
  REFERENCES `attractions`.`place` (`p_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `attractions`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `attractions`.`user` (
  `u_id` INT(10) NOT NULL AUTO_INCREMENT,
  `u_username` VARCHAR(16) NOT NULL,
  `u_email` VARCHAR(255) NOT NULL,
  `u_password` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`u_id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `attractions`.`rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `attractions`.`rating` (
  `r_value` FLOAT(5,1) NULL DEFAULT NULL,
  `r_id` INT(11) NOT NULL,
  `r_place_id` INT(11) NULL DEFAULT NULL,
  `r_user_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`r_id`),
  INDEX `rating_place_fk` (`r_place_id` ASC),
  INDEX `rating_user_fk` (`r_user_id` ASC),
  CONSTRAINT `rating_place_fk`
  FOREIGN KEY (`r_place_id`)
  REFERENCES `attractions`.`place` (`p_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rating_user_fk`
  FOREIGN KEY (`r_user_id`)
  REFERENCES `attractions`.`user` (`u_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
