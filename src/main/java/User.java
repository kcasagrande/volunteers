public class User{
    public final String lastname;
    public final String firstname;
    public final String username;
    public final String email;
    public final String phone;

    public User(String lastname, String firstname, String username, String email, String phone) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.email = email;
        this.phone = phone;
    }

    public String[] getRow() {
        return new String[]{
            lastname,
            firstname,
            username,
            email,
            phone
        };
    }

    @Override
    public String toString(){
        return lastname + ";" +
                firstname + ";" +
                username + ";" +
                email + ";" +
                phone;
    }

    public boolean isSameLastname(User user){
        return this.lastname.equalsIgnoreCase(user.lastname);
    }

}
