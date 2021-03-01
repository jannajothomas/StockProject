package testclasses;

import org.junit.Test;
import static org.junit.Assert.*;
import java.text.ParseException;
import java.util.Calendar;
import datamodels.StockQuote;
import exceptionhandlers.MyFileException;

public class TestStockQuoteClassJunitTest {
	
	double testValue = 12.34;
	String testSymbol = "ABCD";

	StockQuote testQuoteNoParameters = new StockQuote();
	StockQuote testQuoteWithParameters = new StockQuote(testValue, testSymbol);
	Calendar testDate = Calendar.getInstance();
	 
	public TestStockQuoteClassJunitTest() {
		super();
	}
	
	@Test
	    public void testGetValue() {
	        assertEquals("Verify value is 0.0", 0.0, testQuoteNoParameters.getValue(), 0.001);
	        assertEquals("Verify value is correct", testValue, testQuoteWithParameters.getValue(), 0.001);
	    }

	@Test
	    public void testGetTickerSymbol() {
	        assertNull("Verify ticker symbol is null", testQuoteNoParameters.getTickerSymbol());
	        assertEquals("Verify ticker symbol is correct", testSymbol, testQuoteWithParameters.getTickerSymbol());
	    }

	@Test
	    public void testToStringAndDateToString() {
	        // also tests dateToString since toString calls it
	        assertEquals("Verify toString() is correct", "{\"StockQuote\":{\"value\":\"0.0\", \"tickerSymbol\":\"null\", \"quoteDate\":\"null\"}}", testQuoteNoParameters.toString());
	        assertEquals("Verify toString() is correct", "{\"StockQuote\":{\"value\":\"12.34\", \"tickerSymbol\":\"ABCD\", \"quoteDate\":\"null\"}}", testQuoteWithParameters.toString());
	    }

	@Test
	    public void testGetQuoteDate() throws ParseException, MyFileException {
	        Calendar quoteDate = ParseDate.parseDate("4/5/2019");
	        StockQuote testQuoteWithDate = new StockQuote(testValue, testSymbol, quoteDate);
	        assertEquals("Verify quote date is correct", quoteDate, testQuoteWithDate.getQuoteDate());
	    }

	@Test
	    public void testEqualsAndHashCode() throws ParseException, MyFileException {
	        Calendar quoteDate = ParseDate.parseDate("4/5/2019");
	        StockQuote quote1 = new StockQuote(testValue, testSymbol, quoteDate);
	        StockQuote quote2 = new StockQuote(testValue, testSymbol, quoteDate);
	        StockQuote quote3 = new StockQuote(43.21, testSymbol, quoteDate);
	        assertEquals("Verify StockQuotes are equal", quote1, quote2);
	        assertNotEquals("Verify StockQuotes are not equal", quote1, quote3);
	        assertEquals("Verify hashCodes are equal", quote1.hashCode(), quote2.hashCode());
	        assertNotEquals("Verify hashCodes are not equal", quote1.hashCode(), quote3.hashCode());
	    }
}