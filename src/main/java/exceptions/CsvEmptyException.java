package exceptions;

public class CsvEmptyException
    extends Exception
{
    public final String csvPath;

    public CsvEmptyException(String csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public String getMessage() {
        return "Le fichier est vide. Chemin donné : " + csvPath;
    }
}
