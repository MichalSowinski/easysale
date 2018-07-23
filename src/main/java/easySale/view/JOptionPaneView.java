package easySale.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;

import easySale.main.Main;

public class JOptionPaneView {

	public static String logInScreen() {
		UIManager.put("Panel.background", new ColorUIResource(Color.decode("#757575")));
		UIManager.put("OptionPane.background", Color.decode("#757575"));
		UIManager.put("OptionPane.foreground", Color.decode("#757575"));
		UIManager.put("OptionPane.messageForeground", Color.decode("#ffffff"));
		UIManager.put("OptionPane.inputDialogTitle", "Easy-Sale");

		return JOptionPane.showInputDialog("Enter your employee code:");

	}

	public static int menu(String[] nameOfButtons, String title) {

		ImageIcon icon = new ImageIcon(Main.class.getResource("/easylogo.png"));
		UIManager.put("OptionPane.background", new ColorUIResource(Color.decode("#BDBDBD")));
		UIManager.put("Panel.background", new ColorUIResource(Color.decode("#BDBDBD")));
		UIManager.put("OptionPane.messageForeground", Color.decode("#424242"));
		UIManager.put("Button.background", Color.decode("#757575"));
		UIManager.put("OptionPane.messageFont", new FontUIResource(Font.MONOSPACED, Font.BOLD, 16));
		UIManager.put("Button.foreground", Color.decode("#E0E0E0"));
		UIManager.put("Button.select", Color.decode("#9E9E9E"));
		UIManager.put("Button.focus", Color.decode("#757575"));
		UIManager.put("Label.background", Color.red);

		int option = JOptionPane.showOptionDialog(null, "Easy-Sale Module v1.0", title, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.INFORMATION_MESSAGE, icon, nameOfButtons, "default");

		return option;

	}

	public static int administratorPanel(String[] nameOfButtons, String message) {

		ImageIcon icon = new ImageIcon(Main.class.getResource("/easylogo.png"));

		UIManager.put("OptionPane.background", new ColorUIResource(Color.decode("#BDBDBD")));
		UIManager.put("Panel.background", new ColorUIResource(Color.decode("#BDBDBD")));
		UIManager.put("OptionPane.messageForeground", Color.decode("#424242"));
		UIManager.put("Button.background", Color.decode("#757575"));
		UIManager.put("OptionPane.messageFont", new FontUIResource(Font.MONOSPACED, Font.CENTER_BASELINE, 24));
		UIManager.put("Button.foreground", Color.decode("#E0E0E0"));
		UIManager.put("Button.select", Color.decode("#9E9E9E"));
		UIManager.put("Button.focus", Color.decode("#757575"));
		UIManager.put("Label.background", Color.red);

		int option = JOptionPane.showOptionDialog(null, message, "Easy-Sale Administrator Panel",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, icon, nameOfButtons, "default");

		return option;

	}

	public static void menuSales() {

		UIManager.put("OptionPane.background", new ColorUIResource(Color.decode("#BDBDBD")));
		UIManager.put("Panel.background", new ColorUIResource(Color.decode("#BDBDBD")));
		UIManager.put("OptionPane.messageForeground", Color.decode("#424242"));
		UIManager.put("Button.background", Color.decode("#757575"));
		UIManager.put("OptionPane.messageFont", new FontUIResource(Font.MONOSPACED, Font.CENTER_BASELINE, 30));
		UIManager.put("Button.foreground", Color.decode("#E0E0E0"));
		UIManager.put("Button.select", Color.decode("#9E9E9E"));
		UIManager.put("Button.focus", Color.decode("#757575"));
		UIManager.put("Label.background", Color.red);
		
		

	}

}
