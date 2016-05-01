package view;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuSimple {
	public MenuSimple(){
		
	}
	
	public JMenuBar getMenu(){
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		menu.getAccessibleContext().setAccessibleDescription(
		        "Hola");
		JMenuItem menuItem = new JMenuItem("A text-only menu item");
		menu.add(menuItem);
		menuBar.add(menu);
		return menuBar;
	}
}
