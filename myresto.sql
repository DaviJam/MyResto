-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 05 juil. 2021 à 11:40
-- Version du serveur :  10.4.10-MariaDB
-- Version de PHP :  5.6.40

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `myresto`
--
DROP IF EXISTS USER
CREATE USER 'user'@'%' IDENTIFIED BY 'Dbtest123&';
GRANT SELECT, UPDATE, INSERT, DELETE, EXECUTE, FILE, SHOW DATABASES, SHOW VIEW ON *.* TO 'user'@'%' WITH GRANT OPTION;
ALTER USER 'user'@'%' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0; 
USE `myresto`;

CREATE TABLE role(
   id_role INT NOT NULL AUTO_INCREMENT,
   description VARCHAR(50),
   PRIMARY KEY(id_role)
);

INSERT INTO `role` (`id_role`, `description`) VALUES
(1, 'restorer'),
(2, 'client');

CREATE TABLE category(
   id_category INT NOT NULL AUTO_INCREMENT,
   description VARCHAR(50),
   PRIMARY KEY(id_category)
);


INSERT INTO `category` (`id_category`, `description`) VALUES
(1, 'Menu'),
(2, 'Burger'),
(3, 'Accompagnement'),
(4, 'Boisson');


CREATE TABLE status(
   id_status INT NOT NULL AUTO_INCREMENT,
   description VARCHAR(50),
   PRIMARY KEY(id_status)
);

INSERT INTO `status` (`id_status`, `description`) VALUES
(1, 'En attente'),
(2, 'En cours'),
(3, 'Terminé'),
(4, 'Annulé');


CREATE TABLE users(
   id_user INT NOT NULL AUTO_INCREMENT,
   surname VARCHAR(50),
   firstname VARCHAR(50),
   email VARCHAR(50),
   password VARCHAR(50),
   address VARCHAR(50),
   id_role INT NOT NULL,
   PRIMARY KEY(id_user),
   FOREIGN KEY(id_role) REFERENCES role(id_role)
);

INSERT INTO `users` (`id_user`, `surname`, `firstname`, `email`, `password`, `address`, `id_role`) VALUES
(1, 'Gerant', 'Gerant', 'gestion@myresto.com', 'myresto', 'rue', 2),
(2, 'Client', 'Client', 'client1@gmail.com', 'client', 'rue', 1);

CREATE TABLE product(
   id_product INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(50),
   description VARCHAR(50),
   price DOUBLE,
   allergen VARCHAR(50),
   image VARCHAR(50),
   stock int,
   id_category INT NOT NULL,
   PRIMARY KEY(id_product),
   FOREIGN KEY(id_category) REFERENCES category(id_category)
);

INSERT INTO `product` (`id_product`, `name`, `description`, `price`, `allergen`, `image`, `stock`, `id_category`) VALUES
(1, 'Coca cola', 'Soda trèsssss sucré !!!', 1.5, '', 'img/boisson-coca.png', 97, 4),
(2, 'Fanta citron', 'Soda trèsssss sucré !!!', 1.5, '', 'img/boisson-fanta-citron.png', 100, 4),
(3, 'Fanta orange', 'Soda trèsssss sucré !!!', 1.5, '', 'img/boisson-fanta-orange.png', 100, 4),
(4, 'Sprite', 'Soda trèsssss sucré !!!', 1.5, '', 'img/boisson-sprite.png', 100, 4),
(5, 'Chicken burger', '', 5, '', 'img/burger-chicken.png', 99, 2),
(6, 'Beef burger', '', 5, '', 'img/burger-natural-beef.png', 100, 2),
(7, 'Fish burger', '', 5, '', 'img/burger-fish.png', 100, 2),
(8, 'Menu chicken', '', 8, '', 'img/menu-chicken.png', 99, 1),
(9, 'Menu croustille', '', 8, '', 'img/menu-croustil.png', 100, 1),
(10, 'Menu fish', '', 8, '', 'img/menu-fish.png', 100, 1),
(11, 'Menu beef', '', 8, '', 'img/menu-natural-beef.png', 100, 1),
(12, 'Boulette fromage', '', 4, '', 'img/suppl-boulette.png', 100, 3),
(13, 'Frites', '', 3, '', 'img/suppl-frite.png', 100, 3),
(14, 'Pomme frite', '', 4, '', 'img/suppl-pomme-de-terre.png', 100, 3),
(15, 'Wrap poulet', '', 8, '', 'img/suppl-wrap.png', 100, 3);

CREATE TABLE orders(
   id_order INT NOT NULL AUTO_INCREMENT,
   order_date TIMESTAMP,
   id_status INT NOT NULL,
   id_user INT NOT NULL,
   PRIMARY KEY(id_order),
   FOREIGN KEY(id_status) REFERENCES status(id_status),
   FOREIGN KEY(id_user) REFERENCES users(id_user)
);

CREATE TABLE list(
   id_product INT,
   id_order INT,
   quantity INT,
   PRIMARY KEY(id_product, id_order),
   FOREIGN KEY(id_product) REFERENCES product(id_product),
   FOREIGN KEY(id_order) REFERENCES orders(id_order)
);


--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `list`
--
ALTER TABLE `list`
  ADD CONSTRAINT `list_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `list_ibfk_2` FOREIGN KEY (`id_order`) REFERENCES `orders` (`id_order`);

--
-- Contraintes pour la table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`id_status`) REFERENCES `status` (`id_status`),
  ADD CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`);

--
-- Contraintes pour la table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`id_category`) REFERENCES `category` (`id_category`);

--
-- Contraintes pour la table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);
COMMIT;