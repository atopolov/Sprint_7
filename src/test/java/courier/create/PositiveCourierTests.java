package courier.create;

import courier.Courier;
import courier.CourierChecks;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PositiveCourierTests {

    private final CourierChecks check = new CourierChecks();
    int courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void deleteCourier() {
        // Delete courier if it was created
        if (courierId != 0) {
            ValidatableResponse response = check.deleteCourier(courierId);
            check.deleteSuccesfully(response);
        }
    }

    @Test
    @DisplayName("Positive test: create courier")
    public void createCourier() {
        // Positive test to create courier
        Courier courier = Courier.createdCourier();

        // Create courier and check that it was successful
        ValidatableResponse createResponse = check.createCourier(courier);
        check.successfulCreated(createResponse);
    }
}