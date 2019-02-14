package org.arong.egdownloader.ui.menuitem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;

import org.arong.egdownloader.ui.panel.TaskImagePanel;
import org.arong.egdownloader.ui.table.TaskingTable;
import org.arong.egdownloader.ui.window.EgDownloaderWindow;
/**
 * 暂停所有任务
 * @author dipoo
 * @since 2014-12-07
 */
public class ChangeViewMenuItem extends JMenuItem {

	private static final long serialVersionUID = 8033742031776192264L;
	public ChangeViewMenuItem(String text, final EgDownloaderWindow window){
		super(text);
		//this.setIcon(IconManager.getIcon("stop"));
		this.setForeground(new Color(0,0,85));
		this.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X,
                java.awt.Event.CTRL_MASK));
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				TaskingTable table = (TaskingTable) window.runningTable;
				
				window.viewModel = window.viewModel == 1 ? 2 : 1;
				window.setting.setViewModel(window.viewModel);
				window.getContentPane().remove(window.tablePane);
				if(window.viewModel == 1){
					window.tablePane.getViewport().removeAll();
					window.tablePane = new JScrollPane(table);
					table.scrollRectToVisible(new Rectangle(0, 0));
					//隐藏分页栏
					if(window.taskImagePanel.imageTaskPager != null){
						window.taskImagePanel.imageTaskPager.setVisible(false);
					}
				}else{
					window.tablePane.removeAll();
					window.tablePane = new JScrollPane();
					if(window.taskImagePanel == null){
						window.taskImagePanel = new TaskImagePanel(window);
					}else{
						window.taskImagePanel.init(window.tasks);
					}
					window.tablePane.setViewportView(window.taskImagePanel);
					window.tablePane.getVerticalScrollBar().setUnitIncrement(20);
					window.tablePane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
						public void adjustmentValueChanged(AdjustmentEvent e) {
							JScrollBar bar = (JScrollBar) e.getSource();
							if(e.getValue() + bar.getHeight() == bar.getMaximum()){
								window.taskImagePanel.setPreferredSize(new Dimension((int)window.taskImagePanel.getPreferredSize().getWidth(), (int)window.taskImagePanel.getPreferredSize().getHeight() + bar.getUnitIncrement()));
							}
						}
					});
					//回到顶部
					window.taskImagePanel.scrollRectToVisible(new Rectangle(0, 0));
					window.taskImagePanel.setVisible(true);
				}
				window.tablePane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				window.tablePane.setBounds(new Rectangle(5, 40, window.getWidth() - 20, window.getHeight() - 280));
				window.tablePane.getViewport().setBackground(new Color(254,254,254));
				window.getContentPane().add(window.tablePane);
				window.tablePane.repaint();
				window.tablePane.updateUI();
				window.repaint();
			}
		});
	}
}
