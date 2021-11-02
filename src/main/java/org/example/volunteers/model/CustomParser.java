package org.example.volunteers.model;

import java.util.List;

/**
 * Parser interface
 */
public interface CustomParser {
    /**
     * Parse method
     * @param inputRaw List of tab string to parse, from .csv file
     * @return Parsed parameters
     */
    Object parse(List<String[]> inputRaw);
}
