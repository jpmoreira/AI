package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GeneticStopDialog extends JDialog {

	private final JPanel geneticStopPanel = new JPanel();


	/**
	 * Create the dialog.
	 * @param string 
	 * @param b 
	 * @param frame 
	 */
	public GeneticStopDialog(JFrame frame, boolean modal, String string) {
		super(frame, modal);
		
		this.setTitle("Genetic Stop Conditions");
		getContentPane().setLayout(new BorderLayout());
		
		geneticStopPanel.setLayout(new FlowLayout());
		geneticStopPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(geneticStopPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		pack();
		setLocationRelativeTo(frame);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

}
