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
        getCompletedForms(outputVolunteers);
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
        System.out.println("Find duplicates function");
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
        System.out.println("Find duplicates function");
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
        System.out.println("Find duplicates function");
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
        System.out.println("Find duplicates function");
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
        System.out.println("Find duplicates function");
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

    public static Boolean toTitleCase(String name) {
        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0)))
                    .append(w.substring(1).toLowerCase())
                    .append(" ");
        }
        String nameRegex = "^[A-Z][a-z]*$";
        Pattern pattern = Pattern.compile(nameRegex);
        String nameTransformed = sb.toString().trim();
        Matcher matcher = pattern.matcher(nameTransformed);
        System.out.println(nameTransformed + " : " + matcher.matches());
        return matcher.matches();
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
}

