public class User implements Comparable<User>{
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
        this.phone = this.formatPhone(phone);
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
        return this.lastname.isEmpty() ||
                user.lastname.isEmpty() ||
                this.lastname.equalsIgnoreCase(user.lastname);
    }

    public boolean isSameFirstname(User user){
        return this.firstname.isEmpty() ||
                user.firstname.isEmpty() ||
                this.firstname.equalsIgnoreCase(user.firstname);
    }

    public boolean isSameUsername(User user){
        return this.username.isEmpty() ||
                user.username.isEmpty() ||
                this.username.equalsIgnoreCase(user.username);
    }

    public boolean isSameEmail(User user){
        return this.email.isEmpty() ||
                user.email.isEmpty() ||
                this.email.equalsIgnoreCase(user.email);
    }

    public String formatPhone(String phone){
        return phone
                .replace(" ", "")
                .replace(".", "")
                .replace("-", "")
                .replace("+33(0)", "0")
                .replace("+33", "0");
    }

    public boolean isSamePhone(User user){
        String myPhone = formatPhone(phone);
        String yourPhone = formatPhone(user.phone);
        return myPhone.isEmpty() ||
                yourPhone.isEmpty() ||
                myPhone.equalsIgnoreCase(yourPhone);
    }

    public int getScoreTo(User user) {
        int score = 0;
        if(isSameLastname(user)){
            score++;
        }
        if(isSameFirstname(user)){
            score++;
        }
        if(isSameUsername(user)){
            score++;
        }
        if(isSameEmail(user)){
            score++;
        }
        if(isSamePhone(user)){
            score++;
        }
        return score;
    }

    @Override
    public int compareTo(User userB) {
        return this.phone.compareTo(userB.phone) > 0 ? 1 : -1;
    }
}
