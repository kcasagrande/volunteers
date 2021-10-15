import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact
{
    public String firstName;
    public String lastName;
    public String nickName;
    public String email;
    public String phone;

    public static boolean checkIsValidEmail(String email){

        if(email.contains("@") && email.contains(".")){
            return true;
        }else {
            return false;
        }

    }

    public static boolean checkIsValidNumberPhone(String phone){

        int validLength = 12;
        String regex = "^+(.+)$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(phone);

        return matcher.matches() && phone.length() == validLength;

    }
}