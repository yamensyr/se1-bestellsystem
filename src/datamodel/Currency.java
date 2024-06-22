package datamodel;

// import java.util.*;

/**
 * Enumeration type for currencies.
 * 
 * Currency is the unit in which price is quoted.
 * 
 * @version <code style=color:green>{@value application.package_info#Version}</code>
 * @author <code style=color:blue>{@value application.package_info#Author}</code>
 */
// public class Currency {

//     /**
//      * Default constructor
//      */
//     public Currency() {
//     }

//     /**
//      * Euro, legal tender in many countries in the European Union
//      */
//     public void EUR;

//     /**
//      * US Dollar, legal tender in the United States of America
//      */
//     public void USD;

//     /**
//      * Great Britain Pound (or Pound Sterling), legal tender in the United Kingdom
//      */
//     public void GBP;

//     /**
//      * Japanese Yen, legal tender in Japan
//      */
//     public void YEN;

//     /**
//      * Crypto currency, no legal tender
//      */
//     public void BTC;

// }

public enum Currency {
    EUR, // Euro, legal tender in many countries in the European Union
    USD, // US Dollar, legal tender in the United States of America
    GBP, // Great Britain Pound (or Pound Sterling), legal tender in the United Kingdom
    YEN, // Japanese Yen, legal tender in Japan
    BTC // Crypto currency, no legal tender
}