package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.sun.xml.internal.ws.org.objectweb.asm.Label;

import mainPackage.Population;
import mainPackage.Tile;
import mainPackage.constructions.Construction;

// TODO: Auto-generated Javadoc
/**
 * The Class GUInterface.
 */
public class GUInterface {


	/** The landuses. */
	Construction[] landuses;

	/** The tiles. */
	private Tile[] tiles;

	/** The population. */
	private Population population;

	/** The pop size. */
	private int popSize;

	/** The pause. */
	boolean pause = false;

	/* Genetic Generator Settings */
	/** The mutation prob. */
	private double mutationProb = 0.75;
	
	/** The mutation prob var fac. */
	private double mutationProbVarFac = 1.0;
	
	/** The prob to rank. */
	private double probToRank = 0.5;
	
	/** The diversity usage fac. */
	private double diversityUsageFac = 0;
	
	/** The direct fitness to prob. */
	private boolean directFitnessToProb = true;
	
	/** The pairing states. */
	private int pairingStates;



	/** The frame. */
	private JFrame frame;

	/** The left panel. */
	private JPanel leftPanel; 	

	/** The genetic panel. */
	private JPanel geneticPanel;

	/** The exit panel. */
	private JPanel exitPanel;

	/** The top panel. */
	private JPanel topPanel;

	/** The center panel. */
	private JPanel centerPanel;

	/**  The population status output. */
	private JLabel statusOuputLabel;

	/**  The generator status output. */
	private JLabel genStatusOuputLabel;

	/** The run panel. */
	private JPanel runPanel;
	
	

	/** The table results panel. */
	private JPanel resultsPanel;

	/** The graph results panel. */
	private JPanel bestStatisticsPanel;

	/** The solution panel. */
	private JScrollPane bestSolutionPanel;
	/** The solution panel. */
	private JTable bestSolutionTable;
	
	/** The history panel */
	private JPanel historyPanel;


	/**  Genetic Algorithm Label. */
	private JLabel geneticLabel;

	/** The pop settings button. */
	private JButton adjacenciesButton;

	/** The sites settings button. */
	private JButton sitesSettingsButton;

	/** The landuse setting button. */
	private JButton landuseSettingButton;

	/** The state setting button. */
	private JButton restrictionsButton;

	/**  The genetic generator setting button. */
	private JButton geneticButton;

	/** The new problem button. */
	private JButton newProblemButton;
	
	private JButton saveProblemButton;


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

	/** The annealing label. */
	private JLabel annealingLabel;



	/** The population dialog. */
	private AnnealingDialog annealingDialog;

	/** The landuse dialog. */
	private LanduseDialog landuseDialog;

	/** The site dialog. */
	private TileDialog siteDialog;
	
	/**  The Tiles Adjacenties Dialog. */
	private AdjacenciesDialog adjacenciesDialog;

	/** The state dialog. */
	private RestrictionsDialog restrictionsDialog;

	/** The start dialog. */
	private StartDialog startDialog;

	/** The genetic generator dialog. */
	private GeneticGeneratorDialog geneticGeneratorDialog;

	/** The save and load dialog */
	private SaveLoadDialog saveLoadDialog;



	/** Timer */
	private Timer evolutionTimer;
	
	/** Timer rate */
	private int evolutionRate = 1;

	protected int evolutionCount = 0;

	private JLabel bestStateVisualization;



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
		
		evolutionTimer = new Timer(evolutionRate, evolutionListener);

	}





	/**
	 * Start new problem.
	 */
	private void startNewProblem() {


		Construction.resetConstructions();

		if (configPopulation()){
			
			configSites();

			configAdjacencies();

			configLandUses();
			//TODO 
			configRestrictions();

			configGeneticGenerator();

			population = new Population(tiles, landuses, popSize, mutationProb, pairingStates);

			population.coinTosser.setMutationProbability(mutationProb, mutationProbVarFac);
			population.coinTosser.setDiversityUsage(diversityUsageFac);

			if (directFitnessToProb) {
				population.coinTosser.enableDirectMethod();
			} else {
				population.coinTosser.enableFitnessToRank(probToRank);
			}
			
			updateStatusPanel();
			
			createResultWidgets();
			addResultWidgets();
			
			centerPanel.repaint();
		};

		

	}





	private void createResultWidgets() {


		
		/* Best Statistics Panel */
		bestStatisticsPanel = new JPanel();
		bestStatisticsPanel.setLayout(new GridLayout(2,4));
		
		generationBestStateLabel = new JLabel("Generation");
		generationBestStateLabel.setHorizontalTextPosition(JLabel.CENTER);
		//generationBestStateLabel.setPreferredSize(cell);

		
		fitnessBestStateLabel = new JLabel("Fitness");
		fitnessBestStateLabel.setHorizontalTextPosition(JLabel.CENTER);
//		fitnessBestStateLabel.setPreferredSize(cell);
		
		generationBestState = new JLabel("");
		generationBestState.setHorizontalTextPosition(JLabel.CENTER);
		
		fitnessBestState = new JLabel("");
		fitnessBestState.setHorizontalTextPosition(JLabel.CENTER);
		
		/* Best State Panel */
		bestSolutionPanel = new JScrollPane();
		
		int resultsNr = tiles.length;
			
		bestSolutionTable = new JTable(new DefaultTableModel(new String[]{"Site","Landuse"},resultsNr));
		
		for (int i = 0 ; i < resultsNr; i++){
			bestSolutionTable.setValueAt("Lot " + i, i, 0);
		}
		
		bestSolutionPanel.setViewportView(bestSolutionTable);
		
		bestSolutionLabel = new JLabel("Best Solution:");
		bestSolutionLabel.setHorizontalTextPosition(JLabel.LEADING);
		bestSolutionLabel.setBorder(new EmptyBorder(5, 0, 5, 0));

		
		/* Result Panel */
		historyPanel = new JPanel();
		historyPanel.setLayout(new BorderLayout());
		
		bestStateVisualization = new JLabel("");
		
		
	}

	private void addResultWidgets() {
		
		bestStatisticsPanel.add(new JLabel(""));
		bestStatisticsPanel.add(generationBestStateLabel);
		bestStatisticsPanel.add(fitnessBestStateLabel);
		bestStatisticsPanel.add(new JLabel(""));
		bestStatisticsPanel.add(new JLabel(""));
		bestStatisticsPanel.add(generationBestState);
		bestStatisticsPanel.add(fitnessBestState);
		bestStatisticsPanel.add(new JLabel(""));
		
		JPanel temp = new JPanel();
		temp.setLayout(new BorderLayout(5,5));
		temp.add(bestStatisticsPanel,BorderLayout.NORTH);	
		

		historyPanel.add(bestStateVisualization,BorderLayout.CENTER);

		temp.add(historyPanel);
		
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(175,735));
		rightPanel.setLayout(new BoxLayout(rightPanel,BoxLayout.Y_AXIS));
		rightPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			
		rightPanel.add(bestSolutionLabel);
		rightPanel.add(bestSolutionPanel);
		
		resultsPanel.add(rightPanel,BorderLayout.EAST);
		resultsPanel.add(temp,BorderLayout.CENTER);
		
		
	}

	/**
	 * Config restrictions.
	 */
	private void configRestrictions() {
		
		int id = 0;
		restrictionsDialog = new RestrictionsDialog(frame, true, "LandUse Restrictions", landuses, tiles, id);

		
		landuses[id] = restrictionsDialog.getTempLanduse();
		landuses[id].setForbiddenAdjacenciesConstraint(restrictionsDialog.getForbConst(), restrictionsDialog.getForbClassesNames(), restrictionsDialog.getForbPenalty());
		landuses[id].setMustHaveAdjacenciesConstraint(restrictionsDialog.getMustHaveClassesNames(), restrictionsDialog.getMustHaveConst(), restrictionsDialog.getMustHavePenalty());
		
		id = restrictionsDialog.getLanduseID();

		while (!restrictionsDialog.isFinished() && !restrictionsDialog.isCanceled()){

			restrictionsDialog = new RestrictionsDialog(frame, true, "Landuse Settings", landuses, tiles, id);
			if (!restrictionsDialog.isCanceled()){
				landuses[id] = restrictionsDialog.getTempLanduse();
				landuses[id].setForbiddenAdjacenciesConstraint(restrictionsDialog.getForbConst(), restrictionsDialog.getForbClassesNames(), restrictionsDialog.getForbPenalty());
				landuses[id].setMustHaveAdjacenciesConstraint(restrictionsDialog.getMustHaveClassesNames(), restrictionsDialog.getMustHaveConst(), restrictionsDialog.getMustHavePenalty());

				id = restrictionsDialog.getLanduseID();
			} else {
				break;
			}

		}

	}

	/**
	 * Config adjacencies.
	 */
	private void configAdjacencies() {
	
		int id = 0;
		adjacenciesDialog = new AdjacenciesDialog(frame, true, "Adjacencies Settings", getTiles(), id);

		ArrayList <Integer> tempAdj = new ArrayList<Integer>();
		tempAdj = adjacenciesDialog.getAdjacencies();
		
		for (int i = 0; i < tempAdj.size(); i++) {
			tiles[id].addAdjacentTile(tiles[tempAdj.get(i)]);
		}
		
		id = adjacenciesDialog.getTileID();


		while (!adjacenciesDialog.isFinished() && !adjacenciesDialog.isCanceled()){

			adjacenciesDialog = new AdjacenciesDialog(frame, true, "Tile Settings", getTiles(), id);
			if (!adjacenciesDialog.isCanceled()){
				
				tempAdj = adjacenciesDialog.getAdjacencies();
				
				for (int i = 0; i < tempAdj.size(); i++) {
					tiles[id].addAdjacentTile(tiles[tempAdj.get(i)]);
				}
				
				id = adjacenciesDialog.getTileID();
				
			} else {
				break;
			}

		}
		

	}

	/**
	 * Config genetic generator.
	 */
	private void configGeneticGenerator() {

		geneticGeneratorDialog = new GeneticGeneratorDialog(frame, true, "Genetic Generator Settings", popSize/2, pairingStates, mutationProb, mutationProbVarFac, diversityUsageFac, directFitnessToProb, probToRank);

		if (geneticGeneratorDialog.isNewSettings()){
			pairingStates = geneticGeneratorDialog.getPairingStates();
			mutationProb = geneticGeneratorDialog.getMutationProb();

			mutationProbVarFac = geneticGeneratorDialog.getMutationProbVarFac();		
			diversityUsageFac = geneticGeneratorDialog.getDiversityUsageFac();

			if (geneticGeneratorDialog.isDirectFitnessToProb()){
				directFitnessToProb = true;
			} else {
				directFitnessToProb = false;
				probToRank = geneticGeneratorDialog.getProbToRank();
			}
		}	

	}

	/**
	 * Config land uses.
	 */
	private void configLandUses() {

		int id = 0;
		landuseDialog = new LanduseDialog(frame, true, "Landuse Settings", landuses, id);

		landuses[id] = landuseDialog.getTempLanduse();
		id = landuseDialog.getLanduseID();

//		restrictionsDialog = new RestrictionsDialog(frame, true, "LandUse Restrictions", landuses, id);		
//		landuses[id] = restrictionsDialog.getTempLanduse();
		id = landuseDialog.getLanduseID();
		
		
		while (!landuseDialog.isFinished() && !landuseDialog.isCanceled()){

			landuseDialog = new LanduseDialog(frame, true, "Landuse Settings", landuses, id);
			if (!landuseDialog.isCanceled()){
				landuses[id] = landuseDialog.getTempLanduse();
				id = landuseDialog.getLanduseID();
				
//				restrictionsDialog = new RestrictionsDialog(frame, true, "LandUse Restrictions", landuses, id);		
//				landuses[id] = restrictionsDialog.getTempLanduse();
				id = landuseDialog.getLanduseID();
				
			} else {
				break;
			}

		}

	}

	/**
	 * Config sites.
	 */
	private void configSites() {

		int id = 0;
		siteDialog = new TileDialog(frame, true, "Tile Settings", getTiles(), id);
		
		getTiles()[id] = siteDialog.getTempTile();
		id = siteDialog.getTileID();


		while (!siteDialog.isFinished() && !siteDialog.isCanceled()){

			siteDialog = new TileDialog(frame, true, "Tile Settings", getTiles(), id);
			if (!siteDialog.isCanceled()){
				getTiles()[id] = siteDialog.getTempTile();
				id = siteDialog.getTileID();
			} else {
				break;
			}

		}

	}

	/**
	 * Config population.
	 * @return 
	 */
	private boolean configPopulation() {
		startDialog = new StartDialog(frame, true, "New Problem");

		if (startDialog.getNewProblem()) {
			
			setTiles(new Tile[startDialog.getTilesNumber()]);
			for (int i = 0; i < startDialog.getTilesNumber(); i++){
				Tile tempTile = new Tile();
				tiles[i] = tempTile;
			}
			
			landuses = new Construction[startDialog.getLandUseNumber()];
			
			popSize = startDialog.getPopulationSize();
			pairingStates = popSize/2;
			
			return true;
		}	
		
		return false;

	}

	/**
	 * Gets the population.
	 *
	 * @return the population
	 */
	public Population getPopulation() {
		return population;
	}

	/**
	 * Sets the population.
	 *
	 * @param population the new population
	 */
	public void setPopulation(Population population) {
		this.population = population;
	}






	/**
	 * Gets the tiles.
	 *
	 * @return the tiles
	 */
	public Tile[] getTiles() {
		return tiles;
	}

	/**
	 * Sets the tiles.
	 *
	 * @param tiles the new tiles
	 */
	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}


	private void evolution() {
		population.iterate();

		updateStatusPanel();
		updateResultPanel();
		centerPanel.repaint();
	}
	
	private void updateResultPanel() {
		
		if (population.bestStateEver() == null){
			return;
		}
		generationBestState.setText("");
		
		fitnessBestState.setText("");
	
		bestStateVisualization.setText(population.bestStateEver().visualRepresentation());
		
		for (int i = 0 ; i < tiles.length; i++){
			
			bestSolutionTable.setValueAt(population.bestStateEver().constructions[i].name(),i,1);
			
		}
		
		
	}

	ActionListener evolutionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (evolutionCount == 0) {
				evolutionTimer.stop();
			} else {
				evolutionCount--;
				evolution();
			}	
			
		}		
	};

	private JLabel bestSolutionLabel;

	private JLabel generationBestStateLabel;

	private JLabel fitnessBestStateLabel;

	private JLabel fitnessBestState;

	private JLabel generationBestState;
	
	
	/**
	 * Adds the widgets.
	 *
	 * @param contentPane the content pane
	 */
	private void addWidgets(Container contentPane) {

		geneticPanel.add(geneticLabel);
		geneticPanel.add(sitesSettingsButton);
		geneticPanel.add(adjacenciesButton);
		geneticPanel.add(landuseSettingButton);
		geneticPanel.add(restrictionsButton);
		geneticPanel.add(geneticButton);

		geneticPanel.add(annealingLabel);
		geneticPanel.add(new JLabel(""));
		geneticPanel.add(new JLabel(""));

		exitPanel.add(newProblemButton);
		exitPanel.add(saveProblemButton);
		exitPanel.add(exitButton);

		leftPanel.add(geneticPanel);
		leftPanel.add(Box.createVerticalStrut(125));
		leftPanel.add(exitPanel);
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

		centerPanel.add(resultsPanel,BorderLayout.CENTER);

		contentPane.add(centerPanel,BorderLayout.CENTER);

	}

	/**
	 * Creates the widgets.
	 */
	private void createWidgets() {


		/* Left Panel and Elements */
		leftPanel = new JPanel();
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		leftPanel.setMaximumSize(new Dimension(175, 735));

		/* Genetic Algorithm Panel */
		geneticPanel = new JPanel();
		geneticPanel.setLayout(new GridLayout(9,1));

		geneticLabel = new JLabel("Genetic Algorithms");
		geneticLabel.setHorizontalAlignment(JLabel.CENTER);
		geneticLabel.setVerticalAlignment(JLabel.CENTER);
		geneticPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		restrictionsButton = new JButton("<html><center>LandUses<br>Restrictions</center></html>");
		restrictionsButton.addActionListener(new LandUseRestrictionsListener());

		adjacenciesButton = new JButton("<html><center>Adjacencies<br>Settings</center></html>");
		adjacenciesButton.addActionListener(new AdjacenciesSettingsListener());
		 
		sitesSettingsButton = new JButton("<html><center>Tiles<br>Settings</center></html>");
		sitesSettingsButton.addActionListener(new SiteSettingsListener());

		landuseSettingButton = new JButton("<html><center>Landuses<br>Settings</center></html>");
		landuseSettingButton.addActionListener(new LanduseSettingsListener());

		geneticButton = new JButton("<html><center>Generator<br>Settings</center></html>");
		geneticButton.addActionListener(new GeneticListener());

		annealingLabel = new JLabel("Simulated Annealing");
		annealingLabel.setHorizontalAlignment(JLabel.CENTER);
		annealingLabel.setVerticalAlignment(JLabel.CENTER);

		/* Top Panel */
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout(0,0));
		topPanel.setBorder(BorderFactory.createLineBorder(Color.black));

		statusOuputLabel = new JLabel("Nr. of Sites:  ND" + 
				";   Nr. of Landuses: ND" + 
				";   Population Size: ND" +
				";   Nr of Pairing states: ND" +
				";   Generation Nr: ND");
		statusOuputLabel.setBorder(new EmptyBorder(5,5,5,5));
		statusOuputLabel.setVisible(true);

		genStatusOuputLabel = new JLabel("Mutation Probability: ND" +
				";   Mutation Probability Variation Factor: ND" +
				";   Diversity Factor: ND" +
				";   Direct Fitness to Probability: ND" +
				";   Probability to Rank: ND");
		genStatusOuputLabel.setBorder(new EmptyBorder(5,5,5,5));
		genStatusOuputLabel.setVisible(true);


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
		resultsPanel = new JPanel();
		resultsPanel.setLayout(new BorderLayout());
		

		/* Exit Panel */

		exitPanel = new JPanel();
		exitPanel.setLayout(new GridLayout(3,1));
		exitPanel.setBorder(new EmptyBorder(5,5,5,5));
		

		newProblemButton = new JButton("New Problem");
		newProblemButton.addActionListener(new NewProblemListener());
		
		saveProblemButton = new JButton("<html><center>Save/Load<br>Problem</center></html>");
		saveProblemButton.addActionListener(new SaveLoadProblemListener());


		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ExitListener());

	}


	/**
	 * Update status panel.
	 */
	private void updateStatusPanel() {

		statusOuputLabel.setText("Nr. of Sites: " +  tiles.length +
				";   Nr. of Landuses: " + landuses.length +
				";   Population Size: " + population.populationSize() +
				";   Nr of Pairing states: " + population.statesToPair() +
				";   Generation Nr: " + population.getIteration());

		if (directFitnessToProb) {
			genStatusOuputLabel.setText("Mutation Probability: " + String.format("%.2f",population.coinTosser.getMutationProb()) +
					";   Mutation Probability Variation Factor: " + String.format("%.2f",population.coinTosser.getMutationProbVarFac()) +
					";   Diversity Factor: " + String.format("%.2f",population.coinTosser.getDiversityUsageFac()) +
					";   Direct Fitness to Probability: " + population.coinTosser.getDirectFitnessToProb() +
					";   Probability to Rank: N/A");
		} else {
			genStatusOuputLabel.setText("Mutation Probability: " + String.format("%.2f",mutationProb) +
					";   Mutation Probability Variation Factor: " + String.format("%.2f",mutationProbVarFac) +
					";   Diversity Factor: " + String.format("%.2f",population.coinTosser.getDiversityUsageFac()) +
					";   Direct Fitness to Probability: " + population.coinTosser.getDirectFitnessToProb() +
					";   Probability to Rank: " + String.format("%.2f",population.coinTosser.getProbToRank()));
		}

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
			
			if (population == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}

			//evolutionRate = TODO getTimerRate(); 
			evolutionCount = -1;
			
			evolutionTimer.start();
			

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
			
			evolutionCount = 0;

			evolutionTimer.stop();
			
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
			
			if (population == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}

			evolutionCount = 1000;
			evolutionTimer.start();
			

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
			
			if (population == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}
			
			evolutionCount = 100;
			evolutionTimer.start();


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
			
			if (population == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}
			
			evolutionCount = 10;
			evolutionTimer.start();

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
			
			if (population == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}
			
			evolutionCount = 1;
			evolutionTimer.start();

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
//			resultsPanel.requestFocusInWindow();

		}

	}
	
	

	public class SaveLoadProblemListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// JDialog - SAVE/LOAD GAME
			saveLoadDialog = new SaveLoadDialog(frame, true, "Save/Load Problem",population);

			if(saveLoadDialog.problemSaved())
			{
					JOptionPane.showMessageDialog(frame,"Problem saved.");	
			} 
			else if (saveLoadDialog.problemLoaded())
			{
				population = saveLoadDialog.getTempPopulation();
				tiles = population.tiles();
				landuses = population.getLandUses();
				
				pairingStates = population.statesToPair();
				popSize = population.populationSize();
				mutationProb = population.coinTosser.getMutationProb();
				mutationProbVarFac = population.coinTosser.getMutationProbVarFac();
				diversityUsageFac = population.coinTosser.getDiversityUsageFac();
				directFitnessToProb = population.coinTosser.getDirectFitnessToProb();
				probToRank = population.coinTosser.getProbToRank();
				
				resultsPanel.removeAll();
				createResultWidgets();
				addResultWidgets();
				
			}

			saveLoadDialog.setSaveProblem(false);
			saveLoadDialog.setLoadProblem(false);
			
			updateStatusPanel();
			updateResultPanel();
			
			centerPanel.repaint();

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
			
			if (population == null){
				startNewProblem();

			} else {
				
				String exitMsg = "Current population will be lost. Do you want to continue?";
				int reply = JOptionPane.showConfirmDialog(frame,exitMsg,"Exit",JOptionPane.YES_NO_OPTION);

				if(reply == JOptionPane.YES_OPTION)
				{

					population = null;
					tiles = null;
					landuses = null;
					
					startNewProblem();
					
				}
				else if(reply == JOptionPane.NO_OPTION || reply == JOptionPane.CLOSED_OPTION){}
				
			}
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
	public class LandUseRestrictionsListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if (getTiles() == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}
			configRestrictions();
			updateStatusPanel();

			centerPanel.repaint();

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
	public class AdjacenciesSettingsListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (getTiles() == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}
			
			configAdjacencies();
			updateStatusPanel();

			centerPanel.repaint();
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

			if (getTiles() == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}

			configSites();
			updateStatusPanel();

			centerPanel.repaint();

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

			configLandUses();

			centerPanel.repaint();

		}

	}





	/**
	 * The listener interface for receiving genetic events.
	 * The class that is interested in processing a genetic
	 * event implements this interface, and the object created
	 * with that class is registered with a component using the
	 * component's <code>addGeneticListener<code> method. When
	 * the genetic event occurs, that object's appropriate
	 * method is invoked.
	 *
	 * @see GeneticEvent
	 */
	public class GeneticListener implements ActionListener {

		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {

			if (population == null){
				JOptionPane.showMessageDialog(frame, "You need to start a new problem.");
				return;
			}

			configGeneticGenerator();

			population.coinTosser.setMutationProbability(mutationProb, mutationProbVarFac);
			population.coinTosser.setDiversityUsage(diversityUsageFac);

			if (directFitnessToProb) {
				population.coinTosser.enableDirectMethod();
			} else {
				population.coinTosser.enableFitnessToRank(probToRank);
			}

			updateStatusPanel();
			centerPanel.repaint();

		}

	}


}
