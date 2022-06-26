import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class FileReader {
    ArrayList<MonthlyReport> report = new ArrayList<>();
    public ArrayList<MonthlyReport> getMonthlyReportList(String path) {


        String fileNameStart = "/m.20210";
        for (int i = 1; i <= 3; i++) {
            MonthlyReport monthlyReport = new MonthlyReport();
            String textFromFile = readFileContentsOrNull(path + fileNameStart + i + ".csv");
            String[] lines = textFromFile.split(System.lineSeparator());
            for (int j = 1; j < lines.length; j++) {
                String[] lineContents = lines[j].split(",");
                String name = lineContents[0];

                // количество умноженное на единицу товара
                int price = Integer.parseInt(lineContents[2]) * Integer.parseInt(lineContents[3]);
                if (lineContents[1].equals("TRUE")) { // если расход то умножаем на -1
                    price *= -1;
                }
                monthlyReport.items.put(name, price);
            }
            report.add(monthlyReport); // добавление в репорт, так как <= 3 добавится только 3 раза
        }
        return report;
    }

    public YearlyReport getYearlyReport(String path) {
        YearlyReport yearlyReport = new YearlyReport();
        String textFromFile = readFileContentsOrNull(path);
        String[] lines = textFromFile.split(System.lineSeparator());
        for (int i = 1; i < lines.length; i++) {
            String[] lineContents = lines[i].split(",");
            int month = Integer.parseInt(lineContents[0]); // месяц
            int price = Integer.parseInt(lineContents[1]); // сумма за месяц
            if (lineContents[2].equals("true")) { // если расход то умножаем на -1
                price *= -1;
                Integer[] array = yearlyReport.yearlyReport.get(month);
                array[0] = price;
                yearlyReport.yearlyReport.put(month, array);
            } else {
                Integer[] array = yearlyReport.yearlyReport.get(month);
                array[1] = price;
                yearlyReport.yearlyReport.put(month, array);
            }

        }
        return yearlyReport;
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
