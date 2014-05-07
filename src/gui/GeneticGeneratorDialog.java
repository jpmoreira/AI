package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneticGeneratorDialog extends JDialog{

	private boolean newSettings = false;


	private double mutationProb;
	private double mutationProbVarFac;
	private double probToRank;
	private double diversityUsageFac;
	private boolean directFitnessToProb;
	private int pairingStates = 0;


	/** The states pairing check box. */
	private JCheckBox statesPairingCheckBox;

	/** The states to pair label. */
	private JLabel statesToPairLabel;

	/** The states to pair text field. */
	private JTextField statesToPairTextField;  

	/** The mutation check box. */
	private JCheckBox mutationCheckBox;

	/** The mutation prob label. */
	private JLabel mutationProbLabel;

	/** The mutation prob text field. */
	private JTextField mutationProbTextField;

	/** The mutation var label. */
	private JLabel mutationVarLabel;

	/** The mutation var text field. */
	private JTextField mutationVarTextField;    

	/** The direct fitness check box. */
	private JCheckBox directFitnessCheckBox;

	private JLabel probToRankLabel;

	private JTextField probToRankLTextField;

	private JLabel diversityLabel;

	private JTextField diversityTextField;


	/** The left gen panel. */
	private JPanel leftGenPanel;

	/** The center gen panel. */
	private JPanel centerGenPanel;

	/** The right gen panel. */
	private JPanel rightGenPanel;

	/** The genetic label. */
	private JLabel geneticLabel;


	/** The Ok Panel */
	private JPanel okPanel;

	/** The Ok button */
	private JButton okButton;

	/** The Cancel button */
	private JButton cancelButton;


	public GeneticGeneratorDialog(JFrame frame, boolean modal, String myMessage){

		super(frame,modal);

		getContentPane().setLayout(new BorderLayout(5,5));

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}


	private void addWidgets(Container contentPane) {

		leftGenPanel.add(statesPairingCheckBox,BorderLayout.NORTH);
		leftGenPanel.add(statesToPairLabel,BorderLayout.CENTER);
		leftGenPanel.add(statesToPairTextField,BorderLayout.EAST);
		//topPanel.add(leftGenPanel,BorderLayout.WEST);
		//contentPane.add(leftGenPanel);

		centerGenPanel.add(mutationCheckBox);
		centerGenPanel.add(new JLabel(""));
		centerGenPanel.add(mutationProbLabel);
		centerGenPanel.add(mutationProbTextField);
		centerGenPanel.add(mutationVarLabel);
		centerGenPanel.add(mutationVarTextField);
		//topPanel.add(centerGenPanel,BorderLayout.CENTER);
		//contentPane.add(centerGenPanel);

		rightGenPanel.add(directFitnessCheckBox);
		rightGenPanel.add(new JLabel(""));
		rightGenPanel.add(probToRankLabel);
		rightGenPanel.add(probToRankLTextField);
		rightGenPanel.add(diversityLabel);
		rightGenPanel.add(diversityTextField);

		//contentPane.add(rightGenPanel);

		//		JPanel temp = new JPanel();
		//		temp.setLayout(new GridLayout(1,3,5,5));
		//		
		//		temp.add(leftGenPanel);
		//		temp.add(centerGenPanel);
		//		temp.add(rightGenPanel);
		//temp.add(new JLabel(""));
		//temp.setAlignmentY(GridLayout.LEADING);

		//contentPane.add(geneticGenLabel,BorderLayout.NORTH);

		okPanel.add(okButton);
		okPanel.add(cancelButton);

		contentPane.add(leftGenPanel,BorderLayout.WEST);
		contentPane.add(centerGenPanel,BorderLayout.CENTER);
		contentPane.add(rightGenPanel,BorderLayout.EAST);
		contentPane.add(okPanel,BorderLayout.SOUTH);


	}


	private void createWidgets() {

		leftGenPanel = new JPanel();
		leftGenPanel.setLayout(new BorderLayout(0,0));
		centerGenPanel = new JPanel();
		centerGenPanel.setLayout(new GridLayout(3, 2, 0, 0));
		rightGenPanel = new JPanel();
		rightGenPanel.setLayout(new GridLayout(3, 2, 0, 0));

		statesPairingCheckBox = new JCheckBox("Pairing States");
		statesPairingCheckBox.setSelected(false);
		statesPairingCheckBox.addActionListener(new StatesPairingListener());

		statesToPairLabel = new JLabel("States to pair");
		statesToPairLabel.setAlignmentY(TOP_ALIGNMENT);

		statesToPairTextField = new JTextField(4);
		statesToPairTextField.setEditable(false);
		statesToPairTextField.setAlignmentY(TOP_ALIGNMENT);

		mutationCheckBox = new JCheckBox("Mutation");
		mutationCheckBox.setSelected(false);
		mutationCheckBox.addActionListener(new MutationListener());

		mutationProbLabel = new JLabel("Mutation probability");
		mutationProbTextField = new JTextField(4);
		mutationProbTextField.setEditable(false);

		mutationVarLabel = new JLabel("Mutation decreasing factor");
		mutationVarTextField = new JTextField(4);
		mutationVarTextField.setEditable(false);

		directFitnessCheckBox = new JCheckBox("Direct fitness to probability");
		directFitnessCheckBox.setSelected(false);
		directFitnessCheckBox.addActionListener(new DirectFitnessListener());

		probToRankLabel = new JLabel("State chosen probability ");
		probToRankLTextField = new JTextField(4);
		//probToRankLTextField.setEditable(false);

		diversityLabel = new JLabel("Diversity usage factor");
		diversityTextField = new JTextField(4);
		//diversityTextField.setEditable(false);

		/* Ok Panel */
		okPanel = new JPanel();
		okPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));

		okButton = new JButton("OK");
		okButton.addActionListener(new OkListener());

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelListener());



	}


	public double getMutationProb() {
		return mutationProb;
	}


	public void setMutationProb(double mutationProb) {
		this.mutationProb = mutationProb;
	}


	public double getMutationProbVarFac() {
		return mutationProbVarFac;
	}


	public void setMutationProbVarFac(double mutationProbVarFac) {
		this.mutationProbVarFac = mutationProbVarFac;
	}


	public double getProbToRank() {
		return probToRank;
	}


	public void setProbToRank(double probToRank) {
		this.probToRank = probToRank;
	}


	public double getDiversityUsageFac() {
		return diversityUsageFac;
	}


	public void setDiversityUsageFac(double diversityUsageFac) {
		this.diversityUsageFac = diversityUsageFac;
	}


	public boolean isDirectFitnessToProb() {
		return directFitnessToProb;
	}


	public void setDirectFitnessToProb(boolean directFitnessToProb) {
		this.directFitnessToProb = directFitnessToProb;
	}


	public int getPairingStates() {
		return pairingStates;
	}


	public void setPairingStates(int pairingStates) {
		this.pairingStates = pairingStates;
	}


	/**
	 * The listener interface for receiving mutation events.
	 * The class that is interested in processing a mutation
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addMutationListener<code> method. When
	 * the mutation event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see MutationEvent
	 */
	public class MutationListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			mutationProbTextField.setEditable(mutationCheckBox.isSelected());
			mutationVarTextField.setEditable(mutationCheckBox.isSelected());
		}

	}





	/**
	 * The listener interface for receiving statesPairing events.
	 * The class that is interested in processing a statesPairing
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addStatesPairingListener<code> method. When
	 * the statesPairing event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see StatesPairingEvent
	 */
	public class StatesPairingListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {			
			statesToPairTextField.setEditable(statesPairingCheckBox.isSelected());
		}

	}




	/**
	 * The listener interface for receiving directFitness events.
	 * The class that is interested in processing a directFitness
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addDirectFitnessListener<code> method. When
	 * the directFitness event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see DirectFitnessEvent
	 */
	public class DirectFitnessListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public class CancelListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			newSettings = false;
			setVisible(false);

		}

	}





	public class OkListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			

			if (statesPairingCheckBox.isSelected()){
				int tempPairingStates;
				
				try {
					tempPairingStates = Integer.parseInt(statesToPairTextField.getText());
					if (tempPairingStates < 1 ) throw new Exception();
					
					//TODO add population limit
					setPairingStates(tempPairingStates);
				}
				catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(getParent(), "Pairing states input must be an Integer");
					return;
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), "Pairing states input must be greater than 0");
					return;
				}			
			}
			
			if (mutationCheckBox.isSelected()){
				double tempMutProb;
				double tempMutVarFac;
				
				try {
					tempMutProb = Double.parseDouble(mutationProbTextField.getText());
					tempMutVarFac = Double.parseDouble(mutationVarTextField.getText());
					
					if (tempMutProb < 0 || tempMutProb > 1) throw new Exception();
					if (tempMutVarFac < 0 || tempMutVarFac > 1) throw new Exception();
					
					//TODO add population limit
					setMutationProb(tempMutProb);
					setMutationProbVarFac(tempMutVarFac);
				}
				catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(getParent(), "Mutations factors must Doubles");
					return;
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), "Mutations factor must be between 0.0 and 1.0");
					return;
				}
				
			}
				
			setDirectFitnessToProb(directFitnessCheckBox.isSelected());
			
			try {
				
				double tempDivUsageFac;
				double tempProbToRank;
				
				tempDivUsageFac = Double.parseDouble(diversityTextField.getText());
				tempProbToRank = Double.parseDouble(probToRankLTextField.getText());
				
				if (tempDivUsageFac < 0 || tempDivUsageFac > 1) throw new Exception();
				if (tempProbToRank < 0 || tempProbToRank > 1) throw new Exception();
				
				//TODO add population limit
				setProbToRank(tempProbToRank);
				setDiversityUsageFac(tempDivUsageFac);
			}
			catch (NumberFormatException n) {
				JOptionPane.showMessageDialog(getParent(), "Diversity usage factor and probability to rank must Double");
				return;
			}
			catch (Exception e) {
				JOptionPane.showMessageDialog(getParent(), "Diversity usage factor and probability to rank must be between 0.0 and 1.0");
				return;
			}
			
			newSettings = true;
			setVisible(false);
			
		}

	}

}
