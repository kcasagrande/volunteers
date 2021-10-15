package org.example.volunteers.utils;
import org.example.volunteers.model.CustomParser;
import org.example.volunteers.model.Person;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonParser implements CustomParser {

    @Override
    public Person parse(List<String[]> inputRaw) {
        return new Person("","","","");
    }
}
