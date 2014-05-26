package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

import org.w3c.dom.ranges.RangeException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GeneticStopDialog extends JDialog {

	private final JPanel geneticStopPanel = new JPanel();
	private JTextField textFieldMinStop;
	private JTextField textFieldGenStop;
	private JCheckBox chckbxTimeOut;
	private JCheckBox chckbxGenerationsStop;
	
	private boolean newStopConditions = false;
	private int generationsStop;
	private int minutesStop;


	/**
	 * Create the dialog.
	 * @param string 
	 * @param b 
	 * @param frame 
	 */
	public GeneticStopDialog(JFrame frame, boolean modal, String string) {
		super(frame, modal);
		
		this.setTitle("Genetic Stop Conditions");
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		geneticStopPanel.setBorder(new EmptyBorder(5, 5, 0, 5));
		getContentPane().add(geneticStopPanel);
		geneticStopPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		{
			JLabel lblDiferenceBetweenBest = new JLabel("Diference between Best State Fitness and Population Mean Fitness (%):");
			geneticStopPanel.add(lblDiferenceBetweenBest);
		}
		{
			JPanel variationPanel = new JPanel();
			variationPanel.setBorder(new EmptyBorder(0, 5, 0, 5));
			getContentPane().add(variationPanel);
			variationPanel.setLayout(new BoxLayout(variationPanel, BoxLayout.X_AXIS));
			{
				JSlider slider = new JSlider();
				slider.setMaximum(50);
				slider.setValue(15);
				slider.setMajorTickSpacing(10);
				slider.setMinorTickSpacing(1);
				slider.setSnapToTicks(true);
				slider.setPaintLabels(true);
				slider.setPaintTicks(true);
				variationPanel.add(slider);
			}
		}
		{
			JPanel genStopPanel = new JPanel();
			genStopPanel.setBorder(new EmptyBorder(0, 25, 0, 25));
			getContentPane().add(genStopPanel);
			genStopPanel.setLayout(new BoxLayout(genStopPanel, BoxLayout.X_AXIS));
			{
				chckbxGenerationsStop = new JCheckBox("Generations Stop");
				chckbxGenerationsStop.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					textFieldGenStop.setEnabled(chckbxGenerationsStop.isSelected());	
					}
				});
				genStopPanel.add(chckbxGenerationsStop);
			}
			{
				JLabel lblGenerationsStop = new JLabel("           Nr. Generations");
				genStopPanel.add(lblGenerationsStop);
			}
			{
				textFieldGenStop = new JTextField();
				textFieldGenStop.setEnabled(chckbxGenerationsStop.isSelected());
				genStopPanel.add(textFieldGenStop);
				textFieldGenStop.setColumns(3);
			}
		}
		{
			JPanel timeOutPanel = new JPanel();
			timeOutPanel.setBorder(new EmptyBorder(0, 25, 5, 25));
			getContentPane().add(timeOutPanel);
			timeOutPanel.setLayout(new BoxLayout(timeOutPanel, BoxLayout.X_AXIS));
			{
				chckbxTimeOut = new JCheckBox("Time Out Stop");
				chckbxTimeOut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						textFieldMinStop.setEnabled(chckbxTimeOut.isSelected());
					}
				});
				timeOutPanel.add(chckbxTimeOut);
			}
			{
				JLabel lblMinutes = new JLabel("        Minutes:");
				timeOutPanel.add(lblMinutes);
			}
			{
				textFieldMinStop = new JTextField();
				textFieldMinStop.setEnabled(chckbxTimeOut.isSelected());
				timeOutPanel.add(textFieldMinStop);
				textFieldMinStop.setColumns(3);
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


	public boolean isNewStopConditions() {
		return newStopConditions;
	}


	public void setNewStopConditions(boolean newStopConditions) {
		this.newStopConditions = newStopConditions;
	}


	public int getGenerationsStop() {
		return generationsStop;
	}


	public void setGenerationsStop(int generationsStop) {
		this.generationsStop = generationsStop;
	}


	public int getMinutesStop() {
		return minutesStop;
	}


	public void setMinutesStop(int minutesStop) {
		this.minutesStop = minutesStop;
	}


	private void editSettings() throws NumberFormatException{
		// TODO Auto-generated method stub
		
		if (chckbxTimeOut.isSelected()){
			int tempMin;
			tempMin = Integer.parseInt(textFieldMinStop.getText());
			if (tempMin <= 0) throw new RangeException(RangeException.BAD_BOUNDARYPOINTS_ERR, "min");
			
		}
		
		if (chckbxGenerationsStop.isSelected()) {
			int tempGen;
			tempGen = Integer.parseInt(textFieldGenStop.getText());
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
