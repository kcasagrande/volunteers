package org.example.volunteers.models;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class VolunteerEmailError {

    public List<Volunteer> noEmail ;
    public HashMap<String , List<Volunteer>> duplicateEmail;
    public List<Volunteer> badFormatEmail ;

    public VolunteerEmailError( List<Volunteer> noEmail ,HashMap<String , List<Volunteer>> duplicateEmail  ,  List<Volunteer> badFormatEmail){
        this.noEmail = noEmail;
        this.duplicateEmail = duplicateEmail;
        this.badFormatEmail = badFormatEmail;
    }


    public HashMap<Boolean,List<Volunteer>> cleanDuplicateEmail(){
        HashMap<Boolean,List<Volunteer>> cleanDuplicateEmail = new HashMap<>();
        List<Volunteer> cleanEmails = new ArrayList<>();
        List<Volunteer> badEmails = new ArrayList<>();

        for ( String email : this.duplicateEmail.keySet()){
              List<Volunteer> volunteers = this.duplicateEmail.get(email);
              for(Volunteer volunteer : volunteers){
                  if(!cleanEmails.stream().anyMatch(x-> x.equals(volunteer))){
                      List<Volunteer> sameVolonteers =  volunteers.stream().filter(x-> x.equals(volunteer)).collect(Collectors.toList());
                      if(sameVolonteers.size()>1){
                          cleanEmails.add(volunteer);
                      }else{
                          badEmails.add(volunteer);
                      }
                  }else{
                      badEmails.add(volunteer);
                  }
              }
        }
        cleanDuplicateEmail.put(true,cleanEmails);
        cleanDuplicateEmail.put(false,badEmails);
        return cleanDuplicateEmail;
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
