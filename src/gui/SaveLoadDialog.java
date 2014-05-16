package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SaveLoadDialog extends JDialog{

	/** The save problem. */
	private boolean saveProblem = false;
	
	/** The load problem. */
	private boolean loadProblem = false;

	/** The path. */
	private String path;

	/** The text panel. */
	private JPanel textPanel;
	
	/** The path input panel. */
	private JPanel pathInputPanel;
	
	/** The buttons panel. */
	private JPanel buttonsPanel;

	/** The path input label1. */
	private JLabel pathInputLabel1;
	
	/** The path input label2. */
	private JLabel pathInputLabel2;
	
	/** The path input field. */
	private JTextField pathInputField;
	
	/** The save button. */
	private JButton saveButton;
	
	/** The load button. */
	private JButton loadButton;
	
	/** The cancel button. */
	private JButton cancelButton;

	
	/**
	 * Instantiates a new save load dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 */
	public SaveLoadDialog(JFrame frame, boolean modal, String myMessage)
	{

		super(frame,modal);
		getContentPane().setLayout(new GridLayout(3,1,5,5));

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}

	
	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {

		textPanel.add(pathInputLabel1);
		contentPane.add(textPanel);
		
		pathInputPanel.add(pathInputLabel2);
		pathInputPanel.add(pathInputField);
		contentPane.add(pathInputPanel);

		buttonsPanel.add(saveButton);
		buttonsPanel.add(loadButton);
		buttonsPanel.add(cancelButton);
		contentPane.add(buttonsPanel);

	}

	
	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {

		// Input Panel
		textPanel = new JPanel();
		textPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,3));

		// Input Panel
		pathInputPanel = new JPanel();
		pathInputPanel.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

		pathInputLabel1 = new JLabel("All the files are saved in and load from the folder './saved_problems/'");
		pathInputLabel2 = new JLabel("Insert file name:");

		pathInputField = new JTextField();
		pathInputField.setEditable(true);
		pathInputField.setColumns(25);

		// Buttons Panel
		buttonsPanel = new JPanel();
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 3));

		// Button - Save Problem
		saveButton = new JButton("Save Problem");
		saveButton.addActionListener(new SaveProblemListener());

		// Button - Load Problem
		loadButton = new JButton("Load Problem");
		loadButton.addActionListener(new LoadProblemListener());

		// Button - Cancel
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelListener());

	}
	

	/**
	 * Problem saved.
	 *
	 * @return true, if successful
	 */
	public boolean problemSaved() {
		return saveProblem;
	}
	

	/**
	 * Gets the file path.
	 *
	 * @return the file path
	 */
	public String getFilePath() {
		return path;
	}
	

	/**
	 * Problem loaded.
	 *
	 * @return true, if successful
	 */
	public boolean problemLoaded() {
		return loadProblem;
	}

	
	/**
	 * The listener interface for receiving loadProblem events.
	 * The class that is interested in processing a loadProblem
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addLoadProblemListener<code> method. When
	 * the loadProblem event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see LoadProblemEvent
	 */
	private class LoadProblemListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			loadProblem = true;

			String name = pathInputField.getText();
			name.concat(".dat");

			path = "./saved_problems/" + name;

			setVisible(false);

		}

	}
	

	/**
	 * The listener interface for receiving saveProblem events.
	 * The class that is interested in processing a saveProblem
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSaveProblemListener<code> method. When
	 * the saveProblem event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SaveProblemEvent
	 */
	private class SaveProblemListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			saveProblem = true;			 

			String name = pathInputField.getText();
			name.concat(".dat");

			path = "./saved_problems/" + name;

			setVisible(false);

		}

	}
	

	/**
	 * The listener interface for receiving cancel events.
	 * The class that is interested in processing a cancel
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addCancelListener<code> method. When
	 * the cancel event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see CancelEvent
	 */
	private class CancelListener implements ActionListener
	{

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {

			saveProblem = false;
			loadProblem = false;
			path = null;

			setVisible(false);

		}

	}
	

	/**
	 * Sets the save problem.
	 *
	 * @param b the new save problem
	 */
	public void setSaveProblem(boolean b) {
		saveProblem = b;
		
	}

	
	/**
	 * Sets the load problem.
	 *
	 * @param b the new load problem
	 */
	public void setLoadProblem(boolean b) {
		loadProblem = b;
		
	}

}
