package org.example.volunteers.comparison;

import org.example.volunteers.UserMock;

import java.util.List;

public class Comparator {
    UserMock user1;
    UserMock user2;

    public static String Compare(UserMock user1, UserMock user2){
        if(user1.firstName.toLowerCase().equals(user2.firstName.toLowerCase()) && user1.lastName.toLowerCase().equals(user2.lastName.toLowerCase())
                || user1.firstName.toLowerCase().equals(user2.lastName.toLowerCase()) && user1.lastName.toLowerCase().equals(user2.firstName.toLowerCase())){
            return "same";
        }
        return "not same";
    }


    public static String CompareList(List<String[]> lines){

        if(VoidedCompareList(lines)){
            return "same";
        }
        return "not same";

    }

    public static boolean VoidedCompareList(List<String[]> lines){


        boolean removed = false;
        for(int i=0; i < lines.size(); i++){
            for(int j=0; j < lines.size(); j++){
                if(i == j) {
                    continue;
                }
                    if(ComparePhoneNumbers(lines.get(i), lines.get(j))
                        || CompareEmails(lines.get(i), lines.get(j))
                        || CompareSameFnLn(lines.get(i), lines.get(j))
                        || CompareInvertedFnLn(lines.get(i), lines.get(j))
                        || CompareSamePseudo(lines.get(i), lines.get(j))
                        || CompareLastNameInPseudo(lines.get(i), lines.get(j))
                        || CompareFirstNameInPseudo(lines.get(i), lines.get(j))
                        || ComparePseudoInLn(lines.get(i), lines.get(j))
                        || ComparePseudoInFn(lines.get(i), lines.get(j))
                    )
                    {
                        lines.remove(lines.get(j));
                        removed = true;
                    }
            }
        }
        return removed;
    }

    public static boolean CompareFirstNameInPseudo(String[] line, String[] line2){

        return line[0].equalsIgnoreCase(line2[0])
                && line[1].equalsIgnoreCase(line2[2])
                && !line[0].isEmpty()
                && !line[1].isEmpty();
    }

    public static boolean CompareLastNameInPseudo(String[] line, String[] line2){

        return line[0].equalsIgnoreCase(line2[2])
                && line[1].equalsIgnoreCase(line2[1])
                && !line[0].isEmpty()
                && !line[1].isEmpty();
    }

    public static boolean ComparePhoneNumbers(String[] line, String[] line2){

        return line[4].equalsIgnoreCase(line2[4])
                && !line[4].isEmpty();

    }

    public static boolean CompareEmails(String[] line, String[] line2){
        return line[3].equalsIgnoreCase(line2[3])
                && !line[3].isEmpty();

    }

    // Ici Fn = FirstName et Ln = LastName

    public static boolean CompareSameFnLn(String[] line, String[] line2){
        return line[0].equalsIgnoreCase(line2[0])
                && line[1].equalsIgnoreCase(line2[1])
                && !line[0].isEmpty()
                && !line[1].isEmpty();
    }

    public static boolean CompareInvertedFnLn(String[] line, String[] line2){
        return line[0].equalsIgnoreCase(line2[1])
                && line[1].equalsIgnoreCase(line2[0])
                && !line[0].isEmpty()
                && !line[1].isEmpty();
    }

    public static boolean CompareSamePseudo(String[] line, String[] line2){
        return line[2].equalsIgnoreCase(line2[2])
                && !line[2].isEmpty();
    }

    public static boolean ComparePseudoInLn(String[] line, String[] line2){
        return line[2].equalsIgnoreCase(line2[0])
                && !line[2].isEmpty();
    }

    public static boolean ComparePseudoInFn(String[] line, String[] line2){
        return line[2].equalsIgnoreCase(line2[1])
                && !line[2].isEmpty();
    }


}
