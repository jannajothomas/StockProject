/*
 * Listens for events on the input form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed" - this method contains all the logic to process the data
 * on the form, as well as several other events
 */
package controllers.inputformcontroller;

import java.awt.event.ActionListener;
import view.inputforms.StockQuoteInputForm;
import datacontainers.StockQuoteDataContainer;
import datamodels.StockQuote;
import exceptionhandlers.ErrorPopup;
import exceptionhandlers.InvalidDataException;
import java.util.Calendar;
import utilities.DateFunctions;

public class InputStockQuoteFormController implements ActionListener {

   // The data container is passed in via the constructor
   private StockQuoteDataContainer m_stockQuoteDataContainer;

   // The form is created here and this constructor object gets passed to it
   private StockQuoteInputForm form;

   // Constructor 
   public InputStockQuoteFormController(StockQuoteDataContainer p_stockQuoteDataContainer) {

      // Store the passed in data container(s)
      this.m_stockQuoteDataContainer = p_stockQuoteDataContainer;

      // create the form, passing in this controller object to the constructor
      form = new StockQuoteInputForm(this);

      // make the form visible
      this.form.setVisible(true);
   }

   /**
    * actionPerformed method implemented from the ActionListener interface
    *
    * This method figures out which button was clicked based on the text of the
    * button. The button click event is passed in via the ActionEvent object.
    *
    * @param event
    */
   public void actionPerformed(java.awt.event.ActionEvent event) {

      if (event.getActionCommand().equals("Save")) {
         this.saveData();
      } else if (event.getActionCommand().equals("Clear")) {
         this.clearForm();
      } else if (event.getActionCommand().equals("Close")) {
         this.closeForm();
      }
   }

   /**
    * Private save method. If an error is thrown, handle it by creating an error
    * popup and don't save the stock quote
    */
   private void saveData() {

      // Create a new StockQuote object
      StockQuote newQuote = new StockQuote();

      try {

         // Retrieve the stock ticker symbol
         String tickerSymbol = form.getTickerSymbolTextfield().getText();
         newQuote.setTickerSymbol(tickerSymbol);

         // retrieve value
         try {
            double value = Double.parseDouble(form.getStockValueTextField().getText());
            newQuote.setValue(value);
         } catch (Exception exp) {
            throw new InvalidDataException("Invalid value.  Value must be a number");
         }

         // Retrieve the dates from the form and convert to Calendar objects
         String dateString = form.getQuoteDateFormattedTextField().getText();
         Calendar quoteDate = DateFunctions.stringToDate(dateString);
         newQuote.setQuoteDate(quoteDate);

         // No errors, so add the new stock quote to the data container
         this.m_stockQuoteDataContainer.getStockQuoteList().add(newQuote);

      } catch (InvalidDataException exp) {
         // If we caught any errors, create an error popup, passing the form
         // that caused the error along with the actual error
         new ErrorPopup(form, exp);
      }
   }

   /**
    * Private method to clearForm the data
    */
   private void clearForm() {
      form.getTickerSymbolTextfield().setText("");
      form.getQuoteDateFormattedTextField().setText("");
      form.getStockValueTextField().setText("");
   }

   /**
    * Private method to close the form
    */
   private void closeForm() {
      this.form.dispose();
   }

   // gettes to access the private data in the controller object
   public StockQuoteDataContainer getDataModel() {
      return m_stockQuoteDataContainer;
   }

   public StockQuoteInputForm getForm() {
      return form;
   }

   public StockQuoteDataContainer getM_stockQuoteDataContainer() {
      return m_stockQuoteDataContainer;
   }   
}
