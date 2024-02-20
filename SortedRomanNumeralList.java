import java.util.ArrayList;
import java.util.TreeSet;

/**
 * A sorted list of Roman numerals.
 */
public class SortedRomanNumeralList extends ArrayList<RomanNumeral> {

	/**
	 * Adds a Roman numeral to the list in a sorted manner.
	 *
	 * @param x the Roman numeral to be added
	 * @return {@code true} if the Roman numeral is successfully added
	 */
	@Override
	public boolean add(RomanNumeral x) {
		// Insert the Roman numeral into the TreeSet
		TreeSet<RomanNumeral> sortedSet = new TreeSet<>(this);
		sortedSet.add(x);

		// Clear the current list
		this.clear();

		// Add the Roman numerals back to the list in sorted order
		this.addAll(sortedSet);

		return true;
	}

	/**
	 * Returns a string representation of the sorted list of Roman numerals.
	 *
	 * @return a string representation of the sorted list of Roman numerals
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (RomanNumeral numeral : this) {
			sb.append(numeral.getArabic()).append("\n");
		}
		return sb.toString();
	}
}
