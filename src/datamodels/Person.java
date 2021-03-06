package datamodels;

import java.util.Calendar;
import java.util.Objects;

import exceptionhandlers.InvalidDataException;
import interfaces.IPerson;
import utilities.date.DateFunctions;

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
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * sets the value of the name of the Person object
	 * @param value
	 */
	@Override
	public void setName(String value) throws InvalidDataException{
		if(value.isEmpty()) {
			throw new InvalidDataException("Setting Person name failed, no name specified");
		}else {
			this.name=value;
		}
	}
	
	/**
     * Returns the address of the Person object
     */
	@Override
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * sets the value of the address of the Person object
	 * @param value
	 */
	@Override
	public void setAddress(String value) throws InvalidDataException{
		if(value.isEmpty()) {
			throw new InvalidDataException("Setting Person address failed, no address specified");
		}else {
			this.address = value;
		}
	}
	
	/**
     * Returns the dateOfBirth of the Person object
     */
	@Override
	public Calendar getDateOfBirth() {
		return this.dateOfBirth;
	}
	
	/**
	 * sets the value of the dateOfBirth of the Person object
	 * @param value
	 */
	@Override
	public void setDateOfBirth(Calendar value) {
		this.dateOfBirth=value;
	}
	
	/**
     * Returns the id of the Person object
     */
	@Override
	public long getId() {
		return this.id;
	}
	
	/**
	 * sets the value of the id of the Person object
	 * @param value
	 */
	@Override
	public void setId(long value) throws InvalidDataException{
		if(value<0) {
			throw new InvalidDataException("Setting id failed, invalid id: " + value);
		}else {
			this.id = value;
		}
		
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
                + "Id: " + this.id + "\n";
    }

	@Override
	public int hashCode() {
		return Objects.hash(address, dateOfBirth, id, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		return Objects.equals(address, other.address) && Objects.equals(dateOfBirth, other.dateOfBirth)
				&& Objects.equals(id, other.id) && Objects.equals(name, other.name);
	}
	
}