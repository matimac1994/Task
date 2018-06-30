package pl.task.stock.service;

import org.junit.Before;
import org.junit.Test;
import pl.task.stock.model.StockData;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CalculationServiceImplTest {

    private static final double DELTA = 0.000000001;

    private static final String CLOSE1 = "49.222";
    private static final String CLOSE2 = "39.123";
    private static final String CLOSE3 = "45.987";

    private static final double PROPERLY_RESULT = 5.1570263072175;

    private List<StockData> STOCK_DATA = new ArrayList<>();

    private CalculationService calculationService;

    @Before
    public void setUp(){
        calculationService = new CalculationServiceImpl();
    }

    @Test
    public void calculateStandardDeviation() {
        fillStockData();
        double result = calculationService.calculateStandardDeviation(STOCK_DATA);
        assertEquals(PROPERLY_RESULT, result, DELTA);
    }

    @Test
    public void calculateStandardDeviationShouldReturnZeroWhenEmptyList() {
        STOCK_DATA.clear();
        double result = calculationService.calculateStandardDeviation(STOCK_DATA);

        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void calculateStandardDeviationShouldReturnZeroWhenListWithOneElement() {
        STOCK_DATA.clear();
        STOCK_DATA.add(createAndSetValue(CLOSE3));
        double result = calculationService.calculateStandardDeviation(STOCK_DATA);

        assertEquals(0.0, result, DELTA);
    }

    @Test
    public void calculateStandardDeviationShouldReturnZeroWhenListWithTheSameElements() {
        STOCK_DATA.clear();
        STOCK_DATA.add(createAndSetValue(CLOSE3));
        STOCK_DATA.add(createAndSetValue(CLOSE3));
        STOCK_DATA.add(createAndSetValue(CLOSE3));
        STOCK_DATA.add(createAndSetValue(CLOSE3));
        double result = calculationService.calculateStandardDeviation(STOCK_DATA);

        assertEquals(0.0, result, DELTA);
    }

    private void fillStockData() {
        STOCK_DATA.add(createAndSetValue(CLOSE1));
        STOCK_DATA.add(createAndSetValue(CLOSE2));
        STOCK_DATA.add(createAndSetValue(CLOSE3));
    }

    private StockData createAndSetValue(String closeValue){
        StockData stockData = new StockData();
        stockData.setClose(closeValue);
        return stockData;
    }
}