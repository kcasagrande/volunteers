package org.example.volunteers;

import org.example.volunteers.utils.FieldsSanitizer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFieldsSanitizerPhone {
    FieldsSanitizer fieldsSanitizer = new FieldsSanitizer();

    @Test
    public void spacesShouldBeRemoved() {
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("00 69 69 69 69"));
    }

    @Test
    public void shouldBeTenDigits() {
        Assertions.assertEquals("", fieldsSanitizer.clearPhone("00 69 69 69"));
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("0069696969"));
    }

    @Test
    public void indicatorShouldBeRemoved() {
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("+33069696969"));
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("+330069696969"));
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("+33(0)069696969"));
    }

    @Test
    public void specialCharsShouldBeRemoved() {
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("00-69-69-69-69"));
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("(00)69-69-69-69"));
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("(00)69.69.69.69"));
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("00.69.69.69.69"));
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("00,69,69,69,69"));
    }

    @Test
    public void shouldReturnOnlyDigits() {
        Assertions.assertEquals("0069696969", fieldsSanitizer.clearPhone("006r969y6969"));
        Assertions.assertTrue(fieldsSanitizer.clearPhone("0069696969").matches("\\d+"), "Should be Digits Only");
        Assertions.assertTrue(fieldsSanitizer.clearPhone("0069696969").matches("\\d+"), "Should be Digits Only");
    }

}
