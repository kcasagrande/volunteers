package org.example.volunteers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * Change le telephone en entrée pour un truc correct
     * @param tel tel entrée
     * @return tel correct
     */
    public static String conversionTel(String tel){
        if (tel == null)
        {
            return "";
        }
        tel = tel.replaceAll("[^0-9]", ""); // on retire tous les caractères sauf les chiffres
        if(tel.length() > 6)
        {
            if(!"0".equals(tel.substring(0,1)))  // si le premier chiffre c'est pas 0 (+33..), on ajoute 00 au debut
            {
                tel = "00" + tel;
            }
            if("0033".equals(tel.substring(0,4)))  // si le numéro commence par 00.33.6.12.... on met au format 06.12.....
            {
                tel = "0" + tel.substring(4);
            }
            // dans tous les cas on met au format 02.35.63.36.54 ou 06.58.56.54.56 si 0033658565456
            if(tel.length() > 10)
            {
                if (tel.startsWith("001")){ // Gestion des numéros americains
                    return tel.replaceAll("(\\d{2})(\\d{1})(\\d{3})(\\d{3})(\\d{4})","$1.$2.$3.$4.$5");
                }
                return tel.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1.$2.$3.$4.$5.$6");
            }
            return tel.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1.$2.$3.$4.$5");
        }
        return "";
    }

    /**
     * Conversion de mail pour check si c'est un mail correct
     * @param mail mail d'entré
     * @return mail correct ou null
     */
    public static String conversionMail(String mail){
        if(mail == null) return null;
        Pattern patternMail = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
        Matcher mat = patternMail.matcher(mail);

        if(mat.matches()){ // Valid mail
            return mail;
        }else{ // Invalid mail
            return "";
        }
    }
}
