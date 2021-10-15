package org.example.volunteers;

import java.util.List;

public class Verification {

    public Verification() {
    }

    public int NumberParam(String[] line) {
        int numberOfParams = 0;
        for (String param : line) {
            if(!param.isEmpty())
            {
                numberOfParams++;
            }
        }
        return numberOfParams;
    }

    public String[] FusionLineUnique(List<String[]> listeSimilitude) {
        return null;
    }
}
