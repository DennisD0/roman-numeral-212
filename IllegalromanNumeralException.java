/**
 * This class represents an exception that is thrown when an invalid Roman
 * numeral is encountered. It extends the IllegalArgumentException class.
 */
public class IllegalRomanNumeralException extends IllegalArgumentException {

	/**
	 * Constructs a new IllegalRomanNumeralException object with the specified
	 * message.
	 * 
	 * @param message the message to be associated with this exception
	 */
	public IllegalRomanNumeralException(String message) {
		super(message);
	}
}
