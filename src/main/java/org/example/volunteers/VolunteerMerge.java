package org.example.volunteers;

import org.example.volunteers.entity.Volunteer;

import java.util.Optional;

public class VolunteerMerge {

	private static String getValue(String value1, String value2){
		return Optional.ofNullable(value1).filter(s -> !s.equals("")).orElse(value2);
	}

	public static Volunteer merge(Volunteer merged, Volunteer in) {
		return new Volunteer(
			getValue(in.firstName, merged.firstName),
			getValue(in.lastName, merged.lastName),
			getValue(in.nickName, merged.nickName),
			getValue(in.eMail, merged.eMail),
			getValue(in.phone, merged.phone)
		);
	}
}
