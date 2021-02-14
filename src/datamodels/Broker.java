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
	private List<Investor> listOfClients = new ArrayList<>();;

	/**
     * no-arg constructor
     */
	public Broker() {
	}
	
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
	}
		
	 /**
	 * Returns the dateOfHire of the Broker object
	 */
	public Calendar getDateOfHire() {
		return this.dateOfHire;
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
		return this.dateOfTermination;
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
		return this.salary;
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
		return this.status;
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
		return this.listOfClients;
	}
	
	 /**
	 * Adds a client to the listOfClients for the Broker object
	 */
	public void addClient(Investor newClient) {
		this.listOfClients.add(newClient);
	}
	

    /**
     * Returns a String representation of the StockQuote object in JSON format
     */
    @Override
    public String toString() {
    	StringBuilder builder = new StringBuilder();
		builder.append( "\nBroker: \n"
        		+ "---------------\n"
        		+ this.name + "\n"
        		+ this.address + "\n"
        		+ "Date of Birth : " + DateFunctions.dateToString(this.dateOfBirth) + "\n"
                + "Date of Hire : " + DateFunctions.dateToString(this.dateOfHire) + "\n"
                + "Date of Termination : " + DateFunctions.dateToString(this.dateOfTermination) + "\n"
                + "Salary : " + this.salary + "\n"
                + "Status : " + this.status + "\n"
                + "Id : " + this.id + "\n"
                + "\nList Of Clients : \n---------------\n" );
                
          for(Investor client: listOfClients) {
        	  builder.append(client.getName());
        	  builder.append("\n");
          }
          return  builder.toString();
               
    }

    /**
     * Returns a unique long integer of the StockQuote object in JSON format
     */
	public long getId() {
		return id;
	}
	
    /**
     * These methods compares two Broker objects to determine equality.
     *
     * @param obj the object being compared
     * @return true if members are equal
     */


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dateOfHire, dateOfTermination, listOfClients, salary, status);
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
		Broker other = (Broker) obj;
		return Objects.equals(dateOfHire, other.dateOfHire)
				&& Objects.equals(dateOfTermination, other.dateOfTermination)
				&& Objects.equals(listOfClients, other.listOfClients)
				&& Double.doubleToLongBits(salary) == Double.doubleToLongBits(other.salary)
				&& Objects.equals(status, other.status);
	}
}

