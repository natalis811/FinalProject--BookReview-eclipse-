package hr.edunova.natalis.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import hr.edunova.natalis.ApplicationFrame;
import hr.edunova.natalis.controller.BookController;
import hr.edunova.natalis.controller.ReviewController;
import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Book;
import hr.edunova.natalis.model.Review;

public class ReviewPanel extends BasePanel {

	private static final long serialVersionUID = 1L;

	private ReviewController controller;
	private BookController bookController;

	private JList<Review> list;
	private JComboBox<Book> cbBook;
	private DatePicker dpDate;
	private JTextArea taReview;
	private JSlider slRating;
	private JTextField tfUser;

	public ReviewPanel(ApplicationFrame application) {
		super(application);
		controller = new ReviewController();
		bookController = new BookController();
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				loadReviews();
				loadBooks();
				tfUser.setText(application.getUser().getFirstName() + " " + application.getUser().getLastName());
			}
		});

		add(createBackButton());
		add(createHeader("Review"));

		// list
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				reviewSelected(e);
			}
		});
		add(createScrollPane(list));

		// book
		JLabel lblBook = new JLabel("Book");
		lblBook.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblBook.setBounds(375, 110, 127, 26);
		add(lblBook);

		cbBook = new JComboBox<>();
		cbBook.setBounds(502, 110, 261, 26);
		AutoCompleteDecorator.decorate(cbBook);
		add(cbBook);

		// date
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblDate.setBounds(375, 150, 127, 26);
		add(lblDate);

		DatePickerSettings dpSettings= new DatePickerSettings(new Locale("hr", "HR"));
		dpSettings.setFormatForDatesCommonEra("dd.MM.yyyy");
		dpDate = new DatePicker(dpSettings);
		dpDate.setBounds(502, 150, 200, 30);
		add(dpDate);

		// rating
		JLabel lblRating = new JLabel("Rating");
		lblRating.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblRating.setBounds(375, 190, 127, 26);
		add(lblRating);

		slRating = new JSlider();
		slRating.setMinimum(1);
		slRating.setMaximum(5);
		slRating.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				lblRating.setText(String.valueOf(slRating.getValue()));
			}
		});
		slRating.setBounds(502, 190, 190, 29);
		add(slRating);

		// review
		JLabel lblReview = new JLabel("Review");
		lblReview.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblReview.setBounds(375, 230, 127, 26);
		add(lblReview);

		taReview = new JTextArea();
		taReview.setBounds(502, 230, 242, 186);
		add(taReview);

		// user
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(375, 462, 61, 16);
		add(lblUser);

		tfUser = new JTextField();
		tfUser.setBounds(502, 457, 130, 26);
		taReview.setColumns(10);
		add(tfUser);

		// buttons
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addReview();
			}
		});
		btnAdd.setBounds(502, 516, 89, 29);
		add(btnAdd);

		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateReview();
			}
		});
		btnEdit.setBounds(590, 516, 89, 29);
		add(btnEdit);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteReview();
			}
		});
		btnDelete.setBounds(675, 516, 94, 29);
		add(btnDelete);
	}

	private void loadReviews() {
		DefaultListModel<Review> model = new DefaultListModel<>();
		model.addAll(controller.getData());
		list.setModel(model);
		clearReview();
	}

	private void reviewSelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() || list.getSelectedValue() == null) { return; }
		cbBook.setSelectedItem(list.getSelectedValue().getBook());
		taReview.setText(list.getSelectedValue().getText());
		dpDate.setDate(list.getSelectedValue().getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		slRating.setValue(list.getSelectedValue().getRating());


	}

	private void clearReview() {
		cbBook.setSelectedIndex(-1);
		taReview.setText(" ");
		dpDate.setDate(null);
		slRating.setValue(1);

	}

	private void loadBooks() {
		DefaultComboBoxModel<Book> m = new DefaultComboBoxModel<>();
		m.addElement(new Book(-1L, "Choose book..."));
		bookController.getData().forEach(e -> {
			m.addElement(e);
		});
		cbBook.setModel(m);
		cbBook.setSelectedIndex(0);
	}
	private void addReview() {
		try {
			Review review = controller.findReview((Book) cbBook.getSelectedItem(), application.getUser());
			if (review != null) {
				JOptionPane.showMessageDialog(application.getFrame(),
						"Review by you for this book already exists", "Warning", JOptionPane.WARNING_MESSAGE);
				return;
			}

			review = new Review();
			review.setText(taReview.getText());
			review.setBook((Book) cbBook.getSelectedItem());
			review.setRating(slRating.getValue());
			review.setUser(application.getUser());
			if (dpDate.getDate() != null){
				review.setDate(Date.from(dpDate.getDate().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
			}
			controller.setEntity(review);
			controller.create();
			loadReviews();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Adding review failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void updateReview() {
		try {
			Review review = list.getSelectedValue();
			review.setText(taReview.getText());
			review.setBook((Book) cbBook.getSelectedItem());
			review.setRating(slRating.getValue());
			review.setUser(application.getUser());
			controller.setEntity(review);
			controller.update();
			loadBooks();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Updating review failed", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void deleteReview() {
		try {
			Review review = list.getSelectedValue();
			if (review.getUser().getId() == application.getUser().getId() || application.getUser().isAdmin()) {
				controller.setEntity(review);
				controller.delete();
				loadBooks();
			} else {
				JOptionPane.showMessageDialog(application.getFrame(),
						"You're not allowed to delete review", "Warning", JOptionPane.WARNING_MESSAGE);
			}
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(),
					exc.getMessage(), "Deleting review failed", JOptionPane.ERROR_MESSAGE);
		}
	}

}