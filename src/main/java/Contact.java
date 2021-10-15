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
}
