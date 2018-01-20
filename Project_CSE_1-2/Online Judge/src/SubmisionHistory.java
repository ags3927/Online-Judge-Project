import java.io.Serializable;
import java.util.ArrayList;

public class SubmisionHistory implements Serializable {
    ArrayList<String> history = new ArrayList<>();

    public void setHistory(String history) {
        this.history.add(history);
    }

    public ArrayList<String> getHistory() {
        return history;
    }
}
