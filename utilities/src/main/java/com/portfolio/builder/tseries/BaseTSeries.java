package com.portfolio.builder.tseries;

import com.portfolio.builder.MathHelper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by jmarshall on 4/26/16.
 */
public class BaseTSeries implements AbstractTSeries{
    private TreeMap<LocalDate, Double> values = new TreeMap<>();

    public void setValues(TreeMap<LocalDate, Double> values) {
        this.values = values;
    }

    @Override
    public TreeMap<LocalDate, Double> getValues() {
        return values;
    }

    @Override
    public Double getValue(LocalDate date) {
        return values.floorEntry(date).getValue();
    }

    @Override
    public void add(LocalDate date, Double val) {
        values.put(date, val);
    }

    @Override
    public Set<LocalDate> dates() {
        return values.keySet();
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public List<Double> getSubset(LocalDate start, LocalDate end) {
        return values.entrySet().stream()
                    .filter(x -> !x.getKey().isBefore(start))
                    .filter(x -> x.getKey().isBefore(end))
                    .map(Map.Entry::getValue)
                    .collect(Collectors.toList());
    }

    @Override
    public Double totalReturn(LocalDate start, LocalDate end) {
        List<Double> returns = getSubset(start, end);
        int size = returns.size();
        if (size == 0) return 0d;
        double mean = MathHelper.mean(returns);
        double exp = mean * size;
        return Math.exp(exp) - 1;
    }

}
