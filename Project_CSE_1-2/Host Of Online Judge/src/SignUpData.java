import java.io.Serializable;

public class SignUpData implements Serializable{
    String Name, Handle, Email, Password;

    public SignUpData(String name, String handle, String email, String password) {
        Name = name;
        Handle = handle;
        Email = email;
        Password = password;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setHandle(String handle) {
        Handle = handle;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public String getHandle() {
        return Handle;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

}
