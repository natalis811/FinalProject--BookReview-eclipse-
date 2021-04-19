package hr.edunova.natalis.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import hr.edunova.natalis.ApplicationFrame;
import hr.edunova.natalis.controller.UserController;
import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.User;

public class RegistrationPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private UserController controller;

	private JTextField tfFirstName;
	private JTextField tfLastName;
	private JTextField tfUsername;
	private JPasswordField pfPassword;
	private JPasswordField pfConfirmPassword;

	public RegistrationPanel(ApplicationFrame application) {
		super(application);
		controller = new UserController();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		add(createHeader("Registration"));

		// first name
		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(50, 80, 82, 16);
		add(lblFirstName);

		tfFirstName = new JTextField();
		tfFirstName.setBounds(200, 71, 244, 34);
		add(tfFirstName);
		tfFirstName.setColumns(10);

		// last name
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(50, 120, 82, 16);
		add(lblLastName);

		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(200, 111, 244, 34);
		add(tfLastName);

		// user name
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(50, 160, 82, 16);
		add(lblUsername);

		tfUsername = new JTextField();
		tfUsername.setColumns(10);
		tfUsername.setBounds(200, 151, 244, 34);
		add(tfUsername);

		// password
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(50, 200, 82, 22);
		add(lblPassword);

		pfPassword = new JPasswordField();
		pfPassword.setBounds(200, 194, 244, 34);
		add(pfPassword);

		// confirm password
		pfConfirmPassword = new JPasswordField();
		pfConfirmPassword.setBounds(200, 234, 244, 34);
		add(pfConfirmPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm password:");
		lblConfirmPassword.setBounds(50, 240, 127, 22);
		add(lblConfirmPassword);

		// buttons
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		btnRegister.setBounds(327, 280, 117, 29);
		add(btnRegister);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showCardPanel(AuthorizationPanel.class.getSimpleName());
			}
		});
		btnCancel.setBounds(210, 280, 117, 29);
		add(btnCancel);
	}

	private void register() {
		try {
			User user = controller.register(tfFirstName.getText(), tfLastName.getText(),
					tfUsername.getText(), pfPassword.getPassword(), pfConfirmPassword.getPassword());
			application.setUser(user);
			showCardPanel(AuthorizationPanel.class.getSimpleName());
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Registration failed", JOptionPane.ERROR_MESSAGE);
		}
	}

}