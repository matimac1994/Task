package pl.task.stock.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.annotation.Order;
import org.springframework.web.client.RestTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import pl.task.stock.model.Stock;
import pl.task.stock.model.StockData;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AlphaVentageServiceImplTest {

    private static final String OPEN_VALUE = "49.032";
    private static final LocalDate DATE = LocalDate.of(2018, 6, 29);

    private AlphaVentageService alphaVentageService;

    @Mock private RestTemplate restTemplate;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        alphaVentageService = new AlphaVentageServiceImpl(restTemplate);
        ReflectionTestUtils.setField(alphaVentageService, "ENDPOINT", "https://www.alphavantage.co/query");
    }

    @Test
    @Order(value = 1)
    public void methodGetRestTemplateShouldReturnInstance(){
        assertNotNull(alphaVentageService.getRestTemplate());
    }

    @Test
    public void getStockData() {
        Stock stock = fillFakeStockObject();

        when(restTemplate.getForObject(anyString(), any())).thenReturn(stock);

        Optional<Stock> returnedStock = alphaVentageService.getStockData("test", "test");

        assertNotNull(returnedStock);
        assertTrue(returnedStock.isPresent());
        assertEquals(OPEN_VALUE, returnedStock.get().getTimeSeriesDaily().get(DATE).getOpen());

        verify(restTemplate, times(1)).getForObject(anyString(), any());
        verifyNoMoreInteractions(restTemplate);
    }

    private Stock fillFakeStockObject(){
        Stock stock = new Stock();
        StockData stockData = new StockData();
        stockData.setOpen(OPEN_VALUE);
        Map<LocalDate, StockData> map = new HashMap<>();
        map.put(DATE, stockData);
        stock.setTimeSeriesDaily(map);
        return stock;
    }
}