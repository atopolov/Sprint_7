package courier.login;

import courier.CourierAuthorization;
import courier.CourierChecks;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NegativeAuthorizationTests {
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
    @DisplayName("Negative test: wrong password")
    public void testInvalidPassword() {
        // Test for authorization with wrong password
        CourierAuthorization autho = CourierAuthorization.wrongPassword();

        // Authorization with wrong password and expecting an error
        ValidatableResponse loginResponse = check.loginCourier(autho);
        check.invalidCredentials(loginResponse);
    }

    @Test
    @DisplayName("Negative test: without login")
    public void testMissingLogin() {
        // Test for authorization without login
        CourierAuthorization autho = CourierAuthorization.missedLogin();

        // Authorization without login and expecting an error
        ValidatableResponse missedLoginResponse = check.loginCourier(autho);
        check.withoutLogin(missedLoginResponse);
    }

    @Test
    @DisplayName("Negative test: authorization with non-existent user")
    public void testNonExistentUser() {
        // Test for authorization with non-existent user
        CourierAuthorization autho = CourierAuthorization.invalidLogin();

        // Authorization with non-existent user and expecting an unaccounted data error
        ValidatableResponse invalidLoginResponse = check.loginCourier(autho);
        check.invalidCredentials(invalidLoginResponse);
    }

}