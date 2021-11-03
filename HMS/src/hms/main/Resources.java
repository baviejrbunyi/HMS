package hms.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Resources extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1684978889552634842L;

	// colors
	Color babyBlue = new Color(0xD4F1F4);
	Color blueGreen = new Color(0x75E6DA);
	Color navyBlue = new Color(0x05445E);
	Color blueGrotto = new Color(0x189AB4);
	Color orangeAccent = new Color(0xFCDFA6);

	// fonts
	Font tahoma = new Font("Tahoma", Font.PLAIN, 15);
	Font tahomaBold = new Font("Tahoma", Font.BOLD, 15);
	Font tahomaError = new Font("Tahoma", Font.PLAIN, 10);
	Font robotoNav = new Font("Roboto", Font.PLAIN, 25);
	Font robotoBold10 = new Font("Roboto", Font.BOLD, 10);
	Font robotoBold = new Font("Roboto", Font.BOLD, 20);
	Font robotoHead = new Font("Roboto", Font.BOLD, 16);
	Font robotoBold14 = new Font("Roboto", Font.BOLD, 14);

	// images
	ImageIcon logo = new ImageIcon("src/images/logo.png");
	Image icon = Toolkit.getDefaultToolkit().getImage("src/images/logo.png");
}
