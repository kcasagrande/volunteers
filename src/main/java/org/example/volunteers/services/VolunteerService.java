package org.example.volunteers.services;

import org.example.volunteers.Volunteer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class VolunteerService {
    private static final List<Volunteer> Volunteers = new ArrayList<>();

    public VolunteerService(List<String[]> volunteers) {
        for(String[] data: volunteers) {
            Number id = Integer.parseInt(data[0]);
            try {
                Volunteers.add(createNewVolunteer(
                        id,
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5]
                ));
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public static Volunteer createNewVolunteer(Number id, String lastName, String firstName, String nickName, String email, String phone) throws Exception {
        if (lastName.isEmpty() && firstName.isEmpty() && nickName.isEmpty() && email.isEmpty() && phone.isEmpty()) {
            throw new Exception("Volunteer information are incomplete");
        }

        return new Volunteer(id, lastName, firstName, nickName, email, phone);
    }

    public void retrieveDuplicatedVolunteers() {
        AtomicInteger index = new AtomicInteger(0);
        for (Volunteer volunteer : Volunteers) {
            Integer i = index.incrementAndGet();
            List<Integer> duplicatesIndexes = retrieveDuplicatesVolunteersIndex(volunteer, i);
            deleteDuplicateVolunteers(duplicatesIndexes);
        }
    }

    public static List<Integer> retrieveDuplicatesVolunteersIndex(Volunteer currentVolunteer, Integer startIndex){
        List<Integer> indexes = new ArrayList<>();
        for (int i = startIndex; i < Volunteers.size(); i++) {
            Volunteer otherVolunteer = Volunteers.get(i);
            boolean result = compareVolunteers(currentVolunteer, otherVolunteer);
            if(result){
                indexes.add(i);
                mergeTwoVolunteersValue(currentVolunteer, otherVolunteer);
            }
        }
        return indexes;
    }

    public void deleteDuplicateVolunteers(List<Integer> duplicatesIndexes){
        Collections.reverse(duplicatesIndexes);
        for(Integer index : duplicatesIndexes) {
            Volunteers.remove((int)index);
        }
    }

    public static boolean compareVolunteers(Volunteer firstVolunteer, Volunteer secondVolunteer){
        if(compareTwoVolunteersValue(firstVolunteer.getPhone(), secondVolunteer.getPhone())) return true;
        if(compareTwoVolunteersValue(firstVolunteer.getEMail(), secondVolunteer.getEMail())) return true;
        if(compareTwoVolunteersValue(firstVolunteer.getNickName(), secondVolunteer.getNickName())) return true;
        if(compareTwoVolunteersValue(firstVolunteer.getFullName(), secondVolunteer.getFullName())) return true;

        return false;
    }

    protected static boolean compareTwoVolunteersValue(String firstValue, String secondValue){
        return (!Objects.equals(firstValue, "") && !Objects.equals(secondValue, "")) && firstValue.equals(secondValue);
    }

    public static void mergeTwoVolunteersValue(Volunteer firstVolunteer, Volunteer secondVolunteer) {
        try {
            Field[] volunteerFields = Volunteer.class.getDeclaredFields();
            for (Field vField : volunteerFields) {
                if (vField.get(firstVolunteer) == "" && vField.get(secondVolunteer) != "") {
                    vField.set(firstVolunteer, vField.get(secondVolunteer));
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Volunteer> getVolunteers() {
        return Volunteers;
    }
}
