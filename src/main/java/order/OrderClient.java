package order;

import java.util.List;

public class OrderClient {
    private String firstName;        // Client's first name
    private String lastName;         // Client's last name
    private String address;          // Delivery address
    private String metroStation;     // Metro station
    private String phone;            // Client's phone
    private String rentTime;         // Rent time
    private String deliveryDate;     // Delivery date
    private String comment;          // Comments for the order
    private List<String> color;      // List of colors

    public OrderClient() {

    }

    // Parametrized constructor for initializing all fields
    public OrderClient(String firstName, String lastName, String address, String metroStation, String phone,
                       String rentTime, String deliveryDate, String comment, List<String> colors) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metroStation = metroStation;
        this.phone = phone;
        this.rentTime = rentTime;
        this.deliveryDate = deliveryDate;
        this.comment = comment;
        this.color = colors;
    }

    // Static method for creating predefined OrderClient object
    public static OrderClient createOrderClient(List<String> colors) {
        OrderClient orderClient = new OrderClient("DenBel", "Belden", "Lenina, 20",
                "7", "+7 999 888 77 66", "3", "2024-06-19",
                "Please hurry up", colors);
        return orderClient;
    }

    // Getters and Setters for all fields
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMetroStation() {
        return metroStation;
    }

    public void setMetroStation(String metroStation) {
        this.metroStation = metroStation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRentTime() {
        return rentTime;
    }

    public void setRentTime(String rentTime) {
        this.rentTime = rentTime;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> colors) {
        this.color = colors;
    }
}