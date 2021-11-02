import java.util.concurrent.atomic.AtomicInteger;

public class User implements Comparable<User>{
    private static final AtomicInteger count = new AtomicInteger(0);
    public final int id;
    public final String lastname;
    public final String firstname;
    public final String username;
    public final String email;
    public final String phone;

    public User(String lastname, String firstname, String username, String email, String phone) {
        this.id = count.incrementAndGet();
        this.lastname = lastname;
        this.firstname = firstname;
        this.username = username;
        this.email = email;
        this.phone = this.formatPhone(phone);
    }

    public String getLastname() {
        return this.lastname;
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

    public int getNbEmptyFields(){
        int nb = 0;
        for(String field: this.getRow()){
            nb += field.isEmpty() ? 1 : 0;
        }
        return nb;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if(!(obj instanceof User)){
            return false;
        }
        User user = (User) obj;
        return lastname.equals(user.lastname) &&
                firstname.equals(user.firstname) &&
                username.equals(user.username) &&
                email.equals(user.email) &&
                phone.equals(user.phone);
    }

    @Override
    public int compareTo(User user) {
        if(this.phone.equals(user.phone)){
            return this.getIndexSamePhone(user);
        }else{
            return this.getCompareToString(this.phone.compareToIgnoreCase(user.phone));
        }
    }

    private int getIndexSamePhone(User user){
        int myNbEmptyFields = this.getNbEmptyFields();
        int yourNbEmptyFields = user.getNbEmptyFields();

        if(myNbEmptyFields == yourNbEmptyFields){
            int result = this.getCompareToString(this.lastname.compareToIgnoreCase(user.lastname));
            if(result == 0){
                result = this.getCompareToString(this.firstname.compareToIgnoreCase(user.firstname));
            }
            if(result == 0){
                result = this.getCompareToString(this.username.compareToIgnoreCase(user.username));
            }
            return result;
        }

        if(myNbEmptyFields < yourNbEmptyFields){
            return 1;
        }else{
            return -1;
        }
    }

    private int getCompareToString(int value){
        if(value == 0){
            return 0;
        }else{
            return value > 0 ? 1 : -1;
        }
    }
}
