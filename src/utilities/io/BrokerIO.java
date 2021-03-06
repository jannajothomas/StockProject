/*
 *  This Class contains methods to write out the Broker objects in several different formats
 *  and read in the data in the same formats.
 */
package utilities.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import datacontainers.BrokerDataContainer;
import java.io.PrintWriter;
import datamodels.Broker;
import exceptionhandlers.MyFileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class BrokerIO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor is declared private because the IO classes are utilities which
	 * contain static methods and should never be instantiated
	 */
	private BrokerIO() {
	}

	/**
	 * Creates an xml output file containing all Brokers in the Broker data
	 * container using the JAXB libraries
	 */
	public static void writeXMLFile(String fileLocation, BrokerDataContainer BrokerDataContainer)
			throws MyFileException {
		try {
			// Create the format of the xml
			JAXBContext jaxbContext = JAXBContext.newInstance(BrokerDataContainer.class);
			// Create the marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			// Create nicely formatted xml
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// Marshal the Brokers list into an xml file
			jaxbMarshaller.marshal(BrokerDataContainer, new File(fileLocation + "/brokers.xml"));
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
	 * Read in an XML file of Broker objects
	 * 
	 * @param fileLocation
	 * @return
	 */
	public static BrokerDataContainer readXMLFile(String fileLocation) throws MyFileException {
		try {
			// Create the format of the xml
			JAXBContext jaxbContext = JAXBContext.newInstance(BrokerDataContainer.class);
			// Create the unmarshaller
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			// Unmarshal the file
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
			Broker[] BrokerArray = gson.fromJson(jsonFile, Broker[].class);

			// Convert to arraylist for the data model
			listOfBrokers.addAll(Arrays.asList(BrokerArray));
			return listOfBrokers;
		} catch (JsonIOException | JsonSyntaxException | FileNotFoundException exp) {
			throw new MyFileException(exp.getMessage());
		}
	}

}