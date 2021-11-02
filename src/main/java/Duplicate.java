import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Duplicate {
    public Duplicate() {

    }

    public List<User> mergeByName(List<User> users) {
        List<User> newUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            int j = 0;
            String exist = "no";
            String lastname = users.get(i).lastname;
            String firstname = users.get(i).firstname;
            String username = users.get(i).username;
            String email = users.get(i).email;
            String phone = users.get(i).phone;
            while (j < newUsers.size()) {
                if (lastname.toUpperCase(Locale.ROOT).equals(newUsers.get(j).lastname.toUpperCase(Locale.ROOT))
                        && firstname.toUpperCase(Locale.ROOT).equals(newUsers.get(j).firstname.toUpperCase(Locale.ROOT))
                        && !firstname.equals("") && !lastname.equals("")) {
                    exist = "yes";
                    break;
                } else {
                    j++;
                }
            }
            if (exist.equals("no")) {
                while (i2 < users.size()) {
                    if (lastname.toUpperCase(Locale.ROOT).equals(users.get(i2).lastname.toUpperCase(Locale.ROOT))
                            && firstname.toUpperCase(Locale.ROOT).equals(users.get(i2).firstname.toUpperCase(Locale.ROOT))) {
                        if (username.equals("")) {
                            username = users.get(i2).username;
                        }
                        if (email.equals("")) {
                            email = users.get(i2).email;
                        }
                        if (phone.equals("")) {
                            phone = users.get(i2).phone;
                        }
                    }
                    i2++;
                }
                newUsers.add(newUsers.size(), new User(lastname,firstname,username,email,phone));
            }
        }
        return newUsers;
    }

    public List<User> mergeByPhoneNumber(List<User> users) {
        List<User> newUsers = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            int i2 = i;
            int j = 0;
            String exist = "no";
            String lastname = users.get(i).lastname;
            String firstname = users.get(i).firstname;
            String username = users.get(i).username;
            String email = users.get(i).email;
            String phone = users.get(i).phone;
            while (j < newUsers.size()) {
                if (phone.toUpperCase(Locale.ROOT).equals(newUsers.get(j).phone.toUpperCase(Locale.ROOT))
                        && !phone.equals("")) {
                    exist = "yes";
                    break;
                } else {
                    j++;
                }
            }
            if (exist.equals("no")) {
                while (i2 < users.size()) {
                    if (phone.toUpperCase(Locale.ROOT).equals(users.get(i2).phone.toUpperCase(Locale.ROOT))) {
                        if (lastname.equals("")) {
                            lastname = users.get(i2).lastname;
                        }
                        if (firstname.equals("")) {
                            firstname = users.get(i2).firstname;
                        }
                        if (username.equals("")) {
                            username = users.get(i2).username;
                        }
                        if (email.equals("")) {
                            email = users.get(i2).email;
                        }
                    }
                    i2++;
                }
                newUsers.add(newUsers.size(), new User(lastname,firstname,username,email,phone));
            }
        }
        return newUsers;
    }
}
