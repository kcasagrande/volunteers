package org.example.volunteers.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {
    @Test
    public void testNormalizePhoneNumber() {
        String expectedPhoneNumber = "0768580037";

        Client client = new Client("", "","","","+33 (0) 7 68-58 00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","+33 0 7 68-58 00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","+33 7 68-58 00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","+33 768580037");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","+33768580037");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","07 68 58 00 37");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","07.68.58.00.37");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","07-68-58-00-37");
        assertEquals(client.getPhone(), expectedPhoneNumber);

        client = new Client("", "","","","+33 7 68 58 00 37");
        assertEquals(client.getPhone(), expectedPhoneNumber);
    }
}
