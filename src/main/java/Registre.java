import java.util.*;

public class Registre {
    private Header header;
    private HashMap<String, ArrayList<Integer>> dict;

    public Registre(Header header){
        this.header = header;
        this.dict = new HashMap<>();
    }

    public void put(String key, Integer value){
        String keyUpper = key.toUpperCase(Locale.ROOT);
        if(!this.dict.containsKey(keyUpper)){
            this.dict.put(keyUpper, new ArrayList<>(Arrays.asList(value)));
        }else{
            this.dict.get(keyUpper).add(value);
        }
    }

    public boolean containsKey(String key){
        return this.dict.containsKey(key.toUpperCase(Locale.ROOT));
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
