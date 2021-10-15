package Services.csv;

import exceptions.CsvNotExistException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class CsvService {
    private String csvPath;
    private List<String[]> lines;

    public CsvService(String csvPath) {
        this.csvPath = csvPath;
    }

    public String getCsvPath() {
        return csvPath;
    }

    public void setCsvPath(String csvPath) {
        this.csvPath = csvPath;
    }

    public List<String[]> getLines() {
        return lines;
    }

    public void setLines(List<String[]> lines) {
        this.lines = lines;
    }

    public List<String[]> readAllLines() throws IOException, CsvNotExistException {
        // Check if csv file exists
        isCsvExists();

        lines = Files.readAllLines(Paths.get(csvPath))
                .stream().map(string -> string.split(";"))
                .collect(toList());

        return lines;
    }

    public void isCsvExists() throws CsvNotExistException {
        Path csvPath = Paths.get(this.csvPath);
        if(!Files.exists(csvPath))
        {
            throw new CsvNotExistException(this.csvPath);
        }
    }
}
