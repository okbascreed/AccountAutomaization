import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<MonthlyReport> monthlyReports = new ArrayList<>();
        YearlyReport yearlyReport = null;
        FileReader fileReader = new FileReader();

        int userInput = 1;

        while (userInput != 0) {
            printMenu();
            userInput = scanner.nextInt();
            switch (userInput) {
                case 1:
                    String path = new File("src").getAbsolutePath();
                    monthlyReports = fileReader.getMonthlyReportList(path);
                    for (int i = 0; i < monthlyReports.size(); i++) {

                        HashMap<String, Integer> items = monthlyReports.get(i).items;
                        System.out.println(items);
                    }
                    break;

                case 2:
                    yearlyReport = fileReader.getYearlyReport(new File("src").getAbsolutePath() + "/y.2021.csv");
                    for (Map.Entry<Integer, Integer[]> entry : yearlyReport.yearlyReport.entrySet()) {
                        Integer[] arr = entry.getValue();
                        System.out.println(entry.getKey() + " " + arr[0] + " " + arr[1]);
                    }
                    break;

                case 3:
                    int count = 0;
                    if ((yearlyReport.yearlyReport.isEmpty()) || (monthlyReports.isEmpty())) {
                        System.out.println("Ошибка! Пожалуйста считайте годовой и месячные отчеты.");
                        break;
                    }

                    for (int i = 0; i < monthlyReports.size(); i++) {

                        if (monthlyReports.get(i).getExpense() != yearlyReport.yearlyReport.get(i + 1)[0]) {
                            System.out.println("Расходы для месяца " + (i + 1) + " не совпадают.");
                        } else {
                            count += 1;
                        }
                        if (monthlyReports.get(i).getIncome() != yearlyReport.yearlyReport.get(i + 1)[1]) {
                            System.out.println("Доходы для месяца " + (i + 1) + " не совпадают.");
                        } else {
                            count += 1;
                        }
                    }
                    if (count == 0) {
                        System.out.println("Сверка завершена успешно!");
                    }
                    break;

                case 4:
                    if (yearlyReport == null || monthlyReports.isEmpty()) {
                        System.out.println("Ошибка! Пожалуйста считайте годовой и месячные отчеты.");
                        break;
                    }

                    for (int i = 0; i < monthlyReports.size(); i++) {
                        System.out.println("Месяц " + (i + 1));
                        int maxPrice = 0;
                        int maxExpense = 0;
                        String maxExpenseName = "";
                        String goodsName = "";

                        for (Map.Entry<String, Integer> entry : monthlyReports.get(i).items.entrySet()) {
                            Integer price = entry.getValue();
                            if (price > 0) {
                                if (price > maxPrice) {
                                    maxPrice = price;
                                    goodsName = entry.getKey();
                                }
                            } else {
                                if (price < maxExpense) {
                                    maxExpense = price;
                                    maxExpenseName = entry.getKey();
                                }
                            }
                        }
                        System.out.println("    Наименование товара -" + goodsName + ".");
                        System.out.println("    Сумма -" + maxPrice + ".");
                        System.out.println("    Самая большая трата - " + maxExpenseName + " составила " + (maxExpense * -1));
                    }
                    break;

                case 5:
                    if (yearlyReport == null) {
                        System.out.println("Ошибка! Пожалуйста считайте годовой отчет.");
                        break;
                    }

                    System.out.println("Год: 2021");
                    int averageIncome = 0;
                    int averageExpense = 0;

                    for (Map.Entry<Integer, Integer[]> entry : yearlyReport.yearlyReport.entrySet()) {
                        Integer[] values = entry.getValue();
                        int income = values[0] + values[1];
                        int month = entry.getKey();
                        System.out.println("Месяц " + month + " : " + income);
                        averageIncome = values[1] + averageIncome;
                        averageExpense = values[0] + averageExpense;
                    }

                    averageIncome = averageIncome / yearlyReport.yearlyReport.size();
                    averageExpense = averageExpense / yearlyReport.yearlyReport.size();
                    System.out.println("Средний расход = " + averageExpense);
                    System.out.println("Средний доход = " + averageIncome);
                    break;
            }
        }
    }

    static void printMenu() {
        System.out.println("Что вы хотите сделать? ");
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("0 - Выход");
    }
}






