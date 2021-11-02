package org.example.volunteers.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * CSV File Reader
 * Permits CSV file reading
 */
public class CsvFileReader  {
    /**
     * Private constructor
     */
    private CsvFileReader(){}

    /**
     * Extract data from CSV File
     * @param filePath Path of the file
     * @return List of String tab red from file
     * @throws IOException Throw exception if file cannot be open (wrong path, wrong file)
     */
    public static List<String[]> extractDatas(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath))
                .stream()
                .map(string -> string.split(";", -1))
                .collect(toList());
    }
}
