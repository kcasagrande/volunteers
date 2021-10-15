package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
}
