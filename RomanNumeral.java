import java.util.*;

/**
 * This class represents a Roman numeral and provides methods for conversion and
 * comparison.
 */
public class RomanNumeral implements Comparable<RomanNumeral> {
	private String Romanvalue; // The Roman numeral value
	private int Arabicvalue; // The corresponding Arabic numeral value
	private static final Map<Character, Integer> romanToArabicMap = new HashMap<>(); // Mapping of Roman to Arabic
																						// numerals

	static {
		// Initialize the Roman to Arabic numeral mapping in the static initializer
		romanToArabicMap.put('I', 1);
		romanToArabicMap.put('V', 5);
		romanToArabicMap.put('X', 10);
		romanToArabicMap.put('L', 50);
		romanToArabicMap.put('C', 100);
		romanToArabicMap.put('D', 500);
		romanToArabicMap.put('M', 1000);
	}

	/**
	 * Default constructor.
	 */
	public RomanNumeral() {
	}

	/**
	 * Constructs a RomanNumeral object with the given Roman numeral.
	 *
	 * @param r The Roman numeral value.
	 */
	public RomanNumeral(String r) {
		Romanvalue = r;
		Arabicvalue = Integer.parseInt(converter(r));
	}

	/**
	 * Returns the Roman numeral value.
	 *
	 * @return The Roman numeral value.
	 */
	public String getRoman() {
		return Romanvalue;
	}

	/**
	 * Sets the Roman numeral value and updates the corresponding Arabic numeral
	 * value.
	 *
	 * @param r The Roman numeral value to set.
	 */
	public void setRoman(String r) {
		Romanvalue = r;
		Arabicvalue = Integer.parseInt(converter(r));
	}

	/**
	 * Returns the corresponding Arabic numeral value.
	 *
	 * @return The Arabic numeral value.
	 */
	public int getArabic() {
		return Arabicvalue;
	}

	/**
	 * Compares this RomanNumeral object with another RomanNumeral object.
	 *
	 * @param other The other RomanNumeral object to compare.
	 * @return An integer value indicating the comparison result.
	 */
	public int compareTo(RomanNumeral other) {
		return Integer.compare(this.Arabicvalue, other.Arabicvalue);
	}

	/**
	 * Checks if this RomanNumeral object is equal to another object.
	 *
	 * @param other The other object to compare.
	 * @return True if the objects are equal, false otherwise.
	 */
	public boolean equals(Object other) {
		if (other == null || !(other instanceof RomanNumeral)) {
			return false;
		}

		RomanNumeral otherNumeral = (RomanNumeral) other;
		return this.Romanvalue.equals(otherNumeral.Romanvalue) && this.Arabicvalue == otherNumeral.Arabicvalue;
	}

	/**
	 * Converts a Roman numeral to its corresponding Arabic numeral.
	 *
	 * @param word The Roman numeral to convert.
	 * @return The converted Arabic numeral as a string.
	 * @throws IllegalRomanNumeralException if the input Roman numeral is invalid.
	 */
	public static String converter(String word) throws IllegalRomanNumeralException {
		int k = 0; // Previous numeral value
		int sum = 0; // Accumulated sum of Arabic numerals
		char c = ' '; // Current character being processed

		// Iterate through each character of the Roman numeral
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			Integer currentNumeralValue = romanToArabicMap.get(currentChar);

			// Check if the current character is a valid Roman numeral
			if (currentNumeralValue == null) {
				throw new IllegalRomanNumeralException(word + " is an illegal Roman numeral value.");
			}

			// Calculate the Arabic numeral value based on the current and previous values
			if (k < currentNumeralValue && k != 0) {
				sum = sum + currentNumeralValue - k * 2;
			} else {
				sum += currentNumeralValue;
			}

			k = currentNumeralValue; // Update the previous numeral value
		}

		return String.valueOf(sum); // Return the converted Arabic numeral as a string
	}
}
