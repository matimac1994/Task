package pl.task.stock.service;

import pl.task.stock.model.StockData;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface MainClassService {
    void validateDates(LocalDate startDate, LocalDate endDate);
    List<StockData> reduceStockDataToSelected(Map<LocalDate, pl.task.stock.model.StockData> stockDataMap,
                                                              LocalDate startDate, LocalDate endDate);
}
