package org.example.volunteers;

import org.example.volunteers.services.Validations;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationsEmailTest {

    @ParameterizedTest
    @ValueSource(strings = {"email@gmail.com" , "emaidsqkfnsd@gmail.eu" ,"test@test.fr"})
    public void testEmailGood(String email){
        Validations v = new Validations();
        assertTrue(v.validateEmailAddress(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"ema@il@gmail.com" , "email@gmail" , "email" , ""})
    public void testEmailBad(String email){
        Validations v = new Validations();
        assertFalse(v.validateEmailAddress(email));
    }
}
