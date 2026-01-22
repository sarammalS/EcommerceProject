CREATE DATABASE IF NOT EXISTS `greencartname` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `greencartname`;

SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100),
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(255),
  `phone` VARCHAR(20),
  `is_active` BOOLEAN DEFAULT TRUE,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `last_login` TIMESTAMP NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `admin` (name,email,password,phone,is_active) VALUES
('Sarammal','sarammalbca2003@gmail.com','$2a$12$R9h7cIPz0gi.URNNX3kh2OPST9/PgBkqquzi.Pf97fh','7755632012',TRUE),
('Ashish Kumar','test34@gmail.com','$2a$12$KL9n/h6Gvd7Ks2w1.XfVe1Jc8Vz3YrRh5QpMnWtVxD','8565452152',TRUE);

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL UNIQUE,
  `image` VARCHAR(100),
  `is_active` BOOLEAN DEFAULT TRUE,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `category` (name,image) VALUES
('Mobiles','mobiles.jpeg'),
('Appliances','appliances.png'),
('Laptops','newlaptop.jpeg'),
('Home & Furniture','home-furniture.png'),
('Books','books-.png'),
('Clothes & Fashion','cloths.png'),
('Electronics','electronics.png');

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100),
  `email` VARCHAR(100) NOT NULL UNIQUE,
  `password` VARCHAR(255),
  `phone` VARCHAR(20) UNIQUE,
  `gender` VARCHAR(20),
  `registerdate` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `address` VARCHAR(250),
  `city` VARCHAR(100),
  `pincode` VARCHAR(10),
  `state` VARCHAR(100),
  `is_active` BOOLEAN DEFAULT TRUE,
  `email_verified` BOOLEAN DEFAULT FALSE,
  `last_login` TIMESTAMP NULL,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (name,email,password,phone,gender,address,city,pincode,state) VALUES
('Anirudh Kumar','test786@gmail.com','$2a$12$R9h7cIPz0gi.URNNX3kh2OPST9/PgBkqquzi.Pf97fh','7546254260','Male','KN nagar','Patna','401980','Bihar'),
('Amit','amt677@gmail.com','$2a$12$KL9n/h6Gvd7Ks2w1.XfVe1Jc8Vz3YrRh5QpMnWtVxD','8563201201','Male','AJ','Banglore','865012','Karnataka');

DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `pid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `description` VARCHAR(500),
  `price` DECIMAL(10,2) NOT NULL,
  `quantity` INT DEFAULT 0,
  `discount` INT DEFAULT 0,
  `image` VARCHAR(100),
  `cid` INT,
  `is_active` BOOLEAN DEFAULT TRUE,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`pid`),
  CONSTRAINT fk_product_category FOREIGN KEY (`cid`) REFERENCES `category`(`cid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `product` (name, description, price, quantity, discount, image, cid) VALUES
('SAMSUNG Galaxy F14 5G','High performance 5G smartphone','18490.00',9,24,'phone1.jpeg',1),
('LG 242 L Frost Free Double Door Refrigerator','Energy efficient double door fridge','37099.00',50,29,'fridge1.jpeg',2),
('OnePlus Y1S Pro 138 cm LED Smart TV','4K LED Smart TV','49999.00',1,18,'tv1.jpeg',2),
('ASUS TUF Gaming A15','15.6 inch Gaming Laptop','71990.00',11,20,'asus_tuf.jpeg',3),
('Men Printed Casual Jacket','Black Cotton Casual Jacket','1999.00',1,57,'men_jacket.jpeg',6),
('boAt Airdopes 161','TWS earbuds with 40 hours playback','2400.00',27,42,'boat-airdopes.jpeg',7);

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `orderid` VARCHAR(100) NOT NULL UNIQUE,
  `status` VARCHAR(100) DEFAULT 'Pending',
  `paymentType` VARCHAR(100),
  `userId` INT,
  `total_amount` DECIMAL(10,2),
  `shipping_address` VARCHAR(500),
  `date` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  `delivery_date` TIMESTAMP NULL,
  `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_orders_user FOREIGN KEY (`userId`) REFERENCES `user`(`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `ordered_product`;
CREATE TABLE `ordered_product` (
  `oid` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100),
  `quantity` INT,
  `price` DECIMAL(10,2),
  `image` VARCHAR(100),
  `orderid` INT,
  `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`oid`),
  CONSTRAINT fk_ordered_product_order FOREIGN KEY (`orderid`) REFERENCES `orders`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` INT,
  `pid` INT,
  `quantity` INT DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_cart_user FOREIGN KEY (`uid`) REFERENCES `user`(`userid`),
  CONSTRAINT fk_cart_product FOREIGN KEY (`pid`) REFERENCES `product`(`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE `wishlist` (
  `idwishlist` INT NOT NULL AUTO_INCREMENT,
  `iduser` INT,
  `idproduct` INT,
  PRIMARY KEY (`idwishlist`),
  CONSTRAINT fk_wishlist_user FOREIGN KEY (`iduser`) REFERENCES `user`(`userid`),
  CONSTRAINT fk_wishlist_product FOREIGN KEY (`idproduct`) REFERENCES `product`(`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS=1;
