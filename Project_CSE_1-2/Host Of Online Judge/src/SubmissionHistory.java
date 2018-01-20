import java.io.*;

public class SubmissionHistory implements Serializable {
    String all;

    public SubmissionHistory(File file) {
        all = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true){
                String s = bufferedReader.readLine();
                if (s == null)
                    break;
                all = all + s + "\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getAll() {
        return all;
    }

    public void setAll(File file) {
        all = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true){
                String s = bufferedReader.readLine();
                if (s == null)
                    break;
                all = all + s;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
