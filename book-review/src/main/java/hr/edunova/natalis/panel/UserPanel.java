package hr.edunova.natalis.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import hr.edunova.natalis.ApplicationFrame;
import hr.edunova.natalis.controller.UserController;
import hr.edunova.natalis.model.User;

public class UserPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private UserController controller;
	private JList<User> list;
	private JComboBox<User> cbUser;

	public UserPanel(ApplicationFrame application) {
		super(application);
		controller = new UserController();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				loadUsers();
				loadUsercb();

			}
		});

		add(createBackButton());
		add(createHeader("User"));
		// list
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				userSelected(e);
			}
		});
		add(createScrollPane(list));

		// name
		JLabel lblName = new JLabel("User:");
		lblName.setBounds(381, 65, 82, 16);
		add(lblName);

		cbUser = new JComboBox<>();
		cbUser.setBounds(502, 110, 261, 26);
		AutoCompleteDecorator.decorate(cbUser);
		add(cbUser);

		//button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteUser();
			}
		});
		btnDelete.setBounds(502, 174, 94, 29);
		add(btnDelete);

	}
	private void loadUsercb() {
		DefaultComboBoxModel<User> m = new DefaultComboBoxModel<>();
		m.addElement(new User(-1L, "Choose", "user..."));
		controller.getData().forEach(e -> {
			m.addElement(e);
		});
		cbUser.setModel(m);
		cbUser.setSelectedIndex(0);
	}
	private void userSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() || list.getSelectedValue() == null) { return; }
		cbUser.setSelectedItem(list.getSelectedValue());
	}

	private void clearUser() {
		cbUser.setSelectedIndex(-1);
	}

	private void deleteUser() {
		try {
			User user = list.getSelectedValue();
			controller.setEntity(user);
			controller.delete();
			loadUsers();
		}catch (javax.persistence.PersistenceException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					"User cannot be deleted as used in review", "Warning", JOptionPane.WARNING_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Deleting user failed", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void loadUsers() {
		DefaultListModel<User> model = new DefaultListModel<>();
		model.addAll(controller.getData());
		list.setModel(model);
		clearUser();

	}

}