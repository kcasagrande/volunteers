import org.example.volunteers.Cleaner;
import org.example.volunteers.Volunteer;

import javax.xml.validation.Validator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class App {
    public static void main(String[] args) throws IOException {
        Pattern quotes = Pattern.compile("^\"([^\"]*)\"$");

        List<Volunteer> inputVolunteers = Files.readAllLines(Paths.get(args[0])).stream()
                .map(string -> Arrays.stream(string.split(";", -1))
                        .map(token -> quotes.matcher(token).replaceAll("$1"))
                        .collect(toList()))
                .map(tokens -> new Volunteer(tokens.get(0), tokens.get(1), tokens.get(2), tokens.get(3), tokens.get(4)))
                .collect(toList());

        List<List<Volunteer>> outputVolunteers = Cleaner.cleanUp(inputVolunteers);

        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/output.csv"));
        outputVolunteers.get(0).forEach(writer::println);

        PrintWriter writerDuplicat = new PrintWriter(new FileWriter("src/main/resources/outputDuplicat.csv"));
        outputVolunteers.get(1).forEach(writerDuplicat::println);

        PrintWriter writerErroneous = new PrintWriter(new FileWriter("src/main/resources/outpuErroneous .csv"));
        outputVolunteers.get(2).forEach(writerErroneous::println);

        writer.close();
        writerDuplicat.close();
        writerErroneous.close();
    }


    /*
    Objectifs :
     . Le numéro du user doit respecter le format +33000000000
     . L'email du user doit respecter le format standard d'email
     . Le numéro & l'email ne doivent pas apparaitre plus d'une fois sauf si les noms & prénoms sont différents
     . Le nom & prénom ne doivent pas apparaitre plus d'une fois sauf si le pseudo & email & numéro sont différents
     . Email erroné => ajouter le user au fichier d'erronés
     . Numéro ne pouvant pas respecter le format => ajouter le user au fichier d'erronés
     .
    */
}