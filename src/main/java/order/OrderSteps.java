package order;

import io.qameta.allure.Step;
import io.restassured.response.Response;

import static constant.Endpoints.CANCEL_ORDER;
import static constant.Endpoints.ORDERS;
import static io.restassured.RestAssured.given;

public class OrderSteps {

    @Step("Create order")
    public static Response createOrder(OrderModel order) {
        return given()
                .body(order)
                .when()
                .post(ORDERS)
                .then()
                .extract()
                .response();
    }

    @Step("Get orders list")
    public static Response getOrders() {
        return given()
                .when()
                .get(ORDERS)
                .then()
                .extract()
                .response();
    }

    @Step("Cancel order")
    public static void cancelOrder(String track) {
        given()
                .queryParam("track", track)
                .when()
                .put(CANCEL_ORDER);
    }
}