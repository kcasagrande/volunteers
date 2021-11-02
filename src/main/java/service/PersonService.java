package service;

import model.Person;
import model.PersonProperties;
import model.PhoneNumberPattern;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toCollection;

public class PersonService {
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

        List<Person> toDelete = new ArrayList<>();

        for (Person person : personList) {
            List<Person> listPerson = personList.stream().filter(w -> {
                return person.firstName.equals(w.firstName) && person.lastName.equals(w.lastName);
            }).collect(Collectors.toList());

            if (listPerson.size() > 1) {
                toDelete.add(person);
            }
        }
        personList.removeAll(toDelete);
        return personList;
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
    public List<Person> filterPersonDuplicateByPhoneNUmber(List<Person> personList) {
        List<Person> formattedPhoneNumberPerson = new ArrayList<>();
        personList.forEach(person -> {
            if (refactorPhoneNumber(person.getPhoneNumber()).length() == 8) {
                person.setPhoneNumber("00" + refactorPhoneNumber(person.getPhoneNumber()));
            }
            if (refactorPhoneNumber(person.getPhoneNumber()).length() == 12) {
                person.setPhoneNumber(refactorPhoneNumber(person.getPhoneNumber()).replaceFirst("00", ""));
            }
            person.setPhoneNumber(refactorPhoneNumber(person.getPhoneNumber()));
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

    /**
     * Formatted a given phone number
     *
     * @param phoneNumber phone number to be formated
     * @return String phone number
     */
    public String refactorPhoneNumber(String phoneNumber) {
        //+33(0)0.75.55.99.79
        if (phoneNumber.matches(PhoneNumberPattern.pattern1.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, "\\.");
        }

        //+33(0)0-75-55-55-20
        if (phoneNumber.matches(PhoneNumberPattern.pattern2.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, "\\-");
        }

        //+33055587491
        if (phoneNumber.matches(PhoneNumberPattern.pattern3.toString())) {
            return phoneNumber.replace("+33", "0");
        }

        //+33(0)0 85 55 67 37
        if (phoneNumber.matches(PhoneNumberPattern.pattern4.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, " ");
        }

        //+33(0)000555091
        if (phoneNumber.matches(PhoneNumberPattern.pattern5.toString())) {
            return phoneNumber.replace("+33(0)", "0");
        }

        //+330 00 55 52 25
        if (phoneNumber.matches(PhoneNumberPattern.pattern6.toString())) {
            return "00" + this.refactorPhoneNumberForSeparator(phoneNumber, " ");
        }

        //+330-55-55-66-33
        if (phoneNumber.matches(PhoneNumberPattern.pattern7.toString())) {
            return this.refactorPhoneNumberForSeparator(phoneNumber, "\\-");
        }

        //+330.00.55.52.42
        if (phoneNumber.matches(PhoneNumberPattern.pattern8.toString())) {
            return "00" + this.refactorPhoneNumberForSeparator(phoneNumber, "\\.");
        }

        //00 00 55 55 33
        if (phoneNumber.matches(PhoneNumberPattern.pattern9.toString())) {
            return phoneNumber.replace(" ", "");
        }

        //00-35-55-85-21
        if (phoneNumber.matches(PhoneNumberPattern.pattern10.toString())) {
            return phoneNumber.replace("-", "");
        }

        //00.45.55.63.57
        if (phoneNumber.matches(PhoneNumberPattern.pattern11.toString())) {
            return phoneNumber.replace(".", "");
        }

        //0000555302
        if (phoneNumber.matches(PhoneNumberPattern.pattern12.toString())) {
            return phoneNumber;
        }


        return phoneNumber;
    }

    private String refactorPhoneNumberForSeparator(String phoneNumber, String regexSeparator) {
        StringBuilder basedPhoneNumber = new StringBuilder("00");
        for (String s : phoneNumber.split(regexSeparator)) {
            if (s.length() == 2 && !s.equals("00")) {
                basedPhoneNumber.append(s);
            }
        }
        return basedPhoneNumber.toString();
    }
}
