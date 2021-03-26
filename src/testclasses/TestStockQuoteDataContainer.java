package testclasses;

import java.util.Calendar;
import java.util.Iterator;

import datacontainers.StockQuoteDataContainer;
import datamodels.StockQuote;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;

public class TestStockQuoteDataContainer {

    public static void main(String[] args) throws InvalidDataException, MyFileException {

        StockQuoteDataContainer stockQuoteContainer = new StockQuoteDataContainer();

        //  test data
        double value = 12.34;

        // Create some stocks values and store in the containers  
        for (int i = 0; i < 10; i++) {
            // Create a stock object
            StockQuote stock = new StockQuote();
            stock.setQuoteDate(Calendar.getInstance());
            stock.setTickerSymbol("AAPL");
            value = value + 0.34;
            stock.setValue(value);

            // Store in the list in the data container
            stockQuoteContainer.getStockQuoteList().add(stock);

            // Store in the map in the data container (use the array index as the key value)
            stockQuoteContainer.getStockQuoteMap().put(i, stock);

            // Store in the set in the data container 
            stockQuoteContainer.getStockQuoteSet().add(stock);
        }

        // Verify the container values
        System.out.println("The List");
        for (int i = 0; i < 10; i++) {
            System.out.println(stockQuoteContainer.getStockQuoteList().get(i));
        }

        System.out.println("The Map");
        for (int i = 0; i < 10; i++) {
            System.out.println(stockQuoteContainer.getStockQuoteMap().get(i));
        }

        System.out.println("The Set");
        for (Iterator<StockQuote> it = stockQuoteContainer.getStockQuoteSet().iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    }

}
