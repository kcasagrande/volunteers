import java.text.Normalizer;
import java.util.Comparator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact
{
    private String firstName;
    private String lastName;
    private String nickName;
    private String email;
    private String phone;

    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setNickName(String nickName) { this.nickName = nickName; }
    public void setEmail(String email) { this.email = email; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getFirstName() { return this.firstName; }
    public String getLastName() { return lastName; }
    public String getNickName() { return nickName; }
    public String getEmail(){
        return this.email;
    }
    public String getPhone() { return phone; }

    public static String removeDiacriticalMarks(String string) {
        return Normalizer.normalize(string, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    public static boolean isValidEmail(String email){
        String regex = "^[\\w!#$%&'*+/=?`{|L}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|L}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean checkIsValidNumberPhone(String phone){
        Pattern pattern = Pattern.compile("^\\+33\\(?\\d\\)?[\\s|\\d|\\-|\\.]+$");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();

    }

    public static boolean IsFirstNameLastName(Contact contact){
        String emailString = contact.getEmail().split("@")[0].toLowerCase(Locale.ROOT);

        if(!emailString.isEmpty()){
            // firstname is first and lastName is next
            Pattern pattern = Pattern.compile(String.format("%s.%s", contact.getFirstName().toLowerCase(Locale.ROOT), contact.getLastName().toLowerCase(Locale.ROOT)));
            Matcher matcher = pattern.matcher(emailString);
            return matcher.matches();
        }
        return false;
    }
}
