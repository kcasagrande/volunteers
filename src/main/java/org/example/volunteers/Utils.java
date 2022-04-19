package org.example.volunteers;

import java.io.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    private static final String SEPARATOR = " , ";
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);



    public static String convertPhone(String phone){
        // Format souhaité 0000000000
        if (phone == null || phone.equalsIgnoreCase(""))
        {
            return "";
        }

        if(phone.length() < 10) {
            return "";
        }
        phone = phone.replaceAll("[^0-9]", ""); // Remove all special characters

        if(phone.length() > 12) {
            return "";
        }


        if(phone.startsWith("33")) {
            phone = phone.substring(2);
            phone = "0" + phone;

            if(phone.length() > 10 && phone.startsWith("00")) {
                phone = phone.substring(1);
            }
        }

        phone = phone.replaceAll("(\\d{2})(\\d{2})(\\d{2})(\\d{2})(\\d{2})", "$1.$2.$3.$4.$5");

        return phone;
    }

    public static String validateEmail(String email) {
        if (email != null ){
            email = email.toLowerCase();
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
            if (matcher.find()){
                //System.out.println("Email OK");
            } else {
                //System.out.println("Email not OK");
            }
            return email;
        } else {
            return "";
        }
    }


    public static boolean checkIfUserHaveManyEmail(String email){
        if (email != null && email != ""){
            if (email.contains(SEPARATOR)){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }


    public static List<String> getListOfUserEmailFromString(String email){
        List<String> listOfEmail;
        listOfEmail = Arrays.asList(email.split(SEPARATOR));

        return listOfEmail;
    }

}
