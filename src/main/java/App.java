import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class App {

    public static void main(String[] args) throws IOException {
        List<User> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream()
            .map(splitCSV)
            .map(createUserFromLine)
            .sorted(Collections.reverseOrder())
            .collect(toList());

        //FusesLastnameRegister
        Duplicate duplicate = new Duplicate();
        List<User> listUser = duplicate.mergeByName(lines);
        List<User> listUserBis = duplicate.mergeByPhoneNumber(listUser);

        /*HashMap<Integer, User> usersDict = new HashMap<>();

        HashMap<Integer, User> usersDict = new HashMap<>();
        HashMap<Header, Registre> registres = new HashMap<>();


        Registre lastnameRegistre = new Registre(Header.LASTNAME);
        Registre firstnameRegistre = new Registre(Header.FIRSTNAME);
        Registre usernameRegistre = new Registre(Header.USERNAME);
        Registre emailRegistre = new Registre(Header.EMAIL);
        Registre phoneRegistre = new Registre(Header.PHONE);

        for(User user: lines){
            if(!(usersDict.containsKey(user.id))){
                usersDict.put(user.id, user);
            }

            lastnameRegistre.put(user.lastname, user.id);
            firstnameRegistre.put(user.firstname, user.id);
            usernameRegistre.put(user.username, user.id);
            emailRegistre.put(user.email, user.id);
            phoneRegistre.put(user.phone, user.id);
        }

//        // Apply dark magic here...

        ArrayList<User> newUsers = mergeRegistre(usersDict, lastnameRegistre, firstnameRegistre, usernameRegistre, emailRegistre, phoneRegistre);

        System.out.println(newUsers.size());
        lines.forEach(System.out::println);



        // Apply dark magic here...
//        lines.forEach(System.out::println);
//        lines.forEach(System.out::println);
        System.out.println(lastnameRegistre);*/
        /*listUser.forEach((System.out::println));
        System.out.println(listUser.size());*/
        listUserBis.forEach((System.out::println));
        System.out.println(listUserBis.size());

    }

    public static Function<String, String[]> splitCSV = (row) -> row.split(";", -1);

    public static Function<String[], User> createUserFromLine =
            (line) -> new User(line[0], line[1], line[2], line[3], line[4]);

    /*public static ArrayList<User> mergeRegistre( HashMap<Integer, User> users, Registre lastname, Registre firstname, Registre username, Registre email, Registre phone){

        ArrayList<User> result = new ArrayList<>();
        Levenshtein lv = new Levenshtein();

        for(Map.Entry<String, ArrayList<Integer>> entry: lastname.getDict().entrySet()){
            List<Integer> ids = entry.getValue().stream().sorted().collect(toList());

            User user = users.get(ids.get(0));
            result.add(user);

            for (int i = 1; i < ids.size(); i++) {
                User bufUser = users.get(ids.get(i));

                Integer weight = lv.ld(user.firstname.toUpperCase(Locale.ROOT),
                        bufUser.firstname.toUpperCase(Locale.ROOT));

                if( weight > 1){
                    result.add(bufUser);
                }

            }
        }
        return result;
    }*/
}