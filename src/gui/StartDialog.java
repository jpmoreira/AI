package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// TODO: Auto-generated Javadoc
/**
 * The Class StartDialog.
 */
public class StartDialog extends JDialog{

	/** The size input. */
	private boolean newProblem = false;
	
	/** The number of tiles. */
	private int numTiles;
	
	/** The number of landuses. */
	private int numLanduses;
	
	/** Population Size. */
	private int populationSize;
	
	private boolean repeatedConst;
	
	private boolean useAllConstructions;

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
	
	private JCheckBox repeatedConstuctionsCheckbox;
	
	private JCheckBox useAllContructionsCheckbox;
	
	/** The ok input button. */
	private JButton okButton;
	
	/** The cancel input button. */
	private JButton cancelButton;


	/**
	 * Instantiates a new start dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 */
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
	


	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {
		
		inputPanel.add(populationLabel);
		inputPanel.add(populationField);
		inputPanel.add(tilesLabel);
		inputPanel.add(tileField);
		inputPanel.add(landuseLabel);
		inputPanel.add(landuseField);
		inputPanel.add(useAllContructionsCheckbox);
		inputPanel.add(repeatedConstuctionsCheckbox);
		inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		contentPane.add(inputPanel,BorderLayout.CENTER);

		inputButtonsPanel.add(okButton);
		inputButtonsPanel.add(cancelButton);
		contentPane.add(inputButtonsPanel,BorderLayout.SOUTH);

	}
	


	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {

		// Input Panel
		inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(4,2,5,5));

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
		
		repeatedConstuctionsCheckbox = new JCheckBox("Repeat Landuses");
		
		useAllContructionsCheckbox = new JCheckBox("Use all Landuses");

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



	/**
	 * Gets the new problem.
	 *
	 * @return the new problem
	 */
	public boolean getNewProblem() {
		return newProblem;
	}
	

	/**
	 * Gets the tiles number.
	 *
	 * @return the tiles number
	 */
	public int getTilesNumber() {
		return numTiles;
	}
	

	/**
	 * Gets the land use number.
	 *
	 * @return the land use number
	 */
	public int getLandUseNumber() {
		return numLanduses;
	}
	
	
	
	/**
	 * Gets the population size.
	 *
	 * @return the population size
	 */
	public int getPopulationSize() {
		return populationSize;
	}



	public boolean isRepeatedConst() {
		return repeatedConst;
	}



	public void setRepeatedConst(boolean repeatedConst) {
		this.repeatedConst = repeatedConst;
	}



	public boolean isUseAllConstructions() {
		return useAllConstructions;
	}



	public void setUseAllConstructions(boolean useAllConstructions) {
		this.useAllConstructions = useAllConstructions;
	}



	/**
	 * The listener interface for receiving ok events.
	 * The class that is interested in processing a ok
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addOkListener<code> method. When
	 * the ok event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see OkEvent
	 */
	public class OkListener implements ActionListener {

		
	
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
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
					
					if ((tempLanduse > tempTiles) && useAllContructionsCheckbox.isSelected()) throw new Exception("1");
					
					numTiles = tempTiles;
					numLanduses = tempLanduse;
					populationSize = tempPopSize;
					
					repeatedConst = repeatedConstuctionsCheckbox.isSelected();
					useAllConstructions = useAllContructionsCheckbox.isSelected();
					
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
				if (e1.getMessage().equals("1")){
					JOptionPane.showMessageDialog(getParent(), "Number of landuses must equal or lower than the number of lots.");
					return;
				}
				JOptionPane.showMessageDialog(getParent(), "The numbers must be greater than 0");
				return;
				
			}
			
		}
	
	}


	/**
	 * The Class CancelButton.
	 */
	public class CancelButton implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			newProblem = false;
			setVisible(false);
		}

	}

	
	
}
