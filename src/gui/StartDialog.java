package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StartDialog extends JDialog{

	/** The size input. */
	private boolean newProblem = false;
	
	/** The number of tiles. */
	private int numTiles;
	
	/** The number of landuses. */
	private int numLanduses;
	
	/** Population Size. */
	private int populationSize;

	/** The input panel. */
	private JPanel inputPanel;
	
	/** The input buttons panel. */
	private JPanel inputButtonsPanel;

	/** The tiles label. */
	private JLabel tilesLabel;
	
	/** The landuses label. */
	private JLabel landuseLabel;
	
	/** The landuses label. */
	private JLabel populationLabel;
	
	/** The input field. */
	private JTextField populationField;

	/** The input field. */
	private JTextField tileField;

	/** The landuse field. */
	private JTextField landuseField;
	
	/** The ok input button. */
	private JButton okButton;
	
	/** The cancel input button. */
	private JButton cancelButton;


	public StartDialog (JFrame frame, boolean modal, String myMessage){

		super(frame,modal);

		getContentPane().setLayout(new BorderLayout());
		this.setTitle("New Problem");

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}
	


	private void addWidgets(Container contentPane) {
		
		inputPanel.add(populationLabel);
		inputPanel.add(populationField);
		inputPanel.add(tilesLabel);
		inputPanel.add(tileField);
		inputPanel.add(landuseLabel);
		inputPanel.add(landuseField);
		inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		contentPane.add(inputPanel,BorderLayout.CENTER);

		inputButtonsPanel.add(okButton);
		inputButtonsPanel.add(cancelButton);
		contentPane.add(inputButtonsPanel,BorderLayout.SOUTH);

	}
	


	private void createWidgets() {

		// Input Panel
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(3,2,5,5));

		// Input Content
		
		populationLabel = new JLabel("Population Size:");

		populationField = new JTextField();
		populationField.setEditable(true);
		
		
		tilesLabel = new JLabel("Number of Tiles:");

		tileField = new JTextField();
		tileField.setEditable(true);
		
		landuseLabel = new JLabel("Number of Landuses:");

		landuseField = new JTextField();
		landuseField.setEditable(true);

		// Button Panel
		inputButtonsPanel = new JPanel();
		inputButtonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,5,5));

		// Button - OK
		okButton = new JButton("OK");
		okButton.addActionListener(new OkListener());

		// Button - CANCEL
		cancelButton = new JButton("Cancel");	
		cancelButton.addActionListener(new CancelButton());

	}



	public boolean getNewProblem() {
		return newProblem;
	}
	

	public int getTilesNumber() {
		return numTiles;
	}
	

	public int getLandUseNumber() {
		return numLanduses;
	}
	
	
	
	public int getPopulationSize() {
		return populationSize;
	}



	public class OkListener implements ActionListener {

		
	
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int tempTiles;
			int tempLanduse;
			int tempPopSize;
			
			try {
				tempPopSize = Integer.parseInt(populationField.getText());
				tempTiles = Integer.parseInt(tileField.getText());
				tempLanduse = Integer.parseInt(landuseField.getText());
				
				if (tempTiles > 0 && tempLanduse > 0 && tempPopSize > 0){
					numTiles = tempTiles;
					numLanduses = tempLanduse;
					populationSize = tempPopSize;
					newProblem = true;
					setVisible(false);
				} else {
					throw new Exception();
				}
				
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(getParent(), "Input must be an Integer");
				return;
			}
			catch (Exception e1) {	
				JOptionPane.showMessageDialog(getParent(), "The numbers must be greater than 0");
				return;
			}
			
		}
	
	}


	public class CancelButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			newProblem = false;
			setVisible(false);
		}

	}

	
	
}
