package gui;

import gui.GUInterface.PauseListener;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
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
import javax.swing.JSlider;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneticGeneratorDialog.
 */
public class GeneticGeneratorDialog extends JDialog{

	/** The new settings. */
	private boolean newSettings = false;


	/** The mutation prob. */
	private double mutationProb;
	
	/** The mutation prob var fac. */
	private double mutationProbVarFac;
	
	/** The prob to rank. */
	private double probToRank;
	
	/** The diversity usage fac. */
	private double diversityUsageFac;
	
	/** The direct fitness to prob. */
	private boolean directFitnessToProb;
	
	/** The pairing states. */
	private int pairingStates;
	
	/** The max pairing. */
	private int maxPairing;
	

	/** The states to pair label. */
	private JLabel statesToPairLabel;

	/** The states to pair text field. */
	private JSlider statesToPairSlider;  

	/** The mutation check box. */
	private JCheckBox mutationCheckBox;

	/** The mutation prob label. */
	private JLabel mutationProbLabel;

	/** The mutation prob text field. */
	private JSlider mutationProbSlider;

	/** The mutation var label. */
	private JLabel mutationVarLabel;

	/** The mutation var text field. */
	private JTextField mutationVarField;    

	/** The direct fitness check box. */
	private JCheckBox directFitnessCheckBox;

	/** The prob to rank label. */
	private JLabel probToRankLabel;

	/** The prob to rank slider. */
	private JSlider probToRankSlider;

	/** The diversity label. */
	private JLabel diversityLabel;

	/** The diversity slider. */
	private JSlider diversitySlider;


	/** The pairing panel. */
	private JPanel pairingPanel;


	/** The right gen panel. */
	private JPanel fitnessPanel;

	/** The genetic label. */
	private JLabel geneticLabel;


	/**  The Ok Panel. */
	private JPanel okPanel;

	/**  The Ok button. */
	private JButton okButton;

	/**  The Cancel button. */
	private JButton cancelButton;


	/** The diversity panel. */
	private JPanel diversityPanel;
	
	
	/** The mutation pro panel. */
	private JPanel mutationProPanel;
	
	/** The mutation var panel. */
	private JPanel mutationVarPanel;
	
	/** The direct fitness panel. */
	private JPanel directFitnessPanel;


	/**
	 * Instantiates a new genetic generator dialog.
	 *
	 * @param frame the frame
	 * @param modal the modal
	 * @param myMessage the my message
	 * @param maxPairing the max pairing
	 * @param pairingStates2 the pairing states2
	 * @param mutationProb2 the mutation prob2
	 * @param mutationProbVarFac2 the mutation prob var fac2
	 * @param diversityUsageFac2 the diversity usage fac2
	 * @param directFitnessToProb2 the direct fitness to prob2
	 * @param probToRank2 the prob to rank2
	 */
	public GeneticGeneratorDialog(JFrame frame, boolean modal, String myMessage, int maxPairing, int pairingStates2, double mutationProb2, double mutationProbVarFac2, double diversityUsageFac2, boolean directFitnessToProb2, double probToRank2){

		super(frame,modal);

		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		this.setTitle("Genetic Generator Settings");
		
		
		this.maxPairing = maxPairing;
		this.pairingStates = pairingStates2/2;
		this.mutationProb = mutationProb2;
		this.mutationProbVarFac = mutationProbVarFac2;	
		this.diversityUsageFac = diversityUsageFac2;
		this.directFitnessToProb = directFitnessToProb2;
		this.probToRank = probToRank2;

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
		
		pairingPanel.add(statesToPairLabel);
		pairingPanel.add(statesToPairSlider);

		mutationProPanel.add(mutationProbLabel);
		mutationProPanel.add(mutationProbSlider);
		
		mutationVarPanel.add(mutationVarLabel);
		mutationVarPanel.add(mutationVarField);

		directFitnessPanel.add(directFitnessCheckBox);
		
		fitnessPanel.add(probToRankLabel);
		fitnessPanel.add(probToRankSlider);
		
		diversityPanel.add(diversityLabel);
		diversityPanel.add(diversitySlider);

		okPanel.add(okButton);
		okPanel.add(cancelButton);

		
		contentPane.add(diversityPanel);
		contentPane.add(pairingPanel);
		contentPane.add(mutationProPanel);
		contentPane.add(mutationVarPanel);
		contentPane.add(directFitnessPanel);
		contentPane.add(fitnessPanel);
		contentPane.add(okPanel);

	}


	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {
		
		FlowLayout centerLineLayout = new FlowLayout(FlowLayout.CENTER,5,5);
		FlowLayout leftLineLayout = new FlowLayout(FlowLayout.LEADING,5,5);

		pairingPanel = new JPanel();
		pairingPanel.setLayout(centerLineLayout);
		
		mutationProPanel = new JPanel();
		mutationProPanel.setLayout(centerLineLayout);
		
		mutationVarPanel = new JPanel();
		mutationVarPanel.setLayout(centerLineLayout);
		
		fitnessPanel = new JPanel();
		fitnessPanel.setLayout(centerLineLayout);
		
		diversityPanel = new JPanel();
		diversityPanel.setLayout(centerLineLayout);
		
		directFitnessPanel = new JPanel();
		directFitnessPanel.setLayout(leftLineLayout);
		
		
		
		statesToPairLabel = new JLabel("Nr of Pairings:");	
		statesToPairSlider = new JSlider(0, maxPairing, pairingStates);
		statesToPairSlider.setSnapToTicks(true);
		statesToPairSlider.setPaintTicks(true);
		statesToPairSlider.setPaintLabels(true);
		statesToPairSlider.setMinorTickSpacing(1);
		statesToPairSlider.setMajorTickSpacing(maxPairing/4);
		statesToPairSlider.setPreferredSize(new Dimension(500,40));

//		mutationCheckBox = new JCheckBox("Mutation");
//		mutationCheckBox.setSelected(true);
//		mutationCheckBox.addActionListener(new MutationListener());

		mutationProbLabel = new JLabel("Mutation probability (%):");
		mutationProbSlider = new JSlider(0, 100, (int) (mutationProb*100));
		mutationProbSlider.setSnapToTicks(true);
		mutationProbSlider.setPaintTicks(true);
		mutationProbSlider.setPaintLabels(true);
		mutationProbSlider.setMinorTickSpacing(1);
		mutationProbSlider.setMajorTickSpacing(10);
		mutationProbSlider.setPreferredSize(new Dimension(500,40));
//		mutationProbSlider.setEnabled(mutationCheckBox.isSelected());
				

		mutationVarLabel = new JLabel("Mutation variation factor:");
		mutationVarField = new JTextField(((Double) mutationProbVarFac).toString(), 4);
//		mutationVarSlider.setEnabled(mutationCheckBox.isSelected());

		directFitnessCheckBox = new JCheckBox("Direct fitness to probability (%):");
		directFitnessCheckBox.setSelected(directFitnessToProb);
		directFitnessCheckBox.addActionListener(new DirectFitnessListener());

		probToRankLabel = new JLabel("Ranking Factor:");
		probToRankSlider = new JSlider(0, 100, (int) (probToRank*100));
		probToRankSlider.setSnapToTicks(true);
		probToRankSlider.setPaintTicks(true);
		probToRankSlider.setPaintLabels(true);
		probToRankSlider.setMinorTickSpacing(1);
		probToRankSlider.setMajorTickSpacing(10);
		probToRankSlider.setPaintTrack(true);
		probToRankSlider.setEnabled(!directFitnessCheckBox.isSelected());
		probToRankSlider.setPreferredSize(new Dimension(500,40));
	

		diversityLabel = new JLabel("Diversity usage factor (%):");
		diversitySlider = new JSlider(0, 100, (int) (diversityUsageFac*100));
		diversitySlider.setSnapToTicks(true);
		diversitySlider.setPaintTicks(true);
		diversitySlider.setPaintLabels(true);
		diversitySlider.setMinorTickSpacing(1);
		diversitySlider.setMajorTickSpacing(10);
		diversitySlider.setPreferredSize(new Dimension(500,40));

		/* Ok Panel */
		okPanel = new JPanel();
		okPanel.setLayout(new FlowLayout(FlowLayout.TRAILING,5,5));

		okButton = new JButton("OK");
		okButton.addActionListener(new OkListener());

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new CancelListener());



	}


	/**
	 * Gets the mutation prob.
	 *
	 * @return the mutation prob
	 */
	public double getMutationProb() {
		return mutationProb;
	}


	/**
	 * Sets the mutation prob.
	 *
	 * @param mutationProb the new mutation prob
	 */
	public void setMutationProb(double mutationProb) {
		this.mutationProb = mutationProb;
	}


	/**
	 * Gets the mutation prob var fac.
	 *
	 * @return the mutation prob var fac
	 */
	public double getMutationProbVarFac() {
		return mutationProbVarFac;
	}


	/**
	 * Sets the mutation prob var fac.
	 *
	 * @param mutationProbVarFac the new mutation prob var fac
	 */
	public void setMutationProbVarFac(double mutationProbVarFac) {
		this.mutationProbVarFac = mutationProbVarFac;
	}


	/**
	 * Gets the prob to rank.
	 *
	 * @return the prob to rank
	 */
	public double getProbToRank() {
		return probToRank;
	}


	/**
	 * Sets the prob to rank.
	 *
	 * @param probToRank the new prob to rank
	 */
	public void setProbToRank(double probToRank) {
		this.probToRank = probToRank;
	}


	/**
	 * Gets the diversity usage fac.
	 *
	 * @return the diversity usage fac
	 */
	public double getDiversityUsageFac() {
		return diversityUsageFac;
	}


	/**
	 * Sets the diversity usage fac.
	 *
	 * @param diversityUsageFac the new diversity usage fac
	 */
	public void setDiversityUsageFac(double diversityUsageFac) {
		this.diversityUsageFac = diversityUsageFac;
	}


	/**
	 * Checks if is direct fitness to prob.
	 *
	 * @return true, if is direct fitness to prob
	 */
	public boolean isDirectFitnessToProb() {
		return directFitnessToProb;
	}


	/**
	 * Sets the direct fitness to prob.
	 *
	 * @param directFitnessToProb the new direct fitness to prob
	 */
	public void setDirectFitnessToProb(boolean directFitnessToProb) {
		this.directFitnessToProb = directFitnessToProb;
	}


	/**
	 * Gets the pairing states.
	 *
	 * @return the pairing states
	 */
	public int getPairingStates() {
		return pairingStates;
	}


	/**
	 * Sets the pairing states.
	 *
	 * @param pairingStates the new pairing states
	 */
	public void setPairingStates(int pairingStates) {
		this.pairingStates = pairingStates;
	}






	/**
	 * Checks if is new settings.
	 *
	 * @return true, if is new settings
	 */
	public boolean isNewSettings() {
		return newSettings;
	}


	/**
	 * Sets the new settings.
	 *
	 * @param newSettings the new new settings
	 */
	public void setNewSettings(boolean newSettings) {
		this.newSettings = newSettings;
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
			probToRankSlider.setEnabled(!directFitnessCheckBox.isSelected());

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
	public class CancelListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			setNewSettings(false);
			setVisible(false);

		}

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
			
			pairingStates = statesToPairSlider.getValue()*2;
			
			mutationProb = ((double) mutationProbSlider.getValue())/100;
			
				double tempMutVarFac;
				
				try {
					
					tempMutVarFac = Double.parseDouble(mutationVarField.getText());
					
					if (tempMutVarFac < 0) throw new Exception();
					
					//TODO add population limit
					setMutationProbVarFac(tempMutVarFac);
				}
				catch (NumberFormatException n) {
					JOptionPane.showMessageDialog(getParent(), "Mutations factors must a Double");
					return;
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), "Mutations factor must be greater than 0");
					return;
				}
				
			if (directFitnessCheckBox.isSelected()){
				setDirectFitnessToProb(true);
			} else {
				setDirectFitnessToProb(false);
				probToRank = ((double) probToRankSlider.getValue())/100;
			}
			
			diversityUsageFac = ((double) diversitySlider.getValue())/100;
			
			setNewSettings(true);
			setVisible(false);
			
		}

	}

}
