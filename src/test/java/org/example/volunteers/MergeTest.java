package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeTest {

    @Test
    public void test_mergeDuplicateByName() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Doe","John","Jojo","","043534534"));
        listUser.add(listUser.size(), new Volunteer("Doe","John","","john.doe@example.com",""));
        listUser.add(listUser.size(), new Volunteer("DOe","DO","","do.doe@example.com",""));
        listUser.add(listUser.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com",""));
        listUser.add(listUser.size(), new Volunteer("","","","Maxhild@en",""));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));

        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);

        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Doe","John","Jojo","john.doe@example.com","043534534"));
        listExpected.add(listExpected.size(), new Volunteer("DOe","DO","","do.doe@example.com",""));
        listExpected.add(listExpected.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com",""));
        listExpected.add(listExpected.size(), new Volunteer("","","","Maxhild@en",""));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));

        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeDuplicateByPhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("MC","EV","","mark.evian@gmail.com","04697811"));
        listUser.add(listUser.size(), new Volunteer("Doe","John","Jojo","john.doe@example.com","04697811"));
        listUser.add(listUser.size(), new Volunteer("DOe","DO","","do.doe@example.com","34423"));
        listUser.add(listUser.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com","54554"));
        listUser.add(listUser.size(), new Volunteer("","","","Maxhild@en","234234"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com","01324"));

        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByPhoneNumber(listUser);

        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("MC","EV","Jojo","mark.evian@gmail.com,john.doe@example.com","04697811"));
        listExpected.add(listExpected.size(), new Volunteer("DOe","DO","","do.doe@example.com","34423"));
        listExpected.add(listExpected.size(), new Volunteer("Mark","Evian","","mark.evian@gmail.com","54554"));
        listExpected.add(listExpected.size(), new Volunteer("","","","Maxhild@en","234234"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com","01324"));

        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeMispelledNameEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3309847821"));
        listUser.add(listUser.size(), new Volunteer("Murriel","Laurens","","muriel.laurens@example.net","3309847821"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.laurens@example.net","3309847821"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        assertEquals(listExpected, finalListUser);
    }

    @Test
    public void mergeMispelledNamePhoneTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Murriel","Laurens","","muriel.laurens@example.org","3309847821"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821,3309847821"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        assertEquals(listExpected, finalListUser);
    }
    @Test
    public void mergeMispelledNamePhoneAndEmailTest() {
        List<Volunteer> listUser = new ArrayList<>();
        listUser.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org","3307837821"));
        listUser.add(listUser.size(), new Volunteer("Murriel","Laurens","","muriel.laurens@example.net","3309847821"));
        listUser.add(listUser.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        Merge duplicate = new Merge();
        List<Volunteer> finalListUser = duplicate.mergeByName(listUser);
        List<Volunteer> listExpected = new ArrayList<>();
        listExpected.add(0, new Volunteer("Muriel","Laurens","","muriel.laurens@example.org,muriel.laurens@example.net","3307837821,3309847821"));
        listExpected.add(listExpected.size(), new Volunteer("To","To","Max","erg@fe.com",""));
        assertEquals(listExpected, finalListUser);
    }
}
