package pl.task.stock.service;

import pl.task.stock.model.StockData;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MainClassServiceImpl implements MainClassService {

    public void validateDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate))
            throw new IllegalArgumentException("Start date is after end date");
    }

    public List<StockData> reduceStockDataToSelected(Map<LocalDate, StockData> stockDataMap,
                                                      LocalDate startDate, LocalDate endDate){
        return stockDataMap.entrySet()
                .parallelStream()
                .filter((s) -> s.getKey().isAfter(startDate.minusDays(1)) && s.getKey().isBefore(endDate.plusDays(1)))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }

}
