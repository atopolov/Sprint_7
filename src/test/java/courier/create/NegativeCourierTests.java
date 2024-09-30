package courier.create;

import courier.Courier;
import courier.CourierAuthorization;
import courier.CourierChecks;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class NegativeCourierTests {
    private final CourierChecks check = new CourierChecks();
    int courierId;

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @After
    public void deleteCourier() {
        // Delete courier (courierId is not equal to 0)
        if (courierId != 0) {
            ValidatableResponse response = check.deleteCourier(courierId);
            check.deleteSuccesfully(response);
        }
    }

    @Test
    @DisplayName("Negative test: duplicate couriers")
    public void testDuplicateCouriers() {
        // Test for duplicate couriers
        Courier courier = Courier.createdCourier();

        // Create first courier
        ValidatableResponse createResponse = check.createCourier(courier);
        check.successfulCreated(createResponse);

        // Try to create duplicate courier
        ValidatableResponse duplicateResponse = check.createCourier(courier);
        check.conflictCourier(duplicateResponse);

        // Authorize to get the ID of the courier for the following deletion
        ValidatableResponse loginResponse = check.loginCourier(CourierAuthorization.from(courier));
        courierId = check.authorization(loginResponse);
    }

    @Test
    @DisplayName("Negative test: one of the required fields is missing")
    public void testMissingFields() {
        // Test for creation of a courier with one of the required fields missing
        Courier withoutLogin = Courier.noRequiredField();

        // Create courier without required field, expecting an error
        ValidatableResponse errorResponse = check.createCourier(withoutLogin);
        check.withoutLogin(errorResponse);
    }

    @Test
    @DisplayName("Negative test: user creation with already existing login")
    public void testDuplicateLogin() {
        // Test for creation of a courier with an already existing login from another user
        Courier courier = Courier.createdCourier();
        Courier doubleCourier = Courier.duplicateCourier();

        // Create first courier
        ValidatableResponse createResponse = check.createCourier(courier);
        check.successfulCreated(createResponse);

        // Create second courier with the used login, expecting a conflict
        ValidatableResponse duplicateResponse = check.createCourier(doubleCourier);
        check.conflictCourier(duplicateResponse);

        // Authorize to get the ID of the courier for the following deletion
        ValidatableResponse loginResponse = check.loginCourier(CourierAuthorization.from(courier));
        courierId = check.authorization(loginResponse);
    }
}