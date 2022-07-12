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
		this.firstName = StringUtils.formatNames(firstName);
		this.lastName = StringUtils.formatNames(lastName);
		this.nickName = StringUtils.formatNames(nickName);
		this.eMail = StringUtils.formatEmail(eMail);
		this.addEmail(this.eMail);
		this.phone = StringUtils.formatPhone(phone);
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


	@Override
	public String toString() {
		String csvLine = Arrays.stream(new String[]{firstName, lastName, nickName})
				.map(attribute -> String.format("\"%s\"", attribute))
				.collect(joining(";"));

		return csvLine +
				";" +
				String.format("\"%s\"", String.join(",", emails)) +
				";" +
				String.format("\"%s\"", String.join(",", phones));
	}

	public boolean isContactable() {
		return this.eMail != null || this.phone != null;
	}

	private boolean isFirstnameEquals(Volunteer vol) {
		if (this.firstName == null || vol.firstName == null) return false;
		return this.firstName.equals(vol.firstName);
	}

	private boolean isLastnameEquals(Volunteer vol) {
		if (this.lastName == null || vol.lastName == null) return false;
		return this.lastName.equals(vol.lastName);
	}


	private boolean hasCommonEmails(Volunteer vol) {
		for (String email : this.getEmails()) {
			if (vol.getEmails().contains(email)) {
				return true;
			}
		}
		return false;
	}

	private boolean hasCommonPhones(Volunteer vol) {
		for (String phone : this.getPhones()) {
			if (vol.getPhones().contains(phone)) {
				return true;
			}
		}
		return false;
	}


	public void mergeVolunteerIdentities(Volunteer vol) {
		this.setFirstName(StringUtils.getNotNullString(getFirstName(), vol.getFirstName()));
		this.setLastName(StringUtils.getNotNullString(getLastName(), vol.getLastName()));
		this.setNickName(StringUtils.getNotNullString(getNickName(), vol.getNickName()));
		this.phones.addAll(vol.phones);
		this.emails.addAll(vol.emails);
	}


	private static boolean bothEntryAreNotNull(Volunteer v1, Volunteer v2) {
		return v1.getLastName() != null && v2.getLastName() != null
				&& v1.getFirstName() != null && v2.getFirstName() != null;
	}

	public boolean isSamePerson(Volunteer vol) {
		if (!(this.hasCommonEmails(vol) || this.hasCommonPhones(vol))) return false;
		if (getNickName() != null && vol.getNickName() != null && getNickName().equals(vol.getNickName())) return true;
		if (StringUtils.bothStringsAreNull(getLastName(), vol.getLastName())) return false;
		if (bothEntryAreNotNull(this, vol)
				&& getLastName().equals(vol.getFirstName())
				&& getFirstName().equals(vol.getLastName()))
			return true;
		if (!isLastnameEquals(vol)) return false;
		if (StringUtils.hasJustOneNull(getFirstName(), vol.getFirstName())) return true;
		return isFirstnameEquals(vol);
	}
}
