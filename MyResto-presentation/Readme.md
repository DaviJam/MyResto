# Gestion Etablissement


## Pré-requis
Version java : 15.0.2 <br>
Système de gestion de Base de donnée: MySQL <br>
Container web Tomcat : version 7.0

## Fonctionnalités

Ce logiciel permet de gérer un établissement scolaire. Il est actuellement possible de :

* Se connecter,
* Gérer les cours de l'école,
* Gérer les étudiants,
* Associer un cours à un étudiant,
* Donner une note à un étudiant pour un cours donnée,
* Accéder à la liste des étudiants (pour le directeur uniquement)

:warning: Les fonctionnalités suivantes n'ont pas été implémentées :
* Accéder au menu de l'organisation depuis l'p,
* Créer un compte,
* Accéder au menu Planning depuis l'p,

## Installation

* Importer le fichier de base de donnée **gestion_etablissement.sql** dans votre base de donnée.  Le nom de la base de donnée est le même que celui du fichier .sql.
* Copier/Coller le fichier **etablissement.war** dans le répertoire '**webapps**' de votre container web. Vous devez avoir une instance de tomcat en fonctionnement.
* Ouvrir la page web **http://localhost:8080/etablissement/home** dans votre navigateur

Deux choix sont alors possibles.

* Accéder à l'application avec le compte Directeur :

  > Identifiant : e.chaumont@ensup.eu
  >  Mot de passe : e.chaumont

* Accéder à l'application avec le compte Responsable :

  > Identifiant : m.demaison@ensup.eu
  > Mot de passe : m.demaison
  >
  > :warning: Seul le Directeur peut afficher la liste des étudiants.​

## Release V5 

* Création de la version WEB de l'application

## Release V4

* Passage du projet sous maven,
* Ajout des tests unitaires avec junit, harmcrest et mockito

## Release V3

* Possibilité de renseigner la moyenne générale d'un étudiant,
* Possibilité d'afficher la moyenne des étudiants sous forme d'histogramme et camembert

## Release V2

Amélioration de la gestion des erreurs

## Release V1

Amélioration de la robustesse

## Release V0

Création du projet