package model;

public enum PhoneNumberPattern {
    //+33(0)0.75.55.99.79
    pattern1("(\\+\\d{2})(\\(0\\))(0)(\\.\\d{2}){4}"),
    //+33(0)0-75-55-55-20
    pattern2("(\\+\\d{2})(\\(0\\))(0)(\\-\\d{2}){4}"),
    //+33055587491
    pattern3("(\\+\\d{2}\\d{1})(\\d{2}){4}"),
    //+33(0)0 85 55 67 37
    pattern4("(\\+\\d{2})(\\(0\\))(0)(\\ \\d{2}){4}"),
    //+33(0)000555091
    pattern5("(\\+\\d{2})(\\(0\\))(0)(\\d{2}){4}"),
    //+330 00 55 52 25
    pattern6("(\\+\\d{2}0)(\\ \\d{2}){4}"),
    //+330-55-55-66-33
    pattern7("(\\+\\d{2}0)(\\-\\d{2}){4}"),
    //+330.00.55.52.42
    pattern8("(\\+\\d{2}0)(\\.\\d{2}){4}"),
    //00 00 55 55 33
    pattern9("(\\d{2})(\\ \\d{2}){4}"),
    //00-35-55-85-21
    pattern10("(\\d{2})(\\-\\d{2}){4}"),
    //00.45.55.63.57
    pattern11("(\\d{2})(\\.\\d{2}){4}"),
    //0000555204
    pattern12("(\\d{2}){5}");
    private final String text;


    PhoneNumberPattern(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
