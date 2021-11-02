import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Registre {
    private Header header;
    private HashMap<String, ArrayList<Integer>> dict;

    public Registre(Header header){
        this.header = header;
        this.dict = new HashMap<>();
    }

    public void put(String key, Integer value){
        if(!this.dict.containsKey(key)){
            this.dict.put(key, new ArrayList<>(Arrays.asList(value)));
        }else{
            this.dict.get(key).add(value);
        }
    }

    public boolean containsKey(String key){
        return this.dict.containsKey(key);
    }

    @Override
    public String toString() {
        String str = "Type: " + header + "\n";
        for (Map.Entry<String, ArrayList<Integer>> entry : dict.entrySet()) {
            str += "'" + entry.getKey() + "' : ";
            for(Integer user: entry.getValue()){
                str += user.toString() + " ";
            }
            str += "\n";
        }
        return str;
    }
}
