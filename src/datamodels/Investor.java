package datamodels;


/**
 * This class creates a Investor object
 */
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import utilities.DateFunctions;

public class Investor extends Person{
	private Calendar memberSince;
	private List<InvestorStockQuote> listOfStocks;
	
	//TODO: Shoudl I do something with this constructor
	/**
	 * no-arg constuctor
	 */
	public Investor() {
		
	}
	
	//TODO: Update parameter descriptions
	
    /**
     * This constructor takes the value, tickerSymbol and quoteDate
     *
     * @param name The monetary value of the StockQuote object
     * @param address The stock ticker symbol of the StockQuote object
     * @param dateOfBirth The date of the StockQuote object
     * @param id The date of the StockQuote object
     * @parammemberSnce The date of the StockQuote object
     * @param quoteDate The date of the StockQuote object
     */
	
	//TODO: Implement This
	public Investor(String name, String address, Calendar dateOfBirth, long id, Calendar memberSince) {
		super(name, address, dateOfBirth, id);
		this.memberSince=memberSince;
		this.listOfStocks=null;	
	}
	
    /**
     * Returns the memberSince date of the Investor object
     */
	public Calendar getMemberSince() {
		return memberSince;
	}
	
	/**
	 * sets the value of the memberSince date of the Investor object
	 * @param memberSince
	 */
	public void setMemberSince(Calendar memberSince) {
		this.memberSince=memberSince;
	}
	
    /**
     * Returns the accountValue date of the Investor object
     */
	//TODO: Calculate this
	public double getAccountValue() {
		return 1.0;
	}
	
    /**
     * Returns the listOfStocks of the Investor object
     */
	public List<InvestorStockQuote> getListOfStocks(){
		return listOfStocks;
	}
	/**
	 * Adds an individual stock to the Investor object
	 * @param value is the monetary value of  the  stock quote
	 */
	//TODO: Implement this
	public void addStock(InvestorStockQuote value) {
		
	}

    /**
     * These methods compares two Investor objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.memberSince);
        //TODO: Is this the proper hash code for a list???
        hash = 67 * hash + Objects.hashCode(this.listOfStocks);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
    	//The item you are comparing to is the exact object
        if (this == obj) {
            return true;
        }
        //The object is null.  This object is initialize and can't be null
        if (obj == null) {
            return false;
        }
        //If the objects are not the same class, they cannot be equal
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        //TODO: What does this do?
        final Investor other = (Investor) obj;
        //Probabaly should check Person  variables too
        //check classes variable by variable
        
        
        if (!Objects.equals(this.memberSince, other.memberSince)) {
            return false;
        }
        if (!Objects.equals(this.listOfStocks, other.listOfStocks)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    @Override
    public String toString() {
        return "{\"Investor\":{" 
        		+ "\"name\":\"" + this.getName() + "\""
                + ", \"memberSince\":\"" + DateFunctions.dateToString(this.memberSince) + "\""
                + ", \"listOfStocks\":\"" + this.listOfStocks + "\""
                + "}}";
    }

    
	public long getId() {
		return id;
	}
	
}