package datamodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import utilities.DateFunctions;

/**
 * This class creates a Investor object
 */


public class Investor extends Person{
	private Calendar memberSince;
	private List<InvestorStockQuote> listOfStocks;

	/**
	 * no-arg constuctor
	 */
	public Investor() {
		this.listOfStocks = new ArrayList<InvestorStockQuote>();
	}
	
    /**
     * This constructor takes the value, tickerSymbol and quoteDate
     *
     * @param name The name of the Investor object
     * @param address The address of the Investor object
     * @param dateOfBirth The dateOfBirth of the Investor object
     * @param id The id of the Investor object
     * @param memberSince The date of  memberSince of the Investor object
     */
	public Investor(String name, String address, Calendar dateOfBirth, long id, Calendar memberSince) {
		super(name, address, dateOfBirth, id);
		this.memberSince=memberSince;
		this.listOfStocks = new ArrayList<InvestorStockQuote>();	
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
	public void addStock(InvestorStockQuote value) {
		listOfStocks.add(value);
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
        
        final Investor other = (Investor) obj;
        if(!Objects.equals(this.name, other.name)) {
        	return false;
        }
        if(!Objects.equals(this.address, other.address)) {
        	return false;
        }
        if(!Objects.equals(this.dateOfBirth, other.dateOfBirth)) {
        	return false;
        }
        if(!Objects.equals(this.id, other.id)) {
        	return false;
        }
        if (!Objects.equals(this.memberSince, other.memberSince)) {
            return false;
        }
        if (!Objects.equals(this.listOfStocks, other.listOfStocks)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a String representation of the Investor object 
     */
    @Override
    public String toString() {
        return "{\"Investor\":{" 
        		+ "\"name\":\"" + this.getName() + "\""
                + ", \"memberSince\":\"" + DateFunctions.dateToString(this.memberSince) + "\""
                + ", \"listOfStocks\":\"" + this.listOfStocks + "\""
                + "}}";
    }

    /**
     * Returns a unique long integer of the Investor object 
     */
	public long getId() {
		return id;
	}
	
}