import org.example.volunteers.user.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
                .stream().map(string -> string.split(";"))
                .collect(toList());
        lines.sort(Comparator.comparing(strings -> strings[0]));
        List<User> users = createUserListFromCSV(lines);
        users.forEach(
                user -> {
                    user.tel = formatNumbers(user.tel);
                }
        );
        HashMap<String, List<User>> map = aggregateMail(users);
        map = aggregateTel(map);
        users.clear();
        map.forEach(
                (key, user) -> {
                    users.addAll(user);
                }
        );
        List<User> finalUsers;
        finalUsers = filterPhone(users);
        users.clear();
        users.addAll(filterPseudo(finalUsers));
        users.addAll(filterNameAndSurname(finalUsers));
        users.forEach(
                user -> {
                    System.out.println(user);
                }
        );
        System.out.println(users.size());
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

    public static String formatNumbers(String phone) {
        if (phone.contains("+33")) {
            phone = phone.replaceAll("\\+33", "0");
        }
        if (phone.contains(".")) {
            phone = phone.replaceAll("\\.", "");
        }
        if (phone.contains(" ")) {
            phone = phone.replaceAll(" ", "");
        }
        if (phone.contains("-")) {
            phone = phone.replaceAll("-", "");
        }
        if (phone.contains("(0)")) {
            phone = phone.replaceAll("\\(0\\)", "");
        }
        return phone;
    }

    public static HashMap<String, List<User>> aggregateMail(List<User> users) {
        users.forEach(
                user -> {
                    if (user.mail.equals("")) user.mail = "noMail";
                    if (user.tel.equals("")) user.tel = "noTel";
                }
        );

        HashMap<String, List<User>> mailsMap = createMapMail(users);
        mailsMap.forEach(
                (key, value) -> {
                    if (value.size() > 1 && (!key.equals("noMail"))) {
                        final int[] i = {0};
                        AtomicReference<User> finalUser = new AtomicReference<>(new User("", "", "", "", ""));
                        value.forEach(
                                (user) -> {
                                    int j = 0;
                                    if (!user.surname.equals(""))
                                        j++;
                                    if (!user.name.equals(""))
                                        j++;
                                    if (!user.pseudo.equals(""))
                                        j++;
                                    if (!user.tel.equals("noTel"))
                                        j++;
                                    if (j > i[0]) {
                                        i[0] = j;
                                        finalUser.set(user);
                                    }
                                }
                        );
                        value.clear();
                        value.add(finalUser.get());
                    }
                }
        );
        return mailsMap;
    }

    public static HashMap<String, List<User>> aggregateTel(HashMap<String, List<User>> users) {
        List<User> usersFinal = new ArrayList<>();
        users.forEach(
                (key, value) -> {
                    usersFinal.addAll(value);
                }

        );
        HashMap<String, List<User>> mapUser = createMapTel(usersFinal);
        mapUser.forEach(
                (key, value) -> {
                    if (value.size() > 1 && (!key.equals("noTel"))) {
                        final int[] i = {0};
                        AtomicReference<User> finalUser = new AtomicReference<>(new User("", "", "", "", ""));
                        value.forEach(
                                (user) -> {
                                    int j = 0;
                                    if (!user.surname.equals(""))
                                        j++;
                                    if (!user.name.equals(""))
                                        j++;
                                    if (!user.pseudo.equals(""))
                                        j++;
                                    if (!user.mail.equals("noMail"))
                                        j++;
                                    if (j > i[0]) {
                                        i[0] = j;
                                        finalUser.set(user);
                                    }
                                }
                        );
                        value.clear();
                        value.add(finalUser.get());
                    }
                }
        );
        return mapUser;
    }

    public static HashMap<String, List<User>> createMapMail(List<User> users) {
        HashMap<String, List<User>> usersMap = new HashMap<>();
        users.forEach(
                user1 -> {
                    usersMap.put(user1.mail, users.stream().filter(user -> user1.mail.equals(user.mail)).collect(toList()));
                }
        );
        return usersMap;
    }

    public static HashMap<String, List<User>> createMapTel(List<User> users) {
        HashMap<String, List<User>> usersMap = new HashMap<>();
        users.forEach(
                user1 -> {
                    usersMap.put(user1.tel, users.stream().filter(user -> user1.tel.equals(user.tel)).collect(toList()));
                }
        );
        return usersMap;
    }

    public static List<User> filterPhone(List<User> users) {
        return users.stream().filter(
                user -> user.tel.length() == 10
        ).collect(toList());
    }

    public static List<User> filterNameAndSurname(List<User> users) {
        String regex = "[a-zA-ZàáâäãåąčćęèéêëėįìíîïłńòóôöõøùúûüųūÿýżźñçčšžÀÁÂÄÃÅĄĆČĖĘÈÉÊËÌÍÎÏĮŁŃÒÓÔÖÕØÙÚÛÜŲŪŸÝŻŹÑßÇŒÆČŠŽ∂ð,.'-]+";
        List<User> user2 = users.stream().filter(
                user -> user.name.matches(regex) && user.surname.matches(regex)
        ).collect(toList());
        return user2;
    }

    public static List<User> filterPseudo(List<User> users){
        List<User> user2 = users.stream().filter(
                user -> !user.pseudo.equals("") && (user.name.equals("") || user.surname.equals(""))
        ).collect(toList());
        return user2;
    }
}