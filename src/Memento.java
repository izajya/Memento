import javax.swing.JOptionPane;

/**
 * A memento is an object that stores the current
 * state of an object. In this case, this memento
 * stores the textual information and the color
 * of the landing page.
 * @author yvonne
 *
 */
public class Memento {

	private String about;
	private String web;
	private String statement;
	private String hex;
	private int c = 0;

	/**
	 * 
	 * @param String currAbout the current about me text
	 * @param String currWeb the current link
	 * @param String currStatement the current personal statement
	 * @param String currHex the current hex color
	 */
	protected Memento(String aboutText, String webText, 
			String statementText, String hexText, int version){
		about = aboutText;
		web = webText;
		statement = statementText;
		hex = hexText;
		c = version;
	}

	protected String getAbout(){
		return about;
	}

	protected String getWeb(){
		return web;
	}

	protected String getStatement(){
		return statement;
	}

	protected String getHex(){
		try{
			return hex;
		} catch (NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter a valid hexadecimal color. 6 characters, 0-9 and A-F.");
			return "ffffff";
		}
		
	}

	public String toString(){
		return "Version " + c;
	}
}
