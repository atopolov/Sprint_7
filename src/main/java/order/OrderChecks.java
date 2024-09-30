package order;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.net.HttpURLConnection;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderChecks {
    public static final String PATH_ORDERS = "/api/v1/orders";

    @Step("Sending a POST request to create an order")
    public ValidatableResponse createdOrder(OrderClient orderClient) {
        return given().log().all()
                .contentType(ContentType.JSON)
                .body(orderClient)
                .when()
                .post(PATH_ORDERS)
                .then().log().all();
    }

    @Step("Checking the order creation and outputting the tracking number to the console")
    public void checkOrder(ValidatableResponse createResponse) {
        createResponse
                .assertThat()
                .statusCode(HttpURLConnection.HTTP_CREATED)
                .body("track", notNullValue());
    }

    @Step("Sending a GET request to get a list of orders")
    public void extractedOrderList() {
        given()
                .when()
                .get(PATH_ORDERS)
                .then()
                .statusCode(HttpURLConnection.HTTP_OK)
                .body("orders", notNullValue());
    }
}
