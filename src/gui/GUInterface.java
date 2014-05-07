package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import com.sun.tools.internal.ws.processor.generator.GeneratorBase;

import mainPackage.Population;
import mainPackage.State;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

// TODO: Auto-generated Javadoc
/**
 * The Class GUInterface.
 */
public class GUInterface {



	Construction[] landuses;
	
	Tile[] tiles;

	State[] states;
	
	Population population;



	/** The frame. */
	private JFrame frame;

	/** The left panel. */
	private JPanel leftPanel; 	

	/** The genetic panel. */
	private JPanel geneticPanel;

	/** The annealing panel. */
	private JPanel annealingPanel;

	/** The exit panel. */
	private JPanel exitPanel;
	
	/** The top panel. */
	private JPanel topPanel;
	
	/** The center panel. */
	private JPanel centerPanel;
	
	/** The population status output */
	private JLabel statusOuputLabel;
	
	/** The generator status output */
	private JLabel genStatusOuputLabel;
	
	/** The run panel. */
	private JPanel runPanel;

	/** The table results panel. */
	private JPanel tableResultsPanel;

	/** The graph results panel. */
	private JPanel graphResultsPanel;
	
	

	/** Genetic Algorithm Label */
	private JLabel geneticLabel;
	
	/** The pop settings button. */
	private JButton popSettingsButton;

	/** The sites settings button. */
	private JButton sitesSettingsButton;

	/** The landuse setting button. */
	private JButton landuseSettingButton;

	/** The state setting button. */
	private JButton stateSettingButton;
	
	/** The genetic generator setting button*/
	private JButton geneticButton;
	
	/** The new problem button. */
	private JButton newProblemButton;

	/** The exit button. */
	private JButton exitButton;
	
	
	
	
	/** The run1 gen btn. */
	private JButton run1GenBtn;

	/** The run10 gen btn. */
	private JButton run10GenBtn;
	
	/** The run100 gen btn. */
	private JButton run100GenBtn;
	
	/** The run1000 gen btn. */
	private JButton run1000GenBtn;

	/** The run btn. */
	private JButton runBtn;
	
	/** The pause btn. */
	private JButton pauseBtn;

	/** The genetic label. */
	private JLabel geneticGenLabel;

	/** The annealing label. */
	private JLabel annealingLabel;



	/** The population dialog. */
	private PopulationDialog populationDialog;

	/** The landuse dialog. */
	private LanduseDialog landuseDialog;

	/** The site dialog. */
	private TileDialog siteDialog;

	/** The state dialog. */
	private StateDialog stateDialog;
	
	private StartDialog startDialog;
	
	private GeneticGeneratorDialog geneticGeneratorDialog;
	
	



	/**
	 * Launch the application.
	 *
	 * @param args the arguments
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
		
		//startNewProblem();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame("Lot Assignment");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(1200, 735));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		createWidgets();
		addWidgets(frame.getContentPane());

		frame.pack();
		frame.setVisible(true);	
		
	}

	
	
	private void startNewProblem() {
		startDialog = new StartDialog(frame, true, "New Problem");
		
		if (startDialog.getNewProblem()) {
			tiles = new Tile[startDialog.getTilesNumber()];
			landuses = new Construction[startDialog.getLandUseNumber()];
			
		}
		
	}

	
	
	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {

		geneticPanel.add(geneticLabel);
		geneticPanel.add(stateSettingButton);
		geneticPanel.add(sitesSettingsButton);
		geneticPanel.add(landuseSettingButton);
		//geneticPanel.add(popSettingsButton);
		geneticPanel.add(geneticButton);
		leftPanel.add(geneticPanel,BorderLayout.NORTH);

		annealingPanel.add(annealingLabel);
		annealingPanel.add(new JLabel(""));
		annealingPanel.add(new JLabel(""));
		annealingPanel.add(new JLabel(""));
		annealingPanel.add(new JLabel(""));
		annealingLabel.add(Box.createVerticalStrut(100));
		leftPanel.add(annealingPanel,BorderLayout.CENTER);

		exitPanel.add(newProblemButton);
		exitPanel.add(exitButton);
		leftPanel.add(exitPanel,BorderLayout.SOUTH);

		contentPane.add(leftPanel,BorderLayout.WEST);
		
		topPanel.add(statusOuputLabel,BorderLayout.NORTH);
		topPanel.add(genStatusOuputLabel,BorderLayout.CENTER);
		
		runPanel.add(run1GenBtn);
		runPanel.add(run10GenBtn);
		runPanel.add(run100GenBtn);
		runPanel.add(run1000GenBtn);
		runPanel.add(runBtn);
		runPanel.add(pauseBtn);	
		topPanel.add(runPanel,BorderLayout.SOUTH);	
		
		
		
		centerPanel.add(topPanel,BorderLayout.NORTH);
		
		centerPanel.add(tableResultsPanel,BorderLayout.CENTER);
		
		contentPane.add(centerPanel,BorderLayout.CENTER);

	}

	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {
		
		//Dimension btnDim = new Dimension(175, 10);

		/* Left Panel and Elements */
		leftPanel = new JPanel();
		leftPanel.setLayout(new BorderLayout(5, 5));
		//leftPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		leftPanel.setMaximumSize(new Dimension(175, 735));

		/* Genetic Algorithm Panel */
		geneticPanel = new JPanel();
		geneticPanel.setLayout(new GridLayout(5,1,1,1));
//		geneticPanel.setLayout(new BoxLayout(geneticPanel,BoxLayout.Y_AXIS));

		geneticLabel = new JLabel("Genetic Algorithms");
		geneticLabel.setHorizontalAlignment(JLabel.CENTER);
		geneticLabel.setVerticalAlignment(JLabel.CENTER);
		geneticPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		stateSettingButton = new JButton("<html><center>State<br>Settings</center></html>");
		stateSettingButton.addActionListener(new StateSettingsListener());

		sitesSettingsButton = new JButton("<html><center>Tiles<br>Settings</center></html>");
		sitesSettingsButton.addActionListener(new SiteSettingsListener());

		landuseSettingButton = new JButton("<html><center>Landuses<br>Settings</center></html>");
		landuseSettingButton.addActionListener(new LanduseSettingsListener());
/*
		popSettingsButton = new JButton("<html><center>Population<br>Settings</center></html>");
		popSettingsButton.addActionListener(new PopulationSettingsListener());
*/		
		geneticButton = new JButton("<html><center>Generator<br>Settings</center></html>");
		geneticButton.addActionListener(new GeneticListener());

		/* Simulated Annealing Panel */
		annealingPanel = new JPanel();
		annealingPanel.setLayout(new GridLayout(6,1,2,2));
		annealingPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		annealingPanel.setMaximumSize(new Dimension (175,250));

		annealingLabel = new JLabel("Simulated Annealing");
		annealingLabel.setHorizontalAlignment(JLabel.CENTER);
		annealingLabel.setVerticalAlignment(JLabel.CENTER);

		/* Top Panel */
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0,0));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		statusOuputLabel = new JLabel("Nr. of Tiles: " /*+ String.valueOf(tiles.length)*/ + 
								  ";   Nr. of Landuses: "/* + String.valueOf(landuses.length)*/ + 
								  ";   Population Size: " /*+ String.valueOf(population.populationSize())*/ +
								  ";   Generation Nr: "/*+ */);
		statusOuputLabel.setBorder(new EmptyBorder(5,5,5,5));
		statusOuputLabel.setVisible(false);
		
		genStatusOuputLabel = new JLabel("Pairing: " /*+ String.valueOf(tiles.length)*/ + 
				  ";   Mutation: "/* + String.valueOf(landuses.length)*/ + 
				  ";   Diversity: " /*+ String.valueOf(population.populationSize())*/ +
				  ";   Probability to Rank: "/*+ */);
		genStatusOuputLabel.setBorder(new EmptyBorder(5,5,5,5));
		genStatusOuputLabel.setVisible(false);
		
		
		/* Center and Top Panels and Elements */
		centerPanel = new JPanel();
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		runPanel = new JPanel();
		runPanel.setLayout(new GridLayout(1, 6));
		runPanel.setBorder(new EmptyBorder(5,5,5,5));
		
		runBtn = new JButton("Run");
		runBtn.addActionListener(new RunListener());
		
		run1GenBtn = new JButton("Run 1 Generation");
		run1GenBtn.addActionListener(new Run1GenListener());
		
		run10GenBtn = new JButton("Run 10 Generations");
		run10GenBtn.addActionListener(new Run10GenListener());
		
		run100GenBtn = new JButton("Run 100 Generations");
		run100GenBtn.addActionListener(new Run100GenListener());
		
		run1000GenBtn = new JButton("Run 1000 Generations");
		run1000GenBtn.addActionListener(new Run1000GenListener());
		
		pauseBtn = new JButton("Pause");
		pauseBtn.addActionListener(new PauseListener());
      
		
		/* Result Panel */
		tableResultsPanel = new JPanel();
		tableResultsPanel.setLayout(new GridLayout(50, 10, 0, 0));

		
		/* Exit Panel */

		exitPanel = new JPanel();
		exitPanel.setLayout(new GridLayout(2,1,5,5));
		exitPanel.setBorder(new EmptyBorder(5,5,5,5));

		newProblemButton = new JButton("New Problem");
		newProblemButton.addActionListener(new NewProblemListener());

		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());


	}

	
	



	/**
	 * The listener interface for receiving run events.
	 * The class that is interested in processing a run
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addRunListener<code> method. When
	 * the run event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see RunEvent
	 */
	public class RunListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
	




	/**
	 * The listener interface for receiving pause events.
	 * The class that is interested in processing a pause
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addPauseListener<code> method. When
	 * the pause event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see PauseEvent
	 */
	public class PauseListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}




	/**
	 * The listener interface for receiving run1000Gen events.
	 * The class that is interested in processing a run1000Gen
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addRun1000GenListener<code> method. When
	 * the run1000Gen event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see Run1000GenEvent
	 */
	public class Run1000GenListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}



	/**
	 * The listener interface for receiving run100Gen events.
	 * The class that is interested in processing a run100Gen
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addRun100GenListener<code> method. When
	 * the run100Gen event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see Run100GenEvent
	 */
	public class Run100GenListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}



	/**
	 * The listener interface for receiving run10Gen events.
	 * The class that is interested in processing a run10Gen
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addRun10GenListener<code> method. When
	 * the run10Gen event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see Run10GenEvent
	 */
	public class Run10GenListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}



	/**
	 * The listener interface for receiving run1Gen events.
	 * The class that is interested in processing a run1Gen
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addRun1GenListener<code> method. When
	 * the run1Gen event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see Run1GenEvent
	 */
	public class Run1GenListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}
	
	

	/**
	 * The listener interface for receiving exit events.
	 * The class that is interested in processing a exit
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addExitListener<code> method. When
	 * the exit event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see ExitEvent
	 */
	public class ExitListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			String exitMsg = "Do you want to quit?";
			int reply = JOptionPane.showConfirmDialog(frame,exitMsg,"Exit",JOptionPane.YES_NO_OPTION);

			if(reply == JOptionPane.YES_OPTION)
			{
				frame.setVisible(false);
				System.exit(0);
			}
			else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}

			// TODO request focus in result panel 
			tableResultsPanel.requestFocusInWindow();

		}

	}

	/**
	 * The listener interface for receiving newProblem events.
	 * The class that is interested in processing a newProblem
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addNewProblemListener<code> method. When
	 * the newProblem event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see NewProblemEvent
	 */
	public class NewProblemListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			startNewProblem();
			topPanel.repaint();
		}

	}



	/**
	 * The listener interface for receiving stateSettings events.
	 * The class that is interested in processing a stateSettings
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addStateSettingsListener<code> method. When
	 * the stateSettings event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see StateSettingsEvent
	 */
	public class StateSettingsListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (tiles == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}
			// TODO Auto-generated method stub

		}

	}




	/**
	 * The listener interface for receiving populationSettings events.
	 * The class that is interested in processing a populationSettings
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addPopulationSettingsListener<code> method. When
	 * the populationSettings event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see PopulationSettingsEvent
	 */
	public class PopulationSettingsListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			populationDialog = new PopulationDialog(frame, true, "Population Settings");
			topPanel.repaint();
		}

	}



	/**
	 * The listener interface for receiving siteSettings events.
	 * The class that is interested in processing a siteSettings
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addSiteSettingsListener<code> method. When
	 * the siteSettings event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see SiteSettingsEvent
	 */
	public class SiteSettingsListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (tiles == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}
			
			int id = 0;
			siteDialog = new TileDialog(frame, true, "Tile Settings", tiles, id);
			
			tiles[id] = siteDialog.getTempTile();
			id = siteDialog.getTileID();
			
			boolean finished = false;
			
			while (!finished){
				
				siteDialog = new TileDialog(frame, true, "Tile Settings", tiles, id);
				if (!siteDialog.isCanceled()){
					tiles[id] = siteDialog.getTempTile();
					id = siteDialog.getTileID();
					finished = siteDialog.isFinished();
				} else {
					break;
				}

			}
						

		}

	}




	/**
	 * The listener interface for receiving landuseSettings events.
	 * The class that is interested in processing a landuseSettings
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addLanduseSettingsListener<code> method. When
	 * the landuseSettings event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see LanduseSettingsEvent
	 */
	public class LanduseSettingsListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (landuses == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}

			int id = 0;
			landuseDialog = new LanduseDialog(frame, true, "Landuse Settings", landuses, id);
			
			landuses[id] = landuseDialog.getTempLanduse();
			id = landuseDialog.getLanduseID();
			
			boolean finished = false;
			
			while (!finished){
				
				landuseDialog = new LanduseDialog(frame, true, "Landuse Settings", landuses, id);
				if (!landuseDialog.isCanceled()){
					landuses[id] = landuseDialog.getTempLanduse();
					id = landuseDialog.getLanduseID();
					finished = landuseDialog.isFinished();
				} else {
					break;
				}

			}

		}

	}
	
	


	
	public class GeneticListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			geneticGeneratorDialog = new GeneticGeneratorDialog(frame, true, "Genetic Generator Settings");
			topPanel.repaint();
			
			

		}

	}


}
