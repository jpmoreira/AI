package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mainPackage.Tile;
import mainPackage.Tile.SoilType;

public class TileDialog extends JDialog {

	private int tileID = 0;
	
	private Tile[] tiles;
	
	private int numTiles;
	
	
	
	
	
	
	

	
	
	private JPanel tileIDPanel;
	
	private JLabel tileIDLabel;
	
	private JPanel centerPanel;
	
	private JPanel soilTypePanel;
	
	private JLabel soilLabel;
	
	private JComboBox soilCombo;
	
	private JPanel inclinationPanel;
	
	private JLabel inclinationLabel;
	
	private JTextField inclinationField;
	
	
	private JPanel areaPanel;
	
	private JLabel areaLabe;
	
	private JTextField areaText;
	
	private JLabel priceLabe;
	
	private JTextField priceField;
	
	
	private JPanel buttonsPanel;
	
	private JButton saveButton;
	
	private JButton cancelButton;
	
	private JButton nextButton;
	
	private JButton previousButton;	
	
	
	public TileDialog(JFrame frame, boolean modal, String myMessage, Tile[] argTiles){
		
		super(frame,modal);
		
		this.tiles = argTiles;
		
		numTiles = tiles.length;
		

		getContentPane().setLayout(new BorderLayout());

		createWidgets();
		addWidgets(getContentPane());

		pack();
		setLocationRelativeTo(frame);
		setVisible(true);
		
	}


	private void createWidgets() {
		
		tileIDPanel = new JPanel();
		tileIDPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		tileIDLabel = new JLabel("Tile ID: " + tileID);
	
		
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		soilTypePanel = new JPanel();
		soilTypePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		soilLabel = new JLabel("Soil type:");
		
		String[] soilTypes = {"Clay", "Peaty", "Sandy", "Silty", "Rock"};
		soilCombo = new JComboBox(soilTypes);
		soilCombo.setSelectedIndex(0);
		soilCombo.setSize(100, 0);
		
		inclinationPanel = new JPanel();
		inclinationPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		inclinationLabel = new JLabel("Inclination:");
		
		if (tiles[tileID] == null){
			inclinationField = new JTextField("0",4);
		} else {
			
			Integer incl = tiles[tileID].getMaxInclination();		
			inclinationField = new JTextField(incl.toString(),4);
			
		}
		
		
		
		
	}


	private void addWidgets(Container contentPane) {
		
		tileIDPanel.add(tileIDLabel);
		contentPane.add(tileIDPanel, BorderLayout.NORTH);
		
		soilTypePanel.add(soilLabel);
		soilTypePanel.add(soilCombo);
		centerPanel.add(soilTypePanel);
		
		inclinationPanel.add(inclinationLabel);
		inclinationPanel.add(inclinationField);
		centerPanel.add(inclinationPanel);
		
		contentPane.add(centerPanel,BorderLayout.CENTER);
		
		
		
	}
	
}
