import java.util.HashMap;

public class YearlyReport {
    HashMap<Integer, Integer[]> yearlyReport = new HashMap<>();

    public YearlyReport() {
        this.yearlyReport.put(1, new Integer[2]);
        this.yearlyReport.put(2, new Integer[2]);
        this.yearlyReport.put(3, new Integer[2]);
    }
}
