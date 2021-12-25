package dashboard.model;

public class Kommune {
    private String name;
    private int confirmedCases;

    public Kommune() {
    }

    public Kommune(String name) {
        this.name = name;
    }

    public Kommune(String name, int confirmedCases) {
        this.name = name;
        this.confirmedCases = confirmedCases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(int confirmedCases) {
        this.confirmedCases = confirmedCases;
    }
}
