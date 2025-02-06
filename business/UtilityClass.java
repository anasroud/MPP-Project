package business;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class UtilityClass {

	private UtilityClass() {
		// TODO Auto-generated constructor stub
	}
	public static void centerFrameOnDesktop(JFrame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

}
