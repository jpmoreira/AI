package gui;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.GeneticEngine;
import mainPackage.Population;
import mainPackage.TileProblemPopulation;

public class SaveLoadDialog extends JDialog{

	/** The save problem. */
	private boolean saveProblem = false;
	
	/** The load problem. */
	private boolean loadProblem = false;
	
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

	private TileProblemPopulation tempPopulation;
	
	private GeneticEngine tempGeneticEngine;
	
	private JFrame frame;

	
	
	/**
	 * Instantiates a new save load dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 * @param population 
	 * @param geneticEngine 
	 */
	public SaveLoadDialog(JFrame frame, boolean modal, String myMessage, TileProblemPopulation population, GeneticEngine geneticEngine)
	{

		super(frame,modal);
		getContentPane().setLayout(new GridLayout(3,1,5,5));

		this.frame = frame;
		this.tempPopulation = population;
		this.tempGeneticEngine = geneticEngine;
		this.setTitle("Save/Load Problem");
		
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
		if (tempPopulation==null){
			saveButton.setEnabled(false);
		}

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

			String path = "./saved_problems/" + name;
			
			loadProblem(path);

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

				 

			String name = pathInputField.getText();
			name.concat(".dat");

			String path = "./saved_problems/" + name;
			
			saveProblem(path);

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

			setVisible(false);

		}

	}

	
	public void saveProblem(String path) {
		String newProblemMsg = "Save Problem?";
		int reply = JOptionPane.showConfirmDialog(frame,newProblemMsg,"Save Problem",JOptionPane.YES_NO_OPTION);

		if(reply == JOptionPane.YES_OPTION)
		{
			try
			{
				
				FileOutputStream fileOut = new FileOutputStream(path);
				ObjectOutputStream os = new ObjectOutputStream(fileOut);

				/* Write the game in a file */
				
//				tempPopulation.setCoinTosser(tempGeneticEngine);
				
//				os.writeObject(tempPopulation);
				os.writeObject(tempGeneticEngine);

				fileOut.close();
				os.close();
				
				saveProblem = true;

			}
			catch (IOException i )
			{
				JOptionPane.showMessageDialog(frame,"Error writing the file.");
			}
		}
		else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}
		
		
	}


	public void loadProblem(String path2) {
		
		String newProblemMsg = "Load Problem?";
		int reply = JOptionPane.showConfirmDialog(frame,newProblemMsg,"Load Problem",JOptionPane.YES_NO_OPTION);

		if(reply == JOptionPane.YES_OPTION)
		{
			try
			{
				
				FileInputStream fileIn = new FileInputStream(path2);
				ObjectInputStream is = new ObjectInputStream(fileIn);

				/* load the saved game in the file to the object tempGame */
//				setTempPopulation((TileProblemPopulation) is.readObject());
//				setTempGeneticEngine(tempPopulation.getCoinTosser());
				
				setTempGeneticEngine((GeneticEngine) is.readObject());

				is.close();
				fileIn.close();
				
				tempPopulation = (TileProblemPopulation) tempGeneticEngine.getPopulation();
				loadProblem = true;


			}
			catch (FileNotFoundException i)
			{
				JOptionPane.showMessageDialog(frame,"File not supported.");
			}
			catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(frame,"Object error.");
				e.printStackTrace();
			}
			catch (IOException i )
			{
				JOptionPane.showMessageDialog(frame,"Input/OutPut error.");
				i.printStackTrace();
			}
			
		}
		else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}

	}


	private void setTempGeneticEngine(GeneticEngine readObject) {
		this.tempGeneticEngine = readObject;
	}


	public void setSaveProblem(boolean b) {
		this.saveProblem = b;
	}


	public void setLoadProblem(boolean b) {
		this.loadProblem = b;
		
	}


	public TileProblemPopulation getTempPopulation() {
		return tempPopulation;
	}


	public void setTempPopulation(TileProblemPopulation tempPopulation) {
		this.tempPopulation = tempPopulation;
	}


	public GeneticEngine getTempGeneticEngine() {
		return tempGeneticEngine;
	}

}
