package org.example.volunteers;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public String firstName;
    public String lastName;
    public String nickName;
    public String phone;
    public Integer level;
    public String email;

    public Volunteer(
        String firstName,
        String lastName,
        String nickName,
        String email,
        String phone
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName, email,phone, String.valueOf(level)})
            .map(attribute -> String.format("\"%s\"", attribute))
            .collect(joining(";"));
    }

    public boolean hasValidPhoneNumber() {
        if ( this.phone == null || this.phone == "" ) return false;
        return this.phone.matches("^\\+[1-9]\\d{1,14}$");
    }

    public boolean hasValidMail() {
        if ( this.email == null || this.email == "" ) return false;
        return this.email.matches("(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))");
    }

    public boolean hasFullName() {
        return this.firstName != null  && this.lastName != null && this.firstName != ""  && this.lastName != "";
    }

    public void formatPhoneNumber() {
        String phoneNumber = this.phone;

        Pattern pattern = Pattern.compile("-| |\\.|\\(|\\)", Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(phoneNumber);
        String formatedPhoneNumber = matcher.replaceAll("");

        if (formatedPhoneNumber.matches("/^\\+33/gm")) {
            if (!formatedPhoneNumber.matches("/^33/gm")){
                formatedPhoneNumber = "33" + formatedPhoneNumber;
            }
            if (!formatedPhoneNumber.matches("/^\\+/gm")) {
                formatedPhoneNumber = "+" + formatedPhoneNumber;
            }
        }
        this.phone = formatedPhoneNumber;
    }

    public void formatFirstName() {
        int ctr = 0;
        int n = this.firstName.length();
        char ch[ ] = this.firstName.toLowerCase().toCharArray();
        int c = 0;
        for (int i = 0; i < n; i++){
            if(i == 0)
                ch[i] = Character.toUpperCase(ch[i]);
            if (ch[i] == ' ' || ch[i] == '-')
            {
                ctr++;
                ch[ i + 1 ] = Character.toUpperCase(ch[i + 1]);
                continue;
            }
            else
                ch[c++] = ch[i];
        }
        this.firstName =  String.valueOf(ch,0,n - ctr);
    }

    public void formatLastName() {
        int ctr = 0;
        int n = this.lastName.length();
        char ch[ ] = this.lastName.toLowerCase().toCharArray();
        int c = 0;
        for (int i = 0; i < n; i++){
            if(i == 0)
                ch[i] = Character.toUpperCase(ch[i]);
            if (ch[i] == ' ' || ch[i] == '-')
            {
                ctr++;
                ch[ i + 1 ] = Character.toUpperCase(ch[i + 1]);
                continue;
            }
            else
                ch[c++] = ch[i];
        }
        this.lastName =  String.valueOf(ch,0,n - ctr);
    }

    public void formatEmail() {
        this.email = this.email.toLowerCase();
    }

    public void format() {
        if(!this.hasValidMail() && this.email != null && this.email != "") {
            formatEmail();
        }

        if(!this.hasValidPhoneNumber() && this.phone != null && this.phone != "") {
            formatPhoneNumber();
        }

        if(this.firstName != null && this.firstName != "") {
            formatFirstName();
        }

        if(this.lastName != null && this.lastName != "") {
            formatLastName();
        }
    }
}
