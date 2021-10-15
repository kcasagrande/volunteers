package org.example.volunteers.utils;
import org.example.volunteers.model.CustomParser;
import org.example.volunteers.model.Person;

import java.util.List;

public class PersonParser implements CustomParser {

    @Override
    public Person parse(List<String[]> inputRaw) {
        return new Person("","","","");
    }
}
