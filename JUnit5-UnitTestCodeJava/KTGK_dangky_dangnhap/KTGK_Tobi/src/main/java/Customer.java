public class Customer {
    public String FName;
    public String LName;
    public String Email;
    public String Password;

    Customer (String FName, String LName, String Email, String Password){
        this.FName = FName;
        this.LName = LName;
        this.Email = Email;
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}
