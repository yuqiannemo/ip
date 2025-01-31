package nemo;

/**
 * Represents an exception specific to the Nemo application.
 * This exception is thrown when an error occurs during the execution of the application.
 */
public class NemoException extends Exception {
    /**
     * Constructs a new NemoException with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public NemoException(String message) {
        super(message);
    }

    /**
     * Returns the error message associated with this exception.
     *
     * @return The error message.
     */
    @Override
    public String toString() {
        return getMessage();
    }
}
