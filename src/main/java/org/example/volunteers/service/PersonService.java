package org.example.volunteers.service;

import org.example.volunteers.model.Person;
import org.example.volunteers.model.PersonProperties;
import org.example.volunteers.utils.StringUtil;
import org.example.volunteers.model.PhoneNumberPattern;

import java.util.*;
import java.util.regex.Pattern;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class PersonService {
    private static final StringUtil stringUtil = new StringUtil();

    public List<Person> transformInPersonObject(List<Map<PersonProperties, String>> listMap) {
        List<Person> listPersonWDuplicate = new ArrayList<>();

        for (int i = 0; i < listMap.size(); i++) {
            Person person = new Person();

            person.id = i + 1;
            person.lastName = listMap.get(i).get(PersonProperties.lastName);
            person.firstName = listMap.get(i).get(PersonProperties.firstName);
            person.userName = listMap.get(i).get(PersonProperties.userName);
            person.email = listMap.get(i).get(PersonProperties.email);
            person.phoneNumber = listMap.get(i).get(PersonProperties.phoneNumber);

            listPersonWDuplicate.add(person);
        }
        return listPersonWDuplicate;
    }

    public List<Person> listSortByName(List<Person> personList) {

        List<Person> finalListe = new ArrayList<>();

        for (Person person : personList) {

            if(!finalListe.stream().map(Person::getLastName).anyMatch(person.lastName::equals) && !finalListe.stream().map(Person::getFirstName).anyMatch(person.firstName::equals)) {
                finalListe.add(person);
            }else if (person.firstName.equals("") && person.lastName.equals("")){
                finalListe.add(person);
            }

        }
        return finalListe;
    }

    /**
     * Returns filtered list of persons without duplicated emails according to variant emails
     *
     * @param personList list of persons to filter
     * @return list of persons without duplicated emails
     */
    public List<Person> filterPersonDuplicateByEmail(List<Person> personList) {
        List<Person> personListWithoutDuplicate = new ArrayList<>();
        personList.forEach(person -> {
            String email = person.getEmail();
            if (!email.equals("")) {
                List<String> emailVariants = generateEmailVariants(email);
                emailVariants.add(email);
                if (personListWithoutDuplicate.stream()
                        .map(Person::getEmail)
                        .noneMatch(new HashSet<>(emailVariants)::contains)
                        && personListWithoutDuplicate.stream()
                        .map(Person::getSplitEmail)
                        .noneMatch(new HashSet<>(emailVariants)::contains)) {
                    personListWithoutDuplicate.add(person);
                }
            } else {
                personListWithoutDuplicate.add(person);
            }
        });
        return personListWithoutDuplicate;
    }

    /**
     * Generates email variants with . and _ in email first part. Also removes extension
     *
     * @param email base person email
     * @return list of variant emails
     */
    public List<String> generateEmailVariants(String email) {
        List<String> emailVariants = new ArrayList<>();
        String splitEmail = email.split("@")[0];
        int lastDotIndex = email.lastIndexOf('.');
        if (splitEmail.contains(".")) {
            emailVariants.add(email.replaceFirst(Pattern.quote("."), "_"));
            emailVariants.add(email.replaceFirst(Pattern.quote("."), ""));
            emailVariants.add(new String[]{email.substring(0, lastDotIndex), email.substring(lastDotIndex)}[0].replaceFirst(Pattern.quote("."), ""));
            emailVariants.add(new String[]{email.substring(0, lastDotIndex), email.substring(lastDotIndex)}[0].replaceFirst(Pattern.quote("."), "_"));
        }
        if (splitEmail.contains("_")) {
            emailVariants.add(email.replaceFirst(Pattern.quote("_"), "."));
            emailVariants.add(email.replaceFirst(Pattern.quote("_"), ""));
            emailVariants.add(new String[]{email.substring(0, lastDotIndex), email.substring(lastDotIndex)}[0].replaceFirst(Pattern.quote("_"), ""));
            emailVariants.add(new String[]{email.substring(0, lastDotIndex), email.substring(lastDotIndex)}[0].replaceFirst(Pattern.quote("_"), "."));
        }

        if (!emailVariants.contains(new String[]{email.substring(0, lastDotIndex), email.substring(lastDotIndex)}[0])) {
            emailVariants.add(new String[]{email.substring(0, lastDotIndex), email.substring(lastDotIndex)}[0]);
        }
        return emailVariants;
    }


    /**
     * Filter list of person, delete duplicates and refactor phone numbers
     *
     * @param personList person list
     * @return list of filtered person by phone number
     */
    public List<Person> filterPersonDuplicateByPhoneNumber(List<Person> personList) {
        List<Person> formattedPhoneNumberPerson = new ArrayList<>();
        personList.forEach(person -> {
            if (stringUtil.refactorPhoneNumberString(person.getPhoneNumber()).length() == 8) {
                person.setPhoneNumber("00" + stringUtil.refactorPhoneNumberString(person.getPhoneNumber()));
            }
            if (stringUtil.refactorPhoneNumberString(person.getPhoneNumber()).length() == 12) {
                person.setPhoneNumber(stringUtil.refactorPhoneNumberString(person.getPhoneNumber()).replaceFirst("00", ""));
            }
            person.setPhoneNumber(stringUtil.refactorPhoneNumberString(person.getPhoneNumber()));
            formattedPhoneNumberPerson.add(person);
        });
        List<Person> emptyPhoneNumberList = new ArrayList<>();
        formattedPhoneNumberPerson.forEach(person -> {
            if (person.getPhoneNumber().equals("")) {
                emptyPhoneNumberList.add(person);
            }
        });
        List<Person> formattedPhoneNumberPersonWithEmpty = formattedPhoneNumberPerson.stream()
                .collect(collectingAndThen(toCollection(() -> new TreeSet<>(comparing(Person::getPhoneNumber))),
                        ArrayList::new));
        formattedPhoneNumberPersonWithEmpty.addAll(emptyPhoneNumberList);
        return formattedPhoneNumberPersonWithEmpty;
    }


}
