package exceptions;

public class CsvNotExistException
    extends Exception
{
    public final String csvPath;

    public CsvNotExistException(String csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public String getMessage() {
        return "Le fichier n'existe pas. Chemin donné : " + csvPath;
    }
}
