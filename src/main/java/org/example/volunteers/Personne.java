package org.example.volunteers;


public class Personne {
    public String nom;
    public String prenom;
    public String pseudo;
    public String adresseMail;
    public String tel;

    public Personne(){}

    /**
     * Constructeur de base
     * @param tableau les champs remplis
     */
    public Personne(String[] tableau){
        nom = tableau[0];
        prenom = tableau[1];
        pseudo = tableau[2];
        adresseMail = tableau[3];
        tel = tableau[4]!=null ? tableau[4] : "" ;
    }

    /**
     * Va formater les string de la personnes
     */
    public void formater(){
        adresseMail = Utils.conversionMail(adresseMail);
        tel = Utils.conversionTel(tel);
    }

    /**
     * Regarde si pour un objet donné, l'objet en parametre est un doublon
     * @param personne personne qui est un doublon
     * @return si c'est un doublon
     */
    public boolean isDoublon(Personne personne){
        // on test uniquement pseudo
        if (this.pseudo != "" && this.pseudo.equals(personne.pseudo))
            return true;

        // on test soit email soit tel et nom/prenom egaux
        if ((this.tel != null ? this.tel.equals(personne.tel) : false) ||
                (this.adresseMail != null ? this.adresseMail.equals(personne.adresseMail) : false) ) {
            //On check si le nom/prénom n'ont pas été inversé
            if ( (this.prenom != "" && (this.prenom.equals(personne.prenom) || this.prenom.equals(personne.nom) ) ) ||
                    (this.nom != "" && (this.nom.equals(personne.prenom))) )
                return true;
        }

        return false;
    }

    /**
     * Complete l'objet personne en fonction du supposé doublon en parametre
     * @param personne doublon qui doit completer l'objet
     */
    public void complete(Personne personne){
        if( this.nom==null || this.nom.equals("")){
            this.nom = personne.nom;
        }
        if( this.prenom==null || this.prenom.equals("")){
            this.prenom = personne.prenom;
        }
        if( this.pseudo==null || this.pseudo.equals("")){
            this.prenom = personne.prenom;
        }
        if( this.adresseMail==null || this.adresseMail.equals("")){
            this.adresseMail = personne.adresseMail;
        }
        if( this.tel==null || this.tel.equals("")){
            this.tel = personne.tel;
        }
    }
}
