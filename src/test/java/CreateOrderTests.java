
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import order.OrderModel;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static order.OrderSteps.cancelOrder;
import static order.OrderSteps.createOrder;
import static org.hamcrest.Matchers.notNullValue;


@RunWith(Parameterized.class)
public class CreateOrderTests extends BaseScript {

    private static final List<OrderModel> ORDERS = List
            .of(new OrderModel(BaseScript.TEST_FIRST_NAME, BaseScript.TEST_LAST_NAME, BaseScript.TEST_ADDRESS, BaseScript.TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT, TEST_COLOR_BLACK),
                    new OrderModel(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADDRESS, TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT, TEST_COLOR_GREY),
                    new OrderModel(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADDRESS, TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT, TEST_COLORS),
                    new OrderModel(TEST_FIRST_NAME, TEST_LAST_NAME, TEST_ADDRESS, TEST_METRO_STATION, TEST_PHONE, TEST_RENT_TIME, TEST_DELIVERY_DATE, TEST_COMMENT));
    private final OrderModel order;


    public CreateOrderTests(OrderModel order) {
        this.order = order;
    }

    @Parameterized.Parameters
    public static List<OrderModel> getOrderCreationTestData() {
        return ORDERS;
    }

    @Test
    @DisplayName("Создание заказа")
    @Description("Позитивный тест создания заказа с параметризацией")
    public void checkOrderCreate() {
        var response = createOrder(order);
        response.then()
                .assertThat()
                .body("track", notNullValue())
                .and()
                .statusCode(HTTP_CREATED);
        order.setTrack(response.jsonPath().getString("track"));
    }

    @After
    public void deleteData() {
        cancelOrder(order.getTrack());
    }
}