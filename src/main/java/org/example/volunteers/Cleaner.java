package org.example.volunteers;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Cleaner {
    public static List<Volunteer> cleanUp(List<Volunteer> volunteers) {
        // This function should contain your dark magic.
        // For now, it simply returns a copy of the initial list.
        return volunteers.stream().distinct().collect(Collectors.toList());
    }

    public static String cleanPhoneNumber(String phone){
        String regex = "([+][0-9]{2})|([-.\\s])|[()]";
        phone = phone.replaceAll( regex, "");
        //System.out.println(phone);

        if (phone.length() == 10) {
            phone = phone;
        }
        else
        {
            phone = phone + "NaN";
        }

        return phone;
    }
}
