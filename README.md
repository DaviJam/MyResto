# MyResto

## Fonctionalités
Application web et mobile permettant :<br>
* Aux visiteurs de voir la carte
* Aux clients de commander en ligne
* Au gérant d'analyser les commandes en cours et faire un prévisionnel de commandes

## Pré-requis
Version java : 15.0.2 <br>
Système de gestion de Base de donnée: MySQL <br>
Container web Tomcat : version 9.0

## Release V0
* Création de l'application avec les fonctionnalités de base

## Release V1
*  Permettre aux utilisateurs non authentifiés de voir la carte
*  Aux utilisateurs authentifiés de commander

## Utilisation
Aller sur le site www.myresto.com

Deux choix sont alors possibles.

* Accéder à l'application avec le compte Directeur :

  > Identifiant : gestion@myresto.com
  >  <br/>Mot de passe : gestion01*

* Accéder à l'application avec le compte Responsable :

  > Identifiant : m.dillon@gmail.com
  >  <br/>Mot de passe : m.dillon

## Localisation du répertoire des Logs

Format application
* Le répertoire des logs est situé dans le même répertoire que le .jar.

## Convention de nommage

1. <b/>Package:</b>
   <br/>Utiliser la convention <u/>dotcase</u>.
   <br/>Examples:
    * com.sun.eng
    * com.apple.quicktime.v2
    * edu.cmu.cs.bovik.cheese
      <br/>
2. <b/>Attribut:</b>
   <br/>Utiliser la convention <u/>camelCase</u>.
   <br/>Examples:
    * int     iDea;
    * char     n;
    * float    myWidth;
3. <b/>Méthode:</b>
   <br/>Utiliser la convention <u/>camelCase</u>.
   <br/>Examples:
    * run();
    * runFast();
    * getBackground();
4. <b/>Class:</b>
   <br/>Utiliser la convention <u/>PascalCase</u>.
   <br/>Examples:
    * class Raster;
    * class ImageSprite;
5. <b/>Interface:</b>
   <br/>Utiliser la convention <u/>IPascalCase</u>.
   <br/>Examples:
    * interface IRasterDelegate;
    * interface IStoring;
    
## Tests unitaires
Pour lancer les tests unitaires
> mvn test
