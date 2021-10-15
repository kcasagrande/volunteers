import org.example.volunteers.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";"))
            .collect(toList());

        lines.sort(Comparator.comparing(strings -> strings[0]));

        List<User> users = createUserListFromCSV(lines);

        formatNumbers(users);

        users.forEach(
                user -> {
                    System.out.println(user.tel);
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
}