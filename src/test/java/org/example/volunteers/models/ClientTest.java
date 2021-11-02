package org.example.volunteers.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    private static final String expectedPhoneNumber = "0768580037";

    @Test
    public void testNormalizePhoneNumberWithSpaces() {
        Client client = new Client("", "","","","07 68 58 00 37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }

    @Test
    public void testNormalizePhoneNumberWithPoints() {
        Client client = new Client("", "","","","07.68.58.00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }

    @Test
    public void testNormalizePhoneNumberWithIndicatorAndPoints() {
        Client client = new Client("", "","","","+337.68.58.00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }

    @Test
    public void testNormalizePhoneNumberWithIndicatorAndFirstZeroBracketsDashLastSpacePoint() {
        Client client = new Client("", "","","","+33 (0) 7 68-58 00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }

    @Test
    public void testNormalizePhoneNumberWithIndicator() {
        Client client = new Client("", "","","","+33768580037");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }

    @Test
    public void testNormalizePhoneNumberWithIndicatorAndSpaces() {
        Client client = new Client("", "","","","+33 7 68 58 00 37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }

    @Test
    public void testNormalizePhoneNumberWithDashes() {
        Client client = new Client("", "","","","07-68-58-00-37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }

    @Test
    public void testNormalizePhoneNumberSpacesDashPoint() {
        Client client = new Client("", "","","","+33 0 7 68-58 00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }
}
