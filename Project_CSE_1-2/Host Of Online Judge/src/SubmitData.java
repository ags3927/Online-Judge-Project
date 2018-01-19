import java.io.Serializable;

public class SubmitData implements Serializable{
    String submission;
    String language;
    String problem;

    public void setSubmission(String submission) {
        this.submission = submission;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getSubmission() {
        return submission;
    }

    public String getLanguage() {
        return language;
    }

    public String getProblem() {
        return problem;
    }

    public SubmitData(String submission, String language, String problem) {

        this.submission = submission;
        this.language = language;
        this.problem = problem;
    }
}
