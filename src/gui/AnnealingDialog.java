package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;

import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JCheckBox;

import mainPackage.SimulatedAnnealingEngine;

public class AnnealingDialog extends JDialog {
	
	
	private SimulatedAnnealingEngine tempAnnealingEngine;

	private final JPanel temperaturePanel = new JPanel();
	private JTextField textFieldInitialValue;
	private JTextField textFieldStopValue;
	private JTextField textFieldTimeOut;
	
	private JCheckBox chckbxTimeOutStop;
	private boolean newAnnealingEngine;


	/**
	 * Create the dialog.
	 */
	public AnnealingDialog(JFrame frame, boolean modal, String myMessage, SimulatedAnnealingEngine annealingEngine) {
		
		super(frame,modal);
		
		this.setTempAnnealingEngine(annealingEngine);
		this.setTitle("Simulated Annealing Settings");
		
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		temperaturePanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		getContentPane().add(temperaturePanel);
		temperaturePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 1, 5));
		{
			JLabel lblTemperature = new JLabel("Temperature Settings:");
			temperaturePanel.add(lblTemperature);
		}
		{
			JPanel valuesPanel = new JPanel();
			valuesPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
			getContentPane().add(valuesPanel);
			{
				JLabel lblInitialValue = new JLabel("Initial Value:");
				valuesPanel.add(lblInitialValue);
			}
			{
				textFieldInitialValue = new JTextField();
				valuesPanel.add(textFieldInitialValue);
				textFieldInitialValue.setColumns(4);
			}
			{
				JLabel lblStopValue = new JLabel("      Stop Value:");
				valuesPanel.add(lblStopValue);
			}
			{
				textFieldStopValue = new JTextField();
				valuesPanel.add(textFieldStopValue);
				textFieldStopValue.setColumns(4);
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
			JPanel variationValuePanel = new JPanel();
			variationValuePanel.setBorder(new EmptyBorder(0, 5, 0, 5));
			getContentPane().add(variationValuePanel);
			variationValuePanel.setLayout(new BoxLayout(variationValuePanel, BoxLayout.X_AXIS));
			{
				JSlider sliderVariatation = new JSlider();
				sliderVariatation.setValue(10);
				sliderVariatation.setMajorTickSpacing(10);
				sliderVariatation.setMinorTickSpacing(1);
				sliderVariatation.setPaintTicks(true);
				sliderVariatation.setPaintLabels(true);
				variationValuePanel.add(sliderVariatation);
			}
		}
		{
			JPanel timeOutPanel = new JPanel();
			timeOutPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
			getContentPane().add(timeOutPanel);
			timeOutPanel.setLayout(new BoxLayout(timeOutPanel, BoxLayout.X_AXIS));
			{
				chckbxTimeOutStop = new JCheckBox("Time Out Stop");
				chckbxTimeOutStop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						textFieldTimeOut.setEnabled(chckbxTimeOutStop.isSelected());
					}
				});
				timeOutPanel.add(chckbxTimeOutStop);
			}
			{
				JLabel lblMinutes = new JLabel("        Minutes:");
				timeOutPanel.add(lblMinutes);
			}
			{
				textFieldTimeOut = new JTextField();
				textFieldTimeOut.setEnabled(false);
				timeOutPanel.add(textFieldTimeOut);
				textFieldTimeOut.setColumns(5);
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
						okButtonPressed();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}

		}
		
		pack();
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}


	public SimulatedAnnealingEngine getTempAnnealingEngine() {
		return tempAnnealingEngine;
	}


	public void setTempAnnealingEngine(SimulatedAnnealingEngine tempAnnealingEngine) {
		this.tempAnnealingEngine = tempAnnealingEngine;
	}


	protected void okButtonPressed() {
		// TODO Auto-generated method stub
		
		newAnnealingEngine = true;
		setVisible(false);
	}


	protected void cancelButtonPressed() {
		// TODO Auto-generated method stub
		
		newAnnealingEngine = false;
		setVisible(false);
		
	}

}
