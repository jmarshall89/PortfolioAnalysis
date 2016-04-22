import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jmarshall on 4/17/16.
 */
public class CSVReader {
    /**
     * A method for parsing a CSV. Requires a CSV to have specific date formatting in column 0
     * and adjusted close in column 6.
     * NOTE: does not adjust time period. Assumes CSV is monthly.
     * @param ticker Ticker name of the stock, as well as the name of the csv
     * @return a Treemap of adjusted close prices
     */
    //todo we are iterating through the folder in another class, we could do that here
    public static Map<LocalDate, Double> getAdjClose(String ticker, String pathSuffix) {
        Map<LocalDate, Double> adjClose = new TreeMap<>();
        try {
            String path = getCSVsPath(ticker, pathSuffix);
            CSVParser parser = getData(path);
            //CSV has dates in first column, and adj close in col 6
            for (CSVRecord record : parser) {
                adjClose.put(LocalDate.parse(record.get(0)), Double.parseDouble(record.get(6)));
            }
            //todo make this exception better
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adjClose;
    }

    public static CSVParser getData(String path) {
        CSVParser parser = null;
        try {
            parser = CSVParser.parse(new File(path), Charset.defaultCharset(), CSVFormat.EXCEL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser;
    }

    public static String getCSVsPath(String ticker, String pathSuffix) {
        String path = getCSVsPath(pathSuffix);
        return path + ticker + ".csv";
    }

    public static String getCSVsPath(String pathSuffix) {
        File here = new File("");
        //todo find some way to make this better (ex add csvs to classpath to locate quicker)
        return here.getAbsolutePath() + pathSuffix;
    }

}
