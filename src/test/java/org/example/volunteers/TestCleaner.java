package org.example.volunteers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestCleaner {

    // Integration test
    @Test
    public void shouldCallEachFieldSanitizerForEachVolunteers() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"));
        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();

        Cleaner.cleanUp(volunteers, fieldsSanitizer);

        Assertions.assertEquals(2, fieldsSanitizer.clearNameCount);
        Assertions.assertEquals(1, fieldsSanitizer.clearPhoneCount);
        Assertions.assertEquals(1, fieldsSanitizer.clearEmailCount);
    }

    // Unit tests
    @Test
    public void shouldRegroupMails() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("LEO", "duff", "duff", "leo.duff@voleur.twitch", "+330069696969"));
        volunteers.add(new Volunteer("leo", "DUFF", "duff", "leo.duff@voleur.dailmotion", "+330069696969"));

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb;leo.duff@voleur.twitch;leo.duff@voleur.dailmotion", "0069696969"));

        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();

        List<Volunteer> resultVolunteers = Cleaner.cleanUp(volunteers, fieldsSanitizer);

        Assertions.assertEquals(expectedVolunteers.toString(), resultVolunteers.toString());
    }

    @Test
    public void shouldRegroupNickNames() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("LEO", "duff", "miguel", "leo.duff@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("leo", "DUFF", "zera", "leo.duff@voleur.ytb", "+330069696969"));

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "duff;miguel;zera", "leo.duff@voleur.ytb", "0069696969"));

        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();

        List<Volunteer> resultVolunteers = Cleaner.cleanUp(volunteers, fieldsSanitizer);

        Assertions.assertEquals(expectedVolunteers.toString(), resultVolunteers.toString());
    }

    @Test
    public void shouldRegroupPhones() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("LEO", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("leo", "DUFF", "duff", "leo.duff@voleur.ytb", "+330069696968"));

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "0069696969;0069696968"));

        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();

        List<Volunteer> resultVolunteers = Cleaner.cleanUp(volunteers, fieldsSanitizer);

        Assertions.assertEquals(expectedVolunteers.toString(), resultVolunteers.toString());
    }

    @Test
    public void shouldRegroupEmptyNickNames() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("leo", "duff", "", "leo.duff@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("LEO", "duff", "miguel", "leo.duff@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("leo", "DUFF", "zera", "leo.duff@voleur.ytb", "+330069696969"));

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "miguel;zera", "leo.duff@voleur.ytb", "0069696969"));

        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();

        List<Volunteer> resultVolunteers = Cleaner.cleanUp(volunteers, fieldsSanitizer);

        Assertions.assertEquals(expectedVolunteers.toString(), resultVolunteers.toString());
    }

    @Test
    public void shouldRegroupPhonesOnNickName() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("", "", "roche", "example@voleur.ytb", "+330069696967"));
        volunteers.add(new Volunteer("", "", "miguel", "example@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("", "", "roche", "example@voleur.ytb", "+330069696968"));

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("", "", "roche", "example@voleur.ytb", "0069696967;0069696968"));
        expectedVolunteers.add(new Volunteer("", "", "miguel", "example@voleur.ytb", "0069696969"));

        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();

        List<Volunteer> resultVolunteers = Cleaner.cleanUp(volunteers, fieldsSanitizer);

        Assertions.assertEquals(expectedVolunteers.toString(), resultVolunteers.toString());
    }

    @Test
    public void shouldRegroupEmailsOnNickName() {
        List<Volunteer> volunteers = new ArrayList<>();
        volunteers.add(new Volunteer("", "", "roche", "example@voleur.ytb", "+330069696968"));
        volunteers.add(new Volunteer("", "", "miguel", "example@voleur.ytb", "+330069696969"));
        volunteers.add(new Volunteer("", "", "roche", "example@voleur.twitch", "+330069696968"));

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("", "", "roche", "example@voleur.ytb;example@voleur.twitch", "0069696968"));
        expectedVolunteers.add(new Volunteer("", "", "miguel", "example@voleur.ytb", "0069696969"));

        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();

        List<Volunteer> resultVolunteers = Cleaner.cleanUp(volunteers, fieldsSanitizer);

        Assertions.assertEquals(expectedVolunteers.toString(), resultVolunteers.toString());
    }

}
