package datamodel;

/**
 * Class of entity type <i>Article</i>.
 * <p>
 * Articles can be referenced in orders as ordered items .
 * </p>
 * 
 * @version <code style=
 *          color:green>{@value application.package_info#Version}</code>
 * @author <code style=
 *         color:blue>{@value application.package_info#Author}</code>
 */
public class Article {

    /**
     * Default constructor
     */
    // public Article() {
    // }

    /**
     * Unique article id, not null or empty "", id can be set only once
     */
    private String id = null;

    /**
     * Article description, never null, may be empty ""
     */
    private String description = "";

    /**
     * Price in cent per article, negative values are invalid
     */
    private long unitPrice = 0L;

    /**
     * Currency in which price is quoted, EUR is the default currency
     */
    private Currency currency = Currency.EUR;

    /**
     * Tax rate that applies to article, German regular Value-Added Tax (VAT) is
     * default
     */
    private TAX taxRate = TAX.GER_VAT;

    /**
     * Default constructor
     */
    public Article() {
        this.id = null;
        this.description = "";
        this.unitPrice = 0L;
        this.currency = Currency.EUR;
        this.taxRate = TAX.GER_VAT;
    }

    /**
     * Constructor with description and price.
     * 
     * @param description article name or descriptive text of article
     * @param unitPrice   price (in cent) for one unit of the article
     * @throws IllegalArgumentException when description is null or empty "" or
     *                                  price negative {@code < 0}.
     */

    public Article(String description, long unitPrice) {
        this.description = description;
        this.unitPrice = unitPrice;

        if (description == null) {
            throw new IllegalArgumentException("invalid description (null or \"\").");
        }
        if (description.isEmpty()) {
            throw new IllegalArgumentException("invalid description (null or \"\").");
        }
        if (unitPrice < 0) {
            throw new IllegalArgumentException("invalid unitPrice ( < 0).");
        }
    }

    /**
     * Id getter.
     * 
     * @return article order id, returns {@code null}, if id is unassigned
     */
    public String getId() {
        // if (id != null) {
        return id;
        // }
        // return null;
    }

    /**
     * Id setter. Id can only be set once with valid id, id is immutable after
     * assignment.
     * 
     * @param id only valid id (not null or "") updates id attribute on first
     *           invocation
     * @throws IllegalArgumentException if id argument is invalid ({@code id==null}
     *                                  or {@code id==""})
     * @return chainable self-reference
     */
    public Article setId(String id) {
        if (this.id != null) {
            return this;
        }
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("invalid id (null or \"\").");
        }
        this.id = id;
        return this;
    }

    /**
     * Article description getter.
     * 
     * @return article description
     */
    public String getDescription() {
        if (description != null) {
            return description;
        }
        return "";
    }

    /**
     * Article description setter.
     * 
     * @param description descriptive text for article, only valid description (not
     *                    null or "") updates attribute
     * @throws IllegalArgumentException when description is null or empty ""
     * @return chainable self-reference
     */
    public Article setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("invalid description (null or \"\").");
        }
        this.description = description;
        return this;
    }

    /**
     * UnitPrice getter.
     * 
     * @return price in cent for one article unit
     */
    public long getUnitPrice() {
        if (unitPrice >= 0) {
            return unitPrice;
        }
        return 0L;
    }

    /**
     * UnitPrice setter.
     * 
     * @param unitPrice price in cent for one article, only valid price (
     *                  {@code >= 0} ) updates attribute
     * @return chainable self-reference
     */
    public Article setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    /**
     * Currency getter.
     * 
     * @return currency in which unitPrice is quoted.
     */
    public Currency getCurrency() {
        if (currency != null) {
            return currency;
        }
        return Currency.EUR;
    }

    /**
     * Currency setter.
     * 
     * @param currency in which unitPrice is quoted
     * @throws IllegalArgumentException if currency is null
     * @return chainable self-reference
     */
    public Article setCurrency(Currency currency) {
        if (currency == null) {
            throw new IllegalArgumentException("invalid currency (null).");
        }
        this.currency = currency;
        return this;
    }

    /**
     * TAX rate getter.
     * 
     * @return tax rate applicable for article
     */
    public TAX getTax() {
        if (taxRate != null) {
            return taxRate;
        }
        return TAX.TAXFREE;
    }

    /**
     * TAX rate setter.
     * 
     * @param tax rate that applies to article
     * @throws IllegalArgumentException if tax is null
     * @return chainable self-reference
     */
    public Article setTax(TAX tax) {
        if (tax == null) {
            throw new IllegalArgumentException("invalid tax (null).");
        }
        this.taxRate = tax;
        return this;
    }

}

// -c datamodel.Article_100_Constructor_Tests
// -c datamodel.Article_200_SetId_Tests
// -c datamodel.Article_300_Description_Tests
// -c datamodel.Article_400_UnitPrice_Tests
// -c datamodel.Article_500_Currency_Tests
// -c datamodel.Article_600_TAX_Tests
