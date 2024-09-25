## Bati-Cuisine
### Description
Bati-Cuisine est une application Java destinée aux professionnels de la construction et de la rénovation de cuisines. Elle permet de calculer le coût total des travaux en prenant en compte les matériaux et les coûts de main-d'œuvre. L'application offre également des fonctionnalités avancées telles que la gestion des clients, la génération de devis personnalisés et une vue d'ensemble des aspects financiers et logistiques des projets de rénovation.

### Fonctionnalités
Gestion de projet : Suivi des projets de rénovation.
Gestion des composants : Ajout et gestion des matériaux et de la main-d'œuvre.
Gestion des clients : Base de données des clients avec la possibilité de les ajouter, modifier ou supprimer.
Création de devis : Génération de devis personnalisés pour chaque projet.
Calcul des coûts : Calcul automatique des coûts totaux en fonction des matériaux et du temps de travail.
Affichage détaillé des résultats : Résumé détaillé des coûts et du projet.
Captures d'écran
(Ajouter ici des captures d'écran de l'interface utilisateur pour illustrer l'application)

### Prérequis techniques
Java 8 ou version supérieure
PostgreSQL pour la gestion de la base de données
Git pour le contrôle de version
JIRA pour la gestion de projet (optionnel)
Installation
Clonez le dépôt Git :
git clone https://github.com/allaouijihad/bati-cuisine.git
Assurez-vous que Java et PostgreSQL sont installés sur votre système.
Configurez la base de données PostgreSQL :
Créez une base de données nommée bati_cuisine.
Mettez à jour les informations de connexion dans le fichier de configuration.
Installez les dépendances nécessaires (pilote JDBC pour PostgreSQL).
Compilez et exécutez l'application.
Utilisation
Lancer l'application avec votre IDE ou via la ligne de commande.
Suivez les instructions à l'écran pour :
Créer un nouveau projet de rénovation.
Ajouter des clients, des matériaux et de la main-d'œuvre.
Générer des devis personnalisés pour vos projets de cuisine.
Dépendances
PostgreSQL JDBC Driver : Utilisé pour connecter l'application à la base de données PostgreSQL.
Architecture
Ce projet suit une architecture en couches :

Couche présentation : Interface utilisateur (console).
Couche service : Logique métier et traitement des données.
Design Patterns utilisés
Singleton : Pour garantir une unique instance de connexion à la base de données.
Repository Pattern : Pour la gestion des entités (projets, clients, composants) et les opérations CRUD.
Contribution
Les contributions sont les bienvenues ! Veuillez suivre ces étapes :

Fork le projet.
Créez une branche pour vos modifications :
bash
Copy code
git checkout -b ma-nouvelle-fonctionnalite
Soumettez vos modifications :
bash
Copy code
git commit -m "Ajout d'une nouvelle fonctionnalité"
Poussez la branche :
bash
Copy code
git push origin ma-nouvelle-fonctionnalite
