# Volunteers
### Consignes
Écrire les tests 

clarifier la liste 
il y a des doublons / multiples 
se rapprocher de la réalité 
pas de nombre exact ( toujours des doublons ) => pas de perte d'info 

si même personne avec multiples infos => garder les différentes infos 

Fusionner les doublons si possible

tests plus important le besoin c'est pas prioritaire

Comparer des entrées pour éliminer les doublons (sans perdre de l'information)
- Itérer les utilisateurs => test iteration
  - Récuperer un champ 
  - Comparer le champ (maitre)
    - email
      - comparer extension (.org/.fr) => test comparaison extension 
        - comparaison string => test comparaison string
      - telephone
        - format phone number => test format phone number
        - Si correspondance, comparer les autres champs
          - Marge d'erreur sur comparaison => tests de marge d'erreur
          - extension sur les emails
