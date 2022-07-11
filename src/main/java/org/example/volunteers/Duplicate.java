package org.example.volunteers;

import java.util.List;

public class Duplicate {
    public Number volunteerId;
    public List duplicates;
    public Number count;

    public Duplicate(Number volunteerId, List duplicates, Number count) {
        this.volunteerId = volunteerId;
        this.duplicates = duplicates;
        this.count = count;
    }
}
