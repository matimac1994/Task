package pl.task.stock.service;

import pl.task.stock.model.StockData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CalculationServiceImpl implements CalculationService {

    private List<Double> values = new ArrayList<>();

    @Override
    public Double calculateStandardDeviation(List<StockData> stockData) {
        this.values = stockData
                .parallelStream()
                .map(x -> Double.parseDouble(x.getClose()))
                .collect(Collectors.toList());
        return Math.sqrt(getVariance());
    }

    private Double getMean() {
        return values.stream().mapToDouble(x -> x).summaryStatistics().getAverage();
    }

    private Double getVariance() {
        Double mean = getMean();
        Double result = 0.0;
        for(Double val : values)
            result += Math.pow(val-mean, 2);

        return values.size() > 1 ? result/(values.size()-1) : result;
    }
}
