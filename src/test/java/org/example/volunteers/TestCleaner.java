package org.example.volunteers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestCleaner {

    // Integration test
    @Test
    public void shouldCallEachFieldSanitizerForEachVolunteers() {
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
        addVolunteers(
                new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"),
                new Volunteer("LEO", "duff", "duff", "leo.duff@voleur.twitch", "+330069696969"),
                new Volunteer("leo", "DUFF", "duff", "leo.duff@voleur.dailmotion", "+330069696969")
        );

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb;leo.duff@voleur.twitch;leo.duff@voleur.dailmotion", "0069696969"));

        whenCleaningVolunteers();
        thenExpectedListIs(expectedVolunteers);
    }

    @Test
    public void shouldRegroupNickNames() {
        addVolunteers(
                new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"),
                new Volunteer("LEO", "duff", "miguel", "leo.duff@voleur.ytb", "+330069696969"),
                new Volunteer("leo", "DUFF", "zera", "leo.duff@voleur.ytb", "+330069696969")
        );

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "duff;miguel;zera", "leo.duff@voleur.ytb", "0069696969"));

        whenCleaningVolunteers();
        thenExpectedListIs(expectedVolunteers);
    }

    @Test
    public void shouldRegroupPhones() {
        addVolunteers(
                new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"),
                new Volunteer("LEO", "duff", "duff", "leo.duff@voleur.ytb", "+330069696969"),
                new Volunteer("leo", "DUFF", "duff", "leo.duff@voleur.ytb", "+330069696968")
        );

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "duff", "leo.duff@voleur.ytb", "0069696969;0069696968"));

        whenCleaningVolunteers();
        thenExpectedListIs(expectedVolunteers);
    }

    @Test
    public void shouldRegroupEmptyNickNames() {
        addVolunteers(
                new Volunteer("leo", "duff", "", "leo.duff@voleur.ytb", "+330069696969"),
                new Volunteer("LEO", "duff", "miguel", "leo.duff@voleur.ytb", "+330069696969"),
                new Volunteer("leo", "DUFF", "zera", "leo.duff@voleur.ytb", "+330069696969")
        );

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("leo", "duff", "miguel;zera", "leo.duff@voleur.ytb", "0069696969"));

        whenCleaningVolunteers();
        thenExpectedListIs(expectedVolunteers);
    }

    @Test
    public void shouldRegroupPhonesOnNickName() {
        addVolunteers(
                new Volunteer("", "", "roche", "example@voleur.ytb", "+330069696967"),
                new Volunteer("", "", "miguel", "example@voleur.ytb", "+330069696969"),
                new Volunteer("", "", "roche", "example@voleur.ytb", "+330069696968")
        );

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("", "", "roche", "example@voleur.ytb", "0069696967;0069696968"));
        expectedVolunteers.add(new Volunteer("", "", "miguel", "example@voleur.ytb", "0069696969"));

        whenCleaningVolunteers();
        thenExpectedListIs(expectedVolunteers);
    }

    @Test
    public void shouldRegroupEmailsOnNickName() {
        addVolunteers(
                new Volunteer("", "", "roche", "example@voleur.ytb", "+330069696968"),
                new Volunteer("", "", "miguel", "example@voleur.ytb", "+330069696969"),
                new Volunteer("", "", "roche", "example@voleur.twitch", "+330069696968")
        );

        List<Volunteer> expectedVolunteers = new ArrayList<>();
        expectedVolunteers.add(new Volunteer("", "", "roche", "example@voleur.ytb;example@voleur.twitch", "0069696968"));
        expectedVolunteers.add(new Volunteer("", "", "miguel", "example@voleur.ytb", "0069696969"));

        whenCleaningVolunteers();
        thenExpectedListIs(expectedVolunteers);
    }

    private void addVolunteers(Volunteer... volunteers) {
        Collections.addAll(this.volunteers, volunteers);
    }

    private void whenCleaningVolunteers() {
        FieldsSanitizerTesting fieldsSanitizer = new FieldsSanitizerTesting();
        cleared = Cleaner.cleanUp(volunteers, fieldsSanitizer);
    }

    private void thenExpectedListIs(List<Volunteer> expected) {
        Assertions.assertEquals(expected.toString(), cleared.toString());
    }

    private final List<Volunteer> volunteers = new ArrayList<>();
    private List<Volunteer> cleared = new ArrayList<>();

}
