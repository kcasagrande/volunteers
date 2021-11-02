import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

public class App {

    public static void main(String[] args) throws IOException {
        List<User> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream()
            .map(splitCSV)
            .map(createUserFromLine)
            .sorted(Collections.reverseOrder())
            .collect(toList());

        for (int i = 0; i < lines.size(); i++){
            for (int j = 0; j < lines.size(); j++) {
                User ref = lines.get(i);
                User usr = lines.get(j);
                if (ref.phone.equals(usr.phone) && ref.lastname.equalsIgnoreCase(usr.lastname) && ref.firstname.equalsIgnoreCase(usr.firstname)) {
                    lines.remove(j);
                }
            }
        }

//        HashMap<Integer, User> usersDict = new HashMap<>();
//
//        Registre lastnameRegistre = new Registre(Header.LASTNAME);
//        Registre firstnameRegistre = new Registre(Header.FIRSTNAME);
//        Registre usernameRegistre = new Registre(Header.USERNAME);
//        Registre emailRegistre = new Registre(Header.EMAIL);
//        Registre phoneRegistre = new Registre(Header.PHONE);
//
//        for(User user: lines){
//            if(!(usersDict.containsKey(user.id))){
//                usersDict.put(user.id, user);
//            }
//
//            lastnameRegistre.put(user.lastname, user.id);
//            firstnameRegistre.put(user.firstname, user.id);
//            usernameRegistre.put(user.username, user.id);
//            emailRegistre.put(user.email, user.id);
//            phoneRegistre.put(user.phone, user.id);
//        }
//
//        // Apply dark magic here...
//        lines.forEach(System.out::println);
//        System.out.println(lastnameRegistre);
        lines.forEach(System.out::println);
        System.out.println(lines.size());

    }

    public static Function<String, String[]> splitCSV = (row) -> row.split(";", -1);

    public static Function<String[], User> createUserFromLine =
            (line) -> new User(line[0], line[1], line[2], line[3], line[4]);

}
