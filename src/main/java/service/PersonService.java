package service;

import model.Person;
import model.PersonProperties;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class PersonService {
    public List<Person> getListPersonWDuplicate(List<Map<PersonProperties, String>> listMap) {
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
                if (person.firstName.equals(w.firstName) && person.lastName.equals(w.lastName)) {
                    return true;
                }
                return false;
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
                        .noneMatch(
                                new HashSet<>(emailVariants)
                                        ::contains)) {
                    personListWithoutDuplicate.add(person);
                }
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
    private List<String> generateEmailVariants(String email) {
        List<String> emailVariants = new ArrayList<>();
        String splitEmail = email.split("@")[0];
        if (splitEmail.contains(".")) {
            emailVariants.add(email.replaceFirst(Pattern.quote("."), "_"));
            emailVariants.add(email.replaceFirst(Pattern.quote("."), ""));
        }
        if (splitEmail.contains("_")) {
            emailVariants.add(email.replaceFirst(Pattern.quote("_"), "."));
            emailVariants.add(email.replaceFirst(Pattern.quote("_"), ""));
        }
        int i = email.lastIndexOf('.');
        emailVariants.add(new String[]{email.substring(0, i), email.substring(i)}[0]);
        return emailVariants;
    }
}
