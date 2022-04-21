package org.example.volunteers;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
    public String firstName;
    public String lastName;
    public String nickName;
    public String email;
    public String phone;

    public String cleanFirstName;
    public String cleanLastName;
    public String cleanNickName;
    public String cleanEmail;
    public String cleanPhone;

    public Volunteer(
            String firstName,
            String lastName,
            String nickName,
            String email,
            String phone

       /* String cleanFirstName,
        String cleanLastName,
        String cleanNickName,
        String cleanEmail,
        String cleanPhone*/
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.email = email;
        this.phone = phone;

        this.cleanFirstName = firstName.toLowerCase().trim();
        this.cleanLastName = lastName.toLowerCase().trim();
        this.cleanNickName = nickName.toLowerCase().trim();
        this.cleanEmail = email.toLowerCase().trim();
        this.cleanPhone = Cleaner.cleanPhoneNumber(phone);
    }

    public static Volunteer fromString(String string) {
        String[] volunteer = string.split(";", -1);
        return new Volunteer(!volunteer[1].isEmpty()?volunteer[1]:"",!volunteer[0].isEmpty()?volunteer[0]:"",!volunteer[2].isEmpty()?volunteer[2]:"",!volunteer[3].isEmpty()?volunteer[3]:"",!volunteer[4].isEmpty()?volunteer[4]:"");
    }

    @Override
    public String toString() {
        return Arrays.stream(new String[]{firstName,lastName,nickName,email,phone})
                .map(attribute -> String.format("\"%s\"", attribute))
                .collect(joining(";"));
    }

    public String toCleanString() {
        return Arrays.stream(new String[]{cleanFirstName,cleanLastName,cleanNickName,cleanEmail,cleanPhone})
                .map(attribute -> String.format("\"%s\"", attribute))
                .collect(joining(";"));
    }

    @Override
    public int hashCode() {
        //return Objects.hash(cleanFirstName, cleanLastName, cleanNickName, cleanEmail, cleanPhone);
        return Objects.hash("");
    }

    @Override
    public boolean equals(Object o) {

        // Dans le equals on peut d'abord regarder les emails de la liste de volunteers.
        // Si les mails sont identiques ou moins de deux fautes, on prend la ligne avec le moins de valeurs vides pour la comparer aux autres. On compare ensuite le nom de famille.
        //      Sinon = enregistrement de deux volunteers différents
        // Si les noms de familles sont identiques ou moins de deux fautes, on compare ensuite le prénom.
        //      Sinon = enregistrement de deux volunteers différents
        // Si les prénoms sont identiques ou moins de deux fautes, on compare ensuite le numéro de tel.
        //      Sinon = enregistrement de deux volunteers différents
        // Si les num de tel sont identiques ou moins de deux fautes il s'agit de la même personne.
        //      Sinon = il s'agit du même volunteer mais ayant peut être changé de num, on met les deux nums.

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Volunteer volunteer = (Volunteer) o;
        System.out.println(this.toCleanString());
        System.out.println(volunteer.toCleanString());

        if(asOnlyMail(volunteer) || asOnlyMail(this)){
            retreiveDataFromMail(cleanEmail);
        }

        if(isStrictEgale(volunteer,this)){
            return true;
        } else if (asStrictInvertedLastnameAndFirstName(volunteer,this)){
            return true;
        } else if (compareWithName(this,volunteer)){
            removeEmptyParameter(volunteer);
            return true;
        } else if(compareWithNick(this,volunteer)){
            removeEmptyParameter(volunteer);
            return true;
        } else if(didEmailContainIdentifier(this,volunteer) && cleanPhone.equals(volunteer.cleanPhone)){
            return true;
        }
        return false;
    }

    public boolean asOnlyMail(Volunteer volunteer){
        return !volunteer.cleanEmail.isEmpty() && (volunteer.cleanFirstName.isEmpty() || volunteer.cleanLastName.isEmpty() || volunteer.cleanNickName.isEmpty());
    }

    public boolean compareWithName(Volunteer vol1,Volunteer vol2){
        return LevenshteinDistanceDP.asAcceptedMissspell(vol1.cleanFirstName, vol2.cleanFirstName)
                && LevenshteinDistanceDP.asAcceptedMissspell(vol1.cleanLastName, vol2.cleanLastName)
                && (LevenshteinDistanceDP.asAcceptedMissspell(vol1.cleanEmail, vol2.cleanEmail) || (LevenshteinDistanceDP.asAcceptedMissspell(vol1.cleanNickName, vol2.cleanNickName) || vol1.cleanPhone.equals(vol2.cleanPhone)));
    }
    public boolean compareWithNick(Volunteer vol1,Volunteer vol2){
        return LevenshteinDistanceDP.asAcceptedMissspell(vol1.cleanNickName, vol2.cleanNickName)
                && (LevenshteinDistanceDP.asAcceptedMissspell(vol1.cleanEmail, vol2.cleanEmail) ||  vol1.cleanPhone.equals(vol2.cleanPhone));
    }

    public boolean asAtLeastOneIdentifier(Volunteer vol){
        return (!vol.cleanFirstName.isEmpty() && !vol.cleanLastName.isEmpty() && !vol.cleanNickName.isEmpty());

    }

    public boolean didEmailContainIdentifier(Volunteer vol1,Volunteer vol2){
        if(asAtLeastOneIdentifier(vol1)){
            return (vol1.cleanEmail.contains(cleanFirstName) && vol1.cleanEmail.contains(cleanLastName) ) || vol1.cleanEmail.contains(cleanNickName);
        } else if(asAtLeastOneIdentifier(vol2)){
            return (vol2.cleanEmail.contains(cleanFirstName) && vol2.cleanEmail.contains(cleanLastName) ) || vol2.cleanEmail.contains(cleanNickName);
        }
        return false;

    }


    public boolean isStrictEgale(Volunteer vol1,Volunteer vol2){
        return Objects.equals(vol1.cleanFirstName, vol2.cleanFirstName)
                && Objects.equals(vol1.cleanLastName, vol2.cleanLastName)
                && Objects.equals(vol1.cleanNickName, vol2.cleanNickName)
                && Objects.equals(vol1.cleanEmail, vol2.cleanEmail)
                && Objects.equals(vol1.cleanPhone, vol2.cleanPhone);
    }

    public  boolean asStrictInvertedLastnameAndFirstName(Volunteer vol1,Volunteer vol2){
        return Objects.equals(vol1.cleanLastName, vol2.cleanFirstName)
                && Objects.equals(vol1.cleanFirstName, vol2.cleanLastName);
    }
    public void removeEmptyParameter(Volunteer volunteer){
        if(cleanEmail.isEmpty() && !volunteer.cleanEmail.isEmpty()){
            cleanEmail = volunteer.cleanEmail;
            email = volunteer.email;
        }
        if(cleanFirstName.isEmpty() && !volunteer.cleanFirstName.isEmpty()){
            cleanFirstName = volunteer.cleanFirstName;
            firstName = volunteer.firstName;
        }
        if(cleanLastName.isEmpty() && !volunteer.cleanLastName.isEmpty()){
            cleanLastName = volunteer.cleanLastName;
            lastName = volunteer.lastName;
        }
        if( cleanPhone.isEmpty() && !volunteer.cleanPhone.isEmpty() ){
            cleanPhone = volunteer.cleanPhone;
            phone = volunteer.phone;
        }
        if(cleanNickName.isEmpty() && !volunteer.cleanNickName.isEmpty()){
            cleanNickName = volunteer.cleanNickName;
            nickName = volunteer.nickName;
        }
    }

    public void retreiveDataFromMail(String email){

        String wDomaine = email.split("@")[0];
        String[] retreivedInfo = wDomaine.split(Pattern.quote("."));
        if(retreivedInfo.length == 1){
            retreivedInfo = retreivedInfo[0].split("_");
        }
        if(retreivedInfo.length > 1){

            if(cleanFirstName.contains(retreivedInfo[1]) && cleanLastName.isEmpty()){
                cleanLastName = retreivedInfo[1];
                lastName = retreivedInfo[1];
            }
            if(cleanFirstName.contains(retreivedInfo[1]) && cleanLastName.isEmpty()){
                cleanLastName = retreivedInfo[1];
                lastName = retreivedInfo[1];
            }
            if(cleanLastName.equals(retreivedInfo[0]) && cleanFirstName.isEmpty()){
                cleanFirstName = retreivedInfo[0];
                firstName = retreivedInfo[0];
            }

            if(cleanLastName.isEmpty() && cleanFirstName.isEmpty()){
                cleanFirstName = retreivedInfo[0];
                firstName = retreivedInfo[0];

                cleanLastName = retreivedInfo[1];
                lastName = retreivedInfo[1];
            }

        } else{
            if(!this.firstName.isEmpty() && retreivedInfo[0].contains(this.firstName)){
                lastName = retreivedInfo[0].substring(firstName.length());
                cleanLastName = retreivedInfo[0].substring(firstName.length());
            } else if(!this.lastName.isEmpty() && retreivedInfo[0].contains(this.lastName)){
                firstName = retreivedInfo[0].substring(lastName.length());
                cleanFirstName = retreivedInfo[0].substring(lastName.length());
            }
            
        }
    }

    public  boolean VolunteerFirstNameEqualLastname(String lastname){

        if(LevenshteinDistanceDP.compute_Levenshtein_distanceDP(this.firstName, lastname)<3){
            return true;
        }else{
            return false;
        }


    }
    public  boolean VolunteerLastNameEqualFirstname(String firstName){
        if(LevenshteinDistanceDP.compute_Levenshtein_distanceDP(this.lastName, firstName)<3){
            return true;
        }else{
            return false;
        }
    }

    public  boolean VolunteerFirstnameEqual(String firstName){
        return this.firstName.equals(firstName);
    }
    public  boolean VolunteerLaststnameEqual(String lastname){
        return this.lastName.equals(lastname);
    }

    public  boolean MatchingMailNickName(Volunteer v){
        if(this.email.equalsIgnoreCase(v.email) && this.nickName.equalsIgnoreCase(v.nickName))
        {
            return true;
        }
        return false;
    }
}
