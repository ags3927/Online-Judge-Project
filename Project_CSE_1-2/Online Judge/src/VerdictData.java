import java.io.Serializable;

public class VerdictData implements Serializable {
    String verdict;
    String problem;

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getVerdict() {

        return verdict;
    }

    public String getProblem() {
        return problem;
    }

    public VerdictData(String verdict, String problem) {
        this.verdict = verdict;
        this.problem = problem;
    }
}
