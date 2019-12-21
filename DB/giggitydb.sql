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
  `enabled` TINYINT NOT NULL,
  `role` VARCHAR(20) NULL,
  `phone` VARCHAR(100) NULL,
  `address_id` INT NULL,
  `image_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_id_idx` (`address_id` ASC),
  INDEX `fk_user_image1_idx` (`image_id` ASC),
  CONSTRAINT `fk_address_id`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_image1`
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
  `price` DECIMAL(7,2) NULL,
  `description` TEXT NULL,
  `title` VARCHAR(4345) NOT NULL,
  `active` TINYINT NOT NULL,
  `address_id` INT NULL,
  `remote` TINYINT NULL,
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
  `accepted` TINYINT NOT NULL,
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
  `available` TINYINT NULL,
  `bid_amount` DECIMAL(8,2) NULL,
  `description` TEXT NULL,
  `bidder_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  `accepted` TINYINT NULL,
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
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `address` (`id`, `city`, `state`, `zip`, `street`) VALUES (1, 'Denver', 'Colorado', 80210, '1234 Elm Street');
INSERT INTO `address` (`id`, `city`, `state`, `zip`, `street`) VALUES (2, 'Colorado Springs', 'Colorado', 80925, '9319 Daystar Terrace');

COMMIT;


-- -----------------------------------------------------
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `image` (`id`, `image_url`) VALUES (1, 'https://cdn.vox-cdn.com/thumbor/_AobZZDt_RVStktVR7mUZpBkovc=/0x0:640x427/1200x800/filters:focal(0x0:640x427)/cdn.vox-cdn.com/assets/1087137/java_logo_640.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (2, 'https://fiverr-res.cloudinary.com/t_gig_cards_web_x2,q_auto,f_auto/gigs/105561645/original/c935e9736603cc12ba0966219d52f16803020397.png');
INSERT INTO `image` (`id`, `image_url`) VALUES (3, 'https://www.dhresource.com/600x600/f2/albu/g8/M00/33/15/rBVaVFw21liALA8iAADH3G8Xu58683.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (4, 'https://www.rd.com/wp-content/uploads/2018/02/10_Things-Your-Car-Mechanic-Won%E2%80%99t-Tell-You_572422051_Minerva-Studio.jpg');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`) VALUES (1, 'admin', 'admin', 'admin', 'admin@admin.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'admin', '111111111', 1, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`) VALUES (2, 'Kelly', 'Cromeans', 'kvothik', 'cromeans15@gmail.com', '$2a$10$1SSwukC6.gWafhjs52eOUuFFxxXIOSRYwgv4z19EmJEE9UFq0WF9O', 1, 'user', '7196662811', 2, 1);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`) VALUES (3, 'Alex', 'Sandervladi', 'aleksandervladialeksandervladi\naleksandervladi\naleksandervladi\naleksandervladi', 'aleksand@yahoo.com', '12345', 1, 'user', NULL, NULL, 2);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`) VALUES (4, 'Kullen', 'Kee', 'Kkee', 'kullenk@outlook.com', 'kulleniscool', 1, 'user', NULL, NULL, 3);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`) VALUES (5, 'Shaun', 'Roach', 'Sroach', 'sroach@AOL.com', 'ifixstuff', 1, 'user', NULL, 2, 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (1, 'Software Development', 'Conceiving, specifying, designing, programming, documenting, testing, and bug fixing involved in creating and maintaining applications, frameworks, or other software components.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (2, 'Mechanic', 'Inspecting and repairing vehicles, machinery, and light trucks.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (3, 'Marketing', 'Create advertising campaigns, develop pricing strategies and targeting strategies based on demographic data and work with the you to develop more awareness of what you offer.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (4, 'Art', 'Logo design, blue print creation, artistic visual repusentations.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (5, 'Homework & Tutoring', ' Assist, write, or review research papers. Help students learn, reviewing content with them, explaining how to solve problems and checking completed work. A tutor may also help students develop study skills and organization techniques to help improve their academic performance.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (6, 'Landscaping', 'Perform groundskeeping and building maintenance duties. Mow lawn either by hand or using a riding lawnmower. Cut lawn using hand, power or riding mower and trim and edge around walks, flower beds, and walls. Landscape by planting flowers, grass, shrubs, and bushes.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (7, 'Writing & Translation', 'Interprets written or spoken material into one or more other languages, ensures meaning and context are maintained, creates glossaries or term dictionaries, possesses knowledge of multiple languages, works with individual clients and corporations.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (8, 'Home Improvment', 'Electrical, Plumbing, Roofing, Flooring, Drywall, Framing, Masonry, ect...');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`) VALUES (1, 1, 1, 'I can do full stack development with dynamic Spring/Angular UIs.', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `user_id`) VALUES (1, 1, 47.15, 'Troubleshoot issues with Spring-based applications (Spring Boot, Spring Security, Spring Cloud, etc). Provide suggestions for improvements and enhancements via code reviews, and implementation and application of bug fixes that are identified.', 'I will assist and troubleshoot spring framework applications.', 1, 1, 1, 'data:image/png;base64,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', '2019-04-20 16:20:11', '2019-12-10 15:24:11', 1);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `user_id`) VALUES (2, 7, 5.00, 'Hi, My name is Alex. I offer top quality English to French and French to English Contextual and Localization Translation of Technical, Technological or Legal text. I will check the work for the slightest flaw.\nNB: \nThis pricing will vary according to the working condition, complexity and type of content\nFor additional words, the delivery time would be increased depending on the number of words, so do contact me first.\nFor extra fast delivery of more than 5000 words contact me first.\n\nI can translate contents such as: \nliterature,\npoetry,\nstories,\narticles,\nlegal or\ntechnical\n \nMy commitment: \nAll translations are done manually and proofread. \nI DO NOT use Google Translator, or any other translation services.\nMy translations are literal, not word to word.\nOriginal sense is maintained\nAll the information you provide are kept strictly confidential!', 'I will perfect french to english or english to french translation of any technical text', 1, 1, 1, 'https://fiverr-res.cloudinary.com/t_gig_cards_web_x2,q_auto,f_auto/gigs/105561645/original/c935e9736603cc12ba0966219d52f16803020397.png', '2019-12-07 23:59:59', '2019-12-07 23:59:59', 3);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `user_id`) VALUES (3, 4, 113.50, '★★★ 1000+ orders successfully completed in total ★★★\n\n★★★ Professional Service since 2014 ★★★\n\n\n\nNOTE: if you\'re interested only in Logo Design or only in Social Media Design - just send me a message and i\'ll provide you custom offer for separate service.\n\n\nLOGO DESIGN\n\nOur studio provide high-quality UNIQUE logo designs. We\'re very attentive to details, so you can be sure we\'ll hear all your wishes according to your task.', 'Our Studio Will make professional logo design', 1, NULL, 1, 'https://fiverr-res.cloudinary.com/images/t_main1,q_auto,f_auto/gigs/133456006/original/d40984e6bd0f4f63d050e3e9942845ea636e0a32/make-custom-logo-design-and-animation.jpg', '2019-08-16 11:15:45', '2019-12-10 15:24:11', 4);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `user_id`) VALUES (4, 2, 120.00, 'I will supply all parts and tools. Will come to you. I also offer other remote mechanic work.', 'I will change youe brakes and machine your roters.', 1, 2, 0, 'https://gandgautorepair.com/wp-content/uploads/2019/04/horror-4-555x312.jpg', '2019-04-20 16:20:11', '2019-10-10 16:20:11', 5);

COMMIT;


-- -----------------------------------------------------
-- Data for table `booking`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `booking` (`id`, `seller_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (1, 1, 1, '2019-12-07', '2019-12-18', '2019-12-18', 'test', 1);
INSERT INTO `booking` (`id`, `seller_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (2, 1, 2, '2019-12-18', NULL, '2020-01-09', 'test', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `seller_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `seller_review` (`id`, `comment`, `rating`, `booking_id`, `review_date`) VALUES (1, 'Its gonna rain', 4, 1, '2019-12-07 23:59:59');

COMMIT;


-- -----------------------------------------------------
-- Data for table `skill_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `skill_message` (`id`, `poster_id`, `skill_id`, `message`, `message_date`, `in_reply_to`) VALUES (1, 1, 1, 'test', '2019-12-18', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `job_image` (`image_id`, `job_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_skill_image`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `user_skill_image` (`user_skill_id`, `image_id`) VALUES (1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bid`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `bid` (`id`, `available`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`) VALUES (1, 1, 0, 'test', 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `booking_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `booking_message` (`id`, `booking_id`, `message`, `message_date`, `seller_id`, `buyer_id`) VALUES (1, 1, '2019-12-07 23:59:59', '2019-12-07 23:59:59', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `buyer_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `buyer_review` (`id`, `comment`, `rating`, `job_id`, `review_date`) VALUES (1, '23:59:59', 1, 1, '2019-12-07 23:59:59');

COMMIT;

