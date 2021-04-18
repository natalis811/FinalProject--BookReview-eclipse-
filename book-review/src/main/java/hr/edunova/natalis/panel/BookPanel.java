package hr.edunova.natalis.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import hr.edunova.natalis.ApplicationFrame;
import hr.edunova.natalis.controller.AuthorController;
import hr.edunova.natalis.controller.BookController;
import hr.edunova.natalis.controller.CategoryController;
import hr.edunova.natalis.controller.PublisherController;
import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Author;
import hr.edunova.natalis.model.Book;
import hr.edunova.natalis.model.Category;
import hr.edunova.natalis.model.Publisher;

public class BookPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private BookController controller;
	private AuthorController authorController;
	private CategoryController categoryController;
	private PublisherController publisherController;
	
	private JList<Book> list;
	private JTextField tfTitle;
	private JTextField tfIsbn;
	private JTextField tfPublished;
	private JComboBox<Author> cbAuthor;
	private JComboBox<Category> cbCategory;
	private JComboBox<Publisher> cbPublisher;

	public BookPanel(ApplicationFrame application) {
		super(application);
		controller = new BookController();
		authorController = new AuthorController();
		categoryController = new CategoryController();
		publisherController = new PublisherController();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				loadBooks();
				loadAuthors();
				loadPublishers();
				loadCategories();
			}
		});

		add(createBackButton());
		add(createHeader("Book"));

		// list
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				bookSelected(e);
			}
		});
		add(createScrollPane(list));
		
		// name
		JLabel lblTitle = new JLabel("Title");
		lblTitle.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblTitle.setBounds(375, 70, 127, 26);
		add(lblTitle);
		
		tfTitle = new JTextField();
		tfTitle.setBounds(502, 70, 261, 26);
		tfTitle.setColumns(10);
		add(tfTitle);
		
		JLabel lblAuthor = new JLabel("Author");
		lblAuthor.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblAuthor.setBounds(375, 110, 127, 26);
		add(lblAuthor);
		
		cbAuthor = new JComboBox<>();
		cbAuthor.setBounds(502, 110, 261, 26);
		AutoCompleteDecorator.decorate(cbAuthor);
		add(cbAuthor);
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblCategory.setBounds(375, 150, 127, 26);
		add(lblCategory);
		
		cbCategory = new JComboBox<>();
		cbCategory.setBounds(502, 150, 261, 26);
		AutoCompleteDecorator.decorate(cbCategory);
		add(cbCategory);
		
		JLabel lblPublisher = new JLabel("Publisher");
		lblPublisher.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblPublisher.setBounds(375, 190, 127, 26);
		add(lblPublisher);
		
		cbPublisher = new JComboBox<>();
		cbPublisher.setBounds(502, 190, 261, 26);
		AutoCompleteDecorator.decorate(cbPublisher);
		add(cbPublisher);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblIsbn.setBounds(375, 230, 127, 26);
		add(lblIsbn);
		
		tfIsbn = new JTextField();
		tfIsbn.setBounds(502, 230, 261, 26);
		tfIsbn.setColumns(10);
		add(tfIsbn);
		
		JLabel lblPublished = new JLabel("Published");
		lblPublished.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblPublished.setBounds(375, 270, 127, 26);
		add(lblPublished);
		
		tfPublished = new JTextField();
		tfPublished.setBounds(502, 270, 261, 26);
		tfPublished.setColumns(10);
		add(tfPublished);
		
		// buttons
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addBook();
			}
		});
		btnAdd.setBounds(502, 321, 89, 29);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateBook();
			}
		});
		btnEdit.setBounds(590, 321, 89, 29);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBook();
			}
		});
		btnDelete.setBounds(675, 321, 94, 29);
		add(btnDelete);
	}
	
	private void loadBooks() {
		DefaultListModel<Book> model = new DefaultListModel<>();
		model.addAll(controller.getData());
		list.setModel(model);
		clearBook();
	}

	private void bookSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() || list.getSelectedValue() == null) { return; }               
		tfTitle.setText(list.getSelectedValue().getTitle());
		tfIsbn.setText(list.getSelectedValue().getIsbn());
		tfPublished.setText(list.getSelectedValue().getPublished());
		cbAuthor.setSelectedItem(list.getSelectedValue().getAuthors().get(0));
		cbCategory.setSelectedItem(list.getSelectedValue().getCategory());
        cbPublisher.setSelectedItem(list.getSelectedValue().getPublisher());
	}
	
	private void clearBook() {
		tfTitle.setText("");
		tfIsbn.setText("");
		tfPublished.setText("");
		cbAuthor.setSelectedIndex(-1);
		cbCategory.setSelectedIndex(-1);
		cbPublisher.setSelectedIndex(-1);
	}

	private void addBook() {
		try {
			Book book = new Book();
			book.setTitle(tfTitle.getText());
			book.setIsbn(tfIsbn.getText());
			book.setPublished(tfPublished.getText());
			book.setCategory((Category) cbCategory.getSelectedItem());
			book.setPublisher((Publisher) cbPublisher.getSelectedItem());
			List<Author> authors = new ArrayList<>();
			authors.add((Author) cbAuthor.getSelectedItem());
			book.setAuthors(authors);
			controller.setEntity(book);
			controller.create();
			loadBooks();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Adding book failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateBook() {
		try {
			Book book = list.getSelectedValue();
			book.setTitle(tfTitle.getText());
			book.setIsbn(tfIsbn.getText());
			book.setPublished(tfPublished.getText());
			book.setCategory((Category) cbCategory.getSelectedItem());
			book.setPublisher((Publisher) cbPublisher.getSelectedItem());
			List<Author> authors = new ArrayList<>();
			authors.add((Author) cbAuthor.getSelectedItem());
			book.setAuthors(authors);
			controller.setEntity(book);
			controller.update();
			loadBooks();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Updating book failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteBook() {
		try {
			Book book = list.getSelectedValue();
			controller.setEntity(book);
			controller.delete();
			loadBooks();
		} catch (javax.persistence.PersistenceException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					"Book cannot be deleted as used in review", "Warning", JOptionPane.WARNING_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Deleting book failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loadCategories() {
		DefaultComboBoxModel<Category> m = new DefaultComboBoxModel<>();
        m.addElement(new Category(-1L, "Choose category..."));
        categoryController.getData().forEach(e -> {
            m.addElement(e);
        });
        cbCategory.setModel(m); 
        cbCategory.setSelectedIndex(0);
	}
	
	private void loadPublishers() {
		DefaultComboBoxModel<Publisher> m = new DefaultComboBoxModel<>();
        m.addElement(new Publisher(-1L, "Choose publisher..."));
        publisherController.getData().forEach(e -> {
            m.addElement(e);
        });
        cbPublisher.setModel(m); 
        cbPublisher.setSelectedIndex(0);
	}
	
	private void loadAuthors() {
		DefaultComboBoxModel<Author> m = new DefaultComboBoxModel<>();
        m.addElement(new Author(-1L, "Choose", "author..."));
        authorController.getData().forEach(e -> {
            m.addElement(e);
        });
        cbAuthor.setModel(m); 
        cbAuthor.setSelectedIndex(0);
	}
	
}