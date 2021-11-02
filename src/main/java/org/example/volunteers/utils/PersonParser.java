package org.example.volunteers.utils;

import org.example.volunteers.model.CustomParser;
import org.example.volunteers.model.Person;
import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.List;

/**
 * Parser to parse persons
 */
public class PersonParser implements CustomParser {

    /**
     * Parse input tab of string into list of persons
     * @param inputRaw List of tab string to parse, from .csv file
     * @return return list of persons
     */
    @Override
    public List<Person> parse(List<String[]> inputRaw) {
        return inputRaw
                .stream()
                .map(raw -> Arrays
                        .stream(raw)
                        .map(String::toLowerCase)
                        .collect(Collectors.toList())
                )
                .map(entry -> new Person(entry.get(0), entry.get(1), entry.get(2), entry.get(3), entry.get(4)))
                .map(person -> person.setPhoneNumber(PhoneNumberFormatter.format(person.getPhoneNumber())))
                .collect(Collectors.toList());
    }
}
