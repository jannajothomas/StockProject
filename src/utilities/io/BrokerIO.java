/*
 *  This Class contains methods to write out the Broker objects in several different formats
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
import java.util.Arrays;
import java.util.Calendar;
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
import datacontainers.InvestorDataContainer;
import datamodels.Broker;
import datamodels.Investor;
import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;
import utilities.date.DateFunctions;

public class BrokerIO implements Serializable {

   /**
    * Constructor is declared private because the IO classes are utilities which
    * contain static methods and should never be instantiated
    */
   private BrokerIO() {
   }

   /**
    * Writes out a text file containing all broker in the data container
    */
   public static void writeTextFile(String fileLocation, BrokerDataContainer datacontainer) throws MyFileException {

      PrintWriter textFile = null;

      try {
         // Create output file
         // We are putting it in a location specified when the program is run
         // This is done via a command line argument
         textFile = new PrintWriter(fileLocation + "/brokers.csv");

         // Loop through the array list and print delimited text to a file
         for (Broker broker : datacontainer.getBrokerList()) {
            String brokerString = broker.getName()
                    + "," + broker.getAddress()
                    + "," + DateFunctions.dateToString(broker.getDateOfBirth())
                    + "," + DateFunctions.dateToString(broker.getDateOfHire())
                    + "," + DateFunctions.dateToString(broker.getDateOfTermination())
                    + "," + broker.getStatus()
                    + "," + broker.getSalary()
                    + "," + broker.getId()
                    + ",";
            // Add in just the client ids, we will look up the complete client object when reading in data
            // Notice the delimiter for the client list is different from the delimiter for the broker metadata
            for (Investor client : broker.getListOfClients()) {
               brokerString = brokerString + client.getId() + ":";
            }
            textFile.println(brokerString);
         }
      } catch (FileNotFoundException e) {
         throw new MyFileException(e.getMessage());
      } finally {
         // Flush the output stream and close the file
         if (textFile != null) {
            textFile.flush();
            textFile.close();
         }
      }
   }

   /**
    * Creates a serialized object output file containing all broker in the data
 container
    */
   public static void writeSerializedFile(String fileLocation, BrokerDataContainer datacontainer) throws MyFileException {
      try {
         // Create output file
         ObjectOutputStream serializedFile = new ObjectOutputStream(
                 new FileOutputStream(fileLocation + "/brokers.ser"));
         // Write out the data
         serializedFile.writeObject(datacontainer.getBrokerList());
      } catch (IOException exp) {
         throw new MyFileException("Can't serialize file");
      }
   }

   /**
    * Creates an xml output file using the JAXB libraries
    */
   public static void writeXMLFile(String fileLocation, BrokerDataContainer brokersDataContainer) throws MyFileException {
      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(BrokerDataContainer.class);
         // Create the marshaller
         Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
         // Create nicely formatted xml
         jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
         //Marshal the broker list into an xml file
         jaxbMarshaller.marshal(brokersDataContainer, new File(fileLocation + "/brokers.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

	/**
	 * Writes out the Broker data in JSON format containing all Brokers in the stock
	 * quote data container
	 *
	 */
	public static void writeJSONFile(String fileLocation, BrokerDataContainer datacontainer) throws MyFileException {

		PrintWriter jsonFile = null;
		try {
			// Create output file
			jsonFile = new PrintWriter(fileLocation + "/brokers.json");

			// Create JSON object
			Gson gson = new GsonBuilder().create();

			// Convert stock quote list to JSON format
			gson.toJson(datacontainer.getBrokerList(), jsonFile);

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
    * Reads a serialized list of broker
    */
   public static ArrayList<Broker> readSerializedFile(String fileLocation) throws MyFileException {

      try {
         ObjectInputStream ois = new ObjectInputStream(
                 new FileInputStream(fileLocation + "/brokers.ser"));
         ArrayList<Broker> listOfBrokers = (ArrayList<Broker>) ois.readObject();
         return listOfBrokers;
      } catch (IOException | ClassNotFoundException exp) {
         throw new MyFileException("Can't deserialize file");
      }
   }

   /**
    * Read a delimited text file. You need to know the format of the text file
    * in order to parse it correctly.
    */
   public static ArrayList<Broker> readTextFile(String fileLocation, InvestorDataContainer investorDataContainer ) 
           throws MyFileException {

      ArrayList<Broker> listOfBrokers = new ArrayList<>();

      try {

         // Keep track of when we reach the end of the file
         boolean eof = false;

         // Set the input file
         BufferedReader bw = new BufferedReader(new FileReader(fileLocation + "/brokers.csv"));

         // While there are still rows in the input file, read one in
         while (!eof) {

            Broker broker = new Broker();

            // Read in a line from the file 
            String lineFromFile = bw.readLine();
            // Keep going until we reach the end of the file
            if (lineFromFile == null) {
               eof = true;
            } else {
               // Split the input string into elements
               String[] lineElements = lineFromFile.split(",");

               // Set the simple ones    
               broker.setName(lineElements[0]);
               broker.setAddress(lineElements[1]);
               broker.setStatus(lineElements[5]);
               broker.setSalary(Double.parseDouble(lineElements[6]));
               broker.setId(Long.parseLong(lineElements[7]));

               // Convert the string dates to Calendar objects
               Calendar birthDate = DateFunctions.stringToDate(lineElements[2]);
               broker.setDateOfBirth(birthDate);
               Calendar hireDate = DateFunctions.stringToDate(lineElements[3]);
               broker.setDateOfHire(hireDate);
               Calendar terminationDate = DateFunctions.stringToDate(lineElements[4]);
               broker.setDateOfTermination(terminationDate);

               // Summary of what's happening here:
               // --------------------------------
               // The last item in the comma separated list is the list of clients
               // Split the item into clients, we only have the investor ids so we need to look up
               // the complete investor object in the investor data container
               
               // Retrieve the client list string from the line read in
               String clientElement = lineElements[8];
               // Split the list into client ids
               String[] clientList = clientElement.split(":");
               // Retrieve the list of investors from teh investor data container
               List<Investor> listOfAllInvestors = investorDataContainer.getInvestorList();
               // Using a traditional loop here to loop through the client id list and match to
               // an investor from the investor data container
               for (int i=0; i<clientList.length; i++) {
                  for (Investor investor : listOfAllInvestors) {
                     if (investor.getId() == Integer.parseInt(clientList[i])) {
                        // if it matches, store a link in the broker data
                        broker.getListOfClients().add(investor);
                     }
                  }
               }

               // add broker to the data container 
               listOfBrokers.add(broker);
            }
         }
         return listOfBrokers;
      } catch (IOException | NumberFormatException | InvalidDataException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

   /**
    * Reads a set of broker objects from an XML file
    */
   public static BrokerDataContainer readXMLFile(String fileLocation) throws MyFileException {

      try {
         // Create the format of the xml
         JAXBContext jaxbContext = JAXBContext.newInstance(BrokerDataContainer.class);
         // Create the unmarshaller
         Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
         //Unmarshal the file and convert to a broker data container object 
         return (BrokerDataContainer) jaxbUnmarshaller.unmarshal(new File(fileLocation + "/brokers.xml"));
      } catch (JAXBException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }

	/**
	 * Reads a JSON formatted file of stock quotes and returns an array list of
	 * Brokers.
	 *
	 */
	public static ArrayList<Broker> readJSONFile(String fileLocation) throws MyFileException {

		ArrayList<Broker> listOfBrokers = new ArrayList<>();

		try {
			// Create input file
			BufferedReader jsonFile = new BufferedReader(new FileReader(fileLocation + "/brokers.json"));

			// Create JSON object
			Gson gson = new GsonBuilder().create();

         // fromJson returns an array
         Broker[] brokersArray = gson.fromJson(jsonFile, Broker[].class);

         // Convert to arraylist for the data model
         listOfBrokers.addAll(Arrays.asList(brokersArray));
         return listOfBrokers;
      } catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
         throw new MyFileException(exp.getMessage());
      }
   }
}