# volunteers : filtrage de données 

Pour recruter des volontaires dans une association, on utilise normalement un formulaire mal fait.
On a donc des données utilisateurs qui peuvent parfois être erronées (fichier CSV) : parfois les noms/prénoms sont inversés, les demandes sont dupliquées, les mails sont invalides etc..

Le client veut une liste de personnes avec de bonnes données tiré de cette liste : 
- nom, prénom, mail, téléphone, identifiant / pseudo

Le but n'est pas de recréer un formulaire qui fonctionne mieux, mais de traiter les données. Les tests sont prioritaires.

Liste des tests à réaliser : 
- [X] Le fichier output doit bien se générer à l'endroit espéré 
- [X] Le fichier doit bien contenir au moins une ligne
- [X] Filtrer un nom
- [X] Filtrer un prénom
- [X] Filtrer un pseudo
- [x] Filtrer un téléphone
- [x] Filtrer un mail
- [x] Filtrer les doublons
- [x] Filtrer les lignes vides
