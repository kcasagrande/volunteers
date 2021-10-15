package org.example.volunteers.utils;

import org.example.volunteers.model.Person;

public class PersonParser implements CustomParser {

    @Override
    public Person parse(List<String[]> inputRaw) {
        return new Person("","","","");
    }
}
