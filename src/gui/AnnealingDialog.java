package gui;

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
import javax.swing.border.EmptyBorder;

import mainPackage.SimulatedAnnealingEngine;

import org.w3c.dom.ranges.RangeException;

public class AnnealingDialog extends JDialog {
	
	private double initTemp;
	private int tempVariation;

	private final JPanel temperaturePanel = new JPanel();
	private JTextField textFieldInitialValue;
	private JTextField textFieldStopValue;
	private JTextField textFieldTimeOut;

	private JCheckBox chckbxTimeOutStop;
	private boolean newAnnealingEngine;


	/**
	 * Create the dialog.
	 */
	public AnnealingDialog(JFrame frame, boolean modal, String myMessage, double initTemp, double tempVariation) {

		super(frame,modal);

		this.initTemp = initTemp;
		this.tempVariation = (int) (tempVariation*100);
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
				sliderVariatation.setSnapToTicks(true);
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
				textFieldTimeOut.setText("5");
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

		pack();
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}



	public boolean isNewAnnealingEngine() {
		return newAnnealingEngine;
	}


	public void setNewAnnealingEngine(boolean newAnnealingEngine) {
		this.newAnnealingEngine = newAnnealingEngine;
	}


	private void okButtonPressed() throws NumberFormatException, RangeException{
		// TODO Auto-generated method stub


		double tempInitial;
		double tempStop;

		tempInitial = Double.parseDouble(textFieldInitialValue.getText());
		tempStop = Double.parseDouble(textFieldStopValue.getText());

		if (tempStop < 0) throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR,"stop");
		if (tempInitial <= tempStop) throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR,"initial");

		setNewAnnealingEngine(true);
		setVisible(false);
	}


	protected void cancelButtonPressed() {
		// TODO Auto-generated method stub

		setNewAnnealingEngine(false);
		setVisible(false);

	}

}
