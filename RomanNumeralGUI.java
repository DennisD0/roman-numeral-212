import javax.swing.*;
import java.util.Scanner;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.util.Comparator;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * The RomanNumeralGUI class extends JFrame and provides a GUI for displaying
 * Roman numerals.
 */
public class RomanNumeralGUI extends JFrame {

	/**
	 * Default constructor for RomanNumeralGUI.
	 */
	public RomanNumeralGUI() {
	}

	/**
	 * Constructor for RomanNumeralGUI that takes Roman numerals, unsorted list,
	 * sorted list, and the GUI as parameters.
	 *
	 * @param RomanNumerals     A String representing the Roman numerals to display.
	 * @param unsorted          An UnsortedRomanNumeralList object representing the
	 *                          unsorted list of Roman numerals.
	 * @param sorted            A SortedRomanNumeralList object representing the
	 *                          sorted list of Roman numerals.
	 * @param myRomanNumeralGUI A RomanNumeralGUI object representing the GUI.
	 */
	public RomanNumeralGUI(String RomanNumerals, SortedRomanNumeralList sorted, RomanNumeralGUI myRomanNumeralGUI) {
		initialize(RomanNumerals, sorted, myRomanNumeralGUI);
	}

	/**
	 * This method reads a file and returns an array containing the original text
	 * and a list of Roman numerals.
	 *
	 * @param file              A File object representing the file to read.
	 * @param myRomanNumeralGUI A RomanNumeralGUI object representing the GUI.
	 * @return An array of Strings containing the original text and a list of Roman
	 *         numerals.
	 * @throws FileNotFoundException If the file cannot be found.
	 */
	public static String[] fillArray(File file, RomanNumeralGUI myRomanNumeralGUI) throws FileNotFoundException {
		// Create objects of RomanNumeralList class
		SortedRomanNumeralList sorted = new SortedRomanNumeralList();

		// Create variables for text, word, and output
		StringBuilder textBuilder = new StringBuilder(); // The entire text read from the file
		String word = ""; // A single word extracted from the file
		String[] output = new String[2]; // The output array that will be returned by this method

		// Read the text file
		Scanner x = new Scanner(file);
		String line = x.nextLine();
		while (x.hasNextLine()) {
			// Tokenize the line using commas as delimiters
			StringTokenizer s = new StringTokenizer(line, ",");
			while (s.hasMoreTokens()) {
				// Process each token (i.e., Roman numeral)
				word = s.nextToken();
				RomanNumeral r = new RomanNumeral(word); // Create a RomanNumeral object from the word
				textBuilder.append(word).append("\n"); // Add the word to the text (with a newline character)
				r.setRoman(word); // Set the Roman numeral value of the RomanNumeral object
				sorted.add(r); // Add the RomanNumeral object to a sorted list
			}
			line = x.nextLine();
		}
		x.close();

		// Sort the original Roman numerals in Roman numeral order using TreeMap and a
		// custom comparator
		TreeMap<String, Integer> sortedRomanNumerals = new TreeMap<>(new RomanNumeralComparator());
		StringTokenizer st = new StringTokenizer(textBuilder.toString(), "\n");
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			sortedRomanNumerals.put(token, sortedRomanNumerals.size());
		}

		// Initialize the GUI with the extracted Roman numerals and the sorted text
		String sortedRomanNumeralList = sortedRomanNumerals.keySet().stream().collect(Collectors.joining("\n"));

		initialize(sortedRomanNumeralList, sorted, myRomanNumeralGUI);

		// Store the sorted Roman numerals and the Roman numeral list in the output
		output[0] = sortedRomanNumeralList;

		return output;
	}

	/**
	 * Custom comparator for sorting Roman numerals in Roman numeral order.
	 */
	private static class RomanNumeralComparator implements Comparator<String> {
		/**
		 * Compares two Roman numerals.
		 *
		 * @param roman1 the first Roman numeral
		 * @param roman2 the second Roman numeral
		 * @return the result of the comparison (-1 if roman1 < roman2, 0 if roman1 ==
		 *         roman2, 1 if roman1 > roman2)
		 */
		@Override
		public int compare(String roman1, String roman2) {
			RomanNumeral r1 = new RomanNumeral(roman1);
			RomanNumeral r2 = new RomanNumeral(roman2);
			return r1.compareTo(r2);
		}
	}

	/**
	 * Creates and displays the GUI for the Roman numeral program.
	 */
	public static void createGUI() {
		RomanNumeralGUI myRomanNumeralGUI = new RomanNumeralGUI();

		// Set JFrame properties
		myRomanNumeralGUI.setSize(600, 400);
		myRomanNumeralGUI.setTitle("Roman Numeral");
		myRomanNumeralGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myRomanNumeralGUI.createFileMenu(myRomanNumeralGUI);
		myRomanNumeralGUI.setVisible(true);
	}

	/**
	 * Initializes the GUI with Roman numerals and sorted list.
	 *
	 * @param RomanNumerals     the original Roman numerals
	 * @param sorted2           the sorted Roman numerals list
	 * @param myRomanNumeralGUI the RomanNumeralGUI object
	 */
	public static void initialize(String RomanNumerals, SortedRomanNumeralList sorted2,
			RomanNumeralGUI myRomanNumeralGUI) {
		// Create new JFrame

		// Set JFrame properties
		myRomanNumeralGUI.setSize(600, 400);
		myRomanNumeralGUI.setTitle("Roman Numeral");
		myRomanNumeralGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myRomanNumeralGUI.createFileMenu(myRomanNumeralGUI);
		myRomanNumeralGUI.setLayout(new GridLayout(1, 2)); // We want 1 row with 3 columns as a Grid Layout
		myRomanNumeralGUI.setLocationRelativeTo(null);

		// Add Text content pane to be appended with the original text file later
		JTextArea Text = new JTextArea();
		myRomanNumeralGUI.getContentPane().add(Text);
		Text.setEditable(false);
		Text.setText("Original RomanNumerals: " + "\n");

		// Add Sorted content pane to be appended with the Sorted count later
		JTextArea Sorted = new JTextArea();
		myRomanNumeralGUI.getContentPane().add(Sorted);
		Sorted.setEditable(false);
		Sorted.setText("SortedRomanNumerals: " + "\n");

		// Add scrolling for Text TextArea
		JScrollPane content = new JScrollPane(Text);
		myRomanNumeralGUI.getContentPane().add(content);

		// Add scrolling for Unsorted TextArea

		// Add scrolling for Sorted TextArea
		JScrollPane content2 = new JScrollPane(Sorted);
		myRomanNumeralGUI.getContentPane().add(content2);

		// This is why our original text will be added in the JFrame on the left column
		Text.append(RomanNumerals);

		// This is why our unsorted count will be added in the JFrame on the right
		// column

		// This is why our sorted count will be added in the JFrame on the right column
		Sorted.append(sorted2 + "");
	}

	/**
	 * This method creates the File menu for the RomanNumeralGUI and adds the
	 * Convert sub-menu to it.
	 *
	 * @param myRomanNumeralGUI an instance of the RomanNumeralGUI class
	 */
	private void createFileMenu(RomanNumeralGUI myRomanNumeralGUI) {
		JMenuItem item; // Menu item variable

		// Create the menu bar, file menu, and convert sub-menu
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu fileConvert = new JMenu("Convert");

		// Create the file menu handler
		FileMenuHandler fmh = new FileMenuHandler(this, myRomanNumeralGUI);

		// Add Open menu item to the File menu and set its ActionListener
		item = new JMenuItem("Open");
		item.addActionListener(fmh);
		fileMenu.add(item);

		fileMenu.addSeparator(); // Add a horizontal separator line

		// Add Quit menu item to the File menu and set its ActionListener
		item = new JMenuItem("Quit");
		item.addActionListener(fmh);
		fileMenu.add(item);

		// Add Roman to Arabic menu item to the Convert sub-menu and set its
		// ActionListener
		item = new JMenuItem("Roman to Arabic");
		item.addActionListener(fmh);
		fileConvert.add(item);

		// Set the menu bar and add the File menu and Convert sub-menu to it
		setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		menuBar.add(fileConvert);
	}

}
