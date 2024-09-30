package order;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RunWith(Parameterized.class)
public class CreateOrderColorTests {
    private final OrderChecks check = new OrderChecks();
    private String colorBlack;
    private String colorGrey;

    @Before
    public void setUp() {

        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    // Constructor for parameterized test
    public CreateOrderColorTests(String colorBlack, String colorGrey) {
        this.colorBlack = colorBlack;
        this.colorGrey = colorGrey;
    }

    // Parameterized test: define different combinations of colors
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"BLACK", null}, // Black color, grey color is absent
                {null, "GREY"},  // Black color is absent, grey color
                {"BLACK", "GREY"}, // Both colors present
                {null, null}       // Both colors absent
        });
    }

    @Test
    @DisplayName("Order creation test")
    public void createOrder() {
        // Create list of colors for the current combination of parameters
        List<String> colors = Arrays.asList(colorBlack, colorGrey);

        // Create an order client with the specified colors
        OrderClient orderClient = OrderClient.createOrderClient(colors);

        // Making a request to create an order and get the response
        ValidatableResponse createResponse = check.createdOrder(orderClient);

        // Check that the order was created successfully
        check.checkOrder(createResponse);
    }
}