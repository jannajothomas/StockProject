package interfaces;

import java.util.Calendar;

public interface IPerson {
	
    /**
     * Returns the name of the Person object
     */
	public String getName();
	
    /**
     * Sets the name symbol of the Person object
     */
	public void setName(String value);
	
    /**
     * Returns the address of the Person object
     */
	public String getAddress();
	
    /**
     * Sets the name symbol of the Person object
     */
	public void setAddress(String value);
	
    /**
     * Returns the dateOfBirth of the Person object
     */
	public Calendar getDateOfBirth();
	
    /**
     * Sets the name dateOfBirth of the Person object
     */
	public void setDateOfBirth(Calendar value);
	
    /**
     * Returns the id of the Person object
     */
	public long getId();
	
    /**
     * Sets the name dateOfBirth of the Person object
     */
	public void setId(long value);
	
	 /**
     * These methods compares two StockQuote objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
	public boolean equals(Object obj);
	
	//TODO: CHECK AND ADD COMMENT  HERE
	public  int hashCode();
}
