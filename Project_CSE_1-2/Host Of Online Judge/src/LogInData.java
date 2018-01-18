import java.io.Serializable;

public class LogInData implements Serializable{
    String handle, password;

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LogInData(String handle, String password) {
        this.handle = handle;
        this.password = password;
    }

    public String getHandle() {
        return handle;
    }

    public String getPassword() {
        return password;
    }
}
