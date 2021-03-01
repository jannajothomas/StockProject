/*
 * Listens for events on the menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import controllers.reportformcontrollers.ListAllStockQuotesController;
import controllers.inputformcontrollers.InputStockQuoteFormController;
import controllers.reportformcontrollers.ListAllBrokersController;
import controllers.reportformcontrollers.ListAllInvestmentCompaniesController;
import controllers.reportformcontrollers.ListAllInvestorsController;
import controllers.reportformcontrollers.ListAllStockQuotesController;
import java.awt.event.ActionListener;
import datacontainers.BrokerDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datacontainers.InvestorDataContainer;
import datacontainers.StockQuoteDataContainer;
import exceptionhandlers.FileIOErrorPopup;
import exceptionhandlers.MyFileException;
import utilities.io.StockQuoteIO;

// to-do  Add additional import statements

import view.MainMenu;

public class MainMenuController implements ActionListener {

   // File location to store output files
   private String fileLocation;

   // The data models are instantiated here and passed to the 
   // constructors for the controllers
   private StockQuoteDataContainer stockQuoteDataContainer = new StockQuoteDataContainer();
   // to-do Add additional data containers

	private BrokerDataContainer brokerDataContainer = new BrokerDataContainer();
	private InvestmentCompanyDataContainer investmentCompanyDataContainer = new InvestmentCompanyDataContainer();
	private InvestorDataContainer investorDataContainer = new InvestorDataContainer();
   /**
    * Constructor
    *
    * @param fileLocation
    */
   public MainMenuController(String fileLocation) {
      // Store the file location, used in the save methods
      this.fileLocation = fileLocation;
   }

   // The main menu form gets created here. Notice it takes this controller object
   // as an argument to the constructor
   private MainMenu mainMenu = new MainMenu(this);

   /**
    * The ActionListener interface contains a single method, actionPerformed
    */
   public void actionPerformed(java.awt.event.ActionEvent event) {

      //  Figure out which button was clicked
      String menuItemClicked = event.getActionCommand();

      // create the controller which will open the correct form (refer to the controller constructor
      // methods do determine which data container classes need to be passed to the controller constructors)
      if (menuItemClicked.equals("Add Stock Quote")) {
         InputStockQuoteFormController inputController = new InputStockQuoteFormController(stockQuoteDataContainer);
      } else if (menuItemClicked.equals("List Available Stocks")) {
         ListAllStockQuotesController reportController = new ListAllStockQuotesController(stockQuoteDataContainer);
      }
      
      // to-do  Add additional menu items to add and report (this should already be done for assignment 5)
      
      if (menuItemClicked.equals("Add Investment Company")) {
         // Create an input form controller object for the investment compnay and pass the correct containers to the constructor   
    	  InputInvestmentCompanyFormController inputController = new InputInvestmentCompanyFormController(investmentCompanyDataContainer, brokerDataContainer);
      } else if (menuItemClicked.equals("List Investment Companies")) {
            StockQuoteIO.writeSerializedFile(fileLocation, stockQuoteDataContainer);
            StockQuoteIO.writeTextFile(fileLocation, stockQuoteDataContainer);
            StockQuoteIO.writeXMLFile(fileLocation, stockQuoteDataContainer);
            StockQuoteIO.writeJSONFile(fileLocation, stockQuoteDataContainer);
         // Create a report controller object for the investment company and pass the correct containers to the constructor  
    	  ListAllInvestmentCompaniesController reportController = new ListAllInvestmentCompaniesController(investmentCompanyDataContainer);
      }
      
      if (menuItemClicked.equals("Add Broker")) {
         // Create an input form controller object for the broker and pass the correct containers to the constructor
    	  InputBrokerFormController inputController = new InputBrokerFormController(brokerDataContainer, investorDataContainer);
      } else if (menuItemClicked.equals("List Brokers")) {
         // Create a report controller object for the broker and pass the correct containers to the constructor     
    	  ListAllBrokersController reportController = new ListAllBrokersController(brokerDataContainer);
      }
      if (menuItemClicked.equals("Add Investor")) {
         // Create an input form controller object for the investor and pass the correct containers to the constructor
    	  InputInvestorFormController inputController = new InputInvestorFormController(investorDataContainer, stockQuoteDataContainer);
      } else if (menuItemClicked.equals("List Investors")) {
         // Create a report controller object for the investor and pass the correct containers to the constructor  
    	  ListAllInvestorsController reportController = new ListAllInvestorsController(investorDataContainer);
      

else if (menuItemClicked.equals("Exit")) {
   System.exit(0);

      
      } else if (menuItemClicked.equals("Save Data")) {
         try {
            // All 4 types for demonstation
            StockQuoteIO.writeSerializedFile(fileLocation, stockQuoteDataContainer);
            StockQuoteIO.writeTextFile(fileLocation, stockQuoteDataContainer);
            StockQuoteIO.writeXMLFile(fileLocation, stockQuoteDataContainer);
            StockQuoteIO.writeJSONFile(fileLocation, stockQuoteDataContainer);
            
            // to-do  Add additional calls to write methods for the other objects
            
         } catch (MyFileException exp) {
            new FileIOErrorPopup(mainMenu, exp);
         }
      } else if (menuItemClicked.equals("Load Data")) {
         try {
            // All 4 types for demonstation
            stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readSerializedFile(fileLocation));
            stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readTextFile(fileLocation));
            stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readXMLFile(fileLocation).getStockQuoteList());
            stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readJSONFile(fileLocation));
         } catch (MyFileException exp) {
            new FileIOErrorPopup(mainMenu, exp);
         }
      }
   }

   // Getter used in the Application.java class
   public MainMenu getMainMenu() {
      return mainMenu;
   }
}
