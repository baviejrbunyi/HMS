package hms.frames;

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
	private static final long serialVersionUID = 4202816484766865727L;

	Color navyBlue = new Color(0x05445E);
	Color babyBlue = new Color(0xD4F1F4);
	Color orangeAccent = new Color(0xFCDFA6);

	Font robotoBold14 = new Font("Roboto", Font.BOLD, 14);
	Font robotoBold20 = new Font("Roboto", Font.BOLD, 20);
	Font robotoBold10 = new Font("Roboto", Font.BOLD, 10);
	Font robotoPlain14 = new Font("Roboto", Font.PLAIN, 14);
	Font robotoPlain12 = new Font("Roboto", Font.PLAIN, 12);
	Font robotoBold12 = new Font("Roboto", Font.BOLD, 12);

	ImageIcon logo = new ImageIcon("src/images/logo.png");
	Image icon = Toolkit.getDefaultToolkit().getImage("src/images/logo.png");
	ImageIcon profile = new ImageIcon("src/images/profile.jpg");
	ImageIcon show = new ImageIcon("src/images/icons/show.png");
	ImageIcon hide = new ImageIcon("src/images/icons/hide.png");

}
