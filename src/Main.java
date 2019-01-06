import javax.swing.JFrame;

/**
 * Main class to run the landing page editor. 
 * 
 * @author Yvonne
 * @version Apr 11, 2018
 */


public class Main {
	public static void main(String[] args){
		JFrame window = new JFrame();
		window.setSize(450, 375);
		window.add(new GUI());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}