package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GeneticGeneratorDialog extends JDialog{

	
	
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
		
		centerGenPanel.add(mutationCheckBox);
		centerGenPanel.add(new JLabel(""));
		centerGenPanel.add(mutationProbLabel);
		centerGenPanel.add(mutationProbTextField);
		centerGenPanel.add(mutationVarLabel);
		centerGenPanel.add(mutationVarTextField);
		//topPanel.add(centerGenPanel,BorderLayout.CENTER);
		
		rightGenPanel.add(directFitnessCheckBox);
		rightGenPanel.add(new JLabel(""));
		rightGenPanel.add(probToRankLabel);
		rightGenPanel.add(probToRankLTextField);
		rightGenPanel.add(diversityLabel);
		rightGenPanel.add(diversityTextField);
		
		JPanel temp = new JPanel();
		temp.setLayout(new GridLayout(1,3,5,5));
		
		temp.add(leftGenPanel);
		temp.add(centerGenPanel);
		temp.add(rightGenPanel);
		//temp.add(new JLabel(""));
		//temp.setAlignmentY(GridLayout.LEADING);
		
		//contentPane.add(geneticGenLabel,BorderLayout.NORTH);
		contentPane.add(temp,BorderLayout.CENTER);
		
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
        
        statesToPairTextField = new JTextField(4);
        statesToPairTextField.setEditable(false);
        
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
        probToRankLTextField.setEditable(false);
        
        diversityLabel = new JLabel("Diversity usage factor");
        diversityTextField = new JTextField(4);
        diversityTextField.setEditable(false);
        
		
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
	
	
}
