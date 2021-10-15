import org.example.volunteers.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";"))
            .collect(toList());

        lines.sort(Comparator.comparing(strings -> strings[0]));

        List<User> users = createUserListFromCSV(lines);

        formatNumbers(users);
        aggregateMailAndTel(users);
        users.forEach(
                user -> {
                    System.out.println(user.toString());
                }
        );
    }

    public static List<User> createUserListFromCSV(List<String[]> csvList) {
        List<User> users = new ArrayList<>();
        csvList.forEach(
                line -> {
                    users.add(new User(line[0], line[1], line[2], line[3], line.length > 4 ? line[4] : ""));
                }
        );
        return users;
    }

    public static void formatNumbers(List<User> users) {
        users.forEach(
                user -> {
                    if (user.tel.contains("+33")) {
                        user.tel = user.tel.replaceAll("\\+33", "0");
                    }
                    if (user.tel.contains(".")) {
                        user.tel = user.tel.replaceAll("\\.", "");
                    }
                    if (user.tel.contains(" ")) {
                        user.tel = user.tel.replaceAll(" ", "");
                    }
                    if (user.tel.contains("-")) {
                        user.tel = user.tel.replaceAll("-", "");
                    }
                    if (user.tel.contains("(0)")) {
                        user.tel = user.tel.replaceAll("\\(0\\)", "");
                    }
                }
        );
    }

    public static void aggregateMailAndTel(List<User> users) {
        List<User> mails = new ArrayList<>();
        users.forEach(
                user -> {
                    if (!user.mail.equals("")) mails.add(user);
                }
        );

        users.clear();

        HashMap<String, List<User>> mailsMap = createMap(mails);

        mailsMap.forEach(
                (key, value) -> {
                    value.forEach(
                            user -> {

                            }
                    );
                }
        );

    }

    public static HashMap<String, List<User>> createMap(List<User> users) {
        HashMap<String, List<User>> usersMap = new HashMap<>();
        users.forEach(
                user1 -> {
                    usersMap.put(user1.mail, users.stream().filter(user -> user1.mail.equals(user.mail)).collect(toList()));
                }
        );
        return usersMap;
    }

    public static List<User> filterPhone(List<User> users) {
        return users.stream().filter(
                user -> user.tel.length() == 10
        ).collect(toList());
    }
}