import org.example.volunteers.Personne;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class App {

    public static void main(String[] args) throws IOException {
        List<String[]> lines = Files.readAllLines(Paths.get("src/main/resources/data.csv"))
                .stream().map(string -> string.split(";", -1))
                .collect(toList());

        List<Personne> personnes = new ArrayList<>();
        int nbDebut = lines.size();
        int nbFin = 0;
        for (String[] line : lines) {
            personnes.add(new Personne(line));
        }

        //On formate les champs
        personnes.forEach(p -> p.formater());
        System.out.println("nb de base : " + nbDebut);

        //On boucle sur tous
        for (int i=0 ; i< personnes.size() ; i++){
            //On check les doublons et on rempli tmtc
            for (int j=0 ; j< personnes.size() ; j++){
                //On regarde si c'est un doublons
                if(i!=j && personnes.get(i).isDoublon(personnes.get(j))){
                    System.out.println("lui " + personnes.get(j).adresseMail + " est egale à lui :" + personnes.get(i).adresseMail);
                    //On complete le tt
                    personnes.get(i).complete(personnes.get(j));
                    //On le remove pour ne pas repasser dessus et donc enlever les doublons
                    personnes.remove(j);
                }
            }
        }

        System.out.println("Result goes here");
        nbFin = personnes.size();
        System.out.println("nb de fin : " + nbFin);

        FileWriter writer = new FileWriter(String.valueOf(Paths.get("src/main/resources/data_ok.csv")));
        personnes.forEach(p -> {
            try {
                writer.write(p.nom + ";" + p.prenom + ";" + p.pseudo + ";" + p.adresseMail + ";" + p.tel + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.close();
    }
}
