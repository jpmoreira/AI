package gui;

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

public class GeneticGeneratorDialog extends JDialog{

	private boolean newSettings = false;


	private double mutationProb;
	private double mutationProbVarFac;
	private double probToRank;
	private double diversityUsageFac;
	private boolean directFitnessToProb;
	private int pairingStates;
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

	private JLabel probToRankLabel;

	private JSlider probToRankSlider;

	private JLabel diversityLabel;

	private JSlider diversitySlider;


	/** The pairing panel. */
	private JPanel pairingPanel;


	/** The right gen panel. */
	private JPanel fitnessPanel;

	/** The genetic label. */
	private JLabel geneticLabel;


	/** The Ok Panel */
	private JPanel okPanel;

	/** The Ok button */
	private JButton okButton;

	/** The Cancel button */
	private JButton cancelButton;


	private JPanel diversityPanel;
	
	
	private JPanel mutationProPanel;
	private JPanel mutationVarPanel;
	private JPanel directFitnessPanel;


	public GeneticGeneratorDialog(JFrame frame, boolean modal, String myMessage, int maxPairing){

		super(frame,modal);

		getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
		
		this.maxPairing = maxPairing;

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);

	}


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
		statesToPairSlider = new JSlider(0, maxPairing, maxPairing/2);
		statesToPairSlider.setSnapToTicks(true);
		statesToPairSlider.setPaintTicks(true);
		statesToPairSlider.setPaintLabels(true);
		statesToPairSlider.setMinorTickSpacing(1);
		statesToPairSlider.setMajorTickSpacing(maxPairing/4);
		statesToPairSlider.setPreferredSize(new Dimension(400,40));

//		mutationCheckBox = new JCheckBox("Mutation");
//		mutationCheckBox.setSelected(true);
//		mutationCheckBox.addActionListener(new MutationListener());

		mutationProbLabel = new JLabel("Mutation probability (%):");
		mutationProbSlider = new JSlider(0, 100, 0);
		mutationProbSlider.setSnapToTicks(true);
		mutationProbSlider.setPaintTicks(true);
		mutationProbSlider.setPaintLabels(true);
		mutationProbSlider.setMinorTickSpacing(1);
		mutationProbSlider.setMajorTickSpacing(10);
		mutationProbSlider.setPreferredSize(new Dimension(400,40));
//		mutationProbSlider.setEnabled(mutationCheckBox.isSelected());
				

		mutationVarLabel = new JLabel("Mutation variation factor:");
		mutationVarField = new JTextField("1.0", 4);
//		mutationVarSlider.setEnabled(mutationCheckBox.isSelected());

		directFitnessCheckBox = new JCheckBox("Direct fitness to probability (%):");
		directFitnessCheckBox.setSelected(true);
		directFitnessCheckBox.addActionListener(new DirectFitnessListener());

		probToRankLabel = new JLabel("Ranking Factor:");
		probToRankSlider = new JSlider(0, 100, 0);
		probToRankSlider.setSnapToTicks(true);
		probToRankSlider.setPaintTicks(true);
		probToRankSlider.setPaintLabels(true);
		probToRankSlider.setMinorTickSpacing(1);
		probToRankSlider.setMajorTickSpacing(10);
		probToRankSlider.setEnabled(directFitnessCheckBox.isSelected());
		probToRankSlider.setPreferredSize(new Dimension(400,40));
	

		diversityLabel = new JLabel("Diversity usage factor (%):");
		diversitySlider = new JSlider(0, 100, 0);
		diversitySlider.setSnapToTicks(true);
		diversitySlider.setPaintTicks(true);
		diversitySlider.setPaintLabels(true);
		diversitySlider.setMinorTickSpacing(1);
		diversitySlider.setMajorTickSpacing(10);
		diversitySlider.setPreferredSize(new Dimension(400,40));

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
			probToRankSlider.setEnabled(directFitnessCheckBox.isSelected());

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
			
			pairingStates = statesToPairSlider.getValue();
			
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
				
				
			setDirectFitnessToProb(directFitnessCheckBox.isSelected());
			
			probToRank = ((double) probToRankSlider.getValue())/100;
			
			diversityUsageFac = ((double) diversitySlider.getValue())/100;
			
			newSettings = true;
			setVisible(false);
			
		}

	}

}
