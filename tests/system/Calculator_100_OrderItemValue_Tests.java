package system;

import org.junit.jupiter.api.*;

import datamodel.Article;
import datamodel.OrderItem;

import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit5 Tests for Calculator component.
 * 
 * @author sgra64
 *
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Calculator_100_OrderItemValue_Tests {

    /*
     * Unit under test.
     */
    private final Calculator calc = IoC.getInstance().getCalculator();

    @Test
    @Order(100)
    void test100_calculateOrderItemValue() {
        // Create an Article instance
        Article article = new Article("Test Article", 4999);

        // Create an OrderItem instance
        OrderItem orderItem = new OrderItem(article, 3);

        // Calculate expected value: unitPrice * unitsOrdered
        long expectedValue = article.getUnitPrice() * orderItem.getUnitsOrdered();

        // Assert that the calculated value is correct
        assertEquals(expectedValue, calc.calculateOrderItemValue(orderItem));
    }

    @Test
    @Order(190)
    void test190_calculateOrderItemValue_NullArgs() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class, () -> {
                    calc.calculateOrderItemValue(null);
                });
        assertEquals("argument item is null.", thrown.getMessage());
    }
}
