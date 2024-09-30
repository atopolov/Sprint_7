package courier;

public class Courier {
    private String login;
    private String password;
    private String firstName;


    public Courier() {
    }

    // Parametrized constructor
    public Courier(String login, String password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    //Static method for creating predefined courier
    public static Courier createdCourier() {
        // Predefined courier with login, password and name
        Courier courier = new Courier("ATopolov", "Kufojhme1989", "Andrei");
        return courier;
    }

    // Static method for creating duplicate courier (for demonstration)
    public static Courier duplicateCourier() {
        // Another courier with the same login, but different password and name
        Courier doubleCourier = new Courier("ATopolov", "Kufojhme1990", "Max");
        return doubleCourier;
    }

    // Static method for creating courier without required field (for demonstration)
    public static Courier noRequiredField() {
        // Courier without login (Null), with password and name
        Courier withoutLogin = new Courier(null, "Kufojhme1989", "Andrei");
        return withoutLogin;
    }

    // Method for getting login of courier
    public String getLogin() {
        return login;
    }

    // Method for setting the login of the courier
    public void setLogin(String login) {
        this.login = login;
    }

    //Method for getting the password of the courier
    public String getPassword() {
        return password;
    }

    // Method for setting the password of the courier
    public void setPassword(String password) {
        this.password = password;
    }

    // Method for getting the name of the courier
    public String getFirstName() {
        return firstName;
    }

    // Method for setting the name of the courier
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}