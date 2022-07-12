package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CleanerTest {
    @Test
    public void removeAccents() {
        List<Volunteer> volunteersA = new ArrayList<>();
        volunteersA.add(new Volunteer("Éric", "Doé", "jojoé", "johné@mail.com", "+3369867543é"));

        List<Volunteer> result = Cleaner.removeAccents(volunteersA);

        assertEquals(result.get(0).firstName, "Eric", "Les accents doivent être remplacés par des caractères classiques dans les prénoms");
        assertEquals(result.get(0).lastName, "Doe", "Les accents doivent être remplacés par des caractères classiques dans les noms");
        assertEquals(result.get(0).nickName, "jojoe", "Les accents doivent être remplacés par des caractères classiques dans les pseudos");
        assertEquals(result.get(0).eMail, "johné@mail.com", "Les accents ne doivent pas être remplacés par des caractères classiques dans les emails");
        assertEquals(result.get(0).phone, "+3369867543é", "Les accents ne doivent pas être remplacés par des caractères classiques dans les téléphones");
    }

    @Test
    public void emailInsteadOfPhone() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("doe", "john", "jojo2", "+33698675434", "john@mail.com"));
        volunteers.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675487"));

        List<Volunteer> result = Cleaner.sanitizeEmailInsteadOfPhone(volunteers);

        List<Volunteer> resultExpected = new ArrayList<>();
        resultExpected.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675434"));
        resultExpected.add(new Volunteer("doe", "john", "jojo2", "john@mail.com", "+33698675487"));

        assertEquals(resultExpected.toString(), result.toString(), "Les adresses mail mis à la place des téléphones doivent être changé et remis à leur place");
    }
}
