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
  `bio` TEXT NULL,
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
  `price` DECIMAL(7,2) NULL,
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
  `skill_id` INT NOT NULL,
  `price` DECIMAL(7,2) NULL,
  `description` TEXT NULL,
  `title` VARCHAR(4345) NOT NULL,
  `active` TINYINT NOT NULL,
  `address_id` INT NULL,
  `remote` TINYINT NULL,
  `image_url` TEXT NULL,
  `date_created` DATETIME NULL,
  `date_updated` DATETIME NULL,
  `requestor_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_address_id_idx` (`address_id` ASC),
  INDEX `fk_user_id_idx` (`requestor_id` ASC),
  INDEX `fk_skill_id_idx` (`skill_id` ASC),
  CONSTRAINT `fk_address_id_job`
    FOREIGN KEY (`address_id`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_requestor_id_user`
    FOREIGN KEY (`requestor_id`)
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
-- Table `bid`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bid` ;

CREATE TABLE IF NOT EXISTS `bid` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bid_amount` DECIMAL(8,2) NULL,
  `description` TEXT NULL,
  `bidder_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  `accepted` TINYINT NULL,
  `rejected` TINYINT NULL,
  `available` TINYINT NULL,
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
-- Table `booking`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `booking` ;

CREATE TABLE IF NOT EXISTS `booking` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bid_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  `start_date` DATE NULL,
  `complete_date` DATE NULL,
  `expected_complete_date` DATE NULL,
  `notes` TEXT NULL,
  `accepted` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_id_idx` (`job_id` ASC),
  INDEX `fk_bid_id_idx` (`bid_id` ASC),
  CONSTRAINT `fk_bid_id`
    FOREIGN KEY (`bid_id`)
    REFERENCES `bid` (`id`)
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
-- Table `booking_message`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `booking_message` ;

CREATE TABLE IF NOT EXISTS `booking_message` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `booking_id` INT NOT NULL,
  `message` TEXT NULL,
  `message_date` DATETIME NULL,
  `bid_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_booking_booking_message_idx` (`booking_id` ASC),
  INDEX `fk_seller_id_booking_message_idx` (`bid_id` ASC),
  CONSTRAINT `fk_seller_id_booking_message`
    FOREIGN KEY (`bid_id`)
    REFERENCES `bid` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_booking_booking_message`
    FOREIGN KEY (`booking_id`)
    REFERENCES `booking` (`id`)
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
INSERT INTO `address` (`id`, `city`, `state`, `zip`, `street`) VALUES (3, 'Denver', 'Colorado', 80209, '197 Mississippi Ave');
INSERT INTO `address` (`id`, `city`, `state`, `zip`, `street`) VALUES (4, 'Greenwood Village', 'Colorado', 80111, '8505 E Arapahoe Rd');
INSERT INTO `address` (`id`, `city`, `state`, `zip`, `street`) VALUES (5, 'Centennial', 'Colorado', 80015, '5387 South Sedalia Court');
INSERT INTO `address` (`id`, `city`, `state`, `zip`, `street`) VALUES (6, 'Spring', 'Texas', 77386, '7626 Lazy Ln');

COMMIT;


-- -----------------------------------------------------
-- Data for table `image`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `image` (`id`, `image_url`) VALUES (1, 'https://cdn.vox-cdn.com/thumbor/_AobZZDt_RVStktVR7mUZpBkovc=/0x0:640x427/1200x800/filters:focal(0x0:640x427)/cdn.vox-cdn.com/assets/1087137/java_logo_640.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (2, 'https://fiverr-res.cloudinary.com/t_gig_cards_web_x2,q_auto,f_auto/gigs/105561645/original/c935e9736603cc12ba0966219d52f16803020397.png');
INSERT INTO `image` (`id`, `image_url`) VALUES (3, 'http://www.newsshare.in/wp-content/uploads/2017/04/Miniclip-8-Ball-Pool-Avatar-16-180x180.png');
INSERT INTO `image` (`id`, `image_url`) VALUES (4, 'https://www.rd.com/wp-content/uploads/2018/02/10_Things-Your-Car-Mechanic-Won%E2%80%99t-Tell-You_572422051_Minerva-Studio.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (5, 'https://i.imgur.com/bRhw6wY.jpg');
INSERT INTO `image` (`id`, `image_url`) VALUES (6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkozSjbf8nqtjhrVXl39eCmIXwjiMEBABm_bsAEgbJ4yb1EYd1Wg&s');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (1, 'admin', 'admin', 'admin', 'admin@admin.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'admin', '111111111', 1, 1, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (2, 'Kelly', 'Cromeans', 'kellyc', 'cromeans15@gmail.com', '$2a$10$1SSwukC6.gWafhjs52eOUuFFxxXIOSRYwgv4z19EmJEE9UFq0WF9O', 1, 'user', '7196662811', 2, 6, 'Always looking to make some extra cash. I can do a myraid of auto mechanic work, academic help, or write your college research papers.');
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (3, 'Alex', 'Sandervladi', 'alexs', 'aleksand@yahoo.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '3515874430', 3, 2, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (4, 'Kullen', 'Kee', 'kullenk', 'kullenk@outlook.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '5316785542', 4, 3, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (5, 'Shaun', 'Roach', 'shaunr', 'sroach@AOL.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '6678741212', 5, 4, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (6, 'Fred', 'Fredette', 'fredf', 'fredf@gmail.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '5448900871', 3, 3, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (7, 'John', 'Burgers', 'johnb', 'johnb@yahoo.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '2938747483', 6, 3, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (8, 'Caitlin', 'Gerweck', 'caitling', 'caitling@proton.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '9587756649', 4, 3, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (9, 'Pam', 'Gerweck', 'pamg', 'pamg@proton.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '7203452141', 2, 3, NULL);
INSERT INTO `user` (`id`, `first_name`, `last_name`, `username`, `email`, `password`, `enabled`, `role`, `phone`, `address_id`, `image_id`, `bio`) VALUES (10, 'Cindy', 'Holcombe', 'cindyh', 'cindyh@icloud.com', '$2a$10$KMIQ9vF8CXH7U2Nqn3wTV.dUq0EzW94gzEvT7yb6rx9fuyFyY6OSS', 1, 'user', '2818553595', 6, 3, NULL);

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
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (9, 'Photography', 'All about photos!');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (10, 'Catering', 'Food & Amenities planning for events.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (11, 'Mentoring', 'Life coaching, advice, & professional development.');
INSERT INTO `skill` (`id`, `name`, `description`) VALUES (12, 'Music', 'Utilize others musical talent!');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user_skill`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (1, 1, 1, 'I can do full stack development with dynamic Spring/Angular UIs.', 1, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (2, 3, 7, 'I can teach French.', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (3, 2, 2, 'Inspecting and repairing vehicles, machinery, and light trucks.', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (4, 4, 3, 'Create advertising campaigns, develop pricing strategies and targeting strategies based on demographic data and work with the you to develop more awareness of what you offer.', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (5, 5, 4, 'Logo design, blue print creation, artistic visual repusentations.', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (6, 6, 5, ' Assist, write, or review research papers. Help students learn, reviewing content with them, explaining how to solve problems and checking completed work. A tutor may also help students develop study skills and organization techniques to help improve their academic performance.', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (7, 7, 6, 'Perform groundskeeping and building maintenance duties. Mow lawn either by hand or using a riding lawnmower. Cut lawn using hand, power or riding mower and trim and edge around walks, flower beds, and walls. Landscape by planting flowers, grass, shrubs, and bushes.', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (8, 8, 8, 'Electrical, Plumbing, Roofing, Flooring, Drywall, Framing, Masonry, ect...', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (9, 2, 5, 'Perform groundskeeping and building maintenance duties. Mow lawn either by hand or using a riding lawnmower. Cut lawn using hand, power or riding mower and trim and edge around walks, flower beds, and walls. Landscape by planting flowers, grass, shrubs, and bushes.', NULL, NULL);
INSERT INTO `user_skill` (`id`, `user_id`, `skill_id`, `description`, `logo_image_id`, `price`) VALUES (10, 6, 8, 'Electrical, Plumbing, Roofing, Flooring, Drywall, Framing, Masonry, ect...', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (1, 1, 47.15, 'I require some code review/collaboration to work out bugs with my Spring Boot application.', 'Bug troubleshooting assistance with Spring boot application.', 1, 1, 1, 'https://www.computerhope.com/cdn/troubleshoot.jpg', '2019-11-20 16:20:11', '2019-12-10 15:24:11', 2);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (3, 4, 113.50, 'I require a new logo for my Gig-ity account.', 'I would like a professional looking logo designed.', 1, 2, 1, 'https://fiverr-res.cloudinary.com/images/t_main1,q_auto,f_auto/gigs/133456006/original/d40984e6bd0f4f63d050e3e9942845ea636e0a32/make-custom-logo-design-and-animation.jpg', '2019-08-16 11:15:45', '2019-12-10 15:24:11', 2);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (4, 2, 120.00, 'I need my brakes and oil changed on a 2011 Toyota Tundra. I have the parts.', 'Brakes and oil change.', 1, 2, 0, 'data:image/jpeg;base64,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', '2019-04-20 16:20:11', '2019-10-10 16:20:11', 7);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (2, 7, 50.00, 'Bonjour! I will be taking a trip to Paris soon and would like to brush up on my basic French skills.', 'I would like to learn french.', 1, 2, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2-cQnw0szoKgbtxQtAdDKZH_BtJbK37V_vTsH52FCHY3PUVtORQ&s', '2019-12-07 23:59:59', '2019-12-07 23:59:59', 5);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (5, 6, 20.50, 'I would like someone to come and cut my grass in the Denver area.', 'Cut my lawn', 1, 2, 0, 'https://i.ytimg.com/vi/ZMuJ9izVL_g/maxresdefault.jpg', '2019-08-23 13:29:46', NULL, 2);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (6, 6, 40.00, 'I would like a cherry tree planted in my back yard.', 'Cherry tree planting', 1, 2, 0, 'https://cdn11.bigcommerce.com/s-f74ff/images/stencil/1280x1280/products/10484/28279/download__74272.1561135665.jpg?c=2&imbypass=on', '2019-08-23 13:29:46', NULL, 2);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (7, 3, 150.00, 'Looking for someone to hang flyers around the community for my upcoming Thai resturant!', 'Local Bussiness Flyers', 1, 2, 0, 'data:image/webp;base64,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', '2019-12-23 13:29:46', NULL, 3);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (8, 1, 70.50, 'I\'m having some troubles with my angular front end on a person project. I would appriciate some code review/suggestions.', 'Angular Help', 1, 2, 1, 'data:image/png;base64,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', '2019-12-23 13:29:46', NULL, 2);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (9, 5, 50.67, 'Requesting a tutor for a calculus course.', 'Math tutor', 1, 2, 1, 'data:image/jpeg;base64,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', '2019-12-23 13:29:46', NULL, 5);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (10, 5, 65.21, 'I\'d like to find a skilled writer to write my research paper on history of basket weaving. I am going out of town for the holidays and do not have time to complete it myself.', 'Research Paper ', 1, 2, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRzdGu5lRrrRLOSnrZ9w6lmrp0_mDdmnQsq5LSaIh0zyKnyhkiw&s', '2019-12-23 13:29:46', NULL, 4);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (11, 7, 200.00, 'Looking for someone to create an impressive resume for me. I am a Avionics tech.', 'Resume writing', 1, 2, 1, 'data:image/jpeg;base64,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', '2019-12-23 13:29:46', NULL, 10);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (12, 8, 5000, 'Install hardwood floor into ground floor of my home. It is 1000sqFt', 'Hardwood Floors', 1, 2, 0, 'http://theecs.co/wp-content/uploads/2019/12/how-to-install-a-real-wood-floor-installation-of-wood-how-to-install-a-wood-floor-can-you-install-wood-floor-over-carpet.jpg', '2020-01-02 13:29:46', NULL, 2);
INSERT INTO `job` (`id`, `skill_id`, `price`, `description`, `title`, `active`, `address_id`, `remote`, `image_url`, `date_created`, `date_updated`, `requestor_id`) VALUES (13, 8, 3000, 'Looking to have a metal picket fence fabricated for my front yard. Must have welding experience.', 'Picket Fence Welding', 1, 2, 0, 'data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxMSEhUTExMVFhUWFyAaGBYYGR0aGhsaFx0aFxkbGBoaHSggHR0lHRcYITEhJSkrLi4uGB8zODMtNygtLisBCgoKDg0OGxAQGi0lHyUtLSstLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLS0tLf/AABEIALgBEgMBIgACEQEDEQH/xAAcAAABBQEBAQAAAAAAAAAAAAAFAAIDBAYHAQj/xABUEAABAwIEAgYECQkEBwUJAAABAgMRACEEBRIxQVEGEyJhcYEykaGxBxQjQlJiwdHwFSQlM1NyorLhNHOSo0NjZIKTs8ODhMLS8RYmNTZEVFVldP/EABkBAAMBAQEAAAAAAAAAAAAAAAECAwAEBf/EACwRAAICAQMCBQMFAQEAAAAAAAABAhEhAxIxQVEEYYHB8BOR0SIycbHhoRT/2gAMAwEAAhEDEQA/ANQ4iW1RvpN/KgfQX+wI7ir+Y1p3MOQhUTsZHlWd6BJIwQH11+xRrm6HR1DAULRtWcxw/S2HI4sL9hTWr6mOE91ZjNAr8q4W3+hc96KyBI0fWzf1ihvSxz8xxP8AdK91EOoWrh51R6TYacFiCf2SvdQ6hfBbyhQOHYj9mn3VbKeyv90+6qOSicIzIP6tPuq9oV1ah9U38qCMfNSBCl/vH3mpmxP47jT38KUhSv8AWEe00sMPx5GusignlAlESB6f/LqTo2DoQQf9Kof5NV8qI0X5Ljx6urPRoQlBO3WrHn1O9IwnuWLPyYOxOG9WurDyVXPD88M84Imm5OZLIgXVhRf9+pcWYA/773j0hQfJj19BDb43PV4K/Ikg0N6U/qAP9qe94oq+2r5YT83BD1xFCOlauxEz+cvfzUVyZlXowflkR/rP+UqtA4jS1h+0D+i3jbhq6yx9dAOjNlyDBAcj/hqFaN9ACGYt+iFk95OqtLky4B2iWH1TGnAMjxlxui+eQHcVN4ytoA8pDNCHnIw+Ikj+x4cCOPbQaLZ6yTiMY22CpRwTKUpSCpSiQxZIFyaxinqCTiZ//EtpF+JDFXcE8PjOCMARlZsP3Xr38a1OU/B8pQddxZKEO4ZpkNogODSlrUSVAhJ1IIiDvwo9h+hGCCm1p60lDPUg9Z8wgpMwnftG9qrHQnJWTetFM5TlawWsmQPS+Nqt4vIAr3EuqGCzS8TjUeMhThtXXsNhGGQGmUtoQiENCJCVJuogwSVkz2jeRc0P6RdD2MRhl4doJZU4oOLcSkEKUm41DzMkQad+Gl0FWsjNM4ssrb0qlxLTa1A8Q4gEzzBvWyZKHm9QMoWPMf1B91Y/M+ib7bxxbhQWW8JohCiVS23ZXoxp1J5150O6QJCg2o/Ju3SeSjt5HY98VBRccMtuTyg8y04grSSOzeDxHAipnkWSQQkqq1j2ZSTEqAiOY5eI4f1oQ1KlJMqCdp+0TUpKmUWT1rE9SdQBKkqn7/XXQWsYlxtK08RWCxLJSSZkG0mDNXchzEtENr/Vq2P0TzPdRhKsAkjZJd76kDx50PUuDBpB2qWJRfLpphcqr11MU9WsNFzXSqj11KhZqOBjpVnKdziPNo/+WoMB0xzJtENlWiSbNyJO/DnXdkPWMmbHhQLoE/OFI0j9a5/Oan9R9g7PM5en4RMzHEW5t1Cr4Qsb1yXVBBcQCASm8Hf3V3MFIN0pPdArO5wWjmmE7Cf1Tk2EH0Y+2stTyM4vuc9Hwt42bpa9RFLE/CpiHW1NrZaKVgg7ix3rr5wzGqzaJP1RQzpLhmPimIPUonqzfSN4rKavgzi65OdYL4WX220t9SghIgGeA8qur+GFZEfFxcQe1/StrkWVYZWFZlhs/Ji+kTcU/EdHMGUr1YZo2OyRyrb12NtdcnJukGLaVhQUCCpYO3OZoBhVWnv+ytU/g2n8FDYCS0o6h4H+grM4Mdnz+w1VPAjLOVq7Hkv+Sr3RtMpQNJMOLP8AlVSy+yfJX8tXej5ISiP2qtv7qgwnuXpAUzff4sf4qtug6B3Jxnn2hVbLwJZ/7v76lWkqCUpBUpQxQAFySVgAADc1gF5aew/fjgvsrO9Jz2Z4fGHv5q6Dl3QPGPdYFgMJV8XIU5eepHa7KSSDNrxWwyHodhMEUuOFLzpUopW5pASpw6j1abwe8km3CapDSlyK5rhZOP8ARLopjXoUnDrS2UrhxfYT2kQCNVyJO4BroGF6CKcSgLxCJRgviykpSVRMypJJExO0CugvOKPokjv9IedDXXkrOlcIdAkEHhzSeI5jh7a6l4ePUg9WXAGyboPlzYUlZU8pSUpIdsCG40wkW3E8aPsZe2ypS8OlDalABRCEgqCRABUADAFt6puKSvsubn0VbGRvB9v/AKVWxOLcSiAq6Tv9IcJqijGPCFdvlhN/FFQgiCCJqiHi06YPYWkmOSk3JHiPdT8NmTbqJBhcXTHEcu6h+dHUkdrT2V3M2lBva9NvBtG4IhDKSuygnUkbnU4YCiP94es0SaaIQEz21mJPAH0vZWdxr0LeIIIQRCzaSkHSIPNZSb8qL5bjgXesV6TiihkAwNKfSM96h7BS7g7TQtNSIAEd/EbQe6K5n0wybD4ezLYaTqUSnVNiZCkjgkGbcJHDbV47N38SSjDaWkJJBdUJJVJ9AcR9Y/1I3EdF1LSrrHitWkkKVw42jfwpJJSWR1aeB+QZ+l7DwVBS24CjxKTZKvsPkeNUcXmW6QU6b32mTciBI3oPleUlCyLoVsdwCDcRPC23dT8dlLgU5AJI7IETtY7CuaVWdMU6CuV4hDgUkqGpIkC5kd32+Iq86yspKUkBO8kQb99Z/oblq2XlqcbMluEg+IJiPAVpXW+yFEKv7+UVy6kalgouMk4zJ5CAVq6wJEAWkDhfl405jpNhyrQpwIX9BZ0nymx8QTVTCmAoWvsJqPE5ch5sNvNpUCeyCkew1lJ9QNdjRofCrggjuM0i5euBdIsKWHlpZUtACiAAojYnvoQMwemC86f+0VVdgm8+kuvHMUq+a+uc/aL/AMZpUNnmHefRQnTaZj7KBdCW1pw6gUkHrnLGx9I86NA99OSscDt6q5rK9bPZk2G1AszVGY4RX1FiY/do2lZ8qXZJuBbasFj3xe1hQ7pE5GEfn9mfdREH8cK8xEKSUrEg2jhWTyZop9H1/mrO4+TT7qJnDhSTJ4HbwqBpKUjSkWFgOVW22+ydz2T7qKYKwcM6I4zRinWlei4pQ8wTUWf4EsOqEQkq7P8AhoS4sofWobpdJHko1t+kjYxWFbxCYBSmVDviK6OGQ6GWwpCUSfrD+CrWVLI0JSCT1hgDcktRAG5PdVPBjUnSAVKUSEpG5JTAAA4mu49BuhyMuaDroSvFrEk7hqQBpR32uqqQg5ukJOairMn0Y+DV1SW3cYvqYS2Qym7vydxrsQjwgnwrd5fgWMInSwxoF+1pUtUqMklSklVzwog5iNU6pA4mYHncVUDaHI6spMiQbgEGwhXfw3mu6OnDT5OVynN4APS3pl8QaCyypxSlFKYEImJ7Tidv3YmxrD5N8JqFqcXmGDRiBEt6YT1YNimFGCDz338uk5nlbbza8Nil6gfSEDVaCBqkwb7m9rG9C806P4XCFLWHaDcjtXkki0md7Gyhaoa0rddDp0YuC3rDAeW/C/gkEIOWqaa5tuXE3mITPrrqGX4XBYxlvEMyts9pC0qIIIsQZMg7gg94Ncc6TdG2ly4R2gneeUm9vs5bUd+ADMVNuYrBqNoDqQeCkkJcEHaxQY7qnckhnl56nScX0bZWNPyoAOoQoWI5SDUK+iSFT8s5BtBCSBx4JFG8Q6aZ19Tc33Hiq4MJmnRVeFWhaVhaCrTqPZ0E7apMae/+lVM8dbQy46VdlAg6SNUqt2QTfcXrdZiz1zTjf0k28Rce0CuLdOcb1bfxSIdU4FOd0doDvG1+Qq2lqbrslqR20VMDmSng0yVXCjO8kgkpKoEn21qujubsBDyMRp7EhAAhUGApKViFSSecQO+stleWoW/gUALScS11y3QqCghLh0J+bpVoG4J5Gh2Xu9gYgkaS4tiCblWkLBgb+iPbTfUjLAqhJHVMvwwbAekEmyBbS2DaACY8VHed4rxrHdc8UBS9QJEJAmPpEnsnyJ5DeguWY3UyhvSCClIMmUnSO13CEmCN5UK0fRjLEOCJIQbdodtcXEK4CwMeO9c2rLc6bO3RjtjYLw+XOqUkqA06rECNyRcbD+tq1znR9xKHAiQorJBB3kk/b7KOYfLwlISNgQR5URrRW0Wep0Ry7FsPoV8pIjhbfjeJFSMsD0k6lAi0xvxnlR7pWQVW4Csth1DrAhSoQo78lcPXt6q2rG1aAsYZXcPak8fKrjDiJusSD2IMm/AinPp7UWVFhYfjzqFTQaSs9WSsXtBt3AnfuqDph4OT9MP1y5+kffWYUL1rekmDUtxRStPaMgLltVzOygKAKyh4HZPjrTHvrp6Ij1ZWHjSq0MGr6bX+P+lKloJ3JGRNftFDuk0ldHk/NfUO6TVwAEWNJRAsa5ixQOUuJNnjHj99SnAK/aGrqlDgIqutRmw48aASBzLVEWdWD4013I3lQeuXbkauKChceypkYtSdvVRwAos5M/P65fs+6r3xRbKSpbxiDuKvYXGqUYCZ7xT8xbS4AFm3KnSQrs+bcZhVBxaua1X8Sa1nRRuFuYVxU6kgp5XEkVvcb0ZZUT2EQe6sv0pyBTD2GXhkanVuRYHiIE8h31RyTwidNZZc+Cnot+dPYp1PyeHWQ0ObhF1d4Sk+tQ5V09b2onUBfnUDDIZZS2nhueZN1HzM1TexMT416WhppI4tWVsA9Os02aSOwmCpMSHFGYQrmkDtFPElM2kGy00rCJbEwpyVPG0lZCZSNXACLTwmLigedwrEI49pJUDsbj7AKOvZqtS/RagGe0Uo3i0mBB1coureBXPqO5X2OrSrbXc8zR5fW612KzqSSY7JJSCJMhJIiJtYggCrHSOdDcAkAjWoBQFk6QYULTa4MGximYbFFbCikKWstobBCbpJQuybXBWNRImyzNhUWaYoqw7qSUrKClepq4SUKAWlyNrOEkyRKdzFJKdtIstJqDaYJMr0gBMSQoqBsB4C0kC8d9RdF8KjD5mwpsNjXqQQFKcVCkKPaWOwJVFo5VTGM06TGrtgjtczEzBjc8DaquV9aMbhVFOhKXglR60qQokEgoSY+bGybDlUZYkGH7Mo7TiHKqKeqm/i541W+N99JI0QgrGmRFYz4VchQ4yrH6RqGFWhR27QKS2oDaQFOifCtEhzczTOmjfXZJio3S0pX+BUn2A1ODe4fUrac8ycE4zKhICTlxJAPENu78ZNR5Rh2W8F2Qs/nS+rV1hlJ6pGtRWjTPZUpOmPnwRTOjbY+N4JesJjLoiDN21mdo7t5uLUISkJy7L29YJVjytUT2Z0p0qBA7Qi8SL71Ymma19JGIxHVpT23yhIJPcVLPJJCkCbDsk8zWq6O4+EhtTMAou6kk3nTIVEXBBi/CwArN4mQ+txBflD6g4lBDQhSlEdqZIKALATcRzq0zjUKhCgTKusbcN0IhMdl1Q0yAIIVE3m01Nu+h0QVI6Xk2Yz8k4ClwejJB6xA+ek2nhIgEE7RFX8LitZcEegrT7AftrmI6SJJbZfQotrV8m4iCLeiUqBBSqTdPfYkVoB0hWjSpEu6twFTBvISI2JiJ4cavHKIz084JOm61tFKwJSrjyUOB8R7jWRxRC0hafRO45HiPxwrf5p8thHA8BJSSR9GPRI77TXM8E8lBKVeiqx7jwV5e6tdPIWnKPmi5hXypJTp1KTeeJG0k+w1IgGYUSOaY5VQf1Nr5KT6v6giiOHdQqFEaiq4N7Hke+pTjXAidgD4YHz8VY1AAdaYA/cVeuPIF66t8MSycMzO/XG0fUNcrbFPpftElydsyFtPxZjsj9Sjh9UUqkyFY+LMdkfqUc/oilWGDjiTIhO1MSSDJAv31bQiRTepMxwrnwVdkfWFN968LhNODRFNLXE8KFGG8akZbAIKiY40zQSOAPOnhJ40UAtuY1KRDQN96oulRgqmpFo4ixpq1E78KzZkhqU99XssbJOo/N28T+PbQjXJsb1pMO3oQE8QLnv3NX8NDdK+xPXltiQZmsgCDtvQvM3IRI5zRR9sqJEA240GfwYTIUUj6KtV4+iU8R317CWDzW/1GRxmNDr2kLCl6AZAtKDe3iZirPxcOIDoKgUFKHEqNi4rtKKQEngj+LyOe6VMLZeS+2I6v04IuVGQSDcgibjlV3D4pOI0vMBPXJsUKO+8A91zCri5515tShzyelqT05ycoqkzSO41LTAaacggELQCW1KgagpWntjsqIIg3SoncmnJxQGEdQ0pLaiA2hIICSdLjbqidRkkcJVFu41k8Y6FyFFxEXUgi4gkkaR2I7RIVMWtBrRpzLqcOpJUZOu/wAl2tZJj5ROs3Owi4mg2qxyNCUrd8GCwWZHUgqJCZBPl5j31osmxi3sSkDthBJTPBRhNjACiqNzcA1icGCpQQgFRnb3zyFbbCtBAhKfIX9pudzc3M0NSSqiOnd2aVWZHnThmVgIAPE86DpwbpElJSOaoSPWaapQTurUfq/ebVDJU0eExa1WBvWqYbC8qfBuFMvDxHbH2VyrE4pZHZUUjY6TCo49oXB8IrouIzP4vki1vwlQYUgAQNR0lKdMWuINK8NDZcTnXRcJ+NYVatB0ZemUdrrFJU1JV9GBEDYyqhjJCsHgCgGFY1awNWrSjUiNZHIC8xzq7l7S04lCx6LeWsBxUpBShSADCVbm/DYTQtzPGmvkmUrW5cSO8bgzsPC0VVXeBawX82wj4xK3EoWpDjqllSVyNKlHSb2EaT6jxmr4eC0hEpBMFbRiFyLLT3i4PIzQNjEKMhek9kjSTcaRIhRMr5c+Q2qziM0QFw2khZCewqAQtPZMKGx3MHzk0asdNJBjMMQlj5FLnYTIhYOtJJNzYcTfutVnJcTqQVBShpUISDexMEGdgBtWawuHKnDfX2VBIXGoztJ5iYubd1advEo0pSQkQkAhBHC20fRETvJmnigbryajAZiFYfEEzqCDcnh3+6slmGJR8XadNtbikT+OEj20ZS4kM4gAglLMkRsDWTz5rVlmHAJIDzhEcQCoAgHad6DV48xXLqg4w51zcT22xI+sgXI8U+6eVR5djUNrIWewrjyVwV9/9Kr5mQzjNDJAUEhzTykkWncSNu+vc5wgWkOIEJVuPoq4p8OI7j3UidqmaS6oq/C1fDMTP6xW/GEG9co41u+l2ZLewzTS5JZUqFc0kQAfDafCsId/OngqiTlyfQPR15PxTDyD+pb4fUTSpmQNj4qxdH6lHH6opVO0UCSwZgXp7alC/wCPKqrSAnbVB/HGn9ZAAAPLma5k2yuCZTk1E47FeqWQdNiTtTdKx2VWk2Na2bA4r7qideUBOkeu9SOMkASrfzquGwJOoyNo4VjWevOKtfccaYt2Bcg8j/SnqbKhMTxk8vCkAlItv4ffWwax2UQtyYjRdVt+Xtv5Uc6zeh2DToRPFVz7h9vrqdt0Ca9Xw2nth/Jw60rkeYuSbGJ40PcwKE3grV+N6trdB3NVHngbIJR9aJUfCdq71wcb5Mt0p6pKflCkr/ZkifMDbu74rEqwSVq1sqULwQkgLFibpsCDtMwTyrpWKytuCVEkfWhRPsrIZ7kbZIU0lSFTuOzNuW1Q1dLdnqV09Vx/S+DNqwgOtSlwQITMdqSbFQO8TIImTHCoHkOr7KFlw2skk78x99XXsNq7KlqC/MT5VPk7imJbUAUm4UOff3VzPSki/wBWLNJ0XxOBwmHAdRqxCruSqEg7AbEwB37k0VX0gWf1SWm0nYoTJ/xKJ9kVl2izcJba7XpqX2ztwmw8KtamkgJ1tpEQBq4bCBUNucnTNRiltkn7F51alnUpc95UVGnJZTFypXcIT7bmo0Yfa57QkW3HdPjUS3kiB2yS+lgiwhawCCZ4AEUsosVNFxGaKajqWWgr6aklah4ajA9VQ5jnOJSMap9wuPYVLWnk2tazISkQmRCTI322objFlTHIjMEMyCbpTeCO8+6rmdtD9MHipxkepSqCgu3zAXNkeeNKGJcCAjrHMC0yhpMkQtCCopImAlI4zvxoYxli2ktaUj5RRCVwJWRp1AGbAah671Gce84HVLJhemE8AG06Ed4EW86oLfcICeA227+O/E+zkIdJrBk1yWy8pPbuHEEKQZETJJJEb+jBFoG17XMLhRi3krWorddWrrBo0wlCNZVItKoVP9aHoQUo1kg3iDuABJMcrxPjWgyPBrYxYQsjUcI45HILaWQJ5xE+NHCWAfyFMkdKsvKnNBUVr9EWBCEJv3nj3mqBZXGqLLNptbx9vlUuCxKRlQUlOlBdcOkcuwmJO+x3qPC5olxsJk9n0dMbd87b3rQxZnkP5awEYfEEydTYF/E7dxtQrpC8BgcMBpErUIBkbkTfmZPnV3BuTh8ZpvpSn2k0Jz7D/mWBFpK1H1r7/Gm6+vsK3gmznC9d0gZbuElmDfh1bivfB8q8wGZthakEyyslC+4pMBQ7wfZNWnx/7wJI3DR9jZ++sn0fwXWNY93VHVOhXdCivX7k37qi48PyHUuUWc9wZbWptYk7dygdiDyIvWGxuHLbhB23B5iukNr+MNdV/pmUy2eK0C6kd5TuO6RWZzfBhxP1hcHkeR7rU10K0dZyFH5qx2U/qUfyilVjIVxhmBCrMoGw4JFe1Noc9IEWuSeN/I1Gh4yCkCZv+ONTpb1SFbbjh43FehvfSBYWvv8A1rmstREgG91A8D9wFeur1Ab23JrxM2mRHlXiilPC873PrrWAnQ4TJ4xuKroc0KIXB7/sr1oagYNwbkGfCo0tkJhZkSYMQR40TDvjEKBSRB58KjeQSYUQZ2IHOndQDBn2VIFiUi0pEk+FNFW0hZOk2TLMQBsBHqkVBiHa9xSoM9/vvVN9VhXuKNHluRL18U4PWqh1tvCl8YqyZJlp1zioAJHEmKF5ljWlIKdDh7whR28RU6nJN1GmOOD9rHjQYUZLF4dDswZI8j4g1WS0dlXjjz8e+tPiGWyf1iZ+to+6aHYnBRtB7hPs3j3VMerM/meASqNCe1uSLGKhZZ1J0L2HoqA2P43FHEtCTe435jxpmLwCkgPBBKSRqTz5Ecb7UjoZWS5XinEKaSbFAtExuVJUCePM99TYpErQo/PzNs/5bZMes1GMchSgrSQoiJjbhAM9wq9jEXwn18cg+pCE/ZXPqRqJ0Ke+Vgs/qU9+aT7BRLOMMtKswUoaUuPNrEi6k6nAlQ7tSD6vXT+NKLOGICQU5lbSkbJCbm1zAJJNGXWUlbqiVLK1lUKPC+kAHgB7zzqcIOTGclEz+HaVCYsCIUZkL7RI4WAGkRzTPGnlnRMJFxBsDFwbcjw1ciaIljdbQCTNxukxzTt6oNQYrNVbKa0kchqSYMg3v5d9LPSkmPDUi0Bsbh3Qw86mQiAhR2nWbJ/hnyFa/MurGLXpnrUYJWqTYoLAjSItBCpM/OTHGs5i3z+SsTq/+8SPU2DtRXNFRj8VedOXq/5aI99Br39jbs/PMptiMkbtA1uH/MSL+qgWWY1CYCpgkSU+lAuRyNH8Y4n8jNaSYKnI1bwXbaotMbxWHwyr8PxfjTafX+RZYo32W4mcLjyiQEhsetShUGer/Ncs5lw+1wD7agwDoGAzLnLQ/iVTc4dnD5MORTPmtBrXn19jdPncN4dU9InPqsq9iB99Z3own9GZsrvH2/fRrCYkfl/FrifkV92yUCs5kLsZRmFvScF/8P31Fyx9h0vclwIcThcHidYClBekixllZSJ57Jori20PID7cAKstI+avcjwMyPPlWdzp7RleXaQQQXTPPUsmp+j2ZhoyTLTlljiI4+IJp4LcjSl3CiH3gAA4oACAO710qOpytREpgpNwQbEHYi9KhtZtyNWG1SVg22HMDjNe9bEFM8fOvHCSEkRc04sQd4O17z4AcK4zoKrgTGoKJlV/wambN+zsoU1pm0lIIHCbeV6ckz6Poi5H3GsAqpCtjp33mN+6nsMTZTmx5e6pX2kmDPiBPtqJtcklKTqFiLkeuiYkcSDJAkc+J8KkSJSTHCKjZSDJmIvvYmvQ9oRKzYnyE299dHhYuWqiOu6gyliF7TseyfEbH8c6qOOWI5X+/wBtWsWO1B2UPb+PfQt90g39JJ7XeOfmPca9qjy7PHHqrl68Hanu2PMG4NMgGx8jyP3UOBuUSIeHGfEXqdKifRKVjlxoapZbN7d/43qVWIMag1rn5yYAPqo7gbS11g2UjyifZXpabT2oCRzVCY9ZoavGLVbq1JB5OR/4PdXjOCTMlDc8CqXFetZPupWxkhOZnhi5dDyyn56ASgT3p3q6MWl0FNt4gEGwPZPZNvAxVphUcSfZ6gLU13DpWZiFDZQ3Hn9m1TaKIHY/K06exvMx38fX9lU8XjABgiqAEYrUonhpAmfCiuPfDSQFm59E3gxz5b0KweM1rWeqXp7RMJBBCwEqEcdthe5pdSDkqQYyUWOyhSBh9IWF/KKXqSJ0lfMelsBeihCXO0jUoDcAbHnEyKrYdCSNbakpMhIUkAEbyFahY7W8asPIWyoKgOWBBjQsGLjs9kie69OkkqQlt5IhAFvuqHB4frHUNzAcVp8YlXn6JPlXq1Ld9FyDMlLiQDPLUN6hwhCcbgVQbtuKUOSgHEwB/uio6rpFdPIDxM/kl08VY0/8oGj+dpjMcw+rgVfytD7aCNsqdyxtCBK3MaQASBPyY4m1HMyCTmOZBSgkHC6Ss7JCiwNRHIb1zvl+vsWV0vT3KmYSnJsOUmD2iCP701hUI2M3O4rofSTqxlmHDYhGmBcmYWQVCeCiCqOExwrBpim01ab8wTbTS8jUZf8A/CsyPHrWr+Z++rGdAdXkQ5huf8bdUsM7+iMfHHENj3VezwXyFP1WvapqlfPr7BXHzuXsqVGeZgd4Yd9nVCs/kJjIMceb6RP/AAqO5OZzbNVcmHvej7qz2U//AC/izP8A9UkR5M1J/gb/AEd0zTGUZUPquH1mazOW4jRpSr0Vk+RsAffWn6bg/kvKk/UUR56T9tZvpDhuqTh0FJStLZ1g76tap91NB0BoJh9wWC1QLC/LzpV03CYHDFCCW2/RHDur2h/649jfTC+ORAkAC2kQN+Z8ajOKKRZJUNibz6jTWnEG0KHImd/A050BFxJmx1EX8q5DpHJJj0dKZncesinNpCiSlQB75KZ76e44rQEiNXHlVdxAR2wIHBIrUYtB9IJTrSFRcjb3WqNLh0zwBupJn10x0BZEgp1bAkedSKcSlKtJIHDSNzx3tWMRIPDTqna0786WOKdBHAmPsrzCvOKglKQkSZ47WpzTWpOk7kT99el4CFJy9Di8VLhAlvFgjqXbRZK/dP31Rx0gwvcCNXdwnu7+FEMfgNYt6QoL1q0dhQkfRO4/dP2GvROGyTDOT8mq3FJ+yo3gRYVSfN+wTa4SbEfu8/XVQZqNULOlXfafXSNjpBO5sqoNBSqUKUg808fEbGpA+FV6aSx6JkZi+n0mm3RzjSfZapW88b+dh1J8INUU4hTVxccRRXB4lp4cAeRplkDwIZ1hzwUD+6akazBsnsBRPhUn5ORxim4h1LSCUgTsPE7VqBYM6RqLulpKZXBV3W3TPODNVMqbSlSewGXdgr5jn1V8iffRBLUFCvnBYJPeoFP21IpKVgaohY3NwCbQfPY1jPksP4CTIsqJBPzk8Ur+l51XbcCF9SqdBEp5oPETyqbA48IlCljW3dJmSQbQe8beqhklSlKPO1AJdzJhDba3YJ6tBUBO5AkUHy5ZVicuO5+KLUfEpeJNTP4rrMLjIJIbZ9q+PqEedVMtticAP/1xV60O1ya07ddvwdOnGlYNF8rww+ljVfygUcz8/nmdfVw/jupigCVfozADnjFX8wKKZ05OIzxR/ZJH+Y0PsqLln53RTaO6QrjJ8HHFtJPKStdYllSSkyVapTpA2i+rVx+jEd9bTpY8pOTYFueypttUd8uX99YBhdx4im03h/yCeH6Gww6h+RsZzOLR7AmjWeAddkY3htk/xN/0oE2r9BYnvxyfYgUez1c43I0xYNsj+JFI3n19gpYH9H3Pz7OVWsy6L96uHqoFgRHR1+2+LF/Jr7qJ9HFfLZ4scG3N+RU591UGzHRtQ54uf5R9lI37BF08AGXZQP8AVEn+CgvTJ0vYsEgiU2B5CY91GfhCXGBygSP7NNvBves/mWOSvENuTOpqT3KUlQj11o/kx1RjEQlItYAeoUqDLegkajYxvSrmcRzZLfTqCQLgSq20d9PRpvAmeEz6tX9KYoAiLeIHvqQOJ0kkAiwEiB7DW8ioxpzY6fv9lSmPSIA8TvPIHamDCkK3AB+de/hPCmqO8Xg25fdRATocTIBBki3h9lMUJT6MEcrjwpJe+cogqHKZg7C9QlRWDJsTtvf1UDE7dwYTB9lBsxBJUkk6J4WIvuIoy2tLcSdIiL1BjMKLq1Jg/bXt+Hjt0keZrO5syWaN40KBS9KUjsmNx9bn41XOerHZxTM/XT9oNGsTj0NHTq1Dlv5VVxGMac9JCvGK6CDBa8ZhlXS4sdxST696HY11tVtSldwbI99E38tYVcEg+EVWcyBB3W4R40skMnQB+PLaNvR+isgW7uIozl+bIdsk9r6J+znQ/MWcKyCANSuW58+FDUZet49YR1bY9EC23Lv76k0+hRPuawvc6rrTeUmDQjD5xpOly44L4+f31eTikK2UPXWsIby/NJ7KzV5adahyFxWVcBojlWeFsFKk6u+YNMpdBaNChnUb7Agm8GAQTHLbehl1da2gFR1ygDk4ZgDxM07F5shaAEBWomFSIAHGqeIxnUqmJ1RbawEVm10Ml3KGY4/5RJIghWknnIgz5+6psbmbQU22FKKlqSCkbAEgEqPntVLF4YYjFNNtShDq+wV/VEquNwIIoLg7vsE8X/8AqiueWr0RaOmaRpKRh84gWS4UjlAUQPZXmX3xOEnhlhNv7tyvUqPxLOFcDiSI/wB+m4YximI4ZV/0l1yN8/Oh0JA9pROX5an/AGxR/iAq7nDnymeEkzCB4/LImfVVdtZ+JZQIt8aXFt/lRTc3cUfy0eBcbB/40j3UvX53CWemS/0XgRb9U37lmsGwq4vxrc9OT+j8EOTTQ/gUaw2FUkatQJJjQQYAOoEkiLjTqEWuQeFGDwCXJrG1xkTt/Sx4t4NitT0jtmeTAX0tsx/iH2isk4kjIgeCseR6mq2HSBIGc5QmSQEM/wA5+6lfP3/oKBnRpZ0Z8r6i/ap2qj9ujjffij53V91TdGU/m+eK+oR61OVBmEf+zuGtc4lV/Nz7q3+GG/CKmMNlA/2RJ9YbrHgy6j8cTW2+FUAIytI4YNHuT91YZs9tJ7vtNPHgV8m2xuJPWL29I8uZr2hGOxPyi7fPPvNKk2DWdkKjp5A7Rv4mkFXO5HAETPOI2pa1XV6RHLh4xT8OQUkgAE7gn11DgsM1pUoISRAv6UxPjTiiT6WkJ3gST/SlhWNPMKV4bcpk01LYgymCDYpPLiedYx6nDKPzjHAGPdFJQCTckwbbTPHypJxBVzMjtcIg8uFelY7I0AzblRoBXzZtLrZSoSOI9tZTGYBxr0VKKOF63OIbRpkbhO2893jQrD9WpAKXEqbXdN9q9jwmrGeml1R53iIOM77mTw74SdxPI1cTi0HcpFE8Zk6VcBQp7o4JtIrp2voc9jcbmiUWQgrUdoFvM0CxZxDphatI+gm3ri9HG+jq9w4QONqkc6nDj6SuZIufxNBruFPsBsF0fQga3IA5c/6UsxcLkIRYce4VHjs11kyZPIbCqJxJOwPlvS32DRDisK2kXM0Iew87COQitBh8sUs6lA9wqy7hEtiVRPupXFPkZSa4M6yhxsWWQOW49tLD5s4tR7KSkcYp+OUXVaEGQdyNgK9LaG0wTb2nwqD5wXXGQjgcctR0pG88jMAqO45AmrOXPdavD6pKXGnVdo7BpK4iDzTNV8tdbLuHKQZ6p/XfjoVHsill+I0DAwCYwr8j94vSa5tXVle0tDTjVlro6ka8p7+vJJ/eX91BcuV8pgyN/jH/AFU1qMhZh7JkmP1DyrEH0g6obcdrcDWfyRmV5Z9d8+x1NTT+fccIlf5hmiuJxXvWPvp61RikQDbLLyP9WqT4VGVj8l5lzVix6gtNWFpHxpeokacqkd56sR76XuEpYRz5DJ03/tCjvb9eBb2eqm5iZTnMAmXkex5ZvHcKmy0wjI5Ajr1m/wD/AEJ39VeY5zq281Wy4bvoCraSCpx4KSLmRFptvWbz87gJ/hFI+J4EDgy0DPPQqffXPm9x410D4SEgYXBgD/RN+vq7/ZXPm9x400OAS5NZijGSNDnjln1NAfbWyzufy7lkwCENmNtlL+6sdjEj8i4YTc4xy3+4BWs6QrP5fwZXEhtEx/2lK/yEEdFnP0dnB+lp9Ur++oc5t0fwQ+liFnbkXeNM6MYpIyjMwTClqRA53/rXnSFR/ImXiFx1jm4ITMr9EkQd+Fbr6+xuhb+F9fbwI4JwqR+PVWDHpJ8Pvrb/AAvPhb+FICgPiyRCrGxIuOFYcekPAU8P2ivkI4jE9tW3pH30qouKufGlWMfRLeJBA0ySfmxE86c4kEwABJ9XlxpUq5pKi5EQQq5HlI9ff4VYUL2EKHHce21KlQCN6vUo7T87b2n7KgDESTCRw3pUqKAyw2x49x3jxH31yQZi7gcU6I1JDigps/vG6eRi4517Srs8E8tHN4rhGgR8IuGFihaT3iR7KeOm7a7N6JO0mPfSpV2fVldHO9KNWRvYnEOi6wkfV39tD05Fr7SlEz3++KVKrrJJqkXmcibTvFSPKwzAla0J8xSpVpvbGzRVugHmHStERhwCfpqskfaazr60uHU/iArjpSJ9Q2pUq43qtnSoIrO5kQNLSdCeZuo95O1QJJNyZJ50qVCLuWQtUgrkOOS0oqUnUNC0juK0KQD/ABUWyRRa+LlabKy/EaO/V1wB9c0qVT8QlhjaLeUSdGWnFP5aklSfkHyg7WCXiYngYNUujjK1OZWAd3laZsAes50qVQ+f2VLJUfyVjpUY+Ni3D0kk++pMaVHEvkmSnLR/IgR7aVKgEgypgqOTJgmVrIA/vuHqqtiiPi+Z2knFIgzw1u8KVKj8/wCgYS+EpBSxgwQB8kjb+7T99YbEBAKdBURpTq1R6UdqI4TtSpVtPg0jS4xROU4NMD+1OQYufRm/GtJnuH/TWGTEfIgkWPB3fvgUqVB8/cwFyAAZJj1HcuNhNgb6kcdxY0/pS9+hstRJ9JwxPfwHnSpUevr7A6D/AIYmyMWzwnDIPrKqxHzh4D7KVKmhwgPka7ufGlSpUxj/2Q==', '2020-01-02 13:45:23', NULL, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bid`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (1, 5, 'I can do this for 5 $', 3, 2, 1, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (2, 120.00, 'I will come to you. Should take about 2 hours. Let me know!', 2, 4, 1, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (3, 60.00, 'I can tutor you for this calculus class. 60$ for 3 hours.', 2, 9, 1, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (4, 80.00, 'I will construct an expertly composed reasearch paper and I can complete it by this saturday.', 2, 10, 1, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (5, 100.00, 'I can create a logo for your gig-ity account which will display your skills.', 5, 3, 0, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (6, 25.00, 'I can come today to cut the grass and plant the tree.', 7, 5, 1, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (7, 60.00, 'I can come today to cut the grass and plant the tree.', 7, 6, 1, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (8, 5000, 'I can come by to give you an estimate.', 6, 12, 0, 0, NULL);
INSERT INTO `bid` (`id`, `bid_amount`, `description`, `bidder_id`, `job_id`, `accepted`, `rejected`, `available`) VALUES (9, 6000, 'I have a team of experienced professionals who can knock out this job in a few days.', 8, 12, 0, 0, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `booking`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `booking` (`id`, `bid_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (1, 1, 2, '2019-12-07', NULL, '2019-12-18', 'test', 1);
INSERT INTO `booking` (`id`, `bid_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (2, 2, 4, '2019-12-30', '2019-12-30', '2019-12-30', NULL, 1);
INSERT INTO `booking` (`id`, `bid_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (3, 3, 9, '2020-01-01', NULL, '2020-01-04', NULL, 1);
INSERT INTO `booking` (`id`, `bid_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (4, 4, 10, '2019-12-20', '2019-12-26', '2019-12-28', NULL, 1);
INSERT INTO `booking` (`id`, `bid_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (5, 6, 5, '2019-08-24', '2019-08-24', '2019-08-24', NULL, 1);
INSERT INTO `booking` (`id`, `bid_id`, `job_id`, `start_date`, `complete_date`, `expected_complete_date`, `notes`, `accepted`) VALUES (6, 7, 6, '2019-08-24', '2019-08-24', '2019-08-24', NULL, 1);

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
-- Data for table `booking_message`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `booking_message` (`id`, `booking_id`, `message`, `message_date`, `bid_id`) VALUES (1, 1, 'I can do that', '2019-12-07 23:59:59', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `buyer_review`
-- -----------------------------------------------------
START TRANSACTION;
USE `giggity`;
INSERT INTO `buyer_review` (`id`, `comment`, `rating`, `job_id`, `review_date`) VALUES (1, '23:59:59', 1, 1, '2019-12-07 23:59:59');

COMMIT;

