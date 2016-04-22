import java.io.File;
import java.util.function.Function;

/**
 * Created by jmarshall on 4/19/16.
 * <p>
 *     Stateless class, which builds and returns a portfolio.
 *     Contains static methods for calculating necessary calcs
 *     for analysis.
 */
public final class PortfolioBuilder {

    public static Portfolio buildPortfolioFromFiles() {
        Portfolio portfolio = new Portfolio();
        String path = CSVReader.getCSVsPath(Constants.STOCK_DIRECTORY);
        File folder = new File(path);
        Function<String, Stock> makeStock = (a) -> new Stock(a);
        if (!folder.isDirectory()) {
            //todo make exception
            System.out.println("The following path is not a directory:");
            System.out.println(path);
            return null;
        }
        for (File stockCSV : folder.listFiles()) {
            populateStock(portfolio, stockCSV, Constants.STOCK_DIRECTORY, makeStock);
        }
        //Now add market
        String marketPath = CSVReader.getCSVsPath("SP500", Constants.SP500_DIRECTORY);
        File marketCSV = new File(marketPath);
        Function<String, Stock> makeMarket = (a) -> new Market(a);
        populateStock(portfolio, marketCSV, Constants.SP500_DIRECTORY, makeMarket);

        return portfolio;
    }

    private static void populateStock(Portfolio portfolio, File stockCSV, String directory, Function<String, Stock> build) {
        if (stockCSV.getName().substring(0,1).compareTo(".") == 0) return; // don't want hidden files
        String ticker = stockCSV.getName().split("[.csv]")[0]; //want to remove the .csv from name for ticker
        Stock stock = build.apply(ticker);
        stock.setPrices(CSVReader.getAdjClose(ticker, directory));
        portfolio.addStock(stock);
        StockInitalizer.populateReturns(stock);
    }







}
