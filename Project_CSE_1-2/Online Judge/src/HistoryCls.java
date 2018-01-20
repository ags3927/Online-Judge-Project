public class HistoryCls {
    String problem,verdict,lang;

    public HistoryCls(String lang, String problem, String verdict) {
        this.problem = problem;
        this.verdict = verdict;
        this.lang = lang;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
