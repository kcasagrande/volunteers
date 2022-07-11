package org.example.volunteers.models;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VolunteerEmailError {

    public List<Volunteer> noEmail ;
    public HashMap<String , List<Volunteer>> duplicateEmail ;
    public List<Volunteer> badFormatEmail ;

    public VolunteerEmailError( List<Volunteer> noEmail ,HashMap<String , List<Volunteer>> duplicateEmail  ,   List<Volunteer> badFormatEmail){
        this.noEmail = noEmail;
        this.duplicateEmail = duplicateEmail;
        this.badFormatEmail = badFormatEmail;
    }

    public void print(PrintWriter writer){
        this.printNoEmail(writer);
        this.printBadFormatEmail(writer);
        this.printDuplicateEmail(writer);
    }

    public void printNoEmail(PrintWriter writer){
        if(this.noEmail.size()>0){
            writer.println("Records sans email : ");
            this.noEmail.forEach(writer::println);
        }else{
            writer.println("Il n\'y a pas de record sans email : ");
        }
        writer.println();
    }

    public void printDuplicateEmail(PrintWriter writer){
        if(this.duplicateEmail.size()>0){
            writer.println("Records avec des doublons d'emails : ");
            for(String email : this.duplicateEmail.keySet()){
                writer.println("Pour l\'email \""+email+"\" :");
                this.duplicateEmail.get(email).forEach(writer::println);
            }
        }else{
            writer.println("Il n\'y a pas de doublons d'email ");
        }
        writer.println();
    }

    public void printBadFormatEmail(PrintWriter writer){
        if(this.badFormatEmail.size()>0){
            writer.println("Records avec un mauvais format d'email:");
            this.badFormatEmail.forEach(writer::println);
        }else{
            writer.println("Il n\'y a pas de record sans mauvais format d\'email");
        }
        writer.println();
    }

}
