# Guide pour l'utilisation de git dans le cadre de notre projet PPE


## Sommaire
1. Introduction
2. Présentation des commandes principales
3. Bonne pratique pour éviter les conflits et les erreurs
4. Utilisation des issues
5. Utilisation des pull request

## Introduction

Le but de ce document est de fixer les règles que nous nous engageons à suivre pour le dévloppement de notre application et également de prévenir des risques de collision. Il a aussi pour but de fournir une aide à ceux qui ne connaissent pas très bien git et gitHub pour permettre à tout le monde de tirer le meilleur de cette technologie.

## Présentation des commandes pricipales

### Configuration de Git
Pour configurer votre git si cela n'est pas déjà fait vous devez saisir les commandes suivantes sur votre terminal git:
* git config --global user.name "ton nom"
* git config --global user.email "ton.email@truc.com"
--------
pour que soit plus simple mettons nos pseudo et email utilisé sur github

### Importation du projet en local
Le projet est déjà créé sur le repository distant il sufit de créer votre propre repository local. Pour cela placer vous où vous voluez sur votre ordinateur avec la commande :
* cd /c/ouvousvoulez
-------
puis entrez la commande :
* git clone addresse-du-repository-distant
---------
Vous pouvez trouver l'adresse du repository distant sur gitHub quand vous êtes dans le projet en cliquant sur "clone or download" (un bouton vert).Une fois que cela est fait, placez vous à l'intèrieur du projet, si tout c'est bien passé vous devriez avoir un master qui apparait entre parenthèse après le détail du chemin d'accés.

###les commandes de base git

Si vous voulez voir l'état de votre repository local vous devez rentrer la commande suivante.
* git status
------

cette commande vous indique si vous avez des fichiers qui ont été modifié des fichiers qui n'ont pas encore été commmit.

------
------
Vous pouvez voir les différentes modifications que vous avez apporté à votre document avec la commande
* git diff
------
pour sortir de l'affichage des modifications vous devez appuyer sur Q.
-----
------
Avant de commit un fichier ou plusieurs vous devez les ajouter avec la commande :
* git add nom-du-fichier-1 nom-du-fichier-2 ect
-------
Si vous voulez ajouter tout les fichiers pour votre prochain commit vous devez entrer la commande suivante :
* git add --all
-----
-----
Une fois les fichiers ajouter vous pouvez faire un commit pour les enregistrer dans votre repository local avec la commande :
* git commit -m "votre message"
-----
le message sert d'historique des mmodifications il doit donc aidez les autres personnes du groupe à savoir ce qui à été fait en une ligne.
-----
-----
Vous pouvez voir l'hitorique des différents commit du projet avec la commande:
* git log
----
Cette commande présente les différents commit le nom et l'adresse mail de celui qui les a fait ainsi que le commentaire attaché à son commit. Pour sortir de cette historique vous devez encore une fois appuyer sur Q.
----
----
Il faut maintenant sauvegarder votre repository local sur le repository distant. Pour cela il faut utiliser la commande :
* git push
-----
-----
Jusque ici nous étions toujours sur la même branche mais nous pouvons egalement créer de nouvelles branche avec la commande :
* git branch nom-de-la-branche
-----
il est préferable que le nom de la branche soit parlant pour permettre de s'y retrouver plus facilement.
----
----
Une fois la branche créer il faut aussi pouvoir naviguer entre les differente branche. Ainsi pour afficher les différentes branches disponibles vous devez tapez la commande :
* git branch
----
Vous pouvez choisir de vous positioner sur une branche avec la commande:
* git checkout nom-de-la-branche
-----
Une fois le travail finie sur un branch vous pouvez la merger sur une autre branche. Vous devez d'abord vous placer sur la branche qui va recevoir les modifications de la branche que vous voulez merger puis ensuite taper la commande suivante : 
* git merge nom-de-la-branche-à-merger
----
Nous utiliserons trés raremement cette commande puisque nous utiliserons les pull-request pour que notre code soit valider par une autre personne de notre groupe.
----
Nous pouvons aussi vouloir supprimer notre branche une fois qu'elle n'est plus utile pour cela on entre dans le terminal :
* git branch -d nom-de-la-branche-à-supprimer
-----
nous pouvons également avoir envie de supprimer une branche avant de l'avoir merger sur une autre branche puisque notre travail ne nous convient pas. Pour cela on utilise la commande :
* git branch -D nom-de-la-branche-à-supprimer
----
cette commande est irréversible à ma connaissance il faut être bien sur avant de l'utiliser.
------
------
Il nous faut également récupérer les diferentes modification qui se font sur la branche master.Pour cela nous utilisons la commande :
* git pull origin master
------
------


## Bonne pratique pour éviter les conflits et les erreurs

## Utilisation des issues

## Utilisation des pull request

