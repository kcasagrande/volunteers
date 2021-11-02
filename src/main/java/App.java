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

        HashMap<Integer, User> usersDict = new HashMap<>();

        Registre lastnameRegistre = new Registre(Header.LASTNAME);
        Registre firstnameRegistre = new Registre(Header.FIRSTNAME);
        Registre usernameRegistre = new Registre(Header.USERNAME);
        Registre emailRegistre = new Registre(Header.EMAIL);
        Registre phoneRegistre = new Registre(Header.PHONE);

        for(User user: lines){
            if(!(usersDict.containsKey(user.id))){
                usersDict.put(user.id, user);
            }

            if(!(lastnameRegistre.containsKey(user.lastname))){
                lastnameRegistre.put(user.lastname, user.id);
            }

            if(!(firstnameRegistre.containsKey(user.firstname.toUpperCase(Locale.ROOT)))){
                firstnameRegistre.put(user.firstname, user.id);
            }

            if(!(usernameRegistre.containsKey(user.username))){
                usernameRegistre.put(user.username, user.id);
            }

            if(!(emailRegistre.containsKey(user.email))){
                emailRegistre.put(user.email, user.id);
            }

            if(!(phoneRegistre.containsKey(user.phone))){
                phoneRegistre.put(user.phone, user.id);
            }

        }


        // Apply dark magic here...
//        lines.forEach(System.out::println);
        System.out.println(lastnameRegistre);
    }

    public static Function<String, String[]> splitCSV = (row) -> row.split(";", -1);

    public static Function<String[], User> createUserFromLine =
            (line) -> new User(line[0], line[1], line[2], line[3], line[4]);

}
