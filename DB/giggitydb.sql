-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema giggity
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `giggity` ;

-- -----------------------------------------------------
-- Schema giggity
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `giggity` DEFAULT CHARACTER SET utf8 ;
USE `giggity` ;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `zip` INT(5) NULL,
  `street` VARCHAR(245) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `image` ;

CREATE TABLE IF NOT EXISTS `image` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image_url` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(100) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `enabled` TINYINT(1) NOT NULL,
  `role` VARCHAR(20) NULL,
  `phone` INT NULL,
  `address_id` INT NULL,
  `image_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_id_idx` (`address_id` ASC),
  INDEX `fk_user_image_avatar_idx` (`image_id` ASC),
  CONSTRAINT `fk_address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_image_avatar`
    FOREIGN KEY (`image_id`)
    REFERENCES `image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill` ;

CREATE TABLE IF NOT EXISTS `skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `description` TEXT(1000) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_skill`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_skill` ;

CREATE TABLE IF NOT EXISTS `user_skill` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  `description` TEXT NULL,
  `logo_image_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `user_id_idx` (`user_id` ASC),
  INDEX `fk_skill_id_idx` (`skill_id` ASC),
  INDEX `fk_logo_image_idx` (`logo_image_id` ASC),
  CONSTRAINT `fk_skill_id`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_logo_image`
    FOREIGN KEY (`logo_image_id`)
    REFERENCES `image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job` ;

CREATE TABLE IF NOT EXISTS `job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `skill_id` INT NULL,
  `price` INT NULL,
  `description` TEXT NULL,
  `title` VARCHAR(45) NOT NULL,
  `active` TINYINT(1) NOT NULL,
  `address_id` INT NULL,
  `remote` TINYINT(1) NULL,
  `image_url` TEXT NULL,
  `date_created` DATETIME NULL,
  `date_updated` DATETIME NULL,
  `user_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_id_idx` (`address_id` ASC),
  INDEX `fk_user_id_idx` (`user_id` ASC),
  INDEX `fk_skill_id_idx` (`skill_id` ASC),
  CONSTRAINT `fk_address_id_job`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_id_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_skill_id_skill`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `booking` ;

CREATE TABLE IF NOT EXISTS `booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `seller_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  `start_date` DATE NULL,
  `complete_date` DATE NULL,
  `expected_complete_date` DATE NULL,
  `notes` TEXT NULL,
  `accepted` TINYINT(0) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_seller_id_idx` (`seller_id` ASC),
  INDEX `fk_job_id_idx` (`job_id` ASC),
  CONSTRAINT `fk_seller_id`
    FOREIGN KEY (`seller_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_id`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `seller_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `seller_review` ;

CREATE TABLE IF NOT EXISTS `seller_review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NULL,
  `rating` INT(1) NOT NULL,
  `booking_id` INT NOT NULL,
  `review_date` DATETIME NULL,
  PRIMARY KEY (`id`, `booking_id`),
  INDEX `fk_booking_id_review_idx` (`booking_id` ASC),
  CONSTRAINT `fk_booking_id_review`
    FOREIGN KEY (`booking_id`)
    REFERENCES `booking` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `skill_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `skill_message` ;

CREATE TABLE IF NOT EXISTS `skill_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `poster_id` INT NOT NULL,
  `skill_id` INT NOT NULL,
  `message` TEXT NULL,
  `message_date` DATETIME NULL,
  `in_reply_to` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_skill_id_idx` (`skill_id` ASC),
  INDEX `fk_poster_id_idx` (`poster_id` ASC),
  INDEX `fk_in_reply_to_id_idx` (`in_reply_to` ASC),
  CONSTRAINT `fk_poster_id`
    FOREIGN KEY (`poster_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_skill_id_messgae`
    FOREIGN KEY (`skill_id`)
    REFERENCES `skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_in_reply_to_id`
    FOREIGN KEY (`in_reply_to`)
    REFERENCES `skill_message` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_image` ;

CREATE TABLE IF NOT EXISTS `job_image` (
  `image_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  PRIMARY KEY (`image_id`, `job_id`),
  INDEX `fk_job_id_idx` (`job_id` ASC),
  CONSTRAINT `fk_image_id_image`
    FOREIGN KEY (`image_id`)
    REFERENCES `image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_image_job`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user_skill_image`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_skill_image` ;

CREATE TABLE IF NOT EXISTS `user_skill_image` (
  `user_skill_id` INT NOT NULL,
  `image_id` INT NOT NULL,
  PRIMARY KEY (`user_skill_id`, `image_id`),
  INDEX `fk_user_skill_has_image_image1_idx` (`image_id` ASC),
  INDEX `fk_user_skill_has_image_user_skill1_idx` (`user_skill_id` ASC),
  CONSTRAINT `fk_user_skill_has_image_user_skill1`
    FOREIGN KEY (`user_skill_id`)
    REFERENCES `user_skill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_skill_has_image_image1`
    FOREIGN KEY (`image_id`)
    REFERENCES `image` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bid` ;

CREATE TABLE IF NOT EXISTS `bid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `available` VARCHAR(255) NULL,
  `bid_amount` DECIMAL(8,2) NULL,
  `description` TEXT NULL,
  `bidder_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_id_job_idx` (`job_id` ASC),
  INDEX `fk_bidder_id_user_idx` (`bidder_id` ASC),
  CONSTRAINT `fk_bidder_id_user`
    FOREIGN KEY (`bidder_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_id_job`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `booking_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `booking_message` ;

CREATE TABLE IF NOT EXISTS `booking_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `booking_id` INT NOT NULL,
  `message` TEXT NULL,
  `message_date` DATETIME NULL,
  `seller_id` INT NULL,
  `buyer_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_seller_id_booking_message_idx` (`seller_id` ASC),
  INDEX `fk_buyer_id_job_idx` (`buyer_id` ASC),
  CONSTRAINT `fk_seller_id_booking_message`
    FOREIGN KEY (`seller_id`)
    REFERENCES `booking` (`seller_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_buyer_id_job`
    FOREIGN KEY (`buyer_id`)
    REFERENCES `job` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `buyer_review`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `buyer_review` ;

CREATE TABLE IF NOT EXISTS `buyer_review` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NULL,
  `rating` INT(1) NULL,
  `job_id` INT NULL,
  `review_date` DATETIME NULL,
  INDEX `fk_job_id_review_idx` (`job_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_job_id_review`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS admin@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'admin'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`) VALUES (1, 'test', 'test', 'test', 'test@test.com', 'test', 1, 'admin', NULL, NULL, NULL);

COMMIT;

