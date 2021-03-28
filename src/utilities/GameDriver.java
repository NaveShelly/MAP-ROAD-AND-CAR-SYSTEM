/**
 * 
 */
package utilities;
import javax.swing.*;
import java.awt.*;

/**
 * @author Nave shelly 312190796 Koral Moyal 318855038
 *
 */
public class GameDriver {
	public static void main(String[] args) {
		GUI GUIX=new GUI("Road System");
		GUIX.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUIX.setSize((new Dimension(800,600)));
		GUIX.setResizable(true);
		GUIX.pack();
		GUIX.setVisible(true);
	}
}
