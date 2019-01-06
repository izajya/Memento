import java.awt.*;
import java.awt.event.*;
import javax.swing.border.TitledBorder;
import javax.swing.*;

/**
 * The Graphical User Interface where the user can
 * add their name, hobbies, and personal statement
 * to a set of text boxes. The user will also be able
 * to edit and save their work, and revive old versions
 * of this Landing page ("about me" page) in a j combo box.
 * The states of the landing page use the memento pattern to
 * be stored.
 * @author Yvonne
 * @version Apr 20, 2018
 */


@SuppressWarnings("serial")
public class GUI extends JPanel{
	private JPanel landing = new JPanel();	// panel with text content		
	private JPanel options = new JPanel();	// panel with buttons & menu

	// the areas that the user can edit
	private JTextArea about = new JTextArea();
	private JTextField web = new JTextField();
	private JTextArea statement = new JTextArea();

	// the components added to the options panel
	private JButton edit = new JButton("Edit");		
	private JButton save = new JButton("Save");
	private JComboBox<Memento> menu = new JComboBox<Memento>();	

	// the label and text field responsible for changing
	// the background col. these 2 components are often hidden
	private JLabel colorLbl = new JLabel("background col:");	
	private JTextField hex = new JTextField();	// input of hex color.

	// following the memento patten
	private CareTaker caretaker = new CareTaker();	
	private int savedPages = 0;	// total saved states

	//default color for background
	private static final Color DEFAULT_COLOR = new Color(0xAFCCD2);

	/**
	 * Constructor. 
	 */
	public GUI() {
		setLayout(new BorderLayout());

		// creates the panels
		createLandingPanel();
		createOptionsPanel();
		createBorders();

		add(landing, BorderLayout.CENTER);
		add(options, BorderLayout.SOUTH);

		// hide the background color options.
		// this feature will only be shown when the "edit" is pressed.
		colorLbl.setVisible(false);
		hex.setVisible(false);
		save.setEnabled(false);
	}

	/**
	 * Creates the options panel.
	 * Adds buttons and JComboBox.
	 */
	private void createOptionsPanel(){
		options.setLayout(new GridLayout());
		options.add(edit);		
		options.add(save);
		options.add(menu);

		edit.addActionListener(new ActionListener(){
			/**
			 * When the Edit button is pressed on, 
			 * a series components much be enabled
			 * (or disabled.)
			 */
			@Override
			public void actionPerformed(ActionEvent evt){	
				// show the background-color options.
				colorLbl.setVisible(true);		
				hex.setVisible(true);	// text field to add hexadecimal color

				// all text boxes are editable.
				about.setEditable(true);	
				web.setEditable(true);
				statement.setEditable(true);

				// because the user is now in the edit stage,
				// the edit button should be disabled.
				edit.setEnabled(false);
				save.setEnabled(true);
			}
		});

		save.addActionListener(new ActionListener(){
			/**
			 * When the Save button is pressed, 
			 * add the state (the memento) to caretaker.
			 */
			@Override
			public void actionPerformed(ActionEvent evt){	
				// try getting the hexadecimal color.
				// if the hex is invalid, catch the error
				// by showing a pop up.
				try {
					changeColor(hex.getText());
					if (savedPages > 1 && !checkContents(savedPages - 1)){
						// nothing happens
					} else {
						// take what is currently in the text boxes to 
						// set a new memento.
						
						// the caretaker adds the new memento to the arraylist
						caretaker.addMemento(saveState());
						

						// if the current state has the exact same
						// contents of the last state, then the current state
						// will not be added to the menu nor the arraylist in
						// Caretaker.
						menu.addItem(caretaker.getMemento(savedPages));
						savedPages++;
					}
					
					colorLbl.setVisible(false);		
					hex.setVisible(false);
					save.setEnabled(false);
					edit.setEnabled(true);

				} catch (Exception e){
					JOptionPane.showMessageDialog(hex, "Please enter a valid hexadecimal color. 6 characters, 0-9 and A-F.");
				}
			}
		});

		menu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				set(caretaker.getMemento(menu.getSelectedIndex()));
			}
		});
	}

	private boolean checkContents(int prev){
		if (!about.getText().equals(caretaker.getMemento(prev).getAbout()) || 
				!web.getText().equals(caretaker.getMemento(prev).getWeb()) ||
				!statement.getText().equals(caretaker.getMemento(prev).getStatement()) ||
				!hex.getText().equals(caretaker.getMemento(prev).getHex())){
			return true;
		} 
		return false;
	}

	/**
	 * Creates the Landing Page panel, which includes
	 * all of the text boxes and color options.
	 */
	private void createLandingPanel(){
		// set the background to the default color.
		// the layout is absolute.
		landing.setBackground(DEFAULT_COLOR);
		landing.setLayout(null);	

		//	name & hobbies
		about.setBounds(5, 6, 165, 290);
		about.setLineWrap(true);
		about.setWrapStyleWord(true);
		landing.add(about);

		//	area where user can add their website
		web.setBounds(175, 6, 270, 40);
		landing.add(web);

		// area where user can add their personal statement
		statement.setBounds(175, 50, 270, 246);
		statement.setLineWrap(true);
		statement.setWrapStyleWord(true);
		landing.add(statement);

		// add the color jtextfield and label
		colorLbl.setBounds(5, 305, 108, 16);
		hex.setBounds(114, 300, 80, 26);
		landing.add(colorLbl);
		landing.add(hex);

		// set editable to false. the only time
		// these components are editable is when
		// the "edit" button has been pressed on.
		about.setEditable(false);
		web.setEditable(false);
		statement.setEditable(false);
	}

	/**
	 * A method that changes the color of
	 * the background.
	 * Precondition: The code passed in should
	 * be a valid, 6-digit hexadecimal color.
	 */
	private void changeColor(String code){
		// if there is not a code passed into
		// the method, handle this by setting the
		// background to the default color.
		if (code.isEmpty()){
			landing.setBackground(DEFAULT_COLOR);
			return;
		}

		// use the code to set the new color.
		String color = "#" + code;
		landing.setBackground(Color.decode(color));
	}

	/**
	 * Creates the borders for the jtextareas and 
	 * text field. This will allow the users to know
	 * what they should write in each box.
	 */
	private void createBorders(){		
		TitledBorder aboutBorder = new TitledBorder("Name & Hobbies");
		TitledBorder websiteBorder = new TitledBorder("Website");
		TitledBorder statementBorder = new TitledBorder("Personal Statement");

		// set the borders
		about.setBorder(aboutBorder);
		web.setBorder(websiteBorder);
		statement.setBorder(statementBorder);
	}

	private void set(Memento prev){
		about.setText(prev.getAbout());
		web.setText(prev.getWeb());
		statement.setText(prev.getStatement());
		hex.setText(prev.getHex());
		changeColor(prev.getHex());
	}
	
	private Memento saveState(){
		return new Memento(about.getText(), web.getText(), 
				statement.getText(), hex.getText(), savedPages + 1);
	}
}