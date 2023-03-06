package simple.mentoring.domain;

public enum Qualification {
    MENTEE("멘티"),
    MENTOR("멘토");

    private String krName;

    private Qualification() {
    }

    private Qualification(String krName) {
        this.krName = krName;
    }

    public String getKrName() {
        return krName;
    }
}
