/*
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers.inputformcontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

import controllers.Application;
import datacontainers.BrokerDataContainer;
import datacontainers.InvestorDataContainer;
import datamodels.Broker;
import datamodels.Investor;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;
import utilities.date.DateFunctions;
import utilities.formatters.NumberFormatter;
import view.inputforms.BrokerInputForm;

public class InputBrokerFormController implements ActionListener {

   // The data containers are passed in via the constructor
   BrokerDataContainer brokerDataContainer;
   InvestorDataContainer investorDataContainer;

   // The form is declared here
   BrokerInputForm form;

   public InputBrokerFormController(BrokerDataContainer brokerDataContainer,
           InvestorDataContainer investorDataContainer) {

      this.brokerDataContainer = brokerDataContainer;
      this.investorDataContainer = investorDataContainer;
      form = new BrokerInputForm(this);
      form.setVisible(true);

   }

    /**
    * actionPerformed method implemented from the ActionListener interface
    *
    * This method figures out which button was clicked based on the text of the
    * button. The button click event is passed in via the ActionEvent object.
    *
    * @param event
    */
   public void actionPerformed(ActionEvent event) {
      if (event.getActionCommand().equals("Save")) {
         this.saveData();
      } else if (event.getActionCommand().equals("Clear")) {
         this.clearForm();
      } else if (event.getActionCommand().equals("Close")) {
         this.closeForm();
      }
   }

   /**
    * Private method to save all the data on the form and create a new Broker
    * object
 * @throws InvalidDataException 
    */
   private void saveData(){

      // Create a new broker
      Broker newBroker = new Broker(); 
	   
	      
      
      // Retrieve data from all form fields and store directly into object
      try {
	      try {
			newBroker.setName(form.getNameField().getText());
	      } catch (InvalidDataException exp) {
	    	  throw new MyFileException("Invalid name.  Name is required");
	      	}
	      try {
				newBroker.setAddress(form.getAddressField().getText());
	      } catch (InvalidDataException exp) {
				throw new MyFileException("Invalid address. Address required");
	      	}
	
	      // Retrieve id string and convert to a long before storing in object 
	      try {
	    	  
	    	 long idString = Integer.parseInt(form.getIdField().getText());
			newBroker.setId(idString);
         } catch (NumberFormatException | InvalidDataException exp) {
            throw new MyFileException("Invalid ID");
	      	}
	
	      // Retrieve status from drop down list
	      String selectedStatus = (String) form.getStatusField().getSelectedItem();
	      try {
			newBroker.setStatus(selectedStatus);
	      } catch (InvalidDataException exp) {
			throw new MyFileException("Invalid status.  Valid status value are fulltime and partime");
	      	}
	
	     
	      try {
	    	  // Retrieve salary and convert to a double before storing in object
		      String salarystring = form.getSalaryField().getText();
		    	  Double salarydouble = Double.parseDouble(salarystring);
		    	  newBroker.setSalary(salarydouble);
		      } catch (NumberFormatException | InvalidDataException exp) {
		    	  throw new MyFileException("Invalid salary");
		      }
      

		      // Retrieve the dates  and convert to Calendar objects
		      String dateString = form.getDateOfBirthFormattedTextField().getText();
		      Calendar dateOfBirth = DateFunctions.stringToDate(dateString);
		      newBroker.setDateOfBirth(dateOfBirth);
		
		      dateString = form.getDateOfHireFormattedTextField().getText();
		      Calendar dateOfHire = DateFunctions.stringToDate(dateString);
		      newBroker.setDateOfHire(dateOfHire);
		
		      dateString = form.getDateOfTerminationFormattedTextField().getText();
		      Calendar dateOfTermination = DateFunctions.stringToDate(dateString);
		      newBroker.setDateOfTermination(dateOfTermination);


		      List clientList = (List) this.form.getDropdownClientList().getSelectedValuesList();

		      // Convert the generic objects to Strings and look them up in the investor data container
		      for (Object selectedClient : clientList) {
		         String selectedClientString = (String) selectedClient;
		         String[] clientStringNameId = selectedClientString.split(":");

	            //Hold on to the id
	            long selectedId = Integer.parseInt(clientStringNameId[1].trim());

	         List<Investor> investorList = investorDataContainer.getInvestorList();
	         for (Investor investor : investorList) {
	            if (investor.getId() == selectedId) {
	               newBroker.addClient(investor);
	            }
	         }
		  }
		  this.brokerDataContainer.getBrokerList().add(newBroker);
      
			
			StringBuilder stringBuilder = new StringBuilder(100);
		
		  List<Investor> listOfClients = newBroker.getListOfClients();
		  for ( Investor investor: listOfClients) {
			  stringBuilder.append(investor.getName() + ",");
		  }
		  

		  
		  Application.getAPPLICATION_LOGGER().finest("Creating broker with the following values:"  + 
				"\n\t Name : " + newBroker.getName() + "," +
				"\n\t Address: " + newBroker.getAddress() + "," + 
				"\n\t Date of Birth: " + DateFunctions.dateToString(newBroker.getDateOfBirth()) + "," +
				"\n\t Date of Hire: " + DateFunctions.dateToString(newBroker.getDateOfHire()) + "," +
				"\n\t Date of Termination: " + DateFunctions.dateToString(newBroker.getDateOfTermination()) + "," +
				"\n\t Salary: " + NumberFormatter.formatCurrency(newBroker.getSalary()) + "," +
				"\n\t Status: " + newBroker.getStatus() + "," +
				"\n\t Clients: " + stringBuilder.toString());

      
      

      } catch (MyFileException exp) {
         new ErrorPopup(form, exp);}
      }
      

   

   /**
    * Private method to clear the data
    */
   private void clearForm() {

      // The text fields are set to blank
      this.form.getNameField().setText("");
      this.form.getAddressField().setText("");
      this.form.getSalaryField().setText("");
      this.form.getStatusField().setSelectedIndex(0);
      form.getDateOfBirthFormattedTextField().setText("");
      form.getDateOfHireFormattedTextField().setText("");
      form.getDateOfTerminationFormattedTextField().setText("");
      form.getDropdownClientList().clearSelection();

   }

   /**
    * Private method to close the form
    */
   private void closeForm() {
      this.form.dispose();
   }

   public BrokerDataContainer getBrokerDataContainer() {
      return brokerDataContainer;
   }

   public InvestorDataContainer getInvestorDataContainer() {
      return investorDataContainer;
   }

   public BrokerInputForm getForm() {
      return form;
   }
  
}
