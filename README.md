<!-- 
    README.md in branch C12
 -->

# Aufgabe D12: *Datamodel*

The assignment completes the datamodel with classes *Article.java*, *Order.java*
and *OrderItem.java* classes. *Customer.java* already exists from the previous
assignment. In addition, two *enum* are used in the data model:
*Currency.java* amd *TAX.java*.

The data model is (UML Class Diagram, see also StarUML-file:
[Datamodel.mdj](src/datamodel/Datamodel.mdj)
):

<img src="src/datamodel/Datamodel.png" alt="drawing" width="800"/>


Content:

- [Setup](#1-setup)
    - Fetching the Code-drop

- [D1: Completing the Datamodel](#2-d1-completing-the-datamodel)
    - Datamodel Generation and Completion
    - Building and Running the Application
    - Running JUnit Tests

- [D2: Completing Features](#3-completing-features)
    - New Articles, Customers and Orders (steps 1-3)
    - *find()* - method (step 4)
    - *printOrder()* - method (steps 5)
    - order value and tax calculations (steps 6-9)


- [Release Preparation](#4-release-preparation)
    - Generating Javadoc
    - Packaging and Running the Application


&nbsp;
## 1. Setup

Merge files from branch
[D12-Datamodel](https://github.com/sgra64/se1.bestellsystem/tree/D12-Datamodel)
into project `se1.bestellsystem` from the remote repository
(assumes that ):

```sh
cd se1.bestellsystem            # change into project directory
source .env/setenv.sh           # source the project

# add new link to remote repository
git remote add se1-origin https://github.com/sgra64/se1.bestellsystem.git

# fetch branch D12-Datamodel from the remote repository
git fetch se1-origin D12-Datamodel

# merge content of fetched branch into current branch
git merge --strategy-option theirs FETCH_HEAD
```

Files that come with the merge:

```
 README.md                                          |  462 +-
 src/application/Application_D1.java                |  761 +++
 src/application/Runtime.java                       |    6 +-
 src/application/package-info.java                  |    2 +-
 src/datamodel/Datamodel.mdj                        | 5084 ++++++++++++++++++++
 src/datamodel/Datamodel.png                        |  Bin 0 -> 127259 bytes
 src/datamodel/package-info.java                    |   12 +
 src/module-info.java                               |    1 +
 tests/datamodel/Article_100_Constructor_Tests.java |  109 +
 tests/datamodel/Article_200_SetId_Tests.java       |  100 +
 tests/datamodel/Article_300_Description_Tests.java |   71 +
 tests/datamodel/Article_400_UnitPrice_Tests.java   |   37 +
 tests/datamodel/Article_500_Currency_Tests.java    |   48 +
 tests/datamodel/Article_600_TAX_Tests.java         |   49 +
 tests/datamodel/Order_100_Constructor_Tests.java   |   87 +
 tests/datamodel/Order_200_SetId_Tests.java         |  101 +
 tests/datamodel/Order_300_CreationDate_Tests.java  |  162 +
 tests/datamodel/Order_400_OrderItems_Tests.java    |  179 +
 18 files changed, 6940 insertions(+), 331 deletions(-)
```

Verify that files from branch C12 are present:

```sh
find tests
```

If no Customer-tests are showing, merge tests from branch C12:

```sh
# fetch branch D12-Datamodel from the remote repository
git fetch se1-origin C12

# merge content of fetched branch into current branch
git merge --strategy-option ours FETCH_HEAD
```

Now all tests should show, including Customer tests:

```sh
find tests
```

```
tests
tests/application
tests/application/Application_0_always_pass_Tests.java
tests/datamodel
tests/datamodel/Article_100_Constructor_Tests.java
tests/datamodel/Article_200_SetId_Tests.java
tests/datamodel/Article_300_Description_Tests.java
tests/datamodel/Article_400_UnitPrice_Tests.java
tests/datamodel/Article_500_Currency_Tests.java
tests/datamodel/Article_600_TAX_Tests.java
tests/datamodel/Customer_100_Constructor_Tests.java
tests/datamodel/Customer_200_SetId_Tests.java
tests/datamodel/Customer_300_SetName_Tests.java
tests/datamodel/Customer_400_Contacts_Tests.java
tests/datamodel/Customer_500_SetNameExtended_Tests.java
tests/datamodel/Order_100_Constructor_Tests.java
tests/datamodel/Order_200_SetId_Tests.java
tests/datamodel/Order_300_CreationDate_Tests.java
tests/datamodel/Order_400_OrderItems_Tests.java
```


&nbsp;
## 2. D1: Completing the Datamodel
    - Datamodel Generation and Completion
    - Building and Running the Application
    - Running JUnit Tests


### 2.a Datamodel Generation and Completion

File
[src/datamodel/Datamodel.mdj](src/datamodel/Datamodel.mdj)
contains UML model of the `datamodel` classes.

You should have a completed class `Customer.java` from the previous
assignment. Keep it.

Open with *StarUML* and extract classes from the UML-Model:

- Tools -> Java -> Generate Code -> select *"datamodel"* -> OK

- select folder to save datamodel classes

- make sure, datamodel classes arrive in `src/datamodel` and `Customer.java` remains unchanged.

Classes in `src/datamodel` should now be present:

```sh
find src/datamodel
```
```
src/datamodel
src/datamodel/Article.java
src/datamodel/Currency.java
src/datamodel/Customer.java
src/datamodel/Customer.mdj
src/datamodel/Datamodel.mdj
src/datamodel/Datamodel.png
src/datamodel/Order.java
src/datamodel/OrderItem.java
src/datamodel/package-info.java
src/datamodel/TAX.java
src/module-info.java
```

You may remove the `.mdj` and `.png` files.

Adjust `src/datamodel` classes to compile.

```sh
mk compile
```
```
javac $(find src -name '*.java') -d bin/classes; \
copy resources bin/resources
done.
```

Complete methods in classes according to javadocs:

- `Article.java`,

- `Order.java` and

- `OrderItem.java`.


### 2.c Building and Running the Application

After completion of classes, run the application:

```sh
mk compile
```

The application
[Application_D1.java](src/application/Application_D1.java)

shows the folloring output:

```
java application.Application
Hello, Application_D1
(5) Customer objects built.
(6) Article objects built.
(4) Order objects built.
---
(+0) Customer objects added.
(+0) Article objects added.
(+0) Order objects added.
---
Kunden:
+----------+---------------------------------+--------------------------------------+
| Kund.-ID | Name                            | Kontakt                              |
+----------+---------------------------------+--------------------------------------+
|   892474 | Meyer, Eric                     | eric98@yahoo.com, (+1 contacts)      |
|   643270 | Bayer, Anne                     | anne24@yahoo.de, (+2 contacts)       |
|   286516 | Schulz-Mueller, Tim             | tim2346@gmx.de                       |
|   412396 | Blumenfeld, Nadine-Ulla         | +49 152-92454                        |
|   456454 | Abdelalim, Khaled Saad Mohamed  | +49 1524-12948210                    |
+----------+---------------------------------+--------------------------------------+

Artikel:
+----------+---------------------------------+---------------+----------------------+
|Artikel-ID| Beschreibung                    |      Preis CUR|  Mehrwertsteuersatz  |
+----------+---------------------------------+---------------+----------------------+
|SKU-458362| Tasse                           |       2.99 EUR|  19.0% GER_VAT       |
|SKU-693856| Becher                          |       1.49 EUR|  19.0% GER_VAT       |
|SKU-518957| Kanne                           |      19.99 EUR|  19.0% GER_VAT       |
|SKU-638035| Teller                          |       6.49 EUR|  19.0% GER_VAT       |
|SKU-278530| Buch "Java"                     |      49.90 EUR|   7.0% GER_VAT_REDU  |
|SKU-425378| Buch "OOP"                      |      79.95 EUR|   7.0% GER_VAT_REDU  |
+----------+---------------------------------+---------------+----------------------+

Bestellungen:
+----------+-------------------------------------------------+----------------------+
|Bestell-ID| Bestellungen                  MwSt*        Preis|     MwSt       Gesamt|
+----------+-------------------------------------------------+----------------------+
|id        | name                          0.00*     0.00 EUR|     0.00     0.00 EUR|
+----------+-------------------------------------------------+----------------------+
|id        | name                          0.00*     0.00 EUR|     0.00     0.00 EUR|
+----------+-------------------------------------------------+----------------------+
|id        | name                          0.00*     0.00 EUR|     0.00     0.00 EUR|
+----------+-------------------------------------------------+----------------------+
|id        | name                          0.00*     0.00 EUR|     0.00     0.00 EUR|
+----------+-------------------------------------------------+----------------------+
                                                      Gesamt:|     0.00     0.00 EUR|
                                                             +======================+
done.
```


### 2.d Running JUnit Tests

JUnit-tests for class `Customer.java` should pass from the previous assignment.

```sh
java -jar branches/libs/libs/junit-platform-console-standalone-1.9.2.jar \
    $(eval echo $JUNIT_OPTIONS) \
    -c application.Application_0_always_pass_Tests \
    -c datamodel.Customer_100_Constructor_Tests \
    -c datamodel.Customer_200_SetId_Tests \
    -c datamodel.Customer_300_SetName_Tests \
    -c datamodel.Customer_400_Contacts_Tests \
    -c datamodel.Customer_500_SetNameExtended_Tests
```

Output:

```
├─ JUnit Jupiter ✔
│  └─ Application_0_always_pass_Tests ✔
│     ├─ test_001_always_pass() ✔
│     └─ test_002_always_pass() ✔
|  ...
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 142 ms
[        55 tests successful      ]   <-- 55 tests successful
[         0 tests failed          ]   <--  0 tests failed
done.
```

Add tests for classes `Article.java` and `Order.java` one after another
to verify they are passing with your implementation of classes. Fix failures.

```
  - datamodel.Article_100_Constructor_Tests
  - datamodel.Article_200_SetId_Tests
  - datamodel.Article_300_Description_Tests
  - datamodel.Article_400_UnitPrice_Tests
  - datamodel.Article_500_Currency_Tests
  - datamodel.Article_600_TAX_Tests
  - datamodel.Order_100_Constructor_Tests
  - datamodel.Order_200_SetId_Tests
  - datamodel.Order_300_CreationDate_Tests
  - datamodel.Order_400_OrderItems_Tests
```

The full test-suite of *Customer* tests runs

```sh
mk run-tests
```

Output:

```
├─ JUnit Jupiter ✔
│  └─ Application_0_always_pass_Tests ✔
│     ├─ test_001_always_pass() ✔
│     └─ test_002_always_pass() ✔
|  ...
|
├─ JUnit Vintage ✔
└─ JUnit Platform Suite ✔

Test run finished after 142 ms
[       102 tests successful      ]   <-- 102 tests successful
[         0 tests failed          ]   <--   0 tests failed
done.
```

Remove tests that are failing.

Run JUnit-Tests also in your IDE.


&nbsp;
## 3. D2: Completing Features

Assignment D2 will complete features (methods) in
[Application_D1.java](src/application/Application_D1.java)
to print correct orders.

The assignment is structured in four stages:
  - [3.a New Articles, Customers and Orders](#3a-new-articles-customers-and-orders)
  - [3.b *find()* method](#3b-find-method)
  - [3.c *printOrder()* method](#3c-printorder-method)
  - [3.d Order Value and Tax Calculations](#3d-order-value-and-tax-calculations)

Find places in
[Application_D1.java](src/application/Application_D1.java)
labeled with `@TODO: (1.)` (1.-9.):

```sh
grep @TODO src/application/Application_D1.java
```

Output:

```
    * @TODO: (1.) create new Customer "Lena" with:
    * @TODO: (2.) create new articles with:
    * @TODO: (3.) create three new orders:
    * @TODO: (4.) implement the find()-method to find objects in collections:
    * @TODO: (5.) implement method to print order in table-format:
    * @TODO: (6.) implement the method to calculate the value of an order
    * @TODO: (7.) implement the method to calculate the value of an orderItem.
    * @TODO: (8.) implement the method to calculate the VAT of an order
    * @TODO: (9.) implement to calculate included VAT in a gross value
```


### 3.a New Articles, Customers and Orders

@TODO: (1. - 3.)

Navigate to label @TODO (1.) and complete the challenge:

```java
/**
 * @TODO: (1.) create new Customer "Lena" with:
 *  - id: 651286, name: "Lena Neumann", contact: "lena228@gmail.com"
 * //
 * and add to List<Customer> customers;
 * 
 * @TODO: (2.) create new articles with:
 *  - id: "SKU-300926", description: "Pfanne", unit-price: 49.99 EUR
 *  - id: "SKU-663942", description: "Fahrradhelm", unit-price: 169.00 EUR
 *  - id: "SKU-583978", description: "Fahrradkarte", unit-price: 6.95 EUR (reduced 7% tax rate)
 * //
 * and add to List<Article> articles;
 * 
 * 
 * @TODO: (3.) create three new orders:
 * 
 * // Lena's order
 * +----------+-------------------------------------------------+----------------------+
 * |6173043537| Lena's Bestellung:                              |                      |
 * |          |  - 1 Buch "Java"              3.26*    49.90 EUR|                      |
 * |          |  - 1 Fahrradkarte             0.45*     6.95 EUR|     3.71    56.85 EUR|
 * +----------+-------------------------------------------------+----------------------+
 *
 * // Eric's 3rd order
 * +----------+-------------------------------------------------+----------------------+
 * |7372561535| Eric's Bestellung:                              |                      |
 * |          |  - 1 Fahrradhelm             26.98    169.00 EUR|                      |
 * |          |  - 1 Fahrradkarte             0.45*     6.95 EUR|    27.43   175.95 EUR|
 * +----------+-------------------------------------------------+----------------------+
 * 
 * // Eric's 3rd order
 * +----------+-------------------------------------------------+----------------------+
 * |4450305661| Eric's Bestellung:                              |                      |
 * |          |  - 3 Tasse, 3x 2.99           1.43      8.97 EUR|                      |
 * |          |  - 3 Becher, 3x 1.49          0.71      4.47 EUR|                      |
 * |          |  - 1 Kanne                    3.19     19.99 EUR|     5.33    33.43 EUR|
 * +----------+-------------------------------------------------+----------------------+
 * 
 */
```

Output of the program should show the added customers and articles (orders will be completed
next):

```
Hello, Application_D1
(5) Customer objects built.
(6) Article objects built.
(4) Order objects built.
---
(+1) Customer objects added.
(+3) Article objects added.
(+3) Order objects added.
---
Kunden:
+----------+---------------------------------+--------------------------------------+
| Kund.-ID | Name                            | Kontakt                              |
+----------+---------------------------------+--------------------------------------+
|   892474 | Meyer, Eric                     | eric98@yahoo.com, (+1 contacts)      |
|   643270 | Bayer, Anne                     | anne24@yahoo.de, (+2 contacts)       |
|   286516 | Schulz-Mueller, Tim             | tim2346@gmx.de                       |
|   412396 | Blumenfeld, Nadine-Ulla         | +49 152-92454                        |
|   456454 | Abdelalim, Khaled Saad Mohamed  | +49 1524-12948210                    |
|   651286 | Neumann, Lena                   | lena228@gmail.com                    |
+----------+---------------------------------+--------------------------------------+

Artikel:
+----------+---------------------------------+---------------+----------------------+
|Artikel-ID| Beschreibung                    |      Preis CUR|  Mehrwertsteuersatz  |
+----------+---------------------------------+---------------+----------------------+
|SKU-458362| Tasse                           |       2.99 EUR|  19.0% GER_VAT       |
|SKU-693856| Becher                          |       1.49 EUR|  19.0% GER_VAT       |
|SKU-518957| Kanne                           |      19.99 EUR|  19.0% GER_VAT       |
|SKU-638035| Teller                          |       6.49 EUR|  19.0% GER_VAT       |
|SKU-278530| Buch "Java"                     |      49.90 EUR|   7.0% GER_VAT_REDU  |
|SKU-425378| Buch "OOP"                      |      79.95 EUR|   7.0% GER_VAT_REDU  |
|SKU-300926| Pfanne                          |      49.99 EUR|  19.0% GER_VAT       |
|SKU-663942| Fahrradhelm                     |     169.00 EUR|  19.0% GER_VAT       |
|SKU-583978| Fahrradkarte                    |       6.95 EUR|   7.0% GER_VAT_REDU  |
+----------+---------------------------------+---------------+----------------------+
...
```


### 3.b *find()* method

TODO: (4.)

Navigate to label @TODO (4.) and implement the *find()* method:

```java
/**
 * Find first matching object from the collection. Return empty Optional
 * if no object was found. 
 * 
 * @param <T> generic type of objects in collection.
 * @param collection collection of objects of type T.
 * @param matcher function that returns matching result for an object.
 * @return Optional with first matching object or empty Optional.
 */
public <T> Optional<T> find(List<T> collection, Function<T, Boolean> matcher) {

    /**
     * @TODO: (4.) implement the find()-method to find objects in collections:
     * List<Customer> customers, List<Article> articles, List<Order> orders
     */

    return Optional.empty();
}
```


### 3.c *printOrder()* method

@TODO: (5.)

Navigate to label @TODO (5.) and implement the *printOrder()* method such that it
prints one order in table format:

```java
/**
 * Print one order in table format into a StringBuilder, used by printOrders().
 * 
 * @param order order to print into table.
 * @param tf table formatter used by printOrders().
 * @return table formatter used by printOrders().
 */
public TableFormatter printOrder(Order order, TableFormatter tf) {
    if(order==null || tf==null)
        throw new IllegalArgumentException("order or table formatter tf is null.");

    /**
     * @TODO: (5.) implement method to print order in table-format:
     * 
     * +----------+-------------------------------------------------+----------------------+
     * |8592356245| Eric's Bestellung:                              |                      |
     * |          |  - 4 Teller, 4x 6.49          4.14     25.96 EUR|                      |
     * |          |  - 8 Becher, 8x 1.49          1.90     11.92 EUR|                      |
     * |          |  - 1 Buch "OOP"               5.23*    79.95 EUR|                      |
     * |          |  - 4 Tasse, 4x 2.99           1.91     11.96 EUR|    13.18   129.79 EUR|
     * +----------+-------------------------------------------------+----------------------+
     * 
     * Figure out which data is need from the order at which place in the table
     * and extract that data from the order object.
     * 
     * Fill in each row using the tf.row() method (see example below).
     */

    // "*" indicates reduced VAT rate
    tf.row("id", "name", fmtPrice(0L), "*", fmtPrice(0L, 1), fmtPrice(0L), fmtPrice(0L, 1));
    return tf;
}
```


### 3.d Order Value and Tax Calculations

@TODO: (6. - 9.)

Navigate to labels @TODO (6. - 9.) and implement methods for calculations:
*printOrder()*.

Implement the method to calculate the value of an order:

```java
/**
 * Calculate value of an order, which is comprised of the value of each
 * ordered item. The value of each item is calculated with
 * calculateOrderItemValue(item).
 * 
 * @param order to calculate value for.
 * @return value of order.
 */
public long calculateOrderValue(final Order order) {
    if(order==null)
        throw new IllegalArgumentException("order is null.");

    /**
     * @TODO: (6.) implement the method to calculate the value of an order
     * using a Stream<OrderItem> over order items.
     */
    return 0L;
}
```

&nbsp;

Implement the method to calculate the value of an orderItem:

```java
/**
 * Calculate value of an order item, which is calculated by:
 * article.unitPrice * number of units ordered. 
 * 
 * @param item to calculate value for.
 * @return value of item.
 */
public long calculateOrderItemValue(final OrderItem item) {
    if(item==null)
        throw new IllegalArgumentException("item is null.");

    /**
     * @TODO: (7.) implement the method to calculate the value of an orderItem.
     */

    return 0L;
}
```

&nbsp;

Implement the method to calculate the VAT of an order:

```java
/**
 * Calculate VAT of an order, which is comprised of the VAT of each
 * ordered item. The VAT of each item is calculated with
 * calculateOrderItemVAT(item).
 * 
 * @param order to calculate VAT tax for.
 * @return VAT calculated for order.
 */
public long calculateOrderVAT(final Order order) {
    if(order==null)
        throw new IllegalArgumentException("order is null.");

    /**
     * @TODO: (8.) implement the method to calculate the VAT of an order
     * using a Stream<OrderItem> over order items.
     */

    return 0L;
}
```

&nbsp;

Implement the calculation of included VAT in a gross value:

```java
/**
 * Calculate included VAT (Value-Added Tax) from a gross price/value based on
 * a tax rate (VAT is called <i>"Mehrwertsteuer" (MwSt.)</i> in Germany).
 * 
 * @param grossValue value that includes the tax.
 * @param tax applicable tax rate in percent.
 * @return tax included in gross value.
 */
public long calculateVAT(final long grossValue, final TAX tax) {

    /**
     * @TODO: (9.) implement to calculate included VAT in a gross value
     * considering different tax rates.
     */

    return 0L;
}
```



&nbsp;
## 4. Release Preparation


### 4.a Generating Javadoc

Build the javadoc for the project.

```sh
mk javadoc
```

Open `docs/index.html` in a browser.


### 4.b Packaging and Running the Application

The packaged application will be `bin/application-1.0.0-SNAPSHOT.jar`.

```sh
mk package                  # run packaging
ls -la bin                  # show result in bin directory
```

Output:

```
total 16
drwxr-xr-x 1      0 May  5 23:18 ./
drwxr-xr-x 1      0 May  5 23:17 ../
-rw-r--r-- 1 173615 May  5 23:18 application-1.0.0-SNAPSHOT.jar
drwxr-xr-x 1      0 May  5 23:17 classes/
drwxr-xr-x 1      0 May  5 23:17 resources/
drwxr-xr-x 1      0 May  5 23:17 test-classes/
```

Run the packaged jar-file with:

```sh
mk run-jar
java -jar bin/application-1.0.0-SNAPSHOT.jar
```

Final output of correct order table:

```
Hello, Application_D1
(5) Customer objects built.
(6) Article objects built.
(4) Order objects built.
---
(+1) Customer objects added.
(+3) Article objects added.
(+3) Order objects added.
---
Kunden:
+----------+---------------------------------+--------------------------------------+
| Kund.-ID | Name                            | Kontakt                              |
+----------+---------------------------------+--------------------------------------+
|   892474 | Meyer, Eric                     | eric98@yahoo.com, (+1 contacts)      |
|   643270 | Bayer, Anne                     | anne24@yahoo.de, (+2 contacts)       |
|   286516 | Schulz-Mueller, Tim             | tim2346@gmx.de                       |
|   412396 | Blumenfeld, Nadine-Ulla         | +49 152-92454                        |
|   456454 | Abdelalim, Khaled Saad Mohamed  | +49 1524-12948210                    |
|   651286 | Neumann, Lena                   | lena228@gmail.com                    |
+----------+---------------------------------+--------------------------------------+

Artikel:
+----------+---------------------------------+---------------+----------------------+
|Artikel-ID| Beschreibung                    |      Preis CUR|  Mehrwertsteuersatz  |
+----------+---------------------------------+---------------+----------------------+
|SKU-458362| Tasse                           |       2.99 EUR|  19.0% GER_VAT       |
|SKU-693856| Becher                          |       1.49 EUR|  19.0% GER_VAT       |
|SKU-518957| Kanne                           |      19.99 EUR|  19.0% GER_VAT       |
|SKU-638035| Teller                          |       6.49 EUR|  19.0% GER_VAT       |
|SKU-278530| Buch "Java"                     |      49.90 EUR|   7.0% GER_VAT_REDU  |
|SKU-425378| Buch "OOP"                      |      79.95 EUR|   7.0% GER_VAT_REDU  |
|SKU-300926| Pfanne                          |      49.99 EUR|  19.0% GER_VAT       |
|SKU-663942| Fahrradhelm                     |     169.00 EUR|  19.0% GER_VAT       |
|SKU-583978| Fahrradkarte                    |       6.95 EUR|   7.0% GER_VAT_REDU  |
+----------+---------------------------------+---------------+----------------------+

Bestellungen:
+----------+-------------------------------------------------+----------------------+
|Bestell-ID| Bestellungen                  MwSt*        Preis|     MwSt       Gesamt|
+----------+-------------------------------------------------+----------------------+
|8592356245| Eric's Bestellung:                              |                      |
|          |  - 4 Teller, 4x 6.49          4.14     25.96 EUR|                      |
|          |  - 8 Becher, 8x 1.49          1.90     11.92 EUR|                      |
|          |  - 1 Buch "OOP"               5.23*    79.95 EUR|                      |
|          |  - 4 Tasse, 4x 2.99           1.91     11.96 EUR|    13.18   129.79 EUR|
+----------+-------------------------------------------------+----------------------+
|3563561357| Anne's Bestellung:                              |                      |
|          |  - 2 Teller, 2x 6.49          2.07     12.98 EUR|                      |
|          |  - 2 Tasse, 2x 2.99           0.95      5.98 EUR|     3.02    18.96 EUR|
+----------+-------------------------------------------------+----------------------+
|5234968294| Eric's Bestellung:                              |                      |
|          |  - 1 Kanne                    3.19     19.99 EUR|     3.19    19.99 EUR|
+----------+-------------------------------------------------+----------------------+
|6135735635| Nadine-Ulla's Bestellung:                       |                      |
|          |  - 12 Teller, 12x 6.49       12.43     77.88 EUR|                      |
|          |  - 1 Buch "Java"              3.26*    49.90 EUR|                      |
|          |  - 1 Buch "OOP"               5.23*    79.95 EUR|    20.92   207.73 EUR|
+----------+-------------------------------------------------+----------------------+
|6173043537| Lena's Bestellung:                              |                      |
|          |  - 1 Buch "Java"              3.26*    49.90 EUR|                      |
|          |  - 1 Fahrradkarte             0.45*     6.95 EUR|     3.71    56.85 EUR|
+----------+-------------------------------------------------+----------------------+
|7372561535| Eric's Bestellung:                              |                      |
|          |  - 1 Fahrradhelm             26.98    169.00 EUR|                      |
|          |  - 1 Fahrradkarte             0.45*     6.95 EUR|    27.43   175.95 EUR|
+----------+-------------------------------------------------+----------------------+
|4450305661| Eric's Bestellung:                              |                      |
|          |  - 3 Tasse, 3x 2.99           1.43      8.97 EUR|                      |
|          |  - 3 Becher, 3x 1.49          0.71      4.47 EUR|                      |
|          |  - 1 Kanne                    3.19     19.99 EUR|     5.33    33.43 EUR|
+----------+-------------------------------------------------+----------------------+
                                                      Gesamt:|    76.78   642.70 EUR|
                                                             +======================+
done.
```

The packaged .jar file can now be distributed.
