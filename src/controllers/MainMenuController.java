/*
 * Listens for events on the menu form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 */
package controllers;

import controllers.inputformcontrollers.InputStockQuoteFormController;
import controllers.reportformcontrollers.ListAllStockQuotesController;
import java.awt.event.ActionListener;
import datacontainers.StockQuoteDataContainer;

// to-do - Add required imports
import view.MainMenu;

public class MainMenuController implements ActionListener {

   // The data models are declared and instantiated here and passed to the 
   // constructors for the controllers
   private StockQuoteDataContainer stockQuoteDataContainer = new StockQuoteDataContainer();

   // to-do - Add the rest of the data containers
   /**
    * Constructor
    */
   public MainMenuController() {
   }

   // The main menu form gets created here. Notice it takes this controller object
   // as an argument to the constructor
   private MainMenu mainMenu = new MainMenu(this);

   /**
    * actionPerformed method implemented from the ActionListener interface
    *
    * This method figures out which menu item was clicked based on the text of the
    * menu event.
    *
    * @param event
    */
   public void actionPerformed(java.awt.event.ActionEvent event) {

      //  Retrieve the menu item selected event
      String menuItemClicked = event.getActionCommand();

      // The following logic will figure out which controller to construct based on which menu
      // option was chosen
      // Stock quote is already done for you
      if (menuItemClicked.equals("Add Stock Quote")) {
         // Create an input form controller object for the stock quote and pass the correct containers to the constructor  
         InputStockQuoteFormController inputController = new InputStockQuoteFormController(stockQuoteDataContainer);
      } else if (menuItemClicked.equals("List Available Stocks")) {
         // Create a report controller object for the investment company and pass the correct containers to the constructor         
         ListAllStockQuotesController reportController = new ListAllStockQuotesController(stockQuoteDataContainer);
      }
      if (menuItemClicked.equals("Add Investment Company")) {
         // to-do create an input form controller object for the investment compnay and pass the correct containers to the constructor      
      } else if (menuItemClicked.equals("List Investment Companies")) {
         // to-do create a report controller object for the investment company and pass the correct containers to the constructor         
      }
      if (menuItemClicked.equals("Add Broker")) {
         // to-do create an input form controller object for the broker and pass the correct containers to the constructor
      } else if (menuItemClicked.equals("List Brokers")) {
         // to-do create a report controller object for the broker and pass the correct containers to the constructor     
      }
      if (menuItemClicked.equals("Add Investor")) {
         // to-do create an input form controller object for the investor and pass the correct containers to the constructor
      } else if (menuItemClicked.equals("List Investors")) {
         // to-do create a report controller object for the investor and pass the correct containers to the constructor  
      } else if (menuItemClicked.equals("Exit")) {
         System.exit(0);
      }
   }

   // Getter used in the Application.java class
   public MainMenu getMainMenu() {
      return mainMenu;
   }
}
