package org.example.volunteers;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.joining;

public final class Volunteer {
	private String firstName;
	private String lastName;
	private String nickName;
	private String eMail;
	private String phone;

	private Set<String> emails = new HashSet<>();
	private Set<String> phones = new HashSet<>();

	public Volunteer(
			String firstName,
			String lastName,
			String nickName,
			String eMail,
			String phone
	) {
		this.firstName = formatNames(firstName);
		this.lastName = formatNames(lastName);
		this.nickName = formatNames(nickName);
		this.eMail = formatEmail(eMail);
		this.addEmail(this.eMail);
		this.phone = formatPhone(phone);
		this.addPhone(this.phone);
	}

	public void addEmail(String email) {
		if (email == null) return;
		this.emails.add(email);
	}

	public void addPhone(String phone) {
		if (phone == null) return;
		this.phones.add(phone);
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public String geteMail() {
		return this.eMail;
	}

	public String getPhone() {
		return this.phone;
	}

	public Set<String> getEmails() {
		return this.emails;
	}

	public Set<String> getPhones() {
		return this.phones;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	public static String formatNames(String input) {
		if (input == null || input.length() == 0) return null;
		String upperCasedString = input.toUpperCase();
		return Normalizer.normalize(upperCasedString, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}

	public static String formatEmail(String input) {
		if (input == null || input.length() == 0) return null;

		boolean isValid = Pattern.
				compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
				.matcher(input).matches();
		if (!isValid) return null;
		return input;
	}

	public static String formatPhone(String input) {
		if (input == null || input.length() == 0) return null;

		String validString = input
				.replace(".", "")
				.replace("-", "")
				.replace(" ", "")
				.replace("(", "")
				.replace(")", "")
				.replace("_", "");


		if (validString.length() < 10) return null;

		if (!validString.startsWith("+")) {
			validString = "+33" + validString;
		}

		if (validString.length() > 13 || validString.length() < 12) return null;


		return validString;
	}


	@Override
	public String toString() {
		String csvLine = Arrays.stream(new String[]{firstName, lastName, nickName})
				.map(attribute -> String.format("\"%s\"", attribute))
				.collect(joining(";"));

		StringBuilder builder = new StringBuilder(csvLine);

		builder.append(";").append(emails.stream().collect(joining(","))).append(";").append(phones.stream().collect(joining(",")));

		return builder.toString();
	}

	public boolean isContactable() {
		return this.eMail != null || this.phone != null;
	}

	public boolean isFirstnameEquals(Volunteer vol) {
		if (this.firstName == null || vol.firstName == null) return false;
		return this.firstName.equals(vol.firstName);
	}

	public boolean isLastnameEquals(Volunteer vol) {
		if (this.lastName == null || vol.lastName == null) return false;
		return this.lastName.equals(vol.lastName);
	}


	public boolean hasCommonEmails(Volunteer vol) {
		for (String email : this.getEmails()) {
			for (String volEmail : vol.getEmails()) {
				if (volEmail.equals(email)) return true;
			}
		}
		return false;
	}

	public boolean hasCommonPhones(Volunteer vol) {

		for (String phone : this.getPhones()) {
			for (String volPhone : vol.getPhones()) {
				if (volPhone.equals(phone))
					return true;
				;
			}
		}
		return false;
	}


	public void mergeVolunteerIdentities(Volunteer volunteer) {
		this.setFirstName(getNotNullString(getFirstName(), volunteer.getFirstName()));
		this.setLastName(getNotNullString(getLastName(), volunteer.getLastName()));
		this.setNickName(getNotNullString(getNickName(), volunteer.getNickName()));
		this.phones.addAll(volunteer.phones);
		this.emails.addAll(volunteer.emails);
	}

	private String getNotNullString(String st1, String st2) {
		if (st1 == null && st2 == null) return null;
		if (st1 != null) return st1;
		return st2;
	}

	private static boolean bothEntryAreNotNull(Volunteer v1, Volunteer v2) {
		return v1.getLastName() != null && v2.getLastName() != null && v1.getFirstName() != null && v2.getFirstName() != null;
	}

	public boolean isSamePerson(Volunteer volunteer) {
		if (StringUtils.bothStringsAreNull(getLastName(), volunteer.getLastName())) return false;
		if (bothEntryAreNotNull(this, volunteer)
				&& getLastName().equals(volunteer.getFirstName())
				&& getFirstName().equals(volunteer.getLastName()))
			return true;
		if (!isLastnameEquals(volunteer)) return false;
		if (StringUtils.hasJustOneNull(getFirstName(), volunteer.getFirstName())) return true;
		if (!isFirstnameEquals(volunteer)) return false;
		return true;
	}


//	(volunteer1.isLastnameEquals(volunteer2) || StringUtils.bothStringsAreNull(volunteer1.getLastName(), volunteer2.getLastName())) ||
//			(volunteer1.isFirstnameEquals(volunteer2) || StringUtils.hasJustOneNull(volunteer1.getFirstName(), volunteer2.getFirstName()))) {

}
