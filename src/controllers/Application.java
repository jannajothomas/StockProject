package controllers;

/*
 * This is the main entry into the application. It creates a menu controller object
 * and the controller object creates the forms and the data models as needed
 */

public class Application {

   /**
    * main entry into the application 
    * args[0] - location of persisted data 
    */
   public static void main(String[] args) {

      // Create main menu controller, the controller creates the menu form
      MainMenuController controller = new MainMenuController(args[0]);

      // Retrieve the main menu form from the controller and make it visible
      controller.getMainMenu().setVisible(true);

   }

  
}
