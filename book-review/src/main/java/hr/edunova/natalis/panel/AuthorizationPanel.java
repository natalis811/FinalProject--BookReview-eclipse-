package hr.edunova.natalis.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import hr.edunova.natalis.ApplicationFrame;
import hr.edunova.natalis.controller.AuthorizationController;
import hr.edunova.natalis.model.User;

public class AuthorizationPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private AuthorizationController controller;

	private JTextField tfUsername;
	private JPasswordField pfPassword;

	public AuthorizationPanel(ApplicationFrame application) {
		super(application);
		controller = new AuthorizationController();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				if (application.getUser() != null) {
					tfUsername.setText(application.getUser().getEmail());
				}
			}
		});

		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(249, 316, 73, 16);
		add(lblUsername);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(259, 341, 63, 16);
		add(lblPassword);

		tfUsername = new JTextField();
		tfUsername.setBounds(332, 311, 167, 26);
		tfUsername.setColumns(10);
		tfUsername.setText("natalis811@gmail.com"); // TODO
		add(tfUsername);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(332, 337, 167, 26);
		pfPassword.setText("edunova"); // TODO
		add(pfPassword);

		JButton btnLogin = new JButton("Login");
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(425, 373, 79, 29);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		add(btnLogin);

		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(330, 373, 95, 29);
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCardPanel(RegistrationPanel.class.getSimpleName());
			}
		});
		add(btnRegister);

		JLabel show_image = new JLabel("");
		show_image.setIcon(new ImageIcon(AuthorizationPanel.class.getResource("/bookpic.png")));
		show_image.setBounds(6, 6, 273, 290);
		add(show_image);
	}

	private void login() {
		try {
			User user = controller.login(tfUsername.getText(), pfPassword.getPassword());
			application.setUser(user);
			showCardPanel(WelcomePanel.class.getSimpleName());
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Login failed", JOptionPane.ERROR_MESSAGE);
		}
	}

}