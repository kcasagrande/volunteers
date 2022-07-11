package org.example.volunteers.models;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class VolunteerPhoneNumberError {

    public List<Volunteer> noPhoneNumber ;
    public HashMap<String , List<Volunteer>> duplicatePhoneNumber ;
    public List<Volunteer> badFormatPhoneNumber ;

    public VolunteerPhoneNumberError(List<Volunteer> noPhoneNumber , HashMap<String , List<Volunteer>> duplicatePhoneNumber  , List<Volunteer> badFormatPhoneNumber){
        this.noPhoneNumber = noPhoneNumber;
        this.duplicatePhoneNumber = duplicatePhoneNumber;
        this.badFormatPhoneNumber = badFormatPhoneNumber;
    }

    public void print(PrintWriter writer){
        this.printNoPhoneNumber(writer);
        this.printBadFormatPhoneNumber(writer);
        this.printDuplicatePhoneNumber(writer);
    }

    public void printNoPhoneNumber(PrintWriter writer){
        if(this.noPhoneNumber.size()>0){
            writer.println("Records sans numéro : ");
            this.noPhoneNumber.forEach(writer::println);
        }else{
            writer.println("Il n\'y a pas de record sans numéro : ");
        }
        writer.println();
    }

    public void printDuplicatePhoneNumber(PrintWriter writer){
        if(this.duplicatePhoneNumber.size()>0){
            writer.println("Records avec des doublons de numéro : ");
            for(String phoneNumber : this.duplicatePhoneNumber.keySet()){
                writer.println("Pour le numéro \""+phoneNumber+"\" :");
                this.duplicatePhoneNumber.get(phoneNumber).forEach(writer::println);
            }
        }else{
            writer.println("Il n\'y a pas de doublons de numéro ");
        }
        writer.println();
    }

    public void printBadFormatPhoneNumber(PrintWriter writer){
        if(this.badFormatPhoneNumber.size()>0){
            writer.println("Records avec un mauvais format de numéro:");
            this.badFormatPhoneNumber.forEach(writer::println);
        }else{
            writer.println("Il n\'y a pas de record sans mauvais format de numéro");
        }
        writer.println();
    }

}
