package org.example.volunteers.model;

import java.util.List;

public interface CustomParser {
    Object parse(List<String[]> inputRaw);
}
