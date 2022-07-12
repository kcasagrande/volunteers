import org.example.volunteers.Cleaner;
import org.example.volunteers.Volunteer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Stream;


import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");

        List<Volunteer> inputVolunteers = Files.readAllLines(Paths.get(args[0])).stream()
                .map(string -> Arrays.stream(string.split(";", -1))
                        .map(token -> quotes.matcher(token).replaceAll("$1"))
                        .collect(toList()))
                .map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
                .collect(toList());
        List<Volunteer> outputVolunteers = Cleaner.cleanUp(inputVolunteers);
        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));
        outputVolunteers.forEach(writer::println);
        writer.close();
    }

    public static Boolean ensureFieldIsNotEmpty(String field) {
        return field.isEmpty();
    }

    public static Boolean ensureMailIsValidFormat(String emailField) {
        String regexPatternEmail = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regexPatternEmail);
        Matcher matcher = pattern.matcher(emailField);
        return matcher.matches();
    }

    public static List<Volunteer> findDuplicatesByFirstname(List<Volunteer> volunteers) {
        List<Volunteer> duplicates = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            for (int j = i + 1; j < volunteers.size(); j++) {
                if (volunteers.get(i).firstName.equals(volunteers.get(j).firstName)) {
                    duplicates.add(volunteers.get(i));
                    duplicates.add(volunteers.get(j));
                }
            }
        }
        return duplicates;
    }

    public static List<Volunteer> findDuplicatesByLastname(List<Volunteer> volunteers) {
        List<Volunteer> duplicates = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            for (int j = i + 1; j < volunteers.size(); j++) {
                if (volunteers.get(i).lastName.equals(volunteers.get(j).lastName)) {
                    duplicates.add(volunteers.get(i));
                    duplicates.add(volunteers.get(j));
                }
            }
        }
        return duplicates;
    }

    public static List<Volunteer> findDuplicatesByEmail(List<Volunteer> volunteers) {
        List<Volunteer> duplicates = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            for (int j = i + 1; j < volunteers.size(); j++) {
                if (volunteers.get(i).eMail.equals(volunteers.get(j).eMail)) {
                    duplicates.add(volunteers.get(i));
                    duplicates.add(volunteers.get(j));
                }
            }
        }
        return duplicates;
    }

    public static List<Volunteer> findDuplicatesByPhone(List<Volunteer> volunteers) {
        List<Volunteer> duplicates = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            for (int j = i + 1; j < volunteers.size(); j++) {
                if (volunteers.get(i).phone.equals(volunteers.get(j).phone)) {
                    duplicates.add(volunteers.get(i));
                    duplicates.add(volunteers.get(j));
                }
            }
        }
        return duplicates;
    }

    public static List<Volunteer> findDuplicatesByNickname(List<Volunteer> volunteers) {
        List<Volunteer> duplicates = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            for (int j = i + 1; j < volunteers.size(); j++) {
                if (volunteers.get(i).nickName.equals(volunteers.get(j).nickName)) {
                    duplicates.add(volunteers.get(i));
                    duplicates.add(volunteers.get(j));
                }
            }
        }
        return duplicates;
    }


    public static int getCompletedForms(List<Volunteer> volunteers) {
        List<Volunteer> completedVolunteer = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            if (
                    !volunteers.get(i).nickName.isEmpty()
                            && !volunteers.get(i).firstName.isEmpty()
                            && !volunteers.get(i).lastName.isEmpty()
                            && !volunteers.get(i).phone.isEmpty()
                            && !volunteers.get(i).eMail.isEmpty()
            ) {
                completedVolunteer.add(volunteers.get(i));
            }
        }
        return completedVolunteer.size();
    }

    public static int getUncompletedForms(List<Volunteer> volunteers) {
        List<Volunteer> uncompletedVolunteer = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            if (
                    volunteers.get(i).nickName.isEmpty()
                            || volunteers.get(i).firstName.isEmpty()
                            || volunteers.get(i).lastName.isEmpty()
                            || volunteers.get(i).phone.isEmpty()
                            || volunteers.get(i).eMail.isEmpty()
            ) {
                uncompletedVolunteer.add(volunteers.get(i));
            }
        }
        return uncompletedVolunteer.size();
    }

    public static List<Volunteer> deleteDuplicates(List<Volunteer> volunteers) {
        List<Volunteer> withoutDuplicates = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++){
            if (!withoutDuplicates.contains(volunteers.get(i)))
                withoutDuplicates.add(volunteers.get(i));
        }
        return withoutDuplicates;
    }

    public static int getVolunteerContactPseudoOnly(List<Volunteer> volunteers) {
        List<Volunteer> volunteerContactPseudoOnly = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            if (volunteers.get(i).eMail.isEmpty()
                    && volunteers.get(i).phone.isEmpty()
                    && !volunteers.get(i).nickName.isEmpty()
            ){
                volunteerContactPseudoOnly.add(volunteers.get(i));
            }
        }
        return volunteerContactPseudoOnly.size();
    }

    public static int getVolunteerQuickContact(List<Volunteer> volunteers) {
        List<Volunteer> volunteerQuickContact = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            if ((volunteers.get(i).nickName.isEmpty()
                    && volunteers.get(i).firstName.isEmpty()
                    && volunteers.get(i).lastName.isEmpty())
                    && (!volunteers.get(i).phone.isEmpty() || !volunteers.get(i).eMail.isEmpty())
            ){
                volunteerQuickContact.add(volunteers.get(i));
            }
        }
        return volunteerQuickContact.size();
    }

    public static List<Volunteer> removeDuplicateByNameAndNickName(List<Volunteer> volunteers) {
        List<Volunteer> duplicatesCompleted = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            for (int j = i + 1; j < volunteers.size(); j++) {
                if (volunteers.get(i).firstName.equals(volunteers.get(j).firstName) && volunteers.get(i).lastName.equals(volunteers.get(j).lastName) && volunteers.get(i).nickName.equals(volunteers.get(j).nickName)) {
                    Volunteer mergeVolunteer = new Volunteer(
                            volunteers.get(i).firstName,
                            volunteers.get(i).lastName,
                            volunteers.get(i).nickName,
                            volunteers.get(i).eMail + ',' + volunteers.get(j).eMail,
                            volunteers.get(i).phone + ',' + volunteers.get(j).phone
                    );
                    duplicatesCompleted.add(mergeVolunteer);
                }
            }
        }
        return duplicatesCompleted;
    }

    public static List<Volunteer> getEmptyVolunteerData(List<Volunteer> volunteers) {
        List<Volunteer> emptyVolunteer = new ArrayList<>();
        for (int i = 0; i < volunteers.size(); i++) {
            if (volunteers.get(i).nickName.isEmpty()
            && volunteers.get(i).firstName.isEmpty()
            && volunteers.get(i).lastName.isEmpty()
            && volunteers.get(i).phone.isEmpty()
            && volunteers.get(i).eMail.isEmpty()) {
                emptyVolunteer.add(volunteers.get(i));
            }
        }
        return emptyVolunteer;
    }
}

