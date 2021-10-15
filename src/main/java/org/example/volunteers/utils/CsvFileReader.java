package org.example.volunteers.utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CsvFileReader  {

    public List<String[]> extractDatas(String filePath) {
        try {
            return Files.readAllLines(Paths.get(filePath))
                    .stream().map(string -> string.split(";"))
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}
