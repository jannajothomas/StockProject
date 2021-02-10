package datamodels;

import java.util.Calendar;
import java.util.Objects;

import interfaces.IPerson;
//import utilities.DateFunctions;

public class Person implements IPerson{
	String name;
	String address;
	Calendar dateOfBirth;
	Long id;
	
	//TODO: Implement this
	public Person() {
		
	}

	public Person(String name, String address, Calendar dateOfBirth, long id) {
		this.name=name;
		this.address=address;
		this.dateOfBirth=dateOfBirth;
		this.id=id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String value) {
		name=value;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String value) {
		address = value;
	}
	
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Calendar value) {
		dateOfBirth=value;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long value) {
		this.id = value;
	}
	
    /**
     * These methods compares two Person objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public int hashCode() {
        int hash = 5;
      //TODO: Is this the proper hash code for a string???
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.address);
        hash = 67 * hash + Objects.hashCode(this.dateOfBirth);
        //TODO: Is this the proper hash code for a long???
        hash = 67 * hash + Objects.hashCode(this.id);
        return hash;
    }
    
    @Override
	public boolean equals(Object obj) {
		return false;
		//TODO  Implement this
	}
	
    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
   // @Override
   /* public String toString() {
        return "Person: \n"
                + "Name: " + this.name + "\n"
                + "Address: " + this.address + "\n"
                + "Date Of Birth: " + DateFunctions.dateToString(this.dateOfBirth) + "\n"
                + "Id: " + this.id;
    }*/
	
}