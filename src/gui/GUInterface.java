package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUInterface {





	private JFrame frame;
	
	private JPanel settingsPanel; 
	
	private JButton popSettingsButton;
	
	private JButton sitesSettingsButton;
	
	private JButton landuseSettingButton;
	
	private JButton stateSettingButton;
	
	private JPanel navigationPanel;
	
	private JPanel tableResultsPanel;
	
	private JPanel graphResultsPanel;
	
	private PopulationDialog populationDialog;
	
	private LanduseDialog landuseDialog;
	
	private SiteDialog siteDialog;
	
	private StateDialog stateDialog;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUInterface window = new GUInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame("Lot Assignment");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(800, 735));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		createWidgets();
		addWidgets(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);	
	}

	private void addWidgets(Container contentPane) {
		
		
		
	}

	private void createWidgets() {
		
		/* Settings Panel and Elements */
		settingsPanel = new JPanel();
		settingsPanel.setLayout(new GridLayout(0, 1, 5, 5));
		
		stateSettingButton = new JButton("State Settings");
		stateSettingButton.addActionListener(new StateSettingsListener());
		
		sitesSettingsButton = new JButton("Sites Settings");
		sitesSettingsButton.addActionListener(new SiteSettingsListener());
		
		landuseSettingButton = new JButton("Landuses Settings");
		landuseSettingButton.addActionListener(new LanduseSettingsListener());

		popSettingsButton = new JButton("Population Settings");
		popSettingsButton.addActionListener(new PopulationSettingsListener());
		
		
		/* Navigation Panel and Elements */
		navigationPanel = new JPanel();
		navigationPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		
		
	}

	
	



	public class StateSettingsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	
	
	
	public class PopulationSettingsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	
	
	public class SiteSettingsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}




	public class LanduseSettingsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}



	
}
