package system.impl;

import datamodel.Customer;
import system.Formatter;

/**
 * Implementation class of the {@link system.Formatter} interface.
 * 
 * @version <code style=
 *          color:green>{@value application.package_info#Version}</code>
 * @author <code style=
 *         color:blue>{@value application.package_info#Author}</code>
 */
public class FormatterImpl implements Formatter {

	@Override
	public String fmtCustomerName(Customer customer, int... fmt) {
		if (customer == null) {
			return "";
		}

		String firstName = customer.getFirstName();
		String lastName = customer.getLastName();

		StringBuilder formattedName = new StringBuilder();

		if (fmt.length > 0 && fmt[0] == 10) {
			formattedName.append(lastName.toUpperCase()).append(", ").append(firstName.toUpperCase());
		} else if (fmt.length > 0 && fmt[0] == 11) {
			formattedName.append(firstName.toUpperCase()).append(" ").append(lastName.toUpperCase());
		} else if (fmt.length > 0 && fmt[0] == 12) {
			formattedName.append(lastName.toUpperCase()).append(", ").append(firstName.charAt(0)).append(".");
		} else if (fmt.length > 0 && fmt[0] == 13) {
			formattedName.append(firstName.charAt(0)).append(". ").append(lastName.toUpperCase());
		} else if (fmt.length > 0 && fmt[0] == 14) {
			formattedName.append(lastName.toUpperCase());
		} else if (fmt.length > 0 && fmt[0] == 15) {
			formattedName.append(firstName.toUpperCase());
		} else {
			formattedName.append(firstName).append(" ").append(lastName);
		}

		return formattedName.toString();
	}

	@Override
	public String fmtCustomerContacts(Customer customer, int... fmt) {
		if (customer == null) {
			return "";
		}

		Iterable<String> contacts = customer.getContacts();

		StringBuilder formattedContacts = new StringBuilder();

		if (fmt.length > 0 && fmt[0] == 1) {
			formattedContacts.append(getFirstContact(contacts));
			int count = countContacts(contacts) - 1;
			if (count > 0) {
				formattedContacts.append(", (+").append(count).append(" contacts)");
			}
		} else if (fmt.length > 0 && fmt[0] == 2) {
			for (String contact : contacts) {
				formattedContacts.append(contact).append(", ");
			}
			formattedContacts.setLength(formattedContacts.length() - 2);
		} else {
			formattedContacts.append(getFirstContact(contacts));
		}

		return formattedContacts.toString();
	}

	private String getFirstContact(Iterable<String> contacts) {
		for (String contact : contacts) {
			return contact;
		}
		return "";
	}

	private int countContacts(Iterable<String> contacts) {
		int count = 0;
		for (String contact : contacts) {
			count++;
		}
		return count;
	}

	@Override
	public String fmtPrice(long price, int... fmt) {
		if (fmt.length > 0 && fmt[0] == 1) {
			return String.format("%.2f EUR", (double) price / 100);
		} else if (fmt.length > 0 && fmt[0] == 2) {
			return String.format("%.2fEUR", (double) price / 100);
		} else if (fmt.length > 0 && fmt[0] == 3) {
			return String.format("%.2f€", (double) price / 100);
		} else if (fmt.length > 0 && fmt[0] == 4) {
			return String.format("%.2f$", (double) price / 100);
		} else if (fmt.length > 0 && fmt[0] == 5) {
			return String.format("%.2f£", (double) price / 100);
		} else if (fmt.length > 0 && fmt[0] == 6) {
			return String.format("%d¥", price);
		} else if (fmt.length > 0 && fmt[0] == 7) {
			return String.valueOf(price);
		} else {
			return String.format("%.2f", (double) price / 100);
		}
	}

	@Override
	public String fmtDecimal(long value, int decimalDigits, String... unit) {
		if (unit.length > 0) {
			return String.format("%." + decimalDigits + "f %s", (double) value / Math.pow(10, decimalDigits), unit[0]);
		} else {
			return String.format("%." + decimalDigits + "f", (double) value / Math.pow(10, decimalDigits));
		}
	}
}
