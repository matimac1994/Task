package pl.task.stock.service;

import org.junit.Before;
import org.junit.Test;
import pl.task.stock.model.StockData;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MainClassServiceImplTest {

    private static final LocalDate START_DATE = LocalDate.of(2018, 6, 26);
    private static final LocalDate END_DATE = LocalDate.of(2018, 6, 29);

    private int VALID_LENGTH;

    private Map<LocalDate, StockData> STOCK_DATA = new HashMap<>();

    private MainClassService mainClassService;

    @Before
    public void setUp(){
        mainClassService = new MainClassServiceImpl();
        fillFakeData();
    }

    private void fillFakeData() {
        // SHOULD STAY
        STOCK_DATA.put(START_DATE, new StockData());
        STOCK_DATA.put(END_DATE, new StockData());
        STOCK_DATA.put(START_DATE.plusDays(1), new StockData());

        VALID_LENGTH = STOCK_DATA.size();

        // SHOULD REDUCE
        STOCK_DATA.put(START_DATE.minusDays(1), new StockData());
        STOCK_DATA.put(START_DATE.minusDays(5), new StockData());
        STOCK_DATA.put(END_DATE.plusDays(1), new StockData());
        STOCK_DATA.put(END_DATE.plusDays(4), new StockData());
    }

    @Test
    public void validateDatesShouldNotThrow() {
        mainClassService.validateDates(START_DATE, END_DATE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void validateDatesShouldThrow() {
        mainClassService.validateDates(END_DATE, START_DATE);
    }

    @Test
    public void reduceStockDataToSelected() {
        List<StockData> stockData = mainClassService.reduceStockDataToSelected(STOCK_DATA, START_DATE, END_DATE);

        assertNotNull(stockData);
        assertEquals(VALID_LENGTH, stockData.size());
        assertEquals(STOCK_DATA.size() - VALID_LENGTH, STOCK_DATA.size() - stockData.size());
    }
}