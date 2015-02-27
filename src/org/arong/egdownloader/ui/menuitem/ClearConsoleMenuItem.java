package org.arong.egdownloader.ui.menuitem;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import org.arong.egdownloader.ui.ComponentConst;
import org.arong.egdownloader.ui.window.EgDownloaderWindow;
/**
 * 清空控制台菜单项
 * @author dipoo
 * @since 2015-01-09
 */
public class ClearConsoleMenuItem extends JMenuItem {

	private static final long serialVersionUID = -2951780178305327150L;
	
	public ClearConsoleMenuItem(String text, final EgDownloaderWindow mainWindow){
		super(text);
		this.setIcon(new ImageIcon(getClass().getResource(ComponentConst.ICON_PATH + ComponentConst.SKIN_NUM + ComponentConst.SKIN_ICON.get("clear"))));
		this.setForeground(new Color(0,0,85));
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				mainWindow.consoleArea.setText("");
			}
		});
	}
}