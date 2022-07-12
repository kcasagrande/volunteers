package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CleanerTest {

    @Test
    public void shouldMergeStrings() {
        String base = "Coucou";
        String current = "Au revoir";
        String result = Cleaner.mergeStrings(base,current);
        assertEquals(result,"Coucou,Au revoir");
    }

    @Test
    public void shouldMergeStringsWithEmptyStringCurrent() {
        String base = "Coucou";
        String current = "";
        String result = Cleaner.mergeStrings(base,current);
        assertEquals(result,"Coucou");
    }

    @Test
    public void shouldMergeStringsWithEmptyStringBase() {
        String base = "";
        String current = "Coucou";
        String result = Cleaner.mergeStrings(base,current);
        assertEquals(result,"Coucou");
    }

    @Test
    public void shouldDeleteDuplicateVolunteers() {
        Volunteer testVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer testVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer testVolunteer4 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer5 = new Volunteer("Matisse", "Livain Henry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer6 = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        baseList.add(testVolunteer5);
        baseList.add(testVolunteer6);
        verifyList.add(testVolunteer);
        verifyList.add(testVolunteer2);
        verifyList.add(testVolunteer3);
        verifyList.add(testVolunteer5);
        List<Volunteer> noDuplicateList =  Cleaner.handleDuplicates(baseList);
        assertEquals(true, verifyList.containsAll(noDuplicateList) && noDuplicateList.containsAll(verifyList));
    }


    @Test
    public void shouldNotDeleteDuplicateWhenNone() {
        Volunteer testVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer testVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer testVolunteer4 = new Volunteer("Matisse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        verifyList.add(testVolunteer);
        verifyList.add(testVolunteer2);
        verifyList.add(testVolunteer3);
        verifyList.add(testVolunteer4);
        List<Volunteer> noDuplicateList =  Cleaner.handleDuplicates(baseList);
        assertEquals(true, verifyList.containsAll(noDuplicateList) && noDuplicateList.containsAll(verifyList));
    }


    @Test
    public void shouldCleanupDeleteNoFormat() {
        Volunteer testVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer testVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer testVolunteer4 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer5 = new Volunteer("Matisse", "Livain Henry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer6 = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        baseList.add(testVolunteer5);
        baseList.add(testVolunteer6);
        verifyList.add(testVolunteer);
        verifyList.add(testVolunteer2);
        verifyList.add(testVolunteer3);
        verifyList.add(testVolunteer5);
        List<Volunteer> noDuplicateList =  Cleaner.cleanUp(baseList);
        assertEquals(true, verifyList.containsAll(noDuplicateList) && noDuplicateList.containsAll(verifyList));
    }

    @Test
    public void shouldNotCleanupDeleteNoFormat() {
        Volunteer testVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer testVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer testVolunteer4 = new Volunteer("Matisse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        verifyList.add(testVolunteer);
        verifyList.add(testVolunteer2);
        verifyList.add(testVolunteer3);
        verifyList.add(testVolunteer4);
        List<Volunteer> noDuplicateList =  Cleaner.cleanUp(baseList);
        assertEquals(true, verifyList.containsAll(noDuplicateList) && noDuplicateList.containsAll(verifyList));
    }



    @Test
    public void shouldCleanupDeleteAndFormat() {
        Volunteer testVolunteer = new Volunteer("AntOine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+330620/5678");
        Volunteer testVolunteer2 = new Volunteer("Emeline", "PAL",
                "Epa", "emeline.pal@Gmail.com"
                , "+3305(2658)575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040 465");
        Volunteer testVolunteer4 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+3305.2658575");
        Volunteer testVolunteer5 = new Volunteer("Matisse", "LiVain henry",
                "matli", "matisSe.livain@gmail.com"
                , "+330-52658-575");
        Volunteer testVolunteer6 = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");

        Volunteer verifyVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer verifyVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer verifyVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer verifyVolunteer4 = new Volunteer("Matisse", "Livain Henry",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        baseList.add(testVolunteer5);
        baseList.add(testVolunteer6);
        verifyList.add(verifyVolunteer);
        verifyList.add(verifyVolunteer2);
        verifyList.add(verifyVolunteer3);
        verifyList.add(verifyVolunteer4);
        List<Volunteer> noDuplicateList =  Cleaner.cleanUp(baseList);
        assertEquals(true, verifyList.containsAll(noDuplicateList) && noDuplicateList.containsAll(verifyList));
    }

    @Test
    public void shouldCleanupFormatNoDuplicate() {
        Volunteer testVolunteer = new Volunteer("AntOine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer testVolunteer2 = new Volunteer("EmeliNe", "PAL",
                "Epa", "emeline.pal@Gmail.com"
                , "+33052658-575");
        Volunteer testVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.RIVoire@gmail.com"
                , "+33060(40)465");
        Volunteer testVolunteer4 = new Volunteer("Matisse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658/575");

        Volunteer verifyVolunteer = new Volunteer("Antoine", "Bonin",
                "Abo", "antoine.bonin@gmail.com"
                , "+3306205678");
        Volunteer verifyVolunteer2 = new Volunteer("Emeline", "Pal",
                "Epa", "emeline.pal@gmail.com"
                , "+33052658575");
        Volunteer verifyVolunteer3 = new Volunteer("Dimitri", "Rivoire",
                "Dri", "dimitri.rivoire@gmail.com"
                , "+3306040465");
        Volunteer verifyVolunteer4 = new Volunteer("Matisse", "Livain",
                "matli", "matisse.livain@gmail.com"
                , "+33052658575");
        List<Volunteer> baseList = new ArrayList<Volunteer>();
        List<Volunteer> verifyList = new ArrayList<Volunteer>();
        baseList.add(testVolunteer);
        baseList.add(testVolunteer2);
        baseList.add(testVolunteer3);
        baseList.add(testVolunteer4);
        verifyList.add(verifyVolunteer);
        verifyList.add(verifyVolunteer2);
        verifyList.add(verifyVolunteer3);
        verifyList.add(verifyVolunteer4);
        List<Volunteer> noDuplicateList =  Cleaner.cleanUp(baseList);
        assertEquals(true, verifyList.containsAll(noDuplicateList) && noDuplicateList.containsAll(verifyList));
    }
}
