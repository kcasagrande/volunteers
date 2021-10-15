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
        List<Integer> result = searcher.searchSimilar(line,lines);
        List<Integer> listIndex=new ArrayList<Integer>();
        listIndex.add(0);
        listIndex.add(1);
        listIndex.add(2);
        // assert
        assertEquals(listIndex,result);
    }
}