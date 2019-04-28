package first.denglu;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
public class DengLuJieMian extends JPanel {
	protected ImageIcon icon;
	public int width,height;
	public DengLuJieMian() {
		super();
		icon = new ImageIcon("res/login.jpg");
		width = icon.getIconWidth();
		height = icon.getIconHeight();
		setSize(width, height);
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image img = icon.getImage();
		g.drawImage(img, 0, 0,getParent());
	}
}