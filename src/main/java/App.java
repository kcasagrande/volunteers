import org.example.volunteers.services.Cleaner;
import org.example.volunteers.models.Volunteer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");

        List<Volunteer> inputVolunteers = Files.readAllLines(Paths.get("src/main/resources/data.csv")).stream()
            .map(string -> Arrays.stream(string.split(";", -1))
            .map(token -> quotes.matcher(token).replaceAll("$1"))
            .collect(toList()))
            .map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
            .collect(toList());

        Cleaner cleaner = new Cleaner(inputVolunteers);

        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));
        cleaner.cleanUp().forEach(writer::println);

        PrintWriter emailErrorsWriter = new PrintWriter("src/main/resources/badEmail.txt");
        cleaner.emailValidator.print(emailErrorsWriter);
        emailErrorsWriter.close();

        PrintWriter phoneNumberErrorsWriter = new PrintWriter("src/main/resources/badPhoneNumbers.txt");
        cleaner.phoneNumberValidator.print(phoneNumberErrorsWriter);
        phoneNumberErrorsWriter.close();

        writer.close();
    }
}
