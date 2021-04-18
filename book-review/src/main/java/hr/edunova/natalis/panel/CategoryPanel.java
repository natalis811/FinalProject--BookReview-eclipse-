package hr.edunova.natalis.panel;

import java.awt.Dimension;
import java.awt.Font;
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
import hr.edunova.natalis.controller.CategoryController;
import hr.edunova.natalis.exception.BookException;
import hr.edunova.natalis.model.Category;

public class CategoryPanel extends BasePanel {
	
	private static final long serialVersionUID = 1L;

	private CategoryController controller;
	
	private JList<Category> list;
	private JTextField tfName;

	public CategoryPanel(ApplicationFrame application) {
		super(application);
		controller = new CategoryController();
		initialize();
	}
	
	private void initialize() {
		setLayout(null);
		setPreferredSize(new Dimension(800, 600));
		
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
		        loadCategories();
			}
		});
				
		add(createBackButton());
		add(createHeader("Category"));
		
		// list
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				categorySelected(e);
			}
		});
		add(createScrollPane(list));
		
		// name
		JLabel lblName = new JLabel("Category name:");
		lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		lblName.setBounds(375, 70, 127, 26);
		add(lblName);
		
		tfName = new JTextField();
		tfName.setBounds(502, 70, 261, 26);
		tfName.setColumns(10);
		add(tfName);
		
		// buttons
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addCategory();
			}
		});
		btnAdd.setBounds(502, 104, 89, 29);
		add(btnAdd);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateCategory();
			}
		});
		btnEdit.setBounds(590, 104, 89, 29);
		add(btnEdit);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteCategory();
			}
		});
		btnDelete.setBounds(675, 104, 94, 29);
		add(btnDelete);
	}
	
	private void loadCategories() {
		DefaultListModel<Category> model = new DefaultListModel<>();
        model.addAll(controller.getData());
        list.setModel(model);
        clearCategory();
	}
	
	private void categorySelected(ListSelectionEvent e) {
		if (e.getValueIsAdjusting() || list.getSelectedValue() == null) { return; }               
        tfName.setText(list.getSelectedValue().getName());
	}
	
	private void clearCategory() {
		tfName.setText("");
	}
	
	private void addCategory() {
		try {
			Category category = new Category();
			category.setName(tfName.getText());
			controller.setEntity(category);
			controller.create();
			loadCategories();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Adding category failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void updateCategory() {
		try {
			Category category = list.getSelectedValue();
			category.setName(tfName.getText());
			controller.setEntity(category);
			controller.update();
			loadCategories();
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Updating category failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void deleteCategory() {
		try {
			Category category = list.getSelectedValue();
			controller.setEntity(category);
			controller.delete();
			loadCategories();
		}catch (javax.persistence.PersistenceException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					"Category cannot be deleted as used in book", "Warning", JOptionPane.WARNING_MESSAGE);
		} catch (BookException exc) {
			JOptionPane.showMessageDialog(application.getFrame(), 
					exc.getMessage(), "Deleting category failed", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}