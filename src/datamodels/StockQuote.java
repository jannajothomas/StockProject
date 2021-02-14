package datamodels;


/**
 * This class creates a StockQuote object
 */
import interfaces.StockQuoteInterface;
import java.util.Calendar;
import java.util.Objects;
import utilities.DateFunctions;

public class StockQuote implements StockQuoteInterface {

    private double value;
    private String tickerSymbol;
    private Calendar quoteDate;

    /**
     * no-arg constructor
     */
    public StockQuote() {
    }

    /**
     * This constructor takes the value, tickerSymbol and quoteDate
     *
     * @param value The monetary value of the StockQuote object
     * @param tickerSymbol The stock ticker symbol of the StockQuote object
     * @param quoteDate The date of the StockQuote object
     */
    public StockQuote(double value, String tickerSymbol, Calendar quoteDate) {
        this.value = value;
        this.tickerSymbol = tickerSymbol;
        this.quoteDate = quoteDate;
    }

     /**
     * This constructor takes the value, tickerSymbol
     *
     * @param value The monetary value of the StockQuote object
     * @param tickerSymbol The stock ticker symbol of the StockQuote object
     */
    public StockQuote(double value, String tickerSymbol) {
        this.value = value;
        this.tickerSymbol = tickerSymbol;
    }
    
    /**
     * This constructor takes a StockQuote object and creates a copy
     *
     * @param stockQuote The StockQuote to be copied
     */
    public StockQuote(StockQuote stockQuote) {
        this.value = stockQuote.getValue();
        this.tickerSymbol = stockQuote.getTickerSymbol();
        this.quoteDate = stockQuote.getQuoteDate();
    }

    /**
     * Returns the value of the StockQuote object
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Returns the tickerSymbol of the StockQuote object
     */
    public String getTickerSymbol() {
        return this.tickerSymbol;
    }

    /**
     * Returns the quoteDate of the StockQuote object
     */
    public Calendar getQuoteDate() {
        return this.quoteDate;
    }

    /**
     * Sets the value of the StockQuote object
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Sets the ticker symbol of the StockQuote object
     */
    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    /**
     * Sets the quoteDate of the StockQuote object
     */
    public void setQuoteDate(Calendar quoteDate) {
        this.quoteDate = quoteDate;
    }

    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    @Override
    public String toString() {
        return "{\"StockQuote\":{"
                + "\"value\":\"" + this.value + "\""
                + ", \"tickerSymbol\":\"" + this.tickerSymbol + "\""
                + ", \"quoteDate\":\"" + DateFunctions.dateToString(this.quoteDate) + "\""
                + "}}";
    }

	@Override
	public int hashCode() {
		return Objects.hash(quoteDate, tickerSymbol, value);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StockQuote other = (StockQuote) obj;
		return Objects.equals(quoteDate, other.quoteDate) && Objects.equals(tickerSymbol, other.tickerSymbol)
				&& Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
}