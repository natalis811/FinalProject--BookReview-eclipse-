package hr.edunova.natalis;

import java.awt.Dimension;

import javax.swing.JDialog;

public class Preloader extends JDialog {

	private static final long serialVersionUID = 1L;

	public Preloader() {
		super(null, "LOADING", ModalityType.DOCUMENT_MODAL);
		setPreferredSize(new Dimension(300, 200));
		setMinimumSize(new Dimension(300, 200));
		setLocationRelativeTo(null);
		setResizable(false);
	}

}