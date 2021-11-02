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

        for(int i=0; i < lines.size()-1; i++){
            for(int j=0; j < lines.size()-1; j++){
                if(i == j){
                    j += 1;
                    if(lines.get(i)[4].equalsIgnoreCase(lines.get(j)[4])
                            && !lines.get(i)[4].isEmpty()
                        || lines.get(i)[3].equalsIgnoreCase(lines.get(j)[3])
                            && !lines.get(i)[3].isEmpty()
                        || lines.get(i)[0].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[1])
                        || lines.get(i)[0].equalsIgnoreCase(lines.get(j)[1])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[0])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[2])
                            && lines.get(i)[0].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[1])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[0].equalsIgnoreCase(lines.get(j)[2])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[1])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[1])
                            && lines.get(i)[0].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[2])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[2])
                            && !lines.get(i)[2].isEmpty()
                            || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[0])
                            && !lines.get(i)[2].isEmpty()
                            || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[1])
                            && !lines.get(i)[2].isEmpty()
                    )
                    {
                        lines.remove(lines.get(j));
                        return "same";
                    }
                }



            }
        }
        return "not same";
    }

    public static void VoidedCompareList(List<String[]> lines){

        for(int i=0; i < lines.size()-1; i++){
            for(int j=0; j < lines.size()-1; j++){
                if(i == j){
                    j += 1;
                    if(lines.get(i)[4].equalsIgnoreCase(lines.get(j)[4])
                            && !lines.get(i)[4].isEmpty()
                        || lines.get(i)[3].equalsIgnoreCase(lines.get(j)[3])
                            && !lines.get(i)[3].isEmpty()
                        || lines.get(i)[0].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[1])
                        || lines.get(i)[0].equalsIgnoreCase(lines.get(j)[1])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[0])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[2])
                            && lines.get(i)[0].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[1])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[0].equalsIgnoreCase(lines.get(j)[2])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[1])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[1])
                            && lines.get(i)[0].equalsIgnoreCase(lines.get(j)[0])
                            && lines.get(i)[1].equalsIgnoreCase(lines.get(j)[2])
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[2])
                            && !lines.get(i)[2].isEmpty()
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[0])
                            && !lines.get(i)[2].isEmpty()
                        || lines.get(i)[2].equalsIgnoreCase(lines.get(j)[1])
                            && !lines.get(i)[2].isEmpty()
                    )
                    {
                        lines.remove(lines.get(j));
                    }
                }
            }
        }
    }


}
