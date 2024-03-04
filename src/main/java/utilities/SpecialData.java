package utilities;
import io.github.cdimascio.dotenv.Dotenv;

public class SpecialData {

    private String email;
    private String name;
    private String phoneNumber;
    private String password;

    public SpecialData(){
        Dotenv dotenv = Dotenv.configure().load();

        this.email = dotenv.get("EMAIL");
        this.name = dotenv.get("NAME");
        this.phoneNumber = dotenv.get("PHONE_NUMBER");
        this.password = dotenv.get("PASSWORD");
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }
}
