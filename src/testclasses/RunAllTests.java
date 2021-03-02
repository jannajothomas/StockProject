package testclasses;

import exceptionhandlers.InvalidDataException;
import exceptionhandlers.MyFileException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class RunAllTests {

	public static void main(String[] args) throws InvalidDataException, FileNotFoundException, MyFileException {
		{
			
			    System.setOut(new PrintStream(new File("testProgramOutput.txt")));	
			
				TestAllClassesClassCreationAssign2.main(args);
			
				TestAllClassesClassCreationAssign2.main(args);
		
				TestErrorHandlingAllClasses.main(args);
				
				//TestErrorHandlingAllClassesJT.main(args);
			
				TestStockQuoteDataContainer.main(args);
			
		}
	}
}
