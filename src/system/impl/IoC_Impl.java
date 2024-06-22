package system.impl;

import datamodel.Article;
import datamodel.Customer;
import datamodel.Order;
// import datamodel.OrderItem;
// import datamodel.TAX;
import system.*;

/**
 * Implementation class of the {@link system.IoC} interface.
 * <p>
 * "Inversion-of-Control" (IoC) container creates and contains system component
 * objects
 * such as {@link Calculator}, {@link DataStore}, {@link Formatter} and
 * {@link Printer}.
 * </p>
 * 
 * @version <code style=
 *          color:green>{@value application.package_info#Version}</code>
 * @author <code style=
 *         color:blue>{@value application.package_info#Author}</code>
 */
public class IoC_Impl implements IoC {

    /**
     * Singleton instance of IoC component.
     */
    private final static IoC iocSingleton = new IoC_Impl();

    /**
     * Singleton instance of DataStore component.
     */
    private final DataStore dataStore;

    /**
     * Singleton instance of Calculator component.
     */
    private final Calculator calculator;

    /**
     * Singleton instance of Formatter component.
     */
    private final Formatter formatter;

    /**
     * Singleton instance of Printer component.
     */
    private final Printer printer;

    /**
     * Private constructor to implement Singleton pattern of IoC instance.
     */
    private IoC_Impl() {
        this.calculator = new CalculatorImpl();
        this.formatter = new FormatterImpl();

        // prepare depndencies injected into DataStoreImpl
        var customersRepository = new RepositoryImpl<Customer, Long>(c -> c.getId());
        var articlesRepository = new RepositoryImpl<Article, String>(a -> a.getId());
        var ordersRepository = new RepositoryImpl<Order, String>(o -> o.getId());
        //
        this.dataStore = new DataStoreImpl(
                // inject dependencies into DataStoreImpl
                customersRepository, articlesRepository, ordersRepository);
        //
        // inject dependencies into PrinterImpl constructor
        this.printer = new PrinterImpl(calculator, formatter);
    }

    /**
     * IoC component getter.
     * 
     * @return reference to IoC singleton instance.
     */
    public static IoC getInstance() {
        return iocSingleton;
    }

    /**
     * DataStore component getter.
     * 
     * @return reference to DataStore singleton instance.
     */
    @Override
    public DataStore getDataStore() {
        return dataStore;
    }

    /**
     * Calculator component getter.
     * 
     * @return reference to Calculator singleton instance.
     */
    @Override
    public Calculator getCalculator() {
        return calculator;
    }

    /**
     * Formatter component getter.
     * 
     * @return reference to Formatter singleton instance.
     */
    @Override
    public Formatter getFormatter() {
        return formatter;
    }

    /**
     * Printer component getter.
     * 
     * @return reference to Printer singleton instance.
     */
    @Override
    public Printer getPrinter() {
        return printer;
    }
}