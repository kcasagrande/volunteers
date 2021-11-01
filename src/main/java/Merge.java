import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Merge {
    public final String key;
    private ArrayList<User> users;
    private ArrayList<User> result;

    public Merge(String key){
        this.key = key;
        this.users = new ArrayList<>();
        this.result = new ArrayList<>();
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public void mergeUsers(){
        User user = new User(key, getFirstname(), getUsername(), getEmail(), getPhone());
        this.result.add(user);
    }

    public ArrayList<User> getResult() {
        return result;
    }

    private HashMap<String, Double> getPercentageByFirstname(){
        HashMap<String, Double> dict = new HashMap<>();
        for(User user: users){
            if(dict.containsKey(user.firstname)){
                dict.put(user.firstname, dict.get(user.firstname) + 1.);
            }else {
                dict.put(user.firstname, 1.);
            }
        }
        for(String key: dict.keySet()){
            dict.put(key, dict.get(key) / users.size());
        }
        return dict;
    }

    private String getFirstname(){
        String firstname = "";
        Double max = 0.;
        for (Map.Entry<String, Double> entry : getPercentageByFirstname().entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if(max < value){
                max = value;
                firstname = key;
            }
        }
        return firstname;
    }

    private HashMap<String, Double> getPercentageByUsername(){
        HashMap<String, Double> dict = new HashMap<>();
        for(User user: users){
            if(dict.containsKey(user.username)){
                dict.put(user.username, dict.get(user.username) + 1.);
            }else {
                dict.put(user.username, 1.);
            }
        }
        for(String key: dict.keySet()){
            dict.put(key, dict.get(key) / users.size());
        }
        return dict;
    }

    private String getUsername(){
        String username = "";
        Double max = 0.;
        for (Map.Entry<String, Double> entry : getPercentageByUsername().entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if(max < value){
                max = value;
                username = key;
            }
        }
        return username;
    }

    private HashMap<String, Double> getPercentageByEmail(){
        HashMap<String, Double> dict = new HashMap<>();
        for(User user: users){
            if(dict.containsKey(user.email)){
                dict.put(user.email, dict.get(user.email) + 1.);
            }else {
                dict.put(user.email, 1.);
            }
        }
        for(String key: dict.keySet()){
            dict.put(key, dict.get(key) / users.size());
        }
        return dict;
    }

    private String getEmail(){
        String email = "";
        Double max = 0.;
        for (Map.Entry<String, Double> entry : getPercentageByEmail().entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if(max < value){
                max = value;
                email = key;
            }
        }
        return email;
    }

    private HashMap<String, Double> getPercentageByPhone(){
        HashMap<String, Double> dict = new HashMap<>();
        for(User user: users){
            if(dict.containsKey(user.phone)){
                dict.put(user.phone, dict.get(user.phone) + 1.);
            }else {
                dict.put(user.phone, 1.);
            }
        }
        for(String key: dict.keySet()){
            dict.put(key, dict.get(key) / users.size());
        }
        return dict;
    }

    private String getPhone(){
        String phone = "";
        Double max = 0.;
        for (Map.Entry<String, Double> entry : getPercentageByPhone().entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            if(max < value){
                max = value;
                phone = key;
            }
        }
        return phone;
    }
}
