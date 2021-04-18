package hr.edunova.natalis;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import org.hibernate.Session;

import hr.edunova.natalis.model.User;
import hr.edunova.natalis.panel.AuthorPanel;
import hr.edunova.natalis.panel.AuthorizationPanel;
import hr.edunova.natalis.panel.BookPanel;
import hr.edunova.natalis.panel.CategoryPanel;
import hr.edunova.natalis.panel.PublisherPanel;
import hr.edunova.natalis.panel.RegistrationPanel;
import hr.edunova.natalis.panel.ReviewPanel;
import hr.edunova.natalis.panel.UserPanel;
import hr.edunova.natalis.panel.WelcomePanel;
import hr.edunova.natalis.util.HibernateUtil;
import hr.edunova.natalis.util.InitData;

public class ApplicationFrame {

	private User user;
	
	private JFrame frame;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Session s = HibernateUtil.getSession();
					if (s.getMetamodel().getEntities().size() > 0) {
						ApplicationFrame window = new ApplicationFrame();
						window.frame.setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ApplicationFrame() {
		new InitData().init();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		// authorization panel
		AuthorizationPanel authorizationPanel = new AuthorizationPanel(this); 
		frame.getContentPane().add(authorizationPanel, AuthorizationPanel.class.getSimpleName());

		// panel registration
		RegistrationPanel registrationPanel = new RegistrationPanel(this);
		frame.getContentPane().add(registrationPanel, RegistrationPanel.class.getSimpleName());

		// panel welcome (main menu)
		WelcomePanel welcomePanel = new WelcomePanel(this);
		frame.getContentPane().add(welcomePanel, WelcomePanel.class.getSimpleName());

		// category panel
		CategoryPanel categoryPanel = new CategoryPanel(this); 
		frame.getContentPane().add(categoryPanel, CategoryPanel.class.getSimpleName());

		// publisher panel
		PublisherPanel publisherPanel = new PublisherPanel(this);
		frame.getContentPane().add(publisherPanel, PublisherPanel.class.getSimpleName());

		// author panel
		AuthorPanel authorPanel = new AuthorPanel(this);
		frame.getContentPane().add(authorPanel, AuthorPanel.class.getSimpleName());

		// book panel
		BookPanel bookPanel = new BookPanel(this);
		frame.getContentPane().add(bookPanel, BookPanel.class.getSimpleName());

		// user panel
		UserPanel userPanel = new UserPanel(this);
		frame.getContentPane().add(userPanel, UserPanel.class.getSimpleName());

		// review panel
		ReviewPanel reviewPanel = new ReviewPanel(this);
		frame.getContentPane().add(reviewPanel, ReviewPanel.class.getSimpleName());
	}

	public JFrame getFrame() {
		return frame;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}