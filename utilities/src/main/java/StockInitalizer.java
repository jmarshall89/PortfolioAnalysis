import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jmarshall on 4/17/16.
 *
 * This is a stateless class used to initialize a stock.
 * Initializing a stock refers to parsing the prices
 * either from a CSV or (todo from an api).
 *
 * There is also functunality for calculating returns.
 */
public final class StockInitalizer {

    /**
     * initalizes the stock by populating the prices.
     * Either parses the CSV or pulls from an api (future state)
     * @param stock
     */
    public static void populatePrices(Stock stock) {
        Map<LocalDate, Double> prices;
        prices = CSVReader.getAdjClose(stock.getTicker(), Constants.STOCK_DIRECTORY);
        stock.setPrices(prices);
    }

    public static void populateReturns(Stock stock) {
        if (stock.getPrices().isEmpty()) {
            populatePrices(stock);
        }
        Map<LocalDate, Double> returns = new TreeMap<>();
        //Dates are in asending order
        Double prevDate = null;
        Double thisDate;
        for (LocalDate date : stock.getPrices().keySet()) {
            if (prevDate == null) {
                prevDate = stock.getPrices().get(date);
                continue;
            }
            thisDate = stock.getPrices().get(date);
            Double percent = Math.log(thisDate / prevDate);
            returns.put(date, percent);
            prevDate = thisDate;
        }
        stock.setReturns(returns);
    }

}
