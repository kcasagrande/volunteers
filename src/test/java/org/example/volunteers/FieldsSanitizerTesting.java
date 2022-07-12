package org.example.volunteers;

import org.example.volunteers.utils.FieldsSanitizer;

public class FieldsSanitizerTesting extends FieldsSanitizer {
    public Integer clearNameCount = 0;
    public Integer clearPhoneCount = 0;
    public Integer clearEmailCount = 0;

    public String clearName(String name) {
        clearNameCount += 1;
        return super.clearName(name);
    }

    public String clearPhone(String phone) {
        clearPhoneCount += 1;
        return super.clearPhone(phone);
    }

    public String clearEmail(String email) {
        clearEmailCount += 1;
        return super.clearEmail(email);
    }

}
