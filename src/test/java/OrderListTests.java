import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;


import static java.net.HttpURLConnection.HTTP_OK;
import static order.OrderSteps.getOrders;
import static org.hamcrest.Matchers.notNullValue;


public class OrderListTests {

    @Test
    @DisplayName("Получить список заказов")
    @Description("Базовый тест по получению списка заказов. В теле ответа возвращается список заказов")
    public void checkOrderList() {
        getOrders()
                .then()
                .assertThat()
                .statusCode(HTTP_OK)
                .assertThat()
                .body(notNullValue());
    }
}