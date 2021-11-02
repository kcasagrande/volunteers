import org.example.volunteers.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class testParse {

    @Test
    public void testParseTel() {
        String telTest = "+33085552814";
        String telObtenu = Utils.conversionTel(telTest);
        assertEquals("00.85.55.28.14", telObtenu);
    }

    @Test
    public void testParseTelCourt(){
        String telTest = "+3304";
        String telObtenu = Utils.conversionTel(telTest);
        assertEquals("", telObtenu);
    }

    @Test
    public void testPerseTelUsPlus(){
        String telTest = "+1 000 000 0000";
        String telObtenu = Utils.conversionTel(telTest);
        assertEquals("00.1.000.000.0000", telObtenu);
    }

    @Test
    public void testPerseTelUsZero(){
        String telTest = "00 1 000 000 0000";
        String telObtenu = Utils.conversionTel(telTest);
        assertEquals("00.1.000.000.0000", telObtenu);
    }

    @Test
    public void testPerseTelUs(){
        String telTest = "000 000 0000";
        String telObtenu = Utils.conversionTel(telTest);
        assertEquals("00.00.00.00.00", telObtenu);
    }

    @Test
    public void testParseMail() {
        String mailTest = "Tanguy.Reymbaut@test.fr";
        String mailObtenu = Utils.conversionMail(mailTest);
        assertEquals("Tanguy.Reymbaut@test.fr", mailObtenu);
    }

    @Test
    public void testParseMailA() {
        String mailObtenu =  Utils.conversionMail("mailTest@");
        assertEquals("", mailObtenu);
    }

    @Test
    public void testParseMailPoint() {
        String mailObtenu = Utils.conversionMail("mailTest.");
        assertEquals("", mailObtenu);
    }

    @Test
    public void testParseMailSimple() {
        String mailObtenu =  Utils.conversionMail("mailTest");
        assertEquals("", mailObtenu);
    }

    @Test
    public void testParseMailApoint() {
        String mailObtenu = Utils.conversionMail("@.");
        assertEquals("", mailObtenu);
    }

}
