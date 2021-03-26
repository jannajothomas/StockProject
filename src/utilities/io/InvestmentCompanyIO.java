/*
 *  This Class contains methods to write out the InvestmentCompany objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import datacontainers.BrokerDataContainer;
import datacontainers.InvestmentCompanyDataContainer;
import datamodels.Broker;
import datamodels.InvestmentCompany;
import exceptionhandlers.MyFileException;

public class InvestmentCompanyIO implements Serializable {

	private static final long serialVersionUID = 1L;

	private InvestmentCompanyIO() {
	}

   /**
    * Writes out a text file containing all companies in the companies data
    * container
    *
    */
   public static void writeTextFile(String fileLocation, InvestmentCompanyDataContainer datacontainer) throws MyFileException {

      PrintWriter textFile = null;

      try {
         // Create output file
         // We are putting it in a location specified when the program is run
         // This is done via a command line argument
         textFile = new PrintWriter(fileLocation + "/investmentcompanies.csv");

         // Loop through the array list and print delimited text to a file
         for (InvestmentCompany company : datacontainer.getInvestmentCompanyList()) {
            
            // Store the data as a string
            String companyInfoString = "";

            // Add the company name
            companyInfoString += company.getCompanyName() + ",";

            // Add in just the broker ids, we will look up the complete broker object when reading in data
            // Notice the delimiter for the broker list is different from the delimiter for the company metadata
            for (Broker broker : company.getListOfBrokers()) {
               companyInfoString = companyInfoString + broker.getId() + ":";
            }
            textFile.println(companyInfoString);
         }
      } catch (FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      } finally {
         // Flush the output stream and close the file
         if (textFile != null) {
            textFile.flush();
            textFile.close();
         }
      }
   }

   /**
    * Creates a serialized object output file containing all investment company
    * in the investment company data container
    */
   public static void writeSerializedFile(String fileLocation, InvestmentCompanyDataContainer datacontainer) throws MyFileException {
      try {
         // Create output file
         ObjectOutputStream serializedFile = new ObjectOutputStream(
                 new FileOutputStream(fileLocation + "/investmentcompanies.ser"));
         // Write out the data
         serializedFile.writeObject(datacontainer.getInvestmentCompanyList());
      } catch (IOException exp) {
         throw new MyFileException("Can't serialize file");
      }
   }

   /**
    * Creates an xml output file using the JAXB libraries
    */
   public static void writeXMLFile(String fileLocation, InvestmentCompanyDataContainer investmentCompanyDataContainer) throws MyFileException {
      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
         // Create the marshaller
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         // Create nicely formatted xml
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         //Marshal the company list into an xml file
         jaxbMarshaller.marshal(investmentCompanyDataContainer, new File(fileLocation + "/investmentcompanies.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   /**
    * Writes out the investment companies data in JSON format containing all
    * investment companies in the investment companies data container
    *
    */
   public static void writeJSONFile(String fileLocation, InvestmentCompanyDataContainer datacontainer) throws MyFileException {

      PrintWriter jsonFile = null;

      try {
         // Create output file
         jsonFile = new PrintWriter(fileLocation + "/investmentcompanies.json");

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // Convert investment company list to JSON format
         gson.toJson(datacontainer.getInvestmentCompanyList(), jsonFile);

      } catch (JsonIOException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      } finally {
         // Flush the output stream and close the file
         if (jsonFile != null) {
            jsonFile.flush();
            jsonFile.close();
         }
      }
   }

   /**
    * Reads a JSON formatted file of investment companies
    *
    */
   public static ArrayList<InvestmentCompany> readJSONFile(String fileLocation) throws MyFileException {

      ArrayList<InvestmentCompany> listOfInvestmentCompanys = new ArrayList<>();

      try {
         // Create input file
         BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/investmentcompanies.json"));

         // Create JSON object
         Gson gson = new GsonBuilder().create();

         // fromJson returns an array
         InvestmentCompany[] investmentcompaniesArray = gson.fromJson(jsonFile, InvestmentCompany[].class);

         // Convert to arraylist for the data container
         for (int i = 0; i < investmentcompaniesArray.length; i++) {
            listOfInvestmentCompanys.add(investmentcompaniesArray[i]);
         }
         return listOfInvestmentCompanys;
      } catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      }

   }

   /**
    * Reads a set of investment company objects from a serialized file
    */
   public static ArrayList<InvestmentCompany> readSerializedFile(String fileLocation) throws MyFileException {

      ArrayList<InvestmentCompany> listOfInvestmentCompanys = new ArrayList<>();

      try {
         ObjectInputStream serializedFile = new ObjectInputStream(
                 new FileInputStream(fileLocation + "/investmentcompanies.ser"));
         // Read the serialized object and cast to its original type
         listOfInvestmentCompanys = (ArrayList<InvestmentCompany>) serializedFile.readObject();
         return listOfInvestmentCompanys;
      } catch (IOException | ClassNotFoundException exp) {
         throw new MyFileException("Can't deserialize file");
      }
   }

   /**
    * Reads a set of investment companyobjects from an XML file
    */
   public static InvestmentCompanyDataContainer readXMLFile(String fileLocation) throws MyFileException {

      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(InvestmentCompanyDataContainer.class);
         // Create the unmarshaller
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         //Unmarshal the file and convert to a InvestmentCompanyDataContainer object 
         return (InvestmentCompanyDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "/investmentcompanies.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }

   }

   /**
    * Reads a delimited text file of investment companies
    *
    * An end of file flag is used to keep track of whether we hit the end of the
    * file, It starts out false and if we hit the end of file (null input), it
    * changes to true and execution stops.
    *
    */
   public static ArrayList<InvestmentCompany> readTextFile(String fileLocation, BrokerDataContainer brokerDataContainer) throws MyFileException {

      ArrayList<InvestmentCompany> listOfInvestmentCompanies = new ArrayList<>();

      try {
         boolean eof = false;
         BufferedReader textFile = new BufferedReader(new FileReader(fileLocation + "/investmentcompanies.csv"));
         while (!eof) {
            String lineFromFile = textFile.readLine();
            if (lineFromFile == null) {
               eof = true;
            } else {
               // Create an InvestmentCompany object
               InvestmentCompany company = new InvestmentCompany();

               // Split the input line into elements using the delimiter
               String[] lineElements = lineFromFile.split(",");

               // The first element is the investment company name
               company.setCompanyName(lineElements[0]);

               // The second element is the investment company list of brokers
               // Restore the list of brokers, requires pre-loading of broker data container
               String brokerlist[] = lineElements[1].split(":");
               // Retrieve the list of brokers from teh brokers data container
               List<Broker> listOfAllBrokers = brokerDataContainer.getBrokerList();
               // Using a traditional loop here to loop through the broker id list and match to
               // an broker from the broker data container
               for (int i=0; i<brokerlist.length; i++) {
                  for (Broker broker : listOfAllBrokers) {
                     if (broker.getId() == Integer.parseInt(brokerlist[i])) {
                        // if it matches, store a link in the company data
                        company.getListOfBrokers().add(broker);
                     }
                  }
               }

               // add the object to the arraylist
               listOfInvestmentCompanies.add(company);
            }
         }
         return listOfInvestmentCompanies;
      } catch (MyFileException | IOException exp) {
         throw new MyFileException(exp.getMessage());
      }

   }

}
