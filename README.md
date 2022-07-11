# VOLUNTEERS
## Groupe : Emeline, Matisse, Dimitri, Antoine B

## Besoin: sanitize les données pour les exploiter 

## Tâches :
- Lire un fichier CSV
- Valider les 

- Vérifier le nombre d’entrée par ligne
- Vérifier si il y a un mail
- Vérifier si il y a un numéro de téléphone
- Vérifier si il n’y a pas de doublon
    - Par mail
    - Par numéro
- Formater les numéros
- Vérifier si nom et prénom

## Sortie des données:
Séparer en degrés de confiance:
- Niveau 1 (donnée valide 100%): on prend juste les personnes du CSV avec les données valides et complètes sans doublon
- Niveau 2 (manque un contact): Comme au dessus mais un des champs de contact est manquant (Tel, Mail)
- Niveau 3 (Nom complet invalide): Nom prénom manquant mais contact valide 
- Niveau 4 (A completer): Profil a completer mais on a un des contact.
- Niveau 5 (invalide) : fichier poubelle avec les restes