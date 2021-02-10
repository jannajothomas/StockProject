package datamodels;

import java.util.Calendar;
import java.util.Objects;
import interfaces.IPerson;
import utilities.DateFunctions;

public class Person implements IPerson{
	String name;
	String address;
	Calendar dateOfBirth;
	Long id;
	
	/**
	 * no-arg constuctor
	 */
	public Person() {
		
	}

    /**
     * This constructor takes the name, address, dateOfBirth, and id of a Person object
     *
     * @param name The name of the Investor object
     * @param address The address of the Investor object
     * @param dateOfBirth The dateOfBirth of the Investor object
     * @param id The id of the Investor object
     */
	public Person(String name, String address, Calendar dateOfBirth, long id) {
		this.name=name;
		this.address=address;
		this.dateOfBirth=dateOfBirth;
		this.id=id;
	}
	
    /**
     * Returns the name of the Person object
     */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the value of the name of the Person object
	 * @param value
	 */
	public void setName(String value) {
		name=value;
	}
	
	/**
     * Returns the address of the Person object
     */
	public String getAddress() {
		return address;
	}
	
	/**
	 * sets the value of the address of the Person object
	 * @param value
	 */
	public void setAddress(String value) {
		address = value;
	}
	
	/**
     * Returns the dateOfBirth of the Person object
     */
	public Calendar getDateOfBirth() {
		return dateOfBirth;
	}
	
	/**
	 * sets the value of the dateOfBirth of the Person object
	 * @param value
	 */
	public void setDateOfBirth(Calendar value) {
		dateOfBirth=value;
	}
	
	/**
     * Returns the id of the Person object
     */
	public long getId() {
		return id;
	}
	
	/**
	 * sets the value of the id of the Person object
	 * @param value
	 */
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
        return true;
	}
	
    /**
     * Returns a String representation of the Person object 
     */
    @Override
   public String toString() {
        return "Person: \n"
                + "Name: " + this.name + "\n"
                + "Address: " + this.address + "\n"
                + "Date Of Birth: " + DateFunctions.dateToString(this.dateOfBirth) + "\n"
                + "Id: " + this.id;
    }
	
}