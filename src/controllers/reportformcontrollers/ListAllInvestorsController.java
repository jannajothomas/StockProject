/*
 * Listens for events on the report form. 
 * Implements the ActionListener interface which contains a single method, 
 * "actionPerformed"
 *
 * Populates the form with data if there is any in the application data model
 */
package controllers.reportformcontrollers;

import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

//import datacontainers.BrokerDataContainer;
import datacontainers.InvestorDataContainer;
//import datamodels.Broker;
import datamodels.Investor;
import utilities.date.DateFunctions;
//import view.reportforms.BrokerReportForm;
import view.reportforms.InvestorReportForm;

public class ListAllInvestorsController implements ActionListener{

   private InvestorReportForm form;
   private InvestorDataContainer investorDataContainer;

   // A table model will hold the data for the JTable (this is the M in MVC)
   private DefaultTableModel defaultTableModel = new DefaultTableModel();

   public ListAllInvestorsController(InvestorDataContainer investorDataContainer) {
	      // Create the form
	      form = new InvestorReportForm();

	      // Link the buttons to the actionPerformed method
	      this.form.getCloseButton().addActionListener(this);

	      // Call private method that will add all Broker to the text area
	      this.populateTextArea(investorDataContainer);

	      // Call private method that will add all Broker to the table
	      this.populateTable(investorDataContainer);

	      // make the form visible
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
   public void actionPerformed(java.awt.event.ActionEvent event) {

      //  Figure out which button was clicked
      String buttonClicked = event.getActionCommand();

      if (buttonClicked.equals("Close Report")) {
         form.dispose();
      }
   }
   
   private void populateTextArea(InvestorDataContainer investorDataContainer) {

	      // Initialize the string which will hold the data for the text area
	      // Start with labels
	      String listOfInvestorString = "";

	      // Loop through the list and add the broker names to the text area,
	      // Each time adding a cr/lf between items for readibility.
	   
	      for (Investor ainvestor : investorDataContainer.getInvestorList()) {
	         listOfInvestorString += ainvestor.getName() + "\n";
	      }
	      // Once all the data is in the string, set the text of the text area to the string value
	      this.form.getTextAreaofInvestors().setText(listOfInvestorString);

	   }


   /**
    * Implements the valueChanged method in the TreeSelectionListener interface.
    */
   /*
   public void valueChanged(TreeSelectionEvent e) {
      DefaultMutableTreeNode node = (DefaultMutableTreeNode) this.form.getInvestorTree().getLastSelectedPathComponent();

      if (node == null) {
         return;
      }

      Object nodeInfo = node.getUserObject();
      if (node.isLeaf()) {
         // Display investors info
      }
   }*/

   // Getters used by the controller
   public InvestorReportForm getForm() {
      return form;
   }

   public InvestorDataContainer getInvestorDataContainer() {
      return investorDataContainer;
   }
   
   private void populateTable(InvestorDataContainer investorDataContainer2) {

	      // A table model like this will hold the data for the JTable (this is the M in MVC)
	      DefaultTableModel tableModel = new DefaultTableModel();

	      // Link the data model to the table
	      this.form.getTableOfInvestors().setModel(tableModel);

	      // Add columns to table model
	      tableModel.addColumn("Name");
	      tableModel.addColumn("Address");
	      tableModel.addColumn("Date Of Birth");
	      tableModel.addColumn("ID");
	      tableModel.addColumn("Member Since");

	      // Add the Broker from the application data model to the table data model
	      for (Investor ainvestor : investorDataContainer2.getInvestorList()) {

	         // Create a vector that is one row in the table
	         Vector tableRow = new Vector();

	         // Add the data to the vector
	         tableRow.add(ainvestor.getName());
	         tableRow.add(ainvestor.getAddress());
	         //TODO: Add correct data rows
	         //tableRow.add(ainvestor.getStatus());
	         //tableRow.add(ainvestor.getSalary());
	         //tableRow.add(ainvesor.getId());
	         tableRow.add(DateFunctions.dateToString(ainvestor.getDateOfBirth()));
	         //tableRow.add(DateFunctions.dateToString(ainvestor.getDateOfHire()));
	         //tableRow.add(DateFunctions.dateToString(ainvestor.getDateOfTermination()));

	         // Format the broker's client data
	         String stockQuoteList = ainvestor.getListOfStocks().toString();

	         tableRow.add(stockQuoteList);

	         // Add the row of data to the  data model that is connected to the JTable
	         tableModel.addRow(tableRow);
	      }
	   }

}
