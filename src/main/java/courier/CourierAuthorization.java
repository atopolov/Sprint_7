package courier;

public class CourierAuthorization {
    private String login;
    private String password;


    public CourierAuthorization() {
    }

    // Parametrized constructor
    public CourierAuthorization(String login, String password) {
        this.login = login;
        this.password = password;
    }

    // Static method for creating CourierAuthorization object from Courier object
    public static CourierAuthorization from(Courier courier) {
        return new CourierAuthorization(courier.getLogin(), courier.getPassword());
    }

    // Static method for creating CourierAuthorization object with wrong password
    public static CourierAuthorization wrongPassword() {
        CourierAuthorization credentials = new CourierAuthorization("ATopolov", "wrong1996");
        return credentials;
    }

    // Static method for creating CourierAuthorization object without login
    public static CourierAuthorization missedLogin() {
        CourierAuthorization autho = new CourierAuthorization(null, "Kufojhme1989");
        return autho;
    }

    // Static method for creating CourierAuthorization object with invalid login
    public static CourierAuthorization invalidLogin() {
        CourierAuthorization autho = new CourierAuthorization("otopolov", "Kufojhme1989");
        return autho;
    }

    // Method for getting login
    public String getLogin() {
        return login;
    }

    // Method for setting login
    public void setLogin(String login) {
        this.login = login;
    }

    // Method for getting password
    public String getPassword() {
        return password;
    }

    // Method for setting password
    public void setPassword(String password) {
        this.password = password;
    }
}