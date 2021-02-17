package exceptionhandlers;

/**
 * An instance of this class is created whenever an exception is thrown in the 
 * application.  Like all things in Java, errors are also objects so throwing
 * an error in the application, creates an error object!  Notice that this class
 * extends a built in Java class called "Exception".  If you look at the exception
 * class hierarchy, you will see that the Exception class extends the 
 * "Throwable" class.  Starting to seem familiar??
 */
public class InvalidDataException extends Exception {

    public InvalidDataException() {
        super();
    }
    
    public InvalidDataException(String errorMessage) {
        super("Invalid data : " + errorMessage);
    }
}