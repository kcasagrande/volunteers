package org.example.volunteers;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.joining;

public final class Volunteer {
	private final String firstName;
	private final String lastName;
	private final String nickName;
	private final String eMail;
	private final String phone;

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
		this.emails.add(this.eMail);
		this.phone = formatPhone(phone);
		this.phones.add(this.phone);
	}

	public void addEmail(String email) {
		if(email == null) return;
		this.emails.add(email);
	}

	public void addPhone(String phone) {
		if(phone == null) return;
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
		return Arrays.stream(new String[]{firstName, lastName, nickName, eMail, phone})
				.map(attribute -> String.format("\"%s\"", attribute))
				.collect(joining(";"));
	}

	public boolean isContactable() {
		return this.eMail != null || this.phone != null;
	}


	public boolean isFirstNameAndLastnameEquals(Volunteer vol) {
		if (this.firstName == null || vol.firstName == null || this.lastName == null || vol.lastName == null) return false;
		return this.firstName.equals(vol.firstName) && this.lastName.equals(vol.lastName);
	}

	public boolean isEmailAndPhoneTheSame(Volunteer vol) {
		return (this.eMail != null && vol.eMail != null && this.eMail.equals(vol.eMail))
				|| (this.phone != null && vol.phone != null && this.phone.equals(vol.phone));
	}


}
