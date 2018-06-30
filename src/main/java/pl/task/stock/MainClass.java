package pl.task.stock;

import pl.task.stock.model.Stock;
import pl.task.stock.model.StockData;
import pl.task.stock.service.*;
import pl.task.stock.exception.NotFoundException;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class MainClass {

    private String companyName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String apiKey;

    private AlphaVentageService alphaVentageService;
    private CalculationService calculationService;
    private MainClassService mainClassService;

    public MainClass() {
        this.alphaVentageService = new AlphaVentageServiceImpl();
        this.calculationService = new CalculationServiceImpl();
        this.mainClassService = new MainClassServiceImpl();
    }

    public static void main(String[] args){
        MainClass mainClass = new MainClass();
        try {
            mainClass.printResult(args);
        } catch (IllegalArgumentException | DateTimeParseException | NotFoundException e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private void printResult(String[] args) {
        getArguments(args);
        Stock stock = alphaVentageService.getStockData(companyName, apiKey).orElseThrow(() -> new NotFoundException("Stock data not found"));
        List<StockData> stockData = mainClassService.reduceStockDataToSelected(stock.getTimeSeriesDaily(), startDate, endDate);
        DoubleSummaryStatistics summaryStatistics = stockData
                .parallelStream()
                .mapToDouble(s -> Double.parseDouble(s.getOpen()))
                .summaryStatistics();

        System.out.println(format(summaryStatistics.getAverage()));
        System.out.println(format(calculationService.calculateStandardDeviation(stockData)));
    }

    private void getArguments(String[] args) {
        if (args.length < 4)
            throw new IllegalArgumentException("Too few arguments type: <companyName> <startDate> <endDate> <apiKey>");
        this.companyName = args[0];
        this.startDate = LocalDate.parse(args[1]);
        this.endDate = LocalDate.parse(args[2]);
        this.apiKey = args[3];
        mainClassService.validateDates(startDate, endDate);
    }

    private String format(Double value){
        NumberFormat formatter = new DecimalFormat("#0.0000");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        return formatter.format(value);
    }
}
