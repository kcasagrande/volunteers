import org.example.volunteers.Searcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
            .stream().map(string -> string.split(";", -1))
            .collect(toList());

        System.out.println("Result goes here");

        Searcher searcher = new Searcher();
        List<String[]> uniqueList = new ArrayList<>();

       while(lines.size()>0){
            List<Integer> intList = searcher.searchSimilarIndexes(lines.get(0),lines);
            List<String[]> fusionList = searcher.createUniqueListFromSearchSimilarIndex(intList,lines);
            String[] fusionline = searcher.createUniqueUserFromSimilarList(fusionList);
            uniqueList.add(fusionline);
            System.out.println(Arrays.toString(fusionline));

            lines = searcher.deleteIndexesFromMainList(intList,lines);
        }

       System.out.println(uniqueList.size());
       System.out.println(Arrays.toString(uniqueList.toArray()));


    }

   /* */


}
