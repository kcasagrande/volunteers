package org.example.volunteers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Searcher {


    public Searcher() {
    }

    public static List<Integer> searchSimilarIndexes(String[] lineToSearch, List<String[]> lines)
    {
        List<Integer> listOfSimilarIndexes = new ArrayList<>();

        for (String[] line : lines) {
            //System.out.println(Arrays.toString(line));
            if(lineToSearch.equals(line))
            {
                listOfSimilarIndexes.add(lines.indexOf(line));
            }
            //Recherche nom/prénom
            else if(lineToSearch[0].equalsIgnoreCase(line[0]) && lineToSearch[1].equalsIgnoreCase(line[1]))
            {
                listOfSimilarIndexes.add(lines.indexOf(line));
            }
            else if(lineToSearch[0].equalsIgnoreCase(line[1]) && lineToSearch[1].equalsIgnoreCase(line[0]))
            {
                listOfSimilarIndexes.add(lines.indexOf(line));
            }
            //Recherche pseudo
            else if(!lineToSearch[2].isEmpty() && lineToSearch[2].equalsIgnoreCase(line[2]))
            {
                listOfSimilarIndexes.add(lines.indexOf(line));
            }
            //Recherche mail
            else if(lineToSearch[3].equalsIgnoreCase(line[3]))
            {
                listOfSimilarIndexes.add(lines.indexOf(line));
            }
            //Recherche numéro
            else if(lineToSearch[4].equalsIgnoreCase(line[4]))
            {
                listOfSimilarIndexes.add(lines.indexOf(line));
            }
        }

        return listOfSimilarIndexes;
    }

    public List<String[]> createUniqueListFromSearchSimilarIndex(List<Integer> indexList, List<String[]> initLines){

        List<String[]> similarValueList = new ArrayList<>();

        for (int i = 0; i<=indexList.size()-1;i++){

            similarValueList.add(initLines.get(indexList.get(i)));
        }
        return similarValueList;
    }

    // retourne un tableau de string contenant une seule entrée trié sur la liste de similitude
    public String[] createUniqueUserFromSimilarList(List<String[]> similarListValue){

        HashMap<String,String> valueHashMap = new HashMap<>();
        List<String[]> uniqueValueList = new ArrayList<>();

        for(int i = 0; i< similarListValue.size();i++){

            if (valueHashMap.get("prenom") == null || valueHashMap.get("prenom").isEmpty()){
                valueHashMap.put("prenom", similarListValue.get(i)[0]);
            }
            if(valueHashMap.get("nom") == null || valueHashMap.get("nom").isEmpty()){
                valueHashMap.put("nom",similarListValue.get(i)[1]);
            }
            if(valueHashMap.get("pseudo") == null || valueHashMap.get("pseudo").isEmpty()){
                valueHashMap.put("pseudo",similarListValue.get(i)[2]);
            }
            if (valueHashMap.get("mail") == null || valueHashMap.get("mail").isEmpty()){
                valueHashMap.put("mail",similarListValue.get(i)[3]);
            }
            if (valueHashMap.get("telephone") == null || valueHashMap.get("telephone").isEmpty()){
                valueHashMap.put("telephone",similarListValue.get(i)[4]);
            }
        }

        //System.out.println(valueHashMap);

        return new String[]{valueHashMap.get("prenom"), valueHashMap.get("nom"),
                valueHashMap.get("pseudo"),valueHashMap.get("mail"),valueHashMap.get("telephone")};
    }


    public List<String[]> deleteIndexesFromMainList(List<Integer> indexList, List<String[]> voluteerList)
    {
        for (int i = indexList.size()-1; i >= 0; i--) {
            System.out.print("| Index a suppr : " + indexList.get(i));
            voluteerList.remove(indexList.get(i).intValue());
        }

        System.out.println("taille liste : " + voluteerList.size());
        return voluteerList;
    }


}
