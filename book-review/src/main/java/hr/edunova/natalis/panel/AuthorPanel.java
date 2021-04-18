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
import hr.edunova.natalis.controller.AuthorController;
import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Author;

public class AuthorPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private AuthorController controller;

	private JList<Author> list;
	private JTextField tfFirstName;
	private JTextField tfLastName;

	public AuthorPanel(ApplicationFrame application) {
		super(application);
		controller = new AuthorController();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
		        loadAuthors();
			}
		});
		
		add(createBackButton());
		add(createHeader("Author"));
		
		// list
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				authorSelected(e);
			}
		});
		add(createScrollPane(list));
		
		// first name
		JLabel lblFirstName = new JLabel("First name:");
		lblFirstName.setBounds(385, 75, 82, 16);
		add(lblFirstName);
		
		tfFirstName = new JTextField();
		tfFirstName.setColumns(10);
		tfFirstName.setBounds(535, 66, 244, 34);
		add(tfFirstName);
		
		// last name
		JLabel lblLastName = new JLabel("Last name:");
		lblLastName.setBounds(385, 115, 82, 16);
		add(lblLastName);
		
		tfLastName = new JTextField();
		tfLastName.setColumns(10);
		tfLastName.setBounds(535, 106, 244, 34);
		add(tfLastName);
		
		// buttons
		JButton btnAdd_Author = new JButton("Add");
		btnAdd_Author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			        addAuthor();
			}
		});
		btnAdd_Author.setBounds(512, 152, 89, 29);
		add(btnAdd_Author);
		
		JButton btnEdit_Author = new JButton("Edit");
		btnEdit_Author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateAuthor();
			}
		});
		btnEdit_Author.setBounds(600, 152, 89, 29);
		add(btnEdit_Author);
		
		JButton btnDelete_Author = new JButton("Delete");
		btnDelete_Author.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAuthor();
			}
		});
		btnDelete_Author.setBounds(685, 152, 94, 29);
		add(btnDelete_Author);
	}

	private void loadAuthors() {
		DefaultListModel<Author> m = new DefaultListModel<>();
		m.addAll(controller.getData());
		list.setModel(m);
		clearAuthor();
	}

	private void authorSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() || list.getSelectedValue() == null) { return; }               
		tfFirstName.setText(list.getSelectedValue().getFirstName());
		tfLastName.setText(list.getSelectedValue().getLastName());
	}

	private void clearAuthor() {
		tfFirstName.setText("");
		tfLastName.setText("");
	}

	private void addAuthor() {
		try {
			Author author = new Author();
			author.setFirstName(tfFirstName.getText());
			author.setLastName(tfLastName.getText());
			controller.setEntity(author);
			controller.create();
			loadAuthors();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Adding author failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateAuthor() {
		try {
			Author author = list.getSelectedValue();
			author.setFirstName(tfFirstName.getText());
			author.setLastName(tfLastName.getText());
			controller.setEntity(author);
			controller.update();
			loadAuthors();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Updating author failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteAuthor() {
		try {
			Author author = list.getSelectedValue();
			controller.setEntity(author);
			controller.delete();
			loadAuthors();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Deleting author failed", JOptionPane.ERROR_MESSAGE);
		}
	}

}