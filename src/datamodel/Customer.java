package datamodel;


/**
 * Class of entity type <i>Customer</i>.
 * <p>
 * Customer is a person who creates and holds (owns) orders in the system.
 * </p>
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
public class Customer {

    /**
     * TODO: need to add attributes.
     */

    /**
     * Default constructor.
     */
    public Customer() { }

    /**
     * Constructor with single-String name argument.
     * @param name single-String Customer name, e.g. "Eric Meyer"
     * @throws IllegalArgumentException if name argument is null
     */
    public Customer(String name) {
        // TODO implement here
    }

    /**
     * TODO: need to add javadoc.
     */

    public Long getId() { return 0L; }

    public Customer setId(long id) { return this; }

    public String getLastName() { return ""; }

    public String getFirstName() { return ""; }

    public Customer setName(String first, String last) { return this; }

    public Customer setName(String name) { return this; }

    public int contactsCount() { return 0; }

    public Iterable<String> getContacts() { return java.util.List.of(); }

    public Customer addContact(String contact) { return this; }

    public void deleteContact(int i) { }

    public void deleteAllContacts() { }

    // private void splitName(String name) { }
}