package datamodels;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import utilities.DateFunctions;

/**
 * This class creates a Broker object
 */

public class Broker extends Person {
	private Calendar dateOfHire;
	private Calendar dateOfTermination;
	private double salary;
	private String status;
	private List<Investor> listOfClients;

	/**
     * no-arg constructor
     */
	public Broker() {
		this.listOfClients = new ArrayList<Investor>();
	}
	
	
	//TODO: How to add clients to list?
	
    /**
     * This constructor takes the name, address, dateOfBirth, id, dateOfHire, dateOfTermiantion, salary and status
     *
     * @param name The name of the Broker object
     * @param address The address of the Broker object
     * @param dateOfBirth The date of birth of the Broker object
     * @param id The id of the Broker object
     * @param dateOfHire The date of hire of the Broker object
     * @param dateOfTermiation The date of termination of the Broker object
     * @param salary The monetary salary of the Broker object
     * @param status The status of the Broker object
     * 
     */
	
	public Broker(String name, String address, Calendar dateOfBirth, long id, Calendar dateOfHire, Calendar dateOfTermination, double salary, String status) {
		//Call the Person initializer
		super(name, address, dateOfBirth, id);
		this.dateOfHire=dateOfHire;
		this.dateOfTermination=dateOfTermination;
		this.salary=salary;
		this.status=status;
		this.listOfClients = new ArrayList<Investor>();
	}
		
	 /**
	 * Returns the dateOfHire of the Broker object
	 */
	public Calendar getDateOfHire() {
		return dateOfHire;
	}
	
    /**
     * Sets the dateOfHire of the Broker object
     */
	public void setDateOfHire(Calendar dateOfHire) {
		this.dateOfHire=dateOfHire;
	}
	
	 /**
	 * Returns the dateOfTermination of the Broker object
	 */
	public Calendar getDateOfTermination() {
		return dateOfTermination;
	}
	
    /**
     * Sets the dateOfTermination of the Broker object
     */
	public void setDateOfTermination(Calendar dateOfTermination) {
		this.dateOfTermination=dateOfTermination;
	}
	
	 /**
	 * Returns the salary of the Broker object
	 */
	public double getSalary() {
		return salary;
	}
	
    /**
     * Sets the salary of the Broker object
     */
	public void setSalary(double salary) {
		this.salary=salary;
	}
	
	 /**
	 * Returns the status of the Broker object
	 */
	public String getStatus() {
		return status;
	}
	
    /**
     * Sets the status of the Broker object
     */
	public void setStatus(String status) {
		this.status=status;
	}
	
	 /**
	 * Returns the listOfClient for the Broker object
	 */
	public List<Investor> getListOfClients(){
		return listOfClients;
	}
	
	 /**
	 * Adds a client to the listOfClients for the Broker object
	 */
	//TODO: Implement This
	public void addClient(Investor newClient) {
		listOfClients.add(newClient);
	}
	
    /**
     * These methods compares two Broker objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.dateOfHire);
        hash = 67 * hash + Objects.hashCode(this.dateOfTermination);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.salary) ^ (Double.doubleToLongBits(this.salary) >>> 32));
        //TODO: Is this the proper hash code for a string and a list???
        hash = 67 * hash + Objects.hashCode(this.status);
        hash = 67 * hash + Objects.hashCode(this.listOfClients);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
    	//Check to see if the obj is the exact object(this)
        if (this == obj) {
            return true;
        }
        //Check to see if the object you are comparing to is null.  
        if (obj == null) {
            return false;
        }
        //Now that we know the object isn't null, we can check to see if objects are the same class
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        //We make an unchangable(final) copy of the object passed cast as a Broker so we can invoke its Broker methods
        final Broker other = (Broker) obj;

        //check classes variable by variable
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
        if (!Objects.equals(this.dateOfHire, other.dateOfHire)) {
            return false;
        }
        if (!Objects.equals(this.dateOfTermination, other.dateOfTermination)) {
            return false;
        }
        if (!Objects.equals(this.salary, other.salary)) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.listOfClients, other.listOfClients)) {
            return false;
        }
        return true;
    }
    
    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    @Override
    public String toString() {
        return "{\"Broker\":{"
                + "\"dateOfHire\":\"" + DateFunctions.dateToString(this.dateOfHire) + "\""
                + ", \"dateOfTermination\":\"" + DateFunctions.dateToString(this.dateOfTermination) + "\""
                + ", \"salary\":\"" + this.salary + "\""
                + ", \"salary\":\"" + this.listOfClients + "\""
                + "}}";
    }

    /**
     * Returns a unique long integer of the StockQuote object in JSON format
     */
	public long getId() {
		return id;
	}
}

