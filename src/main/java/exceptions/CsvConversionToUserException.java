package exceptions;

public class CsvConversionToUserException
    extends Exception
{
    public final String csvPath;

    public CsvConversionToUserException(String csvPath) {
        this.csvPath = csvPath;
    }

    @Override
    public String getMessage() {
        return "Erreur dans la conversion en User : " + csvPath;
    }
}
