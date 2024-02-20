import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Handles actions for the File menu.
 */
public class FileMenuHandler implements ActionListener {

	JFrame jframe;
	RomanNumeralGUI old;

	/**
	 * Constructor for FileMenuHandler.
	 * 
	 * @param jf The JFrame of the main program.
	 * @param myRomanNumeralGUI The RomanNumeralGUI instance of the main program.
	 */
	public FileMenuHandler(JFrame jf, RomanNumeralGUI myRomanNumeralGUI) {
		jframe = jf;
		old = myRomanNumeralGUI;
	}

	/**
	 * Performs an action based on the selected menu item.
	 * 
	 * @param event The ActionEvent that was triggered.
	 */
	public void actionPerformed(ActionEvent event) {
		String menuName;
		menuName = event.getActionCommand();

		if (menuName.equals("Open")) {
			try {
				openFile(old);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else if (menuName.equals("Quit")) {
			System.exit(0);
		} else if (menuName.equals("Roman to Arabic")) {
			convertMenuHandler();
		}
	} // actionPerformed

	/**
	 * Opens a file and fills the RomanNumeralGUI with the contents of the file.
	 * 
	 * @param myRomanNumeralGUI The RomanNumeralGUI instance to fill.
	 * @throws FileNotFoundException If the file is not found.
	 */
	private void openFile(RomanNumeralGUI myRomanNumeralGUI) throws FileNotFoundException {
		JFileChooser chooser;
		int status;
		chooser = new JFileChooser();
		status = chooser.showOpenDialog(null);

		if (status == JFileChooser.APPROVE_OPTION) {
			RomanNumeralGUI.fillArray(chooser.getSelectedFile(), old);
		} else {
			JOptionPane.showMessageDialog(null, "Open File Import Canceled");
		}
	} // openFile

	/**
	 * Handles the conversion of a Roman numeral to an Arabic numeral.
	 */
	public void convertMenuHandler() {
		String romanNumeralInput = JOptionPane.showInputDialog(null, "Enter a Roman Numeral:");

		if (romanNumeralInput != null) {
			romanNumeralInput = romanNumeralInput.trim();

			try {
				String arabic = RomanNumeral.converter(romanNumeralInput);
				int arabicValue = Integer.parseInt(arabic);
				JOptionPane.showMessageDialog(null, "The converted Arabic value is " + arabicValue);

			} catch (IllegalRomanNumeralException e) {
				System.out.print(romanNumeralInput + " Is An Illegal Roman Numeral Value");
			}
		}
	}

	/**
	 * Reads a file and displays its contents in a text area.
	 * 
	 * @param chosenFile The file to read.
	 */
	private void readSource(File chosenFile) {
		String chosenFileName = chosenFile.getName();
		TextFileInput inFile = new TextFileInput(chosenFileName);
		String ssn;
		int subscript = 0;
		Container myContentPane = jframe.getContentPane();
		TextArea myTextArea = new TextArea();
		TextArea mySubscripts = new TextArea();
		myContentPane.add(myTextArea, BorderLayout.EAST);
		myContentPane.add(mySubscripts, BorderLayout.WEST);

		ssn = inFile.readLine();
		while (ssn != null) {
			mySubscripts.append(Integer.toString(subscript++) + "\n");
			myTextArea.append(ssn + "\n");
			ssn = inFile.readLine();
		} // while

		jframe.setVisible(true);
	}
}
