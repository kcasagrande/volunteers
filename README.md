# Yuck Fnov

Members :

- CAILLON Antonin
- GENDRE Jérémy
- PELCAT Martin

## Table of content

- [CleanerTest](#CleanerTest)
    - [testCleanNotContactable](#testCleanNotContactable)
    - [testCleanDuplicates](#testCleanDuplicates)
    - [testCleanDuplicatesEmailsNumber](#testCleanDuplicatesEmailsNumber)
    - [testCleanDuplicatesPhonesNumber](#testCleanDuplicatesPhonesNumber)
- [StringUtilsTest](#StringUtilsTest)
    - [testHasJustOneNull](#testHasJustOneNull)
    - [testBothStringsAreNull](#testBothStringsAreNull)
    - [testFormatName](#testFormatName)
- [VolunteerTest](#VolunteerTest)
    - [testIsSamePersonTrue](#testIsSamePersonTrue)
    - [testIsSamePersonFalse](#testIsSamePersonFalse)
    - [testMergeVolunteerIdentities](#testMergeVolunteerIdentities)

## CleanerTest

### testCleanNotContactable

| Méthode testée | `Cleaner.cleanNotContactable`             |
|----------------|-------------------------------------------|
| Intérêt        | On teste si le volontaire est contactable |

<hr/>

### testCleanDuplicates

| Méthode testée | `Cleaner.cleanDuplicates`                                               |
|----------------|-------------------------------------------------------------------------|
| Intérêt        | On teste si deux volontaires identiques sont réunis en une seule entrée |

<hr/>

### testCleanDuplicatesEmailsNumber

| Méthode testée | `Cleaner.cleanDuplicates`                                     |
|----------------|---------------------------------------------------------------|
| Intérêt        | On teste si deux volontaires identiques ont leur mails réunis |

<hr/>

### testCleanDuplicatesPhonesNumber

| Méthode testée | `Cleaner.cleanDuplicates`                                                   |
|----------------|-----------------------------------------------------------------------------|
| Intérêt        | On teste si deux volontaires identiques ont leur numéro de téléphone réunis |

<hr/>

## StringUtilsTest

### testHasJustOneNull

| Méthode testée | `StringUtils.hasJustOneNull`                      |
|----------------|---------------------------------------------------|
| Intérêt        | On teste si uniquement une des entrées est `null` |

<hr/>

### testBothStringsAreNull

| Méthode testée | `StringUtils.bothStringsAreNull`         |
|----------------|------------------------------------------|
| Intérêt        | On teste si les deux entrées sont `null` |

<hr/>

### testFormatName

| Méthode testée | `StringUtils.formatNames`                              |
|----------------|--------------------------------------------------------|
| Intérêt        | On teste si le formattage du nom et prénom est correct |

<hr/>

### testFormatEmailNull

| Méthode testée | `StringUtils.formatEmail`                                                    |
|----------------|------------------------------------------------------------------------------|
| Intérêt        | On teste si le formattage de l'email renvoit bien `null` dans les cas testés |

<hr/>

### testFormatEmailNotNull

| Méthode testée | `StringUtils.formatEmail`                                                 |
|----------------|---------------------------------------------------------------------------|
| Intérêt        | On teste si le formattage de l'email n'est pas `null` dans les cas testés |

<hr/>

### testFormatPhone

| Méthode testée | `StringUtils.formatPhone`                                                                         |
|----------------|---------------------------------------------------------------------------------------------------|
| Intérêt        | On teste si le formattage du téléphone renvoit bien la bonne valeur formattée dans les cas testés |

<hr/>

### testFormatPhoneNull

| Méthode testée | `StringUtils.formatPhone`                                                      |
|----------------|--------------------------------------------------------------------------------|
| Intérêt        | On teste si le formattage du téléphone renvoit bien `null` dans les cas testés |

<hr/>

## VolunteerTest

### testIsSamePersonTrue

| Méthode testée | `Volunteer.isSamePerson`                                                 |
|----------------|--------------------------------------------------------------------------|
| Intérêt        | On teste si les personnes testées sont bien considérées comme identiques |

<hr/>

### testIsSamePersonFalse

| Méthode testée | `Volunteer.isSamePerson`                                                  |
|----------------|---------------------------------------------------------------------------|
| Intérêt        | On teste si les personnes testées sont bien considérées comme différentes |

<hr/>

### testMergeVolunteerIdentities

| Méthode testée | `Volunteer.mergeVolunteerIdentities` |
|----------------|-------------------------------------|
| Intérêt        | On teste le merge entre 2 personnes |

<hr/>