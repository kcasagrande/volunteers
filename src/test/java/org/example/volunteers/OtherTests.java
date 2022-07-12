package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OtherTests {
    @Test
    public void contactEmpty() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "Null", "Null");
        List<Volunteer> input = new ArrayList<Volunteer>() {{
            add(vol1);
        }};
        List<Volunteer> result = Cleaner.cleanNoPhoneNoEmail(input);
        List<Volunteer> expected = new ArrayList<Volunteer>(){{
            add(vol1);
        }};

        assertEquals(expected, result,"Si un volontaire n'a pas d'email et de numéro de téléphone on le renvoit");
    }

    @Test
    public void contactEmptyMailNotEmpty() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "johnny@gmail.com", "Null");
        List<Volunteer> input = new ArrayList<Volunteer>() {{
            add(vol1);
        }};
        List<Volunteer> result = Cleaner.cleanNoPhoneNoEmail(input);
        List<Volunteer> expected = new ArrayList<Volunteer>();

        assertEquals(expected, result,"Si un volontaire a un email mais pas de numéro de téléphone on ne le renvoit pas");
    }

    @Test
    public void contactEmptyPhoneNotEmpty() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "Null", "0601423598");
        List<Volunteer> input = new ArrayList<Volunteer>() {{
            add(vol1);
        }};
        List<Volunteer> result = Cleaner.cleanNoPhoneNoEmail(input);
        List<Volunteer> expected = new ArrayList<Volunteer>();

        assertEquals(expected, result,"Si un volontaire a un numéro mais pas d'email on ne le renvoit pas");
    }
}
