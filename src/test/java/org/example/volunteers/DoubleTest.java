package org.example.volunteers;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DoubleTest {
    @Test
    public void mailDoubleOne() {
        Volunteer vol1 = new Volunteer("Jacquie1", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601765984");
        Volunteer vol2 = new Volunteer("Jacquie2", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601765984");
        Volunteer vol3 = new Volunteer("Jacquie3", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601765984");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedEmail(input);

        List<Volunteer> expected = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };

        assertEquals(expected, result, "3 volontaires avec la même email devrait retourner les 3 volontaires");
    }

    @Test
    public void mailDoubleTwo() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601765984");
        Volunteer vol2 = new Volunteer("Jack", "Lam", "jackie", "jacquie@inter.net", "+33601765444");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedEmail(input);
        List<Volunteer> expected = new ArrayList<>();

        assertEquals(expected, result, "2 volontaires différents, doit renvoyé un liste vide");
    }

    @Test
    public void phoneDoubleOne() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601765984");
        Volunteer vol2 = new Volunteer("Jack", "Lam", "jackie", "jacquie@inter.net", "+33601765984");
        Volunteer vol3 = new Volunteer("Jacky", "Lam", "jackiie", "jacquie22@inter.net", "+33601765984");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedPhone(input);

        List<Volunteer> expected = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };

        assertEquals(expected, result, "3 volontaires avec la même numéro de téléphone devrait retourner les 3 volontaires");
    }

    @Test
    public void phoneDoubleTwo() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601765984");
        Volunteer vol2 = new Volunteer("Jack", "Lam", "jackie", "jacquie@inter.net", "+33601765444");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedPhone(input);
        List<Volunteer> expected = new ArrayList<>();

        assertEquals(expected, result, "2 volontaires avec des numéro de téléphone différents devrait retourner une liste vide");
    }

    @Test
    public void firstNameDoubleOne() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("Jacquie", "Lam", "jackie", "jacquie@inter.net", "+33601765284");
        Volunteer vol3 = new Volunteer("Jacquie", "Lam", "jackiie", "jakie22@inter.net", "+33611765984");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedFirstName(input);

        List<Volunteer> expected = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };

        assertEquals(expected, result, "3 volontaires ayant le même prénom, doit renvoyé un tableau des 3 mêmes volontaires");
    }

    @Test
    public void firstNameDoubleTwo() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("Jackie", "Lam", "jackie", "jacquie@inter.net", "+33601765284");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedFirstName(input);
        List<Volunteer> expected = new ArrayList<>();

        assertEquals(expected, result, "2 volontaires dont le prénom est différents, doit renvoyé un tableau vide");
    }

    @Test
    public void lastNameDoubleOne() {
        Volunteer vol1 = new Volunteer("Jacquie", "LAMENASS", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("Jacky", "Lamenass", "jackie", "jacquie@inter.net", "+33601765284");
        Volunteer vol3 = new Volunteer("Jackie", "LAMENASS", "jackiie", "jakie22@inter.net", "+33611765984");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedLastName(input);

        List<Volunteer> expected = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };

        assertEquals(expected, result, "3 volontaires ayant le même nom, doit renvoyé un tableau des 3 mêmes volontaires");
    }

    @Test
    public void lastNameDoubleTwo() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("Jackie", "Lam", "jackie", "jacquie@inter.net", "+33601765284");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedLastName(input);
        List<Volunteer> expected = new ArrayList<>();

        assertEquals(expected, result, "2 volontaires dont le nom est différents, doit renvoyé un tableau vide");
    }

    @Test
    public void fullName() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("JACQUIE", "Lamenass", "jackie", "jacquie@inter.net", "+33601765284");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedNames(input);

        List<Volunteer> expected = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };

        assertEquals(expected, result, "2 volontaires avec le même nom et prénom, retourne les 2 volontaires");
    }

    @Test
    public void fullNameInversed() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("Lamenass", "Jacquie", "jackie", "jacquie@inter.net", "+33601765284");
        Volunteer vol3 = new Volunteer("Non", "Pasdouble", "nonon", "noonpasdouble@inter.net", "+33611165984");
        Volunteer vol4 = new Volunteer("Thierry", "Henry", "henryu", "thierry@inter.net", "+33101765184");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
                add(vol4);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedReverseNames(input);

        List<Volunteer> expected = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };

        assertEquals(expected, result, "4 volontaires dont 2 ayant les mêmes nom et prénom mais inversé, doit renvoyé les 2 volontaire identiques");
    }

    @Test
    public void nickNameDoubleOne() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamnass", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("Jacky", "Lamenass", "jackiller", "jacquie@inter.net", "+33601765284");
        Volunteer vol3 = new Volunteer("Jackie", "Lamenas", "jackiller", "jakie22@inter.net", "+33611765984");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedNickName(input);

        List<Volunteer> expected = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
                add(vol3);
            }
        };

        assertEquals(expected, result,"2 volontaires avec le même pseudo, retourne les volontaires dans ce cas là");
    }

    @Test
    public void nickNameDoubleTwo() {
        Volunteer vol1 = new Volunteer("Jacquie", "Lamenass", "jackiller", "jacquie.michelle@inter.net", "+33601865984");
        Volunteer vol2 = new Volunteer("Jackie", "Lam", "jackie", "jacquie@inter.net", "+33601765284");
        List<Volunteer> input = new ArrayList<Volunteer>() {
            {
                add(vol1);
                add(vol2);
            }
        };
        List<Volunteer> result = Cleaner.getDuplicatedNickName(input);
        List<Volunteer> expected = new ArrayList<>();

        assertEquals(expected, result, "2 volontaires dont le nickname est différents, doit renvoyé un tableau vide");
    }
}
