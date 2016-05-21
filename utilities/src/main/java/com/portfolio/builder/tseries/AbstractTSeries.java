package com.portfolio.builder.tseries;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by jmarshall on 4/26/16.
 */
public interface AbstractTSeries {
    TreeMap<LocalDate, Double> getValues();
    Double getValue(LocalDate date);
    void add(LocalDate date, Double val);
    Set<LocalDate> dates();
    boolean isEmpty();
    List<Double> getSubset(LocalDate start, LocalDate end);
    Double totalReturn(LocalDate start, LocalDate end);
}
