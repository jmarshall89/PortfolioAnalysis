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

    //for testing
    public static void main(String[] args) {
        getAdjClose("FB");
    }

    public static Map<LocalDate, Double> getAdjClose(String ticker) {
        Map<LocalDate, Double> adjClose = new TreeMap<>();
        try {
            File here = new File("");
            String path = here.getAbsolutePath()+"/data-fetcher/src/main/resources/data/quoteCSV/";
            path = path + ticker + ".csv";
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


}
