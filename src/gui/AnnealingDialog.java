package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import mainPackage.SimulatedAnnealingEngine;

import org.w3c.dom.ranges.RangeException;

import javax.swing.JSpinner;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AnnealingDialog extends JDialog {
	
	Integer value;
	
	private double initialTemperature;
	private double temperatureVariation;

	private final JPanel temperaturePanel = new JPanel();

	private boolean newAnnealingEngine;
	private JSpinner temperatureSpinner;

	private JPanel variationValuePanel;

	private JSlider sliderVariatation;


	/**
	 * Create the dialog.
	 */
	public AnnealingDialog(JFrame frame, boolean modal, String string, SimulatedAnnealingEngine annealingEngine) {
		super(frame,modal);
		
		this.value = (Integer) (int)annealingEngine.getCurrentTemperature();
		this.initialTemperature = annealingEngine.getCurrentTemperature();
		this.temperatureVariation = annealingEngine.getVariationFactor();

		this.setTitle("Simulated Annealing Settings");

		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		createWidgets();

		pack();
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}



	private void createWidgets() {
		temperaturePanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		getContentPane().add(temperaturePanel);
		temperaturePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 5));
		{
			JLabel lblTemperature = new JLabel("Temperature Settings:");
			temperaturePanel.add(lblTemperature);
		}
		{
			JPanel valuesPanel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) valuesPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			valuesPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
			getContentPane().add(valuesPanel);
			{
				JLabel lblInitialValue = new JLabel("Initial Value:");
				valuesPanel.add(lblInitialValue);
			}
			{
				Integer min = new Integer(0);
				Integer max = new Integer(10000000);
				Integer step = new Integer(1);
				SpinnerNumberModel numberSpinner = new SpinnerNumberModel(value, min, max, step);
				
				temperatureSpinner = new JSpinner(numberSpinner);
				valuesPanel.add(temperatureSpinner);


			}
		}
		{
			JPanel variationPanel = new JPanel();
			variationPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
			FlowLayout flowLayout = (FlowLayout) variationPanel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			getContentPane().add(variationPanel);
			{
				JLabel lblNewLabel = new JLabel("Variation Value (%):");
				variationPanel.add(lblNewLabel);
			}
		}
		{
			variationValuePanel = new JPanel();
			variationValuePanel.setBorder(new EmptyBorder(0, 5, 10, 5));
			getContentPane().add(variationValuePanel);
			variationValuePanel.setLayout(new BoxLayout(variationValuePanel, BoxLayout.X_AXIS));
			{
				sliderVariatation = new JSlider();
				sliderVariatation.setSnapToTicks(true);
				sliderVariatation.setValue((int) (temperatureVariation*100));
				sliderVariatation.setMajorTickSpacing(10);
				sliderVariatation.setMinorTickSpacing(1);
				sliderVariatation.setPaintTicks(true);
				sliderVariatation.setPaintLabels(true);
				sliderVariatation.setPreferredSize(new Dimension(500,40));
				variationValuePanel.add(sliderVariatation);
			}
		}

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
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
						try {

							okButtonPressed();

						} catch (NumberFormatException n){
								JOptionPane.showMessageDialog(getParent(), "Input must be a number bigger than 0.");
						} catch (RangeException r) {
							if (r.getMessage().equals("stop")){
								JOptionPane.showMessageDialog(getParent(), "Stop value must be bigger than 0.");
							} else if (r.getMessage().equals("initial")){
								JOptionPane.showMessageDialog(getParent(), "Initial value must be bigger than stop value.");
							}
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}

		}
	}



	public boolean hasNewAnnealingEngine() {
		return newAnnealingEngine;
	}


	public void setNewAnnealingEngine(boolean newAnnealingEngine) {
		this.newAnnealingEngine = newAnnealingEngine;
	}


	private void okButtonPressed() throws NumberFormatException, RangeException{

		try {
			
			initialTemperature = (Integer) temperatureSpinner.getModel().getValue();
			
			temperatureVariation = ((double) sliderVariatation.getValue() / 100);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(getParent(), "Input Error.");
			return;
		}
		
		setNewAnnealingEngine(true);
		setVisible(false);
	}


	protected void cancelButtonPressed() {

		setNewAnnealingEngine(false);
		setVisible(false);

	}



	public double getInitialTemperature() {
		return initialTemperature;
	}



	public double getVariationFactor() {
		return temperatureVariation;
	}

}
