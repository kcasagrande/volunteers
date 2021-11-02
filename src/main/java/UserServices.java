import Services.csv.CsvService;
import exceptions.CsvEmptyException;
import exceptions.CsvNotExistException;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServices {

    List<User> allUserUnfiltered;
    List<User> cleanListUser;
    List<User> dirtyListUser;

    protected void userCsvData() throws CsvNotExistException, CsvEmptyException, IOException {
        CsvService csvService = new CsvService("src/main/resources/data.csv");
        List<String[]> lines = csvService.readAllLines();
        allUserUnfiltered = csvService.convertListUser(lines);
    }

    protected void cleanUserList() {
        cleanListUser = new ArrayList<User>();
        dirtyListUser = new ArrayList<User>();
        for (User user: allUserUnfiltered) {
            user.trimAll();
            user.stripAccent();
            user.toLowerCase();
            if (user.checkValidComboLastNameFirstNameOfUser(cleanListUser)
                    && user.checkValidEmailOfUser(cleanListUser)
                    && user.checkNullFirstNameOfUser()
                    && user.checkNullLastNameOfUser()

            ) {
                cleanListUser.add(user);
            } else {
                dirtyListUser.add(user);
            }
        }
    }

    protected void consolidationUserList() {
        for (User userDirty: dirtyListUser) {
            for (User userClean: cleanListUser) {

                if (userClean.getPhone().equals(userDirty.getPhone())
                || userClean.getEmail().equals(userDirty.getEmail())
                || userClean.getUserName().equals(userDirty.getUserName())) {

                    if (!userClean.checkNullPhoneOfUser() && userDirty.checkNullPhoneOfUser()) {
                        userClean.setPhone(userDirty.getPhone()+ " &");
                    }

                    if (!userClean.checkNullEmailOfUser() && userDirty.checkNullEmailOfUser() ) {
                        userClean.setEmail(userDirty.getEmail() + " *");
                    }

                    if (!userClean.checkNullUserNameOfUser() && userDirty.checkNullUserNameOfUser()) {
                        userClean.setUserName(userDirty.getUserName() + " $");
                    }
                }
            }
        }
    }

    public void ShowUserList() throws CsvNotExistException, CsvEmptyException, IOException {
        this.userCsvData();
        this.cleanUserList();
        this.consolidationUserList();
        cleanListUser = cleanListUser.stream().sorted().collect(Collectors.toList());
        for (User user: cleanListUser) {
            System.out.println(user.getLastName() + " - " + user.getFirstName() + " - " + user.getUserName() + " - " + user.getEmail() + " - " + user.getPhone());
            System.out.println("----------------------------------------------------------------");
        }
        System.out.println("Data : " + allUserUnfiltered.size() + " - Clean : " + cleanListUser.size() + " - Dirty : " + dirtyListUser.size());
    }
}
