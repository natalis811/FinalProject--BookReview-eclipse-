package hr.edunova.natalis.panel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import hr.edunova.natalis.ApplicationFrame;
import hr.edunova.natalis.controller.PublisherController;
import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Publisher;

public class PublisherPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private PublisherController controller;

	private JList<Publisher> list;
	private JTextField tfName;
	private JTextField tfAddress;
	private JTextField tfCity;
	private JTextField tfCountry;

	public PublisherPanel(ApplicationFrame application) {
		super(application);
		controller = new PublisherController();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				loadPublishers();
			}
		});

		add(createBackButton());
		add(createHeader("Publisher"));

		// list
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				publisherSelected(e);
			}
		});
		add(createScrollPane(list));

		// name
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(381, 65, 82, 16);
		add(lblName);

		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(531, 56, 244, 34);
		add(tfName);

		// address
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setBounds(381, 105, 82, 16);
		add(lblAddress);

		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(531, 96, 244, 34);
		add(tfAddress);

		// city
		JLabel lblCity = new JLabel("City:");
		lblCity.setBounds(381, 145, 82, 16);
		add(lblCity);

		tfCity = new JTextField();
		tfCity.setColumns(10);
		tfCity.setBounds(531, 136, 244, 34);
		add(tfCity);

		JLabel lblCountry = new JLabel("Country:");
		lblCountry.setBounds(381, 182, 82, 16);
		add(lblCountry);

		// country
		tfCountry = new JTextField();
		tfCountry.setColumns(10);
		tfCountry.setBounds(531, 173, 244, 34);
		add(tfCountry);

		// buttons
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addPublisher();
			}
		});
		btnAdd.setBounds(508, 227, 89, 29);
		add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updatePublisher();
			}
		});
		btnEdit.setBounds(596, 227, 89, 29);
		add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deletePublisher();
			}
		});
		btnDelete.setBounds(681, 227, 94, 29);
		add(btnDelete);
	}

	private void loadPublishers() {
		DefaultListModel<Publisher> model = new DefaultListModel<>();
		model.addAll(controller.getData());
		list.setModel(model);
		clearPublisher();
	}

	private void publisherSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() || list.getSelectedValue() == null) { return; }
		tfName.setText(list.getSelectedValue().getName());
		tfAddress.setText(list.getSelectedValue().getAddress());
		tfCity.setText(list.getSelectedValue().getCity());
		tfCountry.setText(list.getSelectedValue().getCountry());
	}

	private void clearPublisher() {
		tfName.setText("");
		tfAddress.setText("");
		tfCity.setText("");
		tfCountry.setText("");
	}

	private void addPublisher() {
		try {
			Publisher publisher = new Publisher();
			publisher.setName(tfName.getText());
			publisher.setAddress(tfAddress.getText());
			publisher.setCity(tfCity.getText());
			publisher.setCountry(tfCountry.getText());
			controller.setEntity(publisher);
			controller.create();
			loadPublishers();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Adding publisher failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updatePublisher() {
		try {
			Publisher publisher = list.getSelectedValue();
			publisher.setName(tfName.getText());
			publisher.setAddress(tfAddress.getText());
			publisher.setCity(tfCity.getText());
			publisher.setCountry(tfCountry.getText());
			controller.setEntity(publisher);
			controller.update();
			loadPublishers();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Updating publisher failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deletePublisher() {
		try {
			Publisher publisher = list.getSelectedValue();
			controller.setEntity(publisher);
			controller.delete();
			loadPublishers();
		}catch (javax.persistence.PersistenceException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					"Publisher cannot be deleted as used in book", "Warning", JOptionPane.WARNING_MESSAGE);
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Deleting publisher failed", JOptionPane.ERROR_MESSAGE);
		}
	}

}