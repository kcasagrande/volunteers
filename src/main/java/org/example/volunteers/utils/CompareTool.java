package org.example.volunteers.utils;

import org.example.volunteers.model.Person;
import java.lang.reflect.Field;

/**
 * Compare tool, contains methods used to compare element
 */
public class CompareTool {
    /**
     * Private constructor, can't instantiate CompareTool
     */
    private CompareTool() {}

    /**
     * Start of call chain.
     * If fields comparison percentage is greater than 54% of similarity, persons are same
     * Compare fields doesn't compare if name / surname value are switched, then compareNameSurname compare their value
     *
     * @param person1 First person to compare
     * @param person2 Second person to compare
     * @return Boolean to indicate if compared persons are same
     */
    public static boolean comparePersons(Person person1, Person person2){
        try {
            return compareFields(person1, person2) > 0.70 || compareNameSurname(person1, person2);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Compare person's names and person's surnames
     * @param person1 First person to compare
     * @param person2 Second person to compare
     * @return Boolean if name & surname are same
     */
    private static boolean compareNameSurname(Person person1, Person person2){
        var compareResultSurnameXName = StringSimilarity.similarity(person1.getSurname(), person2.getName()) > 0.7;
        var compareResultSurname = StringSimilarity.similarity(person1.getSurname(), person2.getSurname()) > 0.7;
        var compareResultName = StringSimilarity.similarity(person1.getName(), person2.getName()) > 0.7;

        return compareResultName && compareResultSurname || compareResultSurnameXName;
    }

    /**
     * Compare persons fields, if field is empty, it isn't taken in account
     * @param person1 First person to compare
     * @param person2 Second person to compare
     * @return Percentage of similarity
     * @throws IllegalAccessException Throw if it can't access class field
     */
    public static double compareFields(Person person1, Person person2) throws IllegalAccessException {
        double totalSimilarity = 0;
        int amountFields = 0;

        for(Field field : Person.class.getDeclaredFields()){
            field.setAccessible(true);

            if(!field.get(person1).toString().equals("") && !field.get(person2).toString().equals("")){
                var similarity = StringSimilarity.similarity(field.get(person1).toString(), field.get(person2).toString());
                totalSimilarity += similarity;
                amountFields++;
            }
        }

        return totalSimilarity/amountFields;
    }
}
