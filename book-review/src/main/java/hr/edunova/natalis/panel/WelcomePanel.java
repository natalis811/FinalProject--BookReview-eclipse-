package hr.edunova.natalis.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.kordamp.ikonli.fontawesome.FontAwesome;
import org.kordamp.ikonli.swing.FontIcon;

import hr.edunova.natalis.ApplicationFrame;

public class WelcomePanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	private JLabel lblWelcome;
	private JButton btnMenuUser;
	
	public WelcomePanel(ApplicationFrame application) {
		super(application);
		initialize();
	}
	
	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				if (application.getUser() != null) {
					lblWelcome.setText("Welcome " + application.getUser().getFirstName() 
							+ " " + application.getUser().getLastName());
					btnMenuUser.setVisible(application.getUser().isAdmin());
				}
			}
		});
		
		lblWelcome = new JLabel("Welcome...");
		lblWelcome.setBounds(50, 50, 190, 16);
		add(lblWelcome);

		JButton btnCategory = new JButton("Category");
		btnCategory.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnCategory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCardPanel(CategoryPanel.class.getSimpleName());
			}
		});
		btnCategory.setBounds(50, 100, 150, 150);
		btnCategory.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCategory.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCategory.setIcon(FontIcon.of(FontAwesome.CUBES, 40));
		add(btnCategory);

		JButton btnPublisher = new JButton("Publisher");
		btnPublisher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCardPanel(PublisherPanel.class.getSimpleName());
			}
		});
		btnPublisher.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnPublisher.setBounds(231, 100, 150, 150);
		btnPublisher.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnPublisher.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPublisher.setIcon(FontIcon.of(FontAwesome.ADDRESS_BOOK, 40));
		add(btnPublisher);

		JButton btnAuthor = new JButton("Author");
		btnAuthor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCardPanel(AuthorPanel.class.getSimpleName());
			}
		});
		btnAuthor.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnAuthor.setBounds(412, 100, 150, 150);
		btnAuthor.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAuthor.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAuthor.setIcon(FontIcon.of(FontAwesome.PENCIL, 40));
		add(btnAuthor);

		JButton btnBook = new JButton("Book");
		btnBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCardPanel(BookPanel.class.getSimpleName());
			}
		});
		btnBook.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnBook.setBounds(50, 296, 150, 150);
		btnBook.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnBook.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBook.setIcon(FontIcon.of(FontAwesome.BOOK, 40));
		add(btnBook);

		JButton btnReview = new JButton("Review");
		btnReview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCardPanel(ReviewPanel.class.getSimpleName());
			}
		});
		btnReview.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnReview.setBounds(231, 296, 150, 150);
		btnReview.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnReview.setHorizontalTextPosition(SwingConstants.CENTER);
		btnReview.setIcon(FontIcon.of(FontAwesome.STAR_HALF_EMPTY, 40));
		add(btnReview);

		btnMenuUser = new JButton("User");
		btnMenuUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showCardPanel(UserPanel.class.getSimpleName());
			}
		});
		btnMenuUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMenuUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMenuUser.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		btnMenuUser.setBounds(412, 296, 150, 150);
		btnMenuUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnMenuUser.setHorizontalTextPosition(SwingConstants.CENTER);
		btnMenuUser.setIcon(FontIcon.of(FontAwesome.USER, 40));
		add(btnMenuUser);
	}

}