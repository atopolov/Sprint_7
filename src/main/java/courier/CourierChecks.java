package courier;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class CourierChecks {
    public static final String COURIER_PATH = "/api/v1/courier";

    @Step("Sending a POST request to create a courier")
    public ValidatableResponse createCourier(Courier courier) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_PATH)
                .then().log().all();
    }

    @Step("Checking successful courier creation and outputting the status to the console")
    public void successfulCreated(ValidatableResponse createResponse) {
        boolean created = createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .extract()
                .path("ok");

        assertTrue(created);
    }

    @Step("Checking response to conflict when creating two identical couriers")
    public void conflictCourier(ValidatableResponse conflictResponse) {
        conflictResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CONFLICT);
    }

    @Step("Checking response for invalid credentials")
    public void invalidCredentials(ValidatableResponse notFoundResponse) {
        notFoundResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_NOT_FOUND);
    }

    @Step("Checking the response to a request without authorization")
    public void withoutLogin(ValidatableResponse badRequestResponse) {
        badRequestResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_BAD_REQUEST);
    }

    @Step("Courier authorization")
    public ValidatableResponse loginCourier(CourierAuthorization autho) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(autho)
                .when()
                .post(COURIER_PATH + "/login")
                .then().log().all();
    }

    @Step("Receiving a courier ID after authorization")
    public int authorization(ValidatableResponse loginResponse) {
        int id = loginResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK)
                .extract()
                .path("id");
        return id;
    }

    @Step("Removing a courier by ID")
    public ValidatableResponse deleteCourier(int id) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(Map.of("id", id))
                .when()
                .delete(COURIER_PATH + "/" + id)
                .then().log().all()
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_OK);
    }


    public void deleteSuccesfully(ValidatableResponse response) {
    }
}