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

// TODO: Auto-generated Javadoc
/**
 * The Class PopulationDialog.
 */
public class PopulationDialog extends JDialog {


	/** The size input. */
	private boolean newPopulation = false;
	
	/** The number of tiles. */
	private int populationSize;

	/** The input panel. */
	private JPanel inputPanel;
	
	/** The input buttons panel. */
	private JPanel inputButtonsPanel;

	/** The tiles label. */
	private JLabel populationLabel;
	
	/** The input field. */
	private JTextField populationField;
	
	/** The ok input button. */
	private JButton okButton;
	
	/** The cancel input button. */
	private JButton cancelButton;


	/**
	 * Instantiates a new population dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 */
	public PopulationDialog (JFrame frame, boolean modal, String myMessage){

		super(frame,modal);

		getContentPane().setLayout(new BorderLayout());

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
		inputPanel.setLayout(new GridLayout(2,2,5,5));

		// Input Content
		populationLabel = new JLabel("Population size:");

		populationField = new JTextField();
		populationField.setEditable(true);


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
	 * Gets the new population.
	 *
	 * @return the new population
	 */
	public boolean getNewPopulation() {
		return newPopulation;
	}
	

	/**
	 * Gets the population size.
	 *
	 * @return the population size
	 */
	public int getPopulationSize() {
		return populationSize;
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
			try {
				
				int tempPopSize = Integer.parseInt(populationField.getText());
				
				if (tempPopSize > 0){
					populationSize = tempPopSize;
					newPopulation = true;
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
			setVisible(false);
		}

	}

	

	
}
