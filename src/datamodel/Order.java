package datamodel;

import java.util.*;

/**
 * Class of entity type <i>Order</i>.
 * <p>
 * Order defines a relationship between a Customer and the seller over items to
 * purchase.
 * </p>
 * 
 * @version <code style=
 *          color:green>{@value application.package_info#Version}</code>
 * @author <code style=
 *         color:blue>{@value application.package_info#Author}</code>
 */
public class Order {

    private static final long LOWER_BOUND = new GregorianCalendar(2020, Calendar.JANUARY, 1, 0, 0, 0).getTimeInMillis();
    private static final long UPPER_BOUND = new GregorianCalendar().getTimeInMillis() + 86400000L; // today + 1 day
    private static final String OUT_OF_BOUNDS_MSG = "invalid datetime argument (outside bounds 01/01/2020, 00:00:00 <= datetime <= today +1 day, 23:59:59).";

    private String id = null;
    private final Customer customer;
    private final Date created;
    private final List<OrderItem> items;

    public Order(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer empty.");
        }
        if (customer.getId() == null) {
            throw new IllegalArgumentException("Customer has invalid id.");
        }
        this.items = new ArrayList<>();
        this.customer = customer;
        this.created = new Date();
    }

    public String getId() {
        return id;
    }

    public Order setId(String id) {
        if (this.id != null) {
            return this;
        }
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("invalid id (null or \"\").");
        }
        this.id = id;
        return this;
    }

    public Customer getCustomer() {
        return customer;
    }

    public long getCreationDate() {
        return created.getTime();
    }

    public Order setCreationDate(long datetime) {
        if (datetime < LOWER_BOUND || datetime > UPPER_BOUND) {
            throw new IllegalArgumentException(OUT_OF_BOUNDS_MSG);
        }
        this.created.setTime(datetime);
        return this;
    }

    public int itemsCount() {
        return items.size();
    }

    public Iterable<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Order addItem(Article article, int units) {
        if (article == null || units <= 0) {
            throw new IllegalArgumentException("Invalid article or units.");
        }
        items.add(new OrderItem(article, units));
        return this;
    }

    public void deleteItem(int i) {
        if (i >= 0 && i < items.size()) {
            items.remove(i);
        }
    }

    public void deleteAllItems() {
        items.clear();
    }
}
