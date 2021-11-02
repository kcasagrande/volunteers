package com.volunters;

import org.example.volunteers.model.Person;
import org.example.volunteers.service.PersonService;
import org.example.volunteers.utils.StringUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

public class StringUtilTest {

    private static StringUtil stringUtil;
    private static PersonService personService;

    @BeforeEach
    public void setUp() {

        stringUtil = new StringUtil();
        personService = new PersonService();
    }



    private static boolean test(String key, String lol) {
        Map<String, String> nonFormattedAndFormattedMap = new HashMap<>();
        nonFormattedAndFormattedMap.put("+33(0)0.75.55.99.79", "0075559979");
        nonFormattedAndFormattedMap.put("+33(0)0-75-55-55-20", "0075555520");
        nonFormattedAndFormattedMap.put("+33055587491", "0055587491");
        nonFormattedAndFormattedMap.put("+33(0)0 85 55 67 37", "0085556737");
        nonFormattedAndFormattedMap.put("+33(0)000555091", "0000555091");
        nonFormattedAndFormattedMap.put("+330 00 55 52 25", "0000555225");
        nonFormattedAndFormattedMap.put("+330-55-55-66-33", "0055556633");
        nonFormattedAndFormattedMap.put("+330.00.55.52.42", "0000555242");
        nonFormattedAndFormattedMap.put("00 00 55 55 33", "0000555533");
        nonFormattedAndFormattedMap.put("00-35-55-85-21", "0035558521");
        nonFormattedAndFormattedMap.put("00.45.55.63.57", "0045556357");
        nonFormattedAndFormattedMap.put("0000555204", "0000555204");

        return nonFormattedAndFormattedMap.get(key).equals(lol);

    }

    @ParameterizedTest
    @ValueSource(strings = {
            "+33(0)0.75.55.99.79",
            "+33(0)0-75-55-55-20",
            "+33055587491",
            "+33(0)0 85 55 67 37",
            "+33(0)000555091",
            "+330 00 55 52 25",
            "+330-55-55-66-33",
            "+330.00.55.52.42",
            "00 00 55 55 33",
            "00-35-55-85-21",
            "00.45.55.63.57",
            "0000555204"
    })
    public void testPhoneNumberFormatting(String phoneNumber) {
        System.out.println(stringUtil.refactorPhoneNumberString(phoneNumber));
        System.out.println(phoneNumber);
        assertTrue(test(phoneNumber, stringUtil.refactorPhoneNumberString(phoneNumber)));
    }
}
