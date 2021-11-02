package model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {
    public int id;

    public String firstName;

    public String lastName;

    public String userName;

    public String email;

    public String phoneNumber;

    public String getSplitEmail() {
        int i = email.lastIndexOf('.');
        return new String[]{email.substring(0, i), email.substring(i)}[0];
    }
}