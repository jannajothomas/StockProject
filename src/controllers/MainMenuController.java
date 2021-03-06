/*
 * Listens for events on the menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import controllers.reportformcontrollers.ListAllStockQuotesController;
import controllers.inputformcontrollers.InputBrokerFormController;
import controllers.inputformcontrollers.InputInvestmentCompanyFormController;
import controllers.inputformcontrollers.InputInvestorFormController;
import controllers.inputformcontrollers.InputStockQuoteFormController;
import controllers.reportformcontrollers.ListAllBrokersController;
import controllers.reportformcontrollers.ListAllInvestmentCompaniesController;
import controllers.reportformcontrollers.ListAllInvestorsController;
import java.awt.event.ActionListener;
import datacontainers.BrokerDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datacontainers.InvestorDataContainer;
import datacontainers.StockQuoteDataContainer;
import exceptionhandlers.FileIOErrorPopup;
import exceptionhandlers.MyFileException;
import utilities.io.BrokerIO;
import utilities.io.InvestmentCompanyIO;
import utilities.io.InvestorIO;
import utilities.io.StockQuoteIO;
import view.MainMenu;

public class MainMenuController implements ActionListener {

   // File location to store output files
   private String fileLocation;

   // The data models are instantiated here and passed to the 
   // constructors for the controllers
   private StockQuoteDataContainer stockQuoteDataContainer = new StockQuoteDataContainer();
   private BrokerDataContainer brokerDataContainer = new BrokerDataContainer();
   private InvestmentCompanyDataContainer investmentCompanyDataContainer = new InvestmentCompanyDataContainer();
   private InvestorDataContainer investorDataContainer = new InvestorDataContainer();
   private String saveFormat = "JSON";
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

      String menuItemClicked = event.getActionCommand();
      
      // create the controller which will open the correct form (refer to the controller constructor
      // methods do determine which data container classes need to be passed to the controller constructors)
      
      if (menuItemClicked.equals("Add Stock Quote")) {
          // Create an input form controller object for the investment company and pass the correct containers to the constructor 
		InputStockQuoteFormController inputController = new InputStockQuoteFormController(stockQuoteDataContainer);
      } else if (menuItemClicked.equals("List Available Stocks")) {
		ListAllStockQuotesController reportController = new ListAllStockQuotesController(stockQuoteDataContainer);
      }
      
      if (menuItemClicked.equals("Add Investment Company")) {
         // Create an input form controller object for the investment company and pass the correct containers to the constructor   
    	  InputInvestmentCompanyFormController inputController = new InputInvestmentCompanyFormController(investmentCompanyDataContainer, brokerDataContainer);
      } else if (menuItemClicked.equals("List Investment Companies")) {
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
    	  ListAllInvestorsController reportController = new ListAllInvestorsController(investorDataContainer);}
      

		else if (menuItemClicked.equals("Exit")) {
		   System.exit(0);

      
      } else if (menuItemClicked.equals("Save Data")) {
         try {
        	 //System.out.println("Save format is " + saveFormat);
        	 switch(saveFormat) {
        	 case "JSON":
        		 BrokerIO.writeJSONFile(fileLocation, brokerDataContainer);
            	 InvestmentCompanyIO.writeJSONFile(fileLocation, investmentCompanyDataContainer);
            	 InvestorIO.writeJSONFile(fileLocation, investorDataContainer);
            	 StockQuoteIO.writeJSONFile(fileLocation, stockQuoteDataContainer);
            	 break;
        	 case "XML":
        		 StockQuoteIO.writeXMLFile(fileLocation, stockQuoteDataContainer);
        		 //BrokerIO.writeXMLFile(fileLocation, brokerDataContainer);
            	 //InvestmentCompanyIO.writeXMLFile(fileLocation, investmentCompanyDataContainer);
            	 //InvestorIO.writeXMLFile(fileLocation, investorDataContainer);
            	 
        		 break;
        	default:
        		//TODO: Make this throw a popup error
        		System.out.println("Save error");
        	 }            
         } catch (MyFileException exp) {
        	 System.out.println("in  this place here");
            new FileIOErrorPopup(mainMenu, exp);
         }
      } else if (menuItemClicked.equals("Load Data")) {
         try {
        	// System.out.println("hereeo");
        	 switch(saveFormat) {
        	 case "JSON":
            	 investorDataContainer.setInvestorList(InvestorIO.readJSONFile(fileLocation));
            	 brokerDataContainer.setBrokerList(BrokerIO.readJSONFile(fileLocation));
            	 investmentCompanyDataContainer.setcompanyList(InvestmentCompanyIO.readJSONFile(fileLocation));
            	 stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readJSONFile(fileLocation));
        		 break;
        	 case "XML":
        		 System.out.println("here");
        		 investorDataContainer.setInvestorList(InvestorIO.readXMLFile(fileLocation).getInvestorList());
            	 brokerDataContainer.setBrokerList(BrokerIO.readXMLFile(fileLocation).getBrokerList());
            	 investmentCompanyDataContainer.setcompanyList(InvestmentCompanyIO.readXMLFile(fileLocation).getcompanyList());
            	 stockQuoteDataContainer.setStockQuoteList(StockQuoteIO.readXMLFile(fileLocation).getStockQuoteList());
        		 break;
        	 default:
         		//TODO: Make this throw a popup error
         		System.out.println("Save error");
        	 }

         } catch (MyFileException exp) {
        	 System.out.println("in  this catch");
            new FileIOErrorPopup(mainMenu, exp);
         }
      } else if(menuItemClicked.equals("XML")) {
    	  saveFormat = "XML";
    	  
      } else if(menuItemClicked.equals("Text")) {
    	  saveFormat = "Text";
    	  
      } else if(menuItemClicked.equals("JSON")) {
    	  saveFormat = "JSON";
      }
   }

   // Getter used in the Application.java class
   public MainMenu getMainMenu() {
      return mainMenu;
   }
}
