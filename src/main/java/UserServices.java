import Services.csv.CsvService;
import exceptions.CsvEmptyException;
import exceptions.CsvNotExistException;
import user.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServices {

    List<User> allUserUnfiltered;
    List<User> CleanListUser;

    protected void userCsvData() throws CsvNotExistException, CsvEmptyException, IOException {
        CsvService csvService = new CsvService("src/main/resources/data.csv");
        List<String[]> lines = csvService.readAllLines();
        allUserUnfiltered = csvService.convertListUser(lines);
    }

    protected void cleanUserList() {
        CleanListUser = new ArrayList<User>();
        for (User user: allUserUnfiltered) {
            user.trimAll();
            user.stripAccent();
            user.toLowerCase();

            if (user.checkValidComboLastNameFirstNameOfUser(CleanListUser)
                    && user.checkValidEmailOfUser(CleanListUser)) {
                CleanListUser.add(user);
            } 
        }
    }

    public void ShowUserList() throws CsvNotExistException, CsvEmptyException, IOException {
        this.userCsvData();
        //System.out.println(allUserUnfiltered);
        this.cleanUserList();
        System.out.println(CleanListUser);
        for (User user: CleanListUser) {
            System.out.println(user.getFirstName() + " - " + user.getLastName() + " - " + user.getUserName() + " - " + user.getEmail() + " - " + user.getPhone());
            System.out.println("----------------------------------------------------------------");
        }
            System.out.println("Count : " + allUserUnfiltered.size() + " >> " + CleanListUser.size());
    }
}
