package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;

class SearcherTest {


    @Test
    public void testSimilitudeShouldReturnListOfSimilitude() throws IOException {
            Searcher searcher = new Searcher();

        // arrange
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/Testdata.csv"))
                .stream().map(string -> string.split(";",-1))
                .collect(toList());

        String[] line = {"GUILLOUX","SARAH","","sarah_guilloux@example.com","+33085552877"};
        // act
        List<Integer> result = searcher.searchSimilarIndexes(line,lines);
        List<Integer> listIndex=new ArrayList<Integer>();
        listIndex.add(0);
        listIndex.add(1);
        listIndex.add(4);
        // assert
        assertEquals(listIndex,result);
    }


    @Test
    public void createUniqueElementFromIndexList() throws IOException
    {
        Searcher searcher = new Searcher();
        List<Integer> listIndex=new ArrayList<Integer>();
        listIndex.add(0);
        listIndex.add(1);
        listIndex.add(4);

        List<String[]> attendu = new ArrayList<String[]>();
        String[] line1 = {"Sarah","Guilloux","","","+33085552877"};
        attendu.add(line1);
        String[] line2 = {"GUILLOUX","SARAH","","sarah_guilloux@example.com",""};
        attendu.add(line2);
        String[] line3 = {"","","SarahLaBg","sarah_guilloux@example.com","+33085552877"};
        attendu.add(line3);

        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/Testdata.csv"))
                .stream().map(string -> string.split(";",-1))
                .collect(toList());

        List<String[]> result = searcher.createUniqueListFromSearchSimilarIndex(listIndex, lines);

        assertArrayEquals(attendu.toArray(), result.toArray());

    }

    @Test
    public void testFusionLineUnique() throws IOException {

        Searcher searcher = new Searcher();
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/TestdataFusion.csv"))
                .stream().map(string -> string.split(";", -1))
                .collect(toList());
        String[] finalLine = searcher.createUniqueUserFromSimilarList(lines);
        assertEquals(finalLine, "lafromboise;romaine;Banditto;Banditto7416@example.com;+33055520502", "Ce message s'affiche si le testNumberParam échoue");
    }

    @Test
    public void deleteIndexesFromMainList() throws IOException
    {
        Searcher searcher = new Searcher();
        List<Integer> listIndex=new ArrayList<Integer>();
        listIndex.add(0);
        listIndex.add(1);
        listIndex.add(4);

        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/Testdata.csv"))
                .stream().map(string -> string.split(";",-1))
                .collect(toList());

        List<String[]> attendu = new ArrayList<String[]>();
        String[] line1 = {"Reverdin","Léo","","Paladin4409@example.net","+33045550603"};
        attendu.add(line1);
        String[] line2 = {"Gicquel","Valérie","","valerie.gicquel@example.org","+33055529222"};
        attendu.add(line2);
        String[] line3 = {"Reverdin","Léo","SarahLaBg","Paladin4409@example.net","+33045550603"};
        attendu.add(line3);
        String[] line4 = {"Gicquel","Valérie","","valerie.gicquel@example.org","+33055529222"};
        attendu.add(line4);

        List<String[]> result = searcher.deleteIndexesFromMainList(listIndex, lines);

        assertArrayEquals(attendu.toArray(), result.toArray());


    }


}