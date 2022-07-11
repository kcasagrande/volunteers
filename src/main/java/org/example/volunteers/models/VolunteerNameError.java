package org.example.volunteers.models;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class VolunteerNameError {

    public HashMap<String , List<Volunteer>> duplicateName ;
    public List<Volunteer> malformedNames ;

    public VolunteerNameError(HashMap<String, List<Volunteer>> duplicateName, List<Volunteer> malformedNames){
        this.duplicateName = duplicateName;
        this.malformedNames = malformedNames;
    }

    public void print(PrintWriter writer){
        this.printDuplicateName(writer);
        this.printMalformedNames(writer);
    }

    public void printMalformedNames(PrintWriter writer){
        if(this.malformedNames.size()>0){
            writer.println("Records avec mauvais nom : ");
            this.malformedNames.forEach(writer::println);
        }else{
            writer.println("Il n\'y a pas de record avec de mauvais nom : ");
        }
        writer.println();
    }

    public void printDuplicateName(PrintWriter writer){
        if(this.duplicateName.size()>0){
            writer.println("Records avec des doublons de numéro : ");
            for(String fullName : this.duplicateName.keySet()){
                String fullNameFormat = fullName.replace(".", "");
                writer.println("Pour \""+fullNameFormat+"\" :");
                this.duplicateName.get(fullName).forEach(writer::println);
            }
        }else{
            writer.println("Il n\'y a pas de doublons de nom ");
        }
        writer.println();
    }

}
