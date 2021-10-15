package org.example.volunteers.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CsvFileReader  {
    private CsvFileReader(){

    }

    public static List<String[]> extractDatas(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream().map(string -> string.split(";"))
                .collect(toList());
    }
}
