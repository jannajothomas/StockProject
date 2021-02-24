/*
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers.inputformcontrollers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import datacontainers.*;
import datamodels.Investor;
import datamodels.InvestorStockQuote;
import datamodels.StockQuote;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.InvalidDataException;

import java.util.Calendar;
import java.util.List;
import utilities.DateFunctions;
import view.inputforms.InvestorInputForm;

public class InputInvestorFormController implements ActionListener {

   // The data datacontainers are passed in
   InvestorDataContainer investorDataContainer;
   StockQuoteDataContainer stockquoteDataContainer;

   // Input form is created here
   InvestorInputForm form;

   public InputInvestorFormController(InvestorDataContainer investorDataContainer,
           StockQuoteDataContainer stockquoteDataContainer) {

      // store local pointers to the data datacontainers passed in
      this.investorDataContainer = investorDataContainer;
      this.stockquoteDataContainer = stockquoteDataContainer;

      // create the form, pass it this controller
      form = new InvestorInputForm(this);

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
    * Private method to save all the data on the form and create a new investor
    * object
 * @throws InvalidDataException 
    */
   public void saveData()  {

      // Create a new Investor object used in the event methods
      Investor newInvestor = new Investor();

      try {
    	  try {
    			newInvestor.setName(form.getNameField().getText());
    		} catch (Exception exp) {

    			throw new InvalidDataException("Invalid value");
    		}
    	      try {
    			newInvestor.setAddress(form.getAddressField().getText());
    		} catch (Exception exp) {
    			throw new InvalidDataException("Invalid value");
    		}
    	      long investorIdString = Long.parseLong(form.getIdField().getText());
    	      try {
    			newInvestor.setId(investorIdString);
    		} catch (Exception exp) {
    			throw new InvalidDataException("Invalid value");
    		}

    	      // Retrieve the dates from the form and convert to Calendar objects
    	      String dateString = form.getDateOfBirthFormattedTextField().getText();
    	      Calendar dateOfBirth = DateFunctions.stringToDate(dateString);
    	      newInvestor.setDateOfBirth(dateOfBirth);

    	      dateString = form.getMemberSinceFormattedTextField().getText();
    	      Calendar memberSince = DateFunctions.stringToDate(dateString);
    	      newInvestor.setMemberSince(memberSince);
    	  
      } catch(InvalidDataException exp) {
    	  new ErrorPopup(form, exp);
      }
      // to-do - Add exception handling inside one or more try/catch blocks for the fields that require validation      
      // Retrieve data from all text fields and store directly into object
      

      // Retrieve all selected stocks and add to investor's stock list
      // The list only contains stock names.  Need to look them up
      // in the stock quote data container and add pointers to the objects in
      // the data container
      // Retrieve the selected stocks (the returned list is a list of generic objects)
      List stockList = (List) this.form.getDropdownStockList().getSelectedValuesList();

      // Convert the generic objects to Strings before looking up the ticker symbol
      for (Object selectedStock : stockList) {
         String selectedStockString = (String) selectedStock;

         // Split the string into name and id.  We'll use the unique id to find
         // the investor in the investor data container
         String[] stockInfo = selectedStockString.split(":");

         //Hold on to the ticker symbol
         String ticker = stockInfo[0].strip();

         // Find the stock quote in the stock data container based on the ticker symbol
         // Temporay  arraylist of stock quotes from the stock quote data container
         List<StockQuote> stockquoteList = stockquoteDataContainer.getStockQuoteList();

         for (StockQuote stock : stockquoteList) {
            if (stock.getTickerSymbol().equals(ticker)) {
               // Store an investor stock quote 
               InvestorStockQuote investorStock = new InvestorStockQuote(stock, 100);
               newInvestor.addStock(investorStock);
            }
         }
      }

      // to-do - Add the new investor to the data container
   }

   /**
    * Private method to clear the data
    */
   private void clearForm() {
      // The text fields are set to blank
      form.getNameField().setText("");
      form.getIdField().setText("");
      form.getAddressField().setText("");
      form.getGpaField().setText("");
      form.getDateOfBirthFormattedTextField().setText("");
      form.getMemberSinceFormattedTextField().setText("");
   }

   /**
    * Private method to close the form
    */
   private void closeForm() {
      form.dispose();
   }

   public InvestorDataContainer getInvestorDataContainer() {
      return investorDataContainer;
   }

   public StockQuoteDataContainer getStockquoteDataContainer() {
      return stockquoteDataContainer;
   }

   public InvestorInputForm getForm() {
      return form;
   }
   
}