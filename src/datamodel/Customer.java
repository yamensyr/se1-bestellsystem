package datamodel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class of entity type <i>Customer</i>.
 * <p>
 * Customer is a person who creates and holds (owns) orders in the system.
 * </p>
 * 
 * @version <code style=
 *          color:green>{@value application.package_info#Version}</code>
 * @author <code style=
 *         color:blue>{@value application.package_info#Author}</code>
 */
public class Customer {
    private Long id = null;
    private String firstName = "";
    private String lastname = "";
    private int contactsCount = 0;
    private List<String> contacts = new ArrayList<>();

    /**
     * Default constructor.
     */
    public Customer() {
        this.id = null;
        this.firstName = "";
        this.lastname = "";
        this.contactsCount = 0;
        this.contacts = new ArrayList<>();
    }

    /**
     * Constructor with single-String name argument.
     * 
     * @param name single-String Customer name, e.g. "Eric Meyer"
     * @throws IllegalArgumentException if name argument is null
     */
    public Customer(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name null.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name empty.");
        }

        String[] splitName;
        if (name.contains(",")) {
            splitName = name.split(",");
            this.lastname = splitName[0].trim();
            this.firstName = splitName[1].trim();
        } else if (name.contains(";")) {
            splitName = name.split(";");
            this.lastname = splitName[0].trim();
            this.firstName = splitName[1].trim();
        } else if (name.contains(" ")) {
            splitName = name.split(" ");
            this.lastname = splitName[1].trim();
            this.firstName = splitName[0].trim();
        } else {
            this.firstName = "";
            this.lastname = name.trim();
        }

        this.id = null;
        this.contactsCount = 0;
        this.contacts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public Customer setId(Long id) {
        if (id < 0) {
            throw new IllegalArgumentException("invalid id (negative).");
        }
        if (this.id == null) {
            this.id = id;
        }
        return this;
    }

    public String getLastName() {
        return lastname;
    }

    public String getFirstName() {
        return firstName;
    }

    public Customer setName(String first, String last) {
        firstName = first;
        lastname = last;
        return this;
    }

    public Customer setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name null.");
        }
        if (name.isEmpty()) {
            throw new IllegalArgumentException("name empty.");
        }
        String[] splitName;
        if (name.contains(",")) {
            splitName = name.split(",");
            this.lastname = splitName[0].trim();
            this.firstName = splitName[1].trim();
        } else if (name.contains(";")) {
            splitName = name.split(";");
            this.lastname = splitName[0].trim();
            this.firstName = splitName[1].trim();
        } else if (name.contains(" ")) {
            splitName = name.split(" ");
            if (splitName.length > 2) {
                this.lastname = splitName[splitName.length - 1].trim();
                this.firstName = String.join(" ", Arrays.copyOfRange(splitName, 0, splitName.length - 1));
            } else {
                this.lastname = splitName[1].trim();
                this.firstName = splitName[0].trim();
            }

        } else {
            this.firstName = "";
            this.lastname = name.trim();
        }
        return this;
    }

    public int contactsCount() {
        contactsCount = contacts.size();
        return contactsCount;
    }

    public Iterable<String> getContacts() {
        return contacts;
    }

    public Customer addContact(String contact) {
        String beforeTrimming = contact;

        if (contact.startsWith("\"") && contact.endsWith("\"")) {
            contact = contact.substring(1, contact.length() - 1).trim();
        } else {
            contact = contact.trim();
        }

        if (contact.length() < 6) {
            throw new IllegalArgumentException("contact less than 6 characters: \"" + beforeTrimming + "\".");
        }
        contact = contact.replaceAll("^[\\s\"'.,;:]+|[\\s\"'.,;:]+$", "");

        if (!contacts.contains(contact)) {
            contacts.add(contact);
        }
        return this;
    }

    public void deleteContact(int i) {
        if (i >= 0 && i < contacts.size()) {
            contacts.remove(i);
        }
    }

    public void deleteAllContacts() {
        contacts.clear();
    }
}