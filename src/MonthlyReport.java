import java.util.HashMap;
import java.util.Map;

public class MonthlyReport {
    HashMap<String, Integer> items = new HashMap<>();


    public int getIncome() {
        int income = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (entry.getValue() > 0) {
                income += entry.getValue();
            }
        }
        return income;
    }

    public int getExpense() {
        int expense = 0;
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            if (entry.getValue() < 0) {
                expense += entry.getValue();
            }
        }
        return expense;
    }
}
