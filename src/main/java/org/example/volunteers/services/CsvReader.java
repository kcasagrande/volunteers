package org.example.volunteers.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CsvReader {
    public List<String[]> getLinesFromFile(String path) throws IOException {
        return Files.readAllLines(Paths.get(path))
                .stream().map(string -> string.split(";", -1))
                .collect(toList());
    }
}
