package exceptionhandlers;

public class MyFileException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyFileException() {
        super();
    }
    
    public MyFileException(String errorMessage) {
        super(errorMessage);
    }
}
