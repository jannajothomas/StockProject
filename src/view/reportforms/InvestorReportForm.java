package view.reportforms;

import controllers.reportformcontrollers.ListAllInvestorsController;
import datamodels.Investor;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import utilities.date.DateFunctions;

/**
 * This class contains all the code for the investor report form. It creates the
 * form and populates the form with the data from the investor data model
 */
public class InvestorReportForm extends JFrame {

    // Form components
	 private JLabel label1 = new JLabel("List Of Investors At A Glance");
	   // Title of form
	   private JLabel label2 = new JLabel("Investor Detail Data");
    private JButton closeButton = new JButton("Close Report");
   // private JScrollPane treeScrollPane;
    private JTree investorTree;
    private DefaultMutableTreeNode topLevelNode;
    //private JTextArea investorData;
    private JTextArea textAreaOfInvestors = new JTextArea(20, 5);
    private JTable tableOfInvestors = new javax.swing.JTable();

    /**
     * Free form text area
     */

    
    /**
     * scrollable panels for the table and free form text area
     */
   private JScrollPane textAreaScrollPanel = new JScrollPane();
   private JScrollPane tableScrollPanel = new JScrollPane();
    public InvestorReportForm() {

        /**
         * set close function to dispose of the form
         */
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        /**
         * set the form layout to null, we'll place the components manually using
         * x,y layout positions
         */
        getContentPane().setLayout(null);

        /**
         * Add label component to the form
         */
        getContentPane().add(label1);
        label1.setBounds(300, 20, 210, 14);

        /**
         * Put the table in the scrollable panel
         */
        textAreaScrollPanel.setViewportView(textAreaOfInvestors);

        /**
         * Add component to the form
         */
        getContentPane().add(textAreaScrollPanel);
        textAreaScrollPanel.setBounds(10, 45, 720, 124);

        /**
         * Add label component to the form
         */
        getContentPane().add(label2);
        label2.setBounds(380, 190, 270, 14);

        /**
         * Add component to the form
         */
        getContentPane().add(closeButton);
        closeButton.setBounds(500, 380, 120, 23);

        tableOfInvestors.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                   {null, null, null, null, null, null, null, null},
                   {null, null, null, null, null, null, null, null},
                   {null, null, null, null, null, null, null, null},
                   {null, null, null, null, null, null, null, null},
                   {null, null, null, null, null, null, null, null},
                   {null, null, null, null, null, null, null, null},
                   {null, null, null, null, null, null, null, null},},
                new String[]{
                   "Name", "Address", "Status", "Salary", "ID", "Date of Birth", "Date of Hire", "Date of Termination"
                }
        ) {
           Class[] types = new Class[]{
              java.lang.String.class, java.lang.String.class, java.lang.String.class, 
              java.lang.Double.class, java.lang.Double.class, java.lang.String.class, 
              java.lang.String.class, java.lang.String.class
           };
           boolean[] canEdit = new boolean[]{
              false, false, false, false, false, false, false, false
           };

           public Class getColumnClass(int columnIndex) {
              return types[columnIndex];
           }

           public boolean isCellEditable(int rowIndex, int columnIndex) {
              return canEdit[columnIndex];
           }
        });
        tableOfInvestors.setColumnSelectionAllowed(true);
        // Put the table in a scrollablepanel
        tableScrollPanel.setViewportView(tableOfInvestors);

        // Add the panel to the form
        getContentPane().add(tableScrollPanel);
        tableScrollPanel.setBounds(10, 220, 1200, 138);

        this.setTitle("Investor Report");
        
        /**
         * set the overall size of the form
         */
        setSize(1250, 500);

    }

    private void populateTree(List<Investor> listOfInvestors) {

        // Add all investors to the tree
        for (Investor investor : listOfInvestors) {

            // Top level node for each investor is the name field
            DefaultMutableTreeNode oneIinvestor = new DefaultMutableTreeNode(investor.getName());

            // All the other meta data fields are child nodes of the name field
            DefaultMutableTreeNode address = new DefaultMutableTreeNode("Address:"+investor.getAddress());
            DefaultMutableTreeNode id = new DefaultMutableTreeNode("Id:"+investor.getId());
            DefaultMutableTreeNode dateOfBirth = 
                    new DefaultMutableTreeNode("Date of Birth:"+DateFunctions.dateToString(investor.getDateOfBirth()));
            DefaultMutableTreeNode memberSince = 
                    new DefaultMutableTreeNode("Member since:"+DateFunctions.dateToString(investor.getMemberSince()));
            DefaultMutableTreeNode accountValue = new DefaultMutableTreeNode("Account value:"+investor.getAccountValue());

            oneIinvestor.add(address);
            oneIinvestor.add(id);
            oneIinvestor.add(dateOfBirth);
            oneIinvestor.add(memberSince);
            oneIinvestor.add(accountValue);

            // Add the investor node to the tree
            topLevelNode.add(oneIinvestor);
        }
    }

    // Getters to access the controls on the form
    public JButton getCloseButton() {
        return closeButton;
    }

    public JTree getInvestorTree() {
        return investorTree;
    }

    public DefaultMutableTreeNode getTopLevelNode() {
        return topLevelNode;
    }
    
    public JTable getTableOfInvestors() {
    	return tableOfInvestors;
    }
    
    public JTextArea getTextAreaofInvestors() {
    	return textAreaOfInvestors;
    }

}
