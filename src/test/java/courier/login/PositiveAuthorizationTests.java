package courier.login;

import courier.Courier;
import courier.CourierAuthorization;
import courier.CourierChecks;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotEquals;

public class PositiveAuthorizationTests {

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
    @DisplayName("Test for courier authorization")
    public void testAuthorizationCourier() {
        // Positive test for courier authorization
        var autho = CourierAuthorization.from(Courier.createdCourier());

        // Authorization and check that it was successful
        ValidatableResponse loginResponse = check.loginCourier(autho);

        // Check successful authorization and get the ID of the courier
        courierId = check.authorization(loginResponse);

        // Expect that courierId is not equal to 0 (authorization was successful)
        assertNotEquals(0, courierId);
    }
}