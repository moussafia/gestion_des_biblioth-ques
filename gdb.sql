-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 14 sep. 2023 à 20:53
-- Version du serveur : 10.4.25-MariaDB
-- Version de PHP : 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `collection`
--

CREATE TABLE `collection` (
  `id` int(11) NOT NULL,
  `nom_livre` varchar(255) DEFAULT NULL,
  `auteur` varchar(255) DEFAULT NULL,
  `ISBN` varchar(13) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `collection`
--

INSERT INTO `collection` (`id`, `nom_livre`, `auteur`, `ISBN`, `quantity`) VALUES
(1, 'antigone', 'mohammed', '123', 12);

--
-- Déclencheurs `collection`
--
DELIMITER $$
CREATE TRIGGER `add_livre` AFTER INSERT ON `collection` FOR EACH ROW BEGIN
	DECLARE quantity INT;
    SET quantity=NEW.quantity;
    WHILE quantity>0 DO
    insert INTO livre(`satus_id`,`collection_id`)
    VALUES(1,NEW.id);
    SET quantity=quantity-1;
    END WHILE;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `emprunteur`
--

CREATE TABLE `emprunteur` (
  `id` int(11) NOT NULL,
  `nom_emprunteur` varchar(255) DEFAULT NULL,
  `nemuro_emprunteur` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `emprunteur`
--

INSERT INTO `emprunteur` (`id`, `nom_emprunteur`, `nemuro_emprunteur`) VALUES
(1, 'simo', 1234),
(2, 'moi', 2354);

-- --------------------------------------------------------

--
-- Structure de la table `emprunteur_livre`
--

CREATE TABLE `emprunteur_livre` (
  `emprunteur_id` int(11) DEFAULT NULL,
  `livre_id` int(11) DEFAULT NULL,
  `date_emprunte` date DEFAULT NULL,
  `date_retour` date DEFAULT NULL,
  `retard` int(11) DEFAULT NULL,
  `isRetourner` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `emprunteur_livre`
--

INSERT INTO `emprunteur_livre` (`emprunteur_id`, `livre_id`, `date_emprunte`, `date_retour`, `retard`, `isRetourner`) VALUES
(1, 1, '2023-09-14', '2023-10-24', 0, 1),
(2, 2, '2023-09-14', '2023-10-24', 0, 0);

--
-- Déclencheurs `emprunteur_livre`
--
DELIMITER $$
CREATE TRIGGER `update_statut_livre` AFTER INSERT ON `emprunteur_livre` FOR EACH ROW BEGIN
    UPDATE livre
    SET satus_id = 2
    WHERE livre.id = NEW.livre_id;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `livre`
--

CREATE TABLE `livre` (
  `id` int(11) NOT NULL,
  `collection_id` int(11) DEFAULT NULL,
  `satus_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `livre`
--

INSERT INTO `livre` (`id`, `collection_id`, `satus_id`) VALUES
(1, 1, 2),
(2, 1, 2),
(3, 1, 1),
(4, 1, 1),
(5, 1, 1),
(6, 1, 1),
(7, 1, 1),
(8, 1, 1),
(9, 1, 1),
(10, 1, 1),
(11, 1, 1),
(12, 1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `status`
--

CREATE TABLE `status` (
  `id` int(11) NOT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `status`
--

INSERT INTO `status` (`id`, `status`) VALUES
(1, 'diponible'),
(2, 'emprunt'),
(3, 'perdu');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `collection`
--
ALTER TABLE `collection`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `unique_constraint_name` (`ISBN`);

--
-- Index pour la table `emprunteur`
--
ALTER TABLE `emprunteur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_emprunteur` (`nemuro_emprunteur`);

--
-- Index pour la table `emprunteur_livre`
--
ALTER TABLE `emprunteur_livre`
  ADD KEY `livre_id` (`livre_id`),
  ADD KEY `emprunteur_id` (`emprunteur_id`);

--
-- Index pour la table `livre`
--
ALTER TABLE `livre`
  ADD PRIMARY KEY (`id`),
  ADD KEY `satus_id` (`satus_id`),
  ADD KEY `collection_id` (`collection_id`);

--
-- Index pour la table `status`
--
ALTER TABLE `status`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `collection`
--
ALTER TABLE `collection`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT pour la table `emprunteur`
--
ALTER TABLE `emprunteur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `livre`
--
ALTER TABLE `livre`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT pour la table `status`
--
ALTER TABLE `status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `emprunteur_livre`
--
ALTER TABLE `emprunteur_livre`
  ADD CONSTRAINT `emprunteur_livre_ibfk_1` FOREIGN KEY (`livre_id`) REFERENCES `livre` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `emprunteur_livre_ibfk_2` FOREIGN KEY (`emprunteur_id`) REFERENCES `emprunteur` (`id`) ON DELETE CASCADE;

--
-- Contraintes pour la table `livre`
--
ALTER TABLE `livre`
  ADD CONSTRAINT `livre_ibfk_1` FOREIGN KEY (`satus_id`) REFERENCES `status` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `livre_ibfk_2` FOREIGN KEY (`collection_id`) REFERENCES `collection` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
