package org.example.volunteers.entity;

import org.example.volunteers.VolunteerCompare;

import java.util.Arrays;
import java.util.Objects;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
	public final String firstName;
	public final String lastName;
	public final String nickName;
	public final String eMail;
	public final String phone;

	public Volunteer(
			String firstName,
			String lastName,
			String nickName,
			String eMail,
			String phone
	) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.eMail = eMail;
		this.phone = phone;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Volunteer volunteer = (Volunteer) o;
		return firstName.equals(volunteer.firstName) && lastName.equals(volunteer.lastName) && nickName.equals(volunteer.nickName) && eMail.equals(volunteer.eMail) && phone.equals(volunteer.phone);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, nickName, eMail, phone);
	}

	@Override
	public String toString() {
		return Arrays.stream(new String[]{firstName, lastName, nickName, eMail, phone})
				.map(attribute -> String.format("\"%s\"", attribute))
				.collect(joining(";"));
	}
}
