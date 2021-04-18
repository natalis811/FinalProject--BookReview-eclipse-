package hr.edunova.natalis.panel;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hr.edunova.natalis.ApplicationFrame;

public class BasePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	protected ApplicationFrame application;
	
	public BasePanel(ApplicationFrame application) {
		super();
		this.application = application;
	}
	
	protected void showCardPanel(String panelName) {
		CardLayout layout = (CardLayout) application.getFrame().getContentPane().getLayout();
		layout.show(application.getFrame().getContentPane(), panelName);
	}
	
	protected JLabel createHeader(String text) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		label.setBounds(50, 25, 200, 29);
		return label;
	}
	
	protected JButton createBackButton() {
		JButton btn = new JButton("Back to main menu");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCardPanel(WelcomePanel.class.getSimpleName());
			}
		});
		btn.setBounds(45, 530, 148, 29);
		return btn;
	}
	
	protected JScrollPane createScrollPane(JList<?> list) {
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 70, 300, 450);
		scrollPane.setViewportView(list);
		return scrollPane;
	}
	
}