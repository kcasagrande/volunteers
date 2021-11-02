import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestClientList {
    private void formatNumber() {
        Pattern pattern = Pattern.compile("^(?:(?:\+|00)33|0)     # Dialing code\n" +
                "\s[1-9]              # First number (from 1 to 9)\n" +
                "(?:[\s.-]\d{2}){4}   # End of the phone number\n");
        Matcher matcher = pattern.matcher("+33(0)0 00 55 52 26");
        assertTrue(matcher.matches());
        System.out.println(matcher);
        //return matcher.matches();
    }

    private boolean checkMail() {
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }
}
