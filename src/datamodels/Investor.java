package datamodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import utilities.date.DateFunctions;

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
		return this.memberSince;
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
	public double getAccountValue() {
		double accountValue = 0;
		for(InvestorStockQuote stock : listOfStocks) {
			accountValue += stock.getShares() * stock.getStock().getValue();
		}
		return accountValue;
	}
	
    /**
     * Returns the listOfStocks of the Investor object
     */
	public List<InvestorStockQuote> getListOfStocks(){
		return this.listOfStocks;
	}
	
	/**
	 * Adds an individual stock to the Investor object
	 * @param value is the monetary value of  the  stock quote
	 */
	public void addStock(InvestorStockQuote value) {
		this.listOfStocks.add(value);
	}
    
    /**
     * Returns a String representation of the Investor object 
     */
    @Override
    public String toString() {
        return "\nInvestor:\n"
        		+ "---------------\n"
        		+ super.toString()
        		//+ this.getName() + "\n"
        		//+ this.getAddress() + "\n"
        		//+ "Date of Birth: " + DateFunctions.dateToString(this.dateOfBirth) + "\n"
                //+ "Id: " + this.getId() + "\n"
                + "Portfollio value: " + this.getAccountValue();
    }

    /**
     * Returns a unique long integer of the Investor object 
     */
	@Override
	public long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(listOfStocks, memberSince);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Investor other = (Investor) obj;
		return Objects.equals(listOfStocks, other.listOfStocks) && Objects.equals(memberSince, other.memberSince);
	}
	
}