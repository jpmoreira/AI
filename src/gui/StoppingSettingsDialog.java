package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import mainPackage.GeneticEngine;
import mainPackage.SimulatedAnnealingEngine;

import org.w3c.dom.ranges.RangeException;

import com.sun.org.apache.bcel.internal.generic.ANEWARRAY;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.border.MatteBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JSeparator;

public class StoppingSettingsDialog extends JDialog {


	
	private boolean newStopConditions = false;
	
	
	
	
	private GeneticEngine geneticEngine;
	private SimulatedAnnealingEngine annealingEngine;
	
	
	private int geneticIterations;
	private int geneticUnimproves;
	private double geneticMinFitness;
	private double geneticVariations;
	

	
	private JCheckBox geneticMaxIterationsChBx;
	private JTextField geneticMaxIterationsTxtFld;
	
	private JCheckBox geneticUnimprovedChBx;
	private JTextField geneticUnimproveTxtFld;

	private JCheckBox geneticMaxFitnessChBx;
	private JSlider geneticFitnessSlider = new JSlider();
	
	private JCheckBox geneticVariationChBx;
	private JSlider geneticVariationSlider = new JSlider();
	
	
	
	private JCheckBox annealingMaxIterationsChBx;
	private JTextField annealingMaxIterationsTxtFld;
	
	private JCheckBox annealingUnimprovedChBx;
	private JTextField annealingUnimproveTxtFld;
	
	private JCheckBox annealingMaxFitnessChBx;
	private JSlider annealingFitnessSlider = new JSlider();

	private JCheckBox annealingVariationChBx;
	protected JSlider annealingVariationSlider = new JSlider();


	/**
	 * Create the dialog.
	 * @param string 
	 * @param b 
	 * @param frame 
	 * @param annealingEngine 
	 * @param geneticEngine 
	 */
	public StoppingSettingsDialog(JFrame frame, boolean modal, String string, GeneticEngine geneticEngine, SimulatedAnnealingEngine annealingEngine) {
		super(frame, modal);
		
		this.setTitle("Stopping Settings");
		
		this.geneticEngine = geneticEngine;
		this.annealingEngine = annealingEngine;
		
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		createWdgets();
		
		
		lockPanels();
		
		pack();
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}


	private void createWdgets() {
		
		{
			JPanel geneticPanel = new JPanel();
			getContentPane().add(geneticPanel);
			geneticPanel.setLayout(new BoxLayout(geneticPanel, BoxLayout.Y_AXIS));
			{
				JPanel geneticIDPanel = new JPanel();
				geneticIDPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
				geneticPanel.add(geneticIDPanel);
				{
					JLabel lblGeneticAlgorithm = new JLabel("Genetic Algorithm");
					geneticIDPanel.add(lblGeneticAlgorithm);
				}
			}
			{
				JPanel genStopPanel = new JPanel();
				geneticPanel.add(genStopPanel);
				genStopPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
				{
					geneticMaxIterationsChBx = new JCheckBox("Iterations Without Evolve");
					geneticMaxIterationsChBx.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						geneticMaxIterationsTxtFld.setEnabled(geneticMaxIterationsChBx.isSelected());	
						}
					});
					genStopPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
					genStopPanel.add(geneticMaxIterationsChBx);
				}
				{
					geneticMaxIterationsTxtFld = new JTextField();
					geneticMaxIterationsTxtFld.setEnabled(geneticMaxIterationsChBx.isSelected());
					genStopPanel.add(geneticMaxIterationsTxtFld);
					geneticMaxIterationsTxtFld.setColumns(5);
				}
				geneticUnimprovedChBx = new JCheckBox("Iterations Without Improve");
				genStopPanel.add(geneticUnimprovedChBx);

				{
					geneticUnimproveTxtFld = new JTextField();
					geneticUnimproveTxtFld.setEnabled(geneticUnimprovedChBx.isSelected());
					genStopPanel.add(geneticUnimproveTxtFld);
					geneticUnimproveTxtFld.setColumns(5);
				}
				geneticUnimprovedChBx.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						geneticUnimproveTxtFld.setEnabled(geneticUnimprovedChBx.isSelected());
					}
				});
			}
			{
				JPanel geneticMinImprovePanel = new JPanel();
				geneticPanel.add(geneticMinImprovePanel);
				geneticMinImprovePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
				geneticMinImprovePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
				{
					geneticMaxFitnessChBx = new JCheckBox("Minimun Fitness for Best State (%)");
					geneticMaxFitnessChBx.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							geneticFitnessSlider.setEnabled(geneticMaxFitnessChBx.isSelected());
						}
					});
					geneticMinImprovePanel.add(geneticMaxFitnessChBx);
				}
			}
			{
				JPanel geneticFitnessPanel = new JPanel();
				geneticFitnessPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
				geneticPanel.add(geneticFitnessPanel);
				geneticFitnessPanel.setLayout(new BoxLayout(geneticFitnessPanel, BoxLayout.X_AXIS));
				{
					geneticFitnessSlider.setMaximum(100);
					geneticFitnessSlider.setValue(90);
					geneticFitnessSlider.setMajorTickSpacing(10);
					geneticFitnessSlider.setMinorTickSpacing(1);
					geneticFitnessSlider.setSnapToTicks(true);
					geneticFitnessSlider.setPaintLabels(true);
					geneticFitnessSlider.setPaintTicks(true);
					geneticFitnessSlider.setEnabled(geneticMaxFitnessChBx.isSelected());
					geneticFitnessPanel.add(geneticFitnessSlider);
				}
			}
			JPanel geneticDiferencePanel = new JPanel();
			geneticPanel.add(geneticDiferencePanel);
			geneticDiferencePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
			geneticDiferencePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
			{
				geneticVariationChBx = new JCheckBox("Diference between Best State Fitness and Population Mean Fitness (%)");
				geneticVariationChBx.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						geneticVariationSlider.setEnabled(geneticVariationChBx.isSelected());
					}
				});
				geneticDiferencePanel.add(geneticVariationChBx);
			}
			{
				JPanel genVariationPanel = new JPanel();
				geneticPanel.add(genVariationPanel);
				genVariationPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
				genVariationPanel.setLayout(new BoxLayout(genVariationPanel, BoxLayout.X_AXIS));
				{
					geneticVariationSlider.setMaximum(50);
					geneticVariationSlider.setValue(15);
					geneticVariationSlider.setMajorTickSpacing(10);
					geneticVariationSlider.setMinorTickSpacing(1);
					geneticVariationSlider.setSnapToTicks(true);
					geneticVariationSlider.setPaintLabels(true);
					geneticVariationSlider.setPaintTicks(true);
					geneticVariationSlider.setEnabled(geneticVariationChBx.isSelected());
					genVariationPanel.add(geneticVariationSlider);
				}
			}
		}
		
		
		
		
		{
			JSeparator separator = new JSeparator();
			getContentPane().add(separator);
		}
		
		
		
		
		
		{
			JPanel annealingPanel = new JPanel();
			annealingPanel.setBorder(new EmptyBorder(0, 0, 10, 0));
			getContentPane().add(annealingPanel);
			annealingPanel.setLayout(new BoxLayout(annealingPanel, BoxLayout.Y_AXIS));
			{
				JPanel annealingIDPanel = new JPanel();
				annealingIDPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
				annealingPanel.add(annealingIDPanel);
				{
					JLabel lblAnnealingAlgorithm = new JLabel("Simulated Annealing Algorithm");
					annealingIDPanel.add(lblAnnealingAlgorithm);
				}
			}
			
			{
				JPanel annStopPanel = new JPanel();
				annealingPanel.add(annStopPanel);
				annStopPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
				
				{
					annealingMaxIterationsChBx = new JCheckBox("Max. Iterations");
					annealingMaxIterationsChBx.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
						annealingMaxIterationsTxtFld.setEnabled(annealingMaxIterationsChBx.isSelected());	
						}
					});
					annStopPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 0));
					annStopPanel.add(annealingMaxIterationsChBx);
				}
				
				{
					annealingMaxIterationsTxtFld = new JTextField();
					annealingMaxIterationsTxtFld.setEnabled(annealingMaxIterationsChBx.isSelected());
					annStopPanel.add(annealingMaxIterationsTxtFld);
					annealingMaxIterationsTxtFld.setColumns(5);
				}
				
				annealingUnimprovedChBx = new JCheckBox("Iterations Without Improve");
				annStopPanel.add(annealingUnimprovedChBx);
				geneticUnimproveTxtFld.setEnabled(annealingUnimprovedChBx.isSelected());
				
				{
					annealingUnimproveTxtFld = new JTextField();
					annealingUnimproveTxtFld.setEnabled(annealingUnimprovedChBx.isSelected());
					annStopPanel.add(annealingUnimproveTxtFld);
					annealingUnimproveTxtFld.setColumns(5);
				}
				
				annealingUnimprovedChBx.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						annealingUnimproveTxtFld.setEnabled(annealingUnimprovedChBx.isSelected());
					}
				});
			}
			
			{
				JPanel annealingMinImprovePanel = new JPanel();
				annealingPanel.add(annealingMinImprovePanel);
				annealingMinImprovePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
				annealingMinImprovePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
				{
					annealingMaxFitnessChBx = new JCheckBox("Minimun Fitness for Best State (%)");
					annealingMaxFitnessChBx.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							annealingFitnessSlider.setEnabled(annealingMaxFitnessChBx.isSelected());
						}
					});
					annealingMinImprovePanel.add(annealingMaxFitnessChBx);
				}
			}
			{
				JPanel annealingFitnessPanel = new JPanel();
				annealingFitnessPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
				annealingPanel.add(annealingFitnessPanel);
				annealingFitnessPanel.setLayout(new BoxLayout(annealingFitnessPanel, BoxLayout.X_AXIS));
				{
					annealingFitnessSlider.setMaximum(100);
					annealingFitnessSlider.setValue(90);
					annealingFitnessSlider.setMajorTickSpacing(10);
					annealingFitnessSlider.setMinorTickSpacing(1);
					annealingFitnessSlider.setSnapToTicks(true);
					annealingFitnessSlider.setPaintLabels(true);
					annealingFitnessSlider.setPaintTicks(true);
					annealingFitnessSlider.setEnabled(annealingMaxFitnessChBx.isSelected());
					annealingFitnessPanel.add(annealingFitnessSlider);
				}
			}	
			
			JPanel annealingDiferencePanel = new JPanel();
			annealingPanel.add(annealingDiferencePanel);
			annealingDiferencePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
			annealingDiferencePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 5));
			{
				annealingVariationChBx = new JCheckBox("Diference between Best State Fitness and Population Mean Fitness (%)");
				annealingVariationChBx.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						annealingVariationSlider.setEnabled(annealingVariationChBx.isSelected());
					}
				});
				annealingDiferencePanel.add(annealingVariationChBx);
			}
			
			
			{
				JPanel annVariationPanel = new JPanel();
				annealingPanel.add(annVariationPanel);
				annVariationPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
				annVariationPanel.setLayout(new BoxLayout(annVariationPanel, BoxLayout.X_AXIS));
				{
					annealingVariationSlider.setMaximum(50);
					annealingVariationSlider.setValue(15);
					annealingVariationSlider.setMajorTickSpacing(10);
					annealingVariationSlider.setMinorTickSpacing(1);
					annealingVariationSlider.setSnapToTicks(true);
					annealingVariationSlider.setPaintLabels(true);
					annealingVariationSlider.setPaintTicks(true);
					annealingVariationSlider.setEnabled(annealingVariationChBx.isSelected());
					annVariationPanel.add(annealingVariationSlider);
				}
			}
			
			
			
			
			
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelButtonPressed();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okButtonPressed();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}


	private void lockPanels() {
		
		if (geneticEngine == null ){
			
			geneticMaxIterationsChBx.setEnabled(false);
			geneticMaxIterationsChBx.setSelected(false);
			
			geneticMaxIterationsTxtFld.setEnabled(false);
			
			geneticUnimprovedChBx.setEnabled(false);
			geneticUnimprovedChBx.setSelected(false);
			
			geneticUnimproveTxtFld.setEnabled(false);
			
			geneticMaxFitnessChBx.setEnabled(false);
			geneticMaxFitnessChBx.setSelected(false);
			
			geneticFitnessSlider.setEnabled(false);
			
			geneticVariationChBx.setEnabled(false);
			geneticVariationChBx.setSelected(false);
			
			geneticVariationSlider.setEnabled(false);
			
		}
		
		if (annealingEngine == null){
			
			annealingMaxIterationsChBx.setEnabled(false);
			annealingMaxIterationsTxtFld.setEnabled(false);
			annealingUnimprovedChBx.setEnabled(false);
			annealingUnimproveTxtFld.setEnabled(false);
			annealingMaxFitnessChBx.setEnabled(false);
			annealingFitnessSlider.setEnabled(false);
			annealingVariationChBx.setEnabled(false);
			annealingVariationSlider.setEnabled(false);
			
		}
		
	}


	public boolean isNewStopConditions() {
		return newStopConditions;
	}


	public void setNewStopConditions(boolean newStopConditions) {
		this.newStopConditions = newStopConditions;
	}


	private void editSettings() throws NumberFormatException{
		// TODO Auto-generated method stub
		
		if (geneticUnimprovedChBx.isSelected()){
			
			int tempMin;
			tempMin = Integer.parseInt(geneticUnimproveTxtFld.getText());
			if (tempMin <= 0) throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "min");
			
		}
		
		if (geneticMaxIterationsChBx.isSelected()) {
			int tempGen;
			tempGen = Integer.parseInt(geneticMaxIterationsTxtFld.getText());
			
			if (tempGen <= 0)
				throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "gen");
			
		}
		
		
		
		
	}
	
	protected void okButtonPressed() {
		// TODO Auto-generated method stub
		try {
			
			editSettings();
			
			
		} catch (NumberFormatException n) {
			JOptionPane.showMessageDialog(getParent(), "Input must be bigger than 0.");
			return;
		} catch (RangeException r) {
			if (r.getMessage().equals("gen")){
				JOptionPane.showMessageDialog(getParent(), "Number of generations must be bigger than 0.");
			} else if (r.getMessage().equals("min")){
				JOptionPane.showMessageDialog(getParent(), "Number of minutes must be bigger than 0.");
			}
			return;
		}
		
		
		setNewStopConditions(true);
		setVisible(false);
		
		
	}





	protected void cancelButtonPressed() {
		// TODO Auto-generated method stub
		setNewStopConditions(false);
		setVisible(false);
		
	}

}
